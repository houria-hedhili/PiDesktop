/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.ameni;

import ConnexionBd.connexionBd;
import Entity.ameni.reclamation;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
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

/**
 *
 * @author User
 */
public class reclamationService {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
     public reclamationService() {
        cnx = connexionBd.getInstance().getCnx();
    }
     
     //tri mtaa les reclamation bl etat
          public ArrayList<reclamation> affRec(){
         ArrayList<reclamation> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="select * from reclamation ORDER BY description DESC";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){
                    reclamation p = new reclamation();//tansalahoymm lahtha
                    list.add(p);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;   
       }
          
           public List afficherReclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
           
                     public ArrayList<reclamation> affRecFront(){
         ArrayList<reclamation> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                               //String req="select r.*,c.nom,f.username "
                                      // + "from reclamation r "
                                       //+ "INNER JOIN categorie_reclamation c "
                                       //+ "on r.CategorieReclamation = c.ref "
                                     //  + "INNER JOIN fos_user p"
                                     //  + "on e.idParent = f.id";                                    
                String req="select r.*,c.nom from reclamation r INNER JOIN categorie_reclamation c on r.CategorieReclamation = c.ref ";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){//hani jeya lahtha ok
                  reclamation p= new reclamation(rs.getInt(1),rs.getDate(2), rs.getString(3), rs.getString(4), rs.getInt(5),rs.getString("nom"));
                    System.out.println("id ."+rs.getString("nom"));
                  list.add(p);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;   
       }
                     
            public  void supprimerRec(int id){
        try {
            PreparedStatement pt=cnx.prepareStatement("Delete from reclamation where id=? ");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(categorieReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
            
        public boolean modifierReclamation(int id,String etat,String description,int idCat){
        try {       //ybadalech fi categorie??narch aaml li ashalek
            PreparedStatement pt=cnx.prepareStatement("Update reclamation set etat= ?,description=?,CategorieReclamation=? where id= '"+id+"'");
            pt.setString(1, etat);                      
            pt.setInt(3,idCat);
            pt.setString(2,description);   
            pt.executeUpdate();
            System.out.println("update ysor");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        }
        
        
                      public void ajouterReclamation(reclamation b){ 
             try {
            String req = "INSERT INTO reclamation (description,CategorieReclamation,etat,date) VALUES "
                    + "('" + b.getDescription()+ "', '" + b.getIdCategorie()+  "', '" + b.getEtat()+"',NOW() )";
            
            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("une nouvelle Reclamation est ajoutée!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
     }
      //recherche suivant l risque                
         public ArrayList<reclamation> affRecherche(){
         ArrayList<reclamation> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="select * from reclamation where etat LIKE 'Urgence sans risque%'";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){
                    reclamation p = new reclamation();
                    list.add(p);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;   
       }
         //nemchi netaacha z nji ma testana fia khater mrigel ?ok hatena kifrk hhhh
         public List<String> getListCat()
         {   List<String> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="select nom from categorie_reclamation";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){
                   String ch=rs.getString(1);
                    list.add(ch);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;
         }
         public int getIdCategorie(String nom)
         {
         int x=0;
             try {
                Statement st=cnx.createStatement();
                String req="select ref from categorie_reclamation where nom ='"+nom+"'";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){
                   x=rs.getInt(1);
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return x;
         
         }
         //njarbou hna l tri
         public ArrayList<reclamation> affRecBack(){
         ArrayList<reclamation> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="select r.*,c.nom from reclamation r INNER JOIN categorie_reclamation c on r.CategorieReclamation = c.ref ORDER BY etat ASC";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){//hani jeya lahtha ok
                  reclamation p= new reclamation(rs.getInt(1),rs.getDate(2), rs.getString(3), rs.getString(4), rs.getInt(5),rs.getString("nom"));
                    System.out.println("id ."+rs.getString("nom"));
                  list.add(p);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;   
       }
         //hedhi taa l urgence f front
                  public ArrayList<reclamation> affRecUrg (){
         ArrayList<reclamation> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="select r.*,c.nom from reclamation r INNER JOIN categorie_reclamation c on r.CategorieReclamation = c.ref where r.etat LIKE '%urgence risque%'";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){//hani jeya lahtha ok
                  reclamation p= new reclamation(rs.getInt(1),rs.getDate(2), rs.getString(3), rs.getString(4), rs.getInt(5),rs.getString("nom"));
                    System.out.println("id ."+rs.getString("nom"));
                  list.add(p);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;   
       }
                  
                   public ArrayList<reclamation> affRecUrgVit (){
         ArrayList<reclamation> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="select r.*,c.nom from reclamation r INNER JOIN categorie_reclamation c on r.CategorieReclamation = c.ref where r.etat LIKE '%urgence non vitale%'";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){//hani jeya lahtha ok
                  reclamation p= new reclamation(rs.getInt(1),rs.getDate(2), rs.getString(3), rs.getString(4), rs.getInt(5),rs.getString("nom"));
                    System.out.println("id ."+rs.getString("nom"));
                  list.add(p);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;   
       }
                   
                   
              public ObservableList<reclamation> Affichertoutpdf() throws SQLException {
        ObservableList<reclamation> list = FXCollections.observableArrayList();
        String requete = "select r.*,c.nom from reclamation r INNER JOIN categorie_reclamation c on r.CategorieReclamation = c.ref  ";
       // (String sexe, String nom, String prenom, int age, String nomLigne, int idParent)
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reclamation p= new reclamation(rs.getInt(1),rs.getDate(2), rs.getString(3), rs.getString(4), rs.getInt(5),rs.getString("nom"));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
              
              

         public ObservableList<reclamation> recherche(String recherche) throws SQLException {
        ObservableList<reclamation> list = FXCollections.observableArrayList();
       
        //String requete = "Select nom,description,ref from categorie_reclamation WHERE `nom` LIKE '%"+recherche+"%' OR `description` LIKE '%"+recherche+"%' OR `ref` LIKE '%"+recherche+"%'  ";
     String requete="select r.*,c.nom from reclamation r INNER JOIN categorie_reclamation c on r.CategorieReclamation = c.ref where r.etat LIKE '%"+recherche+"%' OR r.date LIKE '%"+recherche+"%' OR r.description LIKE '%"+recherche+"%' OR c.nom LIKE '%"+recherche+"%'";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
           reclamation p= new reclamation(rs.getInt(1),rs.getDate(2), rs.getString(3), rs.getString(4), rs.getInt(5),rs.getString("nom"));
            list.add(p);          
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }        
        
                                
         }

