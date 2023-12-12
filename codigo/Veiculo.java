package codigo;

import java.util.Arrays;
import java.io.*;
import java.util.*;

public class Veiculo {

	private final int MAX_ROTAS = 30;
	private final double CONSUMO = 8.2;
	private String placa;
	private Rota [] rotas;
	private int quantRotas;
	private Tanque tanque;
	private double totalReabastecido;
	private ETipoVeiculo tipoVeiculo;
	
	
	public boolean addRota(Rota rota){
		boolean adicionada = true;
		double kmRota = rota.getQuilometragem();

		
		if (this.quantRotas < MAX_ROTAS) {
		        verificarMes(rota.getMes());
		}
	
		if (kmRota > this.autonomiaMaxima() || this.quantRotas >= MAX_ROTAS ) {
			adicionada = false;
		} 
		else {
			if (rota.getQuilometragem() > this.autonomiaAtual()) {
				this.abastecer(tanque.autonomiaMaxima() - tanque.autonomiaAtual());
			}
			this.rotas[quantRotas] = rota;
			this.quantRotas++;
			this.percorrerRota(rota);
		}
		return adicionada;
	}

	private double autonomiaMaxima() {
		return (tanque.getCapacidadeMaxima()*CONSUMO);
	}

	private double autonomiaAtual() {
		return (tanque.getCapacidadeAtual()*CONSUMO);
	}

	public double abastecer(double litros) {
		if (litros<0)
		{
			System.out.println("Digite um valor válido");
		return 0;
		}
		if (tanque.getCapacidadeAtual()+litros>tanque.getCapacidadeMaxima())
		{
			System.out.printf("Não foi possivel adicionar %d litros, tanque cheio", tanque.getCapacidadeAtual()+litros-tanque.getCapacidadeMaxima());
			tanque.setCapacidadeAtual(tanque.getCapacidadeMaxima());
			tanque.setReabastecidos(tanque.getCapacidadeMaxima()-tanque.getCapacidadeAtual());
			return (tanque.getCapacidadeMaxima()-tanque.getCapacidadeAtual());
			
		}
		else 
		{	
			tanque.setReabastecidos(tanque.getReabastecidos()+litros);
			tanque.setCapacidadeAtual(tanque.getCapacidadeAtual()+litros);
			return litros;
		}
	}

	public double kmNoMes(int mes) {
	    return Arrays.stream(rotas)
	            .filter(rota -> rota.getMes() == mes)
	            .mapToDouble(Rota::getQuilometragem) // Mapeia para os quilômetros e converte para double
	            .sum(); // Calcula a soma dos quilômetros no mês
	}

	public double kmTotal() {
	    return Arrays.stream(rotas)
	            .mapToDouble(Rota::getQuilometragem) // Mapeia para os quilômetros e converte para double
	            .sum(); // Calcula a soma total dos quilômetros percorridos pelo veículo
	}

	private void percorrerRota(Rota rota) {
		tanque.setCapacidadeAtual(calcularConsumo(rota));
	}
	
	private double calcularConsumo(Rota rota) {
		
		return tanque.getCapacidadeAtual()-(rota.getQuilometragem() / CONSUMO);
	}
	
	public String getPlaca() {
	    return placa;
	}
	
	public int getQuantRotas() {
		return quantRotas;
	}
	
	public Rota[] getRotas() {
		return rotas;
	}

//	public void setRotas(Rota[] rotas) {
//		this.rotas = rotas;
//	}

	public double getTotalReabastecido() {
		return totalReabastecido;
	}
	
	public ETipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}
	
	public Tanque getTanque() {
		return tanque;
	}
	
	private void verificarMes(int mes) {
        Calendar calendario = Calendar.getInstance();
        int mesAtual = calendario.get(Calendar.MONTH) + 1; // +1 porque os meses em Calendar começam de 0 (janeiro) a 11 (dezembro)

        if (mes != mesAtual) {
            System.out.println("Atenção: A rota não pertence ao mês atual.");
            // Adicione a lógica adicional que deseja executar quando a rota não pertence ao mês atual.
        }
    }

}
