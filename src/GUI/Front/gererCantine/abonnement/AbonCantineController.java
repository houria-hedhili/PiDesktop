/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererCantine.abonnement;

import Entity.imen.Plat;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.imen.PlatService;

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
    List<Plat> listP = new ArrayList<>();
        PlatService ps = new PlatService();
        int count=0;
        int nbrColumn=3;
        int nbrRow=2;
        Image[] i =new Image[30];
    @FXML
    private ImageView img;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gridPlat.setPrefColumns(nbrColumn);
        gridPlat.setPrefRows(nbrRow);
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
    { 
        listP=ps.afficherAll();
         
         for(int j =0 ;j<listP.size();j++)
         {
         i[j]=new Image(listP.get(j).getImage());
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
  /*Stage newWindow = new Stage();
  Label secondLabel = new Label("I'm a Label on new Window");
 
                StackPane secondaryLayout = new StackPane();
                secondaryLayout.getChildren().add(secondLabel);
 
                Scene secondScene = new Scene(secondaryLayout, 230, 100);             
  newWindow.setTitle("Second Stage");
  newWindow.setHeight(250);
    newWindow.setWidth(250);
  newWindow.setScene(secondScene);
 
                // Set position of second window, related to primary window.
           
 
                newWindow.show();*/
                Stage stage = (Stage) retour1.getScene().getWindow();
      Parent root;
         try {
             root = FXMLLoader.load(getClass().getResource("/GUI/Front/gererCantine/abonnement/creerMenu.fxml"));
              Scene scene = new Scene(root);
               stage.setScene(scene);
              stage.show();
         } catch (IOException ex) {
             Logger.getLogger(AbonCantineController.class.getName()).log(Level.SEVERE, null, ex);
         }       
        
         event.consume();
     }
});
    
    pageImage.getChildren().add(imageview);
    
    //pageImage.getChildren().add(label);
    pageImage.setStyle("-fx-border-color: orange;");
    imageview=null;
    return pageImage;
    }
    
    
    
}
