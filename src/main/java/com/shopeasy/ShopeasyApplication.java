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
}

