package codigo;

/**
 * Frota
 */
public class Frota {

    //#region Atribuos
    private int tamanhoFrota;
    private Veiculo veiculos[];

    Veiculo conect = new Veiculo();
    //#endregion


/**
 * Método para a impressão de um relatório contendo as informações solicitadas pela empresa. Se o vetor estiver vazio, informa a falta de elemnetos inseridos
 * @return
 */
    public String relatorioFrota(){
        if (veiculos[] != null){ //CONFERIR SE O VETOR ESTÁ VAZIO
            System.out.println("::Relatório::\n\n");
        foreach (String placa in veiculos[]){ //A CADA PLACA EXISTENTE NO VETOR, GERAR UM RELATÓRIO ESPECÍFICO
            System.out.println("Litros reabastecidos:: " + conect.totalReabastecido + "\nQuilometragem rodada do mês atual:: " + conect.kmMes()+
            "\nQuilometragem total do veículo:: " + conect.kmTotal());
        }
    }
        else
        System.out.println("Sem veículos adicionados até o momento");
    }


    /**
     * Método para conferir e localizar a existência de um determinado veículo por meio da placa do mesmo.
     * @param placa
     * @return mensagem indicando existência da veículo.
     */
    public Veiculo localizarVeiculo (String placa){
        for (int i = 0; i < veiculos.Lenght; i++){
            if (veiculos[i] == placa){
                System.out.println("Veículo encontrado!");
            }
            else
            System.out.println("Veículo não encontrado!");
        }
    }

/**
 * Método para @return a quilometragem total percorrida pelo veículo
 */
    public double quilometragemTotal(){
        double kmtotal = 0;

        for (int i = 0; i < veiculos.Lenght; i++){
            kmtotal += conect.kmtotal();
        }

        return kmtotal;
    }


    /**
     * Confere e @return qual a maior quilometragem percorrida
     */
    public Veiculo maiorKmTotal(){
        int maior = conect.rotas[0];
        int x = conect.rotas[];
        for (int i = 0; i < x.Lenght; i++){
            if (x[i] > maior){
                maior = x[i];
            }
        }
        return maior;
    }

    
    /**
     * 
     * @return 
     */
    public Veiculo maiorKmMedia(){
        double maiormedia = 0;

        for (int i = 0; i < veiculos.Lenght; i++){

            double media = conect.kmTotal()/conect.getQuantasRotas();

            if (media > maiormedia){
                maiormedia = media;
            }
        }
        return maiormedia;
    }
}