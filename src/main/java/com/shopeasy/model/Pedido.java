package com.shopeasy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    public enum Status {
        PENDENTE, PAGO, ENVIADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relacionamento ManyToOne com Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Positive
    @NotNull
    @Column(nullable = false)
    private BigDecimal total;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    // Um pedido pode ter vários itens
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> itens = new ArrayList<>();

    // Construtor vazio
    public Pedido() {
        this.criadoEm = LocalDateTime.now();
    }

    // Construtor para criar pedido
    public Pedido(Usuario usuario, Status status, BigDecimal total) {
        this.usuario = usuario;
        this.status = status;
        this.total = total;
        this.criadoEm = LocalDateTime.now();
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public List<PedidoItem> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItem> itens) {
        this.itens = itens;
    }

    public void adicionarItem(PedidoItem item) {
        itens.add(item);
        item.setPedido(this);
    }

    public void removerItem(PedidoItem item) {
        itens.remove(item);
        item.setPedido(null);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", usuario=" + (usuario != null ? usuario.getNome() : null) +
                ", status=" + status +
                ", total=" + total +
                ", criadoEm=" + criadoEm +
                ", itens=" + itens.size() +
                '}';
    }
}
