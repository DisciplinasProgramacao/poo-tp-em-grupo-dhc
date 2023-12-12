package codigo;

public enum ETipoVeiculo {
	CARRO(50.0, 10000.0, 10000.0, 10000.0, ECombustivel.ALCOOL),
    VAN(60.0, 10000.0, 10000.0, 12000.0, ECombustivel.GASOLINA),
    FURGAO(80.0, 10000.0, 10000.0, 12000.0, ECombustivel.GASOLINA),
    CAMINHAO(250.0, 20000.0, 20000.0, 20000.0, ECombustivel.DIESEL);

	private double capacidadeTanque;
    private double autonomiaDiaria;
    private double kmManutencao;
    private double kmTrocaPecas;
    private ECombustivel tipoCombustivel;

    ETipoVeiculo(double capacidadeTanque, double autonomiaDiaria, double kmManutencao, double kmTrocaPecas,
            ECombustivel tipoCombustivel) {
    this.capacidadeTanque = capacidadeTanque;
    this.autonomiaDiaria = autonomiaDiaria;
    this.kmManutencao = kmManutencao;
    this.kmTrocaPecas = kmTrocaPecas;
    this.tipoCombustivel = tipoCombustivel;
}

public double getCapacidadeTanque() {
    return capacidadeTanque;
}

public double getAutonomiaDiaria() {
    return autonomiaDiaria;
}

public double getKmManutencao() {
    return kmManutencao;
}

public double getKmTrocaPecas() {
    return kmTrocaPecas;
}

public ECombustivel getTipoCombustivel() {
    return tipoCombustivel;
}
}
