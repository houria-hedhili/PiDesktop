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
      st.setString(6, personnel.getCategorie());
        st.setString(7, personnel.getImage());
            st.executeUpdate();
            System.out.println("personnel ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
            

        }

    }
    public void deletePersonnel(int id) throws SQLException {
        String req = "DELETE FROM personnel WHERE id='" + id + "'";
        PreparedStatement ste = cnx.prepareStatement(req);
        ste.executeUpdate();
    }

    public void updatePersonnel(Personnel personnel, int id) throws IOException {

         try {

                     
            String requete
                    ="UPDATE personnel SET nom = ?,prenom = ?,age = ?,prix_h = ?,nb_h = ?,image = ? WHERE id=?";
                        PreparedStatement st = cnx.prepareStatement(requete);
            st.setString(1, personnel.getNom());
        st.setString(2, personnel.getPrenom());
        st.setInt(3, personnel.getAge());
        st.setFloat(5, (float) personnel.getNb_h());
        st.setFloat(4, (float) personnel.getPrix_h());
       // st.setString(6, personnel.getCategorie());
        st.setString(6, personnel.getImage());
             st.setInt(7,id);
             st.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(PersonnelDao.class.getName()).log(Level.SEVERE, null, ex);
         }
            System.out.println("update valide");



    }

    public ObservableList<Personnel> displayALLPersonnel() {
        ObservableList<Personnel> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM personnel";
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Personnel p = new Personnel();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setAge(rs.getInt("age"));
                p.setNb_h(rs.getFloat("nb_h"));
                p.setPrix_h(rs.getFloat("prix_h"));
                  p.setImage(rs.getString("image"));
                 ImageView v=new ImageView();
                 
                  // v.setImage(new Image(rs.getString(8)));
                   v.setFitHeight(100);
                   v.setFitWidth(100);
                p.setPhoto(v);
                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }
    
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
    
}
