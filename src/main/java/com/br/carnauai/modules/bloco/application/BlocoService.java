package com.br.carnauai.modules.bloco.application;

import com.br.carnauai.modules.bloco.api.dto.BlocoRequestDTO;
import com.br.carnauai.modules.bloco.domain.Bairro;
import com.br.carnauai.modules.bloco.domain.Bloco;
import com.br.carnauai.modules.bloco.domain.BlocoDia;
import com.br.carnauai.modules.bloco.infrastructure.BairroRepositoryJpa;
import com.br.carnauai.modules.bloco.infrastructure.BlocoDiaRepositoryJpa;
import com.br.carnauai.modules.bloco.infrastructure.BlocoRepositoryJpa;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BlocoService {

    private final BlocoRepositoryJpa blocoRepository;
    private final BlocoDiaRepositoryJpa blocoDiaRepository;
    private final BairroRepositoryJpa bairroRepository;

    public BlocoService(BlocoRepositoryJpa blocoRepository, BlocoDiaRepositoryJpa blocoDiaRepository, BairroRepositoryJpa bairroRepository) {
        this.blocoRepository = blocoRepository;
        this.blocoDiaRepository = blocoDiaRepository;
        this.bairroRepository = bairroRepository;
    }

    public Bloco create(BlocoRequestDTO dto) {
        Bairro bairro = bairroRepository.findById(dto.bairroId())
                .orElseThrow(() -> new IllegalArgumentException("Bairro não encontrado: " + dto.bairroId()));
        Bloco bloco = new Bloco();
        bloco.setNome(dto.nome());
        bloco.setDescricao(dto.descricao());
        bloco.setBairro(bairro);
        bloco.setLatitude(dto.latitude());
        bloco.setLongitude(dto.longitude());
        bloco.setAtivo(dto.ativo() != null ? dto.ativo() : true);
        return blocoRepository.save(bloco);
    }

    public Bloco create(Bloco bloco) {
        return blocoRepository.save(bloco);
    }

    public Optional<Bloco> findById(UUID id) {
        return blocoRepository.findById(id);
    }

    public List<Bloco> findAll() {
        return blocoRepository.findAll();
    }

    public void delete(UUID id) {
        blocoRepository.deleteById(id);
    }

    public List<BlocoDia> listByDay(LocalDate data) {
        return blocoDiaRepository.findByDataEvento(data);
    }

    public List<Bloco> findByBairroId(UUID bairroId) {
        return blocoRepository.findAll().stream()
                .filter(b -> b.getBairro() != null && bairroId.equals(b.getBairro().getId()))
                .collect(Collectors.toList());
    }

    public List<BlocoDia> nextDaysForBloco(UUID blocoId, LocalDate fromDate) {
        return blocoDiaRepository.findAll().stream()
                .filter(d -> d.getBloco() != null && blocoId.equals(d.getBloco().getId()) && !d.getDataEvento().isBefore(fromDate))
                .collect(Collectors.toList());
    }
}
