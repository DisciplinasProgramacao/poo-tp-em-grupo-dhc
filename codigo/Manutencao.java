package codigo;

import java.util.Calendar;

public class Manutencao {

    private final Veiculo veiculo;

    public Manutencao(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void realizarManutencao() {
        ETipoVeiculo tipoVeiculo = veiculo.getTipoVeiculo();

        if (this.veiculo.kmNoMes(Calendar.getInstance().get(Calendar.MONTH) + 1) >= EKmManutencao.getKm(tipoVeiculo)) {
            System.out.println("Manutenção periódica necessária!");
            // Adicione lógica para realizar a manutenção necessária
            // ...
        }
        if (this.veiculo.kmTotal() >= EKmTrocaPecas.getKm(tipoVeiculo)) {
            System.out.println("Manutenção de troca de peças necessária!");
            // Adicione lógica para realizar a troca de peças necessária
            // ...
        }
    }

    private enum EKmManutencao {
        CARRO(10000.0),
        VAN(10000.0),
        FURGAO(10000.0),
        CAMINHAO(20000.0);

        private final double kmManutencao;

        EKmManutencao(double kmManutencao) {
            this.kmManutencao = kmManutencao;
        }

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
    }

    private enum EKmTrocaPecas {
        CARRO(10000.0),
        VAN(12000.0),
        FURGAO(12000.0),
        CAMINHAO(20000.0);

        private final double kmTrocaPecas;

        EKmTrocaPecas(double kmTrocaPecas) {
            this.kmTrocaPecas = kmTrocaPecas;
        }

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
    }
}
