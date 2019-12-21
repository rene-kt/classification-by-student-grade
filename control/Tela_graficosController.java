/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.dao.AdmDao;
import model.dao.AlunoDao;
import model.dao.EnfDao;
import model.dao.InfDao;

/**
 * FXML Controller class
 *
 * @author Ludy
 */
public class Tela_graficosController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private ImageView imgFundo;

    @FXML
    private Label numADM;

    @FXML
    private Label numENF;

    @FXML
    private Label numINF;

    @FXML
    private Label numCota;

    @FXML
    private Label mediaADM;

    @FXML
    private Label mediaENF;

    @FXML
    private Label mediaINF;

    @FXML
    private Label publicaMEDIA;

    @FXML
    private Label particularMedia;

    private final NumberFormat df = new DecimalFormat("#0.00");

    public void popular_num_insc_curso() {
        AdmDao admdao = new AdmDao();
        EnfDao enfdao = new EnfDao();
        InfDao infdao = new InfDao();

        try {
            numADM.setText(String.valueOf(admdao.retornar_num_inscricoes_total()));
            numENF.setText(String.valueOf(enfdao.retornar_num_inscricoes_total()));
            numINF.setText(String.valueOf(infdao.retornar_num_inscricoes_total()));

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void popular_medias_cursos() {
        AdmDao admdao = new AdmDao();
        EnfDao enfdao = new EnfDao();
        InfDao infdao = new InfDao();

        try {
            mediaADM.setText(df.format(admdao.retornar_media()));
            mediaENF.setText(df.format(enfdao.retornar_media()));
            mediaINF.setText(df.format(infdao.retornar_media()));

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void popular_num_cota() {
        AlunoDao adao = new AlunoDao();

        numCota.setText(String.valueOf(adao.retornar_num_cota()));
    }

    public void popular_media_escolaridade() {
        AlunoDao adao = new AlunoDao();

        publicaMEDIA.setText(df.format(adao.retornar_media_publica()));
        particularMedia.setText(df.format(adao.retornar_media_particular()));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        popular_num_insc_curso();
        popular_medias_cursos();
        popular_num_cota();
        popular_media_escolaridade();
    }

}
