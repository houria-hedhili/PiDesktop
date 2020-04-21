/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.houria;

import ConnexionBd.connexionBd;
import Entity.houria.Cours;
import Entity.houria.Ratting;
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
public class RatingCRUD {
    
      connexionBd connection = null;
        private Connection cnx;

    public RatingCRUD() {
        cnx= connexionBd.getInstance().getCnx();
    }
        public int test(Cours c,int b) throws SQLException{
        List<Integer> l=new ArrayList<>();
            String req ="select * from rating WHERE idCours ='"+c.getId()+"' AND id_user = '"+b+"%' ";
            
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
        
        public void addRating(Cours cours,int b,Double a) throws IOException {
        try {

            String requete
                    = "INSERT INTO rating (idCours,id_user,note) VALUES (?,?,?)";
            PreparedStatement st = cnx.prepareStatement(requete);
   
        st.setInt(1,cours.getId());
        st.setInt(2, b);
        st.setDouble(3, a);
     
        
            st.executeUpdate();
            System.out.println("note ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }
        public void modifierNote(int a,int b,Double c){
             try {

                     
            String requete
                    ="UPDATE rating SET note = ?  WHERE idCours=? AND id_user=?";
                        PreparedStatement st = cnx.prepareStatement(requete);
             st.setDouble(1,c);
             st.setInt(2,a);
             st.setInt(3,b);

             st.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(RatingCRUD.class.getName()).log(Level.SEVERE, null, ex);
         }
            System.out.println("update valide");
        }
        public Double notefinal(Cours c) throws SQLException{
              double p = 0;
              int nb=0;
              double f=0;

           //  try {
         String req ="select * from rating WHERE idCours ='"+c.getId()+"'  ";
            
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
          while (rs.next()) {
                p=p+rs.getDouble("note");
                nb++;
                
            }
          f=p/nb ;
          return f;
          

        }

}
