CREATE TABLE cliente
(
    id SERIAL,
    cpf character varying(255) NOT NULL,
    data_nascimento date NOT NULL,
    nome character varying(255) NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (id)
)
