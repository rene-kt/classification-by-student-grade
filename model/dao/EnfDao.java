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
import java.util.ArrayList;
import java.util.List;
import model.bean.Aluno;

/**
 *
 * @author Informática
 */
public class EnfDao {

    public static String sql;

    //Todas cotas de bairro já preenchidas
    public int retornar_num_inscricoes_total() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int i = 0;

        try {
            stmt = con.prepareStatement("select id from alunos where curso = 'ENF'");

            rs = stmt.executeQuery();

            while (rs.next()) {
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }

    public double retornar_media() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double soma = 0.0;
        ArrayList<Double> notas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select media from alunos where curso = 'ENF'");

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
        return soma / notas.size();
    }

    public List<Aluno> listarEnf(int num_ampla_publica, int num_cota_publica,
            int num_ampla_particular, int num_cota_particular) {
        List<Aluno> alunos = new ArrayList<>();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        sql = "\n"
                + "\n"
                + "(select \n"
                + "id,\n"
                + "    nome,\n"
                + "    dataNasc,\n"
                + "    media,\n"
                + "    escola,\n"
                + "   bairro\n"
                + "   \n"
                + "   \n"
                + "from\n"
                + "    alunos\n"
                + "WHERE\n"
                + "    escola = 'Publica' AND (curso = 'ENF')\n"
                + "        \n"
                + "ORDER BY media DESC , dataNasc ASC\n"
                + "LIMIT ?) \n"
                + "\n"
                + "UNION (select \n"
                + " id,\n"
                + "    nome,\n"
                + "    dataNasc,\n"
                + "    media,\n"
                + "    escola,\n"
                + "    bairro\n"
                + "    \n"
                + "from\n"
                + "    alunos\n"
                + "WHERE\n"
                + "    escola = 'Publica'\n"
                + "        AND (curso = 'ENF')\n"
                + "        and (bairro = 'Cota de bairro')\n"
                + "       \n"
                + "       \n"
                + "ORDER BY media DESC , dataNasc ASC\n"
                + "LIMIT ?)  \n"
                + "\n"
                + " UNION (select \n"
                + " id,\n"
                + "    nome,\n"
                + "    dataNasc,\n"
                + "    media,\n"
                + "    escola,\n"
                + "    bairro\n"
                + "    \n"
                + "from\n"
                + "    alunos\n"
                + "WHERE\n"
                + "    escola = 'Particular'\n"
                + "        AND (curso = 'ENF')\n"
                + "       \n"
                + "       \n"
                + "ORDER BY media DESC , dataNasc ASC\n"
                + "LIMIT ?)\n"
                + " UNION (select \n"
                + " id,\n"
                + "    nome,\n"
                + "    dataNasc,\n"
                + "    media,\n"
                + "    escola,\n"
                + "    bairro\n"
                + "    \n"
                + "from\n"
                + "    alunos\n"
                + "WHERE\n"
                + "    escola = 'Particular'\n"
                + "        AND (curso = 'ENF')\n"
                + "        and (bairro = 'Cota de bairro')\n"
                + "       \n"
                + "       \n"
                + "ORDER BY media DESC , dataNasc ASC\n"
                + "LIMIT ?);\n"
                + "";

        try {
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, num_ampla_publica);
            stmt.setInt(2, num_cota_publica);
            stmt.setInt(3, num_ampla_particular);
            stmt.setInt(4, num_cota_particular);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno a = new Aluno();

                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setDataNasc(rs.getString("dataNasc"));
                a.setEscola(rs.getString("escola"));
                a.setBairro(rs.getString("bairro"));
                a.setMedia(rs.getDouble("media"));

                alunos.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return alunos;
    }

    public int retornar_num_cota_publica_ENF() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> alunos = new ArrayList<>();

        sql = "select bairro from alunos where curso = 'ENF' and escola = 'Publica' order by media desc limit 32;";

        try {
            stmt = con.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno a = new Aluno();

                a.setBairro(rs.getString("bairro"));

                if (a.getBairro().equals("Cota de bairro")) {
                    alunos.add(a);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return alunos.size();
    }

    public int retornar_num_cota_particular_ENF() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> alunos = new ArrayList<>();

        sql = "select bairro from alunos where curso = 'ENF' and escola = 'Particular' order by media desc limit 8;";

        try {
            stmt = con.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno a = new Aluno();

                a.setBairro(rs.getString("bairro"));

                if (a.getBairro().equals("Cota de bairro")) {
                    alunos.add(a);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return alunos.size();
    }

}
