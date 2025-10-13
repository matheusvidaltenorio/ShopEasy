package com.shopeasy.application.service;

import com.shopeasy.domain.entity.Product;
import com.shopeasy.infrastructure.database.ProductRepository;
import com.shopeasy.presentation.dto.ProductRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductsServiceTest {
    @InjectMocks
    private ProductsService productsService;
    @Mock
    private ProductRepository productRepository;

    @Test
    void givenProductRequest__whenRequestValid__thenCreate() {
        final ProductRequestDTO productRequestDTO = ProductRequestDTO.builder()
                .name("product")
                .price(BigDecimal.ONE)
                .stock(1)
                .build();
        when(productRepository.save(any())).thenReturn(new Product());
        final Product product = productsService.createProduct(productRequestDTO);
        assertNotNull(product);
    }

    @Test
    void givenProductRequest__whenPriceLessOrEqualThanZero__thenThrowException() {
        final ProductRequestDTO productRequestDTO = ProductRequestDTO.builder()
                .name("product")
                .price(BigDecimal.ZERO)
                .stock(1)
                .build();
        assertThrows(IllegalArgumentException.class, () -> productsService.createProduct(productRequestDTO));
    }

    @Test
    void givenProductRequest__whenPriceIsNull__thenThrowException() {
        final ProductRequestDTO productRequestDTO = ProductRequestDTO.builder()
                .name("product")
                .stock(1)
                .build();
        assertThrows(IllegalArgumentException.class, () -> productsService.createProduct(productRequestDTO));
    }

    @Test
    void givenProductRequest__whenStockLessThanZero__thenThrowException() {
        final ProductRequestDTO productRequestDTO = ProductRequestDTO.builder()
                .name("product")
                .stock(-1)
                .price(BigDecimal.ONE)
                .build();
        assertThrows(IllegalArgumentException.class, () -> productsService.createProduct(productRequestDTO));
    }
}