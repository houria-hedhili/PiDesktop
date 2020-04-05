/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.authentification.register;

import service.authentification.UserCRUD;
import Entity.user.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Imen
 */
public class RegisterController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField password1;
    @FXML
    private Button signin;
    @FXML
    private Button signout;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField mail;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img6;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


 

    @FXML
    private void inscrireUtilisateur(ActionEvent event) throws IOException {
   Utilisateur u = new Utilisateur();
        UserCRUD us = new UserCRUD();
           
        if(username.getText().equals("")&& mail.getText().equals("") && password.getText().equals("") && nom.getText().equals("") && prenom.getText().equals(""))
        {Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Invalide inscription");
alert.setHeaderText("Veuillez remplir tous les champs");
alert.setContentText("Ooops!");

alert.showAndWait();
        
        
        }else
        { u.setUsername(username.getText());
            u.setEmail(mail.getText());
            u.setPassword(password.getText());
            u.setEnable(1);
u.setNom(nom.getText());
u.setRoles(null);
u.setPrenom(prenom.getText());

           us.ajoutUser(u, u.getPassword());
               Parent root = FXMLLoader.load(getClass().getResource("/GUI/login/login.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
                      Stage stage = (Stage) signout.getScene().getWindow();
stage.close();
        stage1.setScene(scene);
        stage1.show();}//ghaltet azizaaa
            
    }
    @FXML
    private void pageConnexion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/login/login.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
                      Stage stage = (Stage) signin.getScene().getWindow();
stage.close();
        stage1.setScene(scene);
        stage1.show();
    }

    @FXML
    private void prenomControl(KeyEvent event) {

 Pattern pattern = Pattern.compile("[^A-Z&&[^a-z&&[^ ]]]");
                   Matcher matcher = pattern.matcher(prenom.getText()); 

      if(! matcher.find())
      {if(!"".equals(prenom.getText())){
     img1.setImage(new Image(this.getClass().getResourceAsStream("tic.png")));
      }else  img1.setImage(null);
    }
else  img1.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));   
      if("".equals(prenom.getText())) 
        img1.setImage(null);
    
    }

    @FXML
    private void nomControl(KeyEvent event) {
   
       Pattern pattern = Pattern.compile("[^A-Z&&[^a-z&&[^ ]]]");
                   Matcher matcher = pattern.matcher(nom.getText()); 

      if(! matcher.find())
      {if(!"".equals(nom.getText())){
     img2.setImage(new Image(this.getClass().getResourceAsStream("tic.png")));
      }else  img2.setImage(null);
    }
else  img2.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));   
      if("".equals(prenom.getText())) 
        img2.setImage(null);
    
    }

    @FXML
    private void mailControl(KeyEvent event) {
        boolean controlMaile = controlMail(mail.getText());
    if("".equals(mail.getText())) 
        img3.setImage(null);
    }

    @FXML
    private void loginControl(KeyEvent event) {
     Pattern pattern = Pattern.compile("[^A-Z&&[^a-z&&[^0-9&&[^ ]]]]");
                   Matcher matcher = pattern.matcher(username.getText()); 

      if(! matcher.find())
      {if(!"".equals(username.getText())){
     img4.setImage(new Image(this.getClass().getResourceAsStream("tic.png")));
      }else  img4.setImage(null);
    }
else  img4.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));   
      if("".equals(username.getText())) 
        img4.setImage(null);
    if("".equals(username.getText())) 
        img4.setImage(null);
    }

    @FXML
    private void passwordControl(KeyEvent event) {
           if(password.getText().length()>0)
        {if(((controlPassword(password.getText())==false)|| password.getText().charAt(0)<'A' || password.getText().charAt(0)>'Z')||(password.getText().length()<4))
        {img5.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
        }
        
        
        
        
        else
        { img5.setImage(new Image(this.getClass().getResourceAsStream("tic.png")));
                

        }
   
   
      
    }
    if("".equals(password.getText())) 
        img5.setImage(null);
    }

    @FXML
    private void password1Control(KeyEvent event) {
         if(password1.getText().length()>0)
    { for(int i=0;i<password1.getText().length();i++)
    {if (password1.getText().charAt(i)!= password.getText().charAt(i))
    {img6.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
                       

    } 
    else{img6.setImage(new Image(this.getClass().getResourceAsStream("tic.png")));} 
     

    }
    
    
    }if("".equals(password1.getText())) 
        img6.setImage(null);
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
    private void essai(InputMethodEvent event) {
        if(prenom.getText()=="") 
        img1.setImage(null);
    }
    
    boolean controlMail (String mail){
boolean pass =true;

if (mail.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+" )) {
    img3.setImage(new Image(this.getClass().getResourceAsStream("tic.png")));//a finir
}else{
img3.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
}

return pass;
}

    @FXML
    private void showInfo(MouseEvent event) {


    
    }
}
