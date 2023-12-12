package codigo;

import java.util.Arrays;
import java.util.Calendar;

/**
 * Frota
 */
public class Frota {

    // #region Atribuos
    private int tamanhoFrota;
    private Veiculo veiculos[];
    private Veiculo veiculo;

    Veiculo conect = new Veiculo();
    // #endregion

    public Veiculo[] getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(Veiculo[] veiculos) {
		this.veiculos = veiculos;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	/**
     * Método para a impressão de um relatório contendo as informações solicitadas
     * pela empresa. Se o vetor estiver vazio, informa a falta de elemnetos
     * inseridos
     * 
     * @return
     */
    public String relatorioFrota() {
        StringBuilder relatorio = new StringBuilder();
        if (veiculos != null && veiculos.length > 0) {
            relatorio.append("::Relatório::\n\n");
            for (Veiculo veiculo : veiculos) {
                relatorio.append("Placa:: ").append(veiculo.getPlaca()).append("Litros reabastecidos:: ")
                        .append(veiculo.getTotalReabastecido()).append("\nQuilometragem rodada do mês atual:: ")
                        .append(veiculo.kmNoMes(Calendar.MONTH)).append("\nQuilometragem total do veículo:: ")
                        .append(veiculo.kmTotal()).append("\n\n");
            }
        } else {
            relatorio.append("Sem veículos adicionados até o momento");
        }
        return relatorio.toString();
    }

    /**
     * Método para conferir e localizar a existência de um determinado veículo por
     * meio da placa do mesmo.
     * 
     * @param placa
     * @return mensagem indicando existência da veículo.
     */
    public Veiculo localizarVeiculo(String placa) {
        return Arrays.stream(veiculos).filter(veiculo -> veiculo.getPlaca().equals(placa)).findFirst().orElse(null);
    }

    /**
     * Método para @return a quilometragem total percorrida pelo veículo
     */
    public double quilometragemTotal() {

        return Arrays.stream(veiculos).mapToDouble(Veiculo::kmTotal).sum();
    }

    /**
     * Confere e @return qual a maior quilometragem percorrida
     */
    public double maiorKmTotal() {

         return Arrays.stream(veiculos).mapToDouble(Veiculo::kmTotal).max()
                .orElse(0);
    }

    /**
     * 
     * @return Maior quilometragem média
     */
    public double maiorKmMedia() {

        return Arrays.stream(veiculos).mapToDouble(veiculo -> veiculo.kmTotal() / veiculo.getQuantRotas()).max()
                .orElse(0);
    }
}