package com.br.carnauai.modules.bloco.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "bloco")
public class Bloco {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "id_externo", unique = true, nullable = false)
    private Long idExterno;

    @Column(unique = true, nullable = false, length = 20)
    private String oid;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(length = 255)
    private String tema;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime horario;

    @Column(name = "horario_fim")
    private LocalTime horarioFim;

    @Column(name = "dia_semana", length = 15)
    private String diaSemana;

    @Column(length = 50)
    private String tamanho;

    @Column(name = "tipo_evento", length = 50)
    private String tipoEvento;

    @Column(name = "categoria_evento", length = 50)
    private String categoriaEvento;

    @Column(name = "status_verificacao", length = 50)
    private String statusVerificacao;

    @Column(name = "oficial_prefeitura", nullable = false)
    private Boolean oficialPrefeitura = false;

    @Column(name = "imagem_url", columnDefinition = "text")
    private String imagemUrl;

    @Column(nullable = false)
    private Boolean destaque = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_saida_id")
    private Endereco enderecoSaida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_dispersao_id")
    private Endereco enderecoDispersao;

    @Column(name = "total_favoritos", nullable = false)
    private Integer totalFavoritos = 0;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "bloco_estilo_musical",
            joinColumns = @JoinColumn(name = "bloco_id"),
            inverseJoinColumns = @JoinColumn(name = "estilo_id")
    )
    private Set<EstiloMusical> estilosMusicais = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "bloco_perfil_publico",
            joinColumns = @JoinColumn(name = "bloco_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private Set<PerfilPublico> perfisPublico = new HashSet<>();

    @CreationTimestamp
    @Column(name = "dt_criacao", nullable = false, updatable = false)
    private LocalDateTime dtCriacao;

    @UpdateTimestamp
    @Column(name = "dt_atualizacao")
    private LocalDateTime dtAtualizacao;

    public Bloco() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(Long idExterno) {
        this.idExterno = idExterno;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getCategoriaEvento() {
        return categoriaEvento;
    }

    public void setCategoriaEvento(String categoriaEvento) {
        this.categoriaEvento = categoriaEvento;
    }

    public String getStatusVerificacao() {
        return statusVerificacao;
    }

    public void setStatusVerificacao(String statusVerificacao) {
        this.statusVerificacao = statusVerificacao;
    }

    public Boolean getOficialPrefeitura() {
        return oficialPrefeitura;
    }

    public void setOficialPrefeitura(Boolean oficialPrefeitura) {
        this.oficialPrefeitura = oficialPrefeitura;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public Boolean getDestaque() {
        return destaque;
    }

    public void setDestaque(Boolean destaque) {
        this.destaque = destaque;
    }

    public Endereco getEnderecoSaida() {
        return enderecoSaida;
    }

    public void setEnderecoSaida(Endereco enderecoSaida) {
        this.enderecoSaida = enderecoSaida;
    }

    public Endereco getEnderecoDispersao() {
        return enderecoDispersao;
    }

    public void setEnderecoDispersao(Endereco enderecoDispersao) {
        this.enderecoDispersao = enderecoDispersao;
    }

    public Integer getTotalFavoritos() {
        return totalFavoritos;
    }

    public void setTotalFavoritos(Integer totalFavoritos) {
        this.totalFavoritos = totalFavoritos;
    }

    public Set<EstiloMusical> getEstilosMusicais() {
        return estilosMusicais;
    }

    public void setEstilosMusicais(Set<EstiloMusical> estilosMusicais) {
        this.estilosMusicais = estilosMusicais;
    }

    public Set<PerfilPublico> getPerfisPublico() {
        return perfisPublico;
    }

    public void setPerfisPublico(Set<PerfilPublico> perfisPublico) {
        this.perfisPublico = perfisPublico;
    }

    public LocalDateTime getDtCriacao() {
        return dtCriacao;
    }

    public LocalDateTime getDtAtualizacao() {
        return dtAtualizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bloco)) return false;
        Bloco bloco = (Bloco) o;
        return Objects.equals(id, bloco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
