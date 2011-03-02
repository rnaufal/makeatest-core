Make A Test
===========

O Make A Test é uma ferramenta cujo o objetivo é prover ao desenvolvedor a verificação de efeitos externos causados em testes de unidade.

O desenvolvimento de testes de unidade de forma simples e óbvia passa a se tornar complexo quando há a necessidade de validar um efeito externo. Os casos como verificar o estado dos dados de um SGBD, validar a existência de um recurso ou mesmo realizar chamadas de sistema como por exemplo ter que iniciar um determinado serviço torna a criação do teste complexa.

Considere que efeito externo é o resultado de uma execução de código, sendo que esse resultado está fora do domínio do sistema desenvolvido. Dessa forma, para validar por exemplo se uma determinada informação foi inserida corretamente dentro de um SGBD é necessário utilizar recursos que estão fora do sistema desenvolvido. O [DBUnit](http://www.dbunit.org) permite que verificações no SGBD sejam feitas de forma independente.

O Make A Test permite ao desenvolvedor criar essas verificações de efeitos externos e adicioná-las em testes de unidade através de anotações, deixando-os simples e de fácil entendimento.

Pacotes
-------

O Make A Test está dividido em pacotes:

- [makeatest-core](http://github.com/marcusfloriano/makeatest-core.git) -- `git@github.com:marcusfloriano/makeatest-core.git`

Contem toda a lógica necessária para a execução do framework

- [makeatest-junit](http://github.com/marcusfloriano/makeatest-junit.git) -- `git@github.com:marcusfloriano/makeatest-junit.git`

Integração com o JUnit 4 utilizando a funcionalidade Runners, é adicionado no teste de unidade o Runner abaixo:

    @RunWith(MakeATestRunner.class)

- [makeatest-dbunit](http://github.com/marcusfloriano/makeatest-dbunit.git) -- `git@github.com:marcusfloriano/makeatest-dbunit.git`

Contem as anotações para carregar e validar o estado de um SGBD, é um adaptador do DBUnit para o MakeATest.

Instalação
----------

Adicione no Classpath os pacotes jars:

- makeatest-core
 - https://github.com/marcusfloriano/makeatest-core/blob/master/dist/makeatest-core-X.X.X.jar
- makeatest-junit
 - https://github.com/marcusfloriano/makeatest-junit/blob/master/dist/makeatest-junit-X.X.X.jar

Uso
---

Exemplo 

Para exemplificar o uso da ferramenta considere a necessidade de verificar se um determinado arquivo de propriedade foi criado corretamente a partir da classe `Properies`.

Dessa forma em um determinado teste de unidade temos:

	@Test
	public void verifyFileProperty() {
		Properties properties = new Properties();
		properties.setProperty("company_name", "Make A Test");
		properties.store(new FileOutputStream("filename.properties"), null);
	}
	
Agora é necessário que seja validado se o resultado do arquivo gerado contêm a propriedade "company_name" igual a "Make a Test", e essa validação deve ser feita apenas com a adição de uma anotação como por exemplo:

	@ValidatePropertyFile(property="company_name", value="Make a Test")

Essa anotação 

### Anotação







