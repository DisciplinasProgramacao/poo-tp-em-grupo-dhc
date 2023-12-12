package codigo;

import java.util.Calendar;

public class Manutencao {

    private final Veiculo veiculo;

    public Manutencao(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void realizarManutencao() {
        if (this.veiculo.kmNoMes(Calendar.getInstance().get(Calendar.MONTH) + 1) >= veiculo.getTipoVeiculo().getKmManutencao()) {
            System.out.println("Manutenção periódica necessária!");
            // Adicione lógica para realizar a manutenção necessária
            // ...
        }
        if (this.veiculo.kmTotal() >= veiculo.getTipoVeiculo().getKmTrocaPecas()) {
            System.out.println("Manutenção de troca de peças necessária!");
            // Adicione lógica para realizar a troca de peças necessária
            // ...
        }
    }
}
