package com.example.demo.controllers;

import com.example.demo.dto.AuthResponseDTO;
import com.example.demo.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Base64;

@RestController
@RequestMapping("api/V1/login")
@CrossOrigin("*")
public class LoginController {
    private final AuthService authService;
  @Autowired
  public LoginController(AuthService authService) {this.authService = authService; }

   @GetMapping
   public ResponseEntity<AuthResponseDTO> getAuthResponse(HttpServletRequest request) {

       LocalDateTime expirationTime = LocalDateTime.now().plusWeeks(1);
       String username = authService.extractUsernameFromAuthHeader(request);

       AuthResponseDTO authResponse = new AuthResponseDTO(username, expirationTime);
       return ResponseEntity.ok(authResponse);
   }

}
