package com.br.carnauai.modules.bloco.api.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record BlocoDiaResponseDTO(
        UUID id,
        UUID blocoId,
        LocalDate dataEvento,
        LocalTime horaInicio,
        LocalTime horaFim
) {
}
