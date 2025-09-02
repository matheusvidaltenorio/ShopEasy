package util.test;

import util.domain.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteConexao {

    public static void main(String[] args) {
        try (Connection con = ConnectionFactory.getConnection()) {
            System.out.println("Conexão realizada com sucesso!");

            // Teste simples: listar os produtos
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produtos");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Nome: " + rs.getString("nome") + " | Preço: " + rs.getDouble("preco"));
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

