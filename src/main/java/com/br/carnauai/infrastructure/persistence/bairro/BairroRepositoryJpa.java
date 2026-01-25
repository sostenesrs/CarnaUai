package com.br.carnauai.infrastructure.persistence.bairro;

import com.br.carnauai.domain.bairro.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BairroRepositoryJpa extends JpaRepository<Bairro, UUID> {
}
