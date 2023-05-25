package com.example.demo.controllers;

import com.example.demo.dto.AuthHeaderDTO;
import com.example.demo.services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/V1/auth-header")
@CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = "*", allowCredentials = "true")
public class AuthController {
  @Autowired
  public AuthController() {}
   /* @GetMapping
    public String getLoggedInCreds(Principal principal) {
        String username = principal.getName();

        return username;
    }*/
 /* @GetMapping
  public ResponseEntity<String> getAuthHeader(HttpServletRequest request) {
      HttpSession session = request.getSession(false);

      if (session != null) {
          String authorizationHeader = (String) session.getAttribute("Authorization");

          if (authorizationHeader != null) {
              return ResponseEntity.ok(authorizationHeader);
          }
      }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
  }*/
     @GetMapping
    public ResponseEntity<AuthHeaderDTO> getAuthHeader(HttpServletRequest request) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         AuthHeaderDTO authHeaderDTO = AuthHeaderDTO.fromAuthentication(authentication);

         if (authHeaderDTO != null) {
             return ResponseEntity.ok(authHeaderDTO);
         }
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}
