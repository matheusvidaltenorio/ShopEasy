package com.shopeasy.presentation.controller;

import com.shopeasy.presentation.dto.OrderItemRequestDTO;
import com.shopeasy.presentation.dto.OrderItemResponseDTO;
import com.shopeasy.presentation.dto.OrderRequestDTO;
import com.shopeasy.presentation.dto.OrderResponseDTO;
import com.shopeasy.application.service.OrderItemsService;
import com.shopeasy.application.service.OrdersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/orders")
public class OrdersController {
    private final OrderItemsService orderItemsService;
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService, OrderItemsService orderItemsService) {
        this.ordersService = ordersService;
        this.orderItemsService = orderItemsService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.status(CREATED).body(ordersService.createOrder(orderRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> orderList() {
        return ResponseEntity.ok(ordersService.orderList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findOrder(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(ordersService.findOrderById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable("id") Integer id, @Valid @RequestBody OrderRequestDTO order) {
        return ResponseEntity.ok(ordersService.updateOrder(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Integer id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<OrderItemResponseDTO> addItem(@PathVariable("orderId") Integer orderId, @Valid @RequestBody OrderItemRequestDTO item) {
        return ResponseEntity.status(CREATED).body(orderItemsService.addItem(orderId, item));
    }

    @GetMapping("/items/{orderItemId}")
    public ResponseEntity<OrderItemResponseDTO> findItem(@PathVariable("orderItemId") Integer orderItemId) {
        return ResponseEntity.ok(orderItemsService.findItemById(orderItemId));
    }

    @PutMapping("/items/{orderItemId}")
    public ResponseEntity<OrderItemResponseDTO> updateItem(@PathVariable("orderItemId") Integer orderItemId, @Valid @RequestBody OrderItemRequestDTO item) {
        return ResponseEntity.ok(orderItemsService.updateItem(orderItemId, item));
    }

    @DeleteMapping("/items/{orderItemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable("orderItemId") Integer orderItemId) {
        orderItemsService.deleteItem(orderItemId);
        return ResponseEntity.noContent().build();
    }
}

