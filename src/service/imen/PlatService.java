/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.imen;

import ConnexionBd.connexionBd;
import Entity.imen.Plat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//c bon att naccepti w njarabha aandi byye bb
/**
 *
 * @author Imen
 */
public class PlatService {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public PlatService() {
        cnx = connexionBd.getInstance().getCnx();
    }

    public boolean ajouterPlat(Plat p) {

        try {
            String req = "INSERT INTO plat (nom, description,image,date,type) VALUES "
                    + "('" + p.getNom() + "', '" + p.getDescription() +"', '" + p.getImage() +"',NOW(), '" + p.getType() + "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            return true;

        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }

    }
/*
    public void ajouuterPersonne1(Plat p) {

        try {
            String req = "INSERT INTO user (nom, prenom) VALUES (?, ?)";

            pre = cnx.prepareStatement(req);

            pre.setString(1, p.getNom());
            pre.setString(2, p.getPrenom());

            pre.executeUpdate();

            System.out.println("Insertion 2 Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
*/
    public List<Plat> afficherAll() {

        List<Plat> listP = new ArrayList<>();

        try {

            String req = "SELECT * FROM plat";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                Plat p = new Plat();

                p.setId(res.getInt("id"));
                p.setNom(res.getString(2));
                p.setDescription(res.getString("description"));
 p.setImage(res.getString("image"));
  p.setDate(res.getDate("date"));
   p.setType(res.getString("type"));
   ImageView v=new ImageView();
                   v.setImage(new Image(res.getString("image")));
                   v.setFitHeight(100);
                   v.setFitWidth(100);
                p.setPhoto(v);
                listP.add(p);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
    public List<String> afficherPlat()
    {  List<String> listP = new ArrayList<>();

        try {

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
    public List<Plat> PlatUnique(String nom)
    {  List<Plat> listP = new ArrayList<>();

        try {

            String req = "SELECT id,nom,description,image,type FROM plat where nom = '"+nom+"'";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
      
  while (res.next()) {
              
          Plat p = new Plat();
                p.setId(res.getInt(1));

                p.setNom(res.getString(2));
                p.setDescription(res.getString(3));
 p.setImage(res.getString(4));
 p.setType(res.getString(5));

           
            
                listP.add(p);

            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    
    }
    public void  modifierPlat(String nom,String type,String desc, String img,int id){
        
          try {
            String req = "update plat set nom=?, description=?, image=?,date=NOW(),type=? where id = ?";

            pre = cnx.prepareStatement(req);

            pre.setString(1,nom);
            pre.setString(2,desc);
            pre.setString(3,img);
            pre.setInt(5,id);
              pre.setString(4,type);

            pre.executeUpdate();

            System.out.println("Update 2 Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        
    }
    
    public void deletePlat(int id) {
      
          try {
            String req = "delete from plat where id = ?";

            pre = cnx.prepareStatement(req);

          
            pre.setInt(1, id);

            pre.executeUpdate();

            System.out.println("supprimer 2 Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

      }
}
