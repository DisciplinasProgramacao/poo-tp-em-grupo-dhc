package codigo;

/**
 * A classe Tanque representa o tanque de combustível de um veículo,
 * armazenando informações sobre consumo, capacidade máxima, capacidade atual e reabastecimentos.
 */
public class Tanque {
    private ECombustivel combustivel;
    private double capacidadeMaxima;
    private double capacidadeAtual;
    private double reabastecidos;

    public Tanque(ECombustivel combustivel, double capacidadeMaxima) {
        this.combustivel = combustivel;
        this.capacidadeMaxima = capacidadeMaxima;
        this.capacidadeAtual = 0.0;
    }

    public double getReabastecidos() {
        return reabastecidos;
    }

    public void setReabastecidos(double reabastecidos) {
        this.reabastecidos = reabastecidos;
    }

    public ECombustivel getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(ECombustivel combustivel) {
        this.combustivel = combustivel;
    }

    public double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(double capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public double getCapacidadeAtual() {
        return capacidadeAtual;
    }

    public void setCapacidadeAtual(double capacidadeAtual) {
        this.capacidadeAtual = capacidadeAtual;
    }
    
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
        if (litros < 0) {
            throw new IllegalArgumentException("O valor de litros deve ser não negativo.");
        }

        double capacidadeDisponivel = capacidadeMaxima - capacidadeAtual;

        if (litros > capacidadeDisponivel) {
            throw new IllegalArgumentException(
                    String.format("Não é possível adicionar %.2f litros, tanque cheio.", litros - capacidadeDisponivel));
        }
        
        reabastecidos += litros;
        capacidadeAtual += litros;
        
        return calcularPrecoAbastecimento(litros);
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
