package com.ocupamais.service;

import com.ocupamais.dao.PublicacaoDAO;
import com.ocupamais.model.Publicacao;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PublicacaoService {

    private final PublicacaoDAO publicacaoDAO = new PublicacaoDAO();

    public void cadastrar(Publicacao publicacao) {
        publicacaoDAO.inserir(publicacao);
    }

    public List<Publicacao> listarTodos() {
        return publicacaoDAO.listar();
    }

    public Publicacao buscarPorId(int id) {
        return publicacaoDAO.buscarPorId(id);
    }

    public void atualizar(Publicacao publicacao) {
        publicacaoDAO.atualizar(publicacao);
    }

    public boolean deletar(int id) {
        return publicacaoDAO.deletar(id);
    }

    public Publicacao atualizarStatus(int id, String status) {
        Publicacao p = publicacaoDAO.buscarPorId(id);
        if (p != null) {
            p.setStatus(status);
            publicacaoDAO.atualizar(p);
        }
        return p;
    }
}