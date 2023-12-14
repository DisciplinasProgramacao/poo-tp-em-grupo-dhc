package codigo;

import java.util.Arrays;
import java.util.*;

/**
 * A classe Veiculo representa um veículo na frota, contendo informações sobre suas rotas,
 * tanque, tipo de veículo, etc.
 */
public class Veiculo {

	private final int MAX_ROTAS = 5;
	private String placa;
	private Rota [] rotas;
	private List<Rota> todasRotas;
	private int quantRotas;
	private int quantRotasMes;
	private Tanque tanque;
	private ETipoVeiculo tipoVeiculo;
	private RelatorioVeiculo relatorio;
	private double totalReabastecido;
	private int mesAtual;
	
	
	public String getRelatorioVeiculo() {
		return relatorio.gerarRelatorioVeiculo(this);
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
        this.todasRotas = new ArrayList<>();
        this.tanque = new Tanque(tipoVeiculo.getTipoCombustivel(), tipoVeiculo.getCapacidadeTanque());
		this.quantRotas = 0;
		this.quantRotasMes = 0;
		this.relatorio = new RelatorioVeiculo();
		this.mesAtual = -1;
    }
	
	/**
     * Realiza a adição de uma rota ao veículo, verificando se é possível adicioná-la.
     * Caso necessário, realiza abastecimento automático.
     *
     * @param rota Rota a ser adicionada.
     * @return true se a rota foi adicionada com sucesso, false caso contrário.
     */
    public boolean addRota(Rota rota) {
    	if (podeAdicionarRota(rota)) {
    	        realizarAdicaoRota(rota);
    	        return true;
    	}
        System.out.println("Não foi possível adicionar: \n"+rota.relatorioRota(getPlaca()) + "Verifique as condições.\n");
        return false;
    }

    private boolean podeAdicionarRota(Rota rota) {
        double kmRota = rota.getQuilometragem();

        if (verificarMes(rota)) {
			zerarMes();
		}
        
        if (this.quantRotasMes >= MAX_ROTAS || kmRota > this.tanque.autonomiaMaxima()) {
            return false;
        }

        return true;
    }

    private void realizarAdicaoRota(Rota rota) {
        double litrosNecessarios = tanque.calcularLitrosNecessariosReabastecimento(rota) ;

        if (litrosNecessarios > 0) {
            this.abastecerVeiculo(litrosNecessarios);
        }

        todasRotas.add(rota);
        this.rotas[quantRotasMes] = rota;
        this.quantRotas++;
        this.quantRotasMes++;

        this.percorrerRota(rota);
    }
    
    public void abastecerVeiculo(double litros) {
		this.totalReabastecido += litros;
		tanque.abastecer(litros);
	}
    
    public double totalGasto() {
    	return totalReabastecido*(tanque.getCombustivel().getPrecoMedioCombustivel());
    }
	
	
	 /**
     * Calcula a quilometragem total percorrida pelo veículo no mês especificado.
     *
     * @param mes Número do mês (1 a 12).
     * @return Quilometragem total no mês.
     */
	public double kmNoMes(int mes) {
	    return Arrays.stream(rotas)
	            .filter(rota -> rota != null && rota.getData() != null && rota.getData().getMes() == mes)
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
	            .filter(Objects::nonNull)
	            .mapToDouble(Rota::getQuilometragem)
	            .sum();
	}

	 /**
     * Executa a lógica de percorrer uma rota, atualizando a capacidade do tanque.
     *
     * @param rota Rota a ser percorrida.
     */
	private void percorrerRota(Rota rota) {
		tanque.setCapacidadeAtual(tanque.calcularConsumo(rota));
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
	
	
	public List<Rota> getTodasRotas() {
        return todasRotas;
    }
	
	public ETipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}
	
	public Tanque getTanque() {
		return tanque;
	}
	
	public double getTotalReabastecido() {
		return totalReabastecido;
	}
	
	private void zerarMes() {
		this.quantRotasMes = 0;
	}
	
	
	 /**
     * Verifica se a rota pertence ao mês atual, exibindo uma mensagem caso contrário.
     * 
     * @param mes Mês a ser verificado (número de 1 a 12).
     */
	private boolean verificarMes(Rota rota) {
		boolean trocouDeMes = false;

		if (this.mesAtual < rota.getData().getMes()) {
			this.mesAtual = rota.getData().getMes();
			trocouDeMes = true;
		}

		return trocouDeMes;
	}

}