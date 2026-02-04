
-- ============================
-- EXTENSÃO PARA UUID
-- ============================
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- ============================
-- USUÁRIO
-- ============================
CREATE TABLE usuario (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),


                         nome VARCHAR(100),
                         email VARCHAR(150) UNIQUE,
                         apelido VARCHAR(50),
                         imagem_url TEXT,
                         role varchar(30),
                         dt_criacao TIMESTAMP DEFAULT NOW(),
                         dt_atualizacao TIMESTAMP DEFAULT NOW()


);

-- ============================
-- ENDEREÇO (SAÍDA / DISPERSÃO)
-- ============================
CREATE TABLE endereco (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),


                          logradouro VARCHAR(255),
                          numero VARCHAR(20),
                          complemento VARCHAR(100),
                          bairro VARCHAR(100),
                          cidade VARCHAR(100),
                          estado VARCHAR(50),

                          latitude DECIMAL(9,6),
                          longitude DECIMAL(9,6)

);

-- ============================
-- BLOCO
-- ============================
CREATE TABLE bloco (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                       id_externo BIGINT UNIQUE NOT NULL, -- id vindo do MapadoBloco
                       oid VARCHAR(20) UNIQUE NOT NULL,

                       nome VARCHAR(255) NOT NULL,
                       tema VARCHAR(255),

                       data DATE NOT NULL,
                       horario TIME NOT NULL,
                       horario_fim TIME,

                       dia_semana VARCHAR(15),
                       tamanho VARCHAR(50),

                       tipo_evento VARCHAR(50),
                       categoria_evento VARCHAR(50),

                       status_verificacao VARCHAR(50),
                       oficial_prefeitura BOOLEAN DEFAULT FALSE,

                       imagem_url TEXT,
                       destaque BOOLEAN DEFAULT FALSE,

                       endereco_saida_id UUID,
                       endereco_dispersao_id UUID,

                       total_favoritos INT DEFAULT 0,

                       dt_criacao TIMESTAMP DEFAULT NOW(),
                       dt_atualizacao TIMESTAMP DEFAULT NOW(),

                       CONSTRAINT fk_bloco_endereco_saida
                           FOREIGN KEY (endereco_saida_id)
                           REFERENCES endereco(id),

                       CONSTRAINT fk_bloco_endereco_dispersao
                           FOREIGN KEY (endereco_dispersao_id)
                           REFERENCES endereco(id)


);

-- ============================
-- ESTILO MUSICAL
-- ============================
CREATE TABLE estilo_musical (
                                id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                nome VARCHAR(50) UNIQUE NOT NULL
);

-- ============================
-- PERFIL DE PÚBLICO
-- ============================
CREATE TABLE perfil_publico (
                                id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                nome VARCHAR(50) UNIQUE NOT NULL
);

-- ============================
-- BLOCO x ESTILO MUSICAL
-- ============================
CREATE TABLE bloco_estilo_musical (
                                      bloco_id UUID NOT NULL,
                                      estilo_id UUID NOT NULL,


                                      PRIMARY KEY (bloco_id, estilo_id),

                                      FOREIGN KEY (bloco_id) REFERENCES bloco(id) ON DELETE CASCADE,
                                      FOREIGN KEY (estilo_id) REFERENCES estilo_musical(id) ON DELETE CASCADE


);

-- ============================
-- BLOCO x PERFIL DE PÚBLICO
-- ============================
CREATE TABLE bloco_perfil_publico (
                                      bloco_id UUID NOT NULL,
                                      perfil_id UUID NOT NULL,


                                      PRIMARY KEY (bloco_id, perfil_id),

                                      FOREIGN KEY (bloco_id) REFERENCES bloco(id) ON DELETE CASCADE,
                                      FOREIGN KEY (perfil_id) REFERENCES perfil_publico(id) ON DELETE CASCADE

);

-- ============================
-- FAVORITOS
-- ============================
CREATE TABLE favorito (
                          usuario_id UUID NOT NULL,
                          bloco_id UUID NOT NULL,


                          dt_criacao TIMESTAMP DEFAULT NOW(),

                          PRIMARY KEY (usuario_id, bloco_id),

                          FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
                          FOREIGN KEY (bloco_id) REFERENCES bloco(id) ON DELETE CASCADE


);

-- ============================
-- CHECK-IN
-- ============================
CREATE TABLE checkin (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),


                         usuario_id UUID NOT NULL,
                         bloco_id UUID NOT NULL,

                         latitude DECIMAL(9,6),
                         longitude DECIMAL(9,6),

                         dt_criacao TIMESTAMP DEFAULT NOW(),

                         FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
                         FOREIGN KEY (bloco_id) REFERENCES bloco(id) ON DELETE CASCADE


);

-- ============================
-- STATUS DO BLOCO (HEATMAP)
-- ============================
CREATE TABLE status_bloco (
                              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                              bloco_id UUID NOT NULL,

                              nivel_lotacao VARCHAR(30),
                              policiamento BOOLEAN,
                              observacao TEXT,

                              dt_criacao TIMESTAMP DEFAULT NOW(),

                              FOREIGN KEY (bloco_id) REFERENCES bloco(id) ON DELETE CASCADE


);

-- ============================
-- ÍNDICES
-- ============================
CREATE INDEX idx_bloco_data ON bloco (data);
CREATE INDEX idx_bloco_categoria ON bloco (categoria_evento);
CREATE INDEX idx_bloco_oficial ON bloco (oficial_prefeitura);
CREATE INDEX idx_bloco_id_externo ON bloco (id_externo);

CREATE INDEX idx_endereco_lat_lng ON endereco (latitude, longitude);
CREATE INDEX idx_checkin_bloco ON checkin (bloco_id);
CREATE INDEX idx_checkin_created ON checkin (dt_criacao);
