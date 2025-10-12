package com.shopeasy.controller;

import com.shopeasy.model.Produto;
import com.shopeasy.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    //  Criar produto
    @PostMapping
    public ResponseEntity<?> criarProduto(@Valid @RequestBody Produto produto) {
        try {
            Produto novoProduto = produtoService.cadastrarProduto(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao cadastrar produto");
        }
    }

    //  Listar todos os produtos
    @GetMapping
    public ResponseEntity<?> listarProdutos() {
        try {
            List<Produto> produtos = produtoService.listarProdutos();

            if (produtos.isEmpty()) {
                // Lista vazia, mas requisição bem-sucedida
                return ResponseEntity.noContent().build(); // 204
            }

            return ResponseEntity.ok(produtos); // 200
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Erro ao listar produtos: " + e.getMessage()); // 500
        }
    }


    //  Atualizar produto existente
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(
            @PathVariable Integer id,
            @Valid @RequestBody Produto produto) {
        try {
            produto.setId(id);
            Produto atualizado = produtoService.atualizarProduto(produto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar produto");
        }
    }

    // Remover produto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerProduto(@PathVariable Integer id) {
        try {
            produtoService.removerProduto(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao remover produto");
        }
    }
}
