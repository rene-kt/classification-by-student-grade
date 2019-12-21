/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import connection.ConnectionFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.bean.Aluno;
import model.dao.AdmDao;
import static model.dao.AdmDao.sql;
import model.dao.EnfDao;
import model.dao.InfDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Ludy
 */
public class Tela_rankingController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private ImageView imgFundo;
    @FXML
    private ComboBox<String> comboBox;

    private final List<String> cursos = new ArrayList<>();

    private ObservableList<String> observableListCursos;

    @FXML
    private TableView<Aluno> tableView;

    @FXML
    private TableColumn<Aluno, String> tableNome;
    @FXML
    private TableColumn<Aluno, String> tableData;

    @FXML
    private TableColumn<Aluno, Double> tableMedia;

    @FXML
    private TableColumn<Aluno, String> tableEscolaridade;

    @FXML
    private TableColumn<Aluno, String> tableCota;

    private int num_cota_pub_adm, num_cota_part_adm, num_ampla_pub_adm, num_ampla_part_adm = 0;
    private int num_cota_pub_enf, num_cota_part_enf, num_ampla_pub_enf, num_ampla_part_enf = 0;
    private int num_cota_pub_inf, num_cota_part_inf, num_ampla_pub_inf, num_ampla_part_inf = 0;

   

    public int retornar_num_cota_publica_adm() {
        AdmDao adao = new AdmDao();

        return adao.retornar_num_cota_publica_adm();
    }

    public int retornar_num_cota_particular_adm() {
        AdmDao adao = new AdmDao();

        return adao.retornar_num_cota_particular_adm();
    }

    public int retornar_num_cota_publica_Enf() {
        EnfDao edao = new EnfDao();

        return edao.retornar_num_cota_publica_ENF();
    }

    public int retornar_num_cota_particular_Enf() {
        EnfDao edao = new EnfDao();

        return edao.retornar_num_cota_particular_ENF();
    }

    public int retornar_num_cota_publica_Inf() {
        InfDao idao = new InfDao();

        return idao.retornar_num_cota_publica_INF();
    }

    public int retornar_num_cota_particular_Inf() {
        InfDao idao = new InfDao();

        return idao.retornar_num_cota_particular_INF();
    }

    public void pegarSelecionado() {
        new Thread() {
            @Override
            public void run() {

                String selecionar = comboBox.getSelectionModel().getSelectedItem();
                switch (selecionar) {
                    case "Administração":
                        popularTabelaAdm();
                        break;
                    case "Enfermagem":
                        popularTabelaEnfermagem();
                        break;
                    case "Informática":
                        popularTabelaInfor();
                        break;
                    default:
                        break;
                }
            }
        }.start();
    }

    public void popularComboBox() {
        cursos.add("Enfermagem");
        cursos.add("Administração");
        cursos.add("Informática");

        observableListCursos = FXCollections.observableArrayList(cursos);

        comboBox.setItems(observableListCursos);

    }

    public void popularTabelaEnfermagem() {

        tableNome.setCellValueFactory(new PropertyValueFactory("nome"));
        tableMedia.setCellValueFactory(new PropertyValueFactory("media"));
        tableData.setCellValueFactory(new PropertyValueFactory("dataNasc"));
        tableEscolaridade.setCellValueFactory(new PropertyValueFactory("escola"));
        tableCota.setCellValueFactory(new PropertyValueFactory("bairro"));

        tableView.setItems(atualizaTabelaEnfermagem());

    }

    public void popularTabelaAdm() {

        tableNome.setCellValueFactory(new PropertyValueFactory("nome"));
        tableMedia.setCellValueFactory(new PropertyValueFactory("media"));
        tableData.setCellValueFactory(new PropertyValueFactory("dataNasc"));
        tableEscolaridade.setCellValueFactory(new PropertyValueFactory("escola"));
        tableCota.setCellValueFactory(new PropertyValueFactory("bairro"));

        tableView.setItems(atualizaTabelaAdm());

    }

    public void popularTabelaInfor() {

        tableNome.setCellValueFactory(new PropertyValueFactory("nome"));
        tableMedia.setCellValueFactory(new PropertyValueFactory("media"));
        tableData.setCellValueFactory(new PropertyValueFactory("dataNasc"));
        tableEscolaridade.setCellValueFactory(new PropertyValueFactory("escola"));
        tableCota.setCellValueFactory(new PropertyValueFactory("bairro"));

        tableView.setItems(atualizaTabelaInformatica());

    }

    @FXML
    public void gerarListaParticular() {

        new Thread() {

            @Override
            public void run() {
                String selecionar = comboBox.getSelectionModel().getSelectedItem();
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement stmt = null;
                ResultSet rs = null;
                JasperPrint jp = null;

                try {
                    if (selecionar.equals("Administração")) {
                        String query = "select * from alunos where curso = 'ADM' and escola = 'Particular'"
                                + "and bairro = '' order by media desc limit 8,15";
                        stmt = con.prepareStatement(query);

                        rs = stmt.executeQuery(query);

                        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

                        String arquivoJasper = "Relatórios/esperaADMpart.jasper";
                        jp = JasperFillManager.fillReport(arquivoJasper, null, jrRS);

                        JasperViewer.viewReport(jp, false);

                    } else if (selecionar.equals("Enfermagem")) {
                        String query = "select * from alunos where curso = 'ENF' and escola = 'Particular'"
                                + " and bairro = ''order by media desc limit 8,15";
                        stmt = con.prepareStatement(query);

                        rs = stmt.executeQuery(query);

                        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

                        String arquivoJasper = "Relatórios/esperaENFpart.jasper";
                        jp = JasperFillManager.fillReport(arquivoJasper, null, jrRS);

                        JasperViewer.viewReport(jp, false);
                    } else if (selecionar.equals("Informática")) {
                        String query = "select * from alunos where curso = 'INF' and escola = 'Particular'"
                                + "and bairro = '' order by media desc limit 8,15";
                        stmt = con.prepareStatement(query);

                        rs = stmt.executeQuery(query);

                        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

                        String arquivoJasper = "Relatórios/esperaINFpart.jasper";
                        jp = JasperFillManager.fillReport(arquivoJasper, null, jrRS);

                        JasperViewer.viewReport(jp, false);
                    }

                } catch (JRException e) {
                    System.out.println(e);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }

            }

        }.start();
    }

    @FXML
    public void gerarLista() {

        new Thread() {

            @Override
            public void run() {
                String selecionar = comboBox.getSelectionModel().getSelectedItem();
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement stmt = null;
                ResultSet rs = null;
                JasperPrint jp = null;

                try {
                    if (selecionar.equals("Administração")) {
                        String query = "select * from alunos where curso = 'ADM' and escola = 'Publica'"
                                + "and bairro = '' order by media desc limit 32,52";
                        stmt = con.prepareStatement(query);

                        rs = stmt.executeQuery(query);

                        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

                        String arquivoJasper = "Relatórios/esperaADMpub.jasper";
                        jp = JasperFillManager.fillReport(arquivoJasper, null, jrRS);

                        JasperViewer.viewReport(jp, false);

                    } else if (selecionar.equals("Enfermagem")) {
                        String query = "select * from alunos where curso = 'ENF' and escola = 'Publica'"
                                + " and bairro = '' order by media desc limit 32,52";
                        stmt = con.prepareStatement(query);

                        rs = stmt.executeQuery(query);

                        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

                        String arquivoJasper = "Relatórios/esperaENFpub.jasper";
                        jp = JasperFillManager.fillReport(arquivoJasper, null, jrRS);

                        JasperViewer.viewReport(jp, false);
                    } else if (selecionar.equals("Informática")) {
                        String query = "select * from alunos where curso = 'INF' and escola = 'Publica'"
                                + "and bairro = '' order by media desc limit 32,52";
                        stmt = con.prepareStatement(query);

                        rs = stmt.executeQuery(query);

                        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

                        String arquivoJasper = "Relatórios/esperaINFpub.jasper";
                        jp = JasperFillManager.fillReport(arquivoJasper, null, jrRS);

                        JasperViewer.viewReport(jp, false);
                    }

                } catch (JRException e) {
                    System.out.println(e);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }

            }

        }.start();
    }

    @FXML
    public void gerarRelatorio() {

        new Thread() {

            @Override
            public void run() {
                String selecionar = comboBox.getSelectionModel().getSelectedItem();
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement stmt = null;
                ResultSet rs = null;
                JasperPrint jp = null;

                try {
                    if (selecionar.equals("Administração")) {
                        String query = "\n"
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
                                + "    escola = 'Publica' AND (curso = 'ADM')\n"
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
                                + "        AND (curso = 'ADM')\n"
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
                                + "        AND (curso = 'ADM')\n"
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
                                + "        AND (curso = 'ADM')\n"
                                + "        and (bairro = 'Cota de bairro')\n"
                                + "       \n"
                                + "       \n"
                                + "ORDER BY media DESC , dataNasc ASC\n"
                                + "LIMIT ?);\n"
                                + "";

                        stmt = con.prepareStatement(query);

                        stmt.setInt(1, num_ampla_pub_adm);
                        stmt.setInt(2, num_cota_pub_adm);
                        stmt.setInt(3, num_ampla_part_adm);
                        stmt.setInt(4, num_cota_part_adm);

                        rs = stmt.executeQuery();

                        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

                        String arquivoJasper = "Relatórios/rankingADM.jasper";
                        jp = JasperFillManager.fillReport(arquivoJasper, null, jrRS);

                        JasperViewer.viewReport(jp, false);

                    } else if (selecionar.equals("Enfermagem")) {
                        String query = "\n"
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
                        stmt = con.prepareStatement(query);

                        stmt.setInt(1, num_ampla_pub_enf);
                        stmt.setInt(2, num_cota_pub_enf);
                        stmt.setInt(3, num_ampla_part_enf);
                        stmt.setInt(4, num_cota_part_enf);

                        rs = stmt.executeQuery();

                        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

                        String arquivoJasper = "Relatórios/rankingENF.jasper";
                        jp = JasperFillManager.fillReport(arquivoJasper, null, jrRS);

                        JasperViewer.viewReport(jp, false);
                    } else if (selecionar.equals("Informática")) {
                        String query = "\n"
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
                                + "    escola = 'Publica' AND (curso = 'INF')\n"
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
                                + "        AND (curso = 'INF')\n"
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
                                + "        AND (curso = 'INF')\n"
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
                                + "        AND (curso = 'INF')\n"
                                + "        and (bairro = 'Cota de bairro')\n"
                                + "       \n"
                                + "       \n"
                                + "ORDER BY media DESC , dataNasc ASC\n"
                                + "LIMIT ?);\n"
                                + "";
                        stmt = con.prepareStatement(query);
                        
                        
                        stmt.setInt(1, num_ampla_pub_inf);
                        stmt.setInt(2, num_cota_pub_inf);
                        stmt.setInt(3, num_ampla_part_inf);
                        stmt.setInt(4, num_cota_part_inf);
                        
                        rs = stmt.executeQuery();

                        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

                        String arquivoJasper = "Relatórios/rankingINF.jasper";
                        jp = JasperFillManager.fillReport(arquivoJasper, null, jrRS);

                        JasperViewer.viewReport(jp, false);
                    }

                } catch (JRException e) {
                    System.out.println(e);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }

            }

        }.start();
    }

    public ObservableList<Aluno> gerarListaAdm() {
        AdmDao admdao = new AdmDao();

        num_cota_pub_adm = retornar_num_cota_publica_adm();
        num_ampla_part_adm = retornar_num_cota_particular_adm();

        num_ampla_pub_adm = 32 - num_cota_pub_adm;
        num_ampla_part_adm = 8 - num_cota_part_adm;

        return FXCollections.observableArrayList(admdao.listarAdm(num_ampla_pub_adm, num_cota_pub_adm, num_ampla_part_adm, num_cota_part_adm));
    }

    public ObservableList<Aluno> gerarListaEnf() {
        EnfDao enfdao = new EnfDao();

        num_cota_pub_enf = retornar_num_cota_publica_Enf();
        num_ampla_part_enf = retornar_num_cota_particular_Enf();

        num_ampla_pub_enf = 32 - num_cota_pub_enf;
        num_ampla_part_enf = 8 - num_cota_part_enf;

        return FXCollections.observableArrayList(enfdao.listarEnf(num_ampla_pub_enf, num_cota_pub_enf, num_ampla_part_enf, num_cota_part_enf));
    }

    public ObservableList<Aluno> gerarListaInf() {
        InfDao infdao = new InfDao();

        num_cota_pub_inf = retornar_num_cota_publica_Inf();
        num_ampla_part_inf = retornar_num_cota_particular_Inf();

        num_ampla_pub_inf = 32 - num_cota_pub_inf;
        num_ampla_part_inf = 8 - num_cota_part_inf;

        return FXCollections.observableArrayList(infdao.listarInf(num_ampla_pub_inf, num_cota_pub_inf, num_ampla_part_inf, num_cota_part_inf));
    }

    public ObservableList<Aluno> atualizaTabelaAdm() {
        ObservableList<Aluno> alunos = null;

        alunos = gerarListaAdm();

        return alunos;
    }

    public ObservableList<Aluno> atualizaTabelaEnfermagem() {

        ObservableList<Aluno> alunos = gerarListaEnf();

        return alunos;
    }

    public ObservableList<Aluno> atualizaTabelaInformatica() {
        ObservableList<Aluno> alunos = null;

        alunos = gerarListaInf();
        return alunos;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popularComboBox();

    }

}
