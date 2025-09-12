package app;

import model.domain.Produto;
import model.domain.Usuario;
import service.domain.ProdutoService;
import service.domain.UsuarioService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ProdutoService produtoService = new ProdutoService();
        UsuarioService usuarioService = new UsuarioService();

        int opcao;

        do {
            System.out.println("\n===== ShopEasy =====");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Atualizar Produto");
            System.out.println("4 - Remover Produto");
            System.out.println("5 - Cadastrar Usuário");
            System.out.println("6 - Listar Usuários");
            System.out.println("7 - Atualizar Usuário");
            System.out.println("8 - Remover Usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do produto: ");
                    String nomeProd = sc.nextLine();
                    System.out.print("Preço: ");
                    double preco = sc.nextDouble();
                    System.out.print("Estoque: ");
                    int estoque = sc.nextInt();

                    Produto novoProd = new Produto(nomeProd, preco, estoque);
                    produtoService.cadastrarProduto(novoProd);
                    System.out.println(" Produto cadastrado!");
                    break;

                case 2:
                    System.out.println(" Lista de produtos:");
                    produtoService.listarProdutos().forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("ID do produto para atualizar: ");
                    int idUpdate = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    System.out.print("Novo preço: ");
                    double novoPreco = sc.nextDouble();
                    System.out.print("Novo estoque: ");
                    int novoEstoque = sc.nextInt();

                    Produto prodAtualizado = new Produto(idUpdate, novoNome, novoPreco, novoEstoque);
                    produtoService.atualizarProduto(prodAtualizado);
                    System.out.println("️ Produto atualizado!");
                    break;

                case 4:
                    System.out.print("ID do produto para remover: ");
                    int idRemoverProd = sc.nextInt();
                    produtoService.removerProduto(idRemoverProd);
                    System.out.println("Produto removido!");
                    break;

                case 5:
                    System.out.print("Nome do usuário: ");
                    String nomeUser = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    Usuario novoUser = new Usuario(nomeUser, email, senha);
                    usuarioService.cadastrarUsuario(novoUser);
                    System.out.println(" Usuário cadastrado!");
                    break;

                case 6:
                    System.out.println(" Lista de usuários:");
                    usuarioService.listarUsuarios().forEach(System.out::println);
                    break;

                case 7:
                    System.out.print("ID do usuário para atualizar: ");
                    int idUserUpdate = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo nome: ");
                    String nomeNovo = sc.nextLine();
                    System.out.print("Novo email: ");
                    String emailNovo = sc.nextLine();
                    System.out.print("Nova senha: ");
                    String senhaNova = sc.nextLine();

                    Usuario userAtualizado = new Usuario(idUserUpdate, nomeNovo, emailNovo, senhaNova);
                    usuarioService.atualizarUsuario(userAtualizado);
                    System.out.println(" Usuário atualizado!");
                    break;

                case 8:
                    System.out.print("ID do usuário para remover: ");
                    int idUserRemover = sc.nextInt();
                    usuarioService.removerUsuario(idUserRemover);
                    System.out.println(" Usuário removido!");
                    break;

                case 0:
                    System.out.println(" Saindo do ShopEasy...");
                    break;

                default:
                    System.out.println(" Opção inválida!");

            }
        }while(opcao != 0);
    }
}
