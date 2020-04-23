/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererCantine;

import ConnexionBd.connexionBd;
import Entity.imen.Menu;
import Entity.imen.Plat;
import GUI.Back.gererEnfant.FXMLStatController;
import GUI.Front.gererCantine.abonnement.AbonCantineController;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.CategorizedFacebookType;
import com.restfb.types.Comment;
import com.restfb.types.Post;
import static java.awt.SystemColor.menu;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.imen.PlatService;
import service.imen.menuService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AvanceController implements Initializable {
    @FXML
    private TableView<Plat> tabPost;
    @FXML
    private TableColumn<Plat, String> image;
    @FXML
    private TableColumn<Plat, String> post;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox comment;
PlatService ps =new PlatService();
  String accessToken ="EAAHZCsGQJeY4BAIMcnqpufryoZC2dO9ZA0uLwF8Wm73P3TxPhWy3XMLTKDLOGex37CNpYhqa4ZAlIAccKkdtiP9VFKUM0wSYrxrcpeStC6VH2oW1ie1xeWk5MKx3YNsMs7icKHnIrmGp7Wa9ZBeAZCWaOgX7iNk16sr7ylR5tjZCKiZC4qC1s0656Y2NnUPpYAenfVbZA8Oel7gZDZD";
  FacebookClient fbClient= new DefaultFacebookClient(accessToken,Version.LATEST);
    @FXML
    private HBox stat;
    @FXML
    private BarChart<?, ?> PostChart;
    @FXML
    private NumberAxis axeY;
    @FXML
    private CategoryAxis axeX;
    @FXML
    private BarChart<?, ?> chartAime;
menuService ms= new menuService();
    @FXML
    private BarChart<?, ?> chartPlat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 afficherPost();
List<String> xi=ps.getidPost();
for(int i=0;i<xi.size();i++)
{
Post post = fbClient.fetchObject(xi.get(i),
  Post.class,
  Parameter.with("fields", "from,to,likes.limit(0).summary(true),comments.limit(0).summary(true),shares.limit(0).summary(true)"));

ps.updateLikes(xi.get(i),post.getLikesCount().intValue());
statistique();
statistique2();
statistique3();

}

    }    

    
    public void afficherPost()
    {
    List<Plat> listePlat=ps.afficherPost();
   
    ObservableList<Plat> platListe=FXCollections.observableArrayList(listePlat);
        image.setCellValueFactory(new PropertyValueFactory<>("photo"));
    post.setCellValueFactory(new PropertyValueFactory<>("description"));
      tabPost.setItems(platListe); 
    
    }
    int j=0;
    public void afficherdetailPost(String idPost)
    {String ch="";
    List<String> listeComment=new ArrayList<>();
    Connection<Comment> commentConnection = fbClient.fetchConnection(idPost+ "/comments", 
         Comment.class, Parameter.with("limit", 10000));


for (List<Comment> commentPage : commentConnection) {
  for (Comment comment : commentPage) {
   ch=comment.getMessage().toString();

  
listeComment.add(ch);
    // break both loops
  
    
    
  }}
    
    List<VBox> list = new ArrayList<>();
         Post post = fbClient.fetchObject(idPost,
  Post.class,
  Parameter.with("fields", "from,to,likes.limit(0).summary(true),comments.limit(0).summary(true),shares.limit(0).summary(true)"));
          for(j=0;j<listeComment.size();j++){
          
            TextArea va=new TextArea();
            va.setDisable(false);
va.setEditable(false);

            va.setText(listeComment.get(j));
   HBox h= new HBox();
               h.setSpacing(5);
                  HBox h1= new HBox();
               h1.setSpacing(10);
               VBox v1=new VBox();
               v1.setAlignment(Pos.CENTER);
               
                              v1.setSpacing(5);


               v1.getChildren().addAll(va,h);
               
            
               
               list.add(v1);

              //v1.getChildren().clear();

          }
           
  ImageView aime=new ImageView(new Image(this.getClass().getResourceAsStream("aime.png")));
  aime.setFitHeight(50);
  aime.setFitWidth(50);
  Label aimes=new Label(post.getLikesCount().toString());
ImageView comments =new ImageView(new Image(this.getClass().getResourceAsStream("comment.png")));
comments.setFitHeight(50);
comments.setFitWidth(50);
Label commentss=new Label(post.getCommentsCount().toString());
ImageView share=new ImageView(new Image(this.getClass().getResourceAsStream("share.png")));
share.setFitHeight(50);
share.setFitWidth(50);
Label shares=new Label(post.getSharesCount().toString());
HBox contain=new HBox();
contain.getChildren().addAll(aimes,aime);

contain.setSpacing(10);
contain.setAlignment(Pos.CENTER);
HBox contain1=new HBox();
contain1.getChildren().addAll(commentss,comments);
contain1.setSpacing(10);
contain1.setAlignment(Pos.CENTER);
HBox contain2=new HBox();
contain2.getChildren().addAll(shares,share);
contain2.setSpacing(10);
contain2.setAlignment(Pos.CENTER);
HBox containAll =new HBox();
containAll.getChildren().addAll(contain,contain1,contain2);
containAll.setAlignment(Pos.CENTER);
        stat.getChildren().addAll(containAll);
        stat.setAlignment(Pos.CENTER);
                            comment.getChildren().clear();

         comment.getChildren().addAll(list);

        
    }
    
    public List<String> afficheCommentaire(String postId)
    {String ch="";
    List<String> listeComment=new ArrayList<>();
    Connection<Comment> commentConnection = fbClient.fetchConnection(postId+ "/comments", 
         Comment.class, Parameter.with("limit", 10000));


for (List<Comment> commentPage : commentConnection) {
  for (Comment comment : commentPage) {
   ch=comment.getMessage().toString();

  
listeComment.add(ch);
    // break both loops
  
    
    
  }
 
    }
return listeComment;
    }

    @FXML
    private void afficherComment(MouseEvent event) {

        stat.getChildren().clear();
         Plat a = tabPost.getSelectionModel().getSelectedItem();
        System.out.println(a.getIdPost());
afficherdetailPost(a.getIdPost());
    
    }
    
    private void statistique()
    {XYChart.Series set=new XYChart.Series<>();
   List<Plat> LPlat = ps.afficherAll();
   for(int i=0;i<LPlat.size();i++)
   {
   set.getData().add(new XYChart.Data(LPlat.get(i).getNom(),LPlat.get(i).getNbrAime()));
   }
      PostChart.getData().addAll(set);

    
    }  
    
    public void statistique2()
    {
   /* XYChart.Series set=new XYChart.Series<>();
   List<Plat> LPlat = ps.afficherAll();
   for(int i=0;i<LPlat.size();i++)
   {
   set.getData().add(new XYChart.Data(LPlat.get(i).getNom(),ps.nbrJaimeParPlat(LPlat.get(i).getId())));
   }
      chartPlat.getData().addAll(set);*/
    
    }
    public void statistique3()
    {
    XYChart.Series set=new XYChart.Series<>();
   List<Plat> LPlat = ps.afficherAll();
   for(int i=0;i<LPlat.size();i++)
   {
   set.getData().add(new XYChart.Data(ms.getNomPlat(LPlat.get(i).getId()),ms.getNbrPlatMenu(LPlat.get(i).getId())));
   }
      chartPlat.getData().addAll(set);
    
    }
    
}
