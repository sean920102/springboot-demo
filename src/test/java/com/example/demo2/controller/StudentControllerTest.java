package com.example.demo2.controller;

import com.example.demo2.modal.Student;
import com.example.demo2.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentService studentService;

    @Test
    public void getById() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/student/{id}",2)
                .header("headerName","headerValue")
                .queryParam("name","mike");

        MvcResult mvcResult =
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.id", equalTo(2) ))
                .andExpect(jsonPath("$.name", notNullValue()))
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString();
        System.out.println(body);
    }

    @Test
    @Transactional
    public void create() throws Exception {
        Student student = new Student();
        student.setName("Sean");
        student.setId(5);
        student.setAge(22);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(student);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/student/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();
    }
}