/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.bean.Aluno;
import model.dao.AlunoDao;

/**
 * FXML Controller class
 *
 * @author Ludy
 */
public class Editar_excluirController implements Initializable {

    @FXML
    private TableView<Aluno> tableView;

    @FXML
    private TableColumn<Aluno, Integer> tableId;

    @FXML
    private TableColumn<Aluno, String> tableNome;

    @FXML
    private TableColumn<Aluno, String> tableData;

    @FXML
    private TableColumn<Aluno, String> tableEscolaridade;

    @FXML
    private TableColumn<Aluno, String> tableBairro;

    @FXML
    private TableColumn<Aluno, String> tableMedia;
    
      @FXML
    private TableColumn<Aluno, String> tableCurso;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private Button editar;

    @FXML
    private Pane pane;

    @FXML
    public void editar() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/editarCadastro.fxml"));

        Parent root = (Pane) loader.load();

        EditarCadastroController tc = loader.getController();

        Aluno a = new Aluno();
        a.setId(tableView.getSelectionModel().getSelectedItem().getId());

        tc.txtId.setText(String.valueOf(a.getId()));

        pane.getChildren().setAll(root);

    }

    public void popularTabela() {

        tableId.setCellValueFactory(new PropertyValueFactory("id"));
        tableNome.setCellValueFactory(new PropertyValueFactory("nome"));
        tableData.setCellValueFactory(new PropertyValueFactory("dataNasc"));
        tableEscolaridade.setCellValueFactory(new PropertyValueFactory("escola"));
        tableBairro.setCellValueFactory(new PropertyValueFactory("bairro"));
        tableMedia.setCellValueFactory(new PropertyValueFactory("media"));
        tableCurso.setCellValueFactory(new PropertyValueFactory("curso"));

        tableView.setItems(atualizaTabela());

    }

    public ObservableList<Aluno> atualizaTabela() {
        AlunoDao adao = new AlunoDao();
        return FXCollections.observableArrayList(adao.listarAlunos());
    }

    @FXML
    public void excluir() {

        Aluno a = new Aluno();
        AlunoDao adao = new AlunoDao();

        a.setId(tableView.getSelectionModel().getSelectedItem().getId());
        System.out.println(a.getId());
        adao.excluir(a);

        tableView.setItems(atualizaTabela());

    }

    @FXML
    public void pesquisar() {

        tableView.setItems(pesquisarCampo());
    }

    @FXML
    public void enter(KeyEvent arg0) {
        if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
            pesquisar();
            System.out.println("teste");
        }
    }

    @FXML
    public ObservableList<Aluno> pesquisarCampo() {

        AlunoDao adao = new AlunoDao();

        return FXCollections.observableArrayList(adao.pesquisarAlunos(txtPesquisar.getText()));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        // TODO
        popularTabela();
    }
}
