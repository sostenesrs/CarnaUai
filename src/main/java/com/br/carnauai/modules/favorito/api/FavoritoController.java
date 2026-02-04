package com.br.carnauai.modules.favorito.api;

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
@RequestMapping("/api/usuarios/{usuarioId}/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;
    private final FavoritoMapper favoritoMapper;

    public FavoritoController(FavoritoService favoritoService, FavoritoMapper favoritoMapper) {
        this.favoritoService = favoritoService;
        this.favoritoMapper = favoritoMapper;
    }

    @PostMapping
    public ResponseEntity<FavoritoResponseDTO> create(@PathVariable UUID usuarioId, @Valid @RequestBody FavoritoRequestDTO dto) {
        Favorito favorito = favoritoService.save(new FavoritoRequestDTO(usuarioId, dto.blocoId()));

        return ResponseEntity.created(URI.create("/api/usuarios/" + usuarioId + "/favoritos/" + dto.blocoId())).body(favoritoMapper.toResponseDTO(favorito));
    }

    @GetMapping
    public ResponseEntity<List<FavoritoResponseDTO>> listByUsuario(@PathVariable UUID usuarioId) {
        List<FavoritoResponseDTO> list = favoritoService.listByUsuario(usuarioId).stream().map(favoritoMapper::toResponseDTO).toList();

        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{blocoId}")
    public ResponseEntity<Void> delete(@PathVariable UUID usuarioId, @PathVariable UUID blocoId) {
        favoritoService.delete(usuarioId, blocoId);
        return ResponseEntity.noContent().build();
    }
}
