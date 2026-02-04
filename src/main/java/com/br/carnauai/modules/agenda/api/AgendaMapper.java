package com.br.carnauai.modules.agenda.api;

import com.br.carnauai.modules.agenda.api.dto.AgendaRequestDTO;
import com.br.carnauai.modules.agenda.api.dto.AgendaResponseDTO;
import com.br.carnauai.modules.agenda.domain.Agenda;
import org.springframework.stereotype.Component;

@Component
public class AgendaMapper {

    public AgendaResponseDTO toResponseDTO(Agenda agenda) {
        if (agenda == null) return null;
        return new AgendaResponseDTO(
                agenda.getId(),
                agenda.getUsuario() != null ? agenda.getUsuario().getId() : null,
                agenda.getBloco() != null ? agenda.getBloco().getId() : null,
                agenda.getDthCriacao()
        );
    }
}
