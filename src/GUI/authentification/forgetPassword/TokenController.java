/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.authentification.forgetPassword;

import service.authentification.UserCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Imen
 */
public class TokenController implements Initializable {
    @FXML
    private TextField toke;
    @FXML
    private Button email;
    @FXML
    private Button valider;
    @FXML
    private Button signout;
          UserCRUD us = new UserCRUD();
          String maile;
    @FXML
    private ImageView imgErreur;
    @FXML
    private Label erreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void validerToken(ActionEvent event) throws SQLException, IOException {
   
    if(us.verifToken(toke.getText()))
    {
    FXMLLoader loader = new FXMLLoader();
                     loader.setLocation(getClass().getResource("/GUI/authentification/forgetPassword/resetPass.fxml"));

   
              
              Parent detail=loader.load();
              Scene scene = new Scene(detail);
               ResetPassController controller = loader.getController();
               controller.getMail(maile,toke.getText());
             Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        
        
        
    }else {imgErreur.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
                        erreur.setText("invalid code");}
    
    }

    @FXML
    private void oublier(MouseEvent event) {
    }

    @FXML
    private void pageInscrire(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("/GUI/authentification/register/register.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
                      Stage stage = (Stage) signout.getScene().getWindow();
stage.close();
        stage1.setScene(scene);
        stage1.show();
    }
    public String getMail(String mail)
    { maile=mail;
    return maile;
    }
}
