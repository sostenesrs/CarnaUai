package com.br.carnauai.modules.favorito.domain;

import com.br.carnauai.modules.bloco.domain.Bloco;
import com.br.carnauai.shared.kernel.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "favorito", indexes = @Index(name = "idx_favorito_usuario", columnList = "usuario_id"))
public class Favorito {

    @EmbeddedId
    private FavoritoId id;

    @MapsId("usuarioId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @MapsId("blocoId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "bloco_id", nullable = false)
    private Bloco bloco;
    
    @Column(name = "favoritado", nullable = false)
    private Boolean favoritado = true;

    @CreationTimestamp
    @Column(name = "dth_criacao", nullable = false, updatable = false)
    private LocalDateTime dthCriacao;

    @UpdateTimestamp
    @Column(name = "dth_atualizacao", nullable = false)
    private LocalDateTime dthAtualizacao;

    public Favorito() {
    }

    public Favorito(FavoritoId id, Usuario usuario, Bloco bloco) {
        this.id = id;
        this.usuario = usuario;
        this.bloco = bloco;
    }

    public FavoritoId getId() {
        return id;
    }

    public void setId(FavoritoId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    public LocalDateTime getDthCriacao() {
        return dthCriacao;
    }

    public LocalDateTime getDthAtualizacao() {
        return dthAtualizacao;
    }

    public Boolean getFavoritado() {
        return favoritado;
    }

    public void setFavoritado(Boolean favoritado) {
        this.favoritado = favoritado;
        // update timestamp in memory as a hint; the @UpdateTimestamp will normally handle it on flush
        this.dthAtualizacao = LocalDateTime.now();
    }

    public void setDthCriacao(LocalDateTime dthCriacao) {
        this.dthCriacao = dthCriacao;
    }

    public void setDthAtualizacao(LocalDateTime dthAtualizacao) {
        this.dthAtualizacao = dthAtualizacao;
    }

    /**
     * Mark this favorito as not favorited without deleting the row.
     */
    public void unfavoritar() {
        this.favoritado = false;
        this.dthAtualizacao = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Favorito)) return false;
        Favorito favorito = (Favorito) o;
        return Objects.equals(id, favorito.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
