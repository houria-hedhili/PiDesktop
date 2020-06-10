/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererCours;


import ConnexionBd.connexionBd;
import Entity.houria.Cours;
import Entity.houria.Evenement;
import Entity.houria.Matiere;
import com.jfoenix.controls.JFXTimePicker;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import service.houria.CoursCRUD;
import service.houria.matiereCRUD;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CoursController implements Initializable {

    @FXML
    private ImageView imageview;
    @FXML
    private Button Cajouter;
    @FXML
    private Button Cimage;
    @FXML
    private Button Csupprimer;
    @FXML
    private Button Cmodifier;
    @FXML
    private TableView<Cours> table_cours;
    @FXML
    private TableColumn<?, ?> colmatiere;
    @FXML
    private TableColumn<?, ?> coldescription;
    @FXML
    private TableColumn<?, ?> colplace;
    @FXML
    private TableColumn<?, ?> colduree;
    @FXML
    private TableColumn<?, ?> colage;
    @FXML
    private TableColumn<?, ?> colimage;
    @FXML
    private TextArea Cdescr;
    @FXML
    private TextField Cnbplace;
    @FXML
    private TextField Cage;
    @FXML
    private ComboBox<String> Cmatiere;
    @FXML
    private JFXTimePicker Cduree;
    
            String img="";
    List<String> type;
        Cours hou=new Cours();

        connexionBd connection = null;
        private Connection cnx;
                        Matiere p = new Matiere();
    @FXML
    private Label labelmatiere;
    @FXML
    private Label labeldescription;
    @FXML
    private Label labelseats;
    @FXML
    private Label labelduree;
    @FXML
    private Label labelimage;
    @FXML
    private Label labelage;
    @FXML
    private TextField search;

           private Cours evenn=null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                cnx= connexionBd.getInstance().getCnx();

       afficher();
        type =new ArrayList();
        type.add("*.jpg");
         type.add("*.png");
  table_cours.setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
                evenn = (Cours)table_cours.getSelectionModel().getSelectedItem();
  //kenet tekhdem wala awel mara taa3melha? kont nesyetha aslnn ma5demthech emaa f event amlaa keka w te5demm mafhmtch lena chbeha 
//  System.out.println(evenn.getImage());//yekhou feha null 
                imageview.setImage(new Image("file:/C:/wamp/www/jardin1/web/images/courses/"+evenn.getImage()));//het path !! 
           //chouf maach mesa hh 
           LocalTime d1 = evenn.getDuree().toLocalTime();
           
                    matiereCRUD oui = new matiereCRUD();
        Matiere b=new Matiere();
          
               try {
                   Cmatiere.setValue(oui.getnomat(evenn.getId_mat()));//bech nhot fiha nom de la matiere eliii id mta3ha =evenn.getidmat
               } catch (SQLException ex) {
                   Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);
               }
                Cduree.setValue(d1);
                
                int place=evenn.getSeats();
                String nb_PPP=String.valueOf(place);
                Cnbplace.setText(nb_PPP);
                int ag=evenn.getAge();
                String age=String.valueOf(ag);
                Cage.setText(age);
                Cdescr.setText(evenn.getDescription());
           }
             
         });
        ObservableList<String> availableChoices = FXCollections.observableArrayList("Selectionner matiere");
        matiereCRUD a=new matiereCRUD();
        ObservableList<Matiere> b=a.displayALLMatiere() ;

            String req = "SELECT * FROM matiere";
            Statement pst;
        try {
            pst = cnx.createStatement();
            
              ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setCoefficient(rs.getInt("coeff"));
               availableChoices.add(p.getNom());
               
        Cmatiere.setItems(availableChoices);
        Cmatiere.getSelectionModel().selectFirst();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException, SQLException, NexmoClientException {

       labelmatiere.setText("");
       labeldescription.setText("");
       labelseats.setText("");//saybb lahdha
        labelduree.setText(""); ;
        labelage.setText(""); 
        labelimage.setText(""); 
               try {
             Time valueOf = java.sql.Time.valueOf(Cduree.getValue());
            } catch (Exception e) {
                labelduree.setText("duree vide");

            }
        

        if(Cmatiere.getSelectionModel().getSelectedItem().equals("Selectionner matiere")||Cdescr.getText().isEmpty()||Cage.getText().isEmpty()||Cnbplace.getText().isEmpty()||img=="" ){
         
         if (Cmatiere.getSelectionModel().getSelectedItem().equals("Selectionner matiere")) {
            labelmatiere.setText("il faut selectionner une matiere ");
        }
         if (Cdescr.getText().isEmpty()) {
           labeldescription.setText("Champ description vide"); 
        }
         if (Cage.getText().isEmpty()) {
            labelage.setText("Champ Adresse vide");
        } 
         if (Cnbplace.getText().isEmpty()) {
            labelseats.setText("Champ nombre participant vide");
        } 
         if ("".equals(img)) {
            labelimage.setText("aucune image");
        } 
         
        }else{
           if(!Cage.getText().matches("^([1-9][0-9]{0,4}|31)$")||!Cnbplace.getText().matches("^([1-9][0-9]{0,4}|31)$")) { 
               
            if(!Cage.getText().matches("^([1-9][0-9]{0,4}|31)$")){
           labelage.setText("(il faut que l age soit de type entier   )");
            }else{
                if(Integer.parseInt(Cage.getText())<3){
                               labelage.setText("(il faut que l age soit superieur a 3   )");
                }

            }
            if(!Cnbplace.getText().matches("^([1-9][0-9]{0,4}|31)$")){
           labelseats.setText("(il faut que le nombre de place soit de type entier et superieur a 3   )");
            }else{
                if(Integer.parseInt(Cnbplace.getText())<0){
                               labelage.setText("(il faut que le nombre de place superieure a zero   )");
                }

            }
            
           }else {
          String descC = Cdescr.getText();
        
        LocalTime dur =Cduree.getValue();
        Time dureeC = java.sql.Time.valueOf(dur);
       
        int seats= Integer.parseInt(Cnbplace.getText());
        int age= Integer.parseInt(Cage.getText());
        
        CoursCRUD sp = new CoursCRUD();
         matiereCRUD oui = new matiereCRUD();
        Matiere b=new Matiere();
        b=oui.getMatiere(Cmatiere.getValue());
        Cours e = new Cours(b.getId(),descC,dureeC,seats,age,img);
        sp.addCours(e); 
                NexmoClient client = new NexmoClient.Builder()
                        .apiKey("0a05460e")
                        .apiSecret("sFEhja9oAyNZlyJE")
                        .build();
              TextMessage message = new TextMessage("COCCINELLE"," 21698565782",
                "Bonjour Mr/mdme , nous avons ajouter un cours dans la matiere : "+b.getNom()+" pour plus de details consulter notre site  ");

               SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

            if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
               System.out.println("Message sent successfully.");
           } else {
                System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
           }
         JOptionPane.showMessageDialog(null, "ajout avec succes");
         Cdescr.clear();
         imageview.setImage(null);
            Cduree.setValue(null);
          Cnbplace.clear();
        Cage.clear();
       
     afficher();
           }}
    }

    @FXML
    private void importer(ActionEvent event) {
                FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png",type));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
            img=fc.getName();
            Image i = new Image("file:/C:/wamp/www/jardin1/web/images/courses/"+img);
           imageview.setImage(i);
        }
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
                CoursCRUD cs = new CoursCRUD();
         Cours cc = (Cours)table_cours.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir event");
                   
        }else{
            cs.deleteCours(cc.getId());
    
           afficher();
           
           JOptionPane.showMessageDialog(null, "event supprimer");

         imageview.setImage(null);
            Cdescr.clear();
         imageview.setImage(null);
            Cduree.setValue(null);
          Cnbplace.clear();
        Cage.clear();
        cc=null;
    }
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException {
                         //  hou = (Cours)table_cours.getSelectionModel().getSelectedItem();

        CoursCRUD cs = new CoursCRUD();
        if(hou== null){
            JOptionPane.showMessageDialog(null, "choisir Cours");
                   
        }else{
         matiereCRUD oui = new matiereCRUD();
        Matiere b=new Matiere();
        b=oui.getMatiere(Cmatiere.getValue());
        System.out.println("hetha id etranger"+b.getId());
                LocalTime dur =Cduree.getValue();
        Time dureeC = java.sql.Time.valueOf(dur);//chnnia mochkletha eh n
        if(img==""){
        Cours a=new Cours(b.getId(),Cdescr.getText(),dureeC,Integer.parseInt(Cnbplace.getText()),Integer.parseInt(Cage.getText()),evenn.getImage());
                   cs.updateCours(a,evenn.getId());

        }else{
                    Cours a=new Cours(b.getId(),Cdescr.getText(),dureeC,Integer.parseInt(Cnbplace.getText()),Integer.parseInt(Cage.getText()),img);
           cs.updateCours(a,evenn.getId());

        }
//hekkifi ihot fiha null 5atera mdeclarya null fhemtikk
           afficher();
        JOptionPane.showMessageDialog(null, "cours modifier");
         imageview.setImage(null);
            Cdescr.clear();
         imageview.setImage(null);
            Cduree.setValue(null);
          Cnbplace.clear();
        Cage.clear();
        hou=null;
        
        }
    }
         private void afficher() {
    
     CoursCRUD sp = new CoursCRUD();
      List events=sp.displayALLCours();
       ObservableList et=FXCollections.observableArrayList(events);
       table_cours.setItems(et);
       colmatiere.setCellValueFactory(new PropertyValueFactory<>("mat"));
       colimage.setCellValueFactory(new PropertyValueFactory<>("photo"));
       colplace.setCellValueFactory(new PropertyValueFactory<>("seats"));
       colduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
       coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
       colage.setCellValueFactory(new PropertyValueFactory<>("age"));

}

    @FXML
    private void recherche(KeyEvent event) {
          CoursCRUD sp = new CoursCRUD();
         search.setOnKeyReleased(
         (   KeyEvent e)->{
             if(search.getText().equals("")){
                 afficher();
             }else{ 
                 try{
                  colmatiere.setCellValueFactory(new PropertyValueFactory<>("mat"));
       colimage.setCellValueFactory(new PropertyValueFactory<>("photo"));
       colplace.setCellValueFactory(new PropertyValueFactory<>("seats"));
       colduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
       coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
       colage.setCellValueFactory(new PropertyValueFactory<>("age"));
              table_cours.getItems().clear();
        table_cours.setItems(sp.rechercheCours(search.getText()));
        
                 } catch (SQLException ex) {
                Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);

                }



             }
         });
    }
         
         
         
}
