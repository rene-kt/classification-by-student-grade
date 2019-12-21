/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.bean.Aluno;
import model.dao.AlunoDao;
import main.PreMatricula;

/**
 * FXML Controller class
 *
 * @author Ludy
 */
public class MediasController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private ImageView imgFundo;
    @FXML
    private TextField txtBiologia6;
    @FXML
    private TextField txtArtes6;
    @FXML
    private TextField txtBiologia8;
    @FXML
    private TextField txtArtes8;
    @FXML
    private TextField txtArtes9;
    @FXML
    private TextField txtArtesFinal;
    @FXML
    private TextField txtBiologia7;
    @FXML
    private TextField txtFisicaFinal;
    @FXML
    private TextField txtBiologiaFinal;
    @FXML
    private Button btnSalvar;
    @FXML
    private TextField txtEdFisicainal;
    @FXML
    private TextField txtEdFisica9;
    @FXML
    private TextField txtEdFisica8;
    @FXML
    private TextField txtEdFisica7;
    @FXML
    private TextField txtEdFisica6;
    @FXML
    private TextField txtEspanholFinal;
    @FXML
    private TextField txtEspanhol9;
    @FXML
    private TextField txtEspanhol8;
    @FXML
    private TextField txtEspanhol7;
    @FXML
    private TextField txtFilosofiaFinal;
    @FXML
    private TextField txtFilosofia9;
    @FXML
    private TextField txtFilosofia8;
    @FXML
    private TextField txtEspanhol6;
    @FXML
    private TextField txtFilosofia7;
    @FXML
    private TextField txtFilosofia6;
    @FXML
    private TextField txtFisica9;
    @FXML
    private TextField txtGeografiaFinal;
    @FXML
    private TextField txtFisica6;
    @FXML
    private TextField txtFisica7;
    @FXML
    private TextField txtFisica8;
    @FXML
    private TextField txtGeografia7;
    @FXML
    private TextField txtGeografia8;
    @FXML
    private TextField txtGeografia9;
    @FXML
    private TextField txtGeometria7;
    @FXML
    private TextField txtGeometria8;
    @FXML
    private TextField txtHistoriaFinal;
    @FXML
    private TextField txtGeometria9;
    @FXML
    private TextField txtGeografia6;
    @FXML
    private TextField txtGeometria6;
    @FXML
    private TextField txtHistoria8;
    @FXML
    private TextField txtHistoria9;
    @FXML
    private TextField txtHistoria7;
    @FXML
    private TextField txtInformatica9;
    @FXML
    private TextField txtInformaticaFinal;
    @FXML
    private TextField txtHistoria6;
    @FXML
    private TextField txtInformatica6;
    @FXML
    private TextField txtInformatica7;
    @FXML
    private TextField txtInformatica8;
    @FXML
    private TextField txtIngles8;
    @FXML
    private TextField txtIngles9;
    @FXML
    private TextField txtInglesFinal;
    @FXML
    private TextField txtIngles6;
    @FXML
    private TextField txtIngles7;
    @FXML
    private TextField txtPortugues9;
    @FXML
    private TextField txtPortuguesFinal;
    @FXML
    private TextField txtBiologia9;
    @FXML
    private TextField txtPortugues7;
    @FXML
    private TextField txtPortugues8;
    @FXML
    private TextField txtPortugues6;
    @FXML
    private TextField txtLiteratura6;
    @FXML
    private TextField txtLiteratura7;
    @FXML
    private TextField txtLiteratura8;
    @FXML
    private TextField txtLiteratura9;
    @FXML
    private TextField txtLiteraturaFinal;
    @FXML
    private TextField txtRedacao8;
    @FXML
    private TextField txtRedacao7;
    @FXML
    private TextField txtRedacao6;
    @FXML
    private TextField txtQuimicaFinal;
    @FXML
    private TextField txtQuimica9;
    @FXML
    private TextField txtQuimica8;
    @FXML
    private TextField txtQuimica7;
    @FXML
    private TextField txtQuimica6;
    @FXML
    private TextField txtMatematicaFinal;
    @FXML
    private TextField txtMatematica9;
    @FXML
    private TextField txtMatematica8;
    @FXML
    private TextField txtMatematica7;
    @FXML
    private TextField txtMatematica6;
    @FXML
    private TextField txtValores7;
    @FXML
    private TextField txtValores8;
    @FXML
    private TextField txtValores9;
    @FXML
    private TextField txtValoresFinal;
    @FXML
    private TextField txtValores6;
    @FXML
    private TextField txtRedacao9;
    @FXML
    private TextField txtRedacaoFinal;
    @FXML
    private TextField txtGeometriaFinal;
    @FXML
    private TextField txtMediaFinall;
    @FXML
    private TextField txtArtes7;
    @FXML
    public TextField txtId;

    ArrayList<Double> notas = new ArrayList<>();

    public void salvar() throws IOException {
        Aluno a = new Aluno();
        AlunoDao adao = new AlunoDao();

        a.setId(Integer.parseInt(txtId.getText()));
        a.setMedia(gerarMediaFinal());

        if (adao.atualizar_media(a) == true) {
            Alert aa = new Alert(Alert.AlertType.INFORMATION);
            aa.setHeaderText("O Aluno foi cadastrado com sucesso");
            aa.setContentText("A média do aluno foi salva!");
            aa.show();
        }
    }

    public void popularMedias() {

        try {

            if (!txtArtesFinal.getText().trim().isEmpty()) {
                notas.add(Double.parseDouble(txtArtesFinal.getText()));
            }
            notas.add(Double.parseDouble(txtBiologiaFinal.getText()));
            notas.add(Double.parseDouble(txtEdFisicainal.getText()));

            if (!txtEspanholFinal.getText().trim().isEmpty()) {
                notas.add(Double.parseDouble(txtEspanholFinal.getText()));
            }

            if (!txtFilosofiaFinal.getText().trim().isEmpty()) {
                notas.add(Double.parseDouble(txtFilosofiaFinal.getText()));
            }

            if (!txtFisicaFinal.getText().trim().isEmpty()) {
                notas.add(Double.parseDouble(txtFisicaFinal.getText()));
            }

            if (!txtGeometriaFinal.getText().trim().isEmpty()) {
                notas.add(Double.parseDouble(txtGeometriaFinal.getText()));
            }
            notas.add(Double.parseDouble(txtHistoriaFinal.getText()));
            notas.add(Double.parseDouble(txtGeografiaFinal.getText()));
            if (!txtInformaticaFinal.getText().trim().isEmpty()) {
                notas.add(Double.parseDouble(txtInformaticaFinal.getText()));
            }

            if (!txtInglesFinal.getText().trim().isEmpty()) {
                notas.add(Double.parseDouble(txtInglesFinal.getText()));
            }

            notas.add(Double.parseDouble(txtPortuguesFinal.getText()));

            if (!txtLiteraturaFinal.getText().trim().isEmpty()) {
                notas.add(Double.parseDouble(txtLiteraturaFinal.getText()));
            }

            notas.add(Double.parseDouble(txtMatematicaFinal.getText()));

            if (!txtRedacaoFinal.getText().trim().isEmpty()) {
                notas.add(Double.parseDouble(txtRedacaoFinal.getText()));
            }

            notas.add(Double.parseDouble(txtValoresFinal.getText()));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    public double gerarMediaFinal() {
        double soma = 0.0;
        Double media = 0.0;
        NumberFormat df = new DecimalFormat("#0.00");

        popularMedias();

        for (int i = 0; i < notas.size(); i++) {
            soma += notas.get(i);
        }
        media = soma / notas.size();

        String mediaString = df.format(media);
        
        String StringMedia = mediaString.replaceAll(",", ".");
        
        media = Double.parseDouble(StringMedia);
        
        txtMediaFinall.setText(mediaString);

        return media;

    }
    

    @FXML
    public void gerarMediaArtes() {

        Double soma = Double.parseDouble(txtArtes6.getText()) + Double.parseDouble(txtArtes7.getText())
                + Double.parseDouble(txtArtes8.getText()) + Double.parseDouble(txtArtes9.getText());

        Double media = soma / 4;

        String mediaString = String.valueOf(media);

        txtArtesFinal.setText(mediaString);

        System.out.println("da certo pf " + txtId.getText());

    }

    @FXML
    public void gerarMediaBiologia() {

        Double soma = Double.parseDouble(txtBiologia6.getText()) + Double.parseDouble(txtBiologia7.getText())
                + Double.parseDouble(txtBiologia8.getText()) + Double.parseDouble(txtBiologia9.getText());

        Double media = soma / 4;

        String mediaString = String.valueOf(media);

        txtBiologiaFinal.setText(mediaString);

    }

    @FXML
    public void gerarMediaEdFisica() {

        Double soma = Double.parseDouble(txtEdFisica6.getText()) + Double.parseDouble(txtEdFisica7.getText())
                + Double.parseDouble(txtEdFisica8.getText()) + Double.parseDouble(txtEdFisica9.getText());

        Double media = soma / 4;

        String mediaString = String.valueOf(media);

        txtEdFisicainal.setText(mediaString);

    }

    @FXML
    public void gerarMediaValoresHumanos() {

        Double soma = Double.parseDouble(txtValores6.getText()) + Double.parseDouble(txtValores7.getText())
                + Double.parseDouble(txtValores8.getText()) + Double.parseDouble(txtValores9.getText());

        Double media = soma / 4;

        String mediaString = String.valueOf(media);

        txtValoresFinal.setText(mediaString);

    }

    @FXML
    public void gerarMediaEspanhol() {

        Double soma = 0.0;
        Double media = 0.0;

        if (!txtEspanhol7.getText().trim().isEmpty()) {

            soma = Double.parseDouble(txtEspanhol8.getText()) + Double.parseDouble(txtEspanhol9.getText())
                    + Double.parseDouble(txtEspanhol7.getText()) + Double.parseDouble(txtEspanhol6.getText());

            media = soma / 4;

            String mediaString = String.valueOf(media);

            txtEspanholFinal.setText(mediaString);
        }
        soma = Double.parseDouble(txtEspanhol8.getText()) + Double.parseDouble(txtEspanhol9.getText());

        media = soma / 2;

        String mediaString = String.valueOf(media);

        txtEspanholFinal.setText(mediaString);

    }

    @FXML
    public void gerarMediaIngles() {

        Double soma = 0.0;
        Double media = 0.0;
        int i = 0;

        if (!txtIngles6.getText().trim().isEmpty() && !txtIngles7.getText().trim().isEmpty()) {
            soma = Double.parseDouble(txtIngles8.getText()) + Double.parseDouble(txtIngles9.getText())
                    + Double.parseDouble(txtIngles6.getText()) + Double.parseDouble(txtIngles7.getText());

            media = soma / 4;

            String mediaString = String.valueOf(media);

            txtInglesFinal.setText(mediaString);

        } else if (txtIngles6.getText().trim().isEmpty() && txtIngles7.getText().trim().isEmpty()) {

            soma = Double.parseDouble(txtIngles8.getText()) + Double.parseDouble(txtIngles9.getText());

            media = soma / 2;

            String mediaString = String.valueOf(media);

            txtInglesFinal.setText(mediaString);
        }
    }

    @FXML
    public void gerarMediaInfor() {
        Double soma = Double.parseDouble(txtInformatica6.getText()) + Double.parseDouble(txtInformatica7.getText())
                + Double.parseDouble(txtInformatica8.getText()) + Double.parseDouble(txtInformatica9.getText());

        Double media = soma / 4;

        String mediaString = String.valueOf(media);

        txtInformaticaFinal.setText(mediaString);

    }

    @FXML
    public void gerarMediaFilosofia() {
        Double soma = Double.parseDouble(txtFilosofia6.getText()) + Double.parseDouble(txtFilosofia7.getText())
                + Double.parseDouble(txtFilosofia8.getText()) + Double.parseDouble(txtFilosofia9.getText());

        Double media = soma / 4;

        String mediaString = String.valueOf(media);

        txtFilosofiaFinal.setText(mediaString);

    }

    @FXML
    public void gerarMediaLiteratura() {
        Double soma = Double.parseDouble(txtLiteratura6.getText()) + Double.parseDouble(txtLiteratura7.getText())
                + Double.parseDouble(txtLiteratura8.getText()) + Double.parseDouble(txtLiteratura9.getText());

        Double media = soma / 4;

        String mediaString = String.valueOf(media);

        txtLiteraturaFinal.setText(mediaString);

    }

    @FXML
    public void gerarMediaFisica() {

        Double soma = Double.parseDouble(txtFisica8.getText()) + Double.parseDouble(txtFisica9.getText());

        Double media = soma / 2;

        String mediaString = String.valueOf(media);

        txtFisicaFinal.setText(mediaString);

    }

    @FXML
    public void gerarMediaQuimica() {

        Double soma = Double.parseDouble(txtQuimica8.getText()) + Double.parseDouble(txtQuimica9.getText());

        Double media = soma / 2;

        String mediaString = String.valueOf(media);

        txtQuimicaFinal.setText(mediaString);
    }

    @FXML
    public void gerarMediaGeografia() {

        Double soma = Double.parseDouble(txtGeografia6.getText()) + Double.parseDouble(txtGeografia7.getText())
                + Double.parseDouble(txtGeografia8.getText()) + Double.parseDouble(txtGeografia9.getText());

        Double media = soma / 4;

        String mediaString = String.valueOf(media);

        txtGeografiaFinal.setText(mediaString);

    }

    @FXML
    public void gerarMediaGeometria() {

        Double soma = Double.parseDouble(txtGeometria6.getText()) + Double.parseDouble(txtGeometria7.getText())
                + Double.parseDouble(txtGeometria8.getText()) + Double.parseDouble(txtGeometria9.getText());

        Double media = soma / 4;

        String mediaString = String.valueOf(media);

        txtGeometriaFinal.setText(mediaString);

    }

    @FXML
    public void gerarMediaRedacao() {

        Double soma = Double.parseDouble(txtRedacao6.getText()) + Double.parseDouble(txtRedacao7.getText())
                + Double.parseDouble(txtRedacao8.getText()) + Double.parseDouble(txtRedacao9.getText());

        Double media = soma / 4;

        String mediaString = String.valueOf(media);

        txtRedacaoFinal.setText(mediaString);

    }

    @FXML
    public void gerarMediaHistoria() {

        Double soma = Double.parseDouble(txtHistoria6.getText()) + Double.parseDouble(txtHistoria7.getText())
                + Double.parseDouble(txtHistoria8.getText()) + Double.parseDouble(txtHistoria9.getText());

        Double media = soma / 4;

        String mediaString = String.valueOf(media);

        txtHistoriaFinal.setText(mediaString);

    }

    @FXML
    public void gerarMediaMatematica() {

        Double soma = Double.parseDouble(txtMatematica6.getText()) + Double.parseDouble(txtMatematica7.getText())
                + Double.parseDouble(txtMatematica8.getText()) + Double.parseDouble(txtMatematica9.getText());

        Double media = soma / 4;

        String mediaString = String.valueOf(media);

        txtMatematicaFinal.setText(mediaString);

    }

    @FXML
    public void gerarMediaPortugues() {

        Double soma = Double.parseDouble(txtPortugues6.getText()) + Double.parseDouble(txtPortugues7.getText())
                + Double.parseDouble(txtPortugues8.getText()) + Double.parseDouble(txtPortugues9.getText());

        Double media = soma / 4;

        String mediaString = String.valueOf(media);

        txtPortuguesFinal.setText(mediaString);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("da certo pf" + txtId.getText());
    }
}
