package com.example.bankaccountsbackend.controllers;

import com.example.bankaccountsbackend.model.dto.UserRegistrationDTO;
import com.example.bankaccountsbackend.services.UserService;
import com.example.bankaccountsbackend.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
        return ResponseEntity.ok(userService.registerNewUser(userRegistrationDTO));
    }
}
