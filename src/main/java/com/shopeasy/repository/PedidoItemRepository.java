package com.shopeasy.repository;

import com.shopeasy.model.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, Integer> {

    // Lista todos os itens de um pedido específico
    List<PedidoItem> findByPedidoId(Integer pedidoId);
}
