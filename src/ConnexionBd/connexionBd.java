/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnexionBd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Imen
 */
public class connexionBd {
    private static connexionBd instance;
        private String url = "jdbc:mysql://localhost:3306/coccinelle";
       private String USERNAME="root";
       private  String PASSWORD="";
       private Connection cnx;

       private connexionBd() {
       
      
//ahh mahatotch el cnn eyliyyb rary eyy chouf
    
try {
            cnx = DriverManager.getConnection(url,USERNAME,PASSWORD);                        // driver qui va traduit la cnnx entre java et base de donnee
            System.out.println("connection etablie");
            // lezem tzid librerie add librairie add mysqljdbc driver
       
        } catch (SQLException ex) {
            Logger.getLogger(connexionBd.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
 public static connexionBd getInstance(){
        if (instance == null) {
            instance = new connexionBd();
        }
        return instance ;
    }

    public Connection getCnx() {
        return cnx;
    }    

}
