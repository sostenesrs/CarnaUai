package com.br.carnauai.bloco.repository;

import com.br.carnauai.domain.bloco.Bloco;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BlocoRepository {
    Bloco save(Bloco bloco);
    Optional<Bloco> findById(UUID id);
    List<Bloco> findAll();
    void deleteById(UUID id);
}
