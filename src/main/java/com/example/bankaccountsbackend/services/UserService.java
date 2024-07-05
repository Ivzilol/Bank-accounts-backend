package com.example.bankaccountsbackend.services;

import com.example.bankaccountsbackend.exceptions.UnMatchPasswordException;
import com.example.bankaccountsbackend.model.dto.UserRegistrationDTO;
import com.example.bankaccountsbackend.model.entity.Authority;
import com.example.bankaccountsbackend.model.entity.BankAccountsEntity;
import com.example.bankaccountsbackend.model.entity.UserEntity;
import com.example.bankaccountsbackend.repository.AuthorityRepository;
import com.example.bankaccountsbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserEntity> findUserByEmail(String email) {
        return this.userRepository.findByUsername(email);
    }

    public UserRegistrationDTO registerNewUser(UserRegistrationDTO userRegistrationDTO) {

        if (userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            UserEntity user = createUser(userRegistrationDTO);
            Authority authority = new Authority();
            authority.setAuthority("user");
            authority.setUser(user);
            this.authorityRepository.save(authority);
            return userRegistrationDTO;
        } else {
            throw new UnMatchPasswordException("Password not match");
        }

    }

    private UserEntity createUser(UserRegistrationDTO userRegistrationDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(userRegistrationDTO.getEmail());
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        List<BankAccountsEntity> userBankAccounts = new ArrayList<>();
        user.setBankAccounts(userBankAccounts);
        this.userRepository.save(user);
        return user;
    }

    public Optional<UserEntity> findCurrentUser(String username) {
        return this.userRepository.findByUsername(username);
    }
}
