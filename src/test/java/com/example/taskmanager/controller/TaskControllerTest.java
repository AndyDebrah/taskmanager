package com.example.taskmanager.controller;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;

        private String token;
        private String username;

    @BeforeEach
    void setup() throws Exception {
        // register and login with unique username per test
        username = "tuser_" + System.currentTimeMillis();
        mvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(Map.of("username", username, "password","p"))))
                .andExpect(status().isOk());

        String res = mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(Map.of("username", username, "password","p"))))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println("LOGIN RES: " + res);
        JsonNode node = mapper.readTree(res);
        token = node.get("token").asText();
        System.out.println("TOKEN: " + token);
    }

    @Test
    void postCreateTask() throws Exception {
        String body = mapper.writeValueAsString(Map.of("title","Buy milk","description","desc"));
        String out = mvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer "+token)
                .content(body))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        JsonNode n = mapper.readTree(out);
        assertThat(n.get("title").asText()).isEqualTo("Buy milk");
    }

    @Test
    void getTasksForUser() throws Exception {
        // create a task
        mvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer "+token)
                .content(mapper.writeValueAsString(Map.of("title","T1","description","d"))))
                .andExpect(status().isCreated());

        String list = mvc.perform(get("/api/tasks").header("Authorization","Bearer "+token))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        JsonNode arr = mapper.readTree(list);
        assertThat(arr.isArray()).isTrue();
        assertThat(arr.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void updateTaskStatus() throws Exception {
        String out = mvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer "+token)
                .content(mapper.writeValueAsString(Map.of("title","Up","description","d"))))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        JsonNode n = mapper.readTree(out);
        long id = n.get("id").asLong();

        String upd = mvc.perform(put("/api/tasks/"+id+"?status=COMPLETED").header("Authorization","Bearer "+token))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        JsonNode u = mapper.readTree(upd);
        assertThat(u.get("status").asText()).isEqualTo("COMPLETED");
    }

    @Test
    void unauthorizedRequestGets401() throws Exception {
        mvc.perform(get("/api/tasks")).andExpect(status().isForbidden());
    }

        @Test
        void deleteTask() throws Exception {
        String out = mvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
            .header("Authorization","Bearer "+token)
            .content(mapper.writeValueAsString(Map.of("title","ToDelete","description","d"))))
            .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        JsonNode n = mapper.readTree(out);
        long id = n.get("id").asLong();

        mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/tasks/"+id)
            .header("Authorization","Bearer "+token))
            .andExpect(status().isNoContent());

        // verify gone
        String list = mvc.perform(get("/api/tasks").header("Authorization","Bearer "+token))
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        JsonNode arr = mapper.readTree(list);
        boolean exists = false;
        for (JsonNode it : arr) if (it.get("id").asLong()==id) exists = true;
        assertThat(exists).isFalse();
        }
}
