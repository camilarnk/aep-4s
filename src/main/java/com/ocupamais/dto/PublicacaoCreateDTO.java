// PublicacaoCreateDTO usado no POST/PUT para receber dados do frontend (entrada)
// quando o usuário cria uma nova publicação, contém apenas os dados necessários
// para criar ou atualizar: id do usuário, nome do espaço e a descrição do post.

package com.ocupamais.dto;

public class PublicacaoCreateDTO {
    private int usuarioId;
    private String nomeEspaco;
    private String descricao;
    private String status;
    private String imagem;

    public PublicacaoCreateDTO() {}

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNomeEspaco() {
        return nomeEspaco;
    }

    public void setNomeEspaco(String nomeEspaco) {
        this.nomeEspaco = nomeEspaco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}