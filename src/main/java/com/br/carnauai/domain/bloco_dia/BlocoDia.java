package com.br.carnauai.domain.bloco_dia;

import com.br.carnauai.domain.bloco.Bloco;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "bloco_dia", uniqueConstraints = @UniqueConstraint(columnNames = {"bloco_id", "data_evento"}))
public class BlocoDia {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "bloco_id", nullable = false)
    private Bloco bloco;

    @Column(name = "data_evento", nullable = false)
    private LocalDate dataEvento;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    @CreationTimestamp
    @Column(name = "dth_criacao", nullable = false, updatable = false)
    private LocalDateTime dthCriacao;

    public BlocoDia() {
    }

    public BlocoDia(UUID id, Bloco bloco, LocalDate dataEvento, LocalTime horaInicio, LocalTime horaFim) {
        this.id = id;
        this.bloco = bloco;
        this.dataEvento = dataEvento;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public LocalDateTime getDthCriacao() {
        return dthCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlocoDia)) return false;
        BlocoDia blocoDia = (BlocoDia) o;
        return Objects.equals(id, blocoDia.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
