package service.domain;

import model.domain.Produto;
import repository.domain.ProdutoRepository;
import java.util.List;

public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService() {
        this.produtoRepository = new ProdutoRepository();
    }

    public void cadastrarProduto(Produto produto) {
        // Exemplo de regra de negócio:
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        produtoRepository.adicionarProduto(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.listarProdutos();
    }

    public void atualizarProduto(Produto produto) {
        produtoRepository.atualizarProduto(produto);
    }

    public void removerProduto(int id) {
        produtoRepository.removerProduto(id);
    }
}

