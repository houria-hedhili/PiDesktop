/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wifek;

import ConnexionBd.connexionBd;
import Entity.wifek.Bus;
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
      
      
        public  ArrayList<Bus> rechercheBus(String motClef){
     ArrayList<Bus> list =new ArrayList<>();

         try {
            
            Statement st=cnx.createStatement();
            String req="Select * from bus WHERE `ligne` LIKE '%"+motClef+"%'  ";
            ResultSet rs = st.executeQuery(req);
             int i = 0;
            while(rs.next()){
                   i++;
            Bus p = new Bus(rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4));

            list.add(p);            
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudBusService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
   }
      


}
