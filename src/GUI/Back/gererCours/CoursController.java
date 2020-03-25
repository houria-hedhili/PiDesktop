/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererCours;

import Entity.houria.Cours;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import service.houria.CoursCRUD;

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
    private TableView<?> table_cours;
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
    private ComboBox<?> Cmatiere;
    @FXML
    private JFXTimePicker Cduree;
    
            String img="";
    List<String> type;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficher();
        type =new ArrayList();
        type.add("*.jpg");
         type.add("*.png");
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
          String descC = Cdescr.getText();
        
        LocalTime dur =Cduree.getValue();
        Time dureeC = java.sql.Time.valueOf(dur);
       
        int seats= Integer.parseInt(Cnbplace.getText());
        int age= Integer.parseInt(Cage.getText());
        
        CoursCRUD sp = new CoursCRUD();
        Cours e = new Cours(4,descC,dureeC,seats,age,img);
        sp.addCours(e); 
         JOptionPane.showMessageDialog(null, "ajout avec succes");
         Cdescr.clear();
         imageview.setImage(null);
            Cduree.setValue(null);
          Cnbplace.clear();
        Cage.clear();
        afficher();
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
    private void modifier(ActionEvent event) {
    }
         private void afficher() {
    
     CoursCRUD sp = new CoursCRUD();
      List events=sp.displayALLCours();
       ObservableList et=FXCollections.observableArrayList(events);
       table_cours.setItems(et);
       colmatiere.setCellValueFactory(new PropertyValueFactory<>("id_mat"));
       colimage.setCellValueFactory(new PropertyValueFactory<>("photo"));
       colplace.setCellValueFactory(new PropertyValueFactory<>("seats"));
       colduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
       coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
       colage.setCellValueFactory(new PropertyValueFactory<>("age"));

}
}
