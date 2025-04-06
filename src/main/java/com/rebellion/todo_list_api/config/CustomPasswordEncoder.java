package com.rebellion.todo_list_api.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomPasswordEncoder {

    public String encode(String rawPassword) {
        try {
            // Create SHA-256 MessageDigest instance
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Convert password string to bytes and update the digest
            byte[] hashedBytes = md.digest(rawPassword.getBytes());

            // Convert byte array into a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString(); // return the hashed password
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
        
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return (this.encode(rawPassword)).equals(encodedPassword);
    }
}
