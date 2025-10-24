package com.ocupamais.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ocupamais.model.EspacoPublico;

import util.Conexao;

public class EspacoPublicoDAO {

    // inserir espaço público
    public void inserir(EspacoPublico espaco) {
        String sql = "INSERT INTO EspacosPublicos (nome, localizacao) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, espaco.getNome());
            stmt.setString(2, espaco.getLocalizacao());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                espaco.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // listar todos os espaços
    public List<EspacoPublico> listar() {
        List<EspacoPublico> espacos = new ArrayList<>();
        String sql = "SELECT * FROM EspacosPublicos";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EspacoPublico e = new EspacoPublico(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("localizacao")
                );
                espacos.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return espacos;
    }

    // buscar por id
    public EspacoPublico buscarPorId(int id) {
        String sql = "SELECT * FROM EspacosPublicos WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new EspacoPublico(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("localizacao")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // atualizar
    public void atualizar(EspacoPublico espaco) {
        String sql = "UPDATE EspacosPublicos SET nome = ?, localizacao = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, espaco.getNome());
            stmt.setString(2, espaco.getLocalizacao());
            stmt.setInt(3, espaco.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // deletar
    public boolean deletar(int id) {
        String sql = "DELETE FROM EspacosPublicos WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}