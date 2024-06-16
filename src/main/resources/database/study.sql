--Database: study

CREATE TABLE usuario ( 
  id BIGINT NOT NULL AUTO_INCREMENT, 
  nome VARCHAR(45) NOT NULL, 
  email VARCHAR(50) NOT NULL, 
  senha VARCHAR(255) NOT NULL, 
  PRIMARY KEY (id), 
  UNIQUE KEY (email) 
); 

CREATE TABLE turma ( 
  id BIGINT NOT NULL AUTO_INCREMENT, 
  titulo VARCHAR(45) NOT NULL, 
  descricao VARCHAR(100) NOT NULL, 
  criador_id BIGINT NOT NULL, 
  PRIMARY KEY (id), 
  KEY criador_id (criador_id), 
  CONSTRAINT fk_turma_criador FOREIGN KEY (criador_id) REFERENCES usuario (id) 
); 
 
CREATE TABLE conteudo ( 
  id BIGINT NOT NULL AUTO_INCREMENT, 
  titulo VARCHAR(45) NOT NULL, 
  descricao VARCHAR(200) NOT NULL, 
  data_criacao DATETIME NOT NULL, 
  turma_id BIGINT NOT NULL, 
  PRIMARY KEY (id), 
  KEY turma_id (turma_id), 
  CONSTRAINT fk_conteudo_turma FOREIGN KEY (turma_id) REFERENCES turma (id) ON DELETE CASCADE 
); 
 
CREATE TABLE usuario_turma ( 
  turma_id BIGINT NOT NULL, 
  usuario_id BIGINT NOT NULL, 
  PRIMARY KEY (turma_id, usuario_id), 
  KEY usuario_id (usuario_id), 
  CONSTRAINT fk_usuario_turma_turma FOREIGN KEY (turma_id) REFERENCES turma (id) ON DELETE CASCADE, 
  CONSTRAINT fk_usuario_turma_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id) 
); 