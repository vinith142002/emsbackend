package com.excelr.dto;


import com.excelr.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
//    private String jwt;

    private UserRole userRole;

    private Long userId;
}
