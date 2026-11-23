-- ============================================================
-- Raizes do Nordeste - script de criacao do banco + carga inicial
-- Banco: MySQL 8+
-- Pode ser executado ANTES ou DEPOIS da primeira execucao da API
-- (usa CREATE ... IF NOT EXISTS e INSERT IGNORE, entao e reexecutavel).
-- ============================================================

CREATE DATABASE IF NOT EXISTS raizes_nordeste CHARACTER SET utf8mb4;
USE raizes_nordeste;

-- ---------- DDL (espelha as entidades JPA) ----------

CREATE TABLE IF NOT EXISTS usuarios (
    id      BIGINT       NOT NULL AUTO_INCREMENT,
    nome    VARCHAR(255) NOT NULL,
    email   VARCHAR(255) NOT NULL,
    senha   VARCHAR(255) NOT NULL,              -- hash BCrypt
    perfil  VARCHAR(255) NOT NULL,              -- CLIENTE | ATENDENTE | GERENTE | ADMINISTRADOR
    PRIMARY KEY (id),
    UNIQUE KEY uk_usuarios_email (email)
);

CREATE TABLE IF NOT EXISTS unidades (
    id        BIGINT       NOT NULL AUTO_INCREMENT,
    nome      VARCHAR(255) NOT NULL,
    cnpj      VARCHAR(255) NOT NULL,
    endereco  VARCHAR(255) NOT NULL,
    ativa     BIT(1)       NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_unidades_cnpj (cnpj)
);

CREATE TABLE IF NOT EXISTS produtos (
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    nome        VARCHAR(255)   NOT NULL,
    preco_base  DECIMAL(38,2)  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS estoque (
    id          BIGINT  NOT NULL AUTO_INCREMENT,
    unidade_id  BIGINT  NOT NULL,
    produto_id  BIGINT  NOT NULL,
    quantidade  INT     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_estoque_unidade_produto (unidade_id, produto_id),
    CONSTRAINT fk_estoque_unidade FOREIGN KEY (unidade_id) REFERENCES unidades (id),
    CONSTRAINT fk_estoque_produto FOREIGN KEY (produto_id) REFERENCES produtos (id)
);

CREATE TABLE IF NOT EXISTS pedidos (
    id            BIGINT         NOT NULL AUTO_INCREMENT,
    usuario_id    BIGINT         NOT NULL,
    unidade_id    BIGINT         NOT NULL,
    canal_pedido  VARCHAR(255)   NOT NULL,      -- APP | TOTEM | BALCAO
    status        VARCHAR(255)   NOT NULL,      -- AGUARDANDO_PAGAMENTO | EM_PREPARO | PAGO | CANCELADO
    valor_total   DECIMAL(38,2)  NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_pedidos_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
    CONSTRAINT fk_pedidos_unidade FOREIGN KEY (unidade_id) REFERENCES unidades (id)
);

CREATE TABLE IF NOT EXISTS itens_pedido (
    id              BIGINT         NOT NULL AUTO_INCREMENT,
    pedido_id       BIGINT         NOT NULL,
    produto_id      BIGINT         NOT NULL,
    quantidade      INT            NOT NULL,
    preco_unitario  DECIMAL(38,2)  NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_itens_pedido_pedido  FOREIGN KEY (pedido_id)  REFERENCES pedidos (id),
    CONSTRAINT fk_itens_pedido_produto FOREIGN KEY (produto_id) REFERENCES produtos (id)
);

-- ---------- Carga inicial (seed) ----------

INSERT IGNORE INTO unidades (id, nome, endereco, cnpj, ativa)
VALUES (1, 'Unidade Recife (Matriz)', 'Av. Boa Viagem, 1000', '12.345.678/0001-90', 1);

INSERT IGNORE INTO produtos (id, nome, preco_base) VALUES (1, 'Bolo de Rolo', 35.00);
INSERT IGNORE INTO produtos (id, nome, preco_base) VALUES (2, 'Tapioca Rendada', 15.00);
INSERT IGNORE INTO produtos (id, nome, preco_base) VALUES (3, 'Cuscuz com Charque', 22.00);

INSERT IGNORE INTO estoque (id, unidade_id, produto_id, quantidade) VALUES (1, 1, 1, 50);
INSERT IGNORE INTO estoque (id, unidade_id, produto_id, quantidade) VALUES (2, 1, 2, 30);
INSERT IGNORE INTO estoque (id, unidade_id, produto_id, quantidade) VALUES (3, 1, 3, 20);

-- Usuarios nao sao semeados aqui: crie-os via POST /auth/registrar
-- (a senha e gravada com hash BCrypt pela propria API).
