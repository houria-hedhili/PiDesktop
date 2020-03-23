/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererEnfant;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.wifek.CrudEnfantService;

/**
 * FXML Controller class
 *
 * @author Imen
 */
public class EnfantController implements Initializable {
    @FXML
    private TabPane tabpane;
    @FXML
    private Button retour1;
    @FXML
    private Button retour2;
    @FXML
    private Button retour3;
    @FXML
    private Button retour4;
    @FXML
    private TableView<?> tabAffiche;
    @FXML
    private TableColumn<?, ?> colid;
    @FXML
    private TableColumn<?, ?> colsexe;
    @FXML
    private TableColumn<?, ?> colnom;
    @FXML
    private TableColumn<?, ?> colpre;
    @FXML
    private TableColumn<?, ?> colage;
    @FXML
    private TableColumn<?, ?> colidbus;
    @FXML
    private TableColumn<?, ?> colidp;
//hethi khedmtik linaa
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO               
        afficher();

    }    
    
            private void afficher() {
    CrudEnfantService sp = new CrudEnfantService();
      List buss=sp.afficherEnfant();
       ObservableList et=FXCollections.observableArrayList(buss);
       tabAffiche.setItems(et);
     ObservableList observableList = FXCollections.observableArrayList(buss);
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colsexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colpre.setCellValueFactory(new PropertyValueFactory<>("prenom")); 
        colage.setCellValueFactory(new PropertyValueFactory<>("age")); 
        colidbus.setCellValueFactory(new PropertyValueFactory<>("id_Bus"));
        colidp.setCellValueFactory(new PropertyValueFactory<>("idParent"));


}

    @FXML
    private void retour1(ActionEvent event) throws IOException {

        Stage stage = (Stage) retour1.getScene().getWindow();
    stage.close();
      Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Acceuilfront/acceuilFront.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
    }

    @FXML
    private void retour2(ActionEvent event) throws IOException {
              Stage stage = (Stage) retour2.getScene().getWindow();
               Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Acceuilfront/acceuilFront.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
    stage.close();
     
    }

    @FXML
    private void retour3(ActionEvent event) throws IOException {
       Stage stage = (Stage) retour1.getScene().getWindow();
      Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Acceuilfront/acceuilFront.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
       stage.close();
    }

    @FXML
    private void retour4(ActionEvent event) throws IOException {
   Stage stage = (Stage) retour1.getScene().getWindow();
    Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Acceuilfront/acceuilFront.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
    stage.close();
     
    }
    
}
