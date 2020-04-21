/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererEnfant;

import ConnexionBd.connexionBd;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLStatController implements Initializable {
    @FXML
    private PieChart piechart;
  connexionBd connection = null;
        private final Connection cnx= connexionBd.getInstance().getCnx();

    
        
    

    
    /**
     * Initializes the controller class.
     */

    public void initialize(URL url, ResourceBundle rb) {
      String req ="SELECT UPPER(e.ligne) as trajet ,COUNT(p.id) as num FROM enfant p INNER join Bus e where e.id=p.id_Bus GROUP BY p.id_Bus ";
//       SELECT UPPER(e.ligne) as trajet ,COUNT(p.id) as num FROM enfant p 
//join Bus e with e.id=p.idBus GROUP BY p.idBus

            PreparedStatement ste;
        try {
            ste = (PreparedStatement) cnx.prepareStatement(req);
              ResultSet rs = ste.executeQuery();
            while (rs.next()){
                 piechart.getData().add(new  PieChart.Data(rs.getString(1), rs.getInt(2)));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(FXMLStatController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
     
    }
   }   
    
