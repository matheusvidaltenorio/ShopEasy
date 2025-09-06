package model.domain;

import java.time.LocalDateTime;

public class Pedido {
    private int id;
    private int usuarioId; // chave estrangeira para Usuario
    private LocalDateTime dataPedido;
    private double valorTotal;

    public Pedido() {}

    public Pedido(int usuarioId, LocalDateTime dataPedido, double valorTotal) {
        this.usuarioId = usuarioId;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
    }

    public Pedido(int id, int usuarioId, LocalDateTime dataPedido, double valorTotal) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getUsuarioId() { return usuarioId; }

    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public LocalDateTime getDataPedido() { return dataPedido; }

    public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }

    public double getValorTotal() { return valorTotal; }

    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", dataPedido=" + dataPedido +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
