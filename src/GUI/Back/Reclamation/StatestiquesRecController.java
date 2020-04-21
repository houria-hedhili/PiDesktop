/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Reclamation;

import ConnexionBd.connexionBd;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class StatestiquesRecController implements Initializable {

    
      connexionBd connection = null;
      private final Connection cnx= connexionBd.getInstance().getCnx();
    @FXML
    private PieChart pieChart;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
             //   String req="select r.*,c.nom from reclamation r INNER JOIN categorie_reclamation c on r.CategorieReclamation = c.ref ORDER BY etat ASC";    
       String req ="SELECT r.etat as etat ,COUNT(c.nom) as nom FROM reclamation r INNER join categorie_reclamation c where r.CategorieReclamation=c.ref GROUP BY etat ";
            PreparedStatement ste;
        try {
            ste = (PreparedStatement) cnx.prepareStatement(req);
              ResultSet rs = ste.executeQuery();
            while (rs.next()){
               //  pieChart.getData().add(new  PieChart.Data(rs.getString(1), rs.getInt(2)));
                 pieChart.getData().add(new  PieChart.Data(rs.getString(1), rs.getInt(2)));

            }
           
        } catch (SQLException ex) {
            Logger.getLogger(StatestiquesRecController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
     Stage stage1 = (Stage) retour.getScene().getWindow();
    stage1.close();  
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Back/Acceuilback/menuu.fxml"));     
        Scene scene = new Scene(root);   
        stage.setScene(scene);
        stage.show();
                   
    }
    
}
