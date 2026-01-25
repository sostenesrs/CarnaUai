package com.br.carnauai.bloco.controller;

import com.br.carnauai.bloco.service.BlocoService;
import com.br.carnauai.domain.bloco.Bloco;
import com.br.carnauai.domain.bloco_dia.BlocoDia;
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

    public BlocoController(BlocoService blocoService) {
        this.blocoService = blocoService;
    }

    @PostMapping
    public ResponseEntity<Bloco> create(@RequestBody Bloco bloco) {
        Bloco created = blocoService.create(bloco);
        return ResponseEntity.created(URI.create("/api/blocos/" + created.getId())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Bloco>> getAll(@RequestParam(value = "bairroId", required = false) UUID bairroId) {
        if (bairroId != null) {
            return ResponseEntity.ok(blocoService.findByBairroId(bairroId));
        }
        return ResponseEntity.ok(blocoService.findAll());
    }

    @GetMapping("/dia")
    public ResponseEntity<List<BlocoDia>> listByDay(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(blocoService.listByDay(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bloco> getById(@PathVariable UUID id) {
        return blocoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/detalhes")
    public ResponseEntity<DetalheBloco> detalhes(@PathVariable UUID id,
                                                 @RequestParam(value = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from) {
        LocalDate start = (from == null) ? LocalDate.now() : from;
        return blocoService.findById(id)
                .map(b -> {
                    List<BlocoDia> proximos = blocoService.nextDaysForBloco(id, start);
                    DetalheBloco detalhe = new DetalheBloco(b, proximos);
                    return ResponseEntity.ok(detalhe);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        blocoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    public static class DetalheBloco {
        private Bloco bloco;
        private List<BlocoDia> proximosDias;

        public DetalheBloco(Bloco bloco, List<BlocoDia> proximosDias) {
            this.bloco = bloco;
            this.proximosDias = proximosDias;
        }

        public Bloco getBloco() {
            return bloco;
        }

        public List<BlocoDia> getProximosDias() {
            return proximosDias;
        }
    }
}
