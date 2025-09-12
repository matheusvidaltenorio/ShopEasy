package service.domain;

import model.domain.Pedido;
import model.domain.PedidoItem;
import repository.domain.PedidoItemRepository;
import repository.domain.PedidoRepository;

import java.sql.SQLException;
import java.util.List;

public class PedidoService {
    private PedidoRepository pedidoRepository = new PedidoRepository();
    private PedidoItemRepository pedidoItemRepository = new PedidoItemRepository();

    // Cria pedido e seus itens
    public void criarPedido(Pedido pedido) throws SQLException {
        // 1. Salva o pedido
        pedidoRepository.salvar(pedido);

        // 2. Salva os itens relacionados
        if (pedido.getItens() != null) {
            for (PedidoItem item : pedido.getItens()) {
                item.setPedidoId(pedido.getId()); // FK
                pedidoItemRepository.salvar(item);
            }
        }
    }

    public List<Pedido> listarPedidos() throws SQLException {
        return pedidoRepository.listar();
    }
}
