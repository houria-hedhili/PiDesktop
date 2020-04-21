/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererPersonnels;

import Entity.aziza.Personnel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.aziza.PersonnelDao;

/**
 * FXML Controller class
 *
 * @author GLOBALINFO
 */
public class DetailsSalController implements Initializable {

    @FXML
    private Label nom;
    private Personnel Personnel;
    @FXML
    private Label prenom;
    @FXML
    private Label heure;
    @FXML
    private Label prix;
    @FXML
    private Label sal;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
        public void initData(Personnel per)
    {PersonnelDao sp = new PersonnelDao();
        Personnel = per;
        nom.setText(Personnel.getNom());
        prenom.setText(Personnel.getPrenom());
        heure.setText(Double.toString(Personnel.getNb_h()));
        prix.setText(Double.toString(Personnel.getPrix_h()));
       Double t= sp.CalculSalaire();
        sal.setText(Double.toString(Personnel.getNb_h()*Personnel.getPrix_h()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
                Stage stage = (Stage) retour.getScene().getWindow();
    stage.close();
      Parent root = FXMLLoader.load(getClass().getResource("/GUI/Back/Acceuilback/menuu.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
    
    }
    
}
