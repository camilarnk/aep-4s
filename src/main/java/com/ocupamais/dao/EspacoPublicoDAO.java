package com.ocupamais.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ocupamais.model.EspacoPublico;

import util.Conexao;

public class EspacoPublicoDAO {

    // inserir espaço público
     public void inserir(EspacoPublico espaco) {
        String sql = "INSERT INTO espacospublicos (nome) VALUES (?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, espaco.getNome());
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGerado = rs.getInt(1);
                        espaco.setId(idGerado);
                        System.out.println("Espaço inserido com sucesso! ID=" + idGerado);
                    }
                }
            } else {
                System.err.println("⚠️ Nenhuma linha inserida para o espaço público!");
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao inserir espaço público: " + e.getMessage());
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
                        rs.getString("nome")
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
                        rs.getString("nome")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // buscar por nome
    public EspacoPublico buscarPorNome(String nome) {
        String sql = "SELECT * FROM espacospublicos WHERE nome = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                EspacoPublico espaco = new EspacoPublico();
                espaco.setId(rs.getInt("id"));
                espaco.setNome(rs.getString("nome"));
                return espaco;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erro ao buscar espaço por nome: " + e.getMessage());
        }
        return null;
    }

    // atualizar
    public void atualizar(EspacoPublico espaco) {
        String sql = "UPDATE EspacosPublicos SET nome = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, espaco.getNome());
            stmt.setInt(2, espaco.getId());
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