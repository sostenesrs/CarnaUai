package com.br.carnauai.modules.agenda.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AgendaResponseDTO(
        UUID id,
        UUID usuarioId,
        UUID blocoId,
        LocalDateTime dthCriacao
) {
}
