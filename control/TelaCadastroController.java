/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class TelaCadastroController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private ImageView imgFundo;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtNasc;

    @FXML
    private RadioButton simEscola;

    @FXML
    private ToggleGroup bairroRadio;

    @FXML
    private RadioButton naoEscola;

    @FXML
    private RadioButton simBairro;

    @FXML
    private ToggleGroup escolaRadio;

    @FXML
    private RadioButton naoBairro;

    @FXML
    private Button btnSalvar;
    @FXML
    private ComboBox<String> combo;

    private final List<String> cursos = new ArrayList<>();

    private ObservableList<String> observableListCursos;

    ArrayList<Double> notas = new ArrayList<>();

    public static Integer id;

    /**
     * Initializes the controller class.
     */
    public void popularComboBox() {
        cursos.add("Administração");
        cursos.add("Enfermagem");
        cursos.add("Informática");

        observableListCursos = FXCollections.observableArrayList(cursos);

        combo.setItems(observableListCursos);

    }

    @FXML
    public void cadastrar() {

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
        a.setMedia(0);

        adao.salvar(a);

        id = adao.retornar_id(a);

    }

    @FXML
    public void handleMedias() throws IOException {

        if (txtNome.getText().trim().isEmpty() || txtNasc.getText().trim().isEmpty()
                || simEscola.isSelected() == false && naoEscola.isSelected() == false || simBairro.isSelected() == false
                && naoBairro.isSelected() == false) {
            Alert a = new Alert(Alert.AlertType.WARNING);

            a.setTitle("Campos não estão todos preenchidos");
            a.setHeaderText("Verifique se todos os campos estão preenchidos");
            a.show();
        } else {
            cadastrar();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/medias.fxml"));

            Parent root = (Pane) loader.load();

            MediasController tc = loader.getController();

            tc.txtId.setText(id.toString());

            pane.getChildren().setAll(root);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        popularComboBox();
    }

}
