/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wifek;

import ConnexionBd.connexionBd;
import Entity.wifek.Bus;
import Entity.wifek.enfant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class CrudBusService {
    
private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
     public CrudBusService() {
        cnx = connexionBd.getInstance().getCnx();
    }
     
     public void ajouterBus(Bus b){
             try {
            String req = "INSERT INTO bus (matricule, nbPlaces,ligne) VALUES "
                    + "('" + b.getMatricule() + "', '" + b.getNbPlaces()+ "','" + b.getLigne()+ "')";
            
            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("un nouveau Bus est ajouté!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
     }
     
     public void modifierBus(String matricule,int nbplace,String ligne, int id){
      
        try {
        
            PreparedStatement pt=cnx.prepareStatement("Update bus set matricule= ?, nbPlaces=?, ligne=?  where id=? ");
            pt.setString(1,matricule);
            pt.setInt(2, nbplace);
            pt.setString(3,ligne);
            pt.setInt(4, id);
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudBusService.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
     
     
      public  void supprimerBus(int id){
        
        try {
            PreparedStatement pt=cnx.prepareStatement("Delete from bus where id=? ");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudBusService.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
      
      public ArrayList<Bus> afficherBus(){
         ArrayList<Bus> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="Select * from bus";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){
                    Bus p = new Bus(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
                    list.add(p);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(CrudBusService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;  
       }
           public  ArrayList<enfant>  getLigneBus(int id){
         ArrayList<enfant> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="Select s.id,b.ligne,s.sexe, s.nom,s.prenom,s.age from bus b INNER JOIN enfant s on b.id=s.id_bus where s.idParent='"+id+"'";//stana nkhamemb sayeeleeb na3rafha la requete ena manich bich nnselecti ken ligne bich naffichi tbaleau keml ok
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){//hezni lil
                   //stana nkhamem
                  enfant e= new enfant(rs.getInt(1),rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(2)); //yakhra matmesesch
                    list.add(e);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(CrudBusService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;   
       }
      public ArrayList<String> getAllLigne()
      {
       ArrayList<String> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="Select ligne from bus";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){
                  //  Bus p = new Bus(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
                    list.add(rs.getString(1));            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(CrudBusService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;  
      }
      public int getIdLigne(String ligne)
      {   int x=0;
             try {
                Statement st=cnx.createStatement();
                String req="Select id from bus where ligne = '"+ligne+"'";
                ResultSet rs = st.executeQuery(req);
             while(rs.next())
                x= rs.getInt(1);
                } catch (SQLException ex) {
                    Logger.getLogger(CrudBusService.class.getName()).log(Level.SEVERE, null, ex);
                }
     
      return x;
      
      }
      

      public ObservableList<Bus> rechercheBus(String recherche) throws SQLException {
        ObservableList<Bus> list = FXCollections.observableArrayList();
        String requete = "Select matricule,nbPlaces,ligne from bus WHERE `ligne` LIKE '%"+recherche+"%' OR `nbPlaces` LIKE '%"+recherche+"%' OR `matricule` LIKE '%"+recherche+"%'  ";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
    Bus p = new Bus(
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getString(3));

            list.add(p);          
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }


}
