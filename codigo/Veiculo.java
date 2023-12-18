package codigo;

import java.util.Arrays;
import java.util.*;

/**
 * A classe Veiculo representa um veículo na frota, contendo informações sobre suas rotas,
 * tanque, tipo de veículo, etc.
 */
public class Veiculo {

	//Atributos da classe
	private final int MAX_ROTAS = 30;
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
	private int manutencoesFeitas;
	private Despesas despesas;
	private Manutencao manutencao;
	private int trocaPecasFeitas;
	
	// Método para obter o relatório do veículo
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
		this.manutencoesFeitas=0;
		this.trocaPecasFeitas=0;
		this.despesas = new Despesas(this);
		this.manutencao = new Manutencao(this);
    }
	
    /**
     * Realiza a adição de uma rota ao veículo, verificando se é possível adicioná-la.
     * Caso necessário, realiza abastecimento automático.
     * Antes de percorrer a rota verifica se é necessária manutenção e/ou troca de peças. Caso positivo, acresce uma manutenção e/ou troca de peças.
     *
     * @param rota Rota a ser adicionada.
     * @return true se a rota foi adicionada com sucesso, false caso contrário.
     */
    public boolean addRota(Rota rota) {
    	try{
    		if (rota!=null) 
    		{
    			if (verificarManutencao()) {
					manutencoesFeitas++;
				}
				if (verificarTrocaPecas()) {
					trocaPecasFeitas++;
				}
				
    			if (podeAdicionarRota(rota)) {
    				realizarAdicaoRota(rota);
    				return true;
    			}
    		}
    	}catch (NullPointerException e) {
            e.getMessage();
    	}
    	return false;
    }

    /**
     * Verifica se é possível adicionar uma rota ao veículo. Caso o mes seja diferente, dados acerca do mês são zerados.
     * @param rota Rota a ser adicionada.
     * @return true se a rota pode ser adicionada, false caso contrário.
     */
    private boolean podeAdicionarRota(Rota rota) {
    	double kmRota = rota.getQuilometragem();
    	try {
    		if (kmRota>=0) {
    			if (verificarMes(rota)) {
    				zerarMes();
    			}

    			if (this.quantRotasMes >= MAX_ROTAS || kmRota > this.tanque.autonomiaMaxima()) {
    				return false;
    			}
    		}}catch (IllegalArgumentException e) {
    			e.getMessage();

    		}
    	return true;
    }

    /**
     * Aciona o método de abastecimento do veiculo caso necessário para percorrer a rota, além de incluir a rota em seus respectivos conjuntos e atualizar a quantidade de rota.
     * Por final aciona o método que percorre a Rota.
     * @param rota Rota a ser adicionada.
     */
    private void realizarAdicaoRota(Rota rota) {
    	double litrosNecessarios = tanque.calcularLitrosNecessariosReabastecimento(rota) ;

    	try {
    		if (litrosNecessarios > 0) {
    			this.abastecerVeiculo(litrosNecessarios);
    		}

    		todasRotas.add(rota);
    		this.rotas[quantRotasMes] = rota;
    		this.quantRotas++;
    		this.quantRotasMes++;

    		this.percorrerRota(rota);
    	}
    	catch (IllegalArgumentException e) {
    		e.getMessage();
    	}
    }
    
    /**
     * Abastece o veículo com a quantidade de litros especificada.
     * Atualiza o total reabastecido e o estado do tanque.
     *
     * @param litros Quantidade de litros a ser abastecida.
     */
    public void abastecerVeiculo(double litros) {
    	try {
    		if(litros>0) {
    			tanque.abastecer(litros);
    		}}
    	catch (IllegalArgumentException e) {
    		e.getMessage();
    	}
	}
    
    /**
     * Calcula o total gasto com manutenções no veículo.
     *
     * @return Total gasto com manutenções.
     */
    public double totalGastoManutencao() {
    	return manutencoesFeitas*(manutencao.precoManutencao());
    }
    
    /**
     * Calcula o total gasto com manutenções no veículo.
     *
     * @return Total gasto com manutenções.
     */
    public double totalGastoTrocaPecas() {
    	return trocaPecasFeitas*(manutencao.precoTrocaPecas());
    }
    
    /**
     * Calcula o total gasto com reabastecimento do veículo.
     *
     * @return Total gasto com reabastecimento.
     */
    public double totalGastoReabastecimento() {
    	this.totalReabastecido=tanque.getReabastecidos();
    	
        return totalReabastecido * tanque.calcularPrecoCombustivel() ;
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
		return tanque.getReabastecidos();
	}
	
	private void zerarMes() {
		this.quantRotasMes = 0;
	}
	
	public int getMesAtual() {
		return mesAtual;
	}
	
	public int getManutencoesFeitas() {
		return manutencoesFeitas;
	}
	
	public int getTrocaPecasFeitas() {
		return trocaPecasFeitas;
	}
	
	
	/**
     * Verifica se é necessário realizar uma manutenção no veículo.
     *
     * @return true se uma manutenção é necessária, false caso contrário.
     */
	public boolean verificarTrocaPecas() {
        return manutencao.verificarTrocaPecas();
    }
	
	/**
     * Verifica se é necessário realizar uma manutenção no veículo.
     *
     * @return true se uma manutenção é necessária, false caso contrário.
     */
	public boolean verificarManutencao() {
        return manutencao.verificarManutencao();
        
    }
	
	 public Despesas getDespesas() {
	        return despesas;
	    }
	
	 /**
     * Verifica se a rota pertence ao mês atual, exibindo uma mensagem caso contrário.
     * 
     * @param mes Mês a ser verificado (número de 1 a 12).
     */
	private boolean verificarMes(Rota rota) {
		boolean trocouDeMes = false;

		if (this.mesAtual < rota.getMes()) {
			this.mesAtual = rota.getMes();
			trocouDeMes = true;
		}

		return trocouDeMes;
	}

}