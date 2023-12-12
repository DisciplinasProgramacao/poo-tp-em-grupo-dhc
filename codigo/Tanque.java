package codigo;

/**
 * A classe Tanque representa o tanque de combustível de um veículo,
 * armazenando informações sobre consumo, capacidade máxima, capacidade atual e reabastecimentos.
 */
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



	/**
     * Construtor da classe Tanque.
     * 
     * @param consumo Consumo médio de combustível em km por litro.
     * @param capacidadeMaxima Capacidade máxima do tanque em litros.
     */
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


	/**
     * Realiza o abastecimento do tanque com uma quantidade específica de litros.
     * 
     * @param litros Quantidade de litros a ser abastecida.
     * @return A quantidade real de litros abastecidos, considerando a capacidade disponível.
     */
	public double abastecer(double litros) {
        double espacoDisponivel = capacidadeMaxima - capacidadeAtual;

        if (litros <= espacoDisponivel) {
            capacidadeAtual += litros;
            reabastecidos += litros;  // Atualiza o total reabastecido
            return litros;
        } else {
            capacidadeAtual = capacidadeMaxima;
            reabastecidos += espacoDisponivel;  // Atualiza o total reabastecido
            return espacoDisponivel;
        }
    }


	/**
     * Calcula a autonomia máxima do veículo com base na capacidade atual e no consumo médio.
     * 
     * @return A autonomia máxima em quilômetros.
     */
    public double autonomiaMaxima() {
        return capacidadeAtual * consumo;
    }

    
    /**
     * Calcula a autonomia atual do veículo com base na capacidade atual e no consumo médio.
     * 
     * @return A autonomia atual em quilômetros.
     */
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

