package com.ocupamais.dto;

public class ApoioDTO {
    private int id;
    private String nomeUsuario;
    private String descricaoPublicacao;

    public ApoioDTO() {}

    public ApoioDTO(int id, String nomeUsuario, String descricaoPublicacao) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.descricaoPublicacao = descricaoPublicacao;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getDescricaoPublicacao() {
        return descricaoPublicacao;
    }

    public void setDescricaoPublicacao(String descricaoPublicacao) {
        this.descricaoPublicacao = descricaoPublicacao;
    }
}