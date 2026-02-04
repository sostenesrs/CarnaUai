package com.br.carnauai.modules.agenda.application;

import com.br.carnauai.modules.agenda.api.dto.AgendaRequestDTO;
import com.br.carnauai.modules.agenda.domain.Agenda;
import com.br.carnauai.modules.agenda.infrastructure.AgendaRepositoryJpa;
import com.br.carnauai.modules.bloco.domain.Bloco;
import com.br.carnauai.modules.bloco.infrastructure.BlocoRepositoryJpa;
import com.br.carnauai.shared.infrastructure.persistence.UsuarioRepositoryJpa;
import com.br.carnauai.shared.kernel.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgendaService {

    private final AgendaRepositoryJpa agendaRepository;
    private final UsuarioRepositoryJpa usuarioRepository;
    private final BlocoRepositoryJpa blocoRepository;

    public AgendaService(AgendaRepositoryJpa agendaRepository,
                         UsuarioRepositoryJpa usuarioRepository,
                         BlocoRepositoryJpa blocoRepository) {
        this.agendaRepository = agendaRepository;
        this.usuarioRepository = usuarioRepository;
        this.blocoRepository = blocoRepository;
    }

    public Agenda save(AgendaRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + dto.usuarioId()));
        Bloco bloco = blocoRepository.findById(dto.blocoId())
                .orElseThrow(() -> new IllegalArgumentException("Bloco não encontrado: " + dto.blocoId()));
        Agenda agenda = new Agenda();
        agenda.setUsuario(usuario);
        agenda.setBloco(bloco);
        return agendaRepository.save(agenda);
    }

    public List<Agenda> findAll() {
        return agendaRepository.findAll();
    }

}
