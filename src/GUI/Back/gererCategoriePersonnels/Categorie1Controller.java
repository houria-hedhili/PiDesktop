/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererCategoriePersonnels;


import Entity.aziza.Categorie;
import Entity.houria.Evenement;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import service.aziza.CategorieDao;
import service.houria.EventCRUD;

/**
 * FXML Controller class
 *
 * @author GLOBALINFO
 */
public class Categorie1Controller implements Initializable {

    @FXML
    private TableView<Categorie> tab;
    @FXML
    private TableColumn<?, ?> TypeColonne;
    @FXML
    private TableColumn<?, ?> DescriptionColonne;
    @FXML
    private TextArea type;
    @FXML
    private TextArea description;
    @FXML
    private Button btn;
    @FXML
    private Button btn_supp;
CategorieDao buss=new CategorieDao();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        // TODO
    }    

    @FXML
    private void Ajoutcat(ActionEvent event) throws SQLException {
     CategorieDao b= new CategorieDao();
    if( type.getText().isEmpty() || description.getText().isEmpty() ){
    Alert alertt=new Alert(Alert.AlertType.ERROR);
    alertt.setTitle("WARNING!");
    alertt.setHeaderText(null);
    alertt.setContentText("Merci de vérifier que vous avez remplit tout les champs");
    alertt.showAndWait();
    }else if(!type.getText().matches("^[a-zA-Z\\s]*$")){    
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider ");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez verifier ltype  ");
            alert1.showAndWait();
         }
    else if(!description.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider desc");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ desc accepte que les lettres ");
            alert1.showAndWait();
         }
    else{ Categorie cat=new Categorie(type.getText(),
        
            description.getText()
    );   
    b.insert(cat);
    afficher();
    
      type.clear();
    description.clear();
      
    
    Notifications notif=Notifications.create()
            .title("Categorie personnel ajouté")
            .text("Une nouvelle categorie vient d'être ajoutée !")
            .darkStyle().graphic(null).hideAfter(Duration.seconds(5))
            .position(Pos.TOP_LEFT)
            .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked ont notif");
                    }
                });
    notif.showConfirm();
    }
   
    }
    
    
        private void afficher() {
    CategorieDao sp = new CategorieDao();
      List buss=sp.displayAll();
       ObservableList et=FXCollections.observableArrayList(buss);
       tab.setItems(et);
     ObservableList observableList = FXCollections.observableArrayList(buss);
        TypeColonne.setCellValueFactory(new PropertyValueFactory<>("type"));
        DescriptionColonne.setCellValueFactory(new PropertyValueFactory<>("description"));

}

    @FXML
    private void Suppcat(ActionEvent event) throws SQLException {
          CategorieDao cs = new CategorieDao();
         Categorie cc = (Categorie)tab.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir une catégorie");
                   
        }else{
            cs.deleteCat(cc.getId());
    
           afficher();
           
           JOptionPane.showMessageDialog(null, "catégorie supprimer avec succes");
         type.clear();
         
          description.clear();
        
        cc=null;
    }
         
       
       
    }
    
   
   private void refreshB(ActionEvent event) throws SQLException{
        List<Categorie> listB=new ArrayList<>();
        CategorieDao   cr = new CategorieDao();
       listB = cr.displayAll();
        ObservableList <Categorie> data = FXCollections.observableArrayList(listB);
      tab.setItems(data);
    }

    private void clearCat() {
    type.clear();
    description.clear();
      }
    
    }
    

