package repository.domain;

import model.domain.Produto;
import util.domain.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    public void adicionarProduto(Produto p) {
        String sql = "INSERT INTO produtos (nome, preco, estoque) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getEstoque());
            stmt.executeUpdate();
            System.out.println("Produto adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //public List<Produto> listarProdutos(){}
    //public void atualizarProduto(Produto produto){}
    //public void excluirProduto(Produto produto){}

    

}
