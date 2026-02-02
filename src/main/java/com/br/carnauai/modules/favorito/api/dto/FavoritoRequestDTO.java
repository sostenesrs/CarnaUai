package com.br.carnauai.modules.favorito.api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FavoritoRequestDTO(
        @NotNull UUID usuarioId,
        @NotNull UUID blocoId
) {
}
