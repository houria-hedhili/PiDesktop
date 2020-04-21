 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererCantine.abonnement;

import Entity.imen.Menu;
import Entity.imen.Plat;
import Entity.imen.abonnement;
import Entity.user.Utilisateur;
import GUI.login.LoginController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    private TableView<abonnement> tableabon;
    @FXML
    private TableColumn<abonnement, Date> datedCol;
    @FXML
    private TableColumn<abonnement, Date> datefCol;
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
      abonnementService as=new abonnementService();  
    @FXML
    private TableColumn<abonnement, String> etatCol;
    int id=0;
    @FXML
    private ListView<String> Liste;
    List<String> listSelection=new ArrayList();
    @FXML
    private TableColumn<?, ?> nomCol;
    @FXML
    private TableColumn<?, ?> prenomCol;
    @FXML
    private Pane pla;
    @FXML
    private Button ajoutAbon;
    @FXML
    private AnchorPane platL;
    @FXML
    private Button retour4;
    @FXML
    private ScrollPane platListe;
    @FXML
    private AnchorPane platL1;
    @FXML
    private Button retour41;
    @FXML
    private Button creerMenu;
    @FXML
    private VBox menuEnfant;
    @FXML
    private ImageView bulle;
    @FXML
    private Button raffraichir;
    @FXML
    private Text labelo;
    @FXML
    private ImageView raff;
    @FXML
    private Button test1;
    @FXML
    private Tab platDuJour;
    @FXML
    private Tab VotreMenu;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bulle.setVisible(false);
        raffraichir.setVisible(false);
        labelo.setVisible(false);
       raff.setVisible(false);
        System.out.println(ModifierMenuController.mo);
      test1.setVisible(false);
          
        id = LoginController.ID;
        try {
            display_plat();
        } catch (SQLException ex) {
            Logger.getLogger(AbonCantineController.class.getName()).log(Level.SEVERE, null, ex);
        }
     afficherMenuEnfant();
         List<String> enfants =ms.listeEnfant(id);
         
        /* enfants.add("all");
                 ObservableList<String> enfList=FXCollections.observableArrayList(enfants);
                 listeEnfant.setValue("Liste enfants");
                 listeEnfant.setItems(enfList);*/
                 dated.setValue(LocalDate.now());
                 datef2.setValue(LocalDate.now());
                 /************************/
                 List<String> L=new ArrayList<>();
                      List<String> enfantss =as.listeEnfant(id);
                      for(int i=0;i<enfantss.size();i++)
                      {
                          if(as.abonne(as.getEnfant(enfantss.get(i), id))==false)
                          {
                          L.add(enfantss.get(i));
                          }
                      }
                     ObservableList<String> enfListe=FXCollections.observableArrayList(L);

Liste.setItems(enfListe);
                Liste.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

                 
                 /************/

       /*  List<String> l1=ps.platType("dessert");
                  List<String> l2=ps.platType("plat principal");
                                   ObservableList<String> platList=FXCollections.observableArrayList(l2);
                 ObservableList<String> dessertList=FXCollections.observableArrayList(l1);
platCombo.setValue("plat principal");
platCombo.setItems(platList);
dessertCombo.setValue("dessert");
dessertCombo.setItems(dessertList);*/
      afficherAbonn();    
      
      /**************/
  
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
    
    
   

   

    @FXML
    private void modAbon(MouseEvent event) {
    
         abonnement a = tableabon.getSelectionModel().getSelectedItem();
         datef.setValue(a.getDatef().toLocalDate());
    }

    @FXML
    private void modifierAbonn(ActionEvent event) {
                 abonnement a = tableabon.getSelectionModel().getSelectedItem();
                java.sql.Date sqlDate = java.sql.Date.valueOf(datef.getValue());
 java.sql.Date sqlDate1=a.getDatef();
                
if(sqlDate1.compareTo(sqlDate)>0)
    
{ 
    Error("Date fin doit etre superieur a celle du debut");
   
}else{  as.modifierAbon(sqlDate, 0, a.getId());
  Success("Modification a ete effectuer avec succes");
          
afficherAbonn();}

    }

    @FXML
    private void ajoutAbon(ActionEvent event) {
        

  boolean test=false;
                
                double y=calculTarif();
// int nb=Integer.valueOf(nbenfant2.getText());
   System.out.println("hetha date d'aujourfi"+new java.util.Date());
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    System.out.println(formater.format(new java.util.Date()));
   String aujourdhui=formater.format(new java.util.Date());
   if(dated.getValue().equals(LocalDate.now())||datef2.getValue().equals(LocalDate.now()) ||(listSelection.isEmpty()))
   {Error("vous devez remplir tout le champ");
   } else if(dated.getValue().compareTo(datef2.getValue())>0)
   {Error("Date fin doit etre superieur a celle du debut");
   }else if (dated.getValue().toString().compareTo(aujourdhui)<0)
   {Error("Date debut doit etre superieur a celle d'aujourdhui");
   
   }else if (listSelection.isEmpty())
   {Error("Veuillez selectionner un enfant");
   
   }else
   {LocalDate d= (LocalDate)dated.getValue();
        LocalDate f=datef2.getValue();
                java.sql.Date sqlDate = java.sql.Date.valueOf(d);
                java.sql.Date sqlDate1 = java.sql.Date.valueOf(f);
      
 
       int z=as.getEnfant(Liste.getSelectionModel().getSelectedItem(), id);
   System.out.println("hetha id mte3 el enfant "+z);
           abonnement a=new abonnement(sqlDate, sqlDate1,y,z,id);
        as.ajouterAbon(a);
        test=true;
        
   if (test){
       List<String> L=new ArrayList<>();
                      List<String> enfantss =as.listeEnfant(id);
                      for(int i=0;i<enfantss.size();i++)
                      {
                          if(as.abonne(as.getEnfant(enfantss.get(i), id))==false)
                          {
                          L.add(enfantss.get(i));
                          }
                      }
                     ObservableList<String> enfListe=FXCollections.observableArrayList(L);

Liste.setItems(enfListe);
      platDuJour.setDisable(false);
          VotreMenu.setDisable(false);
  Success("ajout a ete effectuer avec succes");}}
  afficherAbonn();
    }
       private void Success(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajouter un plat");
 
        // Header Text: nullCla
        alert.setHeaderText(null);
        alert.setContentText(msg);
 
        alert.showAndWait();
    }
    public void afficherAbonn()
    { 
    List<abonnement> ls=as.afficherAll(LoginController.ID);
        ObservableList<abonnement> abonListe=FXCollections.observableArrayList(ls);
        datedCol.setCellValueFactory(new PropertyValueFactory<>("dated"));
    datefCol.setCellValueFactory(new PropertyValueFactory<>("datef"));
etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
    tarifCol.setCellValueFactory(new PropertyValueFactory<>("tarif"));
   nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
      prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));

     tableabon.setItems(abonListe);
    
    }
      private void Error(String msg) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("Look, an Error Dialog");
alert.setContentText(msg);
alert.showAndWait();
    }
    
    
       public String getUser(String nom ,String prenom )
    { String ch=nom+" "+prenom;

        return ch ;
    }
     public String getUsername(String nom  )
    { String ch=nom;
        return ch ;
    }
     
     public double calculTarif()
     { double x=0;
     try {
        LocalDate d= (LocalDate)dated.getValue();
        LocalDate f=datef2.getValue();
                java.sql.Date sqlDate = java.sql.Date.valueOf(d);
                java.sql.Date sqlDate1 = java.sql.Date.valueOf(f);
       long diff = sqlDate1.getTime() - sqlDate.getTime();
       float res = (diff / (1000*60*60*24));
      x=res*6;
       
     } catch (Exception e) {
         e.printStackTrace();
     }
     return x;
     }
     
public boolean controlDate()
{LocalDate d= (LocalDate)dated.getValue();
        LocalDate f=datef2.getValue();
                java.sql.Date sqlDate = java.sql.Date.valueOf(d);
                java.sql.Date sqlDate1 = java.sql.Date.valueOf(f);
                 long diff = sqlDate.getTime() - sqlDate1.getTime();
       float res = (diff / (1000*60*60*24));
                if(res>0)
                {return true;
                }else return false;
                    
}

    @FXML
    private void listenfant(MouseEvent event) {
        
        if(!Liste.getSelectionModel().getSelectedItem().isEmpty())
        {  ObservableList<String> selectedItems =  Liste.getSelectionModel().getSelectedItems();
                                for(String s : selectedItems){
                           listSelection.add(s);
                        }
        } else listSelection.clear();
        

    }
static int j=0;
static Plat plat=new Plat();
   /*************************/
        public void display_plat()throws SQLException{
           List<Plat> listPlat=ps.afficherAll();
          List<VBox> list = new ArrayList<>();
         
          while(j<listPlat.size()){
              ImageView va=new ImageView(new Image("file:/C:/wamp/www/jardin1/web/"+listPlat.get(j).getImg()));
               va.setFitHeight(200);
                va.setFitWidth(743);


              HBox h= new HBox();
              Label inscrit=new Label("");
   


             Label nomm=new Label("nom plat : "+listPlat.get(x).getNom()+"");
          nomm.setStyle("-fx-font-family :Cooper Black;-fx-font-size:15;-fx-font-weight: bold;");
             Label local=new Label( " Type : "+listPlat.get(x).getType());
                          local.setStyle("-fx-font-family :Cooper Black;-fx-font-size:15;-fx-font-weight: bold;");

             Button bt2=new Button("Detail" ) ;
             bt2.setStyle("-fx-background-color:#16A0F8;" +
"    -fx-background-insets: 0,1,2,3;" +
"    -fx-background-radius: 3,2,2,2;" +
"    -fx-padding: 12 30 12 30;" +
"    -fx-text-fill: white;" +
"    -fx-font-size: 12px;");
             /*****************/
             int n=j;
                      ImageView vue=new ImageView(new Image(this.getClass().getResourceAsStream("vue.png")));
vue.setFitHeight(20);
vue.setFitWidth(40);
System.out.println(String.valueOf("1: "+listPlat.get(j).getNbrVue()));
Label nbrVue=new Label(String.valueOf(listPlat.get(j).getNbrVue()));
             bt2.setOnAction(new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent event) {
                     int xii= ps.getNbrVue(plat.getId())+1;
                      plat=listPlat.get(n); 
                   boolean test=ps.rechercheVue(LoginController.ID,plat.getId());
                   if(test==false)
                   {ps.nbrVue(LoginController.ID,AbonCantineController.plat.getId());
 
  ps.updateVue(plat.getId(),xii);
   
 plat.setNbrVue(ps.getNbrVue(plat.getId()));
   System.out.println("2 :"+plat.getNbrVue());
   nbrVue.setText(String.valueOf(plat.getNbrVue()));}
   
                      
                  Stage stage=new Stage();
                   Parent root;
                      try {
                          root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererCantine/abonnement/creerMenu.fxml"));
                   Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
                      } catch (IOException ex) {
                          Logger.getLogger(AbonCantineController.class.getName()).log(Level.SEVERE, null, ex);
                      }
        
                  
                  }
              });
             Text t=new Text();
             /*********************/
              HBox No= new HBox();
              No.setSpacing(10);
              No.setAlignment(Pos.CENTER);
               No.getChildren().addAll(nomm);
             HBox ins= new HBox();
              ins.setSpacing(10);
              ins.setAlignment(Pos.CENTER);
               ins.getChildren().addAll(inscrit);  
               HBox adres= new HBox();
              adres.setSpacing(10);
              adres.setAlignment(Pos.CENTER);
               adres.getChildren().addAll(local);
                   HBox det= new HBox();
              det.setSpacing(3);
              det.setAlignment(Pos.CENTER);
               det.getChildren().addAll(bt2,vue,nbrVue);
               VBox v1=new VBox();
               v1.setAlignment(Pos.CENTER);
               
               
               v1.setSpacing(10);
               

               v1.getChildren().addAll(va,No,adres,ins,det,h);
               list.add(v1);
               
               
               
               
               
              j++;


          }
          pla.getChildren().clear();
         pla.getChildren().addAll(list);
        
    }
        static Menu mi=new Menu();
        void afficherMenuEnfant()
        {List<VBox> list=new ArrayList<>();
        List<Menu> listeMenu=ms.afficherAll(LoginController.ID);
        for(int l=0;l<listeMenu.size();l++)
        {
        Label nom=new Label();
        nom.setText(listeMenu.get(l).getNom()+" "+listeMenu.get(l).getPrenom());
                nom.setStyle("-fx-font-family :Cooper Black;-fx-font-size:15;-fx-font-weight: bold;");

      /********************/
        VBox v=new VBox();
        Label plat=new Label();
        plat.setText("Plat :"+listeMenu.get(l).getPlat());
        plat.setStyle("-fx-font-family :Cooper Black;-fx-font-size:15;-fx-font-weight: bold;");
        Label dessert = new Label();
        dessert.setText("Dessert :"+listeMenu.get(l).getDessert());
               dessert.setStyle("-fx-font-family :Cooper Black;-fx-font-size:15;-fx-font-weight: bold;");

         v.setAlignment(Pos.CENTER);
         
               v.setSpacing(5);
               v.getChildren().addAll(plat,dessert);
        /*************/
               
         ImageView va=new ImageView(new Image(this.getClass().getResourceAsStream("edit.png")));
               va.setFitHeight(20);
                va.setFitWidth(20);
  ImageView v1=new ImageView(new Image(this.getClass().getResourceAsStream("delete.png")));
               v1.setFitHeight(20);
                v1.setFitWidth(20);

         HBox No= new HBox();
              No.setSpacing(30);
              No.setAlignment(Pos.CENTER);
               No.getChildren().addAll(nom,v,va,v1);
                VBox all=new VBox();
               all.setAlignment(Pos.CENTER);
               
               
               all.setSpacing(20);
               

               all.getChildren().addAll(No);
               list.add(all);
               int k=l;
               /*********************************/
               va.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

     @Override
     public void handle(MouseEvent event) {
         mi=listeMenu.get(k);
             Stage stage=new Stage();
                   Parent root;
                      try { 
                           bulle.setVisible(true);
        raffraichir.setVisible(true);
        labelo.setVisible(true);
       raff.setVisible(true);
                          root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererCantine/abonnement/modifierMenu.fxml"));
                   Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
                      } catch (IOException ex) {
                          Logger.getLogger(AbonCantineController.class.getName()).log(Level.SEVERE, null, ex);
                      }
        
     }
});
         v1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

     @Override
     public void handle(MouseEvent event) {
                      bulle.setVisible(true);
        raffraichir.setVisible(true);
        labelo.setText("Voulez vous vraiment supprimer le menu ?");
        labelo.setVisible(true);
       raff.setVisible(true);
         mi=listeMenu.get(k);
             ms.deleteMenu(mi.getId());
        
     }
});
        }/***********************/
        
           menuEnfant.getChildren().clear();
        menuEnfant.getChildren().addAll(list);
        }

    @FXML
    private void raff(Event event) {
    }

    @FXML
    private void creerMenu(ActionEvent event) throws IOException {
        
    
      Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererCantine/abonnement/ajoutMenu.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
         stage.setScene(scene);
        stage.show();
              bulle.setVisible(true);
        raffraichir.setVisible(true);
        labelo.setVisible(true);
        labelo.setText("voulez vous vraiment ajouter ce menu ?");
       raff.setVisible(true);
       /* stage1.setScene(scene);
        stage1.show();*/
    }

    @FXML
    private void raffraichir(ActionEvent event) {
        afficherMenuEnfant();
        System.out.println("kaa3ed nenzel");
         bulle.setVisible(false);
        raffraichir.setVisible(false);
        labelo.setVisible(false);
       raff.setVisible(false);
    }

    @FXML
    private void test1(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererCantine/abonnement/platPrincipal.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
       /* stage1.setScene(scene);
        stage1.show();*/
    }

     
}
