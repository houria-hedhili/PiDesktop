/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.ameni;

import ConnexionBd.connexionBd;
import Entity.ameni.categorieReclamation;
import Entity.ameni.chat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class chatService {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
         public chatService() {
        cnx = connexionBd.getInstance().getCnx();
    }
         
           public ArrayList<chat> affChat(){
         ArrayList<chat> list = new ArrayList<>() ;
             try {
                Statement st=cnx.createStatement();
                String req="select * from chat";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){
                    chat p = new chat(rs.getString(1),rs.getString(2),rs.getInt(3));
                    list.add(p);            
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(chat.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;   
       }         
           
           public List afficherChat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
           
            public void ajouterChatQuest(chat b){
             try {
            String req = "INSERT INTO chat (question, reponse) VALUES "
                    + "('" + b.getQuestion() + "', '" + b.getReponse()+ "')";
            
            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("une nouvelle Question est ajoutée!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
     }
            
            
            public void modifierChat(String question,String reponse, int idChat){
        try {       
            PreparedStatement pt=cnx.prepareStatement("Update chat set question= ?,  reponse=?  where idChat=? ");
            pt.setString(1,question);
            pt.setString(2,reponse);
            pt.setInt(3, idChat);          
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chatService.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
            
}
