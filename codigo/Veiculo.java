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
	private RelatorioVeiculo relatorio;
	
	public String getRelatorioVeiculo() {
		return relatorio.gerarRelatorioCompleto(this);
	}
	/**
     * Construtor da classe Veiculo.
     * 
     * @param placa Placa do veículo.
     * @param tipoVeiculo Tipo de veículo (enum ETipoVeiculo).
     */
    public Veiculo(String placa, ETipoVeiculo tipoVeiculo) {
        this.placa = placa;
        this.tipoVeiculo = tipoVeiculo;
        this.rotas = new Rota[MAX_ROTAS];
        this.tanque = new Tanque(tipoVeiculo.getTipoCombustivel(), tipoVeiculo.getCapacidadeTanque());
    }
	
	/**
     * Realiza a adição de uma rota ao veículo, verificando se é possível adicioná-la.
     * Caso necessário, realiza abastecimento automático.
     *
     * @param rota Rota a ser adicionada.
     * @return true se a rota foi adicionada com sucesso, false caso contrário.
     */
	 public boolean addRota(Rota rota) {
	        boolean adicionada = true;
	        double kmRota = rota.getQuilometragem();

	        if (this.quantRotas < MAX_ROTAS) {
	            verificarMes(rota.getData().getMes());
	        }

	        if (kmRota > this.autonomiaMaxima() || this.quantRotas >= MAX_ROTAS) {
	            adicionada = false;
	        } else {
	            try {
	                double litrosNecessarios = calcularLitrosNecessariosReabastecimento(rota);

	                if (litrosNecessarios > 0) {
	                    double litrosAbastecidos = this.abastecerVeiculo(litrosNecessarios);
	                    System.out.println("Litros abastecidos: " + litrosAbastecidos);
	                }

	                this.rotas[quantRotas] = rota;
	                this.quantRotas++;
	                this.percorrerRota(rota);
	            } catch (IllegalArgumentException e) {
	                System.out.println("Erro ao adicionar rota: " + e.getMessage());
	            }
	        }

	        return adicionada;
	    }

	    public double abastecerVeiculo(double litros) throws IllegalArgumentException {
	        if (tanque == null) {
	            throw new IllegalArgumentException("Veículo não possui tanque associado.");
	        }

	        return tanque.abastecer(litros);
	    }
	
	public double calcularLitrosNecessariosReabastecimento(Rota rota) {
		
	    double quilometrosFaltantes = rota.getQuilometragem() - autonomiaAtual();
	    double litrosNecessarios = quilometrosFaltantes / tipoVeiculo.getTipoCombustivel().getConsumoMedio();
	    
	    return litrosNecessarios;
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
     * Calcula a quilometragem total percorrida pelo veículo no mês especificado.
     *
     * @param mes Número do mês (1 a 12).
     * @return Quilometragem total no mês.
     */
	public double kmNoMes(int mes) {
	    return Arrays.stream(rotas)
	            .filter(rota -> rota.getData().getMes() == mes)
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
        }
    }

}
