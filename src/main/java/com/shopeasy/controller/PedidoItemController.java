package com.shopeasy.controller;

import com.shopeasy.model.PedidoItem;
import com.shopeasy.service.PedidoItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido-itens")
public class PedidoItemController {

    private final PedidoItemService pedidoItemService;

    public PedidoItemController(PedidoItemService pedidoItemService) {
        this.pedidoItemService = pedidoItemService;
    }

    // Adiciona um item a um pedido existente
    @PostMapping("/pedido/{pedidoId}")
    public ResponseEntity<PedidoItem> adicionarItem(@PathVariable Integer pedidoId, @Valid @RequestBody PedidoItem item) {
        try {
            PedidoItem itemSalvo = pedidoItemService.adicionarItem(pedidoId, item);
            return ResponseEntity.status(HttpStatus.CREATED).body(itemSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Lista todos os itens de um pedido
    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<PedidoItem>> listarItensPorPedido(@PathVariable Integer pedidoId) {
        List<PedidoItem> itens = pedidoItemService.listarItensPorPedido(pedidoId);
        return ResponseEntity.ok(itens);
    }

    // Busca um item pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoItem> buscarItem(@PathVariable Integer id) {
        return pedidoItemService.buscarItemPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualiza um item
    @PutMapping("/{id}")
    public ResponseEntity<PedidoItem> atualizarItem(@PathVariable Integer id, @Valid @RequestBody PedidoItem item) {
        try {
            item.setId(id);
            PedidoItem atualizado = pedidoItemService.atualizarItem(item);
            return ResponseEntity.ok(atualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Remove um item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerItem(@PathVariable Integer id) {
        try {
            pedidoItemService.removerItem(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}



