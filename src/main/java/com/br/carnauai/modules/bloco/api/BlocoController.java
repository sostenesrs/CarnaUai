package com.br.carnauai.modules.bloco.api;

import com.br.carnauai.modules.bloco.api.dto.BlocoRequestDTO;
import com.br.carnauai.modules.bloco.api.dto.BlocoResponseDTO;
import com.br.carnauai.modules.bloco.application.BlocoService;
import com.br.carnauai.modules.bloco.domain.Bloco;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/blocos")
public class BlocoController {

    private final BlocoService blocoService;
    private final BlocoMapper blocoMapper;

    public BlocoController(BlocoService blocoService, BlocoMapper blocoMapper) {
        this.blocoService = blocoService;
        this.blocoMapper = blocoMapper;
    }

    @PostMapping
    public ResponseEntity<BlocoResponseDTO> create(@Valid @RequestBody BlocoRequestDTO dto) {

        Bloco created = blocoService.create(dto);

        return ResponseEntity.created(URI.create("/api/blocos/" + created.getId())).body(blocoMapper.toResponseDTO(created));
    }

    @GetMapping
    public ResponseEntity<List<BlocoResponseDTO>> getAll(
            @RequestParam(value = "bairroId", required = false) UUID bairroId,
            @RequestParam(value = "data", required = false)
            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data
    ) {
        List<Bloco> blocos;

        if (data != null) {

            blocos = blocoService.findByData(data);

        } else if (bairroId != null) {

            blocos = blocoService.findByBairroId(bairroId);

        } else {

            blocos = blocoService.findAll();
        }

        return ResponseEntity.ok(blocos.stream().map(blocoMapper::toResponseDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlocoResponseDTO> getById(@PathVariable UUID id) {

        return blocoService.findById(id).map(blocoMapper::toResponseDTO).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
