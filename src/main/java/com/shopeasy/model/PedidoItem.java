package com.shopeasy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "pedido_itens")
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relacionamento com Pedido
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    // Relacionamento com Produto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer quantidade;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal preco;

    // Construtor vazio
    public PedidoItem() {}

    // Construtor sem id
    public PedidoItem(Pedido pedido, Produto produto, Integer quantidade, BigDecimal preco) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Construtor completo
    public PedidoItem(Integer id, Pedido pedido, Produto produto, Integer quantidade, BigDecimal preco) {
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    @Override
    public String toString() {
        return "PedidoItem{" +
                "id=" + id +
                ", pedidoId=" + (pedido != null ? pedido.getId() : null) +
                ", produtoId=" + (produto != null ? produto.getId() : null) +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                '}';
    }
}
