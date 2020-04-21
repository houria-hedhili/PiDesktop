/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.houria;

import ConnexionBd.connexionBd;
import Entity.houria.Cours;
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
 * @author Nader
 */
public class CoursCRUD {
    connexionBd connection = null;
        private Connection cnx;

    public CoursCRUD() {
        cnx= connexionBd.getInstance().getCnx();
    }

    public void addCours(Cours event) throws IOException {
        try {

            String requete
                    = "INSERT INTO cours (matiere,description,duree,seats,age,image) VALUES (?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(requete);
   
        st.setInt(1,event.getId_mat());
        st.setString(2, event.getDescription());
        st.setTime(3, event.getDuree());
        st.setInt(4, event.getSeats());
        st.setInt(5, event.getAge());
        
        System.out.println(event.getImage() );
        st.setString(6, event.getImage());
            st.executeUpdate();
            System.out.println("cours ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

    public void deleteCours(int id) throws SQLException {
        String req = "DELETE FROM cours WHERE id='" + id + "'";
        PreparedStatement ste = cnx.prepareStatement(req);
        ste.executeUpdate();
    }

    public void updateCours(Cours event, int id) throws IOException {

         try {

                     
            String requete
                    ="UPDATE cours SET matiere = ? ,description = ? ,duree =? ,seats=? ,age=? ,image=? WHERE id=?";
                        PreparedStatement st = cnx.prepareStatement(requete);
           st.setInt(1,event.getId_mat());
        st.setString(2, event.getDescription());
        st.setTime(3, event.getDuree());
        st.setInt(4, event.getSeats());
        st.setInt(5, event.getAge());

        
        System.out.println(event.getImage() );
        st.setString(6, event.getImage());
        st.setInt(7, id);

        st.executeUpdate();
           // System.out.println("cours ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
            System.out.println("update valide");



    }
    public ObservableList<Cours> displayALLCours() {
        ObservableList<Cours> myList = FXCollections.observableArrayList();
        try {
            String req ="select r.*,c.nom from cours r INNER JOIN matiere c on r.matiere = c.id ";
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Cours p = new Cours();
                p.setId(rs.getInt("id"));
                p.setImage(rs.getString("image"));
                System.out.println(rs.getString("image"));
                p.setId_mat(rs.getInt("matiere"));
                p.setDescription(rs.getString("description"));
                p.setDuree(rs.getTime("duree"));
                p.setSeats(rs.getInt("seats"));
                p.setAge(rs.getInt("age"));
                 ImageView v=new ImageView();
                   v.setImage(new Image("file:/C:/xampp/htdocs/integration/jardin/web/images/courses/"+rs.getString("image")));
                   v.setFitHeight(100);
                   v.setFitWidth(100);
                p.setPhoto(v); 
                p.setMat(rs.getString("nom"));
                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }
               public ObservableList<Cours> rechercheCours(String recherche) throws SQLException {
                   Cours p = new Cours();

        ObservableList<Cours> list = FXCollections.observableArrayList();
        String requete = "select r.*,c.nom from cours r INNER JOIN matiere c on r.matiere = c.id  WHERE c.nom LIKE '%"+recherche+"%' OR description LIKE '%"+recherche+"%' OR duree LIKE '%"+recherche+"%' OR seats LIKE '%"+recherche+"%' OR age LIKE '%"+recherche+"%'  ";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id"));
                
                p.setId_mat(rs.getInt("matiere"));
                p.setDescription(rs.getString("description"));
                p.setDuree(rs.getTime("duree"));
                p.setSeats(rs.getInt("seats"));
                p.setAge(rs.getInt("age"));
                 ImageView v=new ImageView();
                   v.setImage(new Image("file:/C:/xampp/htdocs/integration/jardin/web/images/courses/"+rs.getString(7)));
                   v.setFitHeight(100);
                   v.setFitWidth(100);
                p.setPhoto(v);
                p.setMat(rs.getString("nom"));
                list.add(p);
 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

               
               
  
               
               
}