// PublicacaoDTO usado no GET e nas respostas para enviar dados ao frontend (saída)

package com.ocupamais.dto;

import java.time.LocalDateTime;

public class PublicacaoDTO {
    private int id;
    private int totalApoios;
    private String nomeUsuario;
    private String nomeEspaco;
    private String descricao;
    private String status;
    private LocalDateTime dataCriacao;

    public PublicacaoDTO(int id, int totalApoios, String nomeUsuario, String nomeEspaco, String descricao,
        String status, LocalDateTime  dataCriacao) {
        this.id = id;
        this.totalApoios = totalApoios;
        this.nomeUsuario = nomeUsuario;
        this.nomeEspaco = nomeEspaco;
        this.descricao = descricao;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    public int getId() {
        return id;
    }

    public int getTotalApoios() {
        return totalApoios;
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}