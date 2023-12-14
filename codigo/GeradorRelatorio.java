//package codigo;
//
//import java.text.NumberFormat;
//import java.util.Locale;
//import java.util.Arrays;
//import java.util.Objects;
//
//public class GeradorRelatorio {
//
//    public static String gerarRelatorioVeiculo(Veiculo veiculo) {
//        StringBuilder relatorio = new StringBuilder();
//
//        relatorio.append(":: Relatório do Veículo ::\n\n");
//        relatorio.append("Placa: ").append(veiculo.getPlaca()).append("\n");
//        relatorio.append("Tipo de Veículo: ").append(veiculo.getTipoVeiculo()).append("\n");
//        relatorio.append("Capacidade do Tanque: ").append(veiculo.getTipoVeiculo().getCapacidadeTanque()).append(" litros\n");
//        relatorio.append("Autonomia Diária: ").append(veiculo.getTipoVeiculo().getAutonomiaDiaria()).append(" km\n");
//        relatorio.append("Autonomia Atual: ").append(veiculo.getTanque().autonomiaAtual()).append(" km\n");
//        relatorio.append("Total Reabastecido: ").append(veiculo.getTotalReabastecido()).append(" litros\n");
//        relatorio.append("Quantidade de Rotas Registradas: ").append(veiculo.getQuantRotas()).append("\n\n");
//
//        if (veiculo.getQuantRotas() > 0) {
//            relatorio.append(":: Rotas ::\n");
//            for (Rota rota : veiculo.getTodasRotas()) {
//                if (rota != null) {
//                    relatorio.append("Data da Rota: ").append(rota.getData().dataFormatada())
//                            .append(", Quilometragem: ").append(rota.getQuilometragem()).append("\n");
//                }
//            }
//        } else {
//            relatorio.append("Sem rotas registradas\n\n");
//        }
//
//        relatorio.append(":: Despesa Total Estimada ::\n");
//        
////        // Adiciona verificação para evitar NPE ao acessar rota que pode ser null
////        double despesaCombustivel = veiculo.getRotas() != null
////                ? Arrays.stream(veiculo.getRotas())
////                    .filter(Objects::nonNull)
////                    .mapToDouble(rota -> veiculo.calcularLitrosNecessariosReabastecimento(rota))
////                    .sum()
////                : 0.0;
//
//        
//        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
//        
//        double despesaManutencao = 0.0; // Adicione lógica para calcular a despesa de manutenção
//        double despesaTotal = veiculo.totalGasto() + despesaManutencao;
//        relatorio.append("Despesa de Combustível: ").append(formatoMoeda.format(veiculo.totalGasto())).append("\n");
//        relatorio.append("Despesa de Manutenção: ").append(formatoMoeda.format(despesaManutencao)).append("\n");
//        relatorio.append("Despesa Total: ").append(formatoMoeda.format(despesaTotal)).append("\n");
//        return relatorio.toString();
//    }
//    
//}