package codigo;

public class Despesas {

    private final Veiculo veiculo;

    public Despesas(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    // Novo método para calcular a despesa total considerando combustível e manutenções
    public double calcularDespesaTotal() {
        double despesaCombustivel = (veiculo.kmTotal() / veiculo.getTipoVeiculo().getTipoCombustivel().getConsumoMedio()) *
                veiculo.getTipoVeiculo().getTipoCombustivel().getPrecoMedioCombustivel();
        double despesaManutencao = 0.0; // Adicione lógica para calcular a despesa de manutenção
        return despesaCombustivel + despesaManutencao;
    }
}
