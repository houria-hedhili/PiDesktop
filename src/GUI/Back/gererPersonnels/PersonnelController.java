/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererPersonnels;

import ConnexionBd.connexionBd;
import Entity.aziza.Personnel;

import javafx.concurrent.Task;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Date.from;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JOptionPane;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Notifications;
import service.aziza.CategorieDao;
import service.aziza.PersonnelDao;
import service.houria.EventCRUD;
import service.wifek.CrudBusService;
import service.wifek.CrudEnfantService;

/**
 * FXML Controller class
 *
 * @author GLOBALINFO
 */
public class PersonnelController implements Initializable {

    @FXML
    private Label azii;
    @FXML
    private TableView<Personnel> tab;
    @FXML
    private TableColumn<Personnel, String> NomColonne;
    @FXML
    private TableColumn<Personnel, String> PrenomColonne;
    @FXML
    private TableColumn<Personnel, Integer> AgeColonne;
    @FXML
    private TableColumn<Personnel, Integer> Nb;
    @FXML
    private TableColumn<Personnel, Float> Prix;
    @FXML
    private TableColumn<Personnel, String> CategorieColonne;
    @FXML
    private TableColumn<Personnel, ImageView> ImageColonne;
    @FXML
    private TextArea nom;
    @FXML
    private TextArea prenom;
    @FXML
    private TextArea prixheure;
    @FXML
    private TextArea aagee;
    @FXML
    private TextArea nombreheure;
    @FXML
    private ImageView imageview;
    String img = "";
    List<String> type;
    @FXML
    private Button Eimage;
    @FXML
    private Button btn;
    @FXML
    private Button btnmod;
    @FXML
    private Button btnsupp;
    @FXML
    private ComboBox<String> cat;
    private Personnel evenn = null;
    public ArrayList<Personnel> per;

    @FXML
    private TextField recherche;
    @FXML
    private Pagination pagination;
    connexionBd connection = null;
    private Connection cnx;
    int from = 0, to = 0;
    int itemPerPage = 5;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    
    public void initialize(URL url, ResourceBundle rb) {
 CategorieDao c = new CategorieDao();
        List<String> l = c.getNomCategorie();
        ObservableList<String> listCat = FXCollections.observableArrayList(l);
        cat.setValue("categorie");
        cat.setItems(listCat);
        type = new ArrayList();
        type.add("*.jpg");
        type.add("*.png");

        //afficherPer();

        int count = 0;
        String req = "SELECT count(*) FROM personnel";
        try {
            
            try (Statement pst1 = connexionBd.getInstance().getCnx().createStatement()) {
                ResultSet rs1 = pst1.executeQuery(req);
                rs1.first();
                count = rs1.getInt(1);
                rs1.close();
            
            
        } }
       
            catch (SQLException e) {
            e.printStackTrace();
        }
        NomColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ImageColonne.setCellValueFactory(new PropertyValueFactory<>("photo"));
        PrenomColonne.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        AgeColonne.setCellValueFactory(new PropertyValueFactory<>("age"));
        Nb.setCellValueFactory(new PropertyValueFactory<>("nb_h"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("prix_h"));
        CategorieColonne.setCellValueFactory(new PropertyValueFactory<>("categorie"));
       
        int pageCount = (count / itemPerPage) + 1;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(this::createPage);
        

       
    }

    @FXML
    private void importer(ActionEvent event) {
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png", type));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            System.out.println(fc.getName());
            img = fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img);
            imageview.setImage(i);
        }
    }

    @FXML
    private void AjouterPersonnel(ActionEvent event) throws IOException, SQLException {
        CategorieDao b1 = new CategorieDao();
        PersonnelDao b = new PersonnelDao();
        if (cat.getValue().isEmpty() || nom.getText().isEmpty() || prenom.getText().isEmpty() || aagee.getText().isEmpty() || prixheure.getText().isEmpty() || nombreheure.getText().isEmpty()) {
            Alert alertt = new Alert(Alert.AlertType.ERROR);
            alertt.setTitle("WARNING!");
            alertt.setHeaderText(null);
            alertt.setContentText("Merci de vérifier que vous avez remplit tout les champs");
            alertt.showAndWait();
        } else if (!aagee.getText().matches("^[0-9\\s]*$")) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider Age");
            alert1.setHeaderText(null);
            alert1.setContentText("L'âge doit être des chiffre");
            alert1.showAndWait();
        } else if (!nom.getText().matches("^[a-zA-Z\\s]*$")) {
            Alert alert11 = new Alert(Alert.AlertType.ERROR);
            alert11.setTitle("Verifier le Nom");
            alert11.setHeaderText(null);
            alert11.setContentText("Le champ NOM accepte que les lettres ");
            alert11.showAndWait();
        } else if (!prenom.getText().matches("^[a-zA-Z\\s]*$")) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Verifier le Prénom");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ PRENOM accepte que les lettres ");
            alert1.showAndWait();

        } else if (!prixheure.getText().matches("^[0-9\\s]*$")) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Verifier le prix");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ prix heure accepte que les chiffres ");
            alert1.showAndWait();
        } else if (!nombreheure.getText().matches("^[0-9\\s]*$")) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Verifier le nombre d'heure");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ NOMBREHEURE accepte que les chiffres ");
            alert1.showAndWait();
        } else {

            Personnel categorie = new Personnel(nom.getText(),
                    prenom.getText(),
                    Integer.parseInt(aagee.getText()), Float.parseFloat(prixheure.getText()), Float.parseFloat(nombreheure.getText()),
                    b1.getIdCategorie(cat.getValue()), img);
            b.addPersonnel(categorie);

            System.out.println(b1.getIdCategorie(cat.getValue()));

            Notifications notif = Notifications.create()
                    .title(" ajouté")
                    .text("Un nouveau personnel vient d'être ajoutée !")
                    .darkStyle().graphic(null).hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_LEFT)
                    .onAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println("Clicked ont notif");
                        }
                    });
            notif.showConfirm();
        }

       // afficherPer();
         int count = 0;
        String req = "SELECT count(*) FROM personnel";
        try {
            
            try (Statement pst1 = connexionBd.getInstance().getCnx().createStatement()) {
                ResultSet rs1 = pst1.executeQuery(req);
                rs1.first();
                count = rs1.getInt(1);
                rs1.close();
            
            
        } }
       
            catch (SQLException e) {
            e.printStackTrace();
        }
        NomColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ImageColonne.setCellValueFactory(new PropertyValueFactory<>("photo"));
        PrenomColonne.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        AgeColonne.setCellValueFactory(new PropertyValueFactory<>("age"));
        Nb.setCellValueFactory(new PropertyValueFactory<>("nb_h"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("prix_h"));
        CategorieColonne.setCellValueFactory(new PropertyValueFactory<>("categorie"));
       
        int pageCount = (count / itemPerPage) + 1;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(this::createPage);
        imageview.setImage(null);
        prenom.clear();
        aagee.clear();

        prixheure.clear();
        nombreheure.clear();
        nom.clear();

    }

    @FXML
    private void ModifierPersonnel(ActionEvent event) throws IOException {
        Personnel imen = tab.getSelectionModel().getSelectedItem();

        PersonnelDao cs = new PersonnelDao();
        CategorieDao b1 = new CategorieDao();
        System.out.println(imen);
        if (imen == null) {
            JOptionPane.showMessageDialog(null, "choisir personnel");

        } else {

            if (img != "") {
                Personnel e = new Personnel(nom.getText(), prenom.getText(), Integer.parseInt(aagee.getText()), Float.parseFloat(prixheure.getText()), Float.parseFloat(nombreheure.getText()), b1.getIdCategorie(cat.getValue()), img);
                cs.updatePersonnel(e, imen.getId());

            } else {
                cs.updatePersonnel(new Personnel(nom.getText(), prenom.getText(), Integer.parseInt(aagee.getText()), Float.parseFloat(prixheure.getText()), Float.parseFloat(nombreheure.getText()), b1.getIdCategorie(cat.getValue()), imen.getImage()), imen.getId());
            }
            System.out.println(imen.getId());
           // afficherPer();
             int count = 0;
        String req = "SELECT count(*) FROM personnel";
        try {
            
            try (Statement pst1 = connexionBd.getInstance().getCnx().createStatement()) {
                ResultSet rs1 = pst1.executeQuery(req);
                rs1.first();
                count = rs1.getInt(1);
                rs1.close();
            
            
        } }
       
            catch (SQLException e) {
            e.printStackTrace();
        }
        NomColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ImageColonne.setCellValueFactory(new PropertyValueFactory<>("photo"));
        PrenomColonne.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        AgeColonne.setCellValueFactory(new PropertyValueFactory<>("age"));
        Nb.setCellValueFactory(new PropertyValueFactory<>("nb_h"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("prix_h"));
        CategorieColonne.setCellValueFactory(new PropertyValueFactory<>("categorie"));
       
        int pageCount = (count / itemPerPage) + 1;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(this::createPage);
            JOptionPane.showMessageDialog(null, "personnel modifier");
            imageview.setImage(null);
            prenom.clear();
            aagee.clear();

            prixheure.clear();
            nombreheure.clear();
            nom.clear();
            imen = null;
        }

    }

    @FXML
    private void SuppPersonnet(ActionEvent event) throws SQLException {
        PersonnelDao cs = new PersonnelDao();
        Personnel cc = tab.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if (cc == null) {
            JOptionPane.showMessageDialog(null, "choisir un personnel");

        } else {
            cs.deletePersonnel(cc.getId());

            //afficherPer();
              int count = 0;
        String req = "SELECT count(*) FROM personnel";
        try {
            
            try (Statement pst1 = connexionBd.getInstance().getCnx().createStatement()) {
                ResultSet rs1 = pst1.executeQuery(req);
                rs1.first();
                count = rs1.getInt(1);
                rs1.close();
            
            
        } }
       
            catch (SQLException e) {
            e.printStackTrace();
        }
        NomColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ImageColonne.setCellValueFactory(new PropertyValueFactory<>("photo"));
        PrenomColonne.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        AgeColonne.setCellValueFactory(new PropertyValueFactory<>("age"));
        Nb.setCellValueFactory(new PropertyValueFactory<>("nb_h"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("prix_h"));
        CategorieColonne.setCellValueFactory(new PropertyValueFactory<>("categorie"));
       
        int pageCount = (count / itemPerPage) + 1;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(this::createPage);

            JOptionPane.showMessageDialog(null, "personnel supprimer avec succes");
            nom.clear();
            prenom.clear();
            aagee.clear();
            prixheure.clear();
            nombreheure.clear();

            imageview.setImage(null);

            cc = null;
            Notifications notif = Notifications.create()
                    .title(" supprimé")
                    .text("Un nouveau personnel vient d'être supprimé !")
                    .darkStyle().graphic(null).hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_LEFT)
                    .onAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println("Clicked ont notif");
                        }
                    });
            notif.showConfirm();
        }
    }

    private void afficherPer() {

        CategorieDao sp = new CategorieDao();

        List<Personnel> liste = sp.getCategorie();
        ObservableList et = FXCollections.observableArrayList(liste);
        tab.setItems(et);
        NomColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ImageColonne.setCellValueFactory(new PropertyValueFactory<>("photo"));
        PrenomColonne.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        AgeColonne.setCellValueFactory(new PropertyValueFactory<>("age"));
        Nb.setCellValueFactory(new PropertyValueFactory<>("nb_h"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("prix_h"));
        CategorieColonne.setCellValueFactory(new PropertyValueFactory<>("categorie"));

    }

    @FXML
    private void refModif(MouseEvent event) {
        Personnel evennt = tab.getSelectionModel().getSelectedItem();
        nom.setText(evennt.getNom());
        imageview.setImage(new Image(evennt.getImage()));
        int age = evennt.getAge();
        String nb_PPP = String.valueOf(age);
        aagee.setText(nb_PPP);
        float nb = (float) evennt.getNb_h();
        String nbh = String.valueOf(nb);
        nombreheure.setText(nbh);
        float prix = (float) evennt.getPrix_h();
        String prixx = String.valueOf(prix);
        prixheure.setText(prixx);
        prenom.setText(evennt.getPrenom());

    }
//    private void recherche(KeyEvent event) {
//    PersonnelDao PersonnelDao = new PersonnelDao();
//        recherche.setOnKeyReleased(e
//                -> {
//            if (recherche.getText().equals("") ) {
//
//                try {
//                    refreshB();
//                } 
//                catch (SQLException ex) {
//                    Logger.getLogger(PersonnelController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//
//            } else {
//
//                try {
//        NomColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
//       //ImageColonne.setCellValueFactory(new PropertyValueFactory<>("photo"));
//       PrenomColonne.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//       AgeColonne.setCellValueFactory(new PropertyValueFactory<>("age"));
//       Nb.setCellValueFactory(new PropertyValueFactory<>("nb_h"));
//       Prix.setCellValueFactory(new PropertyValueFactory<>("prix_h"));
//       CategorieColonne.setCellValueFactory(new PropertyValueFactory<>("categorie"));
//
//                    tab.getItems().clear();
//
//                    tab.setItems(PersonnelDao.recherchePersonnel(recherche.getText()));
//
//                } catch (SQLException ex) {
//                Logger.getLogger(PersonnelController.class.getName()).log(Level.SEVERE, null, ex);
//
//                }
//        
//
//            }
//        }
//        );
//
//    }

    private void refreshB() throws SQLException {
        List<Personnel> listB = new ArrayList<>();
        CategorieDao cr = new CategorieDao();
        listB = cr.getCategorie();

        ObservableList<Personnel> data = FXCollections.observableArrayList(listB);
        tab.setItems(data);

    }

    @FXML
    private void rechercher(KeyEvent event) {
        PersonnelDao PersonnelDao = new PersonnelDao();
        recherche.setOnKeyReleased(e
                -> {
            if (recherche.getText().equals("")) {

                try {
                    refreshB();
                } catch (SQLException ex) {
                    Logger.getLogger(PersonnelController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

                try {
                    NomColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
                    PrenomColonne.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                    AgeColonne.setCellValueFactory(new PropertyValueFactory<>("age"));
                    Nb.setCellValueFactory(new PropertyValueFactory<>("nb_h"));
                    Prix.setCellValueFactory(new PropertyValueFactory<>("prix_h"));
                    CategorieColonne.setCellValueFactory(new PropertyValueFactory<>("categorie"));
                    ImageColonne.setCellValueFactory(new PropertyValueFactory<>("photo"));

                    tab.getItems().clear();

                    tab.setItems(PersonnelDao.recherchePersonnel(recherche.getText()));

                } catch (SQLException ex) {
                    Logger.getLogger(PersonnelController.class.getName()).log(Level.SEVERE, null, ex);

                }

            }
        }
        );

    }

    public ObservableList<Personnel> getTableData() {
       ObservableList<Personnel> data = FXCollections.observableArrayList();
        try {
            String req = "Select s.id,s.nom,s.prenom, s.age,s.prix_h,s.nb_h,b.type ,s.image,s.categorie from categorie b INNER JOIN personnel s on b.id=s.categorie limit " + from + "," + to;
             try (Statement pst = connexionBd.getInstance().getCnx().createStatement() ){
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
  Personnel t= new Personnel (rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(4), rs.getFloat(5), rs.getFloat(6), rs.getString(7), rs.getString(8));
ImageView i=new ImageView();
i.setImage(new Image(rs.getString(8)));
i.setFitHeight(100);
i.setFitWidth(100);
t.setPhoto(i);
  data.add(t);
            }
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    private Node createPage(int pageIndex) {
        from = pageIndex * itemPerPage;
        to = itemPerPage;
        
        tab.setItems(FXCollections.observableArrayList(getTableData()));
        return tab;
    }

}
