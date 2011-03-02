Make A Test
===========

O Make A Test é uma ferramenta com o objetivo de ajudar o desenvolvedor a verificar efeitos externos causados por um testes de unidade.

Considere que efeito externo é o resultado de uma execução de código, sendo que esse resultado está fora do domínio do sistema desenvolvido. Assim para validar se uma determinada informação foi inserida corretamente dentro de um SGBD é necessário utilizar recursos que estão fora do sistema desenvolvido, um bom exemplo e o DBUnit www.dbunit.org.

O Make A Test permite o desenvolvedor criar essas verificações de efeitos externos e adicionar nos testes de unidade atravéz de anotações, dessa forma garante que o teste de unidade continue simples e de fácil entendimento.

Pacotes
-------

O Make A Test está dividido em pacotes:

- makeatest-core
git@github.com:marcusfloriano/makeatest-core.git

Contêm toda a lógica necessária para a execução do framework

- makeatest-junit
git@github.com:marcusfloriano/makeatest-junit.git

É a integração com o Runner do JUnit 4, é utilizando na classe de teste com o @RunWith(MakeATestRunner.class)

- makeatest-dbunit
git@github.com:marcusfloriano/makeatest-dbunit.git
Esse pacote contêm as anotações para carregar e validar o estado do banco, é um adaptador do DBUnit para o MakeATest






