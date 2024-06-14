package com.excelr.dto;

import java.util.UUID;

public class ResetTokenGenerator {

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}
