package codigo;

import java.util.Calendar;
import java.util.Date;

/**
 * A classe Rota representa uma viagem realizada por um veículo, armazenando informações
 * sobre a quilometragem percorrida e a data da rota.
 */
public class Rota {
    
    private double quilometragem;
    private Data data;

    
    public Rota(double km, Data data) {
		this.quilometragem = km;
		this.data = data;
	}
    
    /**
     * Gera um relatório simples da rota, incluindo a data e quilometragem.
     *
     * @return String contendo informações da rota.
     */
    public String relatorio(String placa) {
		StringBuilder aux = new StringBuilder();
		aux.append("A rota do dia ").append(data.dataFormatada()).append(" pelo veículo de placa: ").append(placa)
		.append(" possui ").append(quilometragem).append(" km"+ "\n");
		return aux.toString();
	}
    
    public double getQuilometragem() {
        return quilometragem;
    }
   
    public Data getData() {
    	return data;
    }
    
    
}
