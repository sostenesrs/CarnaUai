-- ======================================================
-- MODELAGEM SQL - MVP (PostgreSQL + Flyway)
-- Domínio: Blocos / Agenda de Eventos
-- ======================================================
-- Decisão: NÃO usar PostGIS no início.
-- Latitude/Longitude em DECIMAL atendem bem o MVP.
-- PostGIS pode entrar depois via migration V2.

-- ======================================================
-- EXTENSÕES NECESSÁRIAS
-- ======================================================
CREATE EXTENSION IF NOT EXISTS pgcrypto; -- gen_random_uuid()

-- ======================================================
-- USUÁRIO
-- ======================================================
CREATE TABLE usuario (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome            VARCHAR(120) NOT NULL,
    email           VARCHAR(150) NOT NULL UNIQUE,
    senha_hash      VARCHAR(255) NOT NULL,
    role            VARCHAR(30) NOT NULL DEFAULT 'USER', -- USER | ADMIN
    created_at      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP
);

-- ======================================================
-- BAIRRO
-- ======================================================
CREATE TABLE bairro (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome        VARCHAR(100) NOT NULL,
    cidade      VARCHAR(100) NOT NULL,
    estado      VARCHAR(50) NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_bairro UNIQUE (nome, cidade)
);

-- ======================================================
-- BLOCO
-- ======================================================
CREATE TABLE bloco (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome            VARCHAR(150) NOT NULL,
    descricao       TEXT,
    bairro_id       UUID NOT NULL,
    latitude        DECIMAL(9,6),
    longitude       DECIMAL(9,6),
    ativo           BOOLEAN NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP,
    CONSTRAINT fk_bloco_bairro
        FOREIGN KEY (bairro_id) REFERENCES bairro(id)
);

-- ======================================================
-- BLOCO_DIA (agenda oficial do bloco)
-- Datas e horários bem definidos
-- ======================================================
CREATE TABLE bloco_dia (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    bloco_id        UUID NOT NULL,
    data_evento     DATE NOT NULL,
    hora_inicio     TIME NOT NULL,
    hora_fim        TIME NOT NULL,
    created_at      TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_bloco_dia_bloco
        FOREIGN KEY (bloco_id) REFERENCES bloco(id),
    CONSTRAINT uk_bloco_dia UNIQUE (bloco_id, data_evento)
);

-- ======================================================
-- FAVORITO (usuário ↔ bloco)
-- ======================================================
CREATE TABLE favorito (
    usuario_id  UUID NOT NULL,
    bloco_id    UUID NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (usuario_id, bloco_id),
    CONSTRAINT fk_favorito_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_favorito_bloco
        FOREIGN KEY (bloco_id) REFERENCES bloco(id)
);

-- ======================================================
-- AGENDA (visão do usuário - opcional, pode ser derivada)
-- ======================================================
CREATE TABLE agenda (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id      UUID NOT NULL,
    bloco_dia_id    UUID NOT NULL,
    created_at      TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_agenda_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_agenda_bloco_dia
        FOREIGN KEY (bloco_dia_id) REFERENCES bloco_dia(id),
    CONSTRAINT uk_agenda UNIQUE (usuario_id, bloco_dia_id)
);

-- ======================================================
-- NOTIFICAÇÃO
-- ======================================================
CREATE TABLE notificacao (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id      UUID NOT NULL,
    titulo          VARCHAR(150) NOT NULL,
    mensagem        TEXT NOT NULL,
    lida            BOOLEAN NOT NULL DEFAULT FALSE,
    created_at      TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_notificacao_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- ======================================================
-- ÍNDICES (pensados para os filtros do MVP)
-- ======================================================
CREATE INDEX idx_bloco_bairro ON bloco(bairro_id);
CREATE INDEX idx_bloco_dia_data ON bloco_dia(data_evento);
CREATE INDEX idx_favorito_usuario ON favorito(usuario_id);
