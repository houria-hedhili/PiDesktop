/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.gererParticipation;

import ConnexionBd.connexionBd;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ParticipantController implements Initializable {

    @FXML
    private BarChart<String, Integer> chart;
        connexionBd connection = null;
        private Connection cnx;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                     loadChart();    
    }    


    private void loadChart() {
       try {
            String query="select nom,nbpart FROM event ORDER BY   nbpart";
            XYChart.Series<String,Integer> series = new XYChart.Series<>();
                    cnx= connexionBd.getInstance().getCnx();
            ResultSet rss = null;
            try {
                rss = cnx.createStatement().executeQuery(query);
            } catch (SQLException ex) {
                Logger.getLogger(ParticipantController.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (rss.next())
            {
                try {
                    series.getData().add(new XYChart.Data<>(rss.getString(1), rss.getInt(2)));
                } catch (SQLException ex) {
                    Logger.getLogger(ParticipantController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            chart.getData().add(series);
        } catch (SQLException ex) {
            Logger.getLogger(ParticipantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
