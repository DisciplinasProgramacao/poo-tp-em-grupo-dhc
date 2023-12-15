package codigo;

/**
 * A classe Despesas é responsável por calcular as despesas totais associadas a um veículo,
 * incluindo despesas de combustível, manutenção e troca de peças.
 */
public class Despesas {

    private final Veiculo veiculo;

    /**
     * Construtor da classe Despesas.
     * @param veiculo O veículo associado às despesas.
     */
    public Despesas(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * Calcula a despesa total do veículo, somando as despesas de combustível, manutenção e troca de peças.
     * @return O valor total das despesas.
     */
    public double calcularDespesaTotal() {
        double despesaCombustivel = calcularDespesaCombustivel();
        double despesaManutencao = calcularDespesaManutencao();
        double despesaTrocaPecas = calcularDespesaTrocaPecas();

        return despesaCombustivel + despesaManutencao + despesaTrocaPecas;
    }

    /**
     * Calcula a despesa de combustível do veículo.
     * @return O valor total gasto em combustível.
     */
    public double calcularDespesaCombustivel() {
        return veiculo.totalGastoReabastecimento();
    }

    /**
     * Calcula a despesa de troca de peças do veículo.
     * @return O valor total gasto em troca de peças.
     */
    public double calcularDespesaTrocaPecas() {
        return veiculo.totalGastoTrocaPecas();
    }

    /**
     * Calcula a despesa de manutenção do veículo.
     * @return O valor total gasto em manutenção.
     */
    public double calcularDespesaManutencao() {
        return veiculo.totalGastoManutencao();
    }
}
