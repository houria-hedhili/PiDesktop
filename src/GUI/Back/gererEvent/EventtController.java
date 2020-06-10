/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererEvent;

import Entity.houria.Evenement;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import service.houria.EventCRUD;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class EventtController implements Initializable {

    @FXML
    private Button Eajouter;
    @FXML
    private Button Emodifier;
    @FXML
    private Button Esupprimer;
    @FXML
    private DatePicker Edate_deb;
    @FXML
    private TextField Enom;
    @FXML
    private TextField Enbpart;
    @FXML
    private Button Eimage;
    @FXML
    private TextArea Edescription;
    @FXML
    private TextField Eadresse;
    @FXML
    private ImageView imageview;
            String img="";
    List<String> type;
    @FXML
    private TableView<Evenement> table_event;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> timage;
    @FXML
    private TableColumn<?, ?> dat_d;
    @FXML
    private TableColumn<?, ?> date_f;
    @FXML
    private TableColumn<?, ?> nb_part;
    @FXML
    private TableColumn<?, ?> descrip;
        private Evenement evenn=null;
    @FXML
    private TableColumn<?, ?> adress;
    @FXML
    private Label labelnom;
    @FXML
    private Label labeldescription;
    @FXML
    private Label labeldatedebut;
    @FXML
    private Label labeldatefin;
    @FXML
    private Label labelimage;
    @FXML
    private Label labeladresse;
    @FXML
    private Label labelnbpart;

    @FXML
    private JFXTimePicker ETime_debut;
    @FXML
    private JFXTimePicker ETime_fin;
    @FXML
    private TextField search;


    /**
     * Initializes the controller class.
     */
 @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficher();
        type =new ArrayList();
        type.add("*.jpg");
         type.add("*.png");
        table_event.setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
                evenn = (Evenement)table_event.getSelectionModel().getSelectedItem();
                System.out.println(evenn);
                 System.out.println("wsol lena");

                Enom.setText(evenn.getNom());
                imageview.setImage(new Image("file:/C:/wamp/www/jardin1/web/images/event-list/"+evenn.getImage()));
                    System.out.println("hedhiiii");
                    //Getting the default zone i
	 System.out.println("wish");	
	//Converting the date to Instant
	System.out.println("instant cv");

	//Converting the Date to LocalDate
	LocalDateTime d1 = evenn.getDate().toLocalDateTime();
	System.out.println("Local Date is: "+d1);
                LocalDateTime d2=evenn.getDate_fin().toLocalDateTime();
                  System.out.println("lena za3ma 1");
                Edate_deb.setValue(d1.toLocalDate() );
               ETime_debut.setValue(d1.toLocalTime());
               ETime_fin.setValue(d2.toLocalTime());
                 System.out.println("lena mela za3ma 2");  
                
                int place=evenn.getNbpart();
                String nb_PPP=String.valueOf(place);
                Enbpart.setText(nb_PPP);
                Edescription.setText(evenn.getDescription());
                Eadresse.setText(evenn.getLocal());
           }
             
         });
    }    //ey hia bidha mte3i haya bara netweklou ala rabi hhh haya hhh by 
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
               labelnom.setText("");
       labeldescription.setText("");
       labeladresse.setText("");
        labelnbpart.setText(""); ;
        labeldatedebut.setText(""); 
        labelimage.setText(""); 

          labeldatefin.setText(""); 
        if(nom.getText().isEmpty()||Edescription.getText().isEmpty()||Enbpart.getText().isEmpty()||Eadresse.getText().isEmpty()||img=="" ||Edate_deb==null||ETime_fin==null ||ETime_debut==null){
         
         if (Edescription.getText().isEmpty()) {
            labeldescription.setText("Champ Description vide");
           // new Alert(Alert.AlertType.ERROR, "Champ Description vide").show();
        }
         if (nom.getText().isEmpty()) {
           labelnom.setText("Champ nom vide"); // hedhi habtch tatl fhmtch pk 
           // new Alert(Alert.AlertType.ERROR, " Champ Nom vide").show();
        }
         if (Eadresse.getText().isEmpty()) {
            labeladresse.setText("Champ Adresse vide");
           // new Alert(Alert.AlertType.ERROR, " Champ Adresse vide").show();
        } 
         if (Enbpart.getText().isEmpty()) {
            labelnbpart.setText("Champ nombre participant vide");
           // new Alert(Alert.AlertType.ERROR, " Champ Adresse vide").show();
        } 
         if ("".equals(img)) {
            labelimage.setText("aucune image");
          //  new Alert(Alert.AlertType.ERROR, " Champ Adresse vide").show();
        } 
         
           try {
             Date valueOf = java.sql.Date.valueOf(Edate_deb.getValue());
             Time  time = java.sql.Time.valueOf(ETime_debut.getValue());
            } catch (Exception e) {
                labeldatedebut.setText("remplir date debut ");

            }
           
            try{
          Time  timed = java.sql.Time.valueOf(ETime_fin.getValue());
       } catch (Exception e) {
              labeldatedebut.setText("remplir time debut");
              //  new Alert(Alert.AlertType.ERROR, " Champ Adresse vide").show();
            }
        
       try{
          Time  timef = java.sql.Time.valueOf(ETime_fin.getValue());
       } catch (Exception e) {
              labeldatefin.setText("remplir time fin");
              //  new Alert(Alert.AlertType.ERROR, " Champ Adresse vide").show();
            }

        }else {
      
                       java.util.Date datecourante = new java.util.Date();
        java.sql.Date sqldatecourante = new java.sql.Date(datecourante.getTime());
            // try to parse the postal code into an int.
 
           if(!Enbpart.getText().matches("^([1-9][0-9]{0,4}|31)$")) {
           labelnbpart.setText("(il faut que nombre de participant soit >0)");

           }
         LocalDate dd =Edate_deb.getValue();

        LocalTime td =ETime_debut.getValue();
        try{
        LocalDateTime ldt= LocalDateTime.of(dd,td);
                java.util.Date date = Date.from( ldt.atZone( ZoneId.systemDefault()).toInstant());
        Timestamp date_debutE = new java.sql.Timestamp(date.getTime());
        }catch(Exception e){
         labeldatedebut.setText("remplir time debut");

        }
        

        
         if (java.sql.Time.valueOf(ETime_debut.getValue()).compareTo(java.sql.Time.valueOf(ETime_fin.getValue())) > 0) {


           labeldatefin.setText("date fin doit etre supérieur ou égal a la date debut");
           // new Alert(Alert.AlertType.ERROR, "date fin doit etre supérieur ou égal a la date debut").show();
        } else if (java.sql.Date.valueOf(Edate_deb.getValue()).compareTo(sqldatecourante)  < 0) {
            labeldatedebut.setText("date debut doit etre supérieur ou egal a la date courante");
           // new Alert(Alert.AlertType.ERROR, "date debut doit etre supérieur ou egal a la date courante").show();
        }  
         else {
        
             LocalDateTime ldt= LocalDateTime.of(dd,td);
                java.util.Date date = Date.from( ldt.atZone( ZoneId.systemDefault()).toInstant());
        Timestamp date_debutE = new java.sql.Timestamp(date.getTime());
        
          String titreE = Enom.getText();
        String adresseE=Eadresse.getText();
        String descriptionE=Edescription.getText();
      
        
       //Timestamp date_debutE = new java.sql.Timestamp(date.getTime());

         LocalTime tf =ETime_fin.getValue();
          LocalDateTime ldtt= LocalDateTime.of(dd,tf);
         java.util.Date datef = Date.from( ldtt.atZone( ZoneId.systemDefault()).toInstant());
        Timestamp date_finE = new java.sql.Timestamp(datef.getTime());
       // temchi Date date_finE = java.sql.Date.valueOf(df);

           //java.util.Date date = Date.from( ldt.atZone( ZoneId.systemDefault()).toInstant());
        // Timestamp date_debutE = new java.sql.Timestamp(date.getTime());
               // System.out.println(sqlDate);

        // Date date_debutE = java.sql.Date.valueOf(dd);
       /*  LocalDateTime ldt=
         java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date dt = new java.util.Date();*/
 
      // java.util.Date dateF = java.util.Date.from(dateFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        //java.sql.Date sqldateD = new java.sql.Date(dateD.getTime());
       int nb_place= Integer.parseInt(Enbpart.getText());
       if(nb_place<0){ 
       labelnbpart.setText("nombre de place doit etre >0");
       
       }else{

        EventCRUD sp = new EventCRUD();
        Evenement e = new Evenement(descriptionE,date_debutE,date_finE,nb_place,titreE,adresseE,img);
        sp.addEvent(e); 
         JOptionPane.showMessageDialog(null, "ajout avec succes");
       }
         Enom.clear();
         imageview.setImage(null);
            Edescription.clear();
          Eadresse.clear();
        Edate_deb.setValue(null);
        ETime_debut.setValue(null);
        ETime_fin.setValue(null);
        Enbpart.clear();
        afficher();
               labelnom.setText("");
       labeldescription.setText("");
       labeladresse.setText("");
        labelnbpart.setText(""); ;
        labeldatedebut.setText(""); 
          labeldatefin.setText(""); 
         labelimage.setText(""); 

    } }} 

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        
                       labelnom.setText("");
       labeldescription.setText("");
       labeladresse.setText("");
        labelnbpart.setText(""); ;
        labeldatedebut.setText(""); 
          labeldatefin.setText(""); 
        if(nom.getText().isEmpty()||Edescription.getText().isEmpty()||Enbpart.getText().isEmpty()||Eadresse.getText().isEmpty()||Edate_deb==null||ETime_fin==null ||ETime_debut==null ){
         
         if (Edescription.getText().isEmpty()) {
            labeldescription.setText("Champ Description vide");
           // new Alert(Alert.AlertType.ERROR, "Champ Description vide").show();
        }
         if (nom.getText().isEmpty()) {
           labelnom.setText("Champ nom vide");
           // new Alert(Alert.AlertType.ERROR, " Champ Nom vide").show();
        }
         if (Eadresse.getText().isEmpty()) {
            labeladresse.setText("Champ Adresse vide");
           // new Alert(Alert.AlertType.ERROR, " Champ Adresse vide").show();
        } 
         if (Enbpart.getText().isEmpty()) {
            labelnbpart.setText("Champ nombre participant vide");
           // new Alert(Alert.AlertType.ERROR, " Champ Adresse vide").show();
        } 

         
           try {
             Date valueOf = java.sql.Date.valueOf(Edate_deb.getValue());

            } catch (Exception e) {
                labeldatedebut.setText("champ date debut vide");

            } 
           try {
           Time tt = java.sql.Time.valueOf(ETime_debut.getValue());

            } catch (Exception e) {
                labeldatedebut.setText("champ date debut vide");

            } 

        
       try{
          Time valueOf = java.sql.Time.valueOf(ETime_debut.getValue());

       } catch (Exception e) {
              labeldatefin.setText("champ time debut vide");
              //  new Alert(Alert.AlertType.ERROR, " Champ Adresse vide").show();
            }
          try{
          Time valueOff = java.sql.Time.valueOf(ETime_fin.getValue());

       } catch (Exception e) {
              labeldatefin.setText("champ time fin vide");
              //  new Alert(Alert.AlertType.ERROR, " Champ Adresse vide").show();
            }

        }else {
      
                       java.util.Date datecourante = new java.util.Date();
        java.sql.Date sqldatecourante = new java.sql.Date(datecourante.getTime());
            // try to parse the postal code into an int.
 
           if(!Enbpart.getText().matches("^([1-9][0-9]{0,4}|31)$")) {
           labelnbpart.setText("(il faut que nombre de participant soit >0)");

           }

         if (java.sql.Time.valueOf(ETime_debut.getValue()).compareTo(java.sql.Time.valueOf(ETime_fin.getValue())) > 0) {


           labeldatefin.setText("date fin doit etre supérieur a la date debut");
           // new Alert(Alert.AlertType.ERROR, "date fin doit etre supérieur ou égal a la date debut").show();
        } else if (java.sql.Date.valueOf(Edate_deb.getValue()).compareTo(sqldatecourante)  < 0) {
            labeldatedebut.setText("date debut doit etre supérieur ou egal a la date courante");
           // new Alert(Alert.AlertType.ERROR, "date debut doit etre supérieur ou egal a la date courante").show();
        }  
         else {
        
            
        
          String titreE = Enom.getText();
        String adresseE=Eadresse.getText();
        String descriptionE=Edescription.getText();
      /*  LocalDate dd =Edate_deb.getValue();
        LocalDate df =Edate_fin.getValue();
        Date date_debutE = java.sql.Date.valueOf(dd);
        Date date_finE = java.sql.Date.valueOf(df);*/
  
        LocalDate dd =Edate_deb.getValue();
        LocalTime td =ETime_debut.getValue();
        LocalDateTime ldt= LocalDateTime.of(dd,td);
        java.util.Date date = Date.from( ldt.atZone( ZoneId.systemDefault()).toInstant());
        Timestamp date_debutE = new java.sql.Timestamp(date.getTime());
        
       //Timestamp date_debutE = new java.sql.Timestamp(date.getTime());

         LocalTime tf =ETime_fin.getValue();
          LocalDateTime ldtt= LocalDateTime.of(dd,tf);
         java.util.Date datef = Date.from( ldtt.atZone( ZoneId.systemDefault()).toInstant());
        Timestamp date_finE = new java.sql.Timestamp(datef.getTime());
       int nb_place= Integer.parseInt(Enbpart.getText());
       if(nb_place<0){ 
       labelnbpart.setText("nombre de place doit etre >0");
       
       }else{
               EventCRUD cs = new EventCRUD();
        
        System.out.println(evenn);
        if(evenn== null){
            JOptionPane.showMessageDialog(null, "choisir event");
        }else{
       if(img!=""){
           Evenement e = new Evenement(Edescription.getText(),date_debutE,date_finE,Integer.parseInt(Enbpart.getText()),Enom.getText(),Eadresse.getText(),img);
        cs.updateEvent(e,evenn.getIdEvent());

       }else
           cs.updateEvent(new Evenement(Edescription.getText(),date_debutE,date_finE,Integer.parseInt(Enbpart.getText()),Enom.getText(),Eadresse.getText(),evenn.getImage()),evenn.getIdEvent());
           
       afficher();
        JOptionPane.showMessageDialog(null, "event modifier");
        Enom.clear();
         imageview.setImage(null);
                
        //Edatedebut.setValue(null);
        //Edatefin.setValue(null);
        Enbpart.clear(); 
        Edescription.clear();
        Eadresse.clear();
                Edate_deb.setValue(null);
        ETime_debut.setValue(null);
          ETime_fin.setValue(null);

        evenn=null;
        }
       }
         Enom.clear();
         imageview.setImage(null);
            Edescription.clear();
          Eadresse.clear();
        Edate_deb.setValue(null);
        Enbpart.clear();
        afficher();
               labelnom.setText("");
       labeldescription.setText("");
       labeladresse.setText("");
        labelnbpart.setText(""); ;
        labeldatedebut.setText(""); 
          labeldatefin.setText(""); 
         labelimage.setText(""); 

    } }
        
        
        
        
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
         EventCRUD cs = new EventCRUD();
         Evenement cc = (Evenement)table_event.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir event");
                   
        }else{
            cs.deleteEvent(cc.getIdEvent());
    
           afficher();
           
           JOptionPane.showMessageDialog(null, "event supprimer");
         Enom.clear();
         imageview.setImage(null);
            Edescription.clear();
          Eadresse.clear();
        Edate_deb.setValue(null);
         ETime_debut.setValue(null);
        ETime_fin.setValue(null);
        Enbpart.clear();
        cc=null;
    }
    }

    @FXML
    private void importer(ActionEvent event) {
        FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png",type));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            //System.out.println(fc.getName());
            img=fc.getName();
            Image i = new Image("file:/C:/wamp/www/jardin1/web/images/event-list/"+img);
           imageview.setImage(i);
        }
    }
    private void afficher() {
    
     EventCRUD sp = new EventCRUD();
      List events=sp.displayALLEvent();
       ObservableList et=FXCollections.observableArrayList(events);
       table_event.setItems(et);
       nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       timage.setCellValueFactory(new PropertyValueFactory<>("photo"));
       dat_d.setCellValueFactory(new PropertyValueFactory<>("date"));
       date_f.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
       nb_part.setCellValueFactory(new PropertyValueFactory<>("nbpart"));
       descrip.setCellValueFactory(new PropertyValueFactory<>("description"));
       adress.setCellValueFactory(new PropertyValueFactory<>("local"));

}

    @FXML
    private void recherche(KeyEvent event) {
             EventCRUD sp = new EventCRUD();
         search.setOnKeyReleased(
         (   KeyEvent e)->{
             if(search.getText().equals("")){
                 afficher();
             }else{ 
                 try{
                  nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       timage.setCellValueFactory(new PropertyValueFactory<>("photo"));
       dat_d.setCellValueFactory(new PropertyValueFactory<>("date"));
       date_f.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
       nb_part.setCellValueFactory(new PropertyValueFactory<>("nbpart"));
       descrip.setCellValueFactory(new PropertyValueFactory<>("description"));
       adress.setCellValueFactory(new PropertyValueFactory<>("local"));
              table_event.getItems().clear();
        table_event.setItems(sp.rechercheEvent(search.getText()));
        
                 } catch (SQLException ex) {
                Logger.getLogger(EventtController.class.getName()).log(Level.SEVERE, null, ex);

                }



             }
         });
        
    }
    
    
}
