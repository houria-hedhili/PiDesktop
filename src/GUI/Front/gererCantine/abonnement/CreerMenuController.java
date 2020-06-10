/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererCantine.abonnement;

import Entity.imen.Plat;
import GUI.login.LoginController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import service.imen.PlatService;

/**
 * FXML Controller class
 *
 * @author Imen
 */
public class CreerMenuController implements Initializable {
    private Plat p;
    @FXML
    private Label nomPlat;
    @FXML
    private ImageView image;
    @FXML
    private Text desc;
    @FXML
    private Button aime;
    @FXML
    private Button retour;
    /**
     * Initializes the controller class.
     */
    PlatService ps= new PlatService();
    @FXML
    private Button aime1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
if(ps.rechercheAime(LoginController.ID,AbonCantineController.plat.getId())==true)
   {
  aime1.setVisible(true);
aime.setVisible(false);
   }  else aime1.setVisible(false);
     afficher();   
    }    

    /**
     * This method accepts a person to initialize the view
     * @param p 
     */
    public void afficher()
    {
   
  Image imag = new Image("file:/C:/wamp/www/jardin1/web/"+AbonCantineController.plat.getImg());
    image.setImage(imag);
    desc.setText(AbonCantineController.plat.getDescription());
   
    nomPlat.setText(AbonCantineController.plat.getNom());
   
    
    
    }

    @FXML
    private void aime(ActionEvent event) {
   if(ps.rechercheAime(LoginController.ID,AbonCantineController.plat.getId())==false)
 
   {ps.LikePost(LoginController.ID,AbonCantineController.plat.getId());
   
   }
   
    aime.setVisible(false);
   aime1.setVisible(true);
    
    }

    @FXML
    private void retour(ActionEvent event) {
                 Stage stage = (Stage) retour.getScene().getWindow();
                 stage.close();

    }

    @FXML
    private void aime1(ActionEvent event) {
    ps.deleteLike(AbonCantineController.plat.getId(),LoginController.ID);
      aime.setVisible(true);
   aime1.setVisible(false);
    
    }
 
}
