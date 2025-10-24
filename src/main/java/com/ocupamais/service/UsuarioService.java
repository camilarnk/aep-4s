package com.ocupamais.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ocupamais.dao.UsuarioDAO;
import com.ocupamais.model.Usuario;

@Service
public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void cadastrar(Usuario usuario) {
        usuarioDAO.inserir(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioDAO.listar();
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioDAO.buscarPorEmail(email);
    }

    public Usuario buscarPorId(int id) {
        return usuarioDAO.buscarPorId(id);
    }

    public void atualizar(Usuario usuario) {
        usuarioDAO.atualizar(usuario);
    }

    public boolean deletar(int id) {
        return usuarioDAO.deletar(id);
    }

}
