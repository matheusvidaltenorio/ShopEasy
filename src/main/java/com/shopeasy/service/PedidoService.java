/*package com.shopeasy.service;

import main.java.com.shopeasy.model.Pedido;
import main.java.com.shopeasy.model.PedidoItem;
import main.java.com.shopeasy.repository.PedidoItemRepository;
import main.java.com.shopeasy.repository.PedidoRepository;

import java.sql.SQLException;
import java.util.List;

public class PedidoService {
    private PedidoRepository pedidoRepository = new PedidoRepository();
    private PedidoItemRepository pedidoItemRepository = new PedidoItemRepository();

    // Cria pedido e seus itens
    public void criarPedido(Pedido pedido) throws SQLException {
        // 1. Salva o pedido
        pedidoRepository.salvar(pedido);

        // 2. Salva os itens relacionados
        if (pedido.getItens() != null) {
            for (PedidoItem item : pedido.getItens()) {
                item.setPedidoId(pedido.getId()); // FK
                pedidoItemRepository.salvar(item);
            }
        }
    }

    public List<Pedido> listarPedidos() throws SQLException {
        return pedidoRepository.listar();
    }

    // Busca pedido por ID
    public Pedido buscarPedidoPorId(int id) throws SQLException {
        return pedidoRepository.buscarPorId(id);
    }

    // Atualiza pedido
    public void atualizarPedido(Pedido pedido) throws SQLException {
        pedidoRepository.atualizar(pedido);
    }

    // Remove pedido
    public void removerPedido(int id) throws SQLException {
        pedidoRepository.remover(id);
    }
}*/
