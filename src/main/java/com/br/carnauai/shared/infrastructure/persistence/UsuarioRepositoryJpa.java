package com.br.carnauai.shared.infrastructure.persistence;

import com.br.carnauai.shared.kernel.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepositoryJpa extends JpaRepository<Usuario, UUID> {
}
