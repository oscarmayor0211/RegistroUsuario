package com.nisum.createUser.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.nisum.createUser.models.User;
import com.nisum.createUser.service.impl.user.UserFaker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UserServiceImplTest {
    private static final String PATH = "/user";

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("When create user is called then return created")
    void whenCreateUserIsCalledThenReturnCreated() throws Exception {

        when(userService.createUser(any())).thenReturn(UserFaker.userFaker());

        mockMvc.perform(
                        post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(UserFaker.userFaker()))
                )
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("When create user is called with empty body then return bad request")
    void whenCreateUserIsCalledWithEmptyBodyThenReturnBadRequest() throws Exception {

        mockMvc.perform(
                        post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content("")
                )
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("When create user is called with incorrect body then return bad request")
    void whenCreateUserIsCalledWithIncorrectBodyThenReturnBadRequest() throws Exception {

        User user = new User();
        user.setName("Oscar");
        user.setEmail(null);

        mockMvc.perform(
                        post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user))
                )
                .andExpect(status().isBadRequest());

    }
}