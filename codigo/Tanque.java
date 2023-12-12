package codigo;
 
public class Tanque {
    private double consumo;
    private double capacidadeMaxima;
    private double capacidadeAtual;
    private double reabastecidos;

    public double getReabastecidos() {
		return reabastecidos;
	}



	public void setReabastecidos(double reabastecidos) {
		this.reabastecidos = reabastecidos;
	}



	public Tanque(double consumo, double capacidadeMaxima) {
        this.consumo = consumo;
        this.capacidadeMaxima = capacidadeMaxima;
        this.capacidadeAtual = 0.0;
    }
    
    

    public double getConsumo() {
		return consumo;
	}



	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}



	public double getCapacidadeMaxima() {
		return capacidadeMaxima;
	}



	public void setCapacidadeMaxima(double capacidadeMaxima) {
		this.capacidadeMaxima = capacidadeMaxima;
	}



	public double getCapacidadeAtual() {
		return capacidadeAtual;
	}


	public void setCapacidadeAtual(double capacidadeAtual) {
		this.capacidadeAtual = capacidadeAtual;
	}



	public double abastecer(double litros) {
        double espacoDisponivel = capacidadeMaxima - capacidadeAtual;

        if (litros <= espacoDisponivel) {
            capacidadeAtual += litros;
            return litros;
        } else {
            capacidadeAtual = capacidadeMaxima;
            return espacoDisponivel;
        }
    }

    public double autonomiaMaxima() {
        return capacidadeAtual * consumo;
    }

    public double autonomiaAtual() {
        return capacidadeAtual * consumo;
    }

    public static void main(String[] args) {
        Tanque meuTanque = new Tanque(10.0, 50.0); // Exemplo: consumo de 10 km/l e capacidade máxima de 50 litros
        double litrosAbastecidos = meuTanque.abastecer(30.0); // Abastece 30 litros
        System.out.println("Litros abastecidos: " + litrosAbastecidos);
        System.out.println("Autonomia máxima: " + meuTanque.autonomiaMaxima() + " km");
        System.out.println("Autonomia atual: " + meuTanque.autonomiaAtual() + " km");
    }
}

