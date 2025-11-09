package com.ocupamais.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ocupamais.model.Apoio;
import com.ocupamais.model.Publicacao;
import com.ocupamais.model.Usuario;

import util.Conexao;

public class ApoioDAO {

    // inserir apoio
    public void inserir(Apoio apoio) {
        String sql = "INSERT INTO Apoios (id_usuario, id_publicacao) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, apoio.getUsuario().getId());
            stmt.setInt(2, apoio.getPublicacao().getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // listar todos os apoios
    public List<Apoio> listar() {
        List<Apoio> apoios = new ArrayList<>();
        String sql = """
            SELECT 
                a.id_usuario, a.id_publicacao,
                u.id AS u_id, u.nome AS u_nome,
                p.id AS p_id, p.descricao AS p_desc, p.status AS p_status, p.data_criacao AS p_data
            FROM Apoios a
            JOIN Usuarios u ON a.id_usuario = u.id
            JOIN Publicacoes p ON a.id_publicacao = p.id
        """;
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("u_id"));
                usuario.setNome(rs.getString("u_nome"));

                Publicacao publicacao = new Publicacao();
                publicacao.setId(rs.getInt("p_id"));
                publicacao.setDescricao(rs.getString("p_desc"));
                publicacao.setStatus(rs.getString("p_status"));
                publicacao.setDataCriacao(rs.getTimestamp("p_data"));

                Apoio apoio = new Apoio(usuario, publicacao);
                apoios.add(apoio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apoios;
    }

    // deletar apoio por id_usuario + id_publicacao
    public boolean deletarPorUsuarioEPublicacao(int idUsuario, int idPublicacao) {
        String sql = "DELETE FROM Apoios WHERE id_usuario = ? AND id_publicacao = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idPublicacao);
            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}