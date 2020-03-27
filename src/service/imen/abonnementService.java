/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.imen;

import ConnexionBd.connexionBd;
import Entity.imen.abonnement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
 

/**
 *
 * @author Imen
 */
public class abonnementService {
      private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public abonnementService() {
                cnx = connexionBd.getInstance().getCnx();

    }
    
      public boolean ajouterAbon(abonnement p) {

        try {
            String req = "INSERT INTO abonnec (dated, datef,idParent,nbr_enfant,tarif,etat) VALUES "
                    + "('" + p.getDated() + "', '" + p.getDatef() +"', '" + p.getIdParent() + "', '"+p.getNbEnfant()+"', '"+ p.getTarif() +"', '"+p.getEtat()+ "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            return true;

        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        

    }
      
      
      
        public void  modifierAbon(Date f,double tarif,int id){
        
          try {
            String req = "update abonnec set datef=?, tarif=? where id = ?";

            pre = cnx.prepareStatement(req);

            pre.setDate(1,f);
            pre.setDouble(2,tarif);
                        pre.setInt(3,id);

            pre.executeUpdate();

            System.out.println("Update 2 Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        
    }
            public void  modifierAbonEtat(String etat,int id){
        
          try {
            String req = "update abonnec set etat = ? where id ='"+id+"'";

            pre = cnx.prepareStatement(req);

            pre.setString(1,etat);
           
            pre.executeUpdate();

            System.out.println("Update 2 Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        
    }
      public List<abonnement> afficherAll() {

        List<abonnement> listP = new ArrayList<>();

        try {

            String req = "SELECT * FROM abonnec";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                abonnement p = new abonnement();

                p.setId(res.getInt("id"));
                p.setDated(res.getDate("dated"));
                                p.setDatef(res.getDate("datef"));

                p.setEtat(res.getString("etat"));
p.setTarif(res.getDouble("tarif"));
p.setNbEnfant(res.getInt("nbr_enfant"));
System.out.println(res.getInt("nbr_enfant"));
                listP.add(p);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }

public String  getNomParent(int id)
{ 
    String x="";

        try{ 

            String req = "SELECT nom FROM fos_user where id ='"+id+"'";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
              x=res.getString(1);
       
               
            }
       

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;

}
public String  getPrenomParent(int id)
{ 
    String x="";

        try{ 

            String req = "SELECT prenom FROM fos_user where id ='"+id+"'";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
              x=res.getString(1);
       
               
            }
       

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;

}
public List<abonnement> afficherAllBack() {

        List<abonnement> listP = new ArrayList<>();

        try {

            
            
            
            String req = "SELECT e.nom,e.prenom,m.etat,m.dated,m.datef,m.tarif,m.nbr_enfant,m.id FROM abonnec m INNER JOIN fos_user e ON  e.id = m.idParent ";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
  
           PlatService c= new PlatService();              
abonnement m =new abonnement();

m.setNom(res.getString(1));
m.setPrenom(res.getString(2));
m.setEtat(res.getString(3));
m.setDated(res.getDate(4));
m.setDatef(res.getDate(5));
m.setTarif(res.getDouble(6));
m.setNbEnfant(res.getInt(7));
m.setId(res.getInt(8));

 
                listP.add(m);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
}