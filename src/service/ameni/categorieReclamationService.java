/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.ameni;

import ConnexionBd.connexionBd;
import Entity.ameni.categorieReclamation;
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
 * @author User
 */
public class categorieReclamationService {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
     public categorieReclamationService() {
        cnx = connexionBd.getInstance().getCnx();
    }
     
           public ArrayList<categorieReclamation> affcatRec(){
         ArrayList<categorieReclamation> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="select * from categorie_reclamation";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){
                    categorieReclamation p = new categorieReclamation(rs.getString(1),rs.getString(2),rs.getInt(3));
                    list.add(p);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(categorieReclamationService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;   
       }
           
             public List afficherCategorieReclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
             
             
              public void ajouterCategorieReclamation(categorieReclamation b){
             try {
            String req = "INSERT INTO categorie_reclamation (nom, description) VALUES "
                    + "('" + b.getNom() + "', '" + b.getDescription()+ "')";
            
            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("une nouvelle Categorie est ajoutée!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
     }
              
           public void modifierCategorieReclamation(String nom,String description, int ref){
        try {       
            PreparedStatement pt=cnx.prepareStatement("Update categorie_reclamation set nom= ?,  description=?  where ref=? ");
            pt.setString(1,nom);
            pt.setString(2,description);
            pt.setInt(3, ref);          
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(categorieReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
           
        public  void supprimerCat(int ref){
        try {
            PreparedStatement pt=cnx.prepareStatement("Delete from categorie_reclamation where ref=? ");
            pt.setInt(1, ref);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(categorieReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
        
                  /* public ArrayList<categorieReclamation> chercherCat(){
         ArrayList<categorieReclamation> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="select * from categorie_reclamation where nom = ?";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){
                    categorieReclamation p = new categorieReclamation(rs.getString(1),rs.getString(2),rs.getInt(3));
                    list.add(p);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(categorieReclamationService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;   
       }*/
}
