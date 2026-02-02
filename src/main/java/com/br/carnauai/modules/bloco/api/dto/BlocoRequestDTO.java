package com.br.carnauai.modules.bloco.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record BlocoRequestDTO(
        @NotBlank String nome,
        String descricao,
        UUID bairroId,
        BigDecimal latitude,
        BigDecimal longitude,
        Boolean ativo
) {
}
