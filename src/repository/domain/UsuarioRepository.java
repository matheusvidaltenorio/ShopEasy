package repository.domain;

import model.domain.Usuario;
import util.domain.ConnectionFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    // Método para gerar hash com bcrypt
    private String gerarHash(char[] senha){
        // bcrypt precisa de String temporária
        String senhaStr = new String(senha);
        String hash = BCrypt.hashpw(senhaStr, BCrypt.gensalt(12));
        senhaStr = null; // ajuda GC
        return hash;
    }

    public void adicionarUsuario(Usuario u){
        String sql = "INSERT INTO usuarios (nome, email, senha_hash) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, gerarHash(u.getSenha()));
            stmt.executeUpdate();

            // limpar senha da memória
            u.limparSenha();

            System.out.println("Registro adicionado com sucesso!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Usuario> listarUsuarios(){
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                Usuario u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha_hash")
                );
                lista.add(u);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizarUsuario(Usuario u) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha_hash = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, gerarHash(u.getSenha()));
            stmt.setInt(4, u.getId());
            stmt.executeUpdate();

            System.out.println("Registro atualizado com sucesso!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removerUsuario(int id){
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try { Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Registro removido com sucesso!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }




}