/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererBus;

import Entity.wifek.Bus;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import service.wifek.CrudBusService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class BusController implements Initializable {

    @FXML
    private Button buttonAdd;
    @FXML
    private Button updateButton;
    @FXML
    private TextField matricule;
    @FXML
    private TextField nbplace;
    @FXML
    private TextField ligne;
    @FXML
    private TableView<Bus> tabAffiche;
    @FXML
    private TableColumn<Bus, Integer> colid;
    @FXML
    private TableColumn<Bus, String> colmat;
    @FXML
    private TableColumn<Bus, Integer> colnb;
    @FXML
    private TableColumn<Bus, String> coltraj;
    @FXML
    private Button suppButton;
    
    public ArrayList<Bus> ran;
    CrudBusService buss=new CrudBusService();
    
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher();
    }    
    private void afficher() {
    CrudBusService sp = new CrudBusService();
      List buss=sp.afficherBus();
       ObservableList et=FXCollections.observableArrayList(buss);
       tabAffiche.setItems(et);
     ObservableList observableList = FXCollections.observableArrayList(buss);
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmat.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        colnb.setCellValueFactory(new PropertyValueFactory<>("nbPlaces"));
        coltraj.setCellValueFactory(new PropertyValueFactory<>("ligne")); 

}
        private void refreshB(ActionEvent event) throws SQLException{
        List<Bus> listB=new ArrayList<>();
        CrudBusService   cr = new CrudBusService();
        listB = cr.afficherBus();
        ObservableList <Bus> data = FXCollections.observableArrayList(listB);
        tabAffiche.setItems(data);
    }

    private void clearBus() {
    matricule.clear();
    nbplace.clear();
    ligne.clear();    
    }
    
    @FXML
    private void ajoutB(ActionEvent event) throws SQLException {

        CrudBusService b= new CrudBusService();
    if( matricule.getText().isEmpty() || ligne.getText().isEmpty() || nbplace.getText().isEmpty() ){
    Alert alertt=new Alert(Alert.AlertType.ERROR);
    alertt.setTitle("WARNING!");
    alertt.setHeaderText(null);
    alertt.setContentText("Merci de vérifier que vous avez remplit tout les champs");
    alertt.showAndWait();
    }else if(!nbplace.getText().matches("^([1-9][0-9]{0,4}|31)$")){    
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider Nombre Place Par Bus");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez verifier le nombre des place ne depasse pas 30  ");
            alert1.showAndWait();
         }
    else if(!ligne.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre ligne");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ ligne accepte que les lettres ");
            alert1.showAndWait();
         }
    else{ Bus bus=new Bus(matricule.getText(),
          Integer.parseInt(nbplace.getText()),
            ligne.getText()
    );   
    b.ajouterBus(bus);
        
    
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
    refreshB(event);
   clearBus();
    }

    @FXML
    private void modifB(ActionEvent event)  throws SQLException{
       // Bus cc = (Bus)tabAffiche.getSelectionModel().getSelectedItem();
    CrudBusService cs = new CrudBusService();
      Bus b=tabAffiche.getSelectionModel().getSelectedItem();
      cs.modifierBus(matricule.getText(),Integer.parseInt(nbplace.getText()),ligne.getText(), b.getId());
      refreshB(event);
    }

    @FXML
    private void modifAff(MouseEvent event) {
    Bus b=tabAffiche.getSelectionModel().getSelectedItem();
    matricule.setText(b.getMatricule());
    ligne.setText(b.getLigne());
    nbplace.setText(String.valueOf(b.getNbPlaces()));
    }



     /*   if (button == ButtonType.OK) {
            eventcrud.deleteEvent(id);
            table.getItems().remove(eventt);
            TrayNotification tray = new TrayNotification("Information", "Evènement Supprimé", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(3));

        }*/
    @FXML
    private void supprimeB(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Notification");
        alert.setContentText("Etes vous sure de vouloir supprimer ?");
        Optional<ButtonType> result = alert.showAndWait();
        
        ButtonType button = result.orElse(ButtonType.CANCEL);
        
        CrudBusService rs = new CrudBusService();
         Bus cc = (Bus)tabAffiche.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(button == ButtonType.OK){
            rs.supprimerBus(cc.getId());
            refreshB(event);
           JOptionPane.showMessageDialog(null, "Bus supprimer");
         matricule.clear();
         ligne.clear();
         nbplace.clear();
        cc=null;
    }
        
        
    }

    @FXML
    private void recherche(KeyEvent event) {
            Task<ArrayList<Bus>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                ran = (ArrayList<Bus>) new CrudBusService().rechercheBus(recherche.getText());
                       
                
                return ran;
            }
    };
        task.setOnSucceeded((WorkerStateEvent e) -> {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmat.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        colnb.setCellValueFactory(new PropertyValueFactory<>("nbPlaces"));
        coltraj.setCellValueFactory(new PropertyValueFactory<>("ligne"));             
        });
        task.setOnFailed(e -> {
            afficher();
        });
        Thread th = new Thread(task);
        th.start();
            
               }


}
    

