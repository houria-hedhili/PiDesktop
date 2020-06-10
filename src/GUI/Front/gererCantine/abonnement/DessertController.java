/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererCantine.abonnement;

import Entity.imen.Menu;
import Entity.imen.Plat;
import static GUI.Front.gererCantine.abonnement.PlatPrincipalController.idPlat;
import GUI.login.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.imen.PlatService;
import service.imen.menuService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DessertController implements Initializable {
    @FXML
    private Button valider;
    @FXML
    private ScrollPane dessertListe;
    @FXML
    private VBox details;
static int idDessert=0;
menuService ms =new menuService(); 
PlatService ps=new PlatService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherDessert();
        System.out.println("hetha id enfant  "+PlatPrincipalController.idEnfant);
    }    

    static int m=0;
    public void afficherDessert()
    {
          List<VBox> list = new ArrayList<>();
        List<Plat> listPlat=ps.afficherPlatSelonType("dessert");
          while(m<listPlat.size()){
             
              ImageView va=new ImageView(new Image(listPlat.get(m).getImage()));
               va.setFitHeight(200);
                va.setFitWidth(743);
              Button ajouter=new Button("Ajouter");
              Button detail=new Button("Detail");
              Button Supprimer=new Button("Enlever");

              HBox h= new HBox();
              
              //3rft chniya el scrol ?eyyy
            
               //  bt1.setDisable(true);
                     Supprimer.setVisible(false);
              h.setSpacing(10);
              h.setAlignment(Pos.CENTER);
              h.getChildren().addAll(detail,ajouter,Supprimer);
              
                                
             
int n=m;
              ajouter.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) { //bitha heki chas
                     idDessert=listPlat.get(n).getId();
                     ajouter.setVisible(false);
                     Supprimer.setVisible(true);

                      
                 }
             });
               detail.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) { //bitha heki chas
                    
          AbonCantineController.plat=listPlat.get(n);
                  Stage stage=new Stage();
                   Parent root;
                      try {
                          root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererCantine/abonnement/creerMenu.fxml"));
                   Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
                      } catch (IOException ex) {
                          Logger.getLogger(AbonCantineController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      
                 }
             });
                Supprimer.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) { //bitha heki chas
                    

                      idDessert=0;
                      Supprimer.setVisible(false);
                      ajouter.setVisible(true);
                 }
             });
                
             Label nomm=new Label("Dessert : "+listPlat.get(m).getNom());
          
              HBox No= new HBox();
              No.setSpacing(10);
              No.setAlignment(Pos.CENTER);
               No.getChildren().addAll(nomm);
                HBox N1= new HBox();
              N1.setSpacing(10);
              N1.setAlignment(Pos.CENTER);
            VBox v1=new VBox();
               v1.setAlignment(Pos.CENTER);
               v1.setSpacing(10);

               v1.getChildren().addAll(va,No,h,N1);
               list.add(v1);
               
             m++; 


          }
          details.getChildren().clear();
         details.getChildren().addAll(list);
}
    @FXML
    private void valider(ActionEvent event) throws IOException {
     if(idDessert==0)
  {System.out.println("veuillez choisir un dessert");
   Error("Vous devez saisir un dessert");
  }
  else
  {  Success("le Menu va etre creer");
      Menu m= new Menu(PlatPrincipalController.idPlat,idDessert,LoginController.ID,PlatPrincipalController.idEnfant);
  ms.ajouterMenu(m);
   Stage stage = (Stage) valider.getScene().getWindow();
    stage.close();
      Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererCantine/abonnement/abonCantine.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
  
  }
    }

   
    private void Success(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajouter un plat");
 
        // Header Text: nullCla
        alert.setHeaderText(null);
        alert.setContentText(msg);
 
        alert.showAndWait();
    }
     private void Error(String msg) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("Look, an Error Dialog");
alert.setContentText(msg);
alert.showAndWait();
    }
    
  
    
}
