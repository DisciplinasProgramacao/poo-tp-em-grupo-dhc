package codigo;

/**
 * A classe Rota representa uma viagem realizada por um veículo, armazenando informações
 * sobre a quilometragem percorrida e a data da rota.
 */
public class Rota {

    private double quilometragem;
    private Data data;

    /**
     * Construtor da classe Rota.
     * @param km A quilometragem percorrida na rota.
     * @param data A data da rota.
     */
    public Rota(double km, Data data) {
        this.quilometragem = km;
        this.data = data;
    }

    /**
     * Gera um relatório simples da rota, incluindo a data e quilometragem.
     * @param placa A placa do veículo associado à rota.
     * @return String contendo informações da rota.
     */
    public String relatorioRota(String placa) {
        StringBuilder aux = new StringBuilder();
        aux.append("A rota do dia ").append(data.dataFormatada()).append(" pelo veículo de placa: ").append(placa)
                .append(" possui ").append(String.format("%.2f", quilometragem)).append(" km").append("\n");
        return aux.toString();
    }

    /**
     * Obtém a quilometragem da rota.
     * @return A quilometragem da rota.
     */
    public double getQuilometragem() {
        return quilometragem;
    }

    /**
     * Obtém a data da rota.
     * @return O objeto Data representando a data da rota.
     */
    public Data getData() {
        return data;
    }

    /**
     * Obtém o mês da rota.
     * @return O mês da rota.
     */
    public int getMes() {
        return data.getMes();
    }
}
