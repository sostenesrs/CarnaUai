package com.br.carnauai.modules.favorito.api;

import com.br.carnauai.modules.favorito.api.dto.FavoritoResponseDTO;
import com.br.carnauai.modules.favorito.domain.Favorito;
import com.br.carnauai.modules.favorito.domain.FavoritoId;
import org.springframework.stereotype.Component;

@Component
public class FavoritoMapper {

    public FavoritoResponseDTO toResponseDTO(Favorito favorito) {
        if (favorito == null) return null;
        FavoritoId id = favorito.getId();
        return new FavoritoResponseDTO(
                id != null ? id.getUsuarioId() : null,
                id != null ? id.getBlocoId() : null,
                favorito.getFavoritado(),
                favorito.getDthCriacao()
        );
    }
}
