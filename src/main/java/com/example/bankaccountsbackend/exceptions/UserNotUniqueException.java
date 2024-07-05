package com.example.bankaccountsbackend.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Email is not unique.")
@Getter
public class UserNotUniqueException extends RuntimeException{

    private final String email;


    public UserNotUniqueException(String email){
        super("There is already a registered user with this email " + email + "!");
        this.email = email;
    }
}
