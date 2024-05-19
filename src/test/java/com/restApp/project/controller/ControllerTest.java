package com.restApp.project.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addCat() throws Exception {

        String jsonRequest = "{\"name\":\"Boris\",\"age\":18,\"weight\":100}";

        var response = mockMvc.perform(
                post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(jsonRequest)
        ).andReturn();
        String resultJson = response.getResponse().getContentAsString();
        assertEquals("{\"id\":1\"name\":\"Boris\",\"age\":18,\"weight\":100}", resultJson);
    }
}
