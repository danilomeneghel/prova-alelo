# Prova Alelo

Avaliação técnica de uma API de Cadastro de Produto, desenvolvido em Java com Spring-Boot.

## Características

- CRUD
- API RESTful
- Validation
- Enum
- MockMVC

## Requisitos

- Java JDK 11
- Apache Maven >= 3.6.3 (Opcional)
- Docker (Opcional)
- Jenkins (Opcional)

## Tecnologias

- Java
- JPA
- Maven
- Spring
- Lombok
- Swagger
- H2
- JUnit
- SonarQube
- Docker

## Instalação

```
$ git clone https://github.com/danilomeneghel/prova-alelo.git

$ cd prova-alelo
```

## Maven

Para carregar o projeto, digite no terminal:

```
$ ./mvnw spring-boot:run
```

Aguarde carregar todo o serviço web. <br>
Após concluído, digite o endereço abaixo em seu navegador, nele será listado os produtos 
cadastrados na API. <br>

http://localhost:8181/products

## Docker

Para rodar o projeto via Docker, bastar executar o seguinte comando:

```
$ docker build -t projeto .
$ docker run -p 8181:8181 -d projeto
```

Ou via Docker-Compose:

```
$ docker-compose up
```

Aguarde baixar as dependências e carregar todo o projeto, esse processo é demorado. <br>
Caso conclua e não rode pela primeira vez, tente novamente executando o mesmo comando. <br>

Para encerrar tudo digite:

```
$ docker-compose down
```

## Swagger 

Documentação da API RESTful: <br>

http://localhost:8181/swagger-ui.html

## SonarQube

Para verificar a cobertura de testes, primeiro acesse o seguinte endereço: <br>
http://localhost:9000

Depois efetue o login preenchendo "admin" no usuário e senha (login padrão). <br>
Ao se logar crie um novo projeto e gere o token.

Feito isso, entre no container criado da api:

```
$ docker exec -it api-alelo bash
```

Execute o seguinte comando do sonar:

```
$ mvn clean verify sonar:sonar -Dsonar.projectKey=NOME_PROJETO_GERADO -Dsonar.host.url=http://sonarqube-latest:9000 -Dsonar.login=TOKEN_GERADO
```

Após executado, acesse o seguinte endereço: <br>

http://localhost:9000/dashboard?id=prova-alelo

## Jenkins

Instale o Jenkins. 
Acesse e crie um novo Job do tipo Pipeline, escolha "Pipeline script from SCM", em seguida Git e adicione a url do projeto Git com o arquivo Jenkinsfile marcado.
Depois clique em Construir (build) para carregar a aplicação.

## Testes

Para realizar os testes, execute o seguinte comando no terminal:

```
$ ./mvnw test
```

## Licença

Projeto licenciado sob <a href="LICENSE">The MIT License (MIT)</a>.<br><br>


Desenvolvido por<br>
Danilo Meneghel<br>
danilo.meneghel@gmail.com<br>
http://danilomeneghel.github.io/<br>
