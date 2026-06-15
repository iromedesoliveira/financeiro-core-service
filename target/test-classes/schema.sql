-- Ordem invertida para respeitar a integridade referencial (FK)
DROP TABLE IF EXISTS tb_transacao;
DROP TABLE IF EXISTS tb_usuario;

CREATE TABLE tb_usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    perfil_investidor VARCHAR(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_email UNIQUE (email)
);

CREATE TABLE tb_transacao (
    id BIGINT NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(100) NOT NULL,
    valor DECIMAL(19, 2) NOT NULL,
    data_vencimento DATE,
    usuario_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id)
);