package codigo;

/**
 * Enumeração que representa os diferentes tipos de veículos disponíveis.
 * Cada constante enum possui atributos específicos que definem suas características,
 * como a capacidade do tanque, a autonomia diária e o tipo de combustível.
 */
public enum ETipoVeiculo {
	//tipos de veículo e seus respectivos atributos
	CARRO(50.0, 10000.0, ECombustivel.ALCOOL),
    VAN(60.0, 10000.0, ECombustivel.GASOLINA),
    FURGAO(80.0, 10000.0,ECombustivel.GASOLINA),
    CAMINHAO(250.0, 20000.0, ECombustivel.DIESEL);

	// Atributos que definem as características de cada tipo de veículo
	private double capacidadeTanque;
    private double autonomiaDiaria;
    private ECombustivel tipoCombustivel;

    
    /**
     * Construtor que inicializa os atributos para cada tipo de veículo.
     * @param capacidadeTanque A capacidade do tanque do veículo em litros.
     * @param autonomiaDiaria A autonomia diária estimada do veículo em quilômetros.
     * @param tipoCombustivel O tipo de combustível utilizado pelo veículo.
     */
    ETipoVeiculo(double capacidadeTanque, double autonomiaDiaria,
            ECombustivel tipoCombustivel) {
    this.capacidadeTanque = capacidadeTanque;
    this.autonomiaDiaria = autonomiaDiaria;
    this.tipoCombustivel = tipoCombustivel;
}

    /**
     * Obtém a capacidade do tanque do veículo.
     * @return A capacidade do tanque em litros.
     */    
    public double getCapacidadeTanque() {
    return capacidadeTanque;
    }
    
    /**
     * Obtém a autonomia diária estimada do veículo.
     * @return A autonomia diária em quilômetros.
     */
    public double getAutonomiaDiaria() {
    return autonomiaDiaria;
    }

    /**
     * Obtém o tipo de combustível utilizado pelo veículo.
     * @return O tipo de combustível.
     */
    public ECombustivel getTipoCombustivel() {
    return tipoCombustivel;
    }
}
