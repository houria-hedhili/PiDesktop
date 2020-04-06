/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererEnfant;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.wifek.CrudEnfantService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class EnfantController implements Initializable {

    @FXML
    private TableView<?> tabAffiche1;
    @FXML
    private TableColumn<?, ?> colsexe1;
    @FXML
    private TableColumn<?, ?> colnom1;
    @FXML
    private TableColumn<?, ?> colpre1;
    @FXML
    private TableColumn<?, ?> colage1;
    @FXML
    private TableColumn<?, ?> colidbus1;
    @FXML
    private TableColumn<?, ?> parent1;
    @FXML
    private Button btnpdf;
    @FXML
    private Button btnstat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher1();
    }    
    
    private void afficher1() {
    CrudEnfantService sp = new CrudEnfantService();
      List buss=sp.afficherEnfant1();
       ObservableList et=FXCollections.observableArrayList(buss);
       tabAffiche1.setItems(et);
     ObservableList observableList = FXCollections.observableArrayList(buss);
        //colid1.setCellValueFactory(new PropertyValueFactory<>("id"));
        colsexe1.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        colnom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colpre1.setCellValueFactory(new PropertyValueFactory<>("prenom")); 
        colage1.setCellValueFactory(new PropertyValueFactory<>("age")); 
        colidbus1.setCellValueFactory(new PropertyValueFactory<>("nomLigne"));
        parent1.setCellValueFactory(new PropertyValueFactory<>("username"));
}

    @FXML
    private void pdfAction(ActionEvent event) {
        CrudEnfantService sp = new CrudEnfantService();
                Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("Enfant.pdf"));
            document.open();
            Paragraph ph1 = new Paragraph("Bienvenue a Coccinelle !");
            PdfPTable table = new PdfPTable(6);
            //On créer l'objet cellule.
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Tableau des Enfants Inscrits"));
            cell.setColspan(sp.Affichertoutpdf().size()+1);
            table.addCell(cell);
            //contenu du tableau.
            table.addCell("Sexe : ");
            table.addCell("Nom : ");
            table.addCell("Prenom : ");
            table.addCell("Age : ");
            table.addCell("Ligne : ");
            table.addCell("Parent : ");
          
            sp.Affichertoutpdf().forEach(e
                    -> {
                table.addCell(e.getSexe());
                table.addCell(e.getNom());
                table.addCell(e.getPrenom());
                table.addCell(String.valueOf(e.getAge()));
                table.addCell(e.getNomLigne());
                table.addCell(e.getUsername());
            }
            );
            document.add(ph1);
            document.add(table);
            document.addAuthor("Coccinelle");
//           AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();

    }

    @FXML
    private void voirStat(ActionEvent event) throws IOException {
        Scene scene;
            Stage stage = new Stage();
         
                
                scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/gererEnfant/FXMLStat.fxml")));
                 stage.setScene(scene);
            stage.show();
            
      
    }
    
    
}
