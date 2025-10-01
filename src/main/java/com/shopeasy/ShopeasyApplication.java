package com.shopeasy;

import com.shopeasy.model.Usuario;
import com.shopeasy.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopeasyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopeasyApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(UsuarioService usuarioService) {
        return args -> {
            Usuario u1 = new Usuario("lari", "lari@email.com", "321654".toCharArray());
            usuarioService.cadastrarUsuario(u1);

            System.out.println("Lista de usuários:");
            usuarioService.listarUsuarios().forEach(System.out::println);
        };
    }
}

