package com.example.demo.controllers;

import com.example.demo.dto.AuthResponseDTO;
import com.example.demo.entities.Customer;
import com.example.demo.services.AuthService;
import com.example.demo.services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("api/V1/login")
@CrossOrigin("*")
public class LoginController {
    private final AuthService authService;
    private final CustomerService customerService;

  @Autowired
  public LoginController(AuthService authService, CustomerService customerService) {
      this.authService = authService;
      this.customerService = customerService;
  }

   @GetMapping
   public ResponseEntity<AuthResponseDTO> getAuthResponse(HttpServletRequest request) {

       LocalDateTime expirationTime = LocalDateTime.now().plusWeeks(1);
       String username = authService.extractUsernameFromAuthHeader(request);

       AuthResponseDTO authResponse = new AuthResponseDTO(username, expirationTime);
       return ResponseEntity.ok(authResponse);
   }

}
