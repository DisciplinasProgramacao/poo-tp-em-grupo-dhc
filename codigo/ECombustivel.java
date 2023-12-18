package codigo;

/**
 * Enumeração que representa diferentes tipos de combustíveis, cada um associado
 * a um consumo médio e um preço médio por unidade. Cada constante enum tem
 * informações
 */
public enum ECombustivel {
    ALCOOL(7.0, 3.29),
    GASOLINA(10.0, 5.19),
    DIESEL(4.0, 6.09);

	//Atributos da classe
    private double consumoMedio;
    private double precoMedioCombustivel;

    /**
     * Construtor para inicializar os valores de consumo médio e preço médio do combustível.
     * @param consumoMedio Consumo médio do combustível por unidade, em km/l.
     * @param precoMedioCombustivel Preço médio do combustível por unidade, em R$/litro.
     */
    ECombustivel(double consumoMedio, double precoMedioCombustivel) {
        this.consumoMedio = consumoMedio;
        this.precoMedioCombustivel = precoMedioCombustivel;
    }

    public double getConsumoMedio() {
        return consumoMedio;
    }

    public double getPrecoMedioCombustivel() {
        return precoMedioCombustivel;
    }
}