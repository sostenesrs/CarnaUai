package com.br.carnauai.modules.bloco.infrastructure;

import com.br.carnauai.modules.bloco.domain.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BairroRepositoryJpa extends JpaRepository<Bairro, UUID> {
}
