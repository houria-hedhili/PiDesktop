/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererPersonnel;

import ConnexionBd.connexionBd;
import Entity.aziza.Personnel;
import Entity.user.Utilisateur;
import GUI.Back.gererPersonnels.DetailsSalController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import service.authentification.UserCRUD;
import service.aziza.CategorieDao;
import service.aziza.PersonnelDao;

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
    //  private TableColumn<?, ?> catt;
    @FXML
    private TableColumn<?, ?> image;
    @FXML
    private TableView<?> tabb;
    // private Rating rating;
//    @FXML
//    private Label msg;
    @FXML
    private Button nombtn;
    @FXML
    private Button cate;
    CategorieDao bb = new CategorieDao();
    //  private Utilisateur u=new Utilisateur();

    connexionBd connection = null;
    private Connection cnx;
    Statement ste;
    @FXML
    private Button detail;
    @FXML
    private AnchorPane SCROLL;
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherPer1();

        
    }

    private void afficherPer1() {

        CategorieDao sp = new CategorieDao();

        List<Personnel> liste = sp.getCategorie();
        ObservableList et = FXCollections.observableArrayList(liste);
        tabb.setItems(et);//  ouiii chnouwa 5orm hedha ???  
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        image.setCellValueFactory(new PropertyValueFactory<>("photo"));
        pre.setCellValueFactory(new PropertyValueFactory<>("prenom"));

    }

    @FXML
    private void retour1(ActionEvent event) throws IOException {
        UserCRUD us = new UserCRUD();
        Utilisateur u = new Utilisateur();
        u.setEmail("aziza.nasr@esprit.tn");
        u.setEnable(1);
        u.setNom("aziza");
        u.setPrenom("nasr");
        u.setUsername("aziza");
        // u.setRoles("a:1:{i:0;s:16:\"ROLE_SUPER_ADMIN\";}");
        us.ajoutAdmin(u, "aziza");
        Stage stage = (Stage) retour1.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Acceuilfront/acceuilFront.fxml"));
        Stage stage1 = new Stage();
        Scene scene = new Scene(root);

        stage1.setScene(scene);
        stage1.show();
    }

    private void retour2(ActionEvent event) throws IOException {
        Stage stage = (Stage) retour2.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Acceuilfront/acceuilFront.fxml"));
        Stage stage1 = new Stage();
        Scene scene = new Scene(root);

        stage1.setScene(scene);
        stage1.show();
        stage.close();

    }

    private void retour3(ActionEvent event) throws IOException {
        Stage stage = (Stage) retour1.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Acceuilfront/acceuilFront.fxml"));
        Stage stage1 = new Stage();
        Scene scene = new Scene(root);

        stage1.setScene(scene);
        stage1.show();
        stage.close();
    }

    private void retour4(ActionEvent event) throws IOException {
        Stage stage = (Stage) retour1.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Acceuilfront/acceuilFront.fxml"));
        Stage stage1 = new Stage();
        Scene scene = new Scene(root);

        stage1.setScene(scene);
        stage1.show();
        stage.close();

    }

    @FXML
    private void TrierNom(ActionEvent event) {
        PersonnelDao sp = new PersonnelDao();

        List<Personnel> liste = sp.TrierNomPer();
        ObservableList et = FXCollections.observableArrayList(liste);
        tabb.setItems(et);//  ouiii chnouwa 5orm hedha ???  
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        image.setCellValueFactory(new PropertyValueFactory<>("photo"));
        pre.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        // catt.setCellValueFactory(new PropertyValueFactory<>("categorie"));
    }

    @FXML
    private void TrierCatégorie(ActionEvent event) {
        PersonnelDao sp = new PersonnelDao();

        List<Personnel> liste = sp.TrierCatPer();
        ObservableList et = FXCollections.observableArrayList(liste);
        tabb.setItems(et);//  ouiii chnouwa 5orm hedha ???  
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        image.setCellValueFactory(new PropertyValueFactory<>("photo"));
        pre.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        //catt.setCellValueFactory(new PropertyValueFactory<>("categorie"));
    }

    private void Normal(ActionEvent event) {
        CategorieDao sp = new CategorieDao();

        List<Personnel> liste = sp.getCategorie();
        ObservableList et = FXCollections.observableArrayList(liste);
        tabb.setItems(et);//  ouiii chnouwa 5orm hedha ???  
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        image.setCellValueFactory(new PropertyValueFactory<>("photo"));
        pre.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        // catt.setCellValueFactory(new PropertyValueFactory<>("categorie"));
    }

    @FXML
    private void Detailsfront(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Detailsperso.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //access the controller and call a method
        DetailspersoController controller = loader.getController();
        controller.initData2((Personnel) tabb.getSelectionModel().getSelectedItem());

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

}
