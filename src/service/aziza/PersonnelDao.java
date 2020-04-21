/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.aziza;

import ConnexionBd.connexionBd;
import Entity.aziza.Personnel;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *
 * @author GLOBALINFO
 */
public class PersonnelDao {
        connexionBd connection = null;
        private Connection cnx;

    public PersonnelDao() {
        cnx= connexionBd.getInstance().getCnx();
    }

    public void addPersonnel(Personnel personnel) throws IOException, SQLException {
        try {

            String requete
                    = "INSERT INTO personnel (nom,prenom,age,prix_h,nb_h,categorie,image) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(requete);
        st.setString(1, personnel.getNom());
        st.setString(2, personnel.getPrenom());
        st.setInt(3, personnel.getAge());
        st.setFloat(5, (float) personnel.getNb_h());
        st.setFloat(4, (float) personnel.getPrix_h());
      st.setInt(6, personnel.getId_cat());//LEE ME "ANDICH FEL TA heki id ccat mte3ek heka cle etranger
        st.setString(7, personnel.getImage());
            st.executeUpdate();
          //System.out.println(personnel.getCategorie()); ajo
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
            

        }

    }
    public void deletePersonnel(int id) throws SQLException {
           String req = "DELETE FROM personnel WHERE id='" + id + "'";
        PreparedStatement ste = cnx.prepareStatement(req);
        ste.executeUpdate();
        System.out.println("supprime");
    }

    public void updatePersonnel(Personnel personnel, int id) throws IOException {

         try {

                 
            String requete
                    ="UPDATE personnel SET nom = ?,prenom = ?,age = ?,prix_h = ?,nb_h = ?,categorie=?,image = ? WHERE id=?";
                        PreparedStatement st = cnx.prepareStatement(requete);
            st.setString(1, personnel.getNom());
        st.setString(2, personnel.getPrenom());
        st.setInt(3, personnel.getAge());
        st.setFloat(5, (float) personnel.getNb_h());
        st.setFloat(4, (float) personnel.getPrix_h());
       st.setInt(6, personnel.getId_cat());
        st.setString(7, personnel.getImage());
             st.setInt(8,id);
             st.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(PersonnelDao.class.getName()).log(Level.SEVERE, null, ex);
         }
            System.out.println("update valide");




    }

   /* public ObservableList<Personnel> displayALLPersonnel() {
        ObservableList<Personnel> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM personnel";
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Personnel p = new Personnel();
               // p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setAge(rs.getInt("age"));
                p.setNb_h(rs.getFloat("nb_h"));
                p.setPrix_h(rs.getFloat("prix_h"));
                p.setCategorie(rs.getString("categorie"));
                  p.setImage(rs.getString("image"));
                 ImageView v=new ImageView();
                 
                   v.setImage(new Image(rs.getString(8)));
                   v.setFitHeight(100);
                   v.setFitWidth(100);
                p.setPhoto(v);
                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }*/
    
 public Personnel displayEventByID(int id) {
        Personnel p = new Personnel();
        try {
            String req = "SELECT * FROM personnel where id='" + id + "'";
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {

                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.getNb_h(rs.getFloat("nb_h"));
                p.setPrix_h(rs.getFloat("prix_h"));
                p.setImage(rs.getString("image"));
                

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return p; // jwna bhy aya by esnaa bich tcomitii wnhabtouh oprojet nekhdmou 3lih wala le ? eyh akiddd nkmll n // o93ed maaya nkaml nhot tak tak el matiere w nhabtouh mabaadhna okk
    }
 
 
 
 
 
 
 public ObservableList<Personnel> recherchePersonnel(String recherche) throws SQLException {
       ObservableList<Personnel> list = FXCollections.observableArrayList();
       // ArrayList<Personnel> list = new ArrayList<>() ;
        String requete = "Select s.id,s.nom,s.prenom,s.age,s.prix_h,s.nb_h,b.type,s.image,s.categorie from categorie b INNER JOIN personnel s on b.id=s.categorie where b.type LIKE '%"+recherche+"%' OR s.prenom LIKE '%"+recherche+"%' OR s.nom LIKE '%"+recherche+"%' OR s.age LIKE '%"+recherche+"%' OR s.nb_h LIKE '%"+recherche+"%' OR s.prix_h LIKE '%"+recherche+"%' OR s.categorie LIKE '%"+recherche+"%'" ;
        try {

            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
    Personnel p = new Personnel (rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(4), rs.getFloat(5), rs.getFloat(6), rs.getString(7), rs.getString(8),rs.getInt(9));
                    
ImageView i=new ImageView();
i.setImage(new Image(rs.getString(8)));
i.setFitHeight(100);
i.setFitWidth(100);
p.setPhoto(i);
            list.add(p);          
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
 
       public  ArrayList<Personnel>  TrierNomPer(){
         ArrayList<Personnel> list = new ArrayList<>() ;
             try {//chimdakhell image ???
                Statement st=cnx.createStatement();
                String req="Select s.id,s.nom,s.prenom, s.age,s.prix_h,s.nb_h,b.type ,s.image,s.categorie from categorie b INNER JOIN personnel s on b.id=s.categorie order by nom";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){

                     // public Personnel(String nom, String prenom, int age, float nb_h, float prix_h, String categorie, String image) {
Personnel e= new  Personnel(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(4), rs.getFloat(5), rs.getFloat(6), rs.getString(7), rs.getString(8),rs.getInt(9));
                  //  Personnel e= new Personnel(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getFloat(6),rs.getString(7)); 
ImageView i=new ImageView();
i.setImage(new Image(rs.getString(8)));
i.setFitHeight(100);
i.setFitWidth(100);
e.setPhoto(i);
        list.add(e);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(CategorieDao.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;  
       }
        public  ArrayList<Personnel>  TrierCatPer(){
         ArrayList<Personnel> list = new ArrayList<>() ;
             try {//chimdakhell image ???
                Statement st=cnx.createStatement();
                String req="Select s.id,s.nom,s.prenom, s.age,s.prix_h,s.nb_h,b.type ,s.image,s.categorie from categorie b INNER JOIN personnel s on b.id=s.categorie order by age";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){

                     // public Personnel(String nom, String prenom, int age, float nb_h, float prix_h, String categorie, String image) {
Personnel e= new  Personnel(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(4), rs.getFloat(5), rs.getFloat(6), rs.getString(7), rs.getString(8),rs.getInt(9));
                  //  Personnel e= new Personnel(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getFloat(6),rs.getString(7)); 
ImageView i=new ImageView();
i.setImage(new Image(rs.getString(8)));
i.setFitHeight(100);
i.setFitWidth(100);
e.setPhoto(i);
        list.add(e);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(CategorieDao.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;  
       }
        public double CalculSalaire(){
            
             double sal = 0;
        String req = "SELECT  prix_h*nb_h FROM personnel";
        try {

            try (Statement pst1 = connexionBd.getInstance().getCnx().createStatement()) {
                ResultSet rs1 = pst1.executeQuery(req);
                rs1.first();
                sal = rs1.getDouble(1);
                rs1.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sal;
        }
}
