package codigo;

public enum ECombustivel {
    ALCOOL(7.0, 3.29),
    GASOLINA(10.0, 5.19),
    DIESEL(4.0, 6.09);

    private double consumoMedio;
    private double precoMedioCombustivel;

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