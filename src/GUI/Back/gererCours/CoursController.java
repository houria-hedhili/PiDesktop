/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererCours;


import ConnexionBd.connexionBd;
import Entity.houria.Cours;
import Entity.houria.Matiere;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private ComboBox<String> Cmatiere;
    @FXML
    private JFXTimePicker Cduree;
    
            String img="";
    List<String> type;
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
    private void ajouter(ActionEvent event) throws IOException, SQLException {

       labelmatiere.setText("");
       labeldescription.setText("");
       labelseats.setText("");
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
         JOptionPane.showMessageDialog(null, "ajout avec succes");
         Cdescr.clear();
         imageview.setImage(null);
            Cduree.setValue(null);
          Cnbplace.clear();
        Cage.clear();
        afficher();
    }}}

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
