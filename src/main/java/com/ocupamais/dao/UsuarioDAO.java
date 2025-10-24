package com.ocupamais.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ocupamais.model.Usuario;

import util.Conexao;

public class UsuarioDAO {

    // inserir um usuario no banco de dados
    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (nome, email, senha, tipo, bairro) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = util.Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipo());
            stmt.setString(5, usuario.getBairro());

            stmt.executeUpdate();

            // captura o id gerado pelo AUTO_INCREMENT
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // listar todos os usuários
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";
        try (Connection conn = util.Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("tipo"),
                        rs.getString("bairro")
                );
                usuarios.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    // buscar usuário por email (usado para login)
    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM Usuarios WHERE email = ?";
        try (Connection conn = util.Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("tipo"),
                        rs.getString("bairro")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM Usuarios WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("tipo"),
                    rs.getString("bairro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // atualizar um usuário existente
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE Usuarios SET nome = ?, email = ?, senha = ?, tipo = ?, bairro = ? WHERE id = ?";
        try (Connection conn = util.Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipo());
            stmt.setString(5, usuario.getBairro());
            stmt.setInt(6, usuario.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    // deletar um usuário pelo id
    public boolean deletar(int id) {
        String sql = "DELETE FROM Usuarios WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0; // true se deletou, false se não encontrou
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}