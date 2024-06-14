package com.excelr.Service;


import com.excelr.Repo.UserRepository;
import com.excelr.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {
	private final UserRepository userRepository;
	private final AuthService emailService;

	@Autowired
	public ForgotPasswordService(UserRepository userRepository, AuthService emailService) {
		this.userRepository = userRepository;
		this.emailService = emailService;
	}
	 public void sendPasswordResetEmail(String email) {
	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

	        String resetToken = generateResetToken();
	        user.setResetToken(resetToken);
	        userRepository.save(user);

	        String resetLink = "http://localhost:3000/reset-password?token=" + resetToken;
	        emailService.sendEmail(email, "Password Reset Request", "Please click the following link to reset your password: " + resetLink);
	    }

	    private String generateResetToken() {
			return null;
	        // generate and return a unique reset token
	    }
	    public void resetPassword(String token, String newPassword) {
	        User user = userRepository.findByResetToken(token)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found with token: " + token));

	        user.setPassword(newPassword);
	        user.setResetToken(null);
	        userRepository.save(user);
	    }


}
