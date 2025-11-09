package com.ocupamais.service;

import com.ocupamais.dao.ApoioDAO;
import com.ocupamais.model.Apoio;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApoioService {

    private final ApoioDAO apoioDAO = new ApoioDAO();

    public void cadastrar(Apoio apoio) {
        apoioDAO.inserir(apoio);
    }

    public List<Apoio> listarTodos() {
        return apoioDAO.listar();
    }

    // Deletar por usuario + publicacao
    public boolean deletarPorUsuarioEPublicacao(int idUsuario, int idPublicacao) {
        return apoioDAO.deletarPorUsuarioEPublicacao(idUsuario, idPublicacao);
    }
}