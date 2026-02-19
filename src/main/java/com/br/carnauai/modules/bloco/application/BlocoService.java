package com.br.carnauai.modules.bloco.application;

import com.br.carnauai.modules.bloco.api.dto.BlocoRequestDTO;
import com.br.carnauai.modules.bloco.domain.Bloco;
import com.br.carnauai.modules.bloco.domain.Endereco;
import com.br.carnauai.modules.bloco.domain.EstiloMusical;
import com.br.carnauai.modules.bloco.domain.PerfilPublico;
import com.br.carnauai.modules.bloco.infrastructure.BlocoRepositoryJpa;
import com.br.carnauai.modules.bloco.infrastructure.EnderecoRepositoryJpa;
import com.br.carnauai.modules.bloco.infrastructure.EstiloMusicalRepositoryJpa;
import com.br.carnauai.modules.bloco.infrastructure.PerfilPublicoRepositoryJpa;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class BlocoService {

    private final BlocoRepositoryJpa blocoRepository;
    private final EnderecoRepositoryJpa enderecoRepository;
    private final EstiloMusicalRepositoryJpa estiloMusicalRepository;
    private final PerfilPublicoRepositoryJpa perfilPublicoRepository;

    public BlocoService(BlocoRepositoryJpa blocoRepository,
                        EnderecoRepositoryJpa enderecoRepository,
                        EstiloMusicalRepositoryJpa estiloMusicalRepository,
                        PerfilPublicoRepositoryJpa perfilPublicoRepository) {
        this.blocoRepository = blocoRepository;
        this.enderecoRepository = enderecoRepository;
        this.estiloMusicalRepository = estiloMusicalRepository;
        this.perfilPublicoRepository = perfilPublicoRepository;
    }

    public Bloco create(BlocoRequestDTO dto) {
        Bloco bloco = new Bloco();
        bloco.setIdExterno(dto.idExterno());
        bloco.setOid(dto.oid());
        bloco.setNome(dto.nome());
        bloco.setTema(dto.tema());
        bloco.setData(dto.data());
        bloco.setHorario(dto.horario());
        bloco.setHorarioFim(dto.horarioFim());
        bloco.setDiaSemana(dto.diaSemana());
        bloco.setTamanho(dto.tamanho());
        bloco.setTipoEvento(dto.tipoEvento());
        bloco.setCategoriaEvento(dto.categoriaEvento());
        bloco.setStatusVerificacao(dto.statusVerificacao());
        bloco.setOficialPrefeitura(dto.oficialPrefeitura() != null ? dto.oficialPrefeitura() : false);
        bloco.setImagemUrl(dto.imagemUrl());
        bloco.setDestaque(dto.destaque() != null ? dto.destaque() : false);

        if (dto.enderecoSaidaId() != null) {
            Endereco saida = enderecoRepository.findById(dto.enderecoSaidaId())
                    .orElseThrow(() -> new IllegalArgumentException("Endereço de saída não encontrado: " + dto.enderecoSaidaId()));
            bloco.setEnderecoSaida(saida);
        }
        if (dto.enderecoDispersaoId() != null) {
            Endereco dispersao = enderecoRepository.findById(dto.enderecoDispersaoId())
                    .orElseThrow(() -> new IllegalArgumentException("Endereço de dispersão não encontrado: " + dto.enderecoDispersaoId()));
            bloco.setEnderecoDispersao(dispersao);
        }

        if (dto.estiloMusicalIds() != null && !dto.estiloMusicalIds().isEmpty()) {
            Set<EstiloMusical> estilos = new HashSet<>(estiloMusicalRepository.findAllById(dto.estiloMusicalIds()));
            bloco.setEstilosMusicais(estilos);
        }
        if (dto.perfilPublicoIds() != null && !dto.perfilPublicoIds().isEmpty()) {
            Set<PerfilPublico> perfis = new HashSet<>(perfilPublicoRepository.findAllById(dto.perfilPublicoIds()));
            bloco.setPerfisPublico(perfis);
        }

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

    public List<Bloco> findByData(LocalDate data) {
        return blocoRepository.findByData(data);
    }

    /** Lista outros eventos do mesmo bloco (mesmo oid) em datas a partir de fromDate. */
    public List<Bloco> nextDaysForBloco(UUID blocoId, LocalDate fromDate) {
        return blocoRepository.findById(blocoId)
                .map(b -> blocoRepository.findByOidAndDataGreaterThanEqualAndIdNot(b.getOid(), fromDate, blocoId))
                .orElse(List.of());
    }

    public List<Bloco> findWithFilters(LocalDate data, String bairro, String nome) {
        String bairroFiltro = (bairro != null && !bairro.isBlank()) ? bairro : null;
        String nomeFiltro = (nome != null && !nome.isBlank()) ? nome : null;
        return blocoRepository.findWithFilters(data, bairroFiltro, nomeFiltro);
    }
}
