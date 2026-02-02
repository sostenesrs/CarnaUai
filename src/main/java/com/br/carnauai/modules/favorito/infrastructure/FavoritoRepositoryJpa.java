package com.br.carnauai.modules.favorito.infrastructure;

import com.br.carnauai.modules.favorito.domain.Favorito;
import com.br.carnauai.modules.favorito.domain.FavoritoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavoritoRepositoryJpa extends JpaRepository<Favorito, FavoritoId> {
    List<Favorito> findById_UsuarioId(UUID usuarioId);
}
