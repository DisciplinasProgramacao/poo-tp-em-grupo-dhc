package codigo;

import java.util.Calendar;
import java.util.Date;

public class Rota {
    
    private double quilometragem;
    private Date data;

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
