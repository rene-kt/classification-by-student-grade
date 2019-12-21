/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Ludy
 */
public class FXMLmenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Pane pane;

    @FXML
    private ImageView imgFundo;
    Image image = new Image("/img/Principal.png");
    
    @FXML
    private Button btnCadastro;

    @FXML
    private Button btnInfor;

    @FXML
    private Button btnRanking;

    @FXML
    private Button btnEditar;
    
    @FXML
    private Pane paneLateral;

    @FXML
    public void handleVoltarMenu() throws IOException{
        Pane a = (Pane) FXMLLoader.load(getClass().getResource("/view/tela_cadaastro.fxml"));
        paneLateral.getChildren().setAll(a);
    }
    
    public void handleVoltarVazio() throws IOException{
       Pane p = (Pane) FXMLLoader.load(getClass().getResource("/view/FXMLvazio.fxml"));
        paneLateral.getChildren().setAll(p);
    }
    
    public void handleEditarAluno() throws IOException{
        Pane e = (Pane) FXMLLoader.load(getClass().getResource("/view/editar_excluir.fxml"));
        paneLateral.getChildren().setAll(e);
    }
    
    public void handleInfor() throws IOException{
        Pane e = (Pane) FXMLLoader.load(getClass().getResource("/view/tela_graficos.fxml"));
        paneLateral.getChildren().setAll(e);
    }
    
    public void handleRanking() throws IOException{
        Pane e = (Pane) FXMLLoader.load(getClass().getResource("/view/tela_ranking.fxml"));
        paneLateral.getChildren().setAll(e);
    }
    
    
    
    
    
    
        
    
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            imgFundo.setImage(image);
            
        
    }    
    
    
    
}
