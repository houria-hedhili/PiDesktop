/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererCantine.abonnement;

import Entity.imen.Menu;
import Entity.imen.Plat;
import Entity.imen.abonnement;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.imen.PlatService;
import service.imen.abonnementService;
import service.imen.menuService;

/**
 * FXML Controller class
 *
 * @author Imen
 */
public class AbonCantineController implements Initializable {
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
    private TilePane gridPlat;
    int r;
    List<Plat> listP = new ArrayList<>();
    List<Menu> listm = new ArrayList<>();
int x=0;
            int y=0;
        PlatService ps = new PlatService();
        menuService ms =new menuService();
        int count=0;
        int nbrColumn=3;
        int nbrRow=2;
        Image[] i =new Image[30];
        String text;
       int[] idP= new int[30];
       String dessert;
       int idDessert;
       int idPlatPrincipal;
       String platPrincipal;
       int test=0;
           RadioButton[] rb2 = new RadioButton[20];
    @FXML
    private ImageView img;
    @FXML
    private ComboBox<String> listeEnfant;
    @FXML
    private Button ajoutMenu;
    @FXML
    private TableView<Menu> tableMenu;
    @FXML
    private TableColumn<Menu, String> nomColl;
    @FXML
    private TableColumn<Menu, String> prenomColl;
    @FXML
    private TableColumn<Menu, String> platColl;
    @FXML
    private TableColumn<Menu, String> dessertColl;
    @FXML
    private Button suppMenu;
    @FXML
    private ComboBox<String> platCombo;
    @FXML
    private ComboBox<String> dessertCombo;
    @FXML
    private Button modfierMenu;
    @FXML
    private TableView<abonnement> tableabon;
    @FXML
    private TableColumn<abonnement, Date> datedCol;
    @FXML
    private TableColumn<abonnement, Date> datefCol;
    @FXML
    private TableColumn<abonnement, Integer> nbenfantCol;
    @FXML
    private TableColumn<abonnement,Double> tarifCol;
    @FXML
    private DatePicker datef;
    @FXML
    private Button modifierAbonn;
    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker datef2;
    @FXML
    private TextField nbenfant2;
    @FXML
    private Button ajoutAbon;
      abonnementService as=new abonnementService();  
    @FXML
    private TableColumn<abonnement, String> etatCol;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gridPlat.setPrefColumns(nbrColumn);
        gridPlat.setPrefRows(nbrRow);
         List<String> enfants =ms.listeEnfant(1);
         enfants.add("all");
                 ObservableList<String> enfList=FXCollections.observableArrayList(enfants);
                 listeEnfant.setValue("Liste enfants");
                 listeEnfant.setItems(enfList);
afficher();
         List<String> l1=ps.platType("dessert");
                  List<String> l2=ps.platType("plat principal");
                                   ObservableList<String> platList=FXCollections.observableArrayList(l2);
                 ObservableList<String> dessertList=FXCollections.observableArrayList(l1);
platCombo.setValue("plat principal");
platCombo.setItems(platList);
dessertCombo.setValue("dessert");
dessertCombo.setItems(dessertList);
      afficherAbonn();    
     AllImage();
     Images();
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
    
    private void AllImage()
    { List<String> ch= new ArrayList<>();
        listP=ps.afficherAll();
         ch=ps.afficherPlat();
         for(int j =0 ;j<listP.size();j++)
         {
         i[j]=new Image(listP.get(j).getImage());
         idP[j]=listP.get(j).getId();
         rb2[j]= new RadioButton(ch.get(j));
         }
    }
    private void Images()
    {
    for (int k=0;k<nbrColumn;k++)
    {
        for(int j=0;j<nbrRow;j++)
        {gridPlat.getChildren().add(createPage(count));
    count++;}
    }
    }
    private VBox createPage(int index)
    {        listP=ps.afficherAll();


        ImageView imageview = new ImageView();
    Image imag =i[index];
    imageview.setImage(imag);
    imageview.setFitWidth(170);
    imageview.setFitHeight(170);
    imageview.setSmooth(true);
    imageview.setCache(true);

  VBox pageImage =new VBox();
  imageview.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

     public void handle(MouseEvent event) {

       r=index;  
         Plat p=ps.PlatId(idP[r]);
                //Stage stage = (Stage) retour1.getScene().getWindow();
      Parent root;
            

         try { 
             FXMLLoader loader = new FXMLLoader();
                     loader.setLocation(getClass().getResource("/GUI/Front/gererCantine/abonnement/creerMenu.fxml"));

   
              
              Parent detail=loader.load();
              Scene scene = new Scene(detail);
               CreerMenuController controller = loader.getController();
               controller.afficher(p);
             Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        System.out.println(r);
        window.setScene(scene);
        window.show();
     
         } catch (IOException ex) {
             Logger.getLogger(AbonCantineController.class.getName()).log(Level.SEVERE, null, ex);
         }       
        
         event.consume();
     }
});
  RadioButton affecter= rb2[index];
    affecter.setOnAction(new EventHandler<ActionEvent>() {
boolean t=false;

int k=0;
        @Override
        public void handle(ActionEvent arg0) {
            r=index;//ch
             int tab[]=new int[7];
            if(affecter.isSelected() && test<=1 ){
              test++;
                          int p=index;

              if(ps.getTypePlat(idP[p]).equals("dessert"))
              {
              idDessert=idP[p];
          tab[k]=idDessert;    
              }else {idPlatPrincipal=idP[p];
                  
              tab[k]=idPlatPrincipal;}
              
              
              k++;
              
              
              
            }
            else if(affecter.isSelected()==false  && t==false )
            {            //affecter.setDisable(false);

                test--;
                
                if(ps.getTypePlat(idP[r]).equals("dessert"))
              {
              idDessert=0;
              }else idPlatPrincipal=0;
            
            }
            
            
            
            else if(test>1)
            { t=true;
            //affecter.disableProperty();
            affecter.setSelected(false);
            test=2;
           
                  
            }
            

        System.out.println("id Plat"+idPlatPrincipal+"id dessert "+idDessert+ " test "+test);
        }
    });
    pageImage.getChildren().add(imageview);
    pageImage.getChildren().add(affecter);
            //pageImage.getChildren().add(label);
    pageImage.setStyle("-fx-border-color: orange;");
    imageview=null;
    return pageImage;
    }

    @FXML
    private void ajoutMenu(ActionEvent event) {
        
   Menu m = new Menu(idPlatPrincipal,idDessert,1,ms.enfant(1, listeEnfant.getValue()));
 
          
   if(listeEnfant.getValue().equals("all"))
   {   
       for(int x=0;x<listeEnfant.getItems().size();x++)
       {Menu mi = new Menu(idPlatPrincipal,idDessert,1,ms.enfant(1, listeEnfant.getItems().get(x))); 
        ms.ajouterMenu(mi);
       }
       
   }else
   {Menu mi = new Menu(idPlatPrincipal,idDessert,1,ms.enfant(1, listeEnfant.getValue())); 
        ms.ajouterMenu(mi);
   
   //if menu affecter nfaskh men combo box esmou
   }
    }

    @FXML
    private void suppMenu(ActionEvent event) {
       Menu mii= tableMenu.getSelectionModel().getSelectedItem();
       ms.deleteMenu(mii.getId());
      
       afficher();
    }
    
    private void afficher()
    {
    List<Menu> mi=ms.afficherAll(1);
    ObservableList<Menu> platListe=FXCollections.observableArrayList(mi);
        nomColl.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prenomColl.setCellValueFactory(new PropertyValueFactory<>("prenom"));
 platColl.setCellValueFactory(new PropertyValueFactory<>("plat"));
    dessertColl.setCellValueFactory(new PropertyValueFactory<>("dessert"));
      tableMenu.setItems(platListe);
    }

    @FXML
    private void modifierMenu(ActionEvent event) {
               Menu mii= tableMenu.getSelectionModel().getSelectedItem();
               System.out.println("dessert "+ps.PlatUnique2(dessertCombo.getValue()) +" pLT " +ps.PlatUnique2(platCombo.getValue())+" id   " + mii.getId());

   ms.modifierPlat(ps.PlatUnique2(dessertCombo.getValue()),ps.PlatUnique2(platCombo.getValue()),mii.getId());
   afficher();
    }

    @FXML
    private void raff(Event event) {
        afficher();
    }

    @FXML
    private void affichMod(MouseEvent event) {
             Menu mii= tableMenu.getSelectionModel().getSelectedItem();

dessertCombo.setValue(mii.getDessert());
platCombo.setValue(mii.getPlat());
    }

    @FXML
    private void modAbon(MouseEvent event) {
    
         abonnement a = tableabon.getSelectionModel().getSelectedItem();
         datef.setValue(a.getDatef().toLocalDate());
    }

    @FXML
    private void modifierAbonn(ActionEvent event) {
                 abonnement a = tableabon.getSelectionModel().getSelectedItem();
                java.sql.Date sqlDate = java.sql.Date.valueOf(datef.getValue());

   as.modifierAbon(sqlDate, 0, a.getId());
           afficherAbonn();

    }

    @FXML
    private void ajoutAbon(ActionEvent event) {
        LocalDate d= (LocalDate)dated.getValue();
        LocalDate f=datef2.getValue();
                java.sql.Date sqlDate = java.sql.Date.valueOf(d);
                java.sql.Date sqlDate1 = java.sql.Date.valueOf(f);
       int nb=Integer.valueOf(nbenfant2.getText());
        

        abonnement a=new abonnement(sqlDate, sqlDate1,0,nb);
        as.ajouterAbon(a);
        System.out.print(sqlDate);
        afficherAbonn();
    }
    
    public void afficherAbonn()
    {
    List<abonnement> ls=as.afficherAll();
        ObservableList<abonnement> abonListe=FXCollections.observableArrayList(ls);
        datedCol.setCellValueFactory(new PropertyValueFactory<>("dated"));
    datefCol.setCellValueFactory(new PropertyValueFactory<>("datef"));
etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
    tarifCol.setCellValueFactory(new PropertyValueFactory<>("tarif"));
    nbenfantCol.setCellValueFactory(new PropertyValueFactory<>("nbEnfant"));
     tableabon.setItems(abonListe);
    
    }
}
