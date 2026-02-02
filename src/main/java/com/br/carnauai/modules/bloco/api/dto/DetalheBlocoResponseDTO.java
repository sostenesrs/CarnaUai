package com.br.carnauai.modules.bloco.api.dto;

import java.util.List;

public record DetalheBlocoResponseDTO(
        BlocoResponseDTO bloco,
        List<BlocoDiaResponseDTO> proximosDias
) {
}
