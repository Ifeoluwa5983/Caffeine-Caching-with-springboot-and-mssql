package com.caffeine.Caffeine.Caching.controllers;

import com.caffeine.Caffeine.Caching.models.CompanyDirector;
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
class CompanyDirectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper;

    CompanyDirector companyDirector;

    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
        companyDirector = new CompanyDirector();
    }

    @Test
    void testCreateCompanyDirectorEndpoint_thenReturnOK() throws Exception {
        companyDirector.setCustomerId(1L);
        companyDirector.setId(3L);
        companyDirector.setDirectorName("Mr Yinka");
        companyDirector.setBvn("1234678");
        companyDirector.setEmail("yinka@gmail.com");

        this.mockMvc.perform(post("/customer/create-customer-director")
                .contentType("application/json")
                .content(mapper.writeValueAsString(companyDirector)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void testUpdateCompanyDirectorEndpoint_thenReturnOK() throws Exception {
        companyDirector.setCustomerId(1L);
        companyDirector.setId(3L);
        companyDirector.setBvn("123467890");
        companyDirector.setCountry("Nigeria");
        companyDirector.setCity("Sabo");

        this.mockMvc.perform(put("/customer/updateCustomerDirector")
                .contentType("application/json")
                .content(mapper.writeValueAsString(companyDirector)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testGetAllCompanyDirectorEndpoint() throws Exception {
        this.mockMvc.perform(get("/customer/all-directors"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testGetCompanyDirectorByIdEndpoint() throws Exception {
        this.mockMvc.perform(get("/customer/customer-director/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testDeleteCompanyDirectorByIdEndpoint() throws Exception {
        this.mockMvc.perform(delete("/customer/delete/3"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

}