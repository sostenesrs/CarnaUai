-- File: `src/main/resources/db/migration/V2__renomear_timestamps_para_portugues.sql`
-- Renomeia created_at -> dth_criacao e updated_at -> dth_atualizacao (quando existirem)

DO $$
BEGIN
  -- usuario
  IF EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = current_schema() AND table_name = 'usuario' AND column_name = 'created_at'
  ) THEN
    EXECUTE 'ALTER TABLE usuario RENAME COLUMN created_at TO dth_criacao';
  END IF;

  IF EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = current_schema() AND table_name = 'usuario' AND column_name = 'updated_at'
  ) THEN
    EXECUTE 'ALTER TABLE usuario RENAME COLUMN updated_at TO dth_atualizacao';
  END IF;

  -- bairro
  IF EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = current_schema() AND table_name = 'bairro' AND column_name = 'created_at'
  ) THEN
    EXECUTE 'ALTER TABLE bairro RENAME COLUMN created_at TO dth_criacao';
  END IF;

  -- bloco
  IF EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = current_schema() AND table_name = 'bloco' AND column_name = 'created_at'
  ) THEN
    EXECUTE 'ALTER TABLE bloco RENAME COLUMN created_at TO dth_criacao';
  END IF;

  IF EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = current_schema() AND table_name = 'bloco' AND column_name = 'updated_at'
  ) THEN
    EXECUTE 'ALTER TABLE bloco RENAME COLUMN updated_at TO dth_atualizacao';
  END IF;

  -- bloco_dia
  IF EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = current_schema() AND table_name = 'bloco_dia' AND column_name = 'created_at'
  ) THEN
    EXECUTE 'ALTER TABLE bloco_dia RENAME COLUMN created_at TO dth_criacao';
  END IF;

  -- favorito
  IF EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = current_schema() AND table_name = 'favorito' AND column_name = 'created_at'
  ) THEN
    EXECUTE 'ALTER TABLE favorito RENAME COLUMN created_at TO dth_criacao';
  END IF;

  -- agenda
  IF EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = current_schema() AND table_name = 'agenda' AND column_name = 'created_at'
  ) THEN
    EXECUTE 'ALTER TABLE agenda RENAME COLUMN created_at TO dth_criacao';
  END IF;

  -- notificacao
  IF EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = current_schema() AND table_name = 'notificacao' AND column_name = 'created_at'
  ) THEN
    EXECUTE 'ALTER TABLE notificacao RENAME COLUMN created_at TO dth_criacao';
  END IF;
END
$$;
