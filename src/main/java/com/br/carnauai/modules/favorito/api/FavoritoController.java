package com.br.carnauai.modules.favorito.api;

import com.br.carnauai.modules.favorito.api.dto.FavoritoDeleteDTO;
import com.br.carnauai.modules.favorito.api.dto.FavoritoRequestDTO;
import com.br.carnauai.modules.favorito.api.dto.FavoritoResponseDTO;
import com.br.carnauai.modules.favorito.application.FavoritoService;
import com.br.carnauai.modules.favorito.domain.Favorito;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;
    private final FavoritoMapper favoritoMapper;

    public FavoritoController(FavoritoService favoritoService, FavoritoMapper favoritoMapper) {
        this.favoritoService = favoritoService;
        this.favoritoMapper = favoritoMapper;
    }

    @PostMapping
    public ResponseEntity<FavoritoResponseDTO> create(@Valid @RequestBody FavoritoRequestDTO dto) {
        Favorito created = favoritoService.save(dto);
        return ResponseEntity.created(URI.create("/api/favoritos?usuarioId=" + created.getId().getUsuarioId() + "&blocoId=" + created.getId().getBlocoId()))
                .body(favoritoMapper.toResponseDTO(created));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<FavoritoResponseDTO>> listByUsuario(@PathVariable UUID usuarioId) {
        List<FavoritoResponseDTO> list = favoritoService.listByUsuario(usuarioId).stream()
                .map(favoritoMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@Valid @RequestBody FavoritoDeleteDTO dto) {
        favoritoService.delete(dto.usuarioId(), dto.blocoId());
        return ResponseEntity.noContent().build();
    }
}
