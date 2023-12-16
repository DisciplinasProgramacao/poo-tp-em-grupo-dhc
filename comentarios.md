# Correcao

## Nota 10

## Comentários

  - sem diagrama em figura, conforme pedido.
  - não localizei teste. o App é um arquivo de meses atrás.
  - veículo com vetor em lugar de coleção
  - quebra de encapsulamento:
``` 
this.abastecerVeiculo(tanque.autonomiaMaxima() - tanque.autonomiaAtual());

return (tanque.getCapacidadeMaxima()*CONSUMO);

```

  isso deve ser resolvido pelo tanque (no máximo você passa o valor desejado em litros e cria o tanque com o combustível)

  - tanque não pode ter "setCapacidade", principalmente desprotegido. As operações são 'abastecer' ou 'consumir'


