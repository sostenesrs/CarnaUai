package com.br.carnauai.favorito.service;

import com.br.carnauai.domain.favorito.Favorito;
import com.br.carnauai.domain.favorito.FavoritoId;
import com.br.carnauai.infrastructure.persistence.favorito.FavoritoRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FavoritoService {

    private final FavoritoRepositoryJpa favoritoRepository;

    public FavoritoService(FavoritoRepositoryJpa favoritoRepository) {
        this.favoritoRepository = favoritoRepository;
    }

    public Favorito save(Favorito favorito) {
        if (favorito.getId() == null) {
            favorito.setId(new FavoritoId(favorito.getUsuario().getId(), favorito.getBloco().getId()));
        }
        return favoritoRepository.save(favorito);
    }

    public void delete(FavoritoId id) {
        favoritoRepository.deleteById(id);
    }

    public List<Favorito> listByUsuario(UUID usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId);
    }
}
