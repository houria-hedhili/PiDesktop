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
import java.sql.Date;
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
        st.setTimestamp(2, event.getDate());
        st.setString(3, event.getLocal());
        st.setInt(4, event.getNbpart());
        st.setTimestamp(5, event.getDate_fin());
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


         try {

                     
            String requete
                    ="UPDATE event SET nom = ? ,image = ? ,date = ? ,date_fin = ? ,nbpart =? ,description=? ,local=? WHERE idEvent=?";
                        PreparedStatement st = cnx.prepareStatement(requete);
             st.setString(1,t.getNom());
             st.setString(2,t.getImage());
             st.setTimestamp(3,t.getDate());

             st.setTimestamp(4,t.getDate_fin());
             st.setInt(5, t.getNbpart());
             st.setString(6,t.getDescription());
             st.setString(7,t.getLocal());

             st.setInt(8,id);
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
                p.setDate(rs.getTimestamp("date"));
                p.setLocal(rs.getString("local"));
                p.setNbpart(rs.getInt("nbpart"));
               
                p.setDate_fin(rs.getTimestamp("date_fin"));
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
        public ObservableList<Evenement> displaymesevent(int a) {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        try {
                String requete
                      
                    =" select * from event where idEvent in (select idEvent from participation where idUser = '"+a+"') ORDER BY date DESC ";

                        PreparedStatement st = cnx.prepareStatement(requete);
                 // st.setInt(1, a);

                      ResultSet rs = st.executeQuery(requete);


            while (rs.next()) {
                Evenement p = new Evenement();
                p.setIdEvent(rs.getInt("idEvent"));
                p.setNom(rs.getString("nom"));
                p.setDate(rs.getTimestamp("date"));
                p.setLocal(rs.getString("local"));
                p.setNbpart(rs.getInt("nbpart"));
               
                p.setDate_fin(rs.getTimestamp("date_fin"));
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
                p.setDate(rs.getTimestamp("date"));
                p.setLocal(rs.getString("local"));
                p.setNbpart(rs.getInt("nbpart"));
                p.setImage(rs.getString("image"));
                p.setDate_fin(rs.getTimestamp("date_fin"));
                p.setDescription(rs.getString("description"));
                ImageView v=new ImageView();
                v.setImage(new Image(rs.getString(8)));
                v.setFitHeight(100);
                v.setFitWidth(100);
                p.setPhoto(v);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return p; 
    }
           public void decrementer(int a,int id) {
        try {

                     
            String requete
                    ="UPDATE event SET nbpart =? WHERE idEvent=?";
                        PreparedStatement st = cnx.prepareStatement(requete);
              st.setInt(1,a);

             st.setInt(2,id);
             st.executeUpdate();
             System.out.println("les nb de place dispo est diminué ");

         } catch (SQLException ex) {
             Logger.getLogger(EventCRUD.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
           
           public ObservableList<Evenement> rechercheEvent(String recherche) throws SQLException {
                   Evenement p = new Evenement();

        ObservableList<Evenement> list = FXCollections.observableArrayList();
        String requete = "Select *from event WHERE description LIKE '%"+recherche+"%' OR date LIKE '%"+recherche+"%' OR date_fin LIKE '%"+recherche+"%' OR nom LIKE '%"+recherche+"%' OR nbpart LIKE '%"+recherche+"%' OR local LIKE '%"+recherche+"%'  ";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
           p.setIdEvent(rs.getInt("idEvent"));
                p.setNom(rs.getString("nom"));
                p.setDate(rs.getTimestamp("date"));
                p.setLocal(rs.getString("local"));
                p.setNbpart(rs.getInt("nbpart"));
                p.setImage(rs.getString("image"));
                p.setDate_fin(rs.getTimestamp("date_fin"));
                p.setDescription(rs.getString("description"));
                ImageView v=new ImageView();
                v.setImage(new Image(rs.getString(8)));
                v.setFitHeight(100);
                v.setFitWidth(100);
                p.setPhoto(v);
            list.add(p);          
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
