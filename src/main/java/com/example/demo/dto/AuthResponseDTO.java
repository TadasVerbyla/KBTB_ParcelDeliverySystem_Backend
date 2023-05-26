package com.example.demo.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

public class AuthResponseDTO {
    private String username;
    private LocalDateTime expTime;

    public AuthResponseDTO(String username, LocalDateTime expTime) {
        this.username = username;
        this.expTime = expTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getExpTime() {
        return expTime;
    }

    public void setExpTime(LocalDateTime expTime) {
        this.expTime = expTime;
    }
}
