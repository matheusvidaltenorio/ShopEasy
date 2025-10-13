package com.shopeasy.presentation.dto;

import com.shopeasy.domain.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponseDTO {
    private Integer id;

    private UserResponseDTO user;

    private Status status;

    private BigDecimal total;

    private LocalDateTime createdAt;

    private List<OrderItemResponseDTO> items;
}
