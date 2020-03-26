/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.imen;

import ConnexionBd.connexionBd;
import Entity.imen.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Imen
 */
public class menuService {
     private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public menuService() {
         cnx = connexionBd.getInstance().getCnx();
    }
    
    
       public boolean ajouterMenu(Menu m) {

        try {
            String req = "INSERT INTO menu (idPlat, idDessert,idParent,date,idEnfant) VALUES "
                    + "('" + m.getIdPlat() + "', '" + m.getIdDessert() +"', '" + m.getIdParent() +"',NOW(), '" + m.getIdenfant() + "')";

            st = cnx.createStatement();

            st.executeUpdate(req);
System.out.println("ajout sar");
            return true;

        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }

    }
       
       
       public List<Menu> afficherAll(int idParent) {

        List<Menu> listP = new ArrayList<>();

        try {

            String req = "SELECT m.id,e.nom,e.prenom,m.idPlat,m.idDessert FROM menu m INNER JOIN enfant e ON  m.idenfant = e.id   where m.idParent ='"+idParent+"'";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
  
           PlatService c= new PlatService();              
Menu m =new Menu();
m.setId(res.getInt(1));
m.setNom(res.getString(2));
m.setPrenom(res.getString(3));
m.setIdDessert(res.getInt(5));
m.setIdPlat(res.getInt(4));
m.setDessert(c.getNomPlat1(res.getInt(5)));
m.setPlat(c.getNomPlat1(res.getInt(4)));
        

 
                listP.add(m);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
    public List<String> afficherPlat()
    {  List<String> listP = new ArrayList<>();

        try{ 

            String req = "SELECT nom FROM plat";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
              
       
                listP.add(res.getString(1));
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    
    }
       
       public List<String> listeEnfant(int id)
       {List<String> listP = new ArrayList<>();

        try{ 

            String req = "SELECT nom FROM enfant";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
              
       
                listP.add(res.getString(1));
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
       }
         public int enfant(int id ,String nom)
       {int x=0;

        try{ 

            String req = "SELECT id FROM enfant where idParent ='"+id+"' AND nom ='"+nom+"'";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
              x=res.getInt(1);
       
               
            }
       

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return x;
       }
    
    public void deleteMenu(int id) {
      
          try {
            String req = "delete from menu where id = ?";

            pre = cnx.prepareStatement(req);

          
            pre.setInt(1, id);

            pre.executeUpdate();

            System.out.println("supprimer 2 Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

      }
        public void  modifierPlat(int idDessert,int idPlat,int id){
        
          try {
            String req = "update menu set idPlat = ?, idDessert = ? where id = ?";

            pre = cnx.prepareStatement(req);

                  pre.setInt(1,idPlat);
            pre.setInt(2,idDessert);

            pre.setInt(3,id);
             

            pre.executeUpdate();

            System.out.println("Update 2 Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        
    }

    
}
