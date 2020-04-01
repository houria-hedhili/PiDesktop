/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.authentification.forgetPassword;

import service.authentification.UserCRUD;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Services.Mail;
import java.io.IOException;
import static java.lang.Math.random;
import java.security.SecureRandom;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Imen
 */
public class ForgetController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private Button email;
    @FXML
    private Button valider;
    @FXML
    private Button signout;
          UserCRUD us = new UserCRUD();
 private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();
    String token= generateRandomString(7);
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
    private void envoiemail(ActionEvent event) throws SQLException, IOException {
        String mail= username.getText();
        if(us.existMail(mail))
        { us.creerToken(token);
        Mail m=new Mail();
        m.envoyer(mail,token);
   FXMLLoader loader = new FXMLLoader();
                     loader.setLocation(getClass().getResource("/GUI/authentification/forgetPassword/token.fxml"));

   
              
              Parent detail=loader.load();
              Scene scene = new Scene(detail);
               TokenController controller = loader.getController();
               controller.getMail(mail);
             Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        
        
        }else 
        { imgErreur.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
                        erreur.setText("invalid mail");}
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
    
    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

			// 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            // debug
            System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

            sb.append(rndChar);

        }

        return sb.toString();

    }
}
