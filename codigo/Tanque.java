package codigo;

/**
 * A classe Tanque representa o tanque de combustível de um veículo,
 * armazenando informações sobre consumo, capacidade máxima, capacidade atual e reabastecimentos.
 */
public class Tanque {
	// Atributos da classe
    private ECombustivel combustivel;
    private double capacidadeMaxima;
    private double capacidadeAtual;
    private double reabastecidos;

    /**
     * Construtor da classe Tanque.
     *
     * @param combustivel      Tipo de combustível do tanque.
     * @param capacidadeMaxima Capacidade máxima do tanque em litros.
     */
    public Tanque(ECombustivel combustivel, double capacidadeMaxima) {
        this.combustivel = combustivel;
        this.capacidadeMaxima = capacidadeMaxima;
        this.capacidadeAtual = 0.0;
    }

    public double getReabastecidos() {
        return reabastecidos;
    }

    public ECombustivel getCombustivel() {
        return combustivel;
    }
    
    /**
     * Calcula o preço médio do combustível do tanque.
     *
     * @return Preço médio do combustível.
     */
    public double calcularPrecoCombustivel() {
        return combustivel.getPrecoMedioCombustivel();
    }

    public double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public double getCapacidadeAtual() {
        return capacidadeAtual;
    }
    
    /**
     * Define a capacidade atual do tanque em litros.
     *
     * @param capacidadeAtual Nova capacidade atual do tanque.
     */
    public void setCapacidadeAtual(double capacidadeAtual) {
    	try {
    		if (capacidadeAtual>=0) {
    			this.capacidadeAtual = capacidadeAtual;
    		}}catch (IllegalArgumentException e) {
    			e.getMessage();
    		}
    }

    /**
     * Calcula a quantidade de litros necessários para reabastecer o tanque e percorrer uma rota específica.
     *
     * @param rota Rota a ser percorrida.
     * @return Quantidade de litros necessários para a rota.
     */
    public double calcularLitrosNecessariosReabastecimento(Rota rota) {
    	
        double quilometrosFaltantes = rota.getQuilometragem() - autonomiaAtual();
        return quilometrosFaltantes / combustivel.getConsumoMedio();
    }
    
    /**
     * Realiza o abastecimento do tanque com uma quantidade específica de litros.
     *
     * @param litros Quantidade de litros a ser abastecida.
     * @return O preço gasto pelo reabastecimento feito.
     */
    public double abastecer(double litros) throws IllegalArgumentException {
    	try {
    		if (litros > 0) {

    			double capacidadeDisponivel = capacidadeMaxima - capacidadeAtual;

    			if (litros > capacidadeDisponivel) {
    				reabastecidos+=capacidadeDisponivel;
    				capacidadeAtual=capacidadeMaxima;
    			}
    			else {
    			reabastecidos += litros;
    			capacidadeAtual += litros;
    			}
    			return calcularPrecoAbastecimento(litros);}}
    	catch (IllegalArgumentException e) {
    		e.getMessage();
    	}
    	return 0;
    }

    
    /**
     * Calcula o consumo de combustível para percorrer a rota especificada.
     *
     * @param rota Rota a ser percorrida.
     * @return Consumo de combustível.
     */
	public double calcularConsumo(Rota rota) {
		
		return getCapacidadeAtual()-(rota.getQuilometragem() / combustivel.getConsumoMedio() );
	}
    
    /**
     * Calcula a autonomia máxima do veículo com base na capacidade atual e no consumo médio.
     *
     * @return A autonomia máxima em quilômetros.
     */
    public double autonomiaMaxima() {
        return capacidadeMaxima * combustivel.getConsumoMedio();
    }

    /**
     * Calcula a autonomia atual do veículo com base na capacidade atual e no consumo médio.
     *
     * @return A autonomia atual em quilômetros.
     */
    public double autonomiaAtual() {
        return capacidadeAtual * combustivel.getConsumoMedio();
    }
    
    /**
     * Calcula o preço do abastecimento com base na quantidade de litros e no preço médio do combustível.
     *
     * @param litros Quantidade de litros a ser abastecida.
     * @return O preço do abastecimento.
     */
    private double calcularPrecoAbastecimento(double litros) {
        return litros * combustivel.getPrecoMedioCombustivel();
    }
}
