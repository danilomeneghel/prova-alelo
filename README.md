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

$ cd prova-alelo/
```

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
$ docker-compose up
```

## Swagger 

Documentação da API RESTful: <br>

http://localhost:8181/swagger-ui.html

## SonarQube

http://localhost:7000

## Licença

Projeto licenciado sob <a href="LICENSE">The MIT License (MIT)</a>.<br>

## Testes

Para realizar os testes, execute o seguinte comando no terminal:

```
$ ./mvnw test
```


Desenvolvido por<br>
Danilo Meneghel<br>
danilo.meneghel@gmail.com<br>
http://danilomeneghel.github.io/<br>
