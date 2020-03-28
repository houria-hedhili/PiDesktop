/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererMatiere;

import Entity.houria.Matiere;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import service.houria.matiereCRUD;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class MatiereController implements Initializable {

    @FXML
    private TextField Mnom;
    @FXML
    private TextField Mcoeff;
    @FXML
    private TableView<?> table_matiere;
    @FXML
    private TableColumn<?, ?> colnom;
    @FXML
    private TableColumn<?, ?> colcoeff;
    @FXML
    private Button Eajouter;
    @FXML
    private Button Esupprimer;
    @FXML
    private Button Emodifier1;

   private Matiere matt=null;
    @FXML
    private Label labelnom;
    @FXML
    private Label labelcoeff;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          afficher();
          
        table_matiere.setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
                matt = (Matiere)table_matiere.getSelectionModel().getSelectedItem();
                System.out.println(matt);
                Mnom.setText(matt.getNom());
                
                int place=matt.getCoefficient();
                String nb_PPP=String.valueOf(place);
                Mcoeff.setText(nb_PPP);

           }
             
         });
    
    }    
    private void afficher() {
    
     matiereCRUD sp = new matiereCRUD();
      List events=sp.displayALLMatiere();
       ObservableList et=FXCollections.observableArrayList(events);
       table_matiere.setItems(et);//  ouiii chnouwa 5orm hedha ???  
       colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       colcoeff.setCellValueFactory(new PropertyValueFactory<>("coefficient"));
     

}
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
                                       labelnom.setText("");
       labelnom.setText("");
       labelcoeff.setText("");
        if(Mnom.getText().isEmpty()||Mcoeff.getText().isEmpty() ){
         
         if (Mnom.getText().isEmpty()) {
            labelnom.setText("Champ nom vide");
           // new Alert(Alert.AlertType.ERROR, "Champ Description vide").show();
        }
         if (Mcoeff.getText().isEmpty()) {
           labelcoeff.setText("Champ coefficient vide");
           // new Alert(Alert.AlertType.ERROR, " Champ Nom vide").show();
        }
        

        }else 
        {
             if(!Mcoeff.getText().matches("^([1-9][0-9]{0,4}|31)$")) {
           labelcoeff.setText("(il faut que le coefficient soit un entier positive inferieure a 5)");

           }else{ 
                         int coeff= Integer.parseInt(Mcoeff.getText());

                 if(coeff>5){
               labelcoeff.setText("(il faut que le coefficient soit >0 et < 5)");

                 }else{
                    String nomM = Mnom.getText();
        matiereCRUD sp = new matiereCRUD();
        Matiere e = new Matiere(nomM,coeff);
        sp.addMatiere(e); 
         JOptionPane.showMessageDialog(null, "ajout avec succes");
         Mnom.clear();

        Mcoeff.clear();
        afficher(); }
             }
        }
        
      
               
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException, IOException {
        
                                               labelnom.setText("");
       labelnom.setText("");
       labelcoeff.setText("");
        if(Mnom.getText().isEmpty()||Mcoeff.getText().isEmpty() ){
         
         if (Mnom.getText().isEmpty()) {
            labelnom.setText("Champ nom vide");
           // new Alert(Alert.AlertType.ERROR, "Champ Description vide").show();
        }
         if (Mcoeff.getText().isEmpty()) {
           labelcoeff.setText("Champ coefficient vide");
           // new Alert(Alert.AlertType.ERROR, " Champ Nom vide").show();
        }
        

        }else 
        {
             if(!Mcoeff.getText().matches("^([1-9][0-9]{0,4}|31)$")) {
           labelcoeff.setText("(il faut que le coefficient soit un entier positive inferieure a 5)");

           }else{ 
                         int coeff= Integer.parseInt(Mcoeff.getText());

                 if(coeff>5){
               labelcoeff.setText("(il faut que le coefficient soit >0 et < 5)");

                 }else{
              matiereCRUD cs = new matiereCRUD();
        
        System.out.println(matt);
        if(matt== null){
            JOptionPane.showMessageDialog(null, "choisir matiere");
                   
        }else{
           cs.updateMatiere(new Matiere(Mnom.getText(),Integer.parseInt(Mcoeff.getText())),matt.getId());
           
       afficher();
        JOptionPane.showMessageDialog(null, "event modifier");
        Mnom.clear();
        Mcoeff.clear();
        matt=null;
        }
    }}}}

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        
         matiereCRUD cs = new matiereCRUD();
         Matiere cc = (Matiere)table_matiere.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir matiere");
                   
        }else{
            cs.deleteMatiere(cc.getId());
    
           afficher();
           
           JOptionPane.showMessageDialog(null, "matiere supprimer");
         Mnom.clear();
        Mcoeff.clear();
        cc=null;
    }
    }
    
    
}
