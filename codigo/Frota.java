package codigo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Classe que representa uma frota de veículos.
 * Esta classe gerencia um conjunto de veículos, fornecendo métodos para adicionar,
 * recuperar e gerar relatórios sobre os veículos na frota.
 */
public class Frota {
	
	//Atributos da classe
    private int tamanhoFrota;
    private Veiculo veiculos[];
    private Veiculo veiculo;
    
    
    /**
    * Construtor que cria uma frota com um tamanho específico.
    *
    * @param tamanhoFrota O tamanho da frota.
    */
	public Frota(int tamanhoFrota) {
        this.tamanhoFrota = tamanhoFrota;
        this.veiculos = new Veiculo[tamanhoFrota];
    }

	/**
     * Obtém o array de veículos na frota.
     *
     * @return O array de veículos.
     */
    public Veiculo[] getVeiculos() {
		return veiculos;
	}
    
    /**
     * Obtém um único veículo da frota.
     *
     * @return A referência a um único veículo.
     */
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	
	public int getTamanhoFrota() {
		return this.tamanhoFrota;
	}
	
	 /**
     * Obtém uma lista de placas dos veículos na frota.
     *
     * @return Uma lista de placas dos veículos.
     */
	public List<String> getPlacasVeiculo() {
	    return Arrays.stream(veiculos)
	            .filter(Objects::nonNull)
	            .map(Veiculo::getPlaca)
	            .collect(Collectors.toList());
	}
	
	/**
     * Obtém um veículo com base em sua placa.
     *
     * @param placa A placa do veículo a ser obtido.
     * @return O veículo com a placa especificada, ou null se não encontrado.
     */
	public Veiculo getVeiculo(String placa) {
	    return Arrays.stream(veiculos)
	            .filter(Objects::nonNull)
	            .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
	            .findFirst()
	            .orElse(null);
	    }
	
	/**
     * Método para a impressão de um relatório contendo as informações solicitadas
     * pela empresa. Se o vetor estiver vazio, informa a falta de elemnetos
     * inseridos
     * 
     * @return
     */
	public String relatorioFrota() {
        StringBuilder relatorio = new StringBuilder();
        if (veiculos != null && veiculos.length > 0) {
            relatorio.append("::Relatório::\n\n");
            for (Veiculo veiculo : veiculos) {
            	relatorio.append("Placa: ").append(veiculo.getPlaca()).append("\nLitros reabastecidos: ")
                .append(String.format("%.2f", veiculo.getTotalReabastecido())).append(" litros\n")
                .append("Quilometragem rodada do mês atual: ")
                .append(String.format("%.2f", veiculo.kmNoMes(Calendar.MONTH))).append(" km\n")
                .append("Quilometragem total do veículo: ")
                .append(String.format("%.2f", veiculo.kmTotal())).append(" km\n\n");

            }
        } else {
            relatorio.append("Sem veículos adicionados até o momento");
        }
        return relatorio.toString();
    }
	
	/**
     * Método para gerar um relatório geral da frota, incluindo informações de cada veículo.
     *
     * @return Uma string contendo o relatório geral da frota.
     */
	 public String relatorioGeralFrota() {
	        StringBuilder aux = new StringBuilder();

	        Arrays.stream(veiculos)
	                .filter(Objects::nonNull)
	                .forEach(v -> aux.append(v.getRelatorioVeiculo()).append("\n"));

	        return aux.toString();
	    }
	 
	 


    /**
     * Método para conferir e localizar a existência de um determinado veículo por
     * meio da placa do mesmo.
     * 
     * @param placa
     * @return mensagem indicando existência da veículo.
     */
    public Veiculo localizarVeiculo(String placa) {
    	return Arrays.stream(veiculos)
    			.filter(veiculo -> veiculo != null && veiculo.getPlaca().equals(placa))
    			.findFirst()
    			.orElse(null);
    }

    /**
     * Método para @return a quilometragem total percorrida pelo veículo
     */
    public double quilometragemTotal() {

        return Arrays.stream(veiculos).mapToDouble(Veiculo::kmTotal).sum();
    }

    /**
     * Confere e @return qual a maior quilometragem percorrida
     */
    public double maiorKmTotal() {

         return Arrays.stream(veiculos).mapToDouble(Veiculo::kmTotal).max()
                .orElse(0);
    }

    /**
     * 
     * @return Maior quilometragem média
     */
    public double maiorKmMedia() {

        return Arrays.stream(veiculos).mapToDouble(veiculo -> veiculo.kmTotal() / veiculo.getQuantRotas()).max()
                .orElse(0);
    }
    
    /**
     * Método para adicionar um veículo à frota.
     * 
     * @param veiculo Veículo a ser adicionado
     * @return true se adicionado com sucesso, false se a frota estiver cheia
     */
    public boolean adicionarVeiculo(Veiculo veiculo) {
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] == null) {
                veiculos[i] = veiculo;
                return true;
            }
        }
        return false;
    }

    
}