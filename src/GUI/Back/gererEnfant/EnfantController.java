/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererEnfant;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.wifek.CrudEnfantService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class EnfantController implements Initializable {

    @FXML
    private TableView<?> tabAffiche1;
    @FXML
    private TableColumn<?, ?> colid1;
    @FXML
    private TableColumn<?, ?> colsexe1;
    @FXML
    private TableColumn<?, ?> colnom1;
    @FXML
    private TableColumn<?, ?> colpre1;
    @FXML
    private TableColumn<?, ?> colage1;
    @FXML
    private TableColumn<?, ?> colidbus1;
    @FXML
    private TableColumn<?, ?> parent1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher1();
    }    
    
    private void afficher1() {
    CrudEnfantService sp = new CrudEnfantService();
      List buss=sp.afficherEnfant1();
       ObservableList et=FXCollections.observableArrayList(buss);
       tabAffiche1.setItems(et);
     ObservableList observableList = FXCollections.observableArrayList(buss);
        colid1.setCellValueFactory(new PropertyValueFactory<>("id"));
        colsexe1.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        colnom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colpre1.setCellValueFactory(new PropertyValueFactory<>("prenom")); 
        colage1.setCellValueFactory(new PropertyValueFactory<>("age")); 
        colidbus1.setCellValueFactory(new PropertyValueFactory<>("id_Bus"));
        parent1.setCellValueFactory(new PropertyValueFactory<>("idParent"));
}
    
}
