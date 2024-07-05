package com.example.bankaccountsbackend.model.dto;


import com.example.bankaccountsbackend.validation.annotation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistrationDTO {

    @UniqueEmail(message = "There is already a registered user with this username.")
    @Email (message = "Must use valid email")
    @NotEmpty(message = "Email should be provided.")
    private String email;

    @Size(min = 6, message = "Password must be min 6 symbols")
    @NotNull
    private String password;

    @Size(min = 6, message = "Password must be min 6 symbols")
    @NotNull
    private String confirmPassword;

    @Size(min = 3, message = "First Name must be min 3 symbols")
    @NotNull
    private String firstName;

    @Size(min = 3, message = "Last Name must be min 3 symbols")
    @NotNull
    private String lastName;


}
