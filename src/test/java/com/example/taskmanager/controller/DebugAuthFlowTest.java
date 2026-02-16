package com.example.taskmanager.controller;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class DebugAuthFlowTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;

    @Test
    void printRegisterAndLogin() throws Exception {
        var reg = mvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(Map.of("username","tuser","password","p"))))
                .andReturn().getResponse();
        System.out.println("REGISTER status=" + reg.getStatus() + " body=" + reg.getContentAsString());

        var login = mvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(Map.of("username","tuser","password","p"))))
                .andReturn().getResponse();
        System.out.println("LOGIN status=" + login.getStatus() + " body=" + login.getContentAsString());
    }
}
