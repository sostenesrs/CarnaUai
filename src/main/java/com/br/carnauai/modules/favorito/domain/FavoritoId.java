package com.br.carnauai.modules.favorito.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class FavoritoId implements Serializable {
    private UUID usuarioId;
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
        if (o == null || getClass() != o.getClass()) return false;
        FavoritoId that = (FavoritoId) o;
        return usuarioId.equals(that.usuarioId) && blocoId.equals(that.blocoId);
    }

    @Override
    public int hashCode() {
        return usuarioId.hashCode() ^ blocoId.hashCode();
    }
}
