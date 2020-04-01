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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
    private Button enfan;
    @FXML
    private Button bus;
    @FXML
    private Button cantin;
    @FXML
    private Button abonn;
    @FXML
    private Button reclam;
    @FXML
    private Button catR;
    @FXML
    private Button perso;
    @FXML
    private Button catP;
    @FXML
    private Button profi;
    @FXML
    private Button decon;

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

    @FXML
    private void gererParticipation(MouseEvent event) throws IOException { 
              AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Back/gererParticipation/participant.fxml"));
        ap.getChildren().setAll(pane);
    }

    @FXML
    private void gererEnfant(MouseEvent event) throws IOException {
                  AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Back/gererEnfant/enfant.fxml"));
        ap.getChildren().setAll(pane);
    }

    @FXML
    private void gererBus(MouseEvent event) throws IOException {
                  AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Back/gererBus/bus.fxml"));
        ap.getChildren().setAll(pane);
    }

    @FXML
    private void gererCantine(MouseEvent event) throws IOException {
                  AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Back/gererCantine/cantine.fxml"));
        ap.getChildren().setAll(pane);
    }

    @FXML
    private void gererAbonnement(MouseEvent event) throws IOException {
                  AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Back/gererAbonnement/abonnement.fxml"));
        ap.getChildren().setAll(pane);
    }

    @FXML
    private void gererReclamation(MouseEvent event) throws IOException {
                  AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Back/Reclamation/reclamationBack.fxml"));
        ap.getChildren().setAll(pane);
    }

    @FXML
    private void gererCategReclamation(MouseEvent event) throws IOException {
         AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Back/gererCatRec/categorieReclamation.fxml"));
        ap.getChildren().setAll(pane);
    }

    @FXML
    private void gererPersonnel(MouseEvent event) throws IOException {
                  AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Back/gererPersonnels/personnel.fxml"));
        ap.getChildren().setAll(pane);
    }

    @FXML
    private void gererCategPersonnel(MouseEvent event) throws IOException {
                  AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Back/gererCategoriePersonnels/Categorie1.fxml"));
        ap.getChildren().setAll(pane);
    }

    @FXML
    private void profil(MouseEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/profilback/profilback.fxml"));
        ap.getChildren().setAll(pane);
    }

    @FXML
    private void deconnecter(MouseEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/GUI/login/login.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
                      Stage stage = (Stage) decon.getScene().getWindow();
stage.close();
        stage1.setScene(scene);
        stage1.show();
    }
       public String getName(String name)
    { String namee=name;

        return namee;
    }
    
}
