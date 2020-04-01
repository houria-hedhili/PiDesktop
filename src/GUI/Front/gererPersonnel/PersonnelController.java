/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererPersonnel;

import Entity.aziza.Personnel;
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
import service.aziza.CategorieDao;

/**
 * FXML Controller class
 *
 * @author Imen
 */
public class PersonnelController implements Initializable {
    @FXML
    private TabPane tabpane;
    @FXML
    private Button retour1;
    private Button retour2;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> pre;
    @FXML
    private TableColumn<?, ?> catt;
    @FXML
    private TableColumn<?, ?> image;
    @FXML
    private TableView<?> tabb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherPer1();
        // TODO
    }    
           private void afficherPer1()  {
    
    CategorieDao sp = new CategorieDao();
     //aa stana
          List<Personnel> liste=sp.getCategorie();
       ObservableList et=FXCollections.observableArrayList(liste);
       tabb.setItems(et);//  ouiii chnouwa 5orm hedha ???  
       nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       image.setCellValueFactory(new PropertyValueFactory<>("photo"));
       pre.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       
       catt.setCellValueFactory(new PropertyValueFactory<>("categorie"));
      /* adress.setCellValueFactory(new PropertyValueFactory<>("local"));
       Id_event.setCellValueFactory(new PropertyValueFactory<>("idEvent"));*/

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

    private void retour2(ActionEvent event) throws IOException {
              Stage stage = (Stage) retour2.getScene().getWindow();
               Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Acceuilfront/acceuilFront.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
    stage.close();
     
    }

    private void retour3(ActionEvent event) throws IOException {
       Stage stage = (Stage) retour1.getScene().getWindow();
      Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Acceuilfront/acceuilFront.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
       stage.close();
    }

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
