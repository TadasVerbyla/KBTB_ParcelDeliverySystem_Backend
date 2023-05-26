package com.example.demo.controllers;

import com.example.demo.dto.AuthResponseDTO;
import com.example.demo.services.AuthService;
import com.example.demo.services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/V1/auth")
@CrossOrigin("*")
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {this.authService = authService; }

    @GetMapping
    public ResponseEntity<Void> checkExpirationTime(@RequestParam("expirationTime") String expirationTimeStr) {
        LocalDateTime expirationTime = LocalDateTime.parse(expirationTimeStr);
        LocalDateTime currentTime = LocalDateTime.now();

        if (expirationTime.isBefore(currentTime)) {
            return ResponseEntity.status(440).build();
        }

        return ResponseEntity.ok().build();
    }
    @GetMapping("/name")
    public ResponseEntity<String> getUsername(HttpServletRequest request) {
        String username = authService.extractUsernameFromAuthHeader(request);
        return ResponseEntity.ok(username);
    }
   /* @GetMapping
    public String getLoggedInCreds(Principal principal) {
        String username = principal.getName();

        return username;
    }*/

}
