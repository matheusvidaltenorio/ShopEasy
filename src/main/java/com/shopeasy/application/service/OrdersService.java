package com.shopeasy.application.service;

import com.shopeasy.presentation.dto.OrderItemRequestDTO;
import com.shopeasy.presentation.dto.OrderItemResponseDTO;
import com.shopeasy.presentation.dto.OrderRequestDTO;
import com.shopeasy.presentation.dto.OrderResponseDTO;
import com.shopeasy.presentation.dto.ProductResponseDTO;
import com.shopeasy.presentation.dto.UserResponseDTO;
import com.shopeasy.domain.entity.Order;
import com.shopeasy.domain.entity.OrderItem;
import com.shopeasy.domain.entity.Product;
import com.shopeasy.domain.entity.User;
import com.shopeasy.domain.enums.Status;
import com.shopeasy.infrastructure.database.OrderItemRepository;
import com.shopeasy.infrastructure.database.OrderRepository;
import com.shopeasy.infrastructure.database.ProductRepository;
import com.shopeasy.infrastructure.database.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static java.math.BigDecimal.ZERO;

@Service
public class OrdersService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrdersService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            UserRepository userRepository,
            ProductRepository productRepository
    ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    /**
     * Cria um novo pedido junto com seus itens.
     * O valor total é calculado automaticamente com base nos itens.
     */
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        final List<OrderItem> orderItems = orderRequestDTO.getOrderItems()
                .stream()
                .map(orderItem -> {
                    final Product product = productRepository.findById(orderItem.getProductId()).orElseThrow(EntityNotFoundException::new);
                    return OrderItem.builder()
                            .product(product)
                            .price(product.getPrice())
                            .quantity(orderItem.getQuantity())
                            .build();
                })
                .toList();
        final User user = userRepository.findById(orderRequestDTO.getUserId()).orElseThrow(EntityNotFoundException::new);
        final Order order = Order.builder()
                .user(user)
                .status(Status.valueOf(orderRequestDTO.getStatus().toUpperCase()))
                .createdAt(LocalDateTime.now())
                .total(orderItems.stream().map(OrderItem::getPrice).reduce(ZERO, BigDecimal::add))
                .build();
        final Order savedOrder = orderRepository.save(order);
        orderItems.forEach(orderItem -> {
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        });
        savedOrder.setItems(orderItems);
        return mapToResponse(savedOrder);
    }

    private OrderResponseDTO mapToResponse(Order order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .total(order.getTotal())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .user(UserResponseDTO.builder()
                        .email(order.getUser().getEmail())
                        .id(order.getUser().getId())
                        .name(order.getUser().getName())
                        .build())
                .items(order.getItems()
                        .stream()
                        .map(orderItem -> OrderItemResponseDTO.builder()
                                .id(orderItem.getId())
                                .price(orderItem.getPrice())
                                .quantity(orderItem.getQuantity())
                                .product(ProductResponseDTO.builder()
                                        .id(orderItem.getProduct().getId())
                                        .name(orderItem.getProduct().getName())
                                        .stock(orderItem.getProduct().getStock())
                                        .price(orderItem.getProduct().getPrice())
                                        .build())
                                .build())
                        .toList())
                .build();
    }

    public List<OrderResponseDTO> orderList() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public OrderResponseDTO findOrderById(Integer id) {
        return mapToResponse(orderRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public OrderResponseDTO updateOrder(Integer id, OrderRequestDTO orderRequestDTO) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        updateIfNotNull(obj -> {
            User user = userRepository.findById((Integer) obj).orElseThrow(EntityNotFoundException::new);
            order.setUser(user);
        }, orderRequestDTO.getUserId());

        updateIfNotNull(obj -> order.setStatus(Status.valueOf(((String) obj).toUpperCase())), orderRequestDTO.getStatus());

        updateIfNotNull(obj -> {
            List<OrderItemRequestDTO> reqItems = (List<OrderItemRequestDTO>) obj;
            order.getItems().clear();
            reqItems.forEach(ri -> {
                Product product = productRepository.findById(ri.getProductId()).orElseThrow(EntityNotFoundException::new);
                OrderItem item = OrderItem.builder()
                        .product(product)
                        .price(product.getPrice())
                        .quantity(ri.getQuantity())
                        .order(order)
                        .build();
                order.getItems().add(item);
            });
        }, orderRequestDTO.getOrderItems());
        if (!orderRepository.existsById(id)) throw new EntityNotFoundException();
        return mapToResponse(orderRepository.save(order));
    }

    private void updateIfNotNull(Consumer<Object> consumer, Object object) {
        if (Objects.nonNull(object)) {
            consumer.accept(object);
        }
    }

    @Transactional
    public void deleteOrder(Integer id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        orderRepository.deleteById(id);
    }
}
