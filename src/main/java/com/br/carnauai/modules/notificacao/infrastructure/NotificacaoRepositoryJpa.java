package com.br.carnauai.modules.notificacao.infrastructure;

import com.br.carnauai.modules.notificacao.domain.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificacaoRepositoryJpa extends JpaRepository<Notificacao, UUID> {
}
