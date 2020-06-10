/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererCantine.abonnement;

import Entity.imen.Plat;
import Entity.imen.abonnement;
import static GUI.Front.gererCantine.abonnement.AbonCantineController.plat;
import GUI.login.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.imen.PlatService;
import service.imen.abonnementService;
import service.imen.menuService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class PlatPrincipalController implements Initializable {
    @FXML
    private VBox detail;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private Button valider;
    
    PlatService ps = new PlatService();
        menuService ms =new menuService();
        abonnementService as=new abonnementService();

        static int idEnfant=0;
        static int idPlat=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

      afficherPlatPrincipal(); 
 
comboAfficher();  

    
    
    
    }    
    private void comboAfficher()
    {       List<abonnement> L=as.afficherAbonSelonparent(LoginController.ID);
                   List<String> l=new ArrayList<>();
                   
        for(int i=0;i<L.size();i++)
        {  System.out.println("hetha khra id "+as.haveMenu(LoginController.ID,L.get(i).getIdEnfant()));
        System.out.println(L.get(i).getIdEnfant());
        if(as.haveMenu(LoginController.ID,L.get(i).getIdEnfant())==0)
        { l.add(L.get(i).getPrenom());}
        }
                   
        ObservableList<String> enfListe=FXCollections.observableArrayList(l);
        combo.setValue("Liste des enfants");
combo.setItems(enfListe);
    }
static int k=0;
    public void afficherPlatPrincipal()
    {
          List<VBox> list = new ArrayList<>();
        List<Plat> listPlat=ps.afficherPlatSelonType("plat principal");
        System.out.print(listPlat.get(0).getDescription());
          while(k<listPlat.size()){
             
              ImageView va=new ImageView(new Image(listPlat.get(k).getImage()));
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
              
                                
             
int n=k;
              ajouter.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) { //bitha heki chas
                     idPlat=listPlat.get(n).getId();
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
                    

                      idPlat=0;
                      Supprimer.setVisible(false);
                      ajouter.setVisible(true);
                 }
             });
                
             Label nomm=new Label("Plat principal : "+listPlat.get(k).getNom());
          
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
               
             k++; 


          }
          detail.getChildren().clear();
         detail.getChildren().addAll(list);
}
    @FXML
    private void valider(ActionEvent event) throws IOException {
    if(idPlat==0)
    {
    System.out.println("veuillez saisir un plat principal");
       Error("Vous devez saisir un dplat principal");

    
    }else if(combo.getValue().equals("Liste des enfants"))
    {
        Error("Vous devez saisir un Enfant");
;
    }    
    else
    {idEnfant=as.getEnfant(combo.getValue(),LoginController.ID);
 Success("un plat a ete choisi"); 
    Stage stage = (Stage) valider.getScene().getWindow();
    stage.close();
      Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererCantine/abonnement/dessert.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
    
    
    }}
    
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
    
   
    
