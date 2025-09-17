package repository.domain;

import util.domain.ConnectionFactory;
import model.domain.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepository {

    // Insere um pedido no banco
    public void salvar(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedidos (usuario_id, status, total) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, pedido.getUsuarioId());
            stmt.setString(2, pedido.getStatus());
            stmt.setDouble(3, pedido.getTotal());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                pedido.setId(rs.getInt(1));
            }
        }
    }

    // Busca todos os pedidos
    public List<Pedido> listar() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setUsuarioId(rs.getInt("usuario_id"));
                pedido.setStatus(rs.getString("status"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setCriadoEm(rs.getTimestamp("criado_em").toLocalDateTime());

                pedidos.add(pedido);
            }
        }
        return pedidos;
    }

    // Busca um pedido pelo ID
    public Pedido buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        Pedido pedido = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pedido = new Pedido(
                            rs.getInt("id"),
                            rs.getInt("usuario_id"),
                            rs.getString("status"),
                            rs.getDouble("total"),
                            rs.getTimestamp("criado_em").toLocalDateTime()
                    );
                }
            }
        }
        return pedido;
    }

    // Atualiza o status ou total do pedido
    public void atualizar(Pedido pedido) throws SQLException {
        String sql = "UPDATE pedidos SET usuario_id = ?, status = ?, total = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedido.getUsuarioId());
            stmt.setString(2, pedido.getStatus());
            stmt.setDouble(3, pedido.getTotal());
            stmt.setInt(4, pedido.getId());
            stmt.executeUpdate();
        }
    }

    // Remove um pedido
    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM pedidos WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
