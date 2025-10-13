package com.shopeasy.presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItemResponseDTO {
    private Integer id;

    private ProductResponseDTO product;

    private Integer quantity;

    private BigDecimal price;
}
