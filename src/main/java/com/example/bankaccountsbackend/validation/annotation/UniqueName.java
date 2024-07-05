package com.example.bankaccountsbackend.validation.annotation;

import com.example.bankaccountsbackend.validation.UniqueBankAccountName;
import com.example.bankaccountsbackend.validation.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueBankAccountName.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueName {

    String message() default "Bank account already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
