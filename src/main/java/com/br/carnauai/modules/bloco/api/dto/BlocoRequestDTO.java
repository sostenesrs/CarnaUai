package com.br.carnauai.modules.bloco.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

public record BlocoRequestDTO(
        @NotNull Long idExterno,
        @NotBlank String oid,
        @NotBlank String nome,
        String tema,
        @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate data,
        @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm") LocalTime horario,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm") LocalTime horarioFim,
        String diaSemana,
        String tamanho,
        String tipoEvento,
        String categoriaEvento,
        String statusVerificacao,
        Boolean oficialPrefeitura,
        String imagemUrl,
        Boolean destaque,
        UUID enderecoSaidaId,
        UUID enderecoDispersaoId,
        Set<UUID> estiloMusicalIds,
        Set<UUID> perfilPublicoIds
) {
}
