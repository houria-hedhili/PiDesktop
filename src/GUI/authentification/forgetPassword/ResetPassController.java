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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Imen
 */
public class ResetPassController implements Initializable {
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField password1;
    @FXML
    private Button signin;
    @FXML
    private Button signout;
    String maile;
    boolean test=true;
          UserCRUD us = new UserCRUD();
String t;
    @FXML
    private ImageView imgErreur;
    @FXML
    private Label erreur;
    @FXML
    private ImageView imgErreur2;
    @FXML
    private Label erreur2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
//reset
      public String getMail(String mail,String token)
    { maile=mail;
    t=token;
    return maile;
    }
    @FXML
    private void connexionUtilisateur(ActionEvent event) throws SQLException, IOException {
if(password.getText().equals(password1.getText()))
{
us.modifierPassword(maile,password.getText());
us.supprimerToken(t);
     Parent root = FXMLLoader.load(getClass().getResource("/GUI/login/login.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
                      Stage stage = (Stage) signin.getScene().getWindow();
stage.close();
        stage1.setScene(scene);
        stage1.show();
System.out.println("tbadel");

} else 
    System.out.println("khraa");
    
    }
//page register
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
  private boolean controlPassword(String p )
  {

       Pattern pattern = Pattern.compile("[^A-Z&&[^a-z&&[^0-9&&[^ ]]]]");
                   Matcher matcher = pattern.matcher(password.getText()); 

      if(!matcher.find())
      {
      return true;
      }return false;
   
  } 
      
  
  

    @FXML
    private void controlSaisie(KeyEvent event) {
   /*if(controlPassword(password.getText())==false)
    {imgErreur.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
                        erreur.setText("invalid paassword");
    
    } else {*/
        if(password.getText().length()>0)
        {if(((controlPassword(password.getText())==false)|| password.getText().charAt(0)<'A' || password.getText().charAt(0)>'Z')||(password.getText().length()<4))
        {if( password.getText().charAt(0)<'A' || password.getText().charAt(0)>'Z')
        {imgErreur.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
                        erreur.setText("Vous devez commencer par une majuscule");
        test=false;
        }
        else if((password.getText().length()<5))
        {
        imgErreur.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
                        erreur.setText("Vous devez saisir au moins 5 caractere");
                                test=false;

        }else if ((controlPassword(password.getText())==false))
        { imgErreur.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
                        erreur.setText("Caracteres speciaux interdits");
                                test=false;

        
        }
        }
        
        
        
        else
        { imgErreur.setImage(new Image(this.getClass().getResourceAsStream("tic.png")));
                        erreur.setText("");
                test=true;

        }
   
   
        } else {imgErreur.setImage(null);
                        erreur.setText("");}
    

    }
    
    

    @FXML
    private void controlSaisie2(KeyEvent event) {
    if(password1.getText().length()>0)
    { for(int i=0;i<password1.getText().length();i++)
    {if (password1.getText().charAt(i)!= password.getText().charAt(i))
    {imgErreur2.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
                        erreur2.setText("Il doit etre identique au mot de passe");
                                test=false;

    } 
    else{imgErreur2.setImage(new Image(this.getClass().getResourceAsStream("tic.png")));} 
           test=true;

    }
    
    
    }else {imgErreur2.setImage(null);
                        erreur2.setText("");}
}}
