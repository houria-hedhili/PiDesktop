/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.houria;

import ConnexionBd.connexionBd;
import Entity.houria.Evenement;
import Entity.houria.Participation;
import Entity.user.user;
import GUI.Front.gererEvent.EventController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author LENOVO
 */
public class participationCRUD {

  
   connexionBd connection = null;
        private Connection cnx;

    public participationCRUD() {
        cnx= connexionBd.getInstance().getCnx();
    }
        private Statement ste;
    private PreparedStatement pre;
    
        public boolean chercherparticipation(Participation ta) throws SQLException {
                        String requete
                    ="select * from participation where idEvent= '"+ta.getId_event()+ "' AND idUser ='"+ta.getId_user()+ "'";
                        PreparedStatement st = cnx.prepareStatement(requete);

        List<Participation> list = new ArrayList<>();
       
        try {          
           ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while(rs.next()){
                 //java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
                // user a= new user(rs.getString(2));
                list.add(new Participation(rs.getInt(2),rs.getInt(3)));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(participationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (        !list.isEmpty());
    }
        
    public void insert(Participation e) {
        String req="INSERT INTO `participation` (`IdUser`, `IdEvent`) VALUES ('"+e.getId_user()+"','"+e.getId_event()+"');";
        try {
            
            ste=cnx.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public void deleteparticipation(int b,int a) throws SQLException{
            String req = "DELETE FROM participation WHERE idEvent='" + b + "' AND idUser ='"+a+ "'";
        PreparedStatement ste = cnx.prepareStatement(req);
        ste.executeUpdate();
}
    


    
}
