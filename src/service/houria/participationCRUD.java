/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.houria;

import ConnexionBd.connexionBd;
import Entity.houria.Participation;
import Entity.user.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
        public boolean chercher_ajout_participation(Participation ta) throws SQLException {
                        String requete
                    ="select * from participation where idEvent= ? ";
                        PreparedStatement st = cnx.prepareStatement(requete);
             st.setInt(1,ta.getId_event());
            // st.setInt(2,ta.getId_user());
        List<Participation> list = new ArrayList<>();
       
        try {          
           ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while(rs.next()){
                 //java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
                // user a= new user(rs.getString(2));
              //  list.add(new Participation(a));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(participationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (        !list.isEmpty());
    }
    
}
