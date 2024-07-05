package com.example.bankaccountsbackend.IntegrationTests;


import com.example.bankaccountsbackend.model.dto.UserRegistrationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/users");

        UserRegistrationDTO userRegisterDTO = new UserRegistrationDTO();
        userRegisterDTO.setEmail("ivo_ali@abv.bg");
        userRegisterDTO.setPassword("asdasd");
        userRegisterDTO.setConfirmPassword("asdasd");
        userRegisterDTO.setFirstName("Gosho");
        userRegisterDTO.setLastName("Georgiev");

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegisterDTO)));
    }

    @Test
    public void userRegister_Successful_Test() throws Exception {
        UserRegistrationDTO userRegisterDTO = new UserRegistrationDTO();
        userRegisterDTO.setEmail("ivo_ali3@abv.bg");
        userRegisterDTO.setPassword("asdasd");
        userRegisterDTO.setConfirmPassword("asdasd");
        userRegisterDTO.setFirstName("Gosho");
        userRegisterDTO.setLastName("Georgiev");

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterDTO)))
                .andExpect(status().isOk());
    }
}
