/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererCatRec;

import ConnexionBd.connexionBd;
import Entity.ameni.categorieReclamation;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import service.ameni.categorieReclamationService;


/**
 * FXML Controller class
 *
 * @author User
 */
public class CategorieReclamationController implements Initializable {

    @FXML
    private Label CategorieReclamation;
    @FXML
    private Label nomLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private TextField nomText;
    @FXML
    private TextField descriptionText;
    @FXML
    private TableView<categorieReclamation> tab;
    @FXML
    private TableColumn<categorieReclamation, Integer> colRef;
    @FXML
    private TableColumn<categorieReclamation, String> colNom;
    @FXML
    private TableColumn<categorieReclamation, String> colDescription;
    @FXML
    private Button AjouterBt;
    @FXML
    private Button ModifierBt;
    @FXML
    private Button SupprimerBt;
    
    categorieReclamationService categ=new categorieReclamationService();
    @FXML
    private TextField filtreText;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              afficher();     
        // TODO
    }    

    
      private void afficher() {
      categorieReclamationService sp = new categorieReclamationService();
        List categ=sp.affcatRec();
        ObservableList et=FXCollections.observableArrayList(categ);
        tab.setItems(et);
        ObservableList observableList = FXCollections.observableArrayList(categ);
        colRef.setCellValueFactory(new PropertyValueFactory<>("ref"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));   
        }
      
        private void affichecatrec(ActionEvent event) {
        
      categorieReclamationService sp = new categorieReclamationService();
      List categ=sp.affcatRec();
       ObservableList et=FXCollections.observableArrayList(categ);
       tab.setItems(et);
        ObservableList observableList = FXCollections.observableArrayList(categ);
     //en vert hekom mil interface tu les recuperes m tableau eli samitou tab
     //en oranger hekom les entités mte3ek
        colRef.setCellValueFactory(new PropertyValueFactory<>("ref"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }
        
     private void clearTab() {
    nomText.clear();
    descriptionText.clear();    }
        
        private void refreshB(ActionEvent event) throws SQLException{
        List<categorieReclamation> listC=new ArrayList<>();
        categorieReclamationService   cr = new categorieReclamationService();
        listC = cr.affcatRec();
        ObservableList <categorieReclamation> data = FXCollections.observableArrayList(listC);
        tab.setItems(data);
    }

    @FXML
    private void handleButtonActionAjouter(ActionEvent event) {
        categorieReclamationService b= new categorieReclamationService();
    if( nomText.getText().isEmpty() || descriptionText.getText().isEmpty()){
    Alert alertt=new Alert(Alert.AlertType.ERROR);
    alertt.setTitle("WARNING!");
    alertt.setHeaderText(null);
    alertt.setContentText("Merci de vérifier que vous avez remplit tout les champs");
    alertt.showAndWait();
    }
    else if(!nomText.getText().matches("^[a-zA-Z\\s]*$") || !descriptionText.getText().matches("^[a-zA-Z\\s]*$"))
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre champs"); 
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ nom accepte que les lettres ");
            alert1.showAndWait();
         }
    else{ categorieReclamation categorieRec=new categorieReclamation(nomText.getText(),
            descriptionText.getText()
    );   
    b.ajouterCategorieReclamation(categorieRec);
        //hedhi fiha controle de saisie w notificcatiobs akeli tji
    Notifications notif=Notifications.create()
            .title("Categorie ajouté")
            .text("Une nouvelle categorie vient d'être ajoutée !")
            .darkStyle().graphic(null).hideAfter(Duration.seconds(5))
            .position(Pos.CENTER)
            .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked ont notif");
                    }
                });
    notif.showConfirm();
    }
   // refreshB(event);
   affichecatrec(event);
    }
    

    @FXML
    private void handleButtonActionModifier(ActionEvent event) throws SQLException {
      categorieReclamationService cs = new categorieReclamationService();
      categorieReclamation b=tab.getSelectionModel().getSelectedItem();
      cs.modifierCategorieReclamation(nomText.getText(),descriptionText.getText(), b.getRef());
      affichecatrec(event);
      clearTab();//tfasa5 les cases ken m3ebyin
      //refreshB(event);
    }

    @FXML
    private void suppcat(ActionEvent event) {
         categorieReclamationService rs = new categorieReclamationService();
         categorieReclamation cc = (categorieReclamation)tab.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir ");
        }else{
            rs.supprimerCat(cc.getRef());
            affichecatrec(event);
            //refreshB(event);
            afficher();
           JOptionPane.showMessageDialog(null, "supprimer");
         nomText.clear();
         descriptionText.clear();
        cc=null;
    }
    }

    @FXML
    private void modifCat(MouseEvent event) {
     categorieReclamation b=tab.getSelectionModel().getSelectedItem();
    nomText.setText(b.getNom());
    descriptionText.setText(b.getDescription());
    }
        //beh nheb naaml jointure bin l reclamation wl categorie mteou
   // d'/u/ne facon li f affichage yjini l nom ml l cle  pokrk ifhmeametik fhemtik cbn ok
    //heli scene builder reclamation
           /* private void afficheCher(ActionEvent event) {
        filtreText.setOnKeyReleased(e->{
            
        });
      categorieReclamationService sp = new categorieReclamationService();
      List categ=sp.chercherCat();
       ObservableList et=FXCollections.observableArrayList(categ);
       tab.setItems(et);
        ObservableList observableList = FXCollections.observableArrayList(categ);
     //en vert hekom mil interface tu les recuperes m tableau eli samitou tab
     //en oranger hekom les entités mte3ek
        colRef.setCellValueFactory(new PropertyValueFactory<>("ref"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }*/
       
}
