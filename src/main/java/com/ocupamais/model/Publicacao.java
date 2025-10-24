package com.ocupamais.model;

import java.sql.Timestamp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Publicacao {

    private int id;

    private Usuario usuario; // Obrigatório, validado no DTO ou no Controller

    private EspacoPublico espaco; // Obrigatório, validado no DTO ou no Controller

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    private String descricao;

    private Timestamp dataCriacao;

    private String status; // PENDENTE, EM_ANALISE, RESOLVIDO

    // construtor com id
    public Publicacao(int id, Usuario usuario, EspacoPublico espaco, String descricao, Timestamp dataCriacao, String status) {
        this.id = id;
        this.usuario = usuario;
        this.espaco = espaco;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.status = status;
    }


    // construtor sem id
    public Publicacao(Usuario usuario, EspacoPublico espaco, String descricao) {
        this.usuario = usuario;
        this.espaco = espaco;
        this.descricao = descricao;
        this.status = "PENDENTE";
        this.dataCriacao = new Timestamp(System.currentTimeMillis());
    }


    public Publicacao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EspacoPublico getEspaco() {
        return espaco;
    }

    public void setEspaco(EspacoPublico espaco) {
        this.espaco = espaco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Publicacao [id=" + id +
            ", usuario=" + (usuario != null ? usuario.getNome() : "N/A") +
            ", espaco=" + (espaco != null ? espaco.getNome() : "N/A") +
            ", descricao=" + descricao +
            ", status=" + status + "]";
    }

}