# Autorizador de Transações

## Desafio

O desafio consistia em implementar um autorizador de transações. Para isso, modeleio o negócio de uma forma que ficasse
extensível e fechada para novas implementações.

Sendo assim, temos:

- **Conditional**: Responsável por representar uma condição para uma regra de autorização.
- **Rule**: Responsável por representar uma regra de autorização, que contém um conjunto de condicionais.
- **Category**: Responsável por representar uma categoria, que contém regras de autorização.
- **Account**: Responsável por representar uma conta, que contém as categorias.

Para cada desafio:

- **L1**: Autorizador simples
- **L2**: Autorizador com fallback
- **L3**: Dependente do comerciante

Criei um caso de teste na `TransactionAuthorizerUseCaseTest`.

Como solução para **L4**: Questão aberta, acredito que podemos seguir o padrão de bloqueio otimista, utilizando
versionamento ou um campo `updatedAt`.

Infelizmente, não consegui atingir todos os objetivos do desafio. Acabei focando bastante na implementação de um domínio
de fácil evolução e customização.

Como conclusão, me aventurei no caminho de entregar em Kotlin, seguindo o paradigma funcional, visto que minha stack
principal é Java, com orientação a objetos.