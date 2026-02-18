package com.br.carnauai.modules.favorito.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class FavoritoId implements Serializable {

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    @Column(name = "bloco_id", nullable = false)
    private UUID blocoId;

    public FavoritoId() {
    }

    public FavoritoId(UUID usuarioId, UUID blocoId) {
        this.usuarioId = usuarioId;
        this.blocoId = blocoId;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UUID getBlocoId() {
        return blocoId;
    }

    public void setBlocoId(UUID blocoId) {
        this.blocoId = blocoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoritoId)) return false;
        FavoritoId that = (FavoritoId) o;
        return Objects.equals(usuarioId, that.usuarioId) && Objects.equals(blocoId, that.blocoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, blocoId);
    }
}
