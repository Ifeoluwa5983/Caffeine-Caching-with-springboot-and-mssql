package com.caffeine.Caffeine.Caching.controllers;

import com.caffeine.Caffeine.Caching.models.CompanyDirector;
import com.caffeine.Caffeine.Caching.models.CustomerSignatory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CustomerSignatoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper;

    CustomerSignatory customerSignatory;

    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
        customerSignatory = new CustomerSignatory();
    }

    @Test
    void testCreateCustomerSignatoryEndpoint_thenReturnOK() throws Exception {
        customerSignatory.setCustomerId(1L);
        customerSignatory.setId(4L);
        customerSignatory.setBvn("1234678");
        customerSignatory.setEmail("test@gmail.com");
        customerSignatory.setCity("Ojuelegba");
        customerSignatory.setSignatoryName("iclass");
        customerSignatory.setCountry("Nigeria");

        this.mockMvc.perform(post("/customer/create-customer-signatory")
                .contentType("application/json")
                .content(mapper.writeValueAsString(customerSignatory)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void testUpdateCustomerSignatoryEndpoint_thenReturnOK() throws Exception {
        customerSignatory.setCustomerId(1L);
        customerSignatory.setId(4L);
        customerSignatory.setEmail("ifeoluwa@gmail.com");
        customerSignatory.setMothersMaidenName("Mark");
        customerSignatory.setPhoneNo("09087654321");

        this.mockMvc.perform(put("/customer/updateCustomerSignatory")
                .contentType("application/json")
                .content(mapper.writeValueAsString(customerSignatory)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testGetAllCustomerSignatoryEndpoint() throws Exception {
        this.mockMvc.perform(get("/customer/all-signatories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testGetCustomerSignatoryByIdEndpoint() throws Exception {
        this.mockMvc.perform(get("/customer/customer-signatory/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testDeleteSignatoriesByIdEndpoint() throws Exception {
        this.mockMvc.perform(delete("/customer/signatories/delete/3"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }




}