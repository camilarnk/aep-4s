package com.ocupamais.model;

public class Apoio {
    private int id;
    private Usuario usuario; // Obrigatório, validado no DTO ou no Controller
    private Publicacao publicacao; // Obrigatório, validado no DTO ou no Controller

    // construtor com id
    public Apoio(int id, Usuario usuario, Publicacao publicacao) {
        this.id = id;
        this.usuario = usuario;
        this.publicacao = publicacao;
    }

    // construtor sem id
    public Apoio(Usuario usuario, Publicacao publicacao) {
        this.usuario = usuario;
        this.publicacao = publicacao;
    }

    public Apoio() {
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

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    @Override
    public String toString() {
        return "Apoio [id=" + id +
            ", usuario=" + (usuario != null ? usuario.getNome() : "N/A") +
            ", publicacao=" + (publicacao != null ? publicacao.getDescricao() : "N/A") + "]";
    }

}