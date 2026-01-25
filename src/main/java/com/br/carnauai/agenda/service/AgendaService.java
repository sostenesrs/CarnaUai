package com.br.carnauai.agenda.service;

import com.br.carnauai.domain.agenda.Agenda;
import com.br.carnauai.infrastructure.persistence.agenda.AgendaRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgendaService {

    private final AgendaRepositoryJpa agendaRepository;

    public AgendaService(AgendaRepositoryJpa agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public Agenda save(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public List<Agenda> findAll() {
        return agendaRepository.findAll();
    }

    public void delete(UUID id) {
        agendaRepository.deleteById(id);
    }
}
