/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.bean.Aluno;
import model.dao.AlunoDao;

/**
 * FXML Controller class
 *
 * @author Ludy
 */
public class EditarCadastroController implements Initializable {

    @FXML
    private Pane pane;

    @FXML
    private ImageView imgFundo;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtNasc;

    @FXML
    public TextField txtId;

    @FXML
    private RadioButton simEscola;

    @FXML
    private ToggleGroup escola;

    @FXML
    private RadioButton simEscola1;

    @FXML
    private RadioButton simBairro;

    @FXML
    private ToggleGroup bairro;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button editar;

    @FXML
    private ComboBox<String> combo;

    private final List<String> cursos = new ArrayList<>();

    private ObservableList<String> observableListCursos;

    @FXML
    public void editar() {

        Aluno a = new Aluno();
        AlunoDao adao = new AlunoDao();

        a.setNome(txtNome.getText());
        a.setDataNasc(txtNasc.getText());

        if (simBairro.isSelected()) {

            a.setBairro("Cota de bairro");

        } else {
            a.setBairro("");
        }

        if (simEscola.isSelected()) {

            a.setEscola("Particular");

        } else if (!simEscola.isSelected()) {
            a.setEscola("Publica");
        }

        String selecionar = combo.getSelectionModel().getSelectedItem();

        if (selecionar.equals("Administração")) {
            a.setCurso("ADM");
        } else if (selecionar.equals("Enfermagem")) {
            a.setCurso("ENF");
        } else {
            a.setCurso("INF");
        }
        a.setId(Integer.parseInt(txtId.getText()));

        adao.editar(a);

    }

    public void popularComboBox() {
        cursos.add("Administração");
        cursos.add("Enfermagem");
        cursos.add("Informática");

        observableListCursos = FXCollections.observableArrayList(cursos);

        combo.setItems(observableListCursos);

    }

    /**
     * Initializes the controller class.
     */
    @FXML
    public void salvarAluno() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("Alterações salvas salvas com sucesso");
        a.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popularComboBox();
    }

}
