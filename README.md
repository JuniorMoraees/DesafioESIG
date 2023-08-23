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

- para criar a função que calcular o valor do salario
> CREATE OR REPLACE FUNCTION calcular_salario(cargo_id integer, nome_pessoa text) RETURNS DECIMAL AS $$
DECLARE
    salarioBase DECIMAL;
    salarioCalculado DECIMAL;
BEGIN
    salarioBase := cargo_id;
    CASE
        WHEN cargo_id = 1 THEN
            salarioCalculado := get_valor_beneficio(1);
        WHEN cargo_id = 2 THEN
            salarioCalculado := get_valor_beneficio(2) - get_valor_desconto(8) - get_valor_desconto(9);
        WHEN cargo_id = 3 THEN
            salarioCalculado := get_valor_beneficio(3) - get_valor_desconto(8) - get_valor_desconto(9);
        WHEN cargo_id = 4 THEN
            salarioCalculado := get_valor_beneficio(4) + get_valor_beneficio(6) - get_valor_desconto(8) - get_valor_desconto(9);
        WHEN cargo_id = 5 THEN
            salarioCalculado := get_valor_beneficio(5) + get_valor_beneficio(7) - get_valor_desconto(8) - get_valor_desconto(9);
        ELSE
            salarioCalculado := salarioBase; 
    END CASE;
    RETURN salarioCalculado;
END;
$$ LANGUAGE plpgsql;
CREATE OR REPLACE FUNCTION get_valor_beneficio(salarioId integer) RETURNS DECIMAL AS $$
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
$$ LANGUAGE plpgsql;
CREATE OR REPLACE FUNCTION get_valor_desconto(salarioId integer) RETURNS DECIMAL AS $$
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
$$ LANGUAGE plpgsql;

- para criar a função para popular a tabela nova
> CREATE OR REPLACE FUNCTION tr_populate_pessoa_salario() RETURNS TRIGGER AS $$
DECLARE
    salario DECIMAL;
BEGIN
    IF NEW.cargo_id IS NOT NULL THEN
        salario := calcular_salario(NEW.cargo_id, NEW.nome);
        INSERT INTO pessoa_salario (pessoa_id, nome, salario)
        VALUES (NEW.id, NEW.nome, salario);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER pessoa_insert_trigger
AFTER INSERT ON pessoa
FOR EACH ROW
EXECUTE FUNCTION tr_populate_pessoa_salario();


### Iniciando o projeto
- inicie o projeto startando a classe "DesafioEsigApplication";
- acesse o DBeaver para acessar o DB e na tabela "usuario" cadastre um login e senha para acesso ao sistema;
- acesse a [pagina](http://localhost:3535/login.com)



