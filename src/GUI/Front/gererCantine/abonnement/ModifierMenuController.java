/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererCantine.abonnement;

import Entity.imen.Plat;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;
import service.imen.PlatService;
import service.imen.menuService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ModifierMenuController implements Initializable {
    @FXML
    private AnchorPane back;
    @FXML
    private ComboBox<String> listePlat;
    @FXML
    private ComboBox<String> listDessert;
    @FXML
    private Button modifier;
 PlatService ps = new PlatService();
        menuService ms =new menuService();
    /**
     * Initializes the controller class.
     */
        static int mo=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     comboAfficher();
           
    }    
public void comboAfficher()
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
                       listePlat.setValue(AbonCantineController.mi.getPlat());
                       listDessert.setValue(AbonCantineController.mi.getDessert());
listePlat.setItems(platss);
listDessert.setItems(dessertss);
}
    @FXML
    private void modifier(ActionEvent event) throws IOException {
    mo=1;
   
        int idD=ps.PlatUnique2(listDessert.getValue());
        int idP=ps.PlatUnique2(listePlat.getValue());

        ms.modifierPlat(idD, idP,AbonCantineController.mi.getId());
           Stage stage = (Stage) modifier.getScene().getWindow();
    stage.close();
  
    }
    
}
