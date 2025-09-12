package service.domain;

import model.domain.PedidoItem;
import repository.domain.PedidoItemRepository;

import java.sql.SQLException;
import java.util.List;

public class PedidoItemService {
    private PedidoItemRepository pedidoItemRepository = new PedidoItemRepository();

    public void adicionarItem(PedidoItem item) throws SQLException {
        pedidoItemRepository.salvar(item);
    }

    public List<PedidoItem> listarItensPorPedido(int pedidoId) throws SQLException {
        return pedidoItemRepository.listarPorPedido(pedidoId);
    }
}

