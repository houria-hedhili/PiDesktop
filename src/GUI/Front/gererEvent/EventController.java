/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererEvent;

import ConnexionBd.connexionBd;
import Entity.houria.Evenement;
import Entity.houria.Participation;
import Entity.user.user;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.houria.participationCRUD;

/**
 * FXML Controller class
 *
 * @author Imen
 */
public class EventController implements Initializable {
    @FXML
    private TabPane tabpane;
    @FXML
    private Button retour1;
    @FXML
    private Button retour2;
    @FXML
    private Button retour3;
    @FXML
    private AnchorPane prochEvent;
    @FXML
    private Label precEvent;
    @FXML
    private Tab partcip;
    private VBox eventcont;
     
    connexionBd connection = null;
        private Connection cnx;

    private VBox eventcontainer;

    public EventController() {
        cnx= connexionBd.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // UserSession n = UserSession.getInstance();
                              //  s1 = n.getUserName();
   /*     try {
            eventcontainer.setSpacing(5);

            display_events();
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
 */

    }    

@FXML
    private void retour1(ActionEvent event) throws IOException {

        Stage stage = (Stage) retour1.getScene().getWindow();
    stage.close();
      Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Acceuilfront/acceuilFront.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
    }

    @FXML
    private void retour2(ActionEvent event) throws IOException {
              Stage stage = (Stage) retour2.getScene().getWindow();
               Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Acceuilfront/acceuilFront.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
    stage.close();
     
    }

    @FXML
    private void retour3(ActionEvent event) throws IOException {
       Stage stage = (Stage) retour1.getScene().getWindow();
      Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Acceuilfront/acceuilFront.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
       stage.close();
    }

    private void retour4(ActionEvent event) throws IOException {
   Stage stage = (Stage) retour1.getScene().getWindow();
    Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Acceuilfront/acceuilFront.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
    stage.close();
     
    }
      /*   private void display_events() throws SQLException{
          participationCRUD pa = new participationCRUD();
          String req="select * from event  ";
          List<VBox> list = new ArrayList<>();
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
          while(rs.next()){

              Evenement e= new Evenement(rs.getInt(1), rs.getString(2), rs.getInt(5), rs.getString(6),rs.getString(7));
              ImageView va=new ImageView(new Image(rs.getString(8)));
               va.setFitHeight(200);
        va.setFitWidth(743);
              Button bt1=new Button("participer");
             
              Participation pp= new Participation(new user(),e.getIdEvent());
              if(pa.chercher_ajout_participation(pp)){
                  bt1.setDisable(true);
              }
              HBox h= new HBox();
              h.setSpacing(10);
              h.setAlignment(Pos.CENTER);
              h.getChildren().addAll(bt1);
               VBox v1=new VBox();
               v1.setAlignment(Pos.CENTER);
               v1.setSpacing(10);
               v1.getChildren().addAll(va,h);
               list.add(v1);
               bt1.setOnAction(new EventHandler<ActionEvent>(){
                   @Override
                   public void handle(ActionEvent event) {
                       try {
                           if(!pa.chercher_ajout_participation(pp))
                           {  
                              // pa.insert(pp);
                               bt1.setDisable(true);
                               
                           }          
                           else System.out.println("deja participe");
                       } catch (SQLException ex) {
                           Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                   
               });
          }
         eventcont.getChildren().addAll(list);
     }*/
    
    
}
