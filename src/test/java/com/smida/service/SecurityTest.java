package com.smida.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testGetUnauthorizedRequestOnCompanyController_shouldReturn403() throws Exception {
            mockMvc.perform(get("/api/v1/company")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetUnauthorizedRequestOnReportController_shouldReturn403() throws Exception {
        mockMvc.perform(get("/api/v1/report")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetUnauthorizedRequestOnReportDetailsController_shouldReturn403() throws Exception {
        mockMvc.perform(get("/api/v1/report-details")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "TEST", authorities = "ADMIN")
    public void testGetAuthorizedRequestOnCompanyController_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/company")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "TEST", authorities = "ADMIN")
    public void testGetAuthorizedRequestOnReportController_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/report")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "TEST", authorities = "ADMIN")
    public void testGetAuthorizedRequestOnReportDetailsController_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/report-details")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void testUnauthorizedPostRequestOnCompany_shouldReturn400() throws Exception {
        mockMvc.perform(post("/api/v1/company"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUnauthorizedPostRequestOnReport_shouldReturn400() throws Exception {
        mockMvc.perform(post("/api/v1/report"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUnauthorizedPostRequestOnReportDetails_shouldReturn400() throws Exception {
        mockMvc.perform(post("/api/v1/report-details"))
                .andExpect(status().is4xxClientError());
    }


}

