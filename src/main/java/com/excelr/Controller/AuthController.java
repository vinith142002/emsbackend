package com.excelr.Controller;


import com.excelr.Repo.UserRepository;
import com.excelr.Service.AuthService;
import com.excelr.Service.ForgotPasswordService;
import com.excelr.Service.UserService;
import com.excelr.dto.*;
import com.excelr.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final ForgotPasswordService forgotPasswordService;
    private final PasswordEncoder passwordEncoder;
//    private final JWTutil jwtutil;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;  // Injected via constructor

    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest){
        if (authService.hasCustomerwithEmail(signupRequest.getEmail()))
            return new ResponseEntity<>("Customer already exists with this email", HttpStatus.NOT_ACCEPTABLE);
        UserDto createCustomerDto = authService.createCustomer(signupRequest);
        if (createCustomerDto == null)
            return new ResponseEntity<>("Customer not created, please try again later", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createCustomerDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
//        final String jwt = jwtutil.generateToken(userDetails);

        if (optionalUser.isPresent()) {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            return ResponseEntity.ok(authenticationResponse);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            forgotPasswordService.sendPasswordResetEmail(request.getEmail());
            return ResponseEntity.ok().body("Email with password reset link sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to send email. Please check your email and try again.");
        }
    }


    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        // Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email address"));

        // Check if the old password matches
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Old password is incorrect");
        }

        // Update user's password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return ResponseEntity.ok().body("Password reset successful");
    }



    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "Authorization")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal User user) {

        return ResponseEntity.ok().build();
    }
}

