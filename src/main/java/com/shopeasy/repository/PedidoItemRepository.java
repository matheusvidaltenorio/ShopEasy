/*package com.shopeasy.repository;

import util.domain.ConnectionFactory;
import main.java.com.shopeasy.model.PedidoItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoItemRepository {

    // Insere item do pedido
    public void salvar(PedidoItem item) throws SQLException {
        String sql = "INSERT INTO pedido_itens (pedido_id, produto_id, quantidade, preco) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, item.getPedidoId());
            stmt.setInt(2, item.getProdutoId());
            stmt.setInt(3, item.getQuantidade());
            stmt.setDouble(4, item.getPreco());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                item.setId(rs.getInt(1));
            }
        }
    }

    // Lista itens de um pedido
    public List<PedidoItem> listarPorPedido(int pedidoId) throws SQLException {
        List<PedidoItem> itens = new ArrayList<>();
        String sql = "SELECT * FROM pedido_itens WHERE pedido_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedidoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PedidoItem item = new PedidoItem();
                item.setId(rs.getInt("id"));
                item.setPedidoId(rs.getInt("pedido_id"));
                item.setProdutoId(rs.getInt("produto_id"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPreco(rs.getDouble("preco"));

                itens.add(item);
            }
        }
        return itens;
    }

    // Busca item pelo ID
    public PedidoItem buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pedido_itens WHERE id = ?";
        PedidoItem item = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                item = new PedidoItem(
                        rs.getInt("id"),
                        rs.getInt("pedido_id"),
                        rs.getInt("produto_id"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco")
                );
            }
        }
        return item;
    }

    // Atualiza item
    public void atualizar(PedidoItem item) throws SQLException {
        String sql = "UPDATE pedido_itens SET pedido_id = ?, produto_id = ?, quantidade = ?, preco = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getPedidoId());
            stmt.setInt(2, item.getProdutoId());
            stmt.setInt(3, item.getQuantidade());
            stmt.setDouble(4, item.getPreco());
            stmt.setInt(5, item.getId());
            stmt.executeUpdate();
        }
    }

    // Remove item
    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM pedido_itens WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}*/

