package com.example.taskmanager.controller;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class DebugTaskFlowTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;

    @Test
    void debugCreateTask() throws Exception {
        mvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(Map.of("username","dbg","password","p"))))
                .andReturn();

        String res = mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(Map.of("username","dbg","password","p"))))
                .andReturn().getResponse().getContentAsString();
        System.out.println("LOGIN RES: " + res);
        JsonNode node = mapper.readTree(res);
        String token = node.has("token") ? node.get("token").asText() : null;

        String body = mapper.writeValueAsString(Map.of("title","DBG","description","d"));
        var mvcRes = mvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .content(body)).andReturn().getResponse();

        System.out.println("TASK STATUS: " + mvcRes.getStatus());
        System.out.println("TASK BODY: " + mvcRes.getContentAsString());
    }
}
