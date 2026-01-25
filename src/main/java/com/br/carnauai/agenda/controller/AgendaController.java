package com.br.carnauai.agenda.controller;

import com.br.carnauai.agenda.service.AgendaService;
import com.br.carnauai.domain.agenda.Agenda;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/agenda")
public class AgendaController {

    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @PostMapping
    public ResponseEntity<Agenda> create(@RequestBody Agenda agenda) {
        Agenda created = agendaService.save(agenda);
        return ResponseEntity.created(URI.create("/api/agenda/" + created.getId())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Agenda>> list() {
        return ResponseEntity.ok(agendaService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        agendaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
