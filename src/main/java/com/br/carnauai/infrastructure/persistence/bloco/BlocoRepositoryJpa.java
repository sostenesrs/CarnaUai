package com.br.carnauai.infrastructure.persistence.bloco;

import com.br.carnauai.domain.bloco.Bloco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlocoRepositoryJpa extends JpaRepository<Bloco, UUID> {
}
