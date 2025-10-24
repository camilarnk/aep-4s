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
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, apoio.getUsuario().getId());
            stmt.setInt(2, apoio.getPublicacao().getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                apoio.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // listar todos os apoios
    public List<Apoio> listar() {
        List<Apoio> apoios = new ArrayList<>();
        String sql = "SELECT a.*, u.id AS u_id, u.nome AS u_nome, e.id AS p_id, e.descricao AS p_desc, e.status AS p_status, e.data_criacao AS p_data, e.id_espaco AS p_espaco " +
                     "FROM Apoios a " +
                     "JOIN Usuarios u ON a.id_usuario = u.id " +
                     "JOIN Publicacoes e ON a.id_publicacao = e.id";
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

                Apoio apoio = new Apoio();
                apoio.setId(rs.getInt("id"));
                apoio.setUsuario(usuario);
                apoio.setPublicacao(publicacao);

                apoios.add(apoio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apoios;
    }

    // deletar apoio
    public boolean deletar(int id) {
        String sql = "DELETE FROM Apoios WHERE id = ?";
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