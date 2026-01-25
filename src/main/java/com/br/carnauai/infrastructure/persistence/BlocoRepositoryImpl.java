package com.br.carnauai.infrastructure.persistence;

import com.br.carnauai.bloco.repository.BlocoRepository;
import com.br.carnauai.domain.bloco.Bloco;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

@Repository
public class BlocoRepositoryImpl implements BlocoRepository {

    private final Map<UUID, Bloco> store = new ConcurrentHashMap<>();

    @Override
    public Bloco save(Bloco bloco) {
        if (bloco.getId() == null) {
            bloco.setId(UUID.randomUUID());
        }
        store.put(bloco.getId(), bloco);
        return bloco;
    }

    @Override
    public Optional<Bloco> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Bloco> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(UUID id) {
        store.remove(id);
    }
}
