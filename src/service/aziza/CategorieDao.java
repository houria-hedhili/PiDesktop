/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.aziza;


import Entity.aziza.Categorie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import ConnexionBd.connexionBd;
import Entity.aziza.Personnel;
import Entity.houria.Evenement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import service.wifek.CrudBusService;
/**
 *
 * @author GLOBALINFO
 */
public  class CategorieDao implements Idao<Categorie> {
    private static CategorieDao instance;
    private Statement st;
    private ResultSet rs;
    connexionBd connection = null;
        private Connection cnx;
    
    public CategorieDao() {
        cnx= connexionBd.getInstance().getCnx();
    }
    
    public static CategorieDao getInstance(){
        if(instance==null) 
            instance=new CategorieDao();
        return instance;
    }

    
   
    public void insert(Categorie o)   {
          try {
        String requete
                    = "INSERT INTO categorie (type,description) VALUES (?,?)";
            PreparedStatement st = cnx.prepareStatement(requete);
        st.setString(1, o.getType());
        st.setString(2, o.getDescription());
        
            st.executeUpdate();
            System.out.println("categorie ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }
   

   
    public ObservableList<Categorie> displayAll(){ 
        ObservableList<Categorie> myList = FXCollections.observableArrayList();
    
        try {
            String req = "SELECT * FROM categorie";
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Categorie p = new Categorie();
                p.setId(rs.getInt("id"));
                p.setType(rs.getString("type"));
                p.setDescription(rs.getString("description"));
               myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }
        

    
    
   public Categorie displaycategorieByID(int id) {
        Categorie p = new Categorie();
        try {
            String req = "SELECT * FROM event where id='" + id + "'";
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {

                p.setId(rs.getInt("id"));
                p.setType(rs.getString("type"));
                p.setDescription(rs.getString("description"));
               

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return p; // jwna bhy aya by esnaa bich tcomitii wnhabtouh oprojet nekhdmou 3lih wala le ? eyh akiddd nkmll n // o93ed maaya nkaml nhot tak tak el matiere w nhabtouh mabaadhna okk
    }

    
    public boolean update(Categorie p) {
        String qry = "UPDATE categorie SET type = '"+p.getType()+"',description = '"+p.getDescription()+"' WHERE id = "+p.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

   
    
    private Categorie displayById(SimpleIntegerProperty id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    public void deleteCat(int id) throws SQLException {
       String req = "DELETE FROM categorie WHERE id='" + id + "'";
        PreparedStatement ste = cnx.prepareStatement(req);
        ste.executeUpdate();
    }

   // public void deleteById(TextArea type) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    @Override
    public void delete(Categorie o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categorie displayById(String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

       public  ArrayList<Personnel>  getCategorie(){
         ArrayList<Personnel> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="Select s.id,s.nom,s.prenom, s.age,s.prix_h,s.nb_h,b.type ,s.image from categorie b INNER JOIN personnel s on b.id=s.id_cat";//stana nkhamemb sayeeleeb na3rafha la requete ena manich bich nnselecti ken ligne bich naffichi tbaleau keml ok
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){
                  Personnel e= new Personnel(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getFloat(6),rs.getString(7)); //yakhra matmesesch
                    list.add(e);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(CategorieDao.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;   
       }
    public int getIdCategorie(String type)
      {   int x=0;
             try {
                Statement st=cnx.createStatement();
                String req="Select id from categorie where type = '"+type+"'";
                ResultSet rs = st.executeQuery(req);
             while(rs.next())
                x= rs.getInt(1);
                } catch (SQLException ex) {
                    Logger.getLogger(CategorieDao.class.getName()).log(Level.SEVERE, null, ex);
                }
     
      return x;
      
      }
      

}
