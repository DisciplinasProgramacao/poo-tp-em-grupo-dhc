package codigo;

public enum ETipoVeiculo {
	CARRO(50.0, 10000.0, ECombustivel.ALCOOL),
    VAN(60.0, 10000.0, ECombustivel.GASOLINA),
    FURGAO(80.0, 10000.0,ECombustivel.GASOLINA),
    CAMINHAO(250.0, 20000.0, ECombustivel.DIESEL);

	private double capacidadeTanque;
    private double autonomiaDiaria;
    private ECombustivel tipoCombustivel;

    ETipoVeiculo(double capacidadeTanque, double autonomiaDiaria,
            ECombustivel tipoCombustivel) {
    this.capacidadeTanque = capacidadeTanque;
    this.autonomiaDiaria = autonomiaDiaria;
    this.tipoCombustivel = tipoCombustivel;
}

public double getCapacidadeTanque() {
    return capacidadeTanque;
}

public double getAutonomiaDiaria() {
    return autonomiaDiaria;
}

public ECombustivel getTipoCombustivel() {
    return tipoCombustivel;
}
}
