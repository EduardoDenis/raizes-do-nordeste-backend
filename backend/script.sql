USE raizes_nordeste;

INSERT INTO unidade (nome, endereco, cnpj, ativa)
VALUES ('Unidade Recife (Matriz)', 'Av. Boa Viagem, 1000', '12.345.678/0001-90', true);

INSERT INTO produto (nome, preco_base) VALUES ('Bolo de Rolo', 35.00);
INSERT INTO produto (nome, preco_base) VALUES ('Tapioca Rendada', 15.00);
INSERT INTO produto (nome, preco_base) VALUES ('Cuscuz com Charque', 22.00);

INSERT INTO estoque (unidade_id, produto_id, quantidade_disponivel) VALUES (1, 1, 50);
INSERT INTO estoque (unidade_id, produto_id, quantidade_disponivel) VALUES (1, 2, 30);
INSERT INTO estoque (unidade_id, produto_id, quantidade_disponivel) VALUES (1, 3, 20);