MERGE INTO tb_usuario u
USING dual ON (u.id = 1)
WHEN NOT MATCHED THEN
  INSERT (id, nome, email, senha, perfil_investidor)
  VALUES (1, 'Irome', 'irome@test.com', '$2a$10$eACCYoNOHEqXve8aIWT8JuHAcPFZgQ/v8Zz88K1sYtG9oB.j.B/gG', 'CONSERVADOR');

MERGE INTO tb_transacao t
USING dual ON (t.id = 1)
WHEN NOT MATCHED THEN
  INSERT (id, descricao, valor, data_vencimento, usuario_id)
  VALUES (1, 'Salário Mensal', 5000.00, '2026-06-15', 1);

  MERGE INTO tb_transacao t
USING dual ON (t.id = 2)
WHEN NOT MATCHED THEN
  INSERT (id, descricao, valor, data_vencimento, usuario_id)
  VALUES (2, 'Pagamento Aluguel', 1200.00, '2026-06-16', 1);