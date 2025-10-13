package com.shopeasy.presentation.controller;

import com.shopeasy.presentation.dto.ProductRequestDTO;
import com.shopeasy.domain.entity.Product;
import com.shopeasy.application.service.ProductsService;
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
@RequestMapping("/v1/products")
public class ProductController {
    private final ProductsService productsService;

    @Autowired
    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequestDTO product) {
        return ResponseEntity.status(CREATED).body(productsService.createProduct(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProducts() {
        final List<Product> productList = productsService.listProducts();
        if (productList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productsService.findProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody ProductRequestDTO product) {
        return ResponseEntity.ok(productsService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
        productsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
