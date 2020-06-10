/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererCantine.abonnement;

import Entity.imen.Menu;
import Entity.imen.Plat;
import Entity.imen.abonnement;
import static GUI.Front.gererCantine.abonnement.DessertController.idDessert;
import GUI.login.LoginController;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.imen.PlatService;
import service.imen.abonnementService;
import service.imen.menuService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutMenuController implements Initializable {
    @FXML
    private AnchorPane back;
    @FXML
    private ComboBox<String> listePlat;
    @FXML
    private ComboBox<String> listDessert;
    @FXML
    private Button ajout;
    @FXML
    private ComboBox<String> enfant;
PlatService ps = new PlatService();
        menuService ms =new menuService();
        abonnementService as=new abonnementService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    comboAfficher();
       comboAfficher1();
    }    

    @FXML
    private void ajout(ActionEvent event) {
        int idDessert=ps.PlatUnique2(listDessert.getValue());
                int idPlat=ps.PlatUnique2(listePlat.getValue());
         

        Menu m= new Menu(idPlat,idDessert,LoginController.ID,as.getEnfant(enfant.getValue(),LoginController.ID));
        ms.ajouterMenu(m);
        Stage stage = (Stage) ajout.getScene().getWindow();
    stage.close();
  
    }
     private void comboAfficher()
    {       List<abonnement> L=as.afficherAbonSelonparent(LoginController.ID);
                   List<String> l=new ArrayList<>();
                   
        for(int i=0;i<L.size();i++)
        {  System.out.println("hetha khra id "+as.haveMenu(LoginController.ID,L.get(i).getIdEnfant()));
        System.out.println(L.get(i).getIdEnfant());
        if(as.haveMenu(LoginController.ID,L.get(i).getIdEnfant())==0)
        { l.add(L.get(i).getPrenom());}
        }
                   
        ObservableList<String> enfListe=FXCollections.observableArrayList(l);
        enfant.setValue("Enfants");
enfant.setItems(enfListe);
    }
     
     public void comboAfficher1()
{
       List<String> plats= new ArrayList<>();
        List<String> desserts =new ArrayList<>();
        List<Plat> listeDessert=ps.afficherPlatSelonType("dessert");
        List<Plat>listePlate=ps.afficherPlatSelonType("plat principal");
        for(int i=0;i<listeDessert.size();i++)
        {desserts.add(listeDessert.get(i).getNom());
        }
        for (int i=0;i<listePlate.size();i++)
        {plats.add(listePlate.get(i).getNom());
        }
      
                       ObservableList<String> platss=FXCollections.observableArrayList(plats);
                       ObservableList<String> dessertss=FXCollections.observableArrayList(desserts);
                       
listePlat.setItems(platss);
listDessert.setItems(dessertss);
}
     
}
