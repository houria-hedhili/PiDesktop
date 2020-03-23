/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Acceuilback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class MenuuController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button gerPart;
    @FXML
    private Button gerCours;
    @FXML
    private Button GerMatiere;
    @FXML
    private Button gerEvent;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button gerEvent1;
    @FXML
    private Button gerEvent2;
    @FXML
    private Button gerEvent21;
    @FXML
    private Button gerEvent22;
    @FXML
    private Button gerEvent23;
    @FXML
    private Button gerEvent231;
    @FXML
    private Button gerEvent232;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gererEvennement(MouseEvent event) throws IOException {
             AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Back/gererEvent/eventt.fxml"));
        ap.getChildren().setAll(pane);
    }

    @FXML
    private void gererCours(MouseEvent event) throws IOException {
          AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Back/gererCours/cours.fxml"));
        ap.getChildren().setAll(pane);
    }

    @FXML
    private void gererMatiere(MouseEvent event) throws IOException {
          AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Back/gererMatiere/matiere.fxml"));
        ap.getChildren().setAll(pane);
    }
    
}
