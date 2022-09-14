package com.itentika.autoservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest()
@Transactional
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testOrderCreate_succeed() throws Exception {
        String input = """
            {
              "reason": "test reason",
              "beginDate": "2022-10-10T10:00",
              "client": {
                "name": "Alex",
                "phoneNumber": "+79150001122"
              },
              "administrator": {
                  "id": 1
              }
            }
        """;

        this.mvc.perform(
            post("/order")
                .content(input)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        ;
    }

    @Test
    void testOrderAcceptNonexistent_throwsException() throws Exception {
        String input = """
                {
                  "id": 1,
                  "master": {
                      "id": 2
                  }
                }""";

        this.mvc.perform(
            patch("/order/accept")
                .content(input)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        ;
    }
}