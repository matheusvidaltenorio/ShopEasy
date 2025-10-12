package com.shopeasy.repository;

import com.shopeasy.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Buscar usuário por email
    Usuario findByEmail(String email);

    boolean existsByEmail(String email);


}
