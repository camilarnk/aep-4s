// PublicacaoDTO usado no GET e nas respostas para enviar dados ao frontend (saída)

package com.ocupamais.dto;

import java.sql.Timestamp;

public class PublicacaoDTO {
    private int id;
    private String nomeUsuario;
    private String nomeEspaco;
    private String descricao;
    private String status;
    private Timestamp dataCriacao;

    public PublicacaoDTO(int id, String nomeUsuario, String nomeEspaco, String descricao,
        String status, Timestamp dataCriacao) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.nomeEspaco = nomeEspaco;
        this.descricao = descricao;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    public int getId() {
        return id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getNomeEspaco() {
        return nomeEspaco;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }
}