CREATE EXTENSION IF NOT EXISTS "pgcrypto";

INSERT INTO estilo_musical (id, nome) VALUES
                                          (gen_random_uuid(), 'Axé'),
                                          (gen_random_uuid(), 'Samba'),
                                          (gen_random_uuid(), 'Pagode'),
                                          (gen_random_uuid(), 'Forró'),
                                          (gen_random_uuid(), 'MPB'),
                                          (gen_random_uuid(), 'Pop'),
                                          (gen_random_uuid(), 'Rock'),
                                          (gen_random_uuid(), 'Eletrônica'),
                                          (gen_random_uuid(), 'Afro'),
                                          (gen_random_uuid(), 'Marchinha'),
                                          (gen_random_uuid(), 'Funk'),
                                          (gen_random_uuid(), 'Reggae'),
                                          (gen_random_uuid(), 'Hip Hop'),
                                          (gen_random_uuid(), 'Jazz'),
                                          (gen_random_uuid(), 'Instrumental')
ON CONFLICT (nome) DO NOTHING;

INSERT INTO perfil_publico (id, nome) VALUES
                                          (gen_random_uuid(), 'LGBTQIAPN+'),
                                          (gen_random_uuid(), 'Família'),
                                          (gen_random_uuid(), 'Infantil'),
                                          (gen_random_uuid(), 'Universitário'),
                                          (gen_random_uuid(), 'Alternativo'),
                                          (gen_random_uuid(), 'Tradicional'),
                                          (gen_random_uuid(), 'Inclusivo'),
                                          (gen_random_uuid(), 'Cultural')
ON CONFLICT (nome) DO NOTHING;