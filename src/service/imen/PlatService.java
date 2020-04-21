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
            String req = "INSERT INTO plat (nom, description,image,date,type,id_post,img) VALUES "
                    + "('" + p.getNom() + "', '" + p.getDescription() +"', '" + p.getImage() +"',CAST(NOW() AS DATE) , '" + p.getType() +"','"+p.getIdPost()+"','"+p.getImg()+ "')";

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
   p.setIdPost(res.getString("id_post"));
   p.setNbrAime(res.getInt("nbr_aime"));
   p.setNbrVue(res.getInt("nbr_vue"));
   p.setImg(res.getString("img"));
   ImageView v=new ImageView();
                   v.setImage(new Image("file:/C:/wamp/www/jardin1/web/"+res.getString("img")));
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
        public Plat PlatId(int id)
    {  
              Plat p = new Plat();
    
    try {

            String req = "SELECT id,nom,description,image,type FROM plat where id = '"+id+"'";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
      
  while (res.next()) {
              
                p.setId(res.getInt(1));

                p.setNom(res.getString(2));
                p.setDescription(res.getString(3));
 p.setImage(res.getString(4));
 p.setType(res.getString(5));

           
            

            }
            
           

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return p;
    
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
    public void  modifierPlat(String nom,String type,String desc, String img,int id,String idPost){
        
          try {
            String req = "update plat set nom=?, description=?, image=?,date=NOW(),type=?,id_post=? where id = ?";

            pre = cnx.prepareStatement(req);

            pre.setString(1,nom);
            pre.setString(2,desc);
            pre.setString(3,img);
            pre.setInt(6,id);
              pre.setString(4,type);
              pre.setString(5,idPost);

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
    /*****tebe3 menu*////////
    public String getTypePlat(int id) {
String s="";
        try {

            String req = "SELECT type FROM plat where id = '"+id+"'";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
      
  while (res.next()) {
              
        s=res.getString(1);

               

            }
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return s;

    }
    
      public List<String> getListeEnfant(int id) {

  List<String> listE = new ArrayList<>();
String s="";
        try {

            String req = "SELECT nom FROM enfant where idParent = '"+id+"'";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
      
  while (res.next()) {
              
        s=res.getString(1);
listE.add(s);
               

            }
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listE;

    }
          public List<String> getNomPlat(int id) {

  List<String> listE = new ArrayList<>();
String s="";
        try {

            String req = "SELECT nom FROM plat where id = '"+id+"'";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
      
  while (res.next()) {
              
        s=res.getString(1);
listE.add(s);
               

            }
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listE;

    }
                   public String getNomPlat1(int id) {

String s="";
        try {

            String req = "SELECT nom FROM plat where id = '"+id+"'";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
      
  while (res.next()) {
              
        s=res.getString(1);

               

            }
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return s;

    }
                   
                          public List<String> platType(String type) {

  List<String> listE = new ArrayList<>();
String s="";
        try {

            String req = "SELECT nom FROM plat where type = '"+type+"'";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
      
  while (res.next()) {
              
        s=res.getString(1);
listE.add(s);}
               

            }  catch (SQLException ex) {
            System.out.println(ex);
        }

        return listE;}
    
    public int PlatUnique2(String nom)
    {  
int x=0;
        try {

            String req = "SELECT id FROM plat where nom = '"+nom+"'";

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
    
    public List<Plat> afficherPlatSelonType(String type)        
    {
       List<Plat> listP = new ArrayList<>();

        try {

            String req = "SELECT * FROM plat where type ='"+type+"'";

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
                   v.setImage(new Image("file:/C:/wamp/www/jardin1/web/"+res.getString("img")));
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
    
    public List<Plat> afficherPost()
    { List<Plat> listP = new ArrayList<>();

        try {

            String req = "SELECT id,id_post,description,image FROM plat where date = CAST(NOW() AS DATE) ";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                Plat p = new Plat();

                p.setId(res.getInt("id"));
                p.setIdPost(res.getString(2));
                p.setDescription(res.getString("description"));
 p.setImage(res.getString("image"));

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
    public void updateLikes(String idPost,int nbrAime)
    {
    try {
            String req = "update plat set nbr_aime=? where id_post = ?";

            pre = cnx.prepareStatement(req);

            pre.setInt(1,nbrAime);
            pre.setString(2,idPost);
           
            pre.executeUpdate();

            System.out.println("Update 2 Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    
     public void updateLikes(int id,int nbrAime)
    {
    try {
            String req = "update plat set nbr_aime=? where id = ?";

            pre = cnx.prepareStatement(req);

            pre.setInt(1,nbrAime);
            pre.setInt(2,id);
           
            pre.executeUpdate();

            System.out.println("Update 2 Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
        public void updateVue(int id,int valeur)
    {
    try {
            String req = "update plat set nbr_vue=? where id = ?";

            pre = cnx.prepareStatement(req);

            pre.setInt(1,valeur);
            pre.setInt(2,id);
           
            pre.executeUpdate();

            System.out.println("Update 2 Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
     public void LikePost(int id,int idPlat)
     {
     try {
            String req = "INSERT INTO listeplat (idUser,idPlat) VALUES "
                    + "('" + id +"', '" + idPlat +"')";

            st = cnx.createStatement();

            st.executeUpdate(req);


        } catch (SQLException ex) {
            System.out.println(ex);
        }

     
     }
     public void nbrVue(int id,int idPlat)
     { try {
            String req = "INSERT INTO liste_vue (idUser,idPlat) VALUES "
                    + "('" + id +"', '" + idPlat +"')";

            st = cnx.createStatement();

            st.executeUpdate(req);


        } catch (SQLException ex) {
            System.out.println(ex);
        }

     
     
     }
     public boolean rechercheVue(int id,int idPlat)
     {int x=0;
     boolean test=false;
     try {

            String req = "SELECT idUser FROM liste_vue where idUser ='"+id+"'and idPlat ='"+idPlat+"'";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
      
  while (res.next()) {
              
       x=res.getInt(1);
      if(x==0)
          test=false;
      else test=true;

            }
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }
return test;
     
     }
     public int getNbrVue(int id)
     {int x=0;
   
     try {

            String req = "SELECT Count(*) FROM liste_vue where idPlat ="+id;

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
     public boolean rechercheAime(int id,int idPlat)
     {int x=0;
     boolean test=false;
     try {

            String req = "SELECT idUser FROM listeplat where idUser ='"+id+"' and idPlat ='"+idPlat+"'";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
      
  while (res.next()) {
              
       x=res.getInt(1);
      if(x==0)
          test=false;
      else test=true;

            }
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }
return test;
     
     }
    
    
    public List<String> getidPost()
    {
    String x="";
    List<String> y= new ArrayList<>();   
    try {

            String req = "SELECT id_post FROM plat ";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
      
  while (res.next()) {
              
       x=res.getString(1);
       y.add(x);

            }
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }
return y;
    
    }
    public void deleteLike(int idPlat ,int id)
    {
          try {
            String req = "delete from listeplat where idPlat = ? and idUser =?";

            pre = cnx.prepareStatement(req);

          
            pre.setInt(1, idPlat);
            pre.setInt(2, id);

            pre.executeUpdate();

            System.out.println("supprimer 2 Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    
    public int nbrJaimeParPlat(int idPlat)
    {
    int x=0;
    List<String> y= new ArrayList<>();   
    try {

            String req = "SELECT COUNT(*) FROM listeplat where idPlat = "+idPlat;

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
    public int nbrConsommation(int idPlat)
    {
    int x=0;
    List<String> y= new ArrayList<>();   
    try {

            String req = "SELECT COUNT(*) FROM menu where idPlat = "+idPlat;

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
    
   
}
