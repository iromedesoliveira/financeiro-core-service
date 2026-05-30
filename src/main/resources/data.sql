MERGE INTO tb_usuario t
USING (SELECT 1 AS id FROM dual) s
ON (t.id = s.id)
WHEN NOT MATCHED THEN
  INSERT (id, nome, email, senha) VALUES (1, 'Iromedes', 'iromedes@email.com', '123456');