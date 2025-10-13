package com.shopeasy.application.service;

import com.shopeasy.presentation.dto.UserRequestDTO;
import com.shopeasy.presentation.dto.UserResponseDTO;
import com.shopeasy.domain.entity.User;
import com.shopeasy.infrastructure.database.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersService {
    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public User createUser(UserRequestDTO userRequestDTO) {
        final User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword().toCharArray());
        //  Verifica se já existe um usuário com o mesmo e-mail
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado!");
        }

        //  Gera o hash e limpa a senha
        user.setPasswordHash(gerarHash(user.getPassword()));
        cleanPassword(user);

        //  Salva no banco
        return userRepository.save(user);
    }


    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public UserResponseDTO updateUser(Integer id, UserRequestDTO dto) {
        final User user = User.builder()
                .id(id)
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword().toCharArray())
                .build();
        final User existente = userRepository.findById(user.getId()).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        existente.setName(user.getName());
        existente.setEmail(user.getEmail());
        if (user.getPassword() != null && user.getPassword().length > 0) {
            existente.setPasswordHash(gerarHash(user.getPassword()));
            cleanPassword(user);
        }
        userRepository.save(existente);
        return UserResponseDTO.builder()
                .id(existente.getId())
                .name(existente.getName())
                .email(existente.getEmail())
                .build();
    }


    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        userRepository.deleteById(id);
    }

    /**
     * Busca um usuário pelo email.
     * Retorna Optional para evitar NullPointerException.
     */
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public Optional<UserResponseDTO> findUserById(Integer id) {
        return userRepository.findById(id)
                .map(user -> UserResponseDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .build());
    }

    private void cleanPassword(User user) {
        if (Objects.nonNull(user.getPassword())) {
            Arrays.fill(user.getPassword(), '\0');
            user.setPassword(null);
        }
    }
}
