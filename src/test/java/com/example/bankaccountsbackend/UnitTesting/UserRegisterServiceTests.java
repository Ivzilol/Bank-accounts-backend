package com.example.bankaccountsbackend.UnitTesting;


import com.example.bankaccountsbackend.model.entity.UserEntity;
import com.example.bankaccountsbackend.repository.UserRepository;
import com.example.bankaccountsbackend.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserRegisterServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findUserByEmail_NullOrEmptyValue_ShouldReturnNull() {

        String result = String.valueOf(userService.findUserByEmail(" "));
        assertNull(result);

        result = String.valueOf(userService.findUserByEmail(""));
        assertNull(result);

        result = String.valueOf(userService.findUserByEmail("   "));
        assertNull(result);
    }

    @Test
    public void findUserByEmail_UserNotFound_ShouldReturnNull() {
        when(userRepository.findByUsername(anyString())).thenReturn(null);

        String result = String.valueOf(userRepository.findByUsername("pesho@gmail.com"));
        assertNull(result);
    }

    @Test
    public void findUserByEmail_UserFound_ShouldReturnEmail() {
        UserEntity user = new UserEntity();
        user.setUsername("ivo@gmail.com");

        when(userRepository.findByUsername("ivo@gmail.com")).thenReturn(Optional.of(user));

        String result = String.valueOf(userRepository.findByUsername("ivo@gmail.com"));
        assertEquals("ivo@gmail.com", result);
    }
}
