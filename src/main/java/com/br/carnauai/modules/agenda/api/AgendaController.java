package com.br.carnauai.modules.agenda.api;

import com.br.carnauai.modules.agenda.api.dto.AgendaRequestDTO;
import com.br.carnauai.modules.agenda.api.dto.AgendaResponseDTO;
import com.br.carnauai.modules.agenda.application.AgendaService;
import com.br.carnauai.modules.agenda.domain.Agenda;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/agenda")
public class AgendaController {

    private final AgendaService agendaService;
    private final AgendaMapper agendaMapper;

    public AgendaController(AgendaService agendaService, AgendaMapper agendaMapper) {
        this.agendaService = agendaService;
        this.agendaMapper = agendaMapper;
    }

    @PostMapping
    public ResponseEntity<AgendaResponseDTO> create(@Valid @RequestBody AgendaRequestDTO dto) {
        Agenda created = agendaService.save(dto);
        return ResponseEntity.created(URI.create("/api/agenda/" + created.getId()))
                .body(agendaMapper.toResponseDTO(created));
    }

    @GetMapping
    public ResponseEntity<List<AgendaResponseDTO>> list() {
        List<AgendaResponseDTO> list = agendaService.findAll().stream()
                .map(agendaMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(list);
    }

}
