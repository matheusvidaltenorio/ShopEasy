package com.shopeasy.service;

import com.shopeasy.model.Pedido;
import com.shopeasy.model.PedidoItem;
import com.shopeasy.repository.PedidoRepository;
import com.shopeasy.repository.PedidoItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoItemRepository pedidoItemRepository;

    public PedidoService(PedidoRepository pedidoRepository, PedidoItemRepository pedidoItemRepository) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoItemRepository = pedidoItemRepository;
    }

    /**
     * Cria um novo pedido junto com seus itens.
     * O valor total é calculado automaticamente com base nos itens.
     */
    @Transactional
    public Pedido criarPedido(Pedido pedido) {
        if (pedido.getUsuario() == null) {
            throw new IllegalArgumentException("Pedido deve estar associado a um usuário");
        }

        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("Pedido deve conter ao menos um item");
        }

        // Calcula o total com base nos itens
        BigDecimal total = BigDecimal.ZERO;
        for (PedidoItem item : pedido.getItens()) {
            if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
                throw new IllegalArgumentException("Quantidade inválida no item");
            }
            if (item.getPreco() == null || item.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Preço inválido no item");
            }

            // Soma o subtotal
            total = total.add(item.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));
            item.setPedido(pedido); // garante integridade do relacionamento bidirecional
        }

        pedido.setTotal(total);

        // Salva o pedido e os itens
        Pedido novoPedido = pedidoRepository.save(pedido);
        for (PedidoItem item : pedido.getItens()) {
            pedidoItemRepository.save(item);
        }

        return novoPedido;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPedidoPorId(Integer id) {
        return pedidoRepository.findById(id);
    }

    @Transactional
    public Pedido atualizarPedido(Pedido pedido) {
        if (pedido.getId() == null) {
            throw new IllegalArgumentException("Pedido sem ID não pode ser atualizado");
        }
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void removerPedido(Integer id) {
        if (!pedidoRepository.existsById(id)) {
            throw new IllegalArgumentException("Pedido com ID " + id + " não encontrado");
        }
        pedidoRepository.deleteById(id);
    }
}
