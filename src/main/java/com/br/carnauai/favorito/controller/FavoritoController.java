package com.br.carnauai.favorito.controller;

import com.br.carnauai.domain.favorito.Favorito;
import com.br.carnauai.domain.favorito.FavoritoId;
import com.br.carnauai.favorito.service.FavoritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @PostMapping
    public ResponseEntity<Favorito> create(@RequestBody Favorito favorito) {
        Favorito created = favoritoService.save(favorito);
        return ResponseEntity.created(URI.create("/api/favoritos/" + created.getId())).body(created);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Favorito>> listByUsuario(@PathVariable UUID usuarioId) {
        return ResponseEntity.ok(favoritoService.listByUsuario(usuarioId));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody FavoritoId id) {
        favoritoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
