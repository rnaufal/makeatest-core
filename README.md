Make A Test
===========

O Make A Test é uma ferramenta com o objetivo de ajudar o desenvolvedor a verificar efeitos externos causados por um testes de unidade.

Considere que efeito externo é o resultado de uma execução de código, sendo que esse resultado está fora do domínio do sistema desenvolvido. Assim para validar se uma determinada informação foi inserida corretamente dentro de um SGBD é necessário utilizar recursos que estão fora do sistema desenvolvido, um bom exemplo e o DBUnit www.dbunit.org.

O Make A Test permite o desenvolvedor criar essas verificações de efeitos externos e adicionar nos testes de unidade atravéz de anotações, dessa forma garante que o teste de unidade continue simples e de fácil entendimento.

Pacotes
-------

O Make A Test está dividido em pacotes:

- [makeatest-core](http://github.com:marcusfloriano/makeatest-core.git) -- `git@github.com:marcusfloriano/makeatest-core.git`

Contêm toda a lógica necessária para a execução do framework

- [makeatest-junit](http://github.com:marcusfloriano/makeatest-junit.git) -- `git@github.com:marcusfloriano/makeatest-junit.git`

Integração com o JUnit 4 utilizando a funcionalidade Runners, é adicionado no teste de unidade o Runner abaixo:

    @RunWith(MakeATestRunner.class)

- [makeatest-dbunit](http://github.com:marcusfloriano/makeatest-dbunit.git) -- `git@github.com:marcusfloriano/makeatest-dbunit.git`

Contêm as anotações para carregar e validar o estado de um SGBD, é um adaptador do DBUnit para o MakeATest.

Como desenvolver a verificação utilizando o Make A Test
-------------------------------------------------------

### Anotação

Primeiro é necessário a criação da anotação.






