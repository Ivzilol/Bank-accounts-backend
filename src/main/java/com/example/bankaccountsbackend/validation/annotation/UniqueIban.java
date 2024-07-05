package com.example.bankaccountsbackend.validation.annotation;

import com.example.bankaccountsbackend.validation.UniqueBankAccountIban;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueBankAccountIban.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueIban {

    String message() default "Iban already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
