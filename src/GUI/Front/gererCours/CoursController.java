/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.gererCours;

import ConnexionBd.connexionBd;
import Entity.houria.Cours;
import Entity.houria.Matiere;
import Entity.houria.Ratting;
import GUI.login.LoginController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;
import service.houria.CoursCRUD;
import service.houria.RatingCRUD;
import service.houria.matiereCRUD;

/**
 * FXML Controller class
 *
 * @author Imen
 */
public class CoursController implements Initializable {
    @FXML
    private TabPane tabpane;
    @FXML
    private Button retour1;
    private Button retour2;
    @FXML
    private AnchorPane prochEvent;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox courscontainer;
  
    connexionBd connection = null;
        private Connection cnx;
        Statement ste;
        
        CoursCRUD crud=new CoursCRUD();
        RatingCRUD rr=new RatingCRUD();
        
    public CoursController() {
        cnx= connexionBd.getInstance().getCnx();
    }
                     int id = LoginController.ID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        courscontainer.setSpacing(5); // TODO
        try {
            display_cours();
        } catch (SQLException ex) {
            Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void retour4(ActionEvent event) throws IOException {
   Stage stage = (Stage) retour1.getScene().getWindow();
    Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front/Acceuilfront/acceuilFront.fxml"));
        Stage stage1=new Stage();
        Scene scene = new Scene(root);
        
        stage1.setScene(scene);
        stage1.show();
    stage.close();
     
    }
    
      private void display_cours() throws SQLException, IOException{
         // participationService pa = new participationService();
          String req="select * from cours  ";
          List<VBox> list = new ArrayList<>();
          ste=cnx.createStatement();
          ResultSet rs = ste.executeQuery(req);
          while(rs.next()){
              
             Cours e= new Cours(rs.getInt(1), rs.getString(2),rs.getTime(3),rs.getInt(4),rs.getInt(5));
             e.setId(rs.getInt(6));
              ImageView va=new ImageView(new Image("file:/C:/xampp/htdocs/integration/jardin/web/images/courses/"+rs.getString(7)));
               va.setFitHeight(200);
                va.setFitWidth(200);
          
             Matiere m =new Matiere();
             matiereCRUD cr =new matiereCRUD();
             m=cr.getMatiereId(rs.getInt(1));
             Label nomm=new Label(" ce cours est dédié pour enseigner la matiere : "+ m.getNom()+" avec le coefficient :"+m.getCoefficient()+" aux enfants ayant l age : "+e.getAge()+" ans ");
             Label duree=new Label( " le duree de ce cours : "+e.getDuree());
             Label place=new Label( " le nombre de place disponible pour ce cours est : "+e.getSeats()+" places ");
             Label rdv=new Label( " Donc Rendez vous a notre jardin COCCINELLE pour plus d information ");

             Button bt2=new Button("plus de details" ) ;
             Rating ratingcours=new Rating();
            ratingcours.setPartialRating(true);
              //System.out.println(rr.notefinal(e));
              ratingcours.setRating(rr.notefinal(e));
              HBox h= new HBox();
              h.setSpacing(10);
              h.setAlignment(Pos.CENTER);
              h.getChildren().addAll(ratingcours);
                         
              HBox No= new HBox();
              No.setSpacing(10);
              No.setAlignment(Pos.CENTER);
               No.getChildren().addAll(nomm);
                // h.getChildren().addAll(bt1,bt2);
                         
              HBox ag= new HBox();
              ag.setSpacing(10);
              ag.setAlignment(Pos.CENTER);
               ag.getChildren().addAll(place);
               HBox coef= new HBox();
              coef.setSpacing(10);
              coef.setAlignment(Pos.CENTER);
               coef.getChildren().addAll(duree);
                          HBox rd= new HBox();
              rd.setSpacing(10);
              rd.setAlignment(Pos.CENTER);
               rd.getChildren().addAll(rdv);
               VBox v1=new VBox();
               v1.setAlignment(Pos.CENTER);
               v1.setSpacing(10);
               v1.getChildren().addAll(va,No,coef,ag,rd,h);
               list.add(v1);
               ratingcours.ratingProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number old, Number newT) {
                 try {
                if( rr.test(e,id)==-1){
                //To change body of generated methods, choose Tools | Templates.
                System.out.println("Votre Avis:"+newT);
               Ratting hou= new Ratting(id,e.getId(),newT.doubleValue());
               rr.addRating(e,id,newT.doubleValue());

                }else{
                    System.out.println("rak votit lena dja 9bal msr ");
                            JOptionPane jop = new JOptionPane();    	
                            int option = jop.showConfirmDialog(null, 
                              "Voulez-vous changer votre note ! ",
                              "vous avez déja voté a ce cours ", 
                              JOptionPane.YES_NO_CANCEL_OPTION, 
                              JOptionPane.QUESTION_MESSAGE);

                            if(option != JOptionPane.NO_OPTION && 
                            option != JOptionPane.CANCEL_OPTION && 
                            option != JOptionPane.CLOSED_OPTION){
                                System.out.println("jawk behiii temchi aaml modifier tw ");
                            rr.modifierNote(e.getId(),id,newT.doubleValue());  

                            }
                        //ratingcours.setRating(rr.notefinal(e));


                }
                
                } catch (IOException ex) {
                    Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);
                }
                  //rr.notefinal(e, newT.doubleValue(), id);
                 //   ratingcours.setDisable(true);

           
            
            
            }
            
        });


        }

         courscontainer.getChildren().addAll(list);
     }
    
    
}
