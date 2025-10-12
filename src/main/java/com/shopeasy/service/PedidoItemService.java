package com.shopeasy.service;

import com.shopeasy.model.Pedido;
import com.shopeasy.model.PedidoItem;
import com.shopeasy.repository.PedidoItemRepository;
import com.shopeasy.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoItemService {

    private final PedidoItemRepository pedidoItemRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoItemService(PedidoItemRepository pedidoItemRepository, PedidoRepository pedidoRepository) {
        this.pedidoItemRepository = pedidoItemRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public PedidoItem adicionarItem(Integer pedidoId, PedidoItem item) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(pedidoId);
        if (pedidoOpt.isEmpty()) {
            throw new IllegalArgumentException("Pedido com ID " + pedidoId + " não encontrado");
        }

        if (item.getProduto() == null) {
            throw new IllegalArgumentException("Item deve estar vinculado a um produto");
        }

        if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (item.getPreco() == null || item.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }

        item.setPedido(pedidoOpt.get());
        return pedidoItemRepository.save(item);
    }

    public List<PedidoItem> listarItensPorPedido(Integer pedidoId) {
        return pedidoItemRepository.findByPedidoId(pedidoId);
    }

    public Optional<PedidoItem> buscarItemPorId(Integer id) {
        return pedidoItemRepository.findById(id);
    }

    @Transactional
    public PedidoItem atualizarItem(PedidoItem item) {
        if (item.getId() == null) {
            throw new IllegalArgumentException("Item sem ID não pode ser atualizado");
        }

        if (item.getPreco() == null || item.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço inválido");
        }

        return pedidoItemRepository.save(item);
    }

    @Transactional
    public void removerItem(Integer id) {
        if (!pedidoItemRepository.existsById(id)) {
            throw new IllegalArgumentException("Item com ID " + id + " não encontrado");
        }
        pedidoItemRepository.deleteById(id);
    }
}
