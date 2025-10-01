package com.shopeasy.service;

import com.shopeasy.model.Usuario;
import com.shopeasy.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private String gerarHash(char[] senha) {
        if (senha == null || senha.length == 0) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia");
        }

        String senhaStr = new String(senha); // Necessário pro BCrypt
        String hash = BCrypt.hashpw(senhaStr, BCrypt.gensalt(12));
        senhaStr = null; // referência zerada (GC vai cuidar depois)

        return hash;
    }

    public Usuario cadastrarUsuario(Usuario usuario) {
        usuario.setSenhaHash(gerarHash(usuario.getSenha()));
        usuario.limparSenha();
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    /*Atualiza um usuário. Só re-hash se a senha tiver sido enviada.*/
    public Usuario atualizarUsuario(Usuario usuario) {
        if (usuario.getSenha() != null && usuario.getSenha().length > 0) {
            usuario.setSenhaHash(gerarHash(usuario.getSenha()));
            usuario.limparSenha();
        }
        return usuarioRepository.save(usuario);
    }

    public void removerUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário com ID " + id + " não encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    /**
     * Busca um usuário pelo email.
     * Retorna Optional para evitar NullPointerException.
     */
    public Optional<Usuario> buscarPorEmail(String email) {
        return Optional.ofNullable(usuarioRepository.findByEmail(email));
    }
}
