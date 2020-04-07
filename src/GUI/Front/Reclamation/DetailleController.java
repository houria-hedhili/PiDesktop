/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.Reclamation;

import Entity.ameni.reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DetailleController implements Initializable {

    @FXML
    private Label date;
    private reclamation reclamationn;
    @FXML
    private Label etat;
    @FXML
    private Label description;
    @FXML
    private Label categorieReclamation;
    @FXML
    private Button retour;


    /**
     * Initializes the controller class.
     * @param reclam
     */
    
    
        public void initData(reclamation reclam)
    {
        reclamationn = reclam;
        date.setText(reclamationn.getDate().toString());
        etat.setText(reclamationn.getEtat());
        description.setText(reclamationn.getDescription());
       // categorieReclamation.setText(reclamationn.getCategorieReclamation());
        //idParent.setText(Integer.toString(reclamationn.getIdParent()));
        categorieReclamation.setText(reclamationn.getNom());
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
                Stage stage = (Stage) retour.getScene().getWindow();
    stage.close();
      Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Reclamation/reclamation.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
    }
    
}
