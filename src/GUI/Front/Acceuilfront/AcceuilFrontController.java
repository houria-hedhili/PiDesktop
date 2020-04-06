/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.Acceuilfront;

import Entity.user.Utilisateur;
import GUI.Front.gererCantine.abonnement.AbonCantineController;
import GUI.Front.gererEvent.EventController;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.authentification.UserCRUD;

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
    @FXML
    private Label nom;
    String namee;
    @FXML
    private Label nomUser;
      Utilisateur u=new Utilisateur();
      UserCRUD uc=new UserCRUD();
    @FXML
    private Button VisiterNous;
    /**
     * Initializes the controller class.
     */
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gererEnfant(ActionEvent event) throws IOException {
    Stage stage1 = (Stage) gererEnfant1.getScene().getWindow();
    stage1.close(); 
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererEnfant/enfant.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    
    
    }

    @FXML
    private void gereClasse(ActionEvent event) throws IOException {
            Stage stage1 = (Stage) gereClasse1.getScene().getWindow();
    stage1.close();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererCours/cours.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
       
    }

    @FXML
    private void gererPersonnel(ActionEvent event) throws IOException {
             Stage stage1 = (Stage) gererPersonnel1.getScene().getWindow();
    stage1.close();  
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererPersonnel/personnel.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
      
    }

    @FXML
    private void gererCantine(ActionEvent event) throws IOException {
          Stage stage1 = (Stage) gererCantine1.getScene().getWindow();
    stage1.close();
        FXMLLoader loader = new FXMLLoader();
                     loader.setLocation(getClass().getResource("/GUI/Front/gererCantine/abonnement/abonCantine.fxml"));

              
              Parent detail=loader.load();
              Scene scene = new Scene(detail);
               AbonCantineController controller = loader.getController();
               controller.getUsername(namee);
             Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
      
    }

    @FXML
    private void gererReclamation(ActionEvent event) throws IOException {
         Stage stage1 = (Stage) gererReclamation1.getScene().getWindow();
    stage1.close(); 
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Reclamation/reclamation.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
         
    }

    @FXML
    private void gererEvent(ActionEvent event) throws IOException, SQLException {
       Stage stage1 = (Stage) gererEvent1.getScene().getWindow();
            stage1.close();
        u=uc.getUser(namee);
               System.out.println("lena zaama 0 "+u.getId());//cv jawou bhy
        FXMLLoader loader = new FXMLLoader();
                     loader.setLocation(getClass().getResource("/GUI/Front/gererEvent/event.fxml"));
                    Parent detail=loader.load();
              Scene scene = new Scene(detail);
             
             EventController controller = loader.getController();
               controller.getUser(u);

            
             Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
           
        
    }
      public String getUser(String nom )
    { String ch=nom;
    
               nomUser.setText("Bienvenue "+ch);

        return ch ;
    }
     public String getUsername(String nom)
    { String ch=nom;
            namee=ch;  
                           //nomUser.setText("Bienvenue "+ch);

        return ch ;
    }
    
    
}
