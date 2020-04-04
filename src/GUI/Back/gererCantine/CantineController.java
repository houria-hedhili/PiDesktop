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
import java.io.File;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
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
System.out.println("commiti");
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

    @FXML
    private void upload(ActionEvent event) {
          FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png", typee));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
             img=fc.getAbsoluteFile().toURI().toString();
           System.out.print(img);
             Image i = new Image(img);
           imgg.setImage(i);
        }//dkhalt? oui 
    }

    @FXML
    private void ajout(ActionEvent event) {

 if (controlTitre()==false)
{Error("titre invalide");

}else
    {    Plat plat=new Plat(titre.getText(), description.getText(),img, type.getValue().toString(), 0, 0);
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
    private void modifier(ActionEvent event) {

                Plat x= table.getSelectionModel().getSelectedItem();
  // System.out.println(platUnique.get(0).getId());
if(controlTitre())
{ if(img == "")
{ 
    ps.modifierPlat(titre.getText(),type.getValue(),description.getText(),x.getImage(),x.getId());

}
else { ps.modifierPlat(titre.getText(),type.getValue(),description.getText(),img,x.getId());

}}
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

}
