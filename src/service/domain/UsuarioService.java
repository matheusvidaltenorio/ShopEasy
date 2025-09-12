package service.domain;

import model.domain.Usuario;
import repository.domain.UsuarioRepository;
import java.util.List;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
    }

    public void cadastrarUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        usuarioRepository.adicionarUsuario(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioRepository.atualizarUsuario(usuario);
    }

    public void removerUsuario(int id) {
        usuarioRepository.removerUsuario(id);
    }
}

