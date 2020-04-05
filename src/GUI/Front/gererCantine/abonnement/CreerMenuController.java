/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererCantine.abonnement;

import Entity.imen.Plat;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        
    }    

    /**
     * This method accepts a person to initialize the view
     * @param p 
     */
    public void afficher(Plat p1)
    {p=p1;
    nomPlat.setText(p.getNom());
  Image imag = new Image(p.getImage());
    image.setImage(imag);
    desc.setText(p.getDescription());
    
    }
 
}
