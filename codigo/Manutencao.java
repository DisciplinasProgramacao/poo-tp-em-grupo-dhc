package codigo;

/**
 * A classe Manutencao é responsável por gerenciar a manutenção e troca de peças de um veículo.
 */
public class Manutencao {

    private final Veiculo veiculo;

    /**
     * Construtor da classe Manutencao.
     * @param veiculo O veículo associado à manutenção.
     */
    public Manutencao(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * Verifica se é necessário realizar manutenção no veículo com base nos quilômetros percorridos.
     * @return true se a manutenção for necessária, false caso contrário.
     */
    public boolean verificarManutencao() {
        ETipoVeiculo tipoVeiculo = veiculo.getTipoVeiculo();

        if (this.veiculo.kmNoMes(veiculo.getMesAtual()) >= EKmManutencao.getKm(tipoVeiculo) * (veiculo.getManutencoesFeitas() + 1)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se é necessário realizar a troca de peças no veículo com base nos quilômetros totais percorridos.
     * @return true se a troca de peças for necessária, false caso contrário.
     */
    public boolean verificarTrocaPecas() {
        ETipoVeiculo tipoVeiculo = veiculo.getTipoVeiculo();

        if (this.veiculo.kmTotal() >= EKmTrocaPecas.getKm(tipoVeiculo) * (veiculo.getTrocaPecasFeitas() + 1)) {
            return true;
        }
        return false;
    }

    /**
     * Obtém o preço da troca de peças para um determinado tipo de veículo.
     * @param tipoVeiculo O tipo de veículo.
     * @return O preço da troca de peças.
     */
    public double precoTrocaPecas(ETipoVeiculo tipoVeiculo) {
        return EKmTrocaPecas.getPreco(tipoVeiculo);
    }

    /**
     * Obtém o preço da manutenção para um determinado tipo de veículo.
     * @param tipoVeiculo O tipo de veículo.
     * @return O preço da manutenção.
     */
    public double precoManutencao(ETipoVeiculo tipoVeiculo) {
        return EKmManutencao.getPreco(tipoVeiculo);
    }

    /**
     * Enumeração que define os quilômetros de manutenção e os preços associados para cada tipo de veículo.
     */
    private enum EKmManutencao {
        CARRO(100.0, 100.0),
        VAN(10000.0, 100.0),
        FURGAO(10000.0, 100.0),
        CAMINHAO(20000.0, 100.0);

        private final double kmManutencao;
        private final double precoManutencao;

        EKmManutencao(double kmManutencao, double precoManutencao) {
            this.kmManutencao = kmManutencao;
            this.precoManutencao = precoManutencao;
        }

        /**
         * Obtém os quilômetros de manutenção para um determinado tipo de veículo.
         * @param tipoVeiculo O tipo de veículo.
         * @return Os quilômetros de manutenção.
         */
        public static double getKm(ETipoVeiculo tipoVeiculo) {
            switch (tipoVeiculo) {
                case CARRO:
                    return CARRO.kmManutencao;
                case VAN:
                    return VAN.kmManutencao;
                case FURGAO:
                    return FURGAO.kmManutencao;
                case CAMINHAO:
                    return CAMINHAO.kmManutencao;
                default:
                    throw new IllegalArgumentException("Tipo de veículo desconhecido: " + tipoVeiculo);
            }
        }

        /**
         * Obtém o preço da manutenção para um determinado tipo de veículo.
         * @param tipoVeiculo O tipo de veículo.
         * @return O preço da manutenção.
         */
        public static double getPreco(ETipoVeiculo tipoVeiculo) {
            switch (tipoVeiculo) {
                case CARRO:
                    return CARRO.precoManutencao;
                case VAN:
                    return VAN.precoManutencao;
                case FURGAO:
                    return FURGAO.precoManutencao;
                case CAMINHAO:
                    return CAMINHAO.precoManutencao;
                default:
                    throw new IllegalArgumentException("Tipo de veículo desconhecido: " + tipoVeiculo);
            }
        }
    }

    /**
     * Enumeração que define os quilômetros de troca de peças e os preços associados para cada tipo de veículo.
     */
    private enum EKmTrocaPecas {
        CARRO(100.0, 500.0),
        VAN(12000.0, 700.0),
        FURGAO(12000.0, 500.0),
        CAMINHAO(20000.0, 2000.0);

        private final double kmTrocaPecas;
        private final double precoTrocaPecas;

        EKmTrocaPecas(double kmTrocaPecas, double precoTrocaPecas) {
            this.kmTrocaPecas = kmTrocaPecas;
            this.precoTrocaPecas = precoTrocaPecas;
        }

        /**
         * Obtém os quilômetros de troca de peças para um determinado tipo de veículo.
         * @param tipoVeiculo O tipo de veículo.
         * @return Os quilômetros de troca de peças.
         */
        public static double getKm(ETipoVeiculo tipoVeiculo) {
            switch (tipoVeiculo) {
                case CARRO:
                    return CARRO.kmTrocaPecas;
                case VAN:
                    return VAN.kmTrocaPecas;
                case FURGAO:
                    return FURGAO.kmTrocaPecas;
                case CAMINHAO:
                    return CAMINHAO.kmTrocaPecas;
                default:
                    throw new IllegalArgumentException("Tipo de veículo desconhecido: " + tipoVeiculo);
            }
        }

        /**
         * Obtém o preço da troca de peças para um determinado tipo de veículo.
         * @param tipoVeiculo O tipo de veículo.
         * @return O preço da troca de peças.
         */
        public static double getPreco(ETipoVeiculo tipoVeiculo) {
            switch (tipoVeiculo) {
                case CARRO:
                    return CARRO.precoTrocaPecas;
                case VAN:
                    return VAN.precoTrocaPecas;
                case FURGAO:
                    return FURGAO.precoTrocaPecas;
                case CAMINHAO:
                    return CAMINHAO.precoTrocaPecas;
                default:
                    throw new IllegalArgumentException("Tipo de veículo desconhecido: " + tipoVeiculo);
            }
        }
    }
}
