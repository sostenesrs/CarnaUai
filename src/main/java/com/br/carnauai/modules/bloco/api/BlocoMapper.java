package com.br.carnauai.modules.bloco.api;

import com.br.carnauai.modules.bloco.api.dto.BairroResponseDTO;
import com.br.carnauai.modules.bloco.api.dto.BlocoDiaResponseDTO;
import com.br.carnauai.modules.bloco.api.dto.BlocoResponseDTO;
import com.br.carnauai.modules.bloco.domain.Bairro;
import com.br.carnauai.modules.bloco.domain.Bloco;
import com.br.carnauai.modules.bloco.domain.BlocoDia;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlocoMapper {

    public BlocoResponseDTO toResponseDTO(Bloco bloco) {
        if (bloco == null) return null;
        Bairro bairro = bloco.getBairro();
        return new BlocoResponseDTO(
                bloco.getId(),
                bloco.getNome(),
                bloco.getDescricao(),
                bairro != null ? bairro.getId() : null,
                bairro != null ? bairro.getNome() : null,
                bairro != null ? bairro.getCidade() : null,
                bloco.getLatitude(),
                bloco.getLongitude(),
                bloco.getAtivo(),
                bloco.getDthCriacao()
        );
    }

    public BlocoDiaResponseDTO toResponseDTO(BlocoDia blocoDia) {
        if (blocoDia == null) return null;
        Bloco bloco = blocoDia.getBloco();
        return new BlocoDiaResponseDTO(
                blocoDia.getId(),
                bloco != null ? bloco.getId() : null,
                blocoDia.getDataEvento(),
                blocoDia.getHoraInicio(),
                blocoDia.getHoraFim()
        );
    }

    public BairroResponseDTO toResponseDTO(Bairro bairro) {
        if (bairro == null) return null;
        return new BairroResponseDTO(bairro.getId(), bairro.getNome(), bairro.getCidade(), bairro.getEstado());
    }

    public List<BlocoDiaResponseDTO> toBlocoDiaResponseDTOList(List<BlocoDia> list) {
        if (list == null) return List.of();
        return list.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }
}
