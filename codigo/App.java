package codigo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        //Criando veiculos
    	Veiculo HB20 = new Veiculo("ABC1234", ETipoVeiculo.CARRO);
    	Veiculo CAMINHAOZAO = new Veiculo("ZXY1234", ETipoVeiculo.CAMINHAO);
      	Veiculo FURGAOZAO = new Veiculo("BBB9876", ETipoVeiculo.FURGAO);
      	Veiculo VANDOSCOOBY = new Veiculo("AAA1234", ETipoVeiculo.VAN);
      	Veiculo OPALA = new Veiculo("XXX9874", ETipoVeiculo.CARRO);

      	
      	List<Rota> rotas = new ArrayList<>();
        Random random = new Random();

        //Utilizando Random para criar rotas
        for (int i = 0; i < 100; i++) {
            double distancia = random.nextDouble() * 1000.0; // Distância aleatória até 1000.0
            int dia = random.nextInt(28) + 1; // Dia aleatório entre 1 e 28
            int mes = random.nextInt(12) + 1; // Mês aleatório entre 1 e 12

            Rota rota = new Rota(distancia, new Data(dia, mes, 2023));
            rotas.add(rota);
        }
        //Adicionando veiculos criados a uma lista de veiculos
        List<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(HB20);
        veiculos.add(CAMINHAOZAO);
        veiculos.add(FURGAOZAO);
        veiculos.add(VANDOSCOOBY);
        veiculos.add(OPALA);
      	
      	//Testando o abastecimento do tanque do veiculo criado
      	HB20.abastecerVeiculo(100);
      	
        //Adicionando rotas nos veiculos
      	for (Veiculo veiculo : veiculos) {
            for (int i = 0; i < 5; i++) {
                double distancia = random.nextDouble() * 1000.0; // Distância aleatória até 1000.0
                int dia = random.nextInt(28) + 1; // Dia aleatório entre 1 e 28
                int mes = random.nextInt(12) + 1; // Mês aleatório entre 1 e 12

                Rota rota = new Rota(distancia, new Data(dia, mes, 2023));
                veiculo.addRota(rota);
            }
        }
      	
      	//Testando um dos metodos da classe veiculo para informar quantos km o veiculo percorreu
      	System.out.printf("Caminhaozao tem %.2f km no total%n", CAMINHAOZAO.kmTotal());
      	
        //Gerando relatório de todos os veiculos que informa: Placa, tipo, capacidade, autonomia diaria, autonomia atual, total reabastecido,
      	//quantidade de rotas, manutenções feitas, todas suas rotas com suas respectivas datas e quilometragens e todas suas despesas.
        System.out.println("Relatório do Veículo Carro(HB20:\n" + HB20.getRelatorioVeiculo());
        System.out.println("\nRelatório do Veículo Caminhao:\n" + CAMINHAOZAO.getRelatorioVeiculo());
        System.out.println("Relatório do Veículo FURGAO:\n" + FURGAOZAO.getRelatorioVeiculo());
        System.out.println("Relatório do Veículo VAN:\n" + VANDOSCOOBY.getRelatorioVeiculo());
        System.out.println("Relatório do Veículo Carro(OPALA):\n" + OPALA.getRelatorioVeiculo());
        
        //Criando uma frota com tamanho 5 e adicionando os veiculos a ela.
        try {
        Frota frota = new Frota(10);
        frota.adicionarVeiculo(HB20);
        frota.adicionarVeiculo(CAMINHAOZAO);
        frota.adicionarVeiculo(FURGAOZAO);
        frota.adicionarVeiculo(VANDOSCOOBY);
        frota.adicionarVeiculo(OPALA);
      
        //Testando o método de localizarVeiculo da frota
        System.out.println(frota.localizarVeiculo("ABC1234").getRelatorioVeiculo() );
        System.out.println("---------------------------------------------");
        //Gerando um relatório da frota criada que informa litros reabastecidos, quilometragem rodada do mês atual e quilometragem total do veículo
        System.out.println(frota.relatorioFrota());
        System.out.println("---------------------------------------------");
        //Imprimindo a maior média de km média dos veiculos da frota
        System.out.printf("O veículo que teve maior km de média teve %.2f km", frota.maiorKmMedia());
        System.out.println("---------------------------------------------");
        //Imprimindo o maior número de km que um veículo da frota percorreu
        System.out.printf("O veículo que teve maior km de média teve %.2f km", frota.maiorKmTotal());
        System.out.println("---------------------------------------------");
        }catch (NullPointerException | NegativeArraySizeException e) {
            e.getMessage();
    	}
        
      //Gerando um relatório das rotas que informa a data da rota, quilometragem e placa de cada veiculo
        for (Veiculo veiculo : veiculos) {
            System.out.println("\nRelatório de Rotas para o Veículo " + veiculo.getPlaca() + ":");
            for (Rota rota : veiculo.getRotas()) {
                try {
                    if (rota != null) {
                        System.out.println(rota.relatorioRota(veiculo.getPlaca()));
                    }
                } catch (Exception e) {
                    // Tratamento para a exceção, se necessário.
                    System.out.println("Exceção ao gerar relatório para o veículo " + veiculo.getPlaca() + ": " + e.getMessage());
                }
            }
        }

    }}
