package com.shopeasy.presentation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    @NotNull(message = "Nome é obrigatório")
    private String name;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser positivo")
    private BigDecimal price;

    @NotNull(message = "Estoque é obrigatório")
    @Positive(message = "Estoque deve ser positivo")
    private Integer stock;
}
