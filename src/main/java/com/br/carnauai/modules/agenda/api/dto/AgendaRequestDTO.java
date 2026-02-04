package com.br.carnauai.modules.agenda.api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AgendaRequestDTO(
        @NotNull UUID usuarioId,
        @NotNull UUID blocoId
) {
}
