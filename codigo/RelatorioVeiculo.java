package codigo;

import java.util.Arrays;
import java.util.Objects;

public class RelatorioVeiculo {

    public String gerarRelatorioCompleto(Veiculo veiculo) {
    	StringBuilder relatorio = new StringBuilder();

        relatorio.append(":: Relatório do Veículo ::\n\n");
        relatorio.append("Placa: ").append(veiculo.getPlaca()).append("\n");
        relatorio.append("Tipo de Veículo: ").append(veiculo.getTipoVeiculo()).append("\n");
        relatorio.append("Capacidade do Tanque: ").append(veiculo.getTipoVeiculo().getCapacidadeTanque()).append(" litros\n");
        relatorio.append("Autonomia Diária: ").append(veiculo.getTipoVeiculo().getAutonomiaDiaria()).append(" km\n");
        relatorio.append("Autonomia Atual: ").append(veiculo.getTanque().autonomiaAtual()).append(" km\n");
        relatorio.append("Total Reabastecido: ").append(veiculo.getTotalReabastecido()).append(" litros\n");
        relatorio.append("Quantidade de Rotas Registradas: ").append(veiculo.getQuantRotas()).append("\n\n");

        if (veiculo.getQuantRotas() > 0) {
            relatorio.append(":: Rotas ::\n");
            relatorio.append(Arrays.stream(veiculo.getRotas())
                    .filter(Objects::nonNull) // Filtra rotas não nulas
                    .map(rota -> "Data da Rota: " + rota.getData() + ", Quilometragem: " + rota.getQuilometragem())
                    .reduce((s1, s2) -> s1 + "\n" + s2)
                    .orElse("Sem rotas registradas"))
                    .append("\n");
        } else {
            relatorio.append("Sem rotas registradas\n\n");
        }

        relatorio.append(":: Despesa Total Estimada ::\n");
        
        // Adiciona verificação para evitar NPE ao acessar rota que pode ser null
        double despesaCombustivel = veiculo.getRotas() != null
                ? Arrays.stream(veiculo.getRotas())
                    .filter(Objects::nonNull)
                    .mapToDouble(rota -> veiculo.calcularLitrosNecessariosReabastecimento(rota))
                    .sum()
                : 0.0;

        double despesaManutencao = 0.0; // Adicione lógica para calcular a despesa de manutenção
        double despesaTotal = despesaCombustivel + despesaManutencao;
        relatorio.append("Despesa de Combustível: R$ ").append(despesaCombustivel).append("\n");
        relatorio.append("Despesa de Manutenção: R$ ").append(despesaManutencao).append("\n");
        relatorio.append("Despesa Total: R$ ").append(despesaTotal).append("\n");

        return relatorio.toString();
    }
}
