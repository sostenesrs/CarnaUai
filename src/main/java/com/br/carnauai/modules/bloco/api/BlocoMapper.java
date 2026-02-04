package com.br.carnauai.modules.bloco.api;

import com.br.carnauai.modules.bloco.api.dto.BlocoResponseDTO;
import com.br.carnauai.modules.bloco.api.dto.EnderecoResponseDTO;
import com.br.carnauai.modules.bloco.domain.Bloco;
import com.br.carnauai.modules.bloco.domain.Endereco;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlocoMapper {

    public EnderecoResponseDTO toEnderecoResponseDTO(Endereco endereco) {
        if (endereco == null) return null;
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getLatitude(),
                endereco.getLongitude()
        );
    }

    public BlocoResponseDTO toResponseDTO(Bloco bloco) {
        if (bloco == null) return null;
        List<String> estilos = bloco.getEstilosMusicais() == null
                ? List.of()
                : bloco.getEstilosMusicais().stream().map(e -> e.getNome()).collect(Collectors.toList());
        List<String> perfis = bloco.getPerfisPublico() == null
                ? List.of()
                : bloco.getPerfisPublico().stream().map(p -> p.getNome()).collect(Collectors.toList());
        return new BlocoResponseDTO(
                bloco.getId(),
                bloco.getIdExterno(),
                bloco.getOid(),
                bloco.getNome(),
                bloco.getTema(),
                bloco.getData(),
                bloco.getHorario(),
                bloco.getHorarioFim(),
                bloco.getDiaSemana(),
                bloco.getTamanho(),
                bloco.getTipoEvento(),
                bloco.getCategoriaEvento(),
                bloco.getStatusVerificacao(),
                bloco.getOficialPrefeitura(),
                bloco.getImagemUrl(),
                bloco.getDestaque(),
                toEnderecoResponseDTO(bloco.getEnderecoSaida()),
                toEnderecoResponseDTO(bloco.getEnderecoDispersao()),
                bloco.getTotalFavoritos() != null ? bloco.getTotalFavoritos() : 0,
                estilos,
                perfis,
                bloco.getDtCriacao(),
                bloco.getDtAtualizacao()
        );
    }
}
