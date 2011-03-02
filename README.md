Make A Test
===========

O Make A Test é uma ferramenta cujo o objetivo é prover ao desenvolvedor a verificação de efeitos externos causados em testes de unidade.

O desenvolvimento de testes de unidade de forma simples e óbvia passa a se tornar complexo quando há a necessidade de validar um efeito externo. Os casos como verificar o estado dos dados de um SGBD, validar a existência de um recurso ou mesmo realizar chamadas de sistema como por exemplo ter que iniciar um determinado serviço torna a criação do teste complexa.

Considere que efeito externo é o resultado de uma execução de código, sendo que esse resultado está fora do domínio do sistema desenvolvido. Dessa forma, para validar por exemplo se uma determinada informação foi inserida corretamente dentro de um SGBD é necessário utilizar recursos que estão fora do sistema desenvolvido. O [DBUnit](http://www.dbunit.org) permite que verificações no SGBD sejam feitas de forma independente.

O Make A Test permite ao desenvolvedor criar essas verificações de efeitos externos e adicioná-las em testes de unidade através de anotações, deixando-os simples e de fácil entendimento.

Pacotes
-------

O Make A Test está dividido em pacotes:

- [makeatest-core](http://github.com:marcusfloriano/makeatest-core.git) -- `git@github.com:marcusfloriano/makeatest-core.git`

Contem toda a lógica necessária para a execução do framework

- [makeatest-junit](http://github.com:marcusfloriano/makeatest-junit.git) -- `git@github.com:marcusfloriano/makeatest-junit.git`

Integração com o JUnit 4 utilizando a funcionalidade Runners, é adicionado no teste de unidade o Runner abaixo:

    @RunWith(MakeATestRunner.class)

- [makeatest-dbunit](http://github.com:marcusfloriano/makeatest-dbunit.git) -- `git@github.com:marcusfloriano/makeatest-dbunit.git`

Contem as anotações para carregar e validar o estado de um SGBD, é um adaptador do DBUnit para o MakeATest.

Como desenvolver a verificação utilizando o Make A Test
-------------------------------------------------------

### Anotação

Primeiro é necessário a criação da anotação.






