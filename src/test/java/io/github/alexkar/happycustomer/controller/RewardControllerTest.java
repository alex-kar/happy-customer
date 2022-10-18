package io.github.alexkar.happycustomer.controller;

import io.github.alexkar.happycustomer.service.RewardRequestHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = RewardController.class)
@AutoConfigureMockMvc
@EnableWebMvc
class RewardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardRequestHandler rewardRequestHandler;

    @Test
    public void correctRequestTest() throws Exception {
        String customerId = "test_customer_id";

        mockMvc.perform(get("/v1/reward/customer/{id}", customerId))
                .andExpect(status().isOk());
        Mockito.verify(rewardRequestHandler, Mockito.times(1)).perCustomer(customerId);

        mockMvc.perform(get("/v1/reward/total", customerId))
                .andExpect(status().isOk());
        Mockito.verify(rewardRequestHandler, Mockito.times(1)).total();
    }

    @Test
    public void serverErrorTest() throws Exception {
        String customerId = "test_customer_id";
        Mockito.doThrow(RuntimeException.class)
                .when(rewardRequestHandler).perCustomer(customerId);
        Mockito.doThrow(RuntimeException.class)
                .when(rewardRequestHandler).total();

        mockMvc.perform(get("/v1/reward/customer/{id}", customerId))
                .andExpect(status().is5xxServerError());
        mockMvc.perform(get("/v1/reward/total", customerId))
                .andExpect(status().is5xxServerError());
    }

}