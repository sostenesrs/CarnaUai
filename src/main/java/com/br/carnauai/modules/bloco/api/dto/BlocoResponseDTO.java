package com.br.carnauai.modules.bloco.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public record BlocoResponseDTO(
        UUID id,
        Long idExterno,
        String oid,
        String nome,
        String tema,
        LocalDate data,
        LocalTime horario,
        LocalTime horarioFim,
        String diaSemana,
        String tamanho,
        String tipoEvento,
        String categoriaEvento,
        String statusVerificacao,
        Boolean oficialPrefeitura,
        String imagemUrl,
        Boolean destaque,
        EnderecoResponseDTO enderecoSaida,
        EnderecoResponseDTO enderecoDispersao,
        Integer totalFavoritos,
        List<String> estilosMusicais,
        List<String> perfisPublico,
        LocalDateTime dtCriacao,
        LocalDateTime dtAtualizacao
) {
}
