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

## DADOS
### insira alguns scripts para criação das funções do DB para atualizar automaticamente 
a base de dados da nova tabela criada;
- para inserir os dados de Cargo(valores fixos enviados no desafio)
> INSERT INTO public.cargo
(id, nome)
VALUES(1, 'Estagiario');
INSERT INTO public.cargo
(id, nome)
VALUES(2, 'Tecnico');
INSERT INTO public.cargo
(id, nome)
VALUES(3, 'Analista');
INSERT INTO public.cargo
(id, nome)
VALUES(4, 'Coordenador');
INSERT INTO public.cargo
(id, nome)
VALUES(5, 'Gerente');

- para criar a função que calcular o valor do salario;
> CREATE OR REPLACE FUNCTION calcular_salario(cargo_id integer, nome_pessoa text)
RETURNS numeric
LANGUAGE plpgsql
AS $$
DECLARE
salarioCalculado DECIMAL;
BEGIN
CASE
WHEN cargo_id = 1 THEN
salarioCalculado := get_valor_beneficio(1);
WHEN cargo_id = 2 THEN
salarioCalculado := get_valor_beneficio(2);
salarioCalculado := salarioCalculado - get_valor_desconto(8);
salarioCalculado := salarioCalculado - get_valor_desconto(9);
WHEN cargo_id = 3 THEN
salarioCalculado := get_valor_beneficio(3);
salarioCalculado := salarioCalculado - get_valor_desconto(8);
salarioCalculado := salarioCalculado - get_valor_desconto(9);
WHEN cargo_id = 4 THEN
salarioCalculado := get_valor_beneficio(4);
salarioCalculado := salarioCalculado + get_valor_beneficio(6);
salarioCalculado := salarioCalculado - get_valor_desconto(8);
salarioCalculado := salarioCalculado - get_valor_desconto(9);
WHEN cargo_id = 5 THEN
salarioCalculado := get_valor_beneficio(5);
salarioCalculado := salarioCalculado + get_valor_beneficio(7);
salarioCalculado := salarioCalculado - get_valor_desconto(8);
salarioCalculado := salarioCalculado - get_valor_desconto(9);
ELSE
salarioCalculado := 0.0;
END CASE;
    RETURN salarioCalculado;
END;
$$;
 
- para criar a função para gerar o valor do beneficio;
> CREATE OR REPLACE FUNCTION get_valor_beneficio(salarioId integer)
RETURNS numeric
LANGUAGE plpgsql
AS $$
BEGIN
CASE
WHEN salarioId = 1 THEN
RETURN 400.0;
WHEN salarioId = 2 THEN
RETURN 1000.0;
WHEN salarioId = 3 THEN
RETURN 2500.0;
WHEN salarioId = 4 THEN
RETURN 5000.0;
WHEN salarioId = 5 THEN
RETURN 6500.0;
WHEN salarioId = 6 THEN
RETURN 500.0;
WHEN salarioId = 7 THEN
RETURN 1000.0;
ELSE
RETURN 0.0;
END CASE;
END;
$$;

- para criar a função para criar o valor do desconto;
> CREATE OR REPLACE FUNCTION get_valor_desconto(salarioId integer)
RETURNS numeric
LANGUAGE plpgsql
AS $$
BEGIN
CASE
WHEN salarioId = 8 THEN
RETURN 350.0;
WHEN salarioId = 9 THEN
RETURN 11.0;
ELSE
RETURN 0.0;
END CASE;
END;
$$;

- para criar a função para popular a tabela pessoa_salario;
> CREATE OR REPLACE FUNCTION populate_pessoa_salario()
RETURNS trigger
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO pessoa_salario (pessoa_id, nome, salario)
VALUES (NEW.id, NEW.nome, calcular_salario(NEW.cargo_id, NEW.nome));
RETURN NEW;
END;
$$;
CREATE TRIGGER tr_populate_pessoa_salario
AFTER INSERT ON pessoa
FOR EACH ROW
EXECUTE FUNCTION populate_pessoa_salario();


### Iniciando o projeto
- inicie o projeto startando a classe "DesafioEsigApplication";
- acesse o DBeaver para acessar o DB e na tabela "usuario" cadastre um login e senha para acesso ao sistema;
- acesse a [pagina](http://localhost:3535/login.com)



