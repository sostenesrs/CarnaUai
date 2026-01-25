package com.br.carnauai.domain.notificacao;

import com.br.carnauai.domain.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(length = 150, nullable = false)
    private String titulo;

    @Column(columnDefinition = "text", nullable = false)
    private String mensagem;

    @Column(nullable = false)
    private Boolean lida = false;

    @CreationTimestamp
    @Column(name = "dth_criacao", nullable = false, updatable = false)
    private LocalDateTime dthCriacao;

    public Notificacao() {
    }

    public Notificacao(UUID id, Usuario usuario, String titulo, String mensagem, Boolean lida) {
        this.id = id;
        this.usuario = usuario;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.lida = lida;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Boolean getLida() {
        return lida;
    }

    public void setLida(Boolean lida) {
        this.lida = lida;
    }

    public LocalDateTime getDthCriacao() {
        return dthCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notificacao)) return false;
        Notificacao that = (Notificacao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
