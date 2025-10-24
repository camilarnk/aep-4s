package com.ocupamais.dto;

public class PublicacaoDTO {
    private int id;
    private String nomeUsuario;
    private String nomeEspaco;
    private String descricao;

    public PublicacaoDTO(int id, String nomeUsuario, String nomeEspaco, String descricao) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.nomeEspaco = nomeEspaco;
        this.descricao = descricao;
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
}