/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Reclamation;
//jh
import Entity.ameni.reclamation;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ameni.reclamationService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ReclamationBackController implements Initializable {

    @FXML
    private TableColumn<reclamation, Integer> colId;
    @FXML
    private TableColumn<reclamation, Date> colDate;
    @FXML
    private TableColumn<reclamation, String> colEtat;
    @FXML
    private TableColumn<reclamation, String> colDescription;
    @FXML
    private TableColumn<reclamation, String> colCategorieReclamation;
    @FXML
    private TableColumn<reclamation, String> colIdParent;
    @FXML
    private TableView<reclamation> tab;
    
    reclamationService rec=new reclamationService();
    @FXML
    private Label ListeReclamation;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherReclamation1();
        // TODO
 
    }    
    
           private void afficher() {
        reclamationService sp = new reclamationService();
      List rec=sp.affRec();
       ObservableList et=FXCollections.observableArrayList(rec);
       tab.setItems(et);
     ObservableList observableList = FXCollections.observableArrayList(rec);
       // colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));   
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));   
        colCategorieReclamation.setCellValueFactory(new PropertyValueFactory<>("categorieReclamation")); 
        colIdParent.setCellValueFactory(new PropertyValueFactory<>("idParent"));          
        }
            
       private void afficheRec(ActionEvent event) {   
      reclamationService sp = new reclamationService();
      List categ=sp.affRec();
       ObservableList et=FXCollections.observableArrayList(categ);
       tab.setItems(et);
     ObservableList observableList = FXCollections.observableArrayList(categ);
     //en vert hekom mil interface tu les recuperes m tableau eli samitou tab
     //en oranger hekom les entités mte3ek
       // colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategorieReclamation.setCellValueFactory(new PropertyValueFactory<>("categorieReclamation"));
        colIdParent.setCellValueFactory(new PropertyValueFactory<>("idParent"));   
    }
       
       //hedhi taaml fl affichage l hakiki
         public void afficherReclamation1()
         {
          reclamationService sp = new reclamationService();
      List<reclamation> categ=sp.affRecBack();
       ObservableList et=FXCollections.observableArrayList(categ);
       tab.setItems(et);
     //en vert hekom mil interface tu les recuperes m tableau eli samitou tab
     //en oranger hekom les entités mte3ek
       // colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategorieReclamation.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colIdParent.setCellValueFactory(new PropertyValueFactory<>("idParent"));
         }
         
         
            
}
