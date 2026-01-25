package com.br.carnauai.infrastructure.persistence.bloco_dia;

import com.br.carnauai.domain.bloco_dia.BlocoDia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BlocoDiaRepositoryJpa extends JpaRepository<BlocoDia, UUID> {
    List<BlocoDia> findByDataEvento(LocalDate dataEvento);
}
