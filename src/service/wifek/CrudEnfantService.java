/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wifek;

import ConnexionBd.connexionBd;
import Entity.wifek.enfant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class CrudEnfantService {

            connexionBd connection = null;
        private Connection cnx;

    public CrudEnfantService() {
        cnx= connexionBd.getInstance().getCnx();
    }

    

          public void ajouterEnfant(enfant e){
             try {
            String req = "INSERT INTO enfant (sexe, nom, prenom, age, id_Bus, idParent) VALUES "
                    + "('" + e.getSexe()+ "','" + e.getNom() + "', '" + e.getPrenom()+ "','" + e.getAge()+ "','" + e.getId_Bus()+ "','" + e.getIdParent()+ "')";
            
                 Statement st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("un nouveau Enfant est ajouté!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
     }
          
        public  void supprimerEnfant(int id){
        try {
            PreparedStatement pt=cnx.prepareStatement("Delete from enfant where id=? ");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudEnfantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
        
        public ArrayList<enfant> afficherEnfant(){
         ArrayList<enfant> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="Select E.sexe,E.nom,E.prenom,E.age,E.id_Bus from enfant e inner join fos_user p on e.idParent=p.id where E.idParent=P.id";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){
                    enfant p = new enfant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
                    list.add(p);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(CrudBusService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;   
       }
                public ArrayList<enfant> afficherEnfant1(){
         ArrayList<enfant> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="Select e.id, e.sexe, e.nom, e.prenom, e.age, b.ligne, p.username from enfant e  inner join Bus b on b.id=e.id_Bus inner join fos_user p on e.idParent = p.id ";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){//
                    enfant p = new enfant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7));
                    list.add(p);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(CrudBusService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;   
       }
         public ArrayList<enfant> afficherEnfant2(int id){
         ArrayList<enfant> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="Select e.id, e.sexe, e.nom, e.prenom, e.age, b.ligne, p.username from enfant e  inner join Bus b on b.id=e.id_Bus inner join fos_user p on e.idParent = p.id where idParent = '"+id+"'";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){//
                    enfant p = new enfant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7));
                    list.add(p);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(CrudBusService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;   
       }
        
             
     public void modifierEnfant(String sexe,String nom,String prenom,int age,int id_Bus,int id){
      
        try {
        
            PreparedStatement pt=cnx.prepareStatement("Update enfant set sexe= ?, nom=?, prenom=?, age=?, id_Bus=? where id=? ");
            pt.setString(1,sexe);
            pt.setString(2, nom);
            pt.setString(3,prenom);
            pt.setInt(4, age);
            pt.setInt(5, id_Bus);
            pt.setInt(6, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudEnfantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
     
        public ObservableList<enfant> Affichertoutpdf() throws SQLException {
        ObservableList<enfant> list = FXCollections.observableArrayList();
        String requete = "Select e.sexe, e.nom, e.prenom, e.age, b.ligne, p.username from enfant e  inner join Bus b on b.id=e.id_Bus inner join fos_user p on e.idParent = p.id ";
       // (String sexe, String nom, String prenom, int age, String nomLigne, int idParent)
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new enfant(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
