package com.excelr.Service;

import com.excelr.Repo.UserRepository;
import com.excelr.Service.AuthService;
import com.excelr.dto.SignupRequest;
import com.excelr.dto.UserDto;
import com.excelr.entity.User;
import com.excelr.enums.UserRole;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount() {

       User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
       if (adminAccount == null){
           User newAdminAccount = new User();
           newAdminAccount.setName("Admin");
           newAdminAccount.setEmail("admin@gmail.com");
           newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin@123"));
           newAdminAccount.setUserRole(UserRole.ADMIN);
           userRepository.save(newAdminAccount);
           System.out.println("admin account is created successfully");
       }

    }
    @PostConstruct
    private void createUserAccount() {
        User userAccount = userRepository.findByUserRole(UserRole.USER);
        if (userAccount == null) {
            User newUserAccount = new User();
            newUserAccount.setName("Default User");
            newUserAccount.setEmail("user@gmail.com");
            newUserAccount.setPassword(new BCryptPasswordEncoder().encode("user@123"));
            newUserAccount.setUserRole(UserRole.USER);
            userRepository.save(newUserAccount);
            System.out.println("User account created successfully");
        }
    }


    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {

        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.USER);
        User createdUser = userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        return userDto;
    }

    @Override
    public boolean hasCustomerwithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findFirstByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Check if the password matches (you might want to use a secure password hashing method)
            return new BCryptPasswordEncoder().matches(password,user.getPassword())  ;
        }

        return false;
    }


	@Override
	public void sendEmail(String email, String string, String string2) {
		System.out.println("email sent");
		
	}

}




//    @Override
//    public boolean authenticateUser(String email, String password) {
//        Optional<User> userOptional = userRepository.findFirstByEmail(email);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            // Check if the password matches (you might want to use a secure password hashing method)
//            return user.getPassword().equals(password);
//        }
//        return false;
//    }



