package codigo;

import java.util.Calendar;

public class App {
    public static void main(String[] args) {
        // Create vehicles
    	Veiculo HB20 = new Veiculo("ABC1234", ETipoVeiculo.CARRO);
    	Veiculo CAMINHAOZAO = new Veiculo("ZXY1234", ETipoVeiculo.CAMINHAO);
      	Veiculo FURGAOZAO = new Veiculo("BBB9876", ETipoVeiculo.FURGAO);
      	Veiculo VANDOSCOOBY = new Veiculo("AAA1234", ETipoVeiculo.VAN);
      	Veiculo OPALA = new Veiculo("XXX9874", ETipoVeiculo.CARRO);

        // Create routes
      	Rota rota1 = new Rota(100.0, new Data(1, 1, 2023));
      	Rota rota2 = new Rota(750.0, new Data(5, 1, 2023));
      	Rota rota3 = new Rota(200.0, new Data(10, 1, 2023));
      	Rota rota4 = new Rota(200.0, new Data(10, 2, 2023));
      	Rota rota5 = new Rota(900.0, new Data(15, 2, 2023));
      	Rota rota6 = new Rota(900.0, new Data(15, 2, 2023));
      	Rota rota7 = new Rota(900.0, new Data(15, 2, 2023));
      	Rota rota8 = new Rota(900.0, new Data(15, 2, 2023));
      	Rota rota9 = new Rota(900.0, new Data(15, 2, 2023));
      	
      	
      	
      	HB20.abastecerVeiculo(50);
        // Add routes to vehicles
      	HB20.addRota(rota1);
      	CAMINHAOZAO.addRota(rota2);
      	FURGAOZAO.addRota(rota3);
      	VANDOSCOOBY.addRota(rota4);
      	OPALA.addRota(rota5);
      	
      	CAMINHAOZAO.addRota(rota3);
      	CAMINHAOZAO.addRota(rota9);
      	CAMINHAOZAO.addRota(rota8);
      	CAMINHAOZAO.addRota(rota7);
      	
      	
      	System.out.println(CAMINHAOZAO.kmTotal());
      	try {
            CAMINHAOZAO.addRota(rota6);
      	} catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro ao adicionar rota: " + e.getMessage());
        }
      	
        // Display reports
        System.out.println("Relatório do Veículo Carro(HB20:\n" + HB20.getRelatorioVeiculo());
        System.out.println("\nRelatório do Veículo Caminhao:\n" + CAMINHAOZAO.getRelatorioVeiculo());
        System.out.println("Relatório do Veículo FURGAO:\n" + FURGAOZAO.getRelatorioVeiculo());
        System.out.println("Relatório do Veículo VAN:\n" + VANDOSCOOBY.getRelatorioVeiculo());
        System.out.println("Relatório do Veículo Carro(OPALA):\n" + OPALA.getRelatorioVeiculo());
        
        // Create a fleet and add vehicles
        Frota frota = new Frota(5);
        frota.adicionarVeiculo(HB20);
        frota.adicionarVeiculo(CAMINHAOZAO);
        frota.adicionarVeiculo(FURGAOZAO);
        frota.adicionarVeiculo(VANDOSCOOBY);
        frota.adicionarVeiculo(OPALA);

    }}
