package com.example.demo.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Base64;

@Service
@RequestScope
public class AuthService {
    public String extractUsernameFromAuthHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            String base64Credentials = authorizationHeader.substring(6);
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            String[] parts = credentials.split(":");
            if (parts.length == 2) {
                return parts[0];
            }
        }
        return null;
    }
}
