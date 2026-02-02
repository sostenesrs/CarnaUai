package com.br.carnauai.modules.bloco.api.dto;

import java.util.UUID;

public record BairroResponseDTO(
        UUID id,
        String nome,
        String cidade,
        String estado
) {
}
