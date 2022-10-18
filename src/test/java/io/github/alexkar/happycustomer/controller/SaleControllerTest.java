package io.github.alexkar.happycustomer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.alexkar.happycustomer.dto.SaleRequestV1;
import io.github.alexkar.happycustomer.service.SaleRequestHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SaleController.class)
@AutoConfigureMockMvc
@EnableWebMvc
class SaleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaleRequestHandler saleRequestHandler;

    @ParameterizedTest
    @CsvSource(value = {
            "null | 2 | 100",
            "1 | null | 100",
            "1 | 2 | null",
            "null | null | null",
    }, nullValues = "null", delimiter = '|')
    public void badRequestTest(String consumerId, String itemId, Long price) throws Exception {
        SaleRequestV1 request = new SaleRequestV1();
        request.setCustomerId(consumerId);
        request.setItemId(itemId);
        request.setPrice(price);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(request));

        mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
    }

    @Test
    public void correctRequestTest() throws Exception {
        SaleRequestV1 request = new SaleRequestV1();
        request.setCustomerId("1");
        request.setItemId("2");
        request.setPrice(100L);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(request));

        mockMvc.perform(requestBuilder).andExpect(status().isOk());

        Mockito.verify(saleRequestHandler, Mockito.times(1)).handleSaleRequest(request);
    }

    @Test
    public void serverErrorTest() throws Exception {
        SaleRequestV1 request = new SaleRequestV1();
        request.setCustomerId("1");
        request.setItemId("2");
        request.setPrice(100L);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(request));
        Mockito.doThrow(RuntimeException.class)
                .when(saleRequestHandler).handleSaleRequest(request);

        mockMvc.perform(requestBuilder).andExpect(status().is5xxServerError());
    }


    @Test
    public void notFoundTest() throws Exception {
        SaleRequestV1 request = new SaleRequestV1();
        request.setCustomerId("1");
        request.setItemId("2");
        request.setPrice(100L);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/does_not_exist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(request));
        Mockito.doThrow(RuntimeException.class)
                .when(saleRequestHandler).handleSaleRequest(request);

        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError());
    }
}