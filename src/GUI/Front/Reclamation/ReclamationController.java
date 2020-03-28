/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.Reclamation;

import Entity.ameni.reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import service.ameni.reclamationService;

/**
 * FXML Controller class
 *
 * @author Imen
 */
public class ReclamationController implements Initializable {
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
    private TableView<reclamation> tab;
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
    
    reclamationService rec=new reclamationService();
    @FXML
    private Button SupprimerBt;
    @FXML
    private Label etatLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label categorieReclamationLabel;
    private TextField etatText;
    private TextField descriptionText;
    @FXML
    private ComboBox<String> categorieReclamationCombo;
    @FXML
    private Button ModifierBt;
    @FXML
    private Button AjouterBt;
    @FXML
    private TableView<reclamation> tab1;
    @FXML
    private TableColumn<reclamation, Integer> colId1;
    @FXML
    private TableColumn<reclamation, Date> colDate1;
    @FXML
    private TableColumn<reclamation, String> colEtat1;
    @FXML
    private TableColumn<reclamation, String> colDescription1;
reclamationService rc=new reclamationService();
    @FXML
    private TextArea desc;
    @FXML
    private ComboBox<String> comboEtat;
    @FXML
    private TableColumn<reclamation, String> colCategorieReclamation1;
    @FXML
    private TableView<reclamation> tab2;
    @FXML
    private TableColumn<reclamation, Integer> colId2;
    @FXML
    private TableColumn<reclamation, Date> colDate2;
    @FXML
    private TableColumn<reclamation, String> colEtat2;
    @FXML
    private TableColumn<reclamation, String> colDescription2;
    @FXML
    private TableColumn<reclamation, String> colCategorieReclamation2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> ListeCat=rc.getListCat();
        ObservableList<String> catList=FXCollections.observableArrayList(ListeCat);
        categorieReclamationCombo.setValue("categorie");
        categorieReclamationCombo.setItems(catList);
               ObservableList<String> etatList=FXCollections.observableArrayList("urgence non vitale","urgence risque");
               comboEtat.setValue("etat");
               comboEtat.setItems(etatList);
        afficherReclamation1();
        afficherReclamation2();
        afficherReclamation3();
       // afficher();   
        //afficherRecherche();
        
        // TODO
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
    
    private void clearTab() {
    etatText.clear();
    descriptionText.clear(); 
    }
    
            private void refreshB(ActionEvent event) throws SQLException{
        List<reclamation> listC=new ArrayList<>();
        reclamationService   cr = new reclamationService();
        listC = cr.affRecFront();
        ObservableList <reclamation> data = FXCollections.observableArrayList(listC);
        tab.setItems(data);
    }
    
         private void afficher() {
        reclamationService sp = new reclamationService();
        List rec=sp.affRecFront();
       ObservableList et=FXCollections.observableArrayList(rec);
       tab.setItems(et);
        ObservableList observableList = FXCollections.observableArrayList(rec);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
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
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategorieReclamation.setCellValueFactory(new PropertyValueFactory<>("categorieReclamation"));
        colIdParent.setCellValueFactory(new PropertyValueFactory<>("idParent")); 

    }
                


    @FXML
    private void supprec(ActionEvent event) {
                 reclamationService rs = new reclamationService();
         reclamation cc = (reclamation)tab.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir ");
        }else{
            rs.supprimerRec(cc.getId());
            afficheRec(event);
            //refreshB(event);
            afficher();
           JOptionPane.showMessageDialog(null, "supprimer");
        // nomText.clear();
         //descriptionText.clear();
        cc=null;
    }
}

    @FXML
    private void modifRec(MouseEvent event) {
    reclamation b=tab.getSelectionModel().getSelectedItem();
    comboEtat.setValue(b.getEtat());
    categorieReclamationCombo.setValue(b.getNom());
    desc.setText(b.getDescription());
    }

    @FXML
    private void handleButtonActionAjouter(ActionEvent event) {
    reclamationService b= new reclamationService();
    reclamation r=new reclamation(desc.getText(),b.getIdCategorie(categorieReclamationCombo.getValue()) ,comboEtat.getValue());
    b.ajouterReclamation(r);
   /* if( etatText.getText().isEmpty() || descriptionText.getText().isEmpty()){
    Alert alertt=new Alert(Alert.AlertType.ERROR);
    alertt.setTitle("WARNING!");
    alertt.setHeaderText(null);
    alertt.setContentText("Merci de vérifier que vous avez remplit tout les champs");
    alertt.showAndWait();
    }
    else if(!etatText.getText().matches("^[a-zA-Z\\s]*$") || !descriptionText.getText().matches("^[a-zA-Z\\s]*$"))
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre champs"); 
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ nom accepte que les lettres ");
            alert1.showAndWait();
         }//id parenet kifeh tekhthou? normalment ml fos user houwa nahih pour le moment okk
    else{   reclamation rac=new reclamation(desc.getText(),categorieReclamationCombo.getValue() , rc.getIdCategorie(categorieReclamationCombo.getValue())) ;
 
       /* reclamation rec=new reclamation(datePic.getValue(),etatText.getText(),
            descriptionText.getText(),categorieReclamationCombo.getValue()
    );   */ //etat comboBox feha hekkoun tleetha ?le maamalthech f combobox a33nalh combobox?beh*/
  /*  b.ajouterReclamation(rac);
        //hedhi fiha controle de saisie w notificcatiobs akeli tji
    Notifications notif=Notifications.create()
            .title("Reclamation ajouté")
          .text("Une nouvelle reclamation vient d'être ajoutée !")
            .darkStyle().graphic(null).hideAfter(Duration.seconds(5))
            .position(Pos.CENTER)
            .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked ont notif");
                    }
                });
    notif.showConfirm();
    }
   // refreshB(event);
   afficheRec(event);*/
  
  
  afficherReclamation1();
  
    }

    @FXML
    private void handleButtonActionModifier(ActionEvent event) throws SQLException{
      reclamationService cs = new reclamationService();
      reclamation b=tab.getSelectionModel().getSelectedItem();
//      System.out.println(b.getId());
     boolean test= cs.modifierReclamation(b.getId(),comboEtat.getValue(),desc.getText(),cs.getIdCategorie(categorieReclamationCombo.getValue()));
     System.out.println(cs.getIdCategorie(b.getNom()));
     afficherReclamation1();
      //clearTab();//tfasa5 les cases ken m3ebyin
      //refreshB(event);
    }
    

      

                  public void afficherReclamation1()
         {
          reclamationService sp = new reclamationService();
      List<reclamation> categ=sp.affRecFront();
       ObservableList et=FXCollections.observableArrayList(categ);
       tab.setItems(et);
     //en vert hekom mil interface tu les recuperes m tableau eli samitou tab
     //en oranger hekom les entités mte3ek
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategorieReclamation.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colIdParent.setCellValueFactory(new PropertyValueFactory<>("idParent"));
         }
    
           public void afficherReclamation2()
         {
          reclamationService sp = new reclamationService();
      List<reclamation> categ=sp.affRecUrg();
       ObservableList et=FXCollections.observableArrayList(categ);
       tab1.setItems(et);
     //en vert hekom mil interface tu les recuperes m tableau eli samitou tab
     //en oranger hekom les entités mte3ek
        colId1.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate1.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEtat1.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colDescription1.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategorieReclamation1.setCellValueFactory(new PropertyValueFactory<>("nom"));
         }
           
            public void afficherReclamation3()
         {
          reclamationService sp = new reclamationService();
      List<reclamation> categ=sp.affRecUrgVit();
       ObservableList et=FXCollections.observableArrayList(categ);
       tab2.setItems(et);
     //en vert hekom mil interface tu les recuperes m tableau eli samitou tab
     //en oranger hekom les entités mte3ek
        colId2.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate2.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEtat2.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colDescription2.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategorieReclamation2.setCellValueFactory(new PropertyValueFactory<>("nom"));
         }
    
}
