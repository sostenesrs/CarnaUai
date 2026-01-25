package com.br.carnauai.infrastructure.persistence.notificacao;

import com.br.carnauai.domain.notificacao.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificacaoRepositoryJpa extends JpaRepository<Notificacao, UUID> {
}
