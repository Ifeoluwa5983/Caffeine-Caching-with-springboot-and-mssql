package com.caffeine.Caffeine.Caching.controllers;

import com.caffeine.Caffeine.Caching.models.Customers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    Customers customer;
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        customer = new Customers();
        mapper = new ObjectMapper();
    }

    @Test
    void testThatWeCanFindAllUsers() throws Exception {
        this.mockMvc.perform(get("/all-customers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testThatWeCanSearchForUsers() throws Exception {
        this.mockMvc.perform(get("/search-customers/b"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testCustomerEndpoint_thenReturnOK() throws Exception {
        customer.setEmail("test2@gmail.com");
        customer.setFirstName("iclass");
        customer.setLastName("chima");
        customer.setPhoneRef("09087654321");

        this.mockMvc.perform(post("/customer/register")
                .contentType("application/json")
                .content(mapper.writeValueAsString(customer)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void testGetHighCustomerByIdEndpoint() throws Exception {
        this.mockMvc.perform(get("/customer/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testGetAllEventsOfACustomerByIdEndpoint() throws Exception {
        this.mockMvc.perform(get("/customer/1/events"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}