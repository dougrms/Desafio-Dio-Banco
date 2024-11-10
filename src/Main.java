import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        List<Banco> bancos = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        int opcao1;

        do {
            System.out.println("--------------------------------------");
            System.out.println("Menu Principal:\n");
            System.out.println("1. Cadastrar Cliente.");
            System.out.println("2. Criar Banco.");
            System.out.println("3. Criar Conta.");
            System.out.println("4. Acessar Conta.");
            System.out.println("\n5. Sair.");
            opcao1 = sc.nextInt();
            sc.nextLine(); // Limpar o buffer após nextInt()

            switch (opcao1) {
                case 1:
                    System.out.println("--------------------------------------");
                    System.out.println("Digite o nome do cliente:");
                    String nomeCliente = sc.nextLine();
                    Cliente cliente = new Cliente();
                    cliente.setNome(nomeCliente);
                    clientes.add(cliente);
                    System.out.println("\nCliente " + cliente.getNome() + " cadastrado com sucesso.\n");
                    break;

                case 2:
                    System.out.println("--------------------------------------");
                    System.out.println("Digite o nome do Banco:");
                    String nomeBanco = sc.nextLine();
                    Banco banco = new Banco();
                    banco.setNome(nomeBanco);
                    bancos.add(banco);
                    System.out.println("\nBanco " + banco.getNome() + " criado com sucesso.\n");
                    break;

                case 3:
                    System.out.println("--------------------------------------");
                    System.out.println("Selecione o banco: ");
                    if (bancos.isEmpty()) {
                        System.out.println("Nenhum banco criado. Crie um banco primeiro.");
                        break;
                    }

                    for (int i = 0; i < bancos.size(); i++) {
                        System.out.println((i + 1) + ". " + bancos.get(i).getNome());
                    }
                    int indiceBanco = sc.nextInt() - 1;
                    sc.nextLine(); // Limpar o buffer após nextInt()

                    if (indiceBanco < 0 || indiceBanco >= bancos.size()) {
                        System.out.println("Banco inválido.");
                        break;
                    }

                    Banco bancoSelecionado = bancos.get(indiceBanco);
                    System.out.println("Selecione o cliente: ");
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
                        break;
                    }

                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println((i + 1) + ". " + clientes.get(i).getNome());
                    }

                    int indiceCliente = sc.nextInt() - 1;
                    sc.nextLine(); // Limpar o buffer após nextInt()

                    if (indiceCliente < 0 || indiceCliente >= clientes.size()) {
                        System.out.println("Cliente inválido.");
                        break;
                    }

                    Cliente clienteSelecionado = clientes.get(indiceCliente);
                    System.out.println("Escolha o tipo de conta: \n1. Conta corrente. \n2. Conta poupança.");
                    int tipoConta = sc.nextInt();
                    sc.nextLine(); // Limpar o buffer após nextInt()

                    Conta conta;
                    if (tipoConta == 1) {
                        conta = new ContaCorrente(clienteSelecionado, bancoSelecionado);
                    } else if (tipoConta == 2) {
                        conta = new ContaPoupanca(clienteSelecionado, bancoSelecionado);
                    } else {
                        System.out.println("Tipo de conta inválido!\nUsando conta corrente como padrão.");
                        conta = new ContaCorrente(clienteSelecionado, bancoSelecionado);
                    }

                    bancoSelecionado.adicionarConta(conta);
                    System.out.println("Conta criada com sucesso no banco " + bancoSelecionado.getNome() + ".");
                    break;

                case 4:
                    System.out.println("--------------------------------------");
                    System.out.println("Selecione o banco: ");
                    if (bancos.isEmpty()) {
                        System.out.println("Nenhum banco criado. Crie um banco primeiro.");
                        break;
                    }

                    for (int i = 0; i < bancos.size(); i++) {
                        System.out.println((i + 1) + ". " + bancos.get(i).getNome());
                    }
                    int indiceBancoAcesso = sc.nextInt() - 1;
                    sc.nextLine(); // Limpar o buffer após nextInt()

                    if (indiceBancoAcesso < 0 || indiceBancoAcesso >= bancos.size()) {
                        System.out.println("Banco inválido.");
                        break;
                    }

                    Banco bancoSelecionadoAcesso = bancos.get(indiceBancoAcesso);
                    System.out.println("Selecione a conta: ");
                    List<Conta> contasBanco = bancoSelecionadoAcesso.getContas();
                    if (contasBanco.isEmpty()) {
                        System.out.println("Nenhuma conta cadastrada neste banco.");
                        break;
                    }

                    for (int i = 0; i < contasBanco.size(); i++) {
                        System.out.println((i + 1) + ". " + contasBanco.get(i).getCliente().getNome());
                    }
                    int indiceConta = sc.nextInt() - 1;
                    sc.nextLine(); // Limpar o buffer após nextInt()

                    if (indiceConta < 0 || indiceConta >= contasBanco.size()) {
                        System.out.println("Conta inválida.");
                        break;
                    }

                    Conta contaSelecionada = contasBanco.get(indiceConta);
                    int opcaoConta;
                    do {
                        System.out.println("--------------------------------------");
                        System.out.println("Menu da Conta:\n");
                        System.out.println("1. Sacar.");
                        System.out.println("2. Depositar.");
                        System.out.println("3. Transferir.");
                        System.out.println("4. Imprimir Extrato.");
                        System.out.println("5. Sair.");
                        opcaoConta = sc.nextInt();
                        sc.nextLine(); // Limpar o buffer após nextInt()

                        switch (opcaoConta) {
                            case 1:
                                System.out.println("Digite o valor a ser sacado:");
                                double valorSaque = sc.nextDouble();
                                contaSelecionada.sacar(valorSaque);
                                break;

                            case 2:
                                System.out.println("Digite o valor a ser depositado:");
                                double valorDeposito = sc.nextDouble();
                                contaSelecionada.depositar(valorDeposito);
                                break;

                            case 3:
                                System.out.println("Digite o valor a ser transferido:");
                                double valorTransferencia = sc.nextDouble();
                                sc.nextLine(); // Limpar o buffer após nextDouble()
                                
                                if (valorTransferencia <= 0) {
                                    System.out.println("Valor de transferência deve ser positivo.");
                                    break;
                                }

                                System.out.println("Selecione a conta de destino:");
                                for (int i = 0; i < contasBanco.size(); i++) {
                                    if (i != indiceConta) { // Não mostrar a própria conta como opção
                                        System.out.println((i + 1) + ". " + contasBanco.get(i).getCliente().getNome());
                                    }
                                }
                                int indiceContaDestino = sc.nextInt() - 1;
                                sc.nextLine(); // Limpar o buffer após nextInt()

                                if (indiceContaDestino < 0 || indiceContaDestino >= contasBanco.size() || indiceContaDestino == indiceConta) {
                                    System.out.println("Conta de destino inválida.");
                                    break;
                                }

                                Conta contaDestino = contasBanco.get(indiceContaDestino);
                                contaSelecionada.transferir(valorTransferencia, contaDestino);
                                break;

                            case 4:
                                contaSelecionada.imprimirExtrato();
                                break;

                            case 5:
                                System.out.println("Saindo do menu da conta.");
                                break;

                            default:
                                System.out.println("Opção inválida.");
                        }
                    } while (opcaoConta != 5);
                    break;

                case 5:
                    System.out.println("Saindo do sistema.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao1 != 5);
        
        sc.close();
    }
}
