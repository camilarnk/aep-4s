package com.ocupamais.dto;

public class ApoioCreateDTO {
    private int usuarioId;
    private int publicacaoId;

    public ApoioCreateDTO() {}

    public ApoioCreateDTO(int usuarioId, int publicacaoId) {
        this.usuarioId = usuarioId;
        this.publicacaoId = publicacaoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getPublicacaoId() {
        return publicacaoId;
    }

    public void setPublicacaoId(int publicacaoId) {
        this.publicacaoId = publicacaoId;
    }
}