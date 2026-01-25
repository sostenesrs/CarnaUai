package com.br.carnauai.infrastructure.persistence.favorito;

import com.br.carnauai.domain.favorito.Favorito;
import com.br.carnauai.domain.favorito.FavoritoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavoritoRepositoryJpa extends JpaRepository<Favorito, FavoritoId> {
    List<Favorito> findByUsuarioId(UUID usuarioId);
}
