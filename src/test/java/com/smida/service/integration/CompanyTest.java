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
public class CompanyTest {

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
    public void testGetAllCompanies_ShouldReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/company"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username = "col", authorities = "ADMIN")
    public void testPostCompany_shouldReturn200() throws Exception {
        String companyJson = "{\n" +
                "  \"name\": \"Test Company\",\n" +
                "  \"registration_number\": \"123456789\",\n" +
                "  \"address\": \"123 Test Street\"\n" +
                "}";

        mockMvc.perform(post("/api/v1/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(companyJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCompanyByNonId_shouldReturn404() throws Exception {
        mockMvc.perform(get("/api/v1/company/{id}", 1))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testDeleteCompanyByNonId_shouldReturn404() throws Exception {
        mockMvc.perform(delete("/api/v1/curator/{id}", 1))
                .andExpect(status().is4xxClientError());
    }

}
