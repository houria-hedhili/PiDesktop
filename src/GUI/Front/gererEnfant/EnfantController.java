/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererEnfant;

import Entity.wifek.Bus;
import Entity.wifek.enfant;
import GUI.login.LoginController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import service.wifek.CrudBusService;
import service.wifek.CrudEnfantService;

/**
 * FXML Controller class
 *
 * @author Imen
 */
public class EnfantController implements Initializable {
    public ArrayList<enfant> ran;
    CrudEnfantService buss=new CrudEnfantService();
    
    @FXML
    private TabPane tabpane;
    @FXML
    private Button retour1;
    @FXML
    private Button retour2;
    @FXML
    private TableView<enfant> tabAffiche;
    @FXML
    private TableColumn<enfant, String> colsexe;
    @FXML
    private TableColumn<enfant, String> colnom;
    @FXML
    private TableColumn<enfant, String> colpre;
    @FXML
    private TableColumn<enfant, Integer> colage;
    @FXML
    private TableColumn<enfant, Integer> colidbus;
    @FXML
    private TableView<enfant> tabAffiche1;
    @FXML
    private TableColumn<enfant, String> colsexe1;
    @FXML
    private TableColumn<enfant, String> colnom1;
    @FXML
    private TableColumn<enfant, String> colpre1;
    @FXML
    private TableColumn<enfant, Integer> colage1;
    @FXML
    private TableColumn<enfant, Integer> colidbus1;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button updateButton;
    @FXML
    private Button suppButton;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    
    @FXML
    private ComboBox<String> sexecombo;
    @FXML
    private TextField age;
    @FXML
    private ComboBox<String> trajet1;
    String ch="";
    /**
     * Initializes the controller class.
     */
    CrudEnfantService es;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO               
       afficher1();
        afficher2();
        ObservableList<String> sexe=FXCollections.observableArrayList("Garcon","Fille");
        sexecombo.setValue("sexe");
       sexecombo.setItems(sexe);
        CrudBusService c=new CrudBusService();
        List<String> listeTrajete= c.getAllLigne();
        ObservableList<String> listetrajet =FXCollections.observableArrayList(listeTrajete);
        trajet1.setValue("ligne");
        trajet1.setItems(listetrajet);
       

    }    
    
           private void afficher1() {
   /* CrudEnfantService sp = new CrudEnfantService();
      List buss=sp.afficherEnfant();*/
                 CrudBusService c=new CrudBusService();
          List<enfant> liste=c.getLigneBus(LoginController.ID);
          //System.out.println(liste.get(0).getNomLigne());
       ObservableList et=FXCollections.observableArrayList(liste);
       tabAffiche.setItems(et);
       
   //  ObservableList observableList = FXCollections.observableArrayList(buss);
       //
       //colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colsexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colpre.setCellValueFactory(new PropertyValueFactory<>("prenom")); 
        colage.setCellValueFactory(new PropertyValueFactory<>("age")); 
        colidbus.setCellValueFactory(new PropertyValueFactory<>("nomLigne"));
}
      private void afficher2() {
     CrudBusService c=new CrudBusService();
          List<enfant> liste=c.getLigneBus(LoginController.ID);
   //  List<enfant> liste=es.afficherEnfant2(LoginController.ID);
     System.out.println(LoginController.ID);
       ObservableList et=FXCollections.observableArrayList(liste);
       tabAffiche1.setItems(et);
     ObservableList observableList = FXCollections.observableArrayList(liste);
       // colid1.setCellValueFactory(new PropertyValueFactory<>("id"));
        colsexe1.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        colnom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colpre1.setCellValueFactory(new PropertyValueFactory<>("prenom")); 
        colage1.setCellValueFactory(new PropertyValueFactory<>("age")); 
       colidbus1.setCellValueFactory(new PropertyValueFactory<>("nomLigne"));
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
    @FXML
    private void ajoutB(ActionEvent event)  throws SQLException {
CrudBusService b1= new CrudBusService();
        CrudEnfantService b= new CrudEnfantService();
    if( sexecombo.getValue().isEmpty() || nom.getText().isEmpty() || prenom.getText().isEmpty() || age.getText().isEmpty() || trajet1.getValue().isEmpty() ){
    Alert alertt=new Alert(Alert.AlertType.ERROR);
    alertt.setTitle("WARNING!");
    alertt.setHeaderText(null);
    alertt.setContentText("Merci de vérifier que vous avez remplit tout les champs");
    alertt.showAndWait();
    }
    else if (!age.getText().matches("[3-6]")){
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
             
        enfant bus=new enfant(sexecombo.getValue(),
            nom.getText(),
            prenom.getText(),
            Integer.parseInt(age.getText()),
       b1.getIdLigne(trajet1.getValue())
    );   
    b.ajouterEnfant(bus);
    //    System.out.println( b1.getIdLigne(trajet1.getValue()));
    
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
             afficher2();
             afficher1();

  // clearEnfant(event);
    }

    @FXML
    private void modifB(ActionEvent event) throws SQLException{
       // Bus cc = (Bus)tabAffiche.getSelectionModel().getSelectedItem();
        CrudBusService b1= new CrudBusService();
    CrudEnfantService cs = new CrudEnfantService();
      enfant b=tabAffiche1.getSelectionModel().getSelectedItem();
      cs.modifierEnfant(sexecombo.getValue(),nom.getText(),prenom.getText(),Integer.parseInt(age.getText()),b1.getIdLigne(trajet1.getValue()),b.getId());
       afficher2();
       afficher1();
      clearEnfant(event);
    }
      /*  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Notification");
        alert.setContentText("Etes vous sure de vouloir supprimer ?");
        Optional<ButtonType> result = alert.showAndWait();
        
        ButtonType button = result.orElse(ButtonType.CANCEL);
        
        CrudBusService rs = new CrudBusService();
         Bus cc = (Bus)tabAffiche.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(button == ButtonType.OK){*/
    @FXML
    private void supprimeB(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Notification");
        alert.setContentText("Etes vous sure de vouloir supprimer ?");
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        
        CrudEnfantService rs = new CrudEnfantService();
         enfant cc = (enfant)tabAffiche1.getSelectionModel().getSelectedItem();
        System.out.println(cc);
       /* if(button == ButtonType.OK){
            JOptionPane.showMessageDialog(null, "choisir Enfant");
        }else{
            rs.supprimerEnfant(cc.getId());
            //ereshB(event);
            afficher2();
            clearEnfant(event);
           JOptionPane.showMessageDialog(null, "Enfant supprimer");
         
        cc=null;
    }*/ rs.supprimerEnfant(cc.getId());
        afficher2();
        afficher1();
    }
    

    @FXML
    private void modifAff(MouseEvent event) {
    enfant b=tabAffiche1.getSelectionModel().getSelectedItem();
    sexecombo.setValue(b.getSexe());
    nom.setText(b.getNom());
    prenom.setText(b.getPrenom());
    age.setText(String.valueOf(b.getAge()));
    trajet1.setValue(b.getNomLigne());
    
    }
    
    private void refreshB(ActionEvent event) throws SQLException{
        List<enfant> listB=new ArrayList<>();
        CrudEnfantService   cr = new CrudEnfantService();
        listB = cr.afficherEnfant2(LoginController.ID);
        ObservableList <enfant> data = FXCollections.observableArrayList(listB);
        tabAffiche1.setItems(data);
    }
    
    private void clearEnfant(ActionEvent event) {
    nom.clear();
    prenom.clear();   
    age.clear();
    }

    @FXML
    private void refreshAff(Event event) {
        afficher2();
    }
    
    public void getUsername(String u)
    {
    ch=u;
    }
}
