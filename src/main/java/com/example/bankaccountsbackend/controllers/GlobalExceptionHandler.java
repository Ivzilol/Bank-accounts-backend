package com.example.bankaccountsbackend.controllers;

import com.example.bankaccountsbackend.exceptions.UnMatchPasswordException;
import com.example.bankaccountsbackend.exceptions.UserNotUniqueException;
import com.example.bankaccountsbackend.model.dto.UserErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler(UserNotUniqueException.class)
    public ResponseEntity<UserErrorDto> userNotUnique(UserNotUniqueException unie) {
        UserErrorDto userErrorDto = new UserErrorDto
                (unie.getEmail(), "Email is already exist!");
        return
                ResponseEntity.status(HttpStatus.CONFLICT).body(userErrorDto);
    }

    @ExceptionHandler(UnMatchPasswordException.class)
    public ResponseEntity<?> passwordNotMatch(UnMatchPasswordException exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
    }
}
