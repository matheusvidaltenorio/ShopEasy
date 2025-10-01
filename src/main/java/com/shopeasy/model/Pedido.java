/*package com.shopeasy.model;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
    private int id;
    private int usuarioId;   // FK para Usuario
    private String status;   // pendente, pago, enviado
    private double total;
    private LocalDateTime criadoEm;

    // Um pedido pode ter vários itens
    private List<PedidoItem> itens;

    // Construtor vazio (necessário para frameworks e instanciamento manual)
    public Pedido() {}

    // Construtor para novo pedido (sem id e criadoEm, banco gera)
    public Pedido(int usuarioId, String status, double total) {
        this.usuarioId = usuarioId;
        this.status = status;
        this.total = total;
        this.criadoEm = LocalDateTime.now();
    }
    //para pesquisa
    public Pedido(int id, int usuarioId, String status, double total, LocalDateTime criadoEm) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.status = status;
        this.total = total;
        this.criadoEm = criadoEm;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    public List<PedidoItem> getItens() { return itens; }
    public void setItens(List<PedidoItem> itens) { this.itens = itens; }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", status='" + status + '\'' +
                ", total=" + total +
                ", criadoEm=" + criadoEm +
                '}';
    }
}*/

