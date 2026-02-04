package com.br.carnauai.modules.favorito.application;

import com.br.carnauai.modules.bloco.domain.Bloco;
import com.br.carnauai.modules.bloco.infrastructure.BlocoRepositoryJpa;
import com.br.carnauai.modules.favorito.api.dto.FavoritoRequestDTO;
import com.br.carnauai.modules.favorito.domain.Favorito;
import com.br.carnauai.modules.favorito.domain.FavoritoId;
import com.br.carnauai.modules.favorito.exception.DuplicateFavoriteException;
import com.br.carnauai.modules.favorito.infrastructure.FavoritoRepositoryJpa;
import com.br.carnauai.shared.infrastructure.persistence.UsuarioRepositoryJpa;
import com.br.carnauai.shared.kernel.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FavoritoService {

    private final FavoritoRepositoryJpa favoritoRepository;
    private final UsuarioRepositoryJpa usuarioRepository;
    private final BlocoRepositoryJpa blocoRepository;

    public FavoritoService(FavoritoRepositoryJpa favoritoRepository, UsuarioRepositoryJpa usuarioRepository, BlocoRepositoryJpa blocoRepository) {
        this.favoritoRepository = favoritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.blocoRepository = blocoRepository;
    }

    public Favorito save(FavoritoRequestDTO dto) {
        return save(dto.usuarioId(), dto.blocoId());
    }

    public Favorito save(UUID usuarioId, UUID blocoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + usuarioId));
        Bloco bloco = blocoRepository.findById(blocoId).orElseThrow(() -> new IllegalArgumentException("Bloco não encontrado: " + blocoId));

        FavoritoId id = new FavoritoId(usuarioId, blocoId);

        Optional<Favorito> existing = favoritoRepository.findById(id);
        if (existing.isPresent()) {
            Favorito fav = existing.get();
            if (Boolean.TRUE.equals(fav.getFavoritado())) {
                throw new DuplicateFavoriteException("Bloco já favoritado por este usuário");
            } else {
                // Reactivate favorite
                fav.setFavoritado(true);
                return favoritoRepository.save(fav);
            }
        }

        Favorito favorito = new Favorito(id, usuario, bloco);
        return favoritoRepository.save(favorito);
    }

    public void delete(UUID usuarioId, UUID blocoId) {
        FavoritoId id = new FavoritoId(usuarioId, blocoId);
        Favorito favorito = favoritoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Favorito não encontrado"));
        // soft-unfavorite: mark as not favorited and update timestamp, keep row in DB
        favorito.unfavoritar();
        favoritoRepository.save(favorito);
    }

    public List<Favorito> listByUsuario(UUID usuarioId) {
        return favoritoRepository.findById_UsuarioIdAndFavoritadoTrue(usuarioId);
    }
}
