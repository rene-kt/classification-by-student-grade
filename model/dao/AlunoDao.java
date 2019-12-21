/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Aluno;

/**
 *
 * @author Informática
 */
public class AlunoDao {

    public int retornar_num_cota() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int i = 0;

        try {
            stmt = con.prepareStatement("select id from alunos where bairro = 'Cota de bairro'");

            rs = stmt.executeQuery();

            while (rs.next()) {
                i++;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }
       public double retornar_media_publica() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double soma = 0.0;
        ArrayList<Double> notas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select media from alunos where escola = 'Publica'");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno a = new Aluno();

                a.setMedia(rs.getDouble("media"));

                notas.add(a.getMedia());
            }
            for (int i = 0; i < notas.size(); i++) {
                soma += notas.get(i);
            }
        } catch (Exception e) {
            return 0.0;
        }
        return soma/notas.size();
    }
       
          public double retornar_media_particular() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double soma = 0.0;
        ArrayList<Double> notas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select media from alunos where escola = 'Particular'");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno a = new Aluno();

                a.setMedia(rs.getDouble("media"));

                notas.add(a.getMedia());
            }
            for (int i = 0; i < notas.size(); i++) {
                soma += notas.get(i);
            }
        } catch (Exception e) {
            return 0.0;
        }
        return soma/notas.size();
    }

    public void salvar(Aluno a) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into alunos (nome, dataNasc, bairro, escola, media, curso)values(?,?,?,?,?,?)");

            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getDataNasc());
            stmt.setString(3, a.getBairro());
            stmt.setString(4, a.getEscola());
            stmt.setDouble(5, a.getMedia());
            stmt.setString(6, a.getCurso());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public boolean atualizar_media(Aluno a) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("update alunos set media = ? where id = ?");

            stmt.setDouble(1, a.getMedia());
            stmt.setInt(2, a.getId());

            if (stmt.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return true;
    }

    public void editar(Aluno a) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("update alunos set nome = ?, dataNasc = ?, bairro = ?,"
                    + " escola = ?, curso = ? where id= ?");

            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getDataNasc());
            stmt.setString(3, a.getBairro());
            stmt.setString(4, a.getEscola());
            stmt.setString(5, a.getCurso());
            stmt.setInt(6, a.getId());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void excluir(Aluno a) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from alunos where id = ?");

            stmt.setInt(1, a.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public int retornar_id(Aluno a) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int id = 0;

        try {
            stmt = con.prepareStatement("select id from alunos where nome =? and dataNasc = ? ");

            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getDataNasc());
            rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> Alunos = new ArrayList<>();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = con.prepareStatement("select * from alunos order by media desc");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno a = new Aluno();

                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setDataNasc(rs.getString("dataNasc"));
                a.setMedia(rs.getDouble("media"));
                a.setEscola(rs.getString("escola"));
                a.setBairro(rs.getString("bairro"));
                a.setCurso(rs.getString("curso"));

                Alunos.add(a);
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Alunos;

    }

    public List<Aluno> pesquisarAlunos(String nome) {
        List<Aluno> Alunos = new ArrayList<>();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = con.prepareStatement("select * from alunos where nome like ?");
            stmt.setString(1, nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno a = new Aluno();

                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setDataNasc(rs.getString("dataNasc"));
                a.setMedia(rs.getDouble("media"));
                a.setEscola(rs.getString("escola"));
                a.setBairro(rs.getString("bairro"));

                Alunos.add(a);
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Alunos;

    }

}
