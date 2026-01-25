package com.br.carnauai.infrastructure.persistence.agenda;

import com.br.carnauai.domain.agenda.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AgendaRepositoryJpa extends JpaRepository<Agenda, UUID> {
}
