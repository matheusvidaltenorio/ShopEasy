package repository.domain;

import model.domain.Usuario;
import util.domain.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsuarioRepository {
    public void adicionarUsuario(String nome, String email, String senha) {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, nome);
            stmt.setString(2,email);
            stmt.setString(3,senha);

        }
    }
}
