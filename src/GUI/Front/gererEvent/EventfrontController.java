/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererEvent;

import ConnexionBd.connexionBd;
import Entity.houria.Evenement;
import Entity.houria.Participation;
import Entity.user.Utilisateur;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;
import service.houria.EventCRUD;
import service.houria.participationCRUD;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class EventfrontController implements Initializable {

    @FXML
    private AnchorPane prochEvent;
    @FXML
    private Button retour1;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox eventcontainer;
        EventCRUD bb = new EventCRUD();
        private Utilisateur u=new Utilisateur();

    connexionBd connection = null;
        private Connection cnx;
        Statement ste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
            eventcontainer.setSpacing(5);
            display_events();
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }    }    

    @FXML
    private void retour1(ActionEvent event) {
    }
          private void display_events() throws SQLException{
         // participationService pa = new participationService();

          String req="select * from event  ";
          List<VBox> list = new ArrayList<>();
          ste=cnx.createStatement();
          ResultSet rs = ste.executeQuery(req);
          while(rs.next()){
             Evenement e= new Evenement(rs.getInt(1), rs.getString(2),rs.getInt(5),rs.getString(6),rs.getString(7));
              ImageView va=new ImageView(new Image(rs.getString(8)));
               va.setFitHeight(200);
                va.setFitWidth(743);
              Button bt1=new Button("participer");
              Button bt222=new Button("hou");
              Label complet = null;
                 Participation pp= new Participation(9,rs.getInt(1));
              participationCRUD a = new participationCRUD();
              HBox h= new HBox();
              Label inscrit=new Label("");
              
              //3rft chniya el scrol ?eyyy
              if (a.chercherparticipation(pp)){
                 bt1.setDisable(true);
               inscrit=new Label(" vous etes inscrit a cet evenement  ");

              }
              
              if(e.getNbpart()==0){
               //  bt1.setDisable(true);
             complet=new Label(" Notre evenement est complet   ");
                          
              h.setSpacing(10);
              h.setAlignment(Pos.CENTER);
              h.getChildren().addAll(bt222);
              }else{
                   complet=new Label("");
                                
              h.setSpacing(10);
              h.setAlignment(Pos.CENTER);
              h.getChildren().addAll(bt1,bt222);

              bt1.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) { //bitha heki chas
                     System.out.println("c button mouhage tsir creation houniiii  ");
                      a.insert(pp);// insert hedhi yaanii bech yaaml participer bech nkaml table thenya mtaa event participation aaa fhemtik
                               bt1.setDisable(true); 
                      bb.decrementer(e.getNbpart()-1,e.getIdEvent());
               

                      
                 }
             });}
             Label nomm=new Label("le nom de cette evenement est : "+e.getNom());
             Label local=new Label( " l adress : "+e.getLocal());
             Button bt2=new Button("plus de details" ) ;

              HBox No= new HBox();
              No.setSpacing(10);
              No.setAlignment(Pos.CENTER);
               No.getChildren().addAll(nomm);
             HBox ins= new HBox();
              ins.setSpacing(10);
              ins.setAlignment(Pos.CENTER);
               ins.getChildren().addAll(inscrit);  
              HBox com= new HBox();
              com.setSpacing(10);
              com.setAlignment(Pos.CENTER);
               com.getChildren().addAll(complet);
               HBox adres= new HBox();
              adres.setSpacing(10);
              adres.setAlignment(Pos.CENTER);
               adres.getChildren().addAll(local);
               
               VBox v1=new VBox();
               v1.setAlignment(Pos.CENTER);
               v1.setSpacing(10);
               v1.getChildren().addAll(va,No,adres,com,ins,h);
               list.add(v1);
            
              


          }
         eventcontainer.getChildren().addAll(list);
     }
         
    
}
