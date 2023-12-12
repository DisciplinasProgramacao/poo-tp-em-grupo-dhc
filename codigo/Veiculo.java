package codigo;

import java.util.Arrays;
import java.io.*;
import java.util.*;

/**
 * A classe Veiculo representa um veículo na frota, contendo informações sobre suas rotas,
 * tanque, tipo de veículo, etc.
 */
public class Veiculo {

	private final int MAX_ROTAS = 30;
	private final double CONSUMO = 8.2;
	private String placa;
	private Rota [] rotas;
	private int quantRotas;
	private Tanque tanque;
	private double totalReabastecido;
	private ETipoVeiculo tipoVeiculo;
	
	
	/**
     * Realiza a adição de uma rota ao veículo, verificando se é possível adicioná-la.
     * Caso necessário, realiza abastecimento automático.
     *
     * @param rota Rota a ser adicionada.
     * @return true se a rota foi adicionada com sucesso, false caso contrário.
     */
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
				this.abastecerVeiculo(tanque.autonomiaMaxima() - tanque.autonomiaAtual());
			}
			this.rotas[quantRotas] = rota;
			this.quantRotas++;
			this.percorrerRota(rota);
		}
		return adicionada;
	}

	
	 /**
     * Calcula a autonomia máxima do veículo com base no tanque e no consumo médio.
     *
     * @return Autonomia máxima do veículo.
     */
	private double autonomiaMaxima() {
		return (tanque.getCapacidadeMaxima()*CONSUMO);
	}
	
	/**
     * Calcula a autonomia atual do veículo com base no tanque e no consumo médio.
     *
     * @return Autonomia atual do veículo.
     */
	private double autonomiaAtual() {
		return (tanque.getCapacidadeAtual()*CONSUMO);
	}

	/**
	 * Abastece o veículo com a quantidade especificada de litros.
	 * Verifica se a quantidade de litros é válida e exibe uma mensagem de erro se for negativa.
	 * Delega a operação de abastecimento para o método correspondente na classe Tanque.
	 * Atualiza o total reabastecido e a capacidade atual do tanque com base no resultado do abastecimento.
	 *
	 * @param litros Quantidade de litros a ser abastecida no veículo.
	 * @return A quantidade real de litros abastecidos no veículo, considerando as limitações do tanque.
	 */
	
    public double abastecerVeiculo(double litros) {
        if (litros < 0) {
            System.out.println("Digite um valor válido");
            return 0;
        }

        // Delega a operação de abastecimento para o tanque
        double litrosAbastecidos = tanque.abastecer(litros);

        if (litrosAbastecidos > 0) {
            totalReabastecido += litrosAbastecidos;
        }

        return litrosAbastecidos;
    }
	
	 /**
     * Calcula a quilometragem total percorrida pelo veículo no mês especificado.
     *
     * @param mes Número do mês (1 a 12).
     * @return Quilometragem total no mês.
     */
	public double kmNoMes(int mes) {
	    return Arrays.stream(rotas)
	            .filter(rota -> rota.getMes() == mes)
	            .mapToDouble(Rota::getQuilometragem) // Mapeia para os quilômetros e converte para double
	            .sum(); // Calcula a soma dos quilômetros no mês
	}

	/**
     * Calcula a quilometragem total percorrida pelo veículo.
     *
     * @return Quilometragem total do veículo.
     */
	public double kmTotal() {
	    return Arrays.stream(rotas)
	            .mapToDouble(Rota::getQuilometragem) // Mapeia para os quilômetros e converte para double
	            .sum(); // Calcula a soma total dos quilômetros percorridos pelo veículo
	}

	 /**
     * Executa a lógica de percorrer uma rota, atualizando a capacidade do tanque.
     *
     * @param rota Rota a ser percorrida.
     */
	private void percorrerRota(Rota rota) {
		tanque.setCapacidadeAtual(calcularConsumo(rota));
	}
	
	/**
     * Calcula o consumo de combustível para percorrer a rota especificada.
     *
     * @param rota Rota a ser percorrida.
     * @return Consumo de combustível.
     */
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

	public double getTotalReabastecido() {
		return totalReabastecido;
	}
	
	public ETipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}
	
	public Tanque getTanque() {
		return tanque;
	}
	
	
	 /**
     * Verifica se a rota pertence ao mês atual, exibindo uma mensagem caso contrário.
     * 
     * @param mes Mês a ser verificado (número de 1 a 12).
     */
	private void verificarMes(int mes) {
        Calendar calendario = Calendar.getInstance();
        int mesAtual = calendario.get(Calendar.MONTH) + 1; // +1 porque os meses em Calendar começam de 0 (janeiro) a 11 (dezembro)

        if (mes != mesAtual) {
            System.out.println("Atenção: A rota não pertence ao mês atual.");
            // Adicione a lógica adicional que deseja executar quando a rota não pertence ao mês atual.
        }
    }

}
