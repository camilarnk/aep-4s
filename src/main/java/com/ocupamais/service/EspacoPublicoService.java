package com.ocupamais.service;

import com.ocupamais.dao.EspacoPublicoDAO;
import com.ocupamais.model.EspacoPublico;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EspacoPublicoService {

    private final EspacoPublicoDAO espacoDAO = new EspacoPublicoDAO();

    public void cadastrar(EspacoPublico espaco) {
        espacoDAO.inserir(espaco);
    }

    public List<EspacoPublico> listarTodos() {
        return espacoDAO.listar();
    }

    public EspacoPublico buscarPorId(int id) {
        return espacoDAO.buscarPorId(id);
    }

    public void atualizar(EspacoPublico espaco) {
        espacoDAO.atualizar(espaco);
    }

    public boolean deletar(int id) {
        return espacoDAO.deletar(id);
    }
}