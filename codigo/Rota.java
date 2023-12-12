package codigo;

import java.util.Calendar;
import java.util.Date;

/**
 * A classe Rota representa uma viagem realizada por um veículo, armazenando informações
 * sobre a quilometragem percorrida e a data da rota.
 */
public class Rota {
    
    private double quilometragem;
    private Date data;

    
    /**
     * Gera um relatório simples da rota, incluindo a data e quilometragem.
     *
     * @return String contendo informações da rota.
     */
    public String Relatorio (){
    	return("Data da Rota:" +data + "quilometragem:"+quilometragem);
    }
    
    public double getQuilometragem() {
        return quilometragem;
    }
   
    public Date getData() {
    	return data;
    }
    
    public int getMes() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        // Obtém o mês (janeiro é 0, fevereiro é 1, ..., dezembro é 11)
        int mes = calendar.get(Calendar.MONTH) + 1;

        return mes;
    }
    
    
}
