/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererAbonnement;

import Entity.imen.abonnement;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.imen.abonnementService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AbonnementController implements Initializable {

    @FXML
    private TableView<abonnement> tableabon;
    @FXML
    private TableColumn<abonnement, String> prenomCol;
    @FXML
    private TableColumn<abonnement, String> nomCol;
    @FXML
    private TableColumn<abonnement, Integer> nbenfantCol;
    @FXML
    private TableColumn<abonnement, Double> tarifCol;
    @FXML
    private TableColumn<abonnement, String> etatCol;
    @FXML
    private TableColumn<abonnement, Date> datedCol;
    @FXML
    private TableColumn<abonnement, Date> datefCol;
    @FXML
    private ComboBox<String> abonCombo;
    @FXML
    private Button modifier;
      abonnementService as=new abonnementService();  
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 ObservableList<String> platList=FXCollections.observableArrayList("paye","nom paye");
 abonCombo.setValue("etat");
 abonCombo.setItems(platList);
 afficher();
    }    

    @FXML
    private void moddifAbon(MouseEvent event) {
  
    abonnement a=tableabon.getSelectionModel().getSelectedItem();
 abonCombo.setValue(a.getEtat());
   
    }

    @FXML
    private void modifier(ActionEvent event) {
   abonnement a=tableabon.getSelectionModel().getSelectedItem();
   System.out.println(a.getId());
   as.modifierAbonEtat(abonCombo.getValue(),a.getId()); 
    afficher();
    }
    public void afficher()
    {   List<abonnement> ls=as.afficherAllBack();
        ObservableList<abonnement> abonListe=FXCollections.observableArrayList(ls);
        datedCol.setCellValueFactory(new PropertyValueFactory<>("dated"));
    datefCol.setCellValueFactory(new PropertyValueFactory<>("datef"));
etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
    tarifCol.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));

    nbenfantCol.setCellValueFactory(new PropertyValueFactory<>("nbEnfant"));
     tableabon.setItems(abonListe);
    
    
    }
    
}
