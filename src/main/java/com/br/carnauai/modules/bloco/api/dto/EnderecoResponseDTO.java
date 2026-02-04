package com.br.carnauai.modules.bloco.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record EnderecoResponseDTO(
        UUID id,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        BigDecimal latitude,
        BigDecimal longitude
) {
}
