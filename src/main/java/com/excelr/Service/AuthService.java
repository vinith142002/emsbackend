package com.excelr.Service;


import com.excelr.dto.SignupRequest;
import com.excelr.dto.UserDto;

public interface AuthService {
    UserDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerwithEmail(String email);

    boolean authenticateUser(String email, String password);

	void sendEmail(String email, String string, String string2);
}

