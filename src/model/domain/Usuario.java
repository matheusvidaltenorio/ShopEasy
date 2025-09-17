package model.domain;

import java.util.Arrays;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senhaHash; // vamos armazenar senha como hash
    private transient char[] senha; // senha em memória temporária

    public Usuario() {}

    public Usuario(String nome, String email, String senhaHash) {
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
    }

    public Usuario(int id, String nome, String email, String senhaHash) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }

    public char[] getSenha() { return senha; }
    public void setSenha(char[] senha) { this.senha = senha; }

    public void limparSenha() {
        if (senha != null) {
            Arrays.fill(senha, '\0'); // sobrescreve
            senha = null;
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
