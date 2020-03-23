/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererCours;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
    }

    @FXML
    private void importer(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) {
    }
    
}
