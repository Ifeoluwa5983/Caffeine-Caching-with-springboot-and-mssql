package com.caffeine.Caffeine.Caching.controllers;

import com.caffeine.Caffeine.Caching.models.KYCDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class KYCDetailsControllerTest {


    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper;

    KYCDetail kycDetail;

    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
        kycDetail = new KYCDetail();
    }

    @Test
    void testCreateKYCDetailEndpoint_thenReturnOK() throws Exception {
        kycDetail.setCustomerId(1L);
        kycDetail.setId(3L);
        kycDetail.setDocumentComments("Nice");
        kycDetail.setDocumentConfirmed(false);
        kycDetail.setDocumentConfirmedBy("Ife");

        this.mockMvc.perform(post("/customer/create-KYCDetail")
                .contentType("application/json")
                .content(mapper.writeValueAsString(kycDetail)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void testGetAllKYCDetailsEndpoint() throws Exception {
        this.mockMvc.perform(get("/customer/all-KYCDetails"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testGetKYCDetailByIdEndpoint() throws Exception {
        this.mockMvc.perform(get("/customer/getKYCDetailById/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testUpdateKYCDetailEndpoint_thenReturnOK() throws Exception {

        kycDetail.setCustomerId(1L);
        kycDetail.setId(3L);
        kycDetail.setDocumentValid(true);
        kycDetail.setDocumentValid(true);
        kycDetail.setDocumentReference("Jagons");

        this.mockMvc.perform(put("/customer/updateKYCDetails")
                .contentType("application/json")
                .content(mapper.writeValueAsString(kycDetail)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testDeleteKYCDetailsByIdEndpoint() throws Exception {
        this.mockMvc.perform(delete("/customer/KYCDetails/delete/3"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

}