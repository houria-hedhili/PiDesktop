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
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.houria.EventCRUD;
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
    
     
    connexionBd connection = null;
        private Connection cnx;
        Statement ste;

    @FXML
    private VBox eventcontainer;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TableColumn<?, ?> nom;
    EventCRUD bb = new EventCRUD();
    @FXML
    private TableView<?> table_event;
    @FXML
    private TableColumn<?, ?> timage;
    @FXML
    private TableColumn<?, ?> dat_d;
    @FXML
    private TableColumn<?, ?> date_f;
    @FXML
    private TableColumn<?, ?> descrip;
    @FXML
    private TableColumn<?, ?> adress;

    public EventController() {
        cnx= connexionBd.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      
     afficher();

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
                     System.out.println("c button mouhage tsir creation houniiii  ");//eyy fheemtik chnia theb taamel bih?
                      a.insert(pp);// insert hedhi yaanii bech yaaml participer bech nkaml table thenya mtaa event participation aaa fhemtik
                               bt1.setDisable(true); 
                      bb.decrementer(e.getNbpart()-1,e.getIdEvent());
               afficher();
               

                      
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
          eventcontainer.getChildren().clear();
         eventcontainer.getChildren().addAll(list);
     }
         private void afficher() {
    
     EventCRUD sp = new EventCRUD();
      List events=sp.displaymesevent(9);
       ObservableList et=FXCollections.observableArrayList(events);
       table_event.setItems(et);//  ouiii chnouwa 5orm hedha ???  
       nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       timage.setCellValueFactory(new PropertyValueFactory<>("photo"));
       dat_d.setCellValueFactory(new PropertyValueFactory<>("date"));
       date_f.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
       descrip.setCellValueFactory(new PropertyValueFactory<>("description"));
       adress.setCellValueFactory(new PropertyValueFactory<>("local"));

}

    @FXML
    private void annuler(MouseEvent event) throws SQLException {
        System.out.println("ok");
         participationCRUD cs = new participationCRUD();
         Evenement cc = (Evenement)table_event.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir event");
                   
        }else{
          bb.decrementer(cc.getNbpart()+1,cc.getIdEvent());

            cs.deleteparticipation(cc.getIdEvent(),9);

    
           afficher();
           
           JOptionPane.showMessageDialog(null, "event supprimer");

        cc=null;
    }
    }

    @FXML
    private void aff(Event event) throws SQLException {
                    display_events();

    }

    
}
