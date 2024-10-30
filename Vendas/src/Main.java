import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ArrayList<Cliente> clientes = new ArrayList<>();
    private static final ArrayList<Produto> produtos = new ArrayList<>();
    private static final ArrayList<Pedido> pedidos = new ArrayList<>();
    private static final ArrayList<ItemPedido> itens = new ArrayList<>();

    public static void main(String[] args) {

        boolean executando = true;

        while (executando) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Produto");
            System.out.println("3. Cadastrar Pedido");
            System.out.println("4. Adicionar item ao pedido");
            System.out.println("5. Exibir itens do pedido");
            System.out.println("6. Cancelar item do pedido");
            System.out.println("7. Cancelar pedido completo");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception _) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    cadastrarProduto();
                    break;
                case 3:
                    cadastrarPedido();
                    break;
                case 4:
                    adicionarItemPedido();
                    break;
                case 5:
                    exibirItensPedido();
                    break;
                case 6:
                    cancelarItemPedido();
                    break;
                case 7:
                    cancelarPedido();
                    break;
                case 8:
                    System.out.println("Saindo...");
                    executando = false;
                    scanner.close();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void cadastrarCliente() {
        System.out.println("\n--- Cadastrar Cliente ---");

        String nome;
        do {
            System.out.print("Nome do Cliente: ");
            nome = scanner.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println("Erro: Nome não pode estar vazio. Tente novamente.");
            }
        } while (nome.isEmpty());

        String cpf;
        do {
            System.out.print("CPF do Cliente (apenas números): ");
            cpf = scanner.nextLine().trim();
            if (!cpf.matches("\\d{11}")) {
                System.out.println("Erro: CPF deve conter 11 dígitos numéricos. Tente novamente.");
            }
        } while (!cpf.matches("\\d{11}"));

        Cliente cliente = new Cliente(nome, cpf);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso! Código: " + cliente.getCodigo());
    }

    public static void cadastrarProduto() {
        System.out.println("\n--- Cadastrar Produto ---");

        String nomeProduto;
        do {
            System.out.print("Nome do Produto: ");
            nomeProduto = scanner.nextLine().trim();

            if (nomeProduto.isEmpty()) {
                System.out.println("Erro: Nome não pode estar vazio. Tente novamente.");
            }
        } while (nomeProduto.isEmpty());

        double preco;
        do {
            System.out.print("Preço do Produto: ");
            try {
                preco = scanner.nextDouble();
                if (preco < 0) {
                    System.out.println("Erro: O preço deve ser um valor positivo. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um valor numérico para o preço.");
                preco = -1;
                scanner.nextLine();
            }
        } while (preco < 0);

        int quantidade;
        do {
            System.out.print("Quantidade no estoque: ");
            try {
                quantidade = scanner.nextInt();
                if (quantidade < 0) {
                    System.out.println("Erro: A quantidade deve ser um número inteiro positivo. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número inteiro para a quantidade.");
                quantidade = -1;
                scanner.nextLine();
            }
        } while (quantidade < 0);

        scanner.nextLine();

        Produto produto = new Produto(nomeProduto, preco, quantidade);
        produtos.add(produto);
        System.out.println("Produto cadastrado com sucesso! Código: " + produto.getCodigo());
    }

    public static void cadastrarPedido() {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados. Cadastre um cliente primeiro.");
            return;
        }

        System.out.println("\n--- Cadastrar Pedido ---");

        System.out.println("Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println("\tCódigo: " + cliente.getCodigo() + " - Nome: " + cliente.getNome());
        }

        int codigoCliente;
        do {
            System.out.print("Escolha o cliente para o pedido (código): ");
            try {
                codigoCliente = scanner.nextInt();
                if (codigoCliente < 0) {
                    System.out.println("Erro: O código do cliente deve ser um número inteiro positivo. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número inteiro para o código do cliente.");
                codigoCliente = -1;
                scanner.nextLine();
            }
        } while (codigoCliente < 0);

        Cliente clienteEscolhido = null;
        for (Cliente cliente : clientes) {
            if (cliente.getCodigo() == codigoCliente) {
                clienteEscolhido = cliente;
                break;
            }
        }

        if (clienteEscolhido != null) {
            int numeroPedido;
            do {
                System.out.print("Número do pedido: ");
                try {
                    numeroPedido = scanner.nextInt();
                    if (numeroPedido < 0) {
                        System.out.println("Erro: O número do pedido deve ser um número inteiro positivo. Tente novamente.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro: Entrada inválida. Por favor, insira um número inteiro para o número do pedido.");
                    numeroPedido = -1;
                    scanner.nextLine();
                }
            } while (numeroPedido < 0);

            scanner.nextLine();

            Pedido pedido = new Pedido(clienteEscolhido, numeroPedido);
            pedidos.add(pedido);
            System.out.println("Pedido cadastrado com sucesso! Código: " + pedido.getCodigo());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void adicionarItemPedido() {
        if (pedidos.isEmpty()) {
            System.out.println("Não há pedidos cadastrados. Cadastre um pedido primeiro.");
            return;
        }

        if (produtos.isEmpty()) {
            System.out.println("Não há produtos cadastrados. Cadastre um produto primeiro.");
            return;
        }

        System.out.println("\n--- Adicionar Item ao Pedido ---");

        System.out.println("Pedidos:");
        for (Pedido pedido : pedidos) {
            System.out.println("\tCódigo: " + pedido.getCodigo() + " - Pedido número " + pedido.getNumPedido() + " de " + pedido.getCliente().getNome());
        }

        int codigoPedido;
        do {
            System.out.print("Escolha o pedido (código): ");
            try {
                codigoPedido = scanner.nextInt();
                if (codigoPedido < 0) {
                    System.out.println("Erro: O código do pedido deve ser um número inteiro positivo. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número inteiro para o código do pedido.");
                codigoPedido = -1;
                scanner.nextLine();
            }
        } while (codigoPedido < 0);

        Pedido pedidoEscolhido = null;
        for (Pedido pedido : pedidos) {
            if (pedido.getCodigo() == codigoPedido) {
                pedidoEscolhido = pedido;
                break;
            }
        }

        if (pedidoEscolhido != null) {
            System.out.println("Produtos:");
            for (Produto produto : produtos) {
                System.out.println("\tCódigo: " + produto.getCodigo() + " - Nome: " + produto.getNomeProduto());
            }

            int codigoProduto;
            do {
                System.out.print("Escolha o produto (código): ");
                try {
                    codigoProduto = scanner.nextInt();
                    if (codigoProduto < 0) {
                        System.out.println("Erro: O código do produto deve ser um número inteiro positivo. Tente novamente.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro: Entrada inválida. Por favor, insira um número inteiro para o código do produto.");
                    codigoProduto = -1;
                    scanner.nextLine();
                }
            } while (codigoProduto < 0);

            Produto produtoEscolhido = null;
            for (Produto produto : produtos) {
                if (produto.getCodigo() == codigoProduto) {
                    produtoEscolhido = produto;
                    break;
                }
            }

            if (produtoEscolhido != null) {
                int quantidade;
                do {
                    System.out.print("Informe a quantidade desejada: ");
                    try {
                        quantidade = scanner.nextInt();
                        if (quantidade < 0) {
                            System.out.println("Erro: A quantidade desejada deve ser um número inteiro positivo. Tente novamente.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: Entrada inválida. Por favor, insira um número inteiro para a quantidade desejada.");
                        quantidade = -1;
                        scanner.nextLine();
                    }
                } while (quantidade < 0);

                double preco = produtoEscolhido.getPrecoProduto() + 0.05;

                ItemPedido item = new ItemPedido();

                if (item.ItensPedido(pedidoEscolhido, produtoEscolhido, quantidade, preco)) {
                    itens.add(item);
                    System.out.println("Item adicionado com sucesso!");
                } else {
                    System.out.println("Quantidade insuficiente no estoque.");
                }
            } else {
                System.out.println("Produto não encontrado.");
            }
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }

    public static void exibirItensPedido() {
        if (itens.isEmpty()) {
            System.out.println("Nenhum item adicionado aos pedidos.");
        } else {
            System.out.println("\n--- Itens do Pedido ---");
            for (ItemPedido item : itens) {
                item.mostrarItemPedido();
                System.out.println("--------------");
            }
        }
    }

    public static void cancelarItemPedido() {
        if (itens.isEmpty()) {
            System.out.println("Nenhum item para cancelar.");
            return;
        }

        System.out.println("Pedidos:");
        for (Pedido pedido : pedidos) {
            System.out.println("\tCódigo: " + pedido.getCodigo() + " - Pedido número " + pedido.getNumPedido() + " de " + pedido.getCliente().getNome());
        }

        int codigoPedido;
        do {
            System.out.print("Escolha o pedido (código): ");
            try {
                codigoPedido = scanner.nextInt();
                if (codigoPedido < 0) {
                    System.out.println("Erro: O código do pedido deve ser um número inteiro positivo. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número inteiro para o código do pedido.");
                codigoPedido = -1;
                scanner.nextLine();
            }
        } while (codigoPedido < 0);

        Pedido pedidoEscolhido = null;
        for (Pedido pedido : pedidos) {
            if (pedido.getCodigo() == codigoPedido) {
                pedidoEscolhido = pedido;
                break;
            }
        }

        if (pedidoEscolhido != null) {
            System.out.println("Produtos do pedido:");
            for (ItemPedido item : itens) {
                if (item.getPedido().getCodigo() == pedidoEscolhido.getCodigo()) {
                    System.out.println("\tCódigo: " + item.getProduto().getCodigo() + " - Nome: " + item.getProduto().getNomeProduto());
                }
            }

            int codigoProduto;
            do {
                System.out.print("Informe o código do produto que deseja cancelar (código): ");
                try {
                    codigoProduto = scanner.nextInt();
                    if (codigoProduto < 0) {
                        System.out.println("Erro: O código do produto deve ser um número inteiro positivo. Tente novamente.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro: Entrada inválida. Por favor, insira um número inteiro para o código do produto.");
                    codigoProduto = -1;
                    scanner.nextLine();
                }
            } while (codigoProduto < 0);

            ItemPedido itemParaCancelar = null;

            for (ItemPedido item : itens) {
                if (item.getProduto().getCodigo() == codigoProduto && item.getPedido().getCodigo() == pedidoEscolhido.getCodigo()) {
                    itemParaCancelar = item;
                    break;
                }
            }

            if (itemParaCancelar != null) {
                itemParaCancelar.cancelarItem();
                itens.remove(itemParaCancelar);
                System.out.println("Item cancelado com sucesso!");
            } else {
                System.out.println("Produto não encontrado no pedido.");
            }
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }

    public static void cancelarPedido() {
        if (itens.isEmpty()) {
            System.out.println("Nenhum pedido para cancelar.");
            return;
        }

        System.out.println("Pedidos:");
        for (Pedido pedido : pedidos) {
            System.out.println("\tCódigo: " + pedido.getCodigo() + " - Pedido número " + pedido.getNumPedido() + " de " + pedido.getCliente().getNome());
        }

        int codigoPedido;
        do {
            System.out.print("Escolha o pedido (código): ");
            try {
                codigoPedido = scanner.nextInt();
                if (codigoPedido < 0) {
                    System.out.println("Erro: O código do pedido deve ser um número inteiro positivo. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número inteiro para o código do pedido.");
                codigoPedido = -1;
                scanner.nextLine();
            }
        } while (codigoPedido < 0);

        Pedido pedidoEscolhido = null;
        for (Pedido pedido : pedidos) {
            if (pedido.getCodigo() == codigoPedido) {
                pedidoEscolhido = pedido;
                break;
            }
        }

        if (pedidoEscolhido != null) {
            ArrayList<ItemPedido> itensParaRemover = new ArrayList<>();
            for (ItemPedido item : itens) {
                if (item.getPedido().getCodigo() == pedidoEscolhido.getCodigo()) {
                    item.cancelarItem();
                    itensParaRemover.add(item);
                }
            }

            itens.removeAll(itensParaRemover);
            System.out.println("Pedido cancelado com sucesso!");
        }
    }
}
