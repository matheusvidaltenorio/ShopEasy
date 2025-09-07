package repository.domain;

import com.sun.source.tree.TryTree;
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

        public List<Produto> listarProdutos() {
            List<Produto> produtos = new ArrayList<>();
            String sql = "SELECT * FROM produtos";
            try(Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
                while(rs.next()){
                    Produto produto = new Produto(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDouble("preco"),
                            rs.getInt("estoque")
                    );
                    produtos.add(produto);
                }

            }catch (SQLException e) {
                e.printStackTrace();
            }
            return produtos;
        }
    //public void atualizarProduto(Produto produto){}
    //public void excluirProduto(Produto produto){}

    

}
