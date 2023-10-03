package Projeto.sem Título;

import java.io.*;
import java.util.*;

public class Veiculo {

	private static int MAX_ROTAS;
	private final double CONSUMO;
	private String placa;
	private Rota [] rotas;
	private int quantRotas;
	private Tanque tanque;
	private double totalReabastecido;
	
	static{
        MAX_ROTAS = 30;
        CONSUMO = 8.2;
    }

	public boolean addRota(Rota rota) {
		
	}

	private double autonomiaMaxima() {
		return (tanqueMax*CONSUMO);
	}

	private double autonomiaAtual() {
		return (tanqueAtual*CONSUMO);
	}

	public double abastecer(double litros) {
		if (litros<0)
		{
			System.out.println("Digite um valor válido");
		return 0;
		}
		if (tanqueAtual+litros>tanqueMax)
		{
			tanqueAtual=tanqueMax;
			return (tanqueAtual+litros-tanqueMax);
		}
		else (tanqueAtual+=litros)
	}

	public double kmNoMes() {
	
	}

	public double kmTotal() {
	
	}

	private void percorrerRota(Rota rota) {
	
	}

}
