import model.domain.Produto;
import repository.domain.ProdutoRepository;

public class Main {
    public static void main(String[] args) {

        ProdutoRepository repo = new ProdutoRepository();

        // Adicionar
        Produto p1 = new Produto("Mouse Gamer", 199.90, 10);
        repo.adicionarProduto(p1);

        // Listar e imprimir pulando linha
        for (Produto produto : repo.listarProdutos()){
            System.out.println(produto);
        }

    }
}
