/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.houria;

import ConnexionBd.connexionBd;
import Entity.houria.Cours;
import Entity.houria.Matiere;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//ameni aa oui aa aa3mel comptte ad,in bil phpstorm
/**
 *
 * @author Nader
 */
public class matiereCRUD {       
    connexionBd connection = null;
        private Connection cnx;

    public matiereCRUD() {
        cnx= connexionBd.getInstance().getCnx();
    }

    public void addMatiere(Matiere matiere) throws IOException {
        try {

            String requete
                    = "INSERT INTO matiere (nom,coeff) VALUES (?,?)";
            PreparedStatement st = cnx.prepareStatement(requete);
        st.setString(1, matiere.getNom());
        st.setInt(2, matiere.getCoefficient());
            st.executeUpdate();
            System.out.println("matiere ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

    public void deleteMatiere(int id) throws SQLException {
        String req = "DELETE FROM matiere WHERE id='" + id + "'";
        PreparedStatement ste = cnx.prepareStatement(req);
        ste.executeUpdate();
    }

    public void updateMatiere(Matiere t, int id) throws IOException {

         try {

                     
            String requete
                    ="UPDATE matiere SET nom = ? ,coeff = ? WHERE id=?";
                        PreparedStatement st = cnx.prepareStatement(requete);
             st.setString(1,t.getNom());
             st.setInt(2,t.getCoefficient());
             st.setInt(3,id);
             st.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(matiereCRUD.class.getName()).log(Level.SEVERE, null, ex);
         }
            System.out.println("update valide");



    }

    public ObservableList<Matiere> displayALLMatiere() {
        ObservableList<Matiere> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM matiere";
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Matiere p = new Matiere();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setCoefficient(rs.getInt("coeff"));
                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

  
    
        public Matiere getMatiere(String a) throws SQLException {   
        Matiere an = new Matiere();
        PreparedStatement pre = cnx.prepareStatement("SELECT * FROM matiere WHERE nom LIKE ?  ;");

        pre.setString(1, a);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            an.setId(rs.getInt("id"));
           an.setNom(a);
           an.setCoefficient(rs.getInt("coeff"));


           
        }
        return an;

    }

    public Matiere getMatiereId(int a) throws SQLException {
          Matiere an = new Matiere();
        PreparedStatement pre = cnx.prepareStatement("SELECT * FROM matiere WHERE id = ?  ;");

        pre.setInt(1, a);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            an.setId(a);
           an.setNom(rs.getString("nom"));
           an.setCoefficient(rs.getInt("coeff"));


           
        }
        return an;
     }
    //bara ofter wmbaad ija hel mokhek tawnriglouha  
     public String getnomat(int a) throws SQLException {
          String an="" ;
        PreparedStatement pre = cnx.prepareStatement("SELECT nom FROM matiere WHERE id = ?  ;");

        pre.setInt(1, a);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            an=rs.getString(1);


           
        }
        return an;
     }

    public void modifierRating(Cours e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
