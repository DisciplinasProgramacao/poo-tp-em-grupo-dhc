package codigo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class AppMenu {

    private static Scanner teclado;
    private static Map<String, Frota> frotas;
    private static Map<String, Rota> rotasDisponiveis;

    public static void main(String[] args) throws FileNotFoundException {
        frotas = new HashMap<>();
        rotasDisponiveis = new HashMap<>();
        teclado = new Scanner(System.in);

        frotas.put("#1AB", criarFrotaInicial());

        int opcao = -1;
        while (opcao != 0) {
            limparTela();
            opcao = menuPrincipal();
            switch (opcao) {
                case 0:
                    System.out.println("Finalizando");
                    break;
                case 1:
                    gerenciamentoFrota();
                    break;
                case 2:
                    menuRelatorios();
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pausa() {
        System.out.println("Pressione Enter para continuar...");
        teclado.nextLine();
    }

    private static int menuPrincipal() {
        System.out.println("===== Menu Principal =====");
        System.out.println("1 - Gerenciamento de Frota");
        System.out.println("2 - Relatórios");
        System.out.println("0 - Sair");
        System.out.print("Escolha a opção: ");
        return Integer.parseInt(teclado.nextLine());
    }

    private static int menu(String nomeArquivo) throws FileNotFoundException {
        limparTela();
        File arqMenu = new File(nomeArquivo);
        Scanner leitor = new Scanner(arqMenu, "UTF-8");
        System.out.println(leitor.nextLine());
        System.out.println("==========================");
        int contador = 1;
        while (leitor.hasNextLine()) {
            System.out.println(contador + " - " + leitor.nextLine());
            contador++;
        }
        System.out.println("0 - Voltar");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        leitor.close();
        return opcao;
    }

    private static void gerenciamentoFrota() throws FileNotFoundException {
        int opcao = -1;
        while (opcao != 0) {
            limparTela();
            opcao = menu("menuFrota");
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    criarFrota();
                    pausa();
                    break;
                case 2:
                    adicionarVeiculoFrota();
                    break;
                case 3:
                    adicionarRotaVeiculo();
                    break;
                default:
                    System.out.println("Opção inválida");
                    pausa();
                    break;
            }
        }
    }

    private static void criarFrota() {
        System.out.println("Digite o tamanho da frota (Quantidade de veículos): ");
        int tamanhoFrota = Integer.parseInt(teclado.nextLine());
        Frota frota = new Frota(tamanhoFrota);
        String codigoFrota = gerarCodigoAleatorio();
        frotas.put(codigoFrota, frota);
        System.out.println("Frota criada com sucesso!\nCódigo da Frota: " + codigoFrota);
    }

    private static void adicionarVeiculoFrota() throws FileNotFoundException {
        String codigoFrota = selecionarFrota();
        Frota frota = frotas.get(codigoFrota);
        String nomeArq = "menuVeiculos";
        limparTela();
        int opcao = menu(nomeArq);
        Veiculo veiculo;

        System.out.println("Digite a placa do veículo: ");
        String placa = teclado.nextLine();

        ETipoVeiculo tipoVeiculo = coletaTipoVeiculo(opcao);

        veiculo = new Veiculo(placa, tipoVeiculo);
        addFrota(veiculo, frota);
        pausa();
    }

    private static ETipoVeiculo coletaTipoVeiculo(int opcao) {
        switch (opcao) {
            case 1:
                return ETipoVeiculo.CARRO;
            case 2:
                return ETipoVeiculo.CAMINHAO;
            case 3:
                return ETipoVeiculo.FURGAO;
            case 4:
                return ETipoVeiculo.VAN;
            default:
                return ETipoVeiculo.CARRO;
        }
    }

    private static void addFrota(Veiculo veiculo, Frota frota) {
        frota.adicionarVeiculo(veiculo);
    }

    private static String selecionarFrota() {
        System.out.println("FROTAS DISPONÍVEIS:");
        List<String> keysList = new ArrayList<>(frotas.keySet());
        StringBuilder aux = new StringBuilder();
        keysList.forEach(key -> aux.append(key).append(" ").append("\n"));
        System.out.println(aux.toString());
        System.out.println("Digite o código da frota:");
        return teclado.nextLine();
    }

    private static void adicionarRotaVeiculo() {
        String codigoFrota = selecionarFrota();
        Frota frota = frotas.get(codigoFrota);
        System.out.println("Veículos disponíveis:");
        frota.getPlacasVeiculo().forEach(placa -> System.out.println(placa));
        System.out.println("Digite a placa do veículo:");
        String placa = teclado.nextLine();
        Veiculo veiculo = frota.getVeiculo(placa);
        String codRota = selecionarRota();
        if (veiculo != null) {
            if (veiculo.addRota(rotasDisponiveis.get(codRota))) {
                System.out.println("Rota adicionada com sucesso!");
                rotasDisponiveis.remove(codRota);
                pausa();
            } else {
                System.out.println("Não foi possível adicionar rota!");
                pausa();
            }
        }
    }

    private static String selecionarRota() {
        System.out.println("ROTAS DISPONÍVEIS:");
        StringBuilder aux = new StringBuilder();
        rotasDisponiveis.forEach((codigo, rota) -> aux.append(codigo).append(":").append(rota.toString()).append("\n"));
        System.out.println(aux.toString());
        System.out.println("Digite o código da rota:");
        return teclado.nextLine();
    }

    private static void menuRelatorios() throws FileNotFoundException {
        String nomeArq = "menuRelatorios";
        String codigoFrota;
        Frota frota;
        int opcao = -1;
        while (opcao != 0) {
            limparTela();
            opcao = menu(nomeArq);
            switch (opcao) {
                case 0:
                    System.out.println("");
                    break;
                case 1:
                    codigoFrota = selecionarFrota();
                    frota = frotas.get(codigoFrota);
                    relatoriosFrota(frota);
                    pausa();
                    break;
                default:
                    System.out.println("Opção inválida");
                    pausa();
                    break;
            }
        }
    }

    public static void relatoriosFrota(Frota frota) throws FileNotFoundException {
        try {
            String nomeArq = "menuRelatoriosFrotas";
            int opcao = -1;
            while (opcao != 0) {
                limparTela();
                opcao = menu(nomeArq);
                switch (opcao) {
                    case 0:
                        System.out.println("");
                        break;
                    case 1:
                        System.out.println(frota.relatorioGeralFrota());
                        pausa();
                        break;
                    case 2:
                        System.out.println(frota.relatorioManutencao() + "\n");
                        pausa();
                        break;
                    case 3:
                        System.out.println("O veículo com maior quilometragem é: " + frota.maiorKmTotal());
                        pausa();
                        break;
                    case 4:
                        System.out.println("O veículo com maior quilometragem média é: " + frota.maiorKmMedia());
                        pausa();
                        break;
                    case 5:
                        System.out.println("A quilometragem total da frota é de: " + frota.quilometragemTotal() + " km");
                        pausa();
                        break;
                    case 6:
                        System.out.println(frota.relatorioFrota());
                        pausa();
                        break;
                    default:
                        System.out.println("Opcão inválida");
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }
    }

    private static Frota criarFrotaInicial() {
        Frota frota = new Frota(10);

        // Criando alguns veículos e adicionando-os à frota
        Veiculo veiculo1 = new Veiculo("ABC1234", ETipoVeiculo.CARRO);
        Veiculo veiculo2 = new Veiculo("ZXY1234", ETipoVeiculo.CAMINHAO);
        Veiculo veiculo3 = new Veiculo("BBB9876", ETipoVeiculo.FURGAO);
        Veiculo veiculo4 = new Veiculo("AAA1234", ETipoVeiculo.VAN);
        Veiculo veiculo5 = new Veiculo("XXX9874", ETipoVeiculo.CARRO);

        frota.adicionarVeiculo(veiculo1);
        frota.adicionarVeiculo(veiculo2);
        frota.adicionarVeiculo(veiculo3);
        frota.adicionarVeiculo(veiculo4);
        frota.adicionarVeiculo(veiculo5);

        // Adicionando algumas rotas aos veículos
        Rota rota1 = new Rota(1000.0, new Data(1, 1, 2023));
        Rota rota2 = new Rota(750.0, new Data(5, 1, 2023));
        Rota rota3 = new Rota(200.0, new Data(10, 1, 2023));
        Rota rota4 = new Rota(120.0, new Data(10, 2, 2023));
        Rota rota5 = new Rota(120.0, new Data(15, 2, 2023));

        veiculo1.addRota(rota1);
        veiculo1.addRota(rota2);
        veiculo1.addRota(rota3);
        veiculo1.addRota(rota4);
        veiculo1.addRota(rota5);
        
        return frota;
    }

    private static Rota gerarRotaAleatoria() {
        double quilometragem = gerarNumeroAleatorio(10, 10000); // km entre 10 e 10000 km
        int dia = gerarNumeroAleatorio(1, 28); // Dia entre 1 e 28
        int mes = gerarNumeroAleatorio(1, 12); // Mês entre 1 e 12
        int ano = 2023; // Em 2023

        return new Rota(quilometragem, new Data(dia, mes, ano));
    }

    private static void adicionarRotaAleatoria() {
        for (int i = 0; i < 50; i++) {
            rotasDisponiveis.put(gerarCodigoAleatorio(), gerarRotaAleatoria());
        }
    }

    private static String gerarCodigoAleatorio() {
        int numero1 = gerarNumeroAleatorio(0, 9);
        int numero2 = gerarNumeroAleatorio(0, 9);
        String letrasAleatorias = gerarLetrasAleatorias(3);
        return "#" + numero1 + numero2 + letrasAleatorias;
    }

    private static int gerarNumeroAleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    private static String gerarLetrasAleatorias(int quantidade) {
        Random random = new Random();
        StringBuilder letras = new StringBuilder();
        for (int i = 0; i < quantidade; i++) {
            char letra = (char) (random.nextInt(26) + 'A');
            letras.append(letra);
        }
        return letras.toString();
    }
}
