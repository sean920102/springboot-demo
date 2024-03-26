package com.example.demo2.controller;

import com.example.demo2.constant.ProductCategory;
import com.example.demo2.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    public void createProduct() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProduct_name("Apple");
        productRequest.setCategory(ProductCategory.FOOD);
        productRequest.setImage_url("123.com");
        productRequest.setPrice(20);
        productRequest.setStock(10);
        productRequest.setDescription("");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(productRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/products/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();
    }

    @Test
    public void queryProduct() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products")
                .queryParam("productCategory",ProductCategory.FOOD.toString());

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
    }
}