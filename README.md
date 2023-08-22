# DesafioESIG

Este projeto é um desafio proposto pela empresa ESIG GROUP

### Linguagem
- Java 11

### Tecnologias Usadas
- SpringBoot;
- Hibernate;
- JSF
- Primefaces
- Lombok;
- Javax;
- Maven

### Banco de Dados
- PostgreSQL


### Ferramentas de Desenvolvimento
- [IntelliJ (Windows)](https://jetbrains.com/idea/download/?section=windows)
- [Docker (Windows)](https://www.docker.com/products/docker-desktop)
- [DBeaver (Windows)](https://dbeaver.io/download)


### Variaveis necessarias para iniciar o projeto

| NAME                   | VALUE    |
|------------------------|----------|
| POSTGRESQL_DB_PASSWORD | postgres |
| POSTGRESQL_DB_USERNAME | postgres |


### Comando para iniciar o container do Docker(PostgreSQL)
#### entre na partição do docker
- cd docker;
#### execute o comando para criar e executar o container
- docker-compose up -d;


### Iniciando o projeto
#### acesse o terminal(pela IDE) e execute o comando
- mvn clean install


Após feito isso, inicie o projeto startando a classe "DesafioEsigApplication"



