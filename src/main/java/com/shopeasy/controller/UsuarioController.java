package com.shopeasy.controller;

import com.shopeasy.dto.UsuarioDTO;
import com.shopeasy.dto.UsuarioResponse;
import com.shopeasy.model.Usuario;
import com.shopeasy.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Criar usuário
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody UsuarioDTO dto) {
        try {
            Usuario u = new Usuario();
            u.setNome(dto.getNome());
            u.setEmail(dto.getEmail());
            u.setSenha(dto.getSenha().toCharArray());

            Usuario usuarioSalvo = usuarioService.cadastrarUsuario(u);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Listar todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // Buscar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarUsuarioPorId(@PathVariable("id") Integer id) {
        return usuarioService.buscarPorId(id)
                .map(usuario -> new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(
            @PathVariable("id") Integer id,
            @Valid @RequestBody UsuarioDTO dto) {

        try {
            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNome(dto.getNome());
            usuario.setEmail(dto.getEmail());
            usuario.setSenha(dto.getSenha().toCharArray());

            Usuario atualizado = usuarioService.atualizarUsuario(usuario);

            UsuarioResponse response = new UsuarioResponse(
                    atualizado.getId(),
                    atualizado.getNome(),
                    atualizado.getEmail()
            );

            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    // Remover usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerUsuario(@PathVariable("id") Integer id) {
        try {
            usuarioService.removerUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar usuário por email (opcional)
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@PathVariable("email") String email) {
        return usuarioService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
