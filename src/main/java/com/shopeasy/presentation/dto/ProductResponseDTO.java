package com.shopeasy.presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponseDTO {
    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer stock;
}
