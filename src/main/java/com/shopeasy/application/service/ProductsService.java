package com.shopeasy.application.service;

import com.shopeasy.presentation.dto.ProductRequestDTO;
import com.shopeasy.domain.entity.Product;
import com.shopeasy.infrastructure.database.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductsService {

    private final ProductRepository productRepository;

    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequestDTO productRequestDTO) {
        final Product product = Product.builder()
                .name(productRequestDTO.getName())
                .price(productRequestDTO.getPrice())
                .stock(productRequestDTO.getStock())
                .build();
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        return productRepository.save(product);
    }

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Integer id, ProductRequestDTO productRequestDTO) {
        final Product product = Product.builder()
                .id(id)
                .name(productRequestDTO.getName())
                .price(productRequestDTO.getPrice())
                .stock(productRequestDTO.getStock())
                .build();
        product.setId(id);
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        return productRepository.save(product);
    }

    public void deleteById(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        productRepository.deleteById(id);
    }

    public Product findProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
