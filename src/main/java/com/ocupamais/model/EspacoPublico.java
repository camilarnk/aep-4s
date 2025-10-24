package com.ocupamais.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EspacoPublico {

    private int id;

    @NotBlank(message = "Nome do espaço é obrigatório")
    @Size(max = 100, message = "Nome do espaço deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "Localização é obrigatória")
    @Size(max = 200, message = "Localização deve ter no máximo 200 caracteres")
    private String localizacao;

    // construtor com id para objetos do banco
    public EspacoPublico(int id, String nome, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
    }

    // construtor sem id para inserção no banco
    public EspacoPublico(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public EspacoPublico(){       
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "EspacoPublico [id=" + id + ", nome=" + nome + ", localizacao=" + localizacao + "]";
    }
}