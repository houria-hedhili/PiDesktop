/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.Acceuilfront;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Imen
 */
public class AcceuilFrontController implements Initializable {
    @FXML
    private Button gererEnfant1;
    @FXML
    private Button gereClasse1;
    @FXML
    private Button gererPersonnel1;
    @FXML
    private Button gererCantine1;
    @FXML
    private Button gererReclamation1;
    @FXML
    private Button gererEvent1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gererEnfant(ActionEvent event) throws IOException {
  Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererEnfant/enfant.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
           Stage stage1 = (Stage) gererEnfant1.getScene().getWindow();
    stage1.close(); 
    
    
    }

    @FXML
    private void gereClasse(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererCours/cours.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
           Stage stage1 = (Stage) gereClasse1.getScene().getWindow();
    stage1.close(); 
    }

    @FXML
    private void gererPersonnel(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererPersonnel/personnel.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
           Stage stage1 = (Stage) gererPersonnel1.getScene().getWindow();
    stage1.close(); 
    }

    @FXML
    private void gererCantine(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererCantine/abonnement/abonCantine.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
           Stage stage1 = (Stage) gererCantine1.getScene().getWindow();
    stage1.close(); 
    }

    @FXML
    private void gererReclamation(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Reclamation/reclamation.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
           Stage stage1 = (Stage) gererReclamation1.getScene().getWindow();
    stage1.close(); 
    }

    @FXML
    private void gererEvent(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererEvent/event.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
           Stage stage1 = (Stage) gererEvent1.getScene().getWindow();
    stage1.close(); 
    }
    
}
