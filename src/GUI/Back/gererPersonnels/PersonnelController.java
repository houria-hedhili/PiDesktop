/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererPersonnels;

import Entity.aziza.Personnel;
import Entity.houria.Evenement;
import Entity.wifek.enfant;

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
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import service.aziza.CategorieDao;
import service.aziza.PersonnelDao;
import service.houria.EventCRUD;
import service.wifek.CrudBusService;
import service.wifek.CrudEnfantService;

/**
 * FXML Controller class
 *
 * @author GLOBALINFO
 */
public class PersonnelController implements Initializable {

    @FXML
    private Label azii;
    @FXML
    private TableView<?> tab;
    @FXML
    private TableColumn<?, ?> NomColonne;
    @FXML
    private TableColumn<?, ?> PrenomColonne;
    @FXML
    private TableColumn<?, ?> AgeColonne;
    @FXML
    private TableColumn<?, ?> Nb;
    @FXML
    private TableColumn<?, ?> Prix;
    @FXML
    private TableColumn<?, ?> CategorieColonne;
    @FXML
    private TableColumn<?, ?> ImageColonne;
    @FXML
    private TextArea nom;
    @FXML
    private TextArea prenom;
    @FXML
    private TextArea prixheure;
    @FXML
    private TextArea aagee;
    @FXML
    private TextArea nombreheure;
    @FXML
    private ImageView imageview;
       String img="";
    List<String> type;
    @FXML
    private Button Eimage;
    @FXML
    private Button btn;
    @FXML
    private Button btnmod;
    @FXML
    private Button btnsupp;
    @FXML
    private ComboBox<String> cat;
private Personnel evenn=null;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherPer();
        type =new ArrayList();
        type.add("*.jpg");
         type.add("*.png");
        tab.setOnMouseClicked((MouseEvent event) -> {
            evenn = (Personnel)tab.getSelectionModel().getSelectedItem();
            System.out.println(evenn);
            nom.setText(evenn.getNom());
            imageview.setImage(new Image(evenn.getImage()));
            int age=evenn.getAge();
            String nb_PPP=String.valueOf(age);
            aagee.setText(nb_PPP);
            float nb=(float) evenn.getNb_h();
            String nbh=String.valueOf(nb);
            nombreheure.setText(nb_PPP);
            float prix=(float) evenn.getPrix_h();
            String prixx=String.valueOf(prix);
            prixheure.setText(prixx);
            prenom.setText(evenn.getPrenom());
        });
        // TODO
    }    

    @FXML
    private void importer(ActionEvent event) {FileChooser f=new FileChooser();
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
    private void AjouterPersonnel(ActionEvent event) throws IOException, SQLException {
CategorieDao b1= new CategorieDao();
        PersonnelDao b= new PersonnelDao();
    if (!aagee.getText().matches("[3-6]")){
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.setTitle("Valider Age");
            alert1.setHeaderText(null);
            alert1.setContentText("L'âge doit être entre 3 ans et 6 ans");
            alert1.showAndWait();
    }else if(!nom.getText().matches("^[a-zA-Z\\s]*$")){
            Alert alert11 = new Alert(Alert.AlertType.ERROR);
            alert11.setTitle("Verifier");
            alert11.setHeaderText(null);
            alert11.setContentText("Le champ NOM accepte que les lettres ");
            alert11.showAndWait();
         }else if( !prenom.getText().matches("^[a-zA-Z\\s]*$")){
         Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Verifier");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ PRENOM accepte que les lettres ");
            alert1.showAndWait();
         
         }else{ 
             
        Personnel categorie=new Personnel(nom.getText(),
            prenom.getText(),
            
            Integer.parseInt(aagee.getText()),Float.parseFloat(prixheure.getText()),Float.parseFloat(nombreheure.getText()),
       b1.getIdCategorie(cat.getValue()),img);   
    b.addPersonnel(categorie);

        System.out.println( b1.getIdCategorie(cat.getValue()));
    
    Notifications notif=Notifications.create()
            .title("Bus ajouté")
            .text("Un nouveau Bus vient d'être ajoutée !")
            .darkStyle().graphic(null).hideAfter(Duration.seconds(5))
            .position(Pos.TOP_LEFT)
            .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked ont notif");
                    }
                });
    notif.showConfirm();
    }
            
             afficherPer();

  // clearEnfant(event);
    }
    

    @FXML
    private void ModifierPersonnel(ActionEvent event) throws IOException {
        PersonnelDao cs = new PersonnelDao();
        
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
           Personnel e = new Personnel(nom.getText(),prenom.getText(),Integer.parseInt(aagee.getText()),Float.parseFloat(prixheure.getText()),Float.parseFloat(nombreheure.getText()),img);
        cs.updatePersonnel(e,evenn.getId());

       }else
           cs.updatePersonnel(new Personnel(nom.getText(),prenom.getText(),Integer.parseInt(aagee.getText()),Float.parseFloat(prixheure.getText()),Float.parseFloat(nombreheure.getText()),evenn.getImage()),evenn.getId());
           
       afficherPer();
        JOptionPane.showMessageDialog(null, "personnel modifier");
       nom.clear();
         imageview.setImage(null);
            prenom.clear();
          aagee.clear();
        //Edate_deb.setValue(null);
       prixheure.clear();
       nombreheure.clear();
        evenn=null;
        }

    }

    @FXML
    private void SuppPersonnet(ActionEvent event) throws SQLException {
       PersonnelDao cs = new PersonnelDao();
    
         Personnel cc = (Personnel)tab.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir personnel");
                   
        }else{
            cs.deletePersonnel(cc.getId());
    
           afficherPer();
           
           JOptionPane.showMessageDialog(null, "personnel supprimer");
         nom.clear();
         imageview.setImage(null);
            prenom.clear();
          aagee.clear();
        //Edate_deb.setValue(null);
       prixheure.clear();
       nombreheure.clear();
        cc=null;
    }
    }
       private void afficherPer()  {
    
    CategorieDao sp = new CategorieDao();
     
          List<Personnel> liste=sp.getCategorie();
       ObservableList et=FXCollections.observableArrayList(liste);
       tab.setItems(et);//  ouiii chnouwa 5orm hedha ???  
       NomColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
       ImageColonne.setCellValueFactory(new PropertyValueFactory<>("photo"));
       PrenomColonne.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       AgeColonne.setCellValueFactory(new PropertyValueFactory<>("age"));
       Nb.setCellValueFactory(new PropertyValueFactory<>("nb_h"));
       Prix.setCellValueFactory(new PropertyValueFactory<>("prix_h"));
       CategorieColonne.setCellValueFactory(new PropertyValueFactory<>("categorie"));
      /* adress.setCellValueFactory(new PropertyValueFactory<>("local"));
       Id_event.setCellValueFactory(new PropertyValueFactory<>("idEvent"));*/

}
    
}
