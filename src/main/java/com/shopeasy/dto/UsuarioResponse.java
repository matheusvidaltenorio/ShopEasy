package com.shopeasy.dto;

public class UsuarioResponse {
    private Integer id;
    private String nome;
    private String email;

    public UsuarioResponse(Integer id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Integer getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
}
