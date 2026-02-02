package com.br.carnauai.modules.bloco.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record BlocoResponseDTO(
        UUID id,
        String nome,
        String descricao,
        UUID bairroId,
        String bairroNome,
        String bairroCidade,
        BigDecimal latitude,
        BigDecimal longitude,
        Boolean ativo,
        LocalDateTime dthCriacao
) {
}
