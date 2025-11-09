package com.ocupamais.model;

public class Apoio {
    private Usuario usuario; // Obrigatório, validado no DTO ou no Controller
    private Publicacao publicacao; // Obrigatório, validado no DTO ou no Controller

    public Apoio(Usuario usuario, Publicacao publicacao) {
        this.usuario = usuario;
        this.publicacao = publicacao;
    }

    public Apoio() {
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    @Override
    public String toString() {
        return "Usuario=" + (usuario != null ? usuario.getNome() : "N/A") +
            ", publicacao=" + (publicacao != null ? publicacao.getDescricao() : "N/A") + "]";
    }

}