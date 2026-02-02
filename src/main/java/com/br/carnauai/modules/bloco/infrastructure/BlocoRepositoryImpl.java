package com.br.carnauai.modules.bloco.infrastructure;

import com.br.carnauai.modules.bloco.application.BlocoRepository;
import com.br.carnauai.modules.bloco.domain.Bloco;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
