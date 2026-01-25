package com.br.carnauai.bloco.service;

import com.br.carnauai.domain.bloco.Bloco;
import com.br.carnauai.domain.bloco_dia.BlocoDia;
import com.br.carnauai.infrastructure.persistence.bairro.BairroRepositoryJpa;
import com.br.carnauai.infrastructure.persistence.bloco.BlocoRepositoryJpa;
import com.br.carnauai.infrastructure.persistence.bloco_dia.BlocoDiaRepositoryJpa;
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
