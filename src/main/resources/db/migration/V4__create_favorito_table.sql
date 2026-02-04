-- Flyway migration: create favorito table with composite PK (usuario_id, bloco_id)
-- Columns: usuario_id UUID, bloco_id UUID, favoritado boolean, dth_criacao, dth_atualizacao

CREATE TABLE IF NOT EXISTS favorito (
    usuario_id UUID NOT NULL,
    bloco_id UUID NOT NULL,
    favoritado BOOLEAN NOT NULL DEFAULT TRUE,
    dth_criacao TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    dth_atualizacao TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    CONSTRAINT pk_favorito PRIMARY KEY (usuario_id, bloco_id),
    CONSTRAINT fk_favorito_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id) ON DELETE CASCADE,
    CONSTRAINT fk_favorito_bloco FOREIGN KEY (bloco_id) REFERENCES bloco (id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_favorito_usuario ON favorito (usuario_id);
CREATE INDEX IF NOT EXISTS idx_favorito_bloco ON favorito (bloco_id);

//todo alterar script para o de baixo:?
ALTER TABLE favorito
    ADD COLUMN IF NOT EXISTS dt_alteracao TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now();

ALTER TABLE favorito
    ADD COLUMN IF NOT EXISTS favoritado BOOLEAN NOT NULL DEFAULT TRUE;
