package com.br.carnauai.modules.agenda.application;

import com.br.carnauai.modules.agenda.api.dto.AgendaRequestDTO;
import com.br.carnauai.modules.agenda.domain.Agenda;
import com.br.carnauai.modules.agenda.infrastructure.AgendaRepositoryJpa;
import com.br.carnauai.modules.bloco.domain.BlocoDia;
import com.br.carnauai.modules.bloco.infrastructure.BlocoDiaRepositoryJpa;
import com.br.carnauai.shared.infrastructure.persistence.UsuarioRepositoryJpa;
import com.br.carnauai.shared.kernel.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgendaService {

    private final AgendaRepositoryJpa agendaRepository;
    private final UsuarioRepositoryJpa usuarioRepository;
    private final BlocoDiaRepositoryJpa blocoDiaRepository;

    public AgendaService(AgendaRepositoryJpa agendaRepository,
                         UsuarioRepositoryJpa usuarioRepository,
                         BlocoDiaRepositoryJpa blocoDiaRepository) {
        this.agendaRepository = agendaRepository;
        this.usuarioRepository = usuarioRepository;
        this.blocoDiaRepository = blocoDiaRepository;
    }

    public Agenda save(AgendaRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + dto.usuarioId()));
        BlocoDia blocoDia = blocoDiaRepository.findById(dto.blocoDiaId())
                .orElseThrow(() -> new IllegalArgumentException("BlocoDia não encontrado: " + dto.blocoDiaId()));
        Agenda agenda = new Agenda();
        agenda.setUsuario(usuario);
        agenda.setBlocoDia(blocoDia);
        return agendaRepository.save(agenda);
    }

    public List<Agenda> findAll() {
        return agendaRepository.findAll();
    }

    public void delete(UUID id) {
        agendaRepository.deleteById(id);
    }
}
