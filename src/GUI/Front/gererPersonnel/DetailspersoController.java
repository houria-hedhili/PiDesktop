/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererPersonnel;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import service.aziza.PersonnelDao;

/**
 * FXML Controller class
 *
 * @author GLOBALINFO
 */
public class DetailspersoController implements Initializable {
  private Personnel Personnel;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label Categorie;
    @FXML
    private ImageView img;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    
     public void initData2(Personnel per)
    {PersonnelDao sp = new PersonnelDao();
        Personnel = per;
        nom.setText(Personnel.getNom());
        prenom.setText(Personnel.getPrenom());
        Categorie.setText(Personnel.getCategorie());
        img.setImage(new Image("file:/C:/wamp/www/jardin1/web/images/team/"+Personnel.getImage()));
img.setFitHeight(150);
img.setFitWidth(150);
//e.setPhoto(i);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void rtourner() throws IOException {
                Stage stage = (Stage) retour.getScene().getWindow();
    stage.close();
      Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererPersonnel/personnel.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
    
    }
    
}
