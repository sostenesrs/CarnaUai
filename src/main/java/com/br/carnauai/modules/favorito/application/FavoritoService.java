package com.br.carnauai.modules.favorito.application;

import com.br.carnauai.modules.bloco.domain.Bloco;
import com.br.carnauai.modules.bloco.infrastructure.BlocoRepositoryJpa;
import com.br.carnauai.modules.favorito.api.dto.FavoritoRequestDTO;
import com.br.carnauai.modules.favorito.domain.Favorito;
import com.br.carnauai.modules.favorito.domain.FavoritoId;
import com.br.carnauai.modules.favorito.infrastructure.FavoritoRepositoryJpa;
import com.br.carnauai.shared.infrastructure.persistence.UsuarioRepositoryJpa;
import com.br.carnauai.shared.kernel.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FavoritoService {

    private final FavoritoRepositoryJpa favoritoRepository;
    private final UsuarioRepositoryJpa usuarioRepository;
    private final BlocoRepositoryJpa blocoRepository;

    public FavoritoService(FavoritoRepositoryJpa favoritoRepository,
                           UsuarioRepositoryJpa usuarioRepository,
                           BlocoRepositoryJpa blocoRepository) {
        this.favoritoRepository = favoritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.blocoRepository = blocoRepository;
    }

    public Favorito save(FavoritoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + dto.usuarioId()));
        Bloco bloco = blocoRepository.findById(dto.blocoId())
                .orElseThrow(() -> new IllegalArgumentException("Bloco não encontrado: " + dto.blocoId()));
        FavoritoId id = new FavoritoId(dto.usuarioId(), dto.blocoId());
        Favorito favorito = new Favorito(id, usuario, bloco);
        return favoritoRepository.save(favorito);
    }

    public void delete(UUID usuarioId, UUID blocoId) {
        favoritoRepository.deleteById(new FavoritoId(usuarioId, blocoId));
    }

    public List<Favorito> listByUsuario(UUID usuarioId) {
        return favoritoRepository.findById_UsuarioId(usuarioId);
    }
}
