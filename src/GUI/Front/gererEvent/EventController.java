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
import Entity.user.user;
import GUI.login.LoginController;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
import toolsHouria.PDF;

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
    private Utilisateur u=new Utilisateur();
    @FXML
    private ScrollPane scrollpass;
    @FXML
    private VBox eventcontainerpass;
    public EventController() {
        cnx= connexionBd.getInstance().getCnx();
    }
                 int id = LoginController.ID;


    /**
     * Initializes the controller class.
     */
          public void getUser(Utilisateur m )
    { 
               u=m;
               System.out.println("lena fi event : id "+u.getId());

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       id=LoginController.ID;
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
      
      private void display_events() throws SQLException, FileNotFoundException, DocumentException{
         // participationService pa = new participationService();
         
          System.out.println("hedha l id eli connecté pour verifier : "+ id);
          String req="select * from event where date > NOW()";
          List<VBox> list = new ArrayList<>();
          ste=cnx.createStatement();
          ResultSet rs = ste.executeQuery(req);
          while(rs.next()){
              Evenement e = new Evenement();
                e.setIdEvent(rs.getInt("idEvent"));
                e.setNom(rs.getString("nom"));
                e.setDate(rs.getTimestamp("date"));
                e.setLocal(rs.getString("local"));
                e.setNbpart(rs.getInt("nbpart"));
               
                e.setDate_fin(rs.getTimestamp("date_fin"));
                e.setDescription(rs.getString("description"));
                e.setImage(rs.getString("image"));
              ImageView va=new ImageView(new Image(rs.getString(8)));
               va.setFitHeight(200);
                va.setFitWidth(743);
                e.setPhoto(va);
              Button bt1=new Button("participer");
              Button bt222=new Button("hou");
              Label complet = null;
          System.out.println("9bal creation de participation fer8a"+id);

                 Participation pp= new Participation(id,rs.getInt(1));
     System.out.println("baed creation w fi wost pp "+pp.getId_user());

              participationCRUD a = new participationCRUD();
              HBox h= new HBox();
              Label inscrit=new Label("");
              
              //3rft chniya el scrol ?eyyy
              if (a.chercherparticipation(pp)){
              System.out.println("wsol lel chercher"+id);

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
                      
                     a.insert(pp);//insert participation
                         PDF pdf = new PDF();
                     try {
                         pdf.pdf(e);
                     } catch (SQLException ex) {
                         Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (FileNotFoundException ex) {
                         Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (DocumentException ex) {
                         Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IOException ex) {
                         Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                     }
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
    System.out.println("awel l addichage"+id);
     EventCRUD sp = new EventCRUD();
     System.out.println("mes part"+id);
      List events=sp.displaymesevent(id);
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
                   
        }if(cc.getDate().before(new Timestamp(new Date().getTime())) ){
           JOptionPane.showMessageDialog(null, "l evenement est deja passé vous pouvez pas annuler cette ");

        }else{
          bb.decrementer(cc.getNbpart()+1,cc.getIdEvent());

            cs.deleteparticipation(cc.getIdEvent(),id);

    
           afficher();
           
           JOptionPane.showMessageDialog(null, "participation  supprimé");

        cc=null;
    }
    }
    public void display_passevents()throws SQLException{
             System.out.println("hedha l id eli connecté pour verifier : "+ id);
          String req="select * from event where date < NOW()";
          List<VBox> list = new ArrayList<>();
          ste=cnx.createStatement();
          ResultSet rs = ste.executeQuery(req);
          while(rs.next()){
             Evenement e= new Evenement(rs.getInt(1), rs.getString(2),rs.getInt(5),rs.getString(6),rs.getString(7));
              ImageView va=new ImageView(new Image(rs.getString(8)));
               va.setFitHeight(200);
                va.setFitWidth(743);


              HBox h= new HBox();
              Label inscrit=new Label("");
   


             Label nomm=new Label(" evenement : "+e.getNom()+"");
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
               HBox adres= new HBox();
              adres.setSpacing(10);
              adres.setAlignment(Pos.CENTER);
               adres.getChildren().addAll(local);
               
               VBox v1=new VBox();
               v1.setAlignment(Pos.CENTER);
               v1.setSpacing(10);

               v1.getChildren().addAll(va,No,adres,ins,h);
               list.add(v1);
               
              


          }
          eventcontainerpass.getChildren().clear();
         eventcontainerpass.getChildren().addAll(list);
        
    }


    @FXML
    private void aff(Event event) throws SQLException, FileNotFoundException, DocumentException {
                    display_events();
                    System.out.println("offff"+id);

    }

    @FXML
    private void affpassee(Event event) throws SQLException {
             display_passevents();

    }

   
     
}
