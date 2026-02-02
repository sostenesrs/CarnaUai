package com.br.carnauai.modules.agenda.infrastructure;

import com.br.carnauai.modules.agenda.domain.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AgendaRepositoryJpa extends JpaRepository<Agenda, UUID> {
}
