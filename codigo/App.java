package codigo;
import java.util.*;
class App{
    public static void Menu(int op){
      Scanner entrada = new Scanner (System.in);
      while (op != 0){
        switch (op){
          case 1:
            Frota frota1 = new Frota();
            System.out.println(frota1.relatorioFrota());
            break;
            
          case 2:
            System.out.println("Placa:");
            String placa = entrada.next();
            frota1.localizarVeiculo(placa);
            break;
            
          case 3: 
            Veiculo veiculo = new Veiculo();
            System.out.println("Distância em Km: ");
            double km = entrada.nextInt();
            System.out.println("Data (dia/mês/ano): ");
            int dia = entrada.nextInt();
            int mes = entrada.nextInt();
            int ano = entrada.nextInt();
            Date data = new Date(dia, mes, ano);
            Rota rota = new Rota(km, data);
            veiculo.addRota(rota);
            break;
            
          case 4:
            System.out.println("Quilometragem total percorrida pelo veiculo em km: " + frota.quilometragemtotal());
            break;
            
          case 5:
            System.out.println("Deseja abastecer quantos litros? ");
            double litros = entrada.nextDouble();
            Tanque tanque = new Tanque();
            tanque.abastecer(litros);
            break;
            
          case 6:
            Frota fr = new Frota();
            System.out.println("Maior km média: " + fr.maiorKmMedia() + "km");
            break;
          case 7:
            Frota frota = new Frota();
            System.out.println("Maior quilometragem total: " + frota.maiorKmTotal() + "km");
            break;
            
          default: 
            System.out.println("Opção Inválida!!");
        }
      }
    }
    public static void main(String[] args){
      Scanner entrada = new Scanner(System.in);
      
      System.out.printf("0- Sair!\n1- Relatorio da frota.\n2- Localizar veiculo.\n3- Adicionar rota.\n4- Quilometragem total.\n5- Abastecer.\n6- Maior quilometragem media.\n7- Maior quilometragem total.");
      int op = entrada.nextInt();
      
      Menu(op);
      
      entrada.close();
    }
}