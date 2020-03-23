/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererEvent;

import Entity.houria.Evenement;
import service.houria.EventCRUD;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private DatePicker Edate_fin;
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
    private TableView<?> table_event;
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
                Enom.setText(evenn.getNom());
                imageview.setImage(new Image(evenn.getImage()));
                
                
               // LocalDate d1=evenn.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
               // LocalDate d2=evenn.getDate_fin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
               // Edate_deb.setValue(d1);
                //Edate_fin.setValue(d2);
                
                
                int place=evenn.getNbpart();
                String nb_PPP=String.valueOf(place);
                Enbpart.setText(nb_PPP);
                Edescription.setText(evenn.getDescription());
                Eadresse.setText(evenn.getLocal());
           }
             
         });
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
          String titreE = Enom.getText();
        String adresseE=Eadresse.getText();
        String descriptionE=Edescription.getText();
       // LocalDate dd =Edate_deb.getValue();
       // LocalDate df =Edate_fin.getValue();
       // Date date_debutE = java.sql.Date.valueOf(dd);
        //Date date_finE = java.sql.Date.valueOf(df);
        int nb_place= Integer.parseInt(Enbpart.getText());
        EventCRUD sp = new EventCRUD();
        Evenement e = new Evenement(descriptionE,nb_place,titreE,adresseE,img);
        sp.addEvent(e); 
         JOptionPane.showMessageDialog(null, "ajout avec succes");
         Enom.clear();
         imageview.setImage(null);
            Edescription.clear();
          Eadresse.clear();
        //Edate_deb.setValue(null);
        Edate_fin.setValue(null);
        Enbpart.clear();
        afficher();
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
               EventCRUD cs = new EventCRUD();
        
        System.out.println(evenn);
        if(evenn== null){
            JOptionPane.showMessageDialog(null, "choisir event");
                   
        }else{

                   // LocalDate dd=Edatedebut.getValue();
       // LocalDate df=Edatefin.getValue();
        //java.util.Date d1=java.sql.Date.valueOf(dd);
        //java.util.Date d2=java.sql.Date.valueOf(df);
       //int nb_place= Integer.parseInt(nb_part.getText());
       if(img!=""){
           Evenement e = new Evenement(Edescription.getText(),Integer.parseInt(Enbpart.getText()),Enom.getText(),Eadresse.getText(),img);
        cs.updateEvent(e,evenn.getIdEvent());

       }else
           cs.updateEvent(new Evenement(Edescription.getText(),Integer.parseInt(Enbpart.getText()),Enom.getText(),Eadresse.getText(),evenn.getImage()),evenn.getIdEvent());
           
       afficher();
        JOptionPane.showMessageDialog(null, "event modifier");
        Enom.clear();
         imageview.setImage(null);
                
        //Edatedebut.setValue(null);
        //Edatefin.setValue(null);
        Enbpart.clear(); 
        Edescription.clear();
        Eadresse.clear();
        evenn=null;
        }
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
        //Edate_deb.setValue(null);
        Edate_fin.setValue(null);
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
            System.out.println(fc.getName());
            img=fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img);
           imageview.setImage(i);
        }
    }
    private void afficher() {
    
     EventCRUD sp = new EventCRUD();
      List events=sp.displayALLEvent();
       ObservableList et=FXCollections.observableArrayList(events);
       table_event.setItems(et);//  ouiii chnouwa 5orm hedha ???  
       nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       timage.setCellValueFactory(new PropertyValueFactory<>("photo"));
       dat_d.setCellValueFactory(new PropertyValueFactory<>("date"));
       date_f.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
       nb_part.setCellValueFactory(new PropertyValueFactory<>("nbpart"));
       descrip.setCellValueFactory(new PropertyValueFactory<>("description"));
      /* adress.setCellValueFactory(new PropertyValueFactory<>("local"));
       Id_event.setCellValueFactory(new PropertyValueFactory<>("idEvent"));*/

}
    
}
