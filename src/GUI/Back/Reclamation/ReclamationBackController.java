/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Reclamation;
//jh
import Entity.ameni.chat;
import Entity.ameni.reclamation;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.ameni.chatService;
import service.ameni.reclamationService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ReclamationBackController implements Initializable {

    @FXML
    private TableColumn<reclamation, Date> colDate;
    @FXML
    private TableColumn<reclamation, String> colEtat;
    @FXML
    private TableColumn<reclamation, String> colDescription;
    @FXML
    private TableColumn<reclamation, String> colCategorieReclamation;
    @FXML
    private TableView<reclamation> tab;
    
    reclamationService rec=new reclamationService();
    @FXML
    private Label ListeReclamation;
    @FXML
    private Button affStat;
    @FXML
    private Button pdf;
    
    
        private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 24,
            Font.BOLD, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    @FXML
    private TableView<?> tabChat;
    @FXML
    private TableColumn<?, ?> colQuest;
    @FXML
    private TableColumn<?, ?> colRep;
    @FXML
    private TextField question;
    @FXML
    private TextArea reponse;
    @FXML
    private Button ajouBt;
    @FXML
    private Label chatRoom;
    @FXML
    private AnchorPane rechercher;
    @FXML
    private TextField recherche;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherReclamation1();
        afficherChat();
        
        question.setVisible(false);

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
        //colIdParent.setCellValueFactory(new PropertyValueFactory<>("idParent"));          
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
       // colIdParent.setCellValueFactory(new PropertyValueFactory<>("idParent"));   
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
       // colIdParent.setCellValueFactory(new PropertyValueFactory<>("idParent"));
         }

    @FXML
    private void affStat(ActionEvent event) throws IOException {
             Stage stage1 = (Stage) affStat.getScene().getWindow();
    stage1.close();  
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Back/Reclamation/statestiquesRec.fxml"));     
        Scene scene = new Scene(root);   
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException     {
              reclamationService sp = new reclamationService();
                Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("C:\\wamp64\\www\\PiDesktop\\ReclamationAmeni\\Reclamation.pdf"));
            document.open();
       
            Paragraph ph1 = new Paragraph("Bienvenue a Coccinelle !", redFont);
            ph1.setAlignment(Element.ALIGN_CENTER);
   
            Paragraph ph2 = new Paragraph(" ", redFont);
            ph1.setAlignment(Element.ALIGN_CENTER);
            
            
            PdfPTable table = new PdfPTable(4);
            //On créer l'objet cellule.
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Votre Liste de reclamations", catFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            cell.setColspan(sp.Affichertoutpdf().size()+1);
            table.addCell(cell);
            //contenu du tableau.
         
        cell = new PdfPCell(new Phrase("Date", smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Etat", smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Description", smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("CategorieReclamation", smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
          
            sp.Affichertoutpdf().forEach(e
                    -> {
                table.addCell(String.valueOf(e.getDate()));
                table.addCell(e.getEtat());
                table.addCell(e.getDescription());
                table.addCell(e.getNom());
            }
            );
            document.add(ph1);
            document.add(ph2);
            document.add(table);
            document.addAuthor("Coccinelle");
            JOptionPane.showMessageDialog(null, "PDF ajouté");
        //  AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();
        }

    
    
            private void clearTabb() {
            question.clear();    }
        
        private void refreshBb() throws SQLException{
        List<chat> listB=new ArrayList<>();
        chatService   cr = new chatService();
        listB = cr.affChat();
        ObservableList <chat> data = FXCollections.observableArrayList(listB);
       // tabChat.setItems(data);
    }
    
    
             public void afficherChat()
         {
      chatService sp = new chatService();
        List categ=sp.affChat();
        ObservableList et=FXCollections.observableArrayList(categ);
        tabChat.setItems(et);
        ObservableList observableList = FXCollections.observableArrayList(categ);
       // colRef.setCellValueFactory(new PropertyValueFactory<>("ref"));
        colQuest.setCellValueFactory(new PropertyValueFactory<>("question"));
        colRep.setCellValueFactory(new PropertyValueFactory<>("reponse"));  

         }
             
           private void afficheChatt(ActionEvent event) {
        
      chatService sp = new chatService();
      List categ=sp.affChat();
       ObservableList et=FXCollections.observableArrayList(categ);
       tabChat.setItems(et);
        ObservableList observableList = FXCollections.observableArrayList(categ);
     //en vert hekom mil interface tu les recuperes m tableau eli samitou tab
     //en oranger hekom les entités mte3ek
     //   colRef.setCellValueFactory(new PropertyValueFactory<>("ref"));
        colQuest.setCellValueFactory(new PropertyValueFactory<>("question"));
        colRep.setCellValueFactory(new PropertyValueFactory<>("reponse"));
    }

    @FXML
    private void modifChat(MouseEvent event) {
    chat b=(chat) tabChat.getSelectionModel().getSelectedItem();
    question.setText(b.getQuestion());
    reponse.setText(b.getReponse());
    }

    @FXML
    private void handleButtonActionModifierChat(ActionEvent event) throws SQLException{
      chatService cs = new chatService();
      chat ch=(chat) tabChat.getSelectionModel().getSelectedItem();
      cs.modifierChat(question.getText(),reponse.getText(), ch.getIdChat());
      afficheChatt(event);  
      clearTabb();
    }
    
    
    
            private void refreshB(ActionEvent event) throws SQLException{
        List<reclamation> listC=new ArrayList<>();
        reclamationService   cr = new reclamationService();
        listC = cr.affRecFront();
        ObservableList <reclamation> data = FXCollections.observableArrayList(listC);
        tab.setItems(data);
    }
    

    @FXML
    private void rechercheCat(KeyEvent event) {
    }

    @FXML
    private void recherche(KeyEvent event) {
        reclamationService reclamationService = new reclamationService();
        recherche.setOnKeyReleased(e
                -> {
            if (recherche.getText().equals("") ) {
                try {
                    refreshBb();
                } catch (SQLException ex) {
                    Logger.getLogger(ReclamationBackController.class.getName()).log(Level.SEVERE, null, ex);
                }                
            } else {
                try {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategorieReclamation.setCellValueFactory(new PropertyValueFactory<>("nom"));
                    tab.getItems().clear();
                    tab.setItems(reclamationService.recherche(recherche.getText()));
                } catch (SQLException ex) {
                Logger.getLogger(ReclamationBackController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );    
    }
         

}