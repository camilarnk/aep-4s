// ApoioCreateDTO usado no POST, só carrega os IDs (entrada)
// ApoioDTO usado no GET e resposta, exibe nome e descrição (saída)

package com.ocupamais.dto;

public class ApoioDTO {
    private String nomeUsuario;
    private String descricaoPublicacao;

    public ApoioDTO() {}

    public ApoioDTO(String nomeUsuario, String descricaoPublicacao) {
        this.nomeUsuario = nomeUsuario;
        this.descricaoPublicacao = descricaoPublicacao;
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