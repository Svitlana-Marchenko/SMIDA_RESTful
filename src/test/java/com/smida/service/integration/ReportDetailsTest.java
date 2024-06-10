package com.smida.service.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ReportDetailsTest {

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
    @WithMockUser(username = "test", authorities = "ADMIN")
    public void testGetAllReports_ShouldReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/report-details"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username = "col", authorities = "ADMIN")
    public void testPostReportWithoutOkId_shouldReturn500() throws Exception {
        String companyJson = "{ \"id\": \"d290f1ee-6c54-4b01-90e6-d701748f0851\",\n" +
                "  \"financialData\": \"{test}\",\n" +
                "  \"comments\": \"{test}\"";

        mockMvc.perform(post("/api/v1/report-details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(companyJson))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testGetReportByNonId_shouldReturn404() throws Exception {
        mockMvc.perform(get("/api/v1/report-details/{id}", 1))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testDeleteReportByNonId_shouldReturn404() throws Exception {
        mockMvc.perform(delete("/api/v1/report-details/{id}", 1))
                .andExpect(status().is4xxClientError());
    }

}

