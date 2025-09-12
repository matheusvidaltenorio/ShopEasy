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
}
