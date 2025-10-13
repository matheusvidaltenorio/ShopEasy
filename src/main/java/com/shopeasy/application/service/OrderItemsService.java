package com.shopeasy.application.service;

import com.shopeasy.presentation.dto.OrderItemRequestDTO;
import com.shopeasy.presentation.dto.OrderItemResponseDTO;
import com.shopeasy.presentation.dto.ProductResponseDTO;
import com.shopeasy.domain.entity.Order;
import com.shopeasy.domain.entity.OrderItem;
import com.shopeasy.domain.entity.Product;
import com.shopeasy.infrastructure.database.OrderItemRepository;
import com.shopeasy.infrastructure.database.OrderRepository;
import com.shopeasy.infrastructure.database.ProductRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.function.Consumer;

@Service
public class OrderItemsService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderItemsService(OrderItemRepository orderItemRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public OrderItemResponseDTO addItem(Integer pedidoId, OrderItemRequestDTO orderItemRequestDTO) {
        final Product product = productRepository.findById(orderItemRequestDTO.getProductId()).orElseThrow(EntityNotFoundException::new);
        final Order order = orderRepository.findById(pedidoId).orElseThrow(EntityNotFoundException::new);
        final OrderItem orderItem = OrderItem.builder()
                .product(product)
                .order(order)
                .quantity(orderItemRequestDTO.getQuantity())
                .price(product.getPrice())
                .build();
        return mapToResponse(orderItemRepository.save(orderItem));
    }

    public OrderItemResponseDTO findItemById(Integer id) {
        return mapToResponse(orderItemRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public OrderItemResponseDTO updateItem(Integer orderItemId, OrderItemRequestDTO orderItemRequestDTO) {
        final OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(EntityNotFoundException::new);
        updateIfNotNull(object -> {
            final Integer productId = (Integer) object;
            if (!Objects.equals(productId, orderItem.getProduct().getId())) {
                final Product product = productRepository.findById(productId).orElseThrow(EntityExistsException::new);
                orderItem.setProduct(product);
                orderItem.setPrice(product.getPrice());
            }
        }, orderItemRequestDTO.getProductId());
        updateIfNotNull(object -> orderItem.setQuantity((Integer) object), orderItemRequestDTO.getQuantity());
        if (!orderItemRepository.existsById(orderItemId)) {
            throw new EntityNotFoundException();
        }
        return mapToResponse(orderItemRepository.save(orderItem));
    }

    private void updateIfNotNull(Consumer<Object> consumer, Object object) {
        if (Objects.nonNull(object)) {
            consumer.accept(object);
        }
    }

    private OrderItemResponseDTO mapToResponse(OrderItem orderItem) {
        return OrderItemResponseDTO.builder()
                .id(orderItem.getId())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .product(ProductResponseDTO.builder()
                        .id(orderItem.getProduct().getId())
                        .stock(orderItem.getProduct().getStock())
                        .price(orderItem.getProduct().getPrice())
                        .build())
                .build();
    }

    @Transactional
    public void deleteItem(Integer id) {
        if (!orderItemRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        orderItemRepository.deleteById(id);
    }
}
