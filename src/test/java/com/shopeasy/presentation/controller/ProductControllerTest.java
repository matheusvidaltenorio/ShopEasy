package com.shopeasy.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.shopeasy.application.service.ProductsService;
import com.shopeasy.domain.entity.Product;
import com.shopeasy.presentation.dto.ProductRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductsService productService;

    @Test
    public void givenProductURIWithPost__whenMockMVC__thenVerifyResponse() throws Exception {
        final ProductRequestDTO productRequestDTO = ProductRequestDTO.builder()
                .name("product")
                .price(BigDecimal.ONE)
                .stock(1)
                .build();
        final Product resp = Product.builder()
                .id(1)
                .name("product")
                .price(BigDecimal.ONE)
                .stock(1)
                .build();
        when(productService.createProduct(any(ProductRequestDTO.class))).thenReturn(resp);
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        final ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        final String requestJson = ow.writeValueAsString(productRequestDTO);
        mockMvc.perform(post("/v1/products")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated()).andExpect(content().contentType("application/json"));
    }
}