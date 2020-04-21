/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.houria;

import ConnexionBd.connexionBd;
import Entity.houria.Evenement;
import java.io.IOException;
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
public class AimeeventCRUD {
      connexionBd connection = null;
        private Connection cnx;
    public AimeeventCRUD(){
            cnx= connexionBd.getInstance().getCnx();

     }
    public int exist(int a,int b) throws SQLException{
         List<Integer> l=new ArrayList<>();
            String req ="select * from aimeevent WHERE idEvent ='"+a+"' AND user_id = '"+b+"%' ";
            
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
              System.out.println(rs.getInt("id"));
              l.add(rs.getInt("id"));

          }
          if(l.isEmpty()){
              return -1;
              
          }else{

              return 1;
          }

         }
public void updateaime(Evenement e,int b,int a){
      try {

                     
            String requete
                    ="UPDATE aimeevent SET aime = ?  WHERE idEvent=? AND user_id=?";
                        PreparedStatement st = cnx.prepareStatement(requete);
              st.setInt(1,a);

             st.setInt(2,e.getIdEvent());
             st.setInt(3,b);

             st.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(AimeeventCRUD.class.getName()).log(Level.SEVERE, null, ex);
         }
            System.out.println("update valide");
}
    
 public int getAime(Evenement event,int b) throws IOException, SQLException {
        int k = -1;
         String req ="select aime from aimeevent WHERE idEvent ='"+ event.getIdEvent() +"' AND user_id = '"+b+"%' ";
            
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
              k=rs.getInt("aime");
          }
        return k;
    }
 
 public void addAime(Evenement event,int b) throws IOException {
        try {

            String requete
                    = "INSERT INTO aimeevent (idEvent,user_id,aime) VALUES (?,?,?)";
            PreparedStatement st = cnx.prepareStatement(requete);
   
        st.setInt(1,event.getIdEvent());
        st.setInt(2, b);
        st.setInt(3, 1);
     
        
            st.executeUpdate();
            System.out.println("j aime ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }
    
}
