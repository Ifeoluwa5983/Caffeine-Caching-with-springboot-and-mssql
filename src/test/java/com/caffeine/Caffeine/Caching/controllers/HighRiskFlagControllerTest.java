package com.caffeine.Caffeine.Caching.controllers;

import com.caffeine.Caffeine.Caching.models.HighRiskFlag;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HighRiskFlagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper;

    HighRiskFlag highRiskFlag;

    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
        highRiskFlag = new HighRiskFlag();
    }

    @Test
    void testCreateHighRiskFlagEndpoint_thenReturnOK() throws Exception {
        highRiskFlag.setCustomerId(1L);
        highRiskFlag.setId(1L);
        highRiskFlag.setHighRiskConfirmed(false);
        highRiskFlag.setHighRiskFlagReason("Disappointments");
        highRiskFlag.setHighRiskFlagMode("Standby");
        highRiskFlag.setHighRiskFlaggedBy("Mr yinka");


        this.mockMvc.perform(post("/customer/create-high-risk-flag")
                .contentType("application/json")
                .content(mapper.writeValueAsString(highRiskFlag)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void testUpdateHighRiskFlagEndpoint_thenReturnOK() throws Exception {
        highRiskFlag.setCustomerId(1L);
        highRiskFlag.setId(1L);
        highRiskFlag.setHighRiskConfirmedBy("Ifeoluwa");
        highRiskFlag.setHighRiskRule("Stict");


        this.mockMvc.perform(put("/customer/updateHighRiskFlag")
                .contentType("application/json")
                .content(mapper.writeValueAsString(highRiskFlag)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testGetAllHighRiskFlagEndpoint() throws Exception {
        this.mockMvc.perform(get("/customer/getAllHighRiskFlags"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testGetHighRiskFlagByIdEndpoint() throws Exception {
        this.mockMvc.perform(get("/customer/high-risk-flag/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testDeleteHighRiskFlagByIdEndpoint() throws Exception {
        this.mockMvc.perform(delete("/customer/highFlagRisk/delete/3"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }


}