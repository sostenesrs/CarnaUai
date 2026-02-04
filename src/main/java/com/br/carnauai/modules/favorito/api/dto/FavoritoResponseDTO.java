package com.br.carnauai.modules.favorito.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record FavoritoResponseDTO(
        UUID usuarioId,
        UUID blocoId,
        Boolean favoritado,
        LocalDateTime dthCriacao
) {
}
