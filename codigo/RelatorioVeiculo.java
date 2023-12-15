package codigo;

import java.text.NumberFormat;
import java.util.Locale;

public class RelatorioVeiculo {

    public static String gerarRelatorioVeiculo(Veiculo veiculo) {
        StringBuilder relatorio = new StringBuilder();

        relatorio.append(":: Relatório do Veículo ::\n\n");
        relatorio.append("Placa: ").append(veiculo.getPlaca()).append("\n");
        relatorio.append("Tipo de Veículo: ").append(veiculo.getTipoVeiculo()).append("\n");
        relatorio.append("Capacidade do Tanque: ").append(veiculo.getTipoVeiculo().getCapacidadeTanque()).append(" litros\n");
        relatorio.append("Autonomia Diária: ").append(String.format("%.2f", veiculo.getTipoVeiculo().getAutonomiaDiaria())).append(" km\n");
        relatorio.append("Autonomia Atual: ").append(String.format("%.2f", veiculo.getTanque().autonomiaAtual())).append(" km\n");
        relatorio.append("Total Reabastecido: ").append(String.format("%.2f", veiculo.getTotalReabastecido())).append(" litros\n");
        relatorio.append("Quantidade de Rotas Registradas: ").append(veiculo.getQuantRotas()).append("\n");
        relatorio.append("Quantidade de manutenções feitas: ").append(veiculo.getManutencoesFeitas()).append("\n");
        relatorio.append("Quantidade de troca de peças feitas: ").append(veiculo.getTrocaPecasFeitas()).append("\n\n");

        if (veiculo.getQuantRotas() > 0) {
            relatorio.append(":: Rotas ::\n");
            for (Rota rota : veiculo.getTodasRotas()) {
                if (rota != null) {
                    relatorio.append("Data da Rota: ").append(rota.getData().dataFormatada())
                    .append(", Quilometragem: ").append(String.format("%.2f", rota.getQuilometragem())).append(" km\n");
                }
            }
        } else {
            relatorio.append("Sem rotas registradas\n\n");
        }
        
        relatorio.append(":: Despesa Total Estimada ::\n");
        
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        
        relatorio.append("Despesa de Combustível: ").append(formatoMoeda.format(veiculo.totalGastoReabastecimento())).append("\n");
        relatorio.append("Despesa de Manutenção: ").append(formatoMoeda.format(veiculo.getDespesas().calcularDespesaManutencao())).append("\n");
        relatorio.append("Despesa de Troca de peças: ").append(formatoMoeda.format(veiculo.getDespesas().calcularDespesaTrocaPecas())).append("\n");
        relatorio.append("Despesa Total: ").append(formatoMoeda.format(veiculo.getDespesas().calcularDespesaTotal())).append("\n");
        return relatorio.toString();
    }
    
}