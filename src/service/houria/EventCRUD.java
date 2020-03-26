/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.houria;

import ConnexionBd.connexionBd;
import Entity.houria.Evenement;
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
 * @author houria
 */
public class EventCRUD {
        connexionBd connection = null;
        private Connection cnx;

    public EventCRUD() {
        cnx= connexionBd.getInstance().getCnx();
    }

    public void addEvent(Evenement event) throws IOException {
        try {

            String requete
                    = "INSERT INTO event (nom,date,local,nbpart,date_fin,description,image) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(requete);
        st.setString(1, event.getNom());
        st.setDate(2, event.getDate());
        st.setString(3, event.getLocal());
        st.setInt(4, event.getNbpart());
        st.setDate(5, event.getDate_fin());
        st.setString(6, event.getDescription());
        st.setString(7, event.getImage());
            st.executeUpdate();
            System.out.println("event ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

    public void deleteEvent(int id) throws SQLException {
        String req = "DELETE FROM event WHERE idEvent='" + id + "'";
        PreparedStatement ste = cnx.prepareStatement(req);
        ste.executeUpdate();
    }

    public void updateEvent(Evenement t, int id) throws IOException {

              // Date dd=new java.sql.Date(t.getDate_debut_event().getTime());
        //Date df=new java.sql.Date(t.getDate_fin_event().getTime()); 
         try {

                     
            String requete
                    ="UPDATE event SET nom = ? ,image = ? ,nbpart =? ,description=? ,local=? WHERE idEvent=?";
                        PreparedStatement st = cnx.prepareStatement(requete);
             st.setString(1,t.getNom());
             st.setString(2,t.getImage());
             //pre.setDate(3,dd);
             //pre.setDate(4, df);
             st.setInt(3, t.getNbpart());
             st.setString(4,t.getDescription());
            st.setString(5,t.getLocal());

             st.setInt(6,id);
             st.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(EventCRUD.class.getName()).log(Level.SEVERE, null, ex);
         }
            System.out.println("update valide");



    }

    public ObservableList<Evenement> displayALLEvent() {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM event";
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Evenement p = new Evenement();
                p.setIdEvent(rs.getInt("idEvent"));
                p.setNom(rs.getString("nom"));
                p.setDate(rs.getDate("date"));
                p.setLocal(rs.getString("local"));
                p.setNbpart(rs.getInt("nbpart"));
               
                p.setDate_fin(rs.getDate("date_fin"));
                p.setDescription(rs.getString("description"));
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
    }

    public Evenement displayEventByID(int id) {
        Evenement p = new Evenement();
        try {
            String req = "SELECT * FROM event where id='" + id + "'";
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {

                p.setIdEvent(rs.getInt("idEvent"));
                p.setNom(rs.getString("nom"));
                p.setDate(rs.getDate("date"));
                p.setLocal(rs.getString("local"));
                p.setNbpart(rs.getInt("nbpart"));
                p.setImage(rs.getString("image"));
                p.setDate_fin(rs.getDate("date_fin"));
                p.setDescription(rs.getString("description"));

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return p; // jwna bhy aya by esnaa bich tcomitii wnhabtouh oprojet nekhdmou 3lih wala le ? eyh akiddd nkmll n // o93ed maaya nkaml nhot tak tak el matiere w nhabtouh mabaadhna okk
    }

}
