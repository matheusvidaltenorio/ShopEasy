/*package com.shopeasy.service;

import main.java.com.shopeasy.model.PedidoItem;
import main.java.com.shopeasy.repository.PedidoItemRepository;

import java.sql.SQLException;
import java.util.List;

public class PedidoItemService {
    private PedidoItemRepository pedidoItemRepository = new PedidoItemRepository();

    // Criar item
    public void adicionarItem(PedidoItem item) throws SQLException {
        pedidoItemRepository.salvar(item);
    }

    // Listar itens por pedido
    public List<PedidoItem> listarItensPorPedido(int pedidoId) throws SQLException {
        return pedidoItemRepository.listarPorPedido(pedidoId);
    }

    // Buscar item por ID
    public PedidoItem buscarItemPorId(int id) throws SQLException {
        return pedidoItemRepository.buscarPorId(id);
    }

    // Atualizar item
    public void atualizarItem(PedidoItem item) throws SQLException {
        pedidoItemRepository.atualizar(item);
    }

    // Remover item
    public void removerItem(int id) throws SQLException {
        pedidoItemRepository.remover(id);
    }
}*/

