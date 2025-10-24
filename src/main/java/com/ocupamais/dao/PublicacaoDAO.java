package com.ocupamais.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ocupamais.model.EspacoPublico;
import com.ocupamais.model.Publicacao;
import com.ocupamais.model.Usuario;

import util.Conexao;

public class PublicacaoDAO {

    // inserir publicação
    public void inserir(Publicacao publicacao) {
        String sql = "INSERT INTO Publicacoes (id_usuario, id_espaco, descricao, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, publicacao.getUsuario().getId());
            stmt.setInt(2, publicacao.getEspaco().getId());
            stmt.setString(3, publicacao.getDescricao());

            // se o status estiver null define "PENDENTE"
            String status = publicacao.getStatus() != null ? publicacao.getStatus() : "PENDENTE";
            stmt.setString(4, status);
            publicacao.setStatus(status); // objeto Java também tenha PENDENTE

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                publicacao.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // listar todas as publicações
    public List<Publicacao> listar() {
        List<Publicacao> publicacoes = new ArrayList<>();
        String sql = "SELECT p.*, u.id AS u_id, u.nome AS u_nome, u.email AS u_email, u.senha AS u_senha, u.tipo AS u_tipo, u.bairro AS u_bairro," +
                     " e.id AS e_id, e.nome AS e_nome, e.localizacao AS e_localizacao " +
                     "FROM Publicacoes p " +
                     "JOIN Usuarios u ON p.id_usuario = u.id " +
                     "JOIN EspacosPublicos e ON p.id_espaco = e.id";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("u_id"),
                        rs.getString("u_nome"),
                        rs.getString("u_email"),
                        rs.getString("u_senha"),
                        rs.getString("u_tipo"),
                        rs.getString("u_bairro")
                );

                EspacoPublico espaco = new EspacoPublico(
                        rs.getInt("e_id"),
                        rs.getString("e_nome"),
                        rs.getString("e_localizacao")
                );

                Publicacao p = new Publicacao(
                        rs.getInt("id"),
                        usuario,
                        espaco,
                        rs.getString("descricao"),
                        rs.getTimestamp("data_criacao"),
                        rs.getString("status")
                );

                publicacoes.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicacoes;
    }

    // buscar por id
    public Publicacao buscarPorId(int id) {
        String sql = "SELECT p.*, u.id AS u_id, u.nome AS u_nome, u.email AS u_email, u.senha AS u_senha, u.tipo AS u_tipo, u.bairro AS u_bairro," +
                     " e.id AS e_id, e.nome AS e_nome, e.localizacao AS e_localizacao " +
                     "FROM Publicacoes p " +
                     "JOIN Usuarios u ON p.id_usuario = u.id " +
                     "JOIN EspacosPublicos e ON p.id_espaco = e.id " +
                     "WHERE p.id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("u_id"),
                        rs.getString("u_nome"),
                        rs.getString("u_email"),
                        rs.getString("u_senha"),
                        rs.getString("u_tipo"),
                        rs.getString("u_bairro")
                );

                EspacoPublico espaco = new EspacoPublico(
                        rs.getInt("e_id"),
                        rs.getString("e_nome"),
                        rs.getString("e_localizacao")
                );

                return new Publicacao(
                        rs.getInt("id"),
                        usuario,
                        espaco,
                        rs.getString("descricao"),
                        rs.getTimestamp("data_criacao"),
                        rs.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // atualizar status ou descrição
    public void atualizar(Publicacao publicacao) {
        String sql = "UPDATE Publicacoes SET descricao = ?, status = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, publicacao.getDescricao());
            stmt.setString(2, publicacao.getStatus());
            stmt.setInt(3, publicacao.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // deletar publicação
    public boolean deletar(int id) {
        String sql = "DELETE FROM Publicacoes WHERE id = ?";
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
