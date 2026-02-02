package com.br.carnauai.modules.agenda.domain;

import com.br.carnauai.modules.bloco.domain.BlocoDia;
import com.br.carnauai.shared.kernel.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "agenda", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "bloco_dia_id"}))
public class Agenda {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "bloco_dia_id", nullable = false)
    private BlocoDia blocoDia;

    @CreationTimestamp
    @Column(name = "dth_criacao", nullable = false, updatable = false)
    private LocalDateTime dthCriacao;

    public Agenda() {
    }

    public Agenda(UUID id, Usuario usuario, BlocoDia blocoDia) {
        this.id = id;
        this.usuario = usuario;
        this.blocoDia = blocoDia;
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

    public BlocoDia getBlocoDia() {
        return blocoDia;
    }

    public void setBlocoDia(BlocoDia blocoDia) {
        this.blocoDia = blocoDia;
    }

    public LocalDateTime getDthCriacao() {
        return dthCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agenda)) return false;
        Agenda agenda = (Agenda) o;
        return Objects.equals(id, agenda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
