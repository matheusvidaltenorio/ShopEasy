package com.shopeasy.presentation.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    @NotNull
    @Positive
    private Integer userId;

    @NotBlank
    private String status;

    @Valid
    @NotNull
    @NotEmpty
    private List<OrderItemRequestDTO> orderItems;
}
