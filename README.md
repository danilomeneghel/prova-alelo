
# Prova Alelo

Avaliação técnica de uma API de Cadastro de Produto, desenvolvido em Java com Spring-Boot.

## Características

- CRUD
- API RESTful
- JUnit
- Mockito

## Requisitos

- Java JDK 11
- Apache Maven >= 3.6.3
- Docker (Opcional)

## Tecnologias

- Java
- JPA
- Maven
- Spring
- Swagger
- H2
- Docker

## Instalação

```
$ git clone https://github.com/danilomeneghel/prova-alelo.git

$ cd prova-alelo/
```

Para carregar o projeto, digite no terminal:

```
$ mvn spring-boot:run
```

Aguarde carregar todo o serviço web. <br>
Após concluído, digite o endereço abaixo em seu navegador, nele será listado os produtos 
cadastrados na API. <br>

http://localhost:8080/produtos


## Docker

```
$ mvn package
$ docker-compose up
```

## Swagger 

Documentação da API RESTful: <br>

http://localhost:8080/swagger-ui.html

## Licença

Projeto licenciado sob <a href="LICENSE">The MIT License (MIT)</a>.<br>

## Testes

Para realizar os testes, execute o seguinte comando no terminal:

```
$ mvn test
```


Desenvolvido por<br>
Danilo Meneghel<br>
danilo.meneghel@gmail.com<br>
http://danilomeneghel.github.io/<br>
