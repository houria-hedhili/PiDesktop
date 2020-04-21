/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererCantine;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Entity.imen.Plat;
import GUI.login.LoginController;
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.imen.PlatService;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CantineController implements Initializable {
   
    
    @FXML
    private TableColumn<Plat, Date> dateCol;
 
    //*********/
       @FXML
    private TableView<Plat> table;
    @FXML
    private TableColumn<Plat,String> idCol;
    @FXML
    private TableColumn<Plat, String> typeCol;
    @FXML
    private TableColumn<Button, Boolean> ActionCol;
    
    /**********/
    @FXML
    private TextField titre;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Button image;
    @FXML
    private ImageView imgg;
    @FXML
    private TextArea description;
    @FXML
    private Button ajout;
    @FXML
    private Button modifier;

ObservableList<String> platList=FXCollections.observableArrayList("dessert","plat principal");
        List<Plat> listP = new ArrayList<>();
        
PlatService ps = new PlatService();
   List<String> typee;
            String img="";
    @FXML
    private Label erreurTitre;
    @FXML
    private Label ErreurDesc;
    @FXML
    private Label ErreurImg;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
                affichAllPlat();
 int id = LoginController.ID;
       System.out.println("hetha id user" +id);
        type.setValue("plat");
        type.setItems(platList);
          typee =new ArrayList();
        typee.add("*.jpg");
         typee.add("*.png");
                
    }    

    @FXML
    private void affichDet(MouseEvent event) {
    
        Plat x=table.getSelectionModel().getSelectedItem();
      // imgg.setImage(x.getImage());
                        imgg.setImage(new Image(x.getImage()));

       description.setText(x.getDescription());
       titre.setText(x.getNom());
       type.setValue(x.getType());
        
    }
    String immg="";
    String immmg="";

    @FXML
    private void upload(ActionEvent event) throws IOException {
          FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png", typee));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
             img=fc.getAbsoluteFile().toURI().toString();
             immg=fc.getPath();
             immmg=fc.getName();
             System.out.println(img);
          // System.out.print(img);
             Image i = new Image(img);
           imgg.setImage(i);
        }//dkhalt? oui 
    }
String des="";
String tit="";
    @FXML
    private void ajout(ActionEvent event) throws FileNotFoundException {

 if (titre.getText().isEmpty() || description.getText().isEmpty() || img.isEmpty() || type.getValue().equals("plat"))
{Error("Veuillez remplir tous le champs");

}else if(!titre.getText().matches("^[a-zA-Z\\s]*$"))
{Error("invalide titre : le champs titre ne contient que des lettres");
}else if(!description.getText().matches("^[a-zA-Z\\s]*$"))
{Error("invalide description : le champs description ne contient que des lettres");}
 else
    {   tit=titre.getText();
         des=description.getText();
   /*   String accessToken="EAAkiGpblq8kBAPGRmhprYw9NUZC6Dvp6SU1eM4JhooTFiuxgOymlDIUvOaPUoGJK3xUhLfGXTrOMmPh0hhxCwqF1byuyJeZAlzj3j7kntbktcmoyeO9Hn0cTDh6m8ypKSZAIXwyMk052ag0bZAt9KH3JQmZAqKZAJ9jEMgt2hqogFchu2S3y2zdhMEmmYsubSJAWImE7l8WQZDZD";
   FacebookClient fbClient=new DefaultFacebookClient(accessToken);
   FileInputStream fis=new FileInputStream(new File(immg));
   FacebookType response=fbClient.publish("me/photos",FacebookType.class, BinaryAttachment.with(immmg,fis),Parameter.with("message","Titre :"+tit+"\n"+des),Parameter.with("link","http://localhost/jardin1/web/app_dev.php/index"));
   System.out.println(response);*/
        
        //Plat plat=new Plat(titre.getText(), description.getText(),img, type.getValue().toString(),0,0,"100174535001126_"+response.getId());
                 Plat plat=new Plat(titre.getText(), description.getText(),"file:/C:/wamp/www/jardin1/web/"+immmg, type.getValue().toString(),0,0,"0",immmg);

if(img=="")
{img="plat.png";
plat.setImage(img);
} 
        Boolean test= ps.ajouterPlat(plat);
    if(test)
     {
       
     Success("Votre ajout a ete efectue");
     titre.setText("");
     description.setText("");
     img="";
     erreurTitre.setText("");
     
     type.setValue("plat");
   //  partageFb();
       Notifications notif=Notifications.create()
            .title("Facebook ")
            .text("Le plat a été publié !")
            .darkStyle().graphic(null).hideAfter(Duration.seconds(5))
            .position(Pos.TOP_LEFT)
            .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked ont notif");
                    }
                });
    notif.showConfirm();
      affichAllPlat();
     }
    }
        
    /*
    else
     { if(control(titre.getText())|| control(description.getText()))
     { Error("Veuillez  ne pas saisir des caracteres speciaux");
     }else if(control2()==false)
     {Error("Veuillez  remplir tous les champs");
     } else if(control3()==false)
     {   Error("la taille du champs est invalide");
     }
     else  { Error("invalid ajout");
     System.out.println(test);
     } 
      
     }*/ 
    }

    @FXML
    private void supprimer(ActionEvent event) {
          Plat x=table.getSelectionModel().getSelectedItem();
ps.deletePlat(x.getId());
titre.setText("");
description.setText("");
type.setValue("plat");
imgg.setImage(new Image(this.getClass().getResourceAsStream("plat.png")));
affichAllPlat();
         
    }

    @FXML
    private void modifier(ActionEvent event) throws FileNotFoundException {

                Plat x= table.getSelectionModel().getSelectedItem();
               
  // System.out.println(platUnique.get(0).getId());
if(img == "")
{  
    ps.modifierPlat(titre.getText(),type.getValue(),description.getText(),x.getImage(),x.getId(),x.getIdPost());

}
else { ps.modifierPlat(titre.getText(),type.getValue(),description.getText(),img,x.getId(),x.getIdPost());

}
affichAllPlat();
    }
    
      private void affichAllPlat()
   {        listP=ps.afficherAll();
ObservableList<Plat> platListe=FXCollections.observableArrayList(listP);
        ActionCol.setCellValueFactory(new PropertyValueFactory<>("photo"));
    typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
 dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    idCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
      table.setItems(platListe); 
   }/*************************************/
       private void Success(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajouter un plat");
 
        // Header Text: nullCla
        alert.setHeaderText(null);
        alert.setContentText(msg);
 
        alert.showAndWait();
    }
 
    private void Error(String msg) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("Look, an Error Dialog");
alert.setContentText(msg);
alert.showAndWait();
    }
    private boolean control(String ch)
{
for(int i=0;i<ch.length();i++)
{
if(ch.charAt(i)<'a'||ch.charAt(i)>'z'|| ch.charAt(i)<'A'||ch.charAt(i)>'Z')
{return false;
}
}
return true;
}
private boolean control2()
{
if((titre.getText()!="")&&(description.getText()!="")&&(img!="")&&(type.getValue().toString()!="plat"))
    {  return true;
    } return false;
}
private boolean control3()
{
if(titre.getText().length()<=2 && description.getText().length()<=10)
{return false;
}return true;
}
 
public boolean controlTitre()
{
      Pattern pattern = Pattern.compile("[^A-Z&&[^a-z&&[^ ]]]");
                   Matcher matcher = pattern.matcher(titre.getText()); 

      if(! matcher.find())
      {if(!"".equals(titre.getText())){
return true;      }else  {
      erreurTitre.setText("veuillez remplir ce champs");
      return false;
      }
    }
else  {erreurTitre.setText("champs saisi invalide ");
      return false;
      } 
    
}

public boolean controlDesc()
{
      Pattern pattern = Pattern.compile("[A-Z&&[a-z]]");
                   Matcher matcher = pattern.matcher(titre.getText()); 

      if(! matcher.find())
      {if(!"".equals(description.getText())){
return true;      }else  {
      ErreurDesc.setText("veuillez remplir ce champs");
      return false;
      }
    }
else  {ErreurDesc.setText("champs saisi invalide ");
      return false;
      } 
    
}

public void partageFb() throws FileNotFoundException
{
   String accessToken="EAAkiGpblq8kBAP92Wf1PGqrQktdZA2rZB8aSOFBwWPRWgazFym064FDm9Ncsca1vCd7cq3UqJSZAXqArfRNIMB8SobOXmexmWAlRmSjq2m86f32ioAT0XNEOrq6nDNGGqthoR8O0FYpfNl6Teb4994xsitgGTE8iMbcahEfgTdcMB0lDkBHHg90DTyh47gZD";
   FacebookClient fbClient=new DefaultFacebookClient(accessToken);
   FileInputStream fis=new FileInputStream(new File(immg));
   FacebookType response=fbClient.publish("me/photos",FacebookType.class, BinaryAttachment.with(immmg,fis),Parameter.with("message","Titre :"+tit+"\n"+des),Parameter.with("link","http://localhost/jardin1/web/app_dev.php/index"));
   System.out.println(response);
}


    @FXML
    private void Avance(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/GUI/Back/gererCantine/Avance.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
    
    }

}
