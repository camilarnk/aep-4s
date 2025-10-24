package com.ocupamais.dto;

public class PublicacaoCreateDTO {
    private int usuarioId;
    private int espacoPublicoId;
    private String descricao;

    public PublicacaoCreateDTO() {}

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getEspacoPublicoId() {
        return espacoPublicoId;
    }

    public void setEspacoPublicoId(int espacoPublicoId) {
        this.espacoPublicoId = espacoPublicoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}