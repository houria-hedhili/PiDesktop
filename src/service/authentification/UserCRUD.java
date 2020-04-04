/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.authentification;

/**
 *
 * @author ASUS
 */
//import edu.EcoSystem.entities.User;
//import edu.EcoSystem.touls.MyConnection;
import ConnexionBd.connexionBd;
import Entity.user.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
        

/**
 *
 * @author zied
 */
public class UserCRUD {
    
    public boolean verifpassword(String username, String password ) throws SQLException {
        if (!username.isEmpty() && !password.isEmpty() ) {
            String requete = "SELECT password FROM fos_user WHERE username = '" + username +"'";
            System.out.println("requete = " +requete);
            Statement s = connexionBd.getInstance().getCnx().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
                String pw = rs.getString(1);
                System.out.println("pw1 = " +pw);
                pw = pw.replace("$2y$", "$2a$");
                System.out.println("pw2 = " +pw);
                return (BCrypt.checkpw(password, pw));
            }
            else return false ;
        }  
        else {
            return false;
        }

    }
    
    //
   
    
    
    public boolean bloqué( int enable){
        if(enable==1){
            return true;
        }
        return false;
    }
    
    
    
    
    public void ajoutUser(Utilisateur u, String password ){
        try {
            String hashedpw = BCrypt.hashpw(password, BCrypt.gensalt());
             hashedpw = hashedpw.replace("$2a$", "$2y$");
            String UserRole = "a:0:{}";
        String requete = "INSERT INTO fos_user ( username, username_canonical, email , email_canonical ,enabled, password , roles,nom,prenom) VALUES "
                + "('" + u.getUsername() + "','"+  u.getUsername() + "','" + u.getEmail() + "','" + u.getEmail() +"','" + 1 + "','" + hashedpw + "','" + UserRole + "','" + u.getNom() + "','" + u.getPrenom() + "')";
        Statement st =  connexionBd.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
   public void ajoutAdmin(Utilisateur u, String password ){
        try { String role="a:1:{i:0;s:16:\"ROLE_SUPER_ADMIN \";}";
            String hashedpw = BCrypt.hashpw(password, BCrypt.gensalt());
        String requete = "INSERT INTO fos_user (username,email,password,roles) VALUES "
                + "('" + u.getUsername() + "','" + u.getEmail() + "','" + hashedpw + "','" +role+ "','" + "')";
        Statement st =  connexionBd.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
   
   
   
   
   
 
    public List<Utilisateur> chercher(String s) throws SQLException {
		List<Utilisateur> users = new ArrayList<>();
String rq = "select * from fos_user where username like'"+s+"%' or email like'"+s+"%' or roles like'"+s+"%'" ;
			
			Statement st =  connexionBd.getInstance().getCnx().createStatement();
			ResultSet rst = st.executeQuery(rq);

			while (rst.next()) {
				Utilisateur u=new Utilisateur(rst.getInt("id"),rst.getString("username"),rst.getString("email"),rst.getInt("enabled"),rst.getString("roles"));
                                users.add(u);
			}
		
		return users;
	}
    
    public List<Utilisateur> chercherEnabled(String s) throws SQLException {
		List<Utilisateur> users = new ArrayList<>();
String rq = "select * from fos_user where enabled ='"+ 1 +"' and (username like'"+s+"%' or email like'"+s+"%' or roles like'"+s+"%')" ;
			
			Statement st =  connexionBd.getInstance().getCnx().createStatement();
			ResultSet rst = st.executeQuery(rq);

			while (rst.next()) {
				Utilisateur u=new Utilisateur(rst.getInt("id"),rst.getString("username"),rst.getString("email"),rst.getInt("enabled"),rst.getString("roles"));
                                users.add(u);
			}
		
		return users;
	}
    
    public List<Utilisateur> chercherDisabled(String s) throws SQLException {
		List<Utilisateur> users = new ArrayList<>();
String rq = "select * from fos_user where enabled ='"+ 0 +"' and (username like'"+s+"%' or email like'"+s+"%' or roles like'"+s+"%')" ;
			
			Statement st =  connexionBd.getInstance().getCnx().createStatement();
			ResultSet rst = st.executeQuery(rq);

			while (rst.next()) {
				Utilisateur u=new Utilisateur(rst.getInt("id"),rst.getString("username"),rst.getString("email"),rst.getInt("enabled"),rst.getString("roles"));
                                users.add(u);
			}
		
		return users;
	}
    
    
    
     public List<Utilisateur> getAllUsers() throws SQLException
    {   
        List <Utilisateur> products= new ArrayList<>();
        String req="SELECT * FROM fos_user";
	Statement stm =  connexionBd.getInstance().getCnx().createStatement();
        ResultSet rst=stm.executeQuery(req);
        
        while (rst.next()){
              Utilisateur u=new Utilisateur(rst.getInt("id"),rst.getString("username"),rst.getString("email"),rst.getInt("enabled"),rst.getString("roles"));
            products.add(u);
        }
        return products;
    }

    
    
    
    
    
    
    
    
    
    public boolean modifierUser(Utilisateur u){
        try{
            String cpass=BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
        String requete = "UPDATE fos_user SET "
                + "username = ?,"
                + "email = ?,"
                + "password = ?"
                + "WHERE id=?";
        PreparedStatement pst =  connexionBd.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1, u.getUsername());
        pst.setString(2, u.getEmail());
        pst.setString(3, cpass);
        pst.setInt(4, u.getId());
        pst.executeUpdate();
        System.out.println(u.getEmail()+ u.getUsername());
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return true;
}
    
    
    
    public void modifierUsername(String email, String username) throws SQLException{
        String requete= "UPDATE fos_user SET username='"+ username +"'"+"WHERE email='"+ email+"'";
        Statement st =  connexionBd.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierEmail(String email, String mail) throws SQLException{
        String requete= "UPDATE fos_user SET email='"+ mail +"'"+"WHERE email='"+ email+"'";
        Statement st =  connexionBd.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierPassword(String email, String pass) throws SQLException{
       String cpass=BCrypt.hashpw(pass, BCrypt.gensalt());
        String requete= "UPDATE fos_user SET password='"+ cpass +"'"+"WHERE email='"+ email+"'";
        Statement st =  connexionBd.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierRole(String email, String role) throws SQLException{
        String requete= "UPDATE fos_user SET roles='"+ role +"'"+"WHERE email='"+ email+"'";
        Statement st =  connexionBd.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierAvatar(String email, String avatars) throws SQLException{
        String requete= "UPDATE fos_user SET avatar='"+ avatars +"'"+"WHERE email='"+ email+"'";
        Statement st = connexionBd.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierEtat(int id, int enable ) throws SQLException{
        System.out.println(id);
        String requete= "UPDATE ser SET enabled='"+ enable +"'"+"WHERE id='"+ id +"'";
        Statement st =  connexionBd.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
    }
    
    
    
    public void supprimerUser(int id ){
        try{
        String requete = "DELETE FROM fos_user WHERE id=?";
        PreparedStatement pst =  connexionBd.getInstance().getCnx().prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    public List<Utilisateur> afficherUser() {
        
        List<Utilisateur> listUser = new ArrayList<>();
        try {
        String requete ="SELECT * FROM fos_user";
        Statement st =  connexionBd.getInstance().getCnx().createStatement();
        
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));
                listUser.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUser;
    }
    
    public List<Utilisateur> afficherUserEnabled() {
        
        List<Utilisateur> listUser = new ArrayList<>();
        try {
        String requete ="SELECT * FROM fos_user where enabled='"+ 1 +"'";
        Statement st =  connexionBd.getInstance().getCnx().createStatement();
        
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));
                listUser.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUser;
    }
    
    public List<Utilisateur> afficherUserDisabled() {
        
        List<Utilisateur> listUser = new ArrayList<>();
        try {
        String requete ="SELECT * FROM fos_user where enabled='"+ 0 +"'";
        Statement st =  connexionBd.getInstance().getCnx().createStatement();
        
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));
                listUser.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUser;
    }
    
    
    
    public Utilisateur getUserByUsername(String username) throws SQLException{
        String requete="SELECT * FROM fos_user WHERE username='"+username+"'";
        Statement st =  connexionBd.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        Utilisateur u = new Utilisateur();
       while(rs.next()){
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));  
            }
       return u ;
    }
    
    
    
    
     public boolean existUser (String email) throws SQLException {
        
          String requete = "SELECT * FROM fos_user WHERE email = '" + email +"'";
            Statement s =  connexionBd.getInstance().getCnx().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
            return true ; }
            else 
                return false ;
        
    }
    
     public boolean existMail (String email) throws SQLException {
        
          String requete = "SELECT * FROM fos_user WHERE email = '" + email +"'";
            Statement s = connexionBd.getInstance().getCnx().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
            return true ; }
            else 
                return false ;
        
    }
     public void creerToken(String token)
     {
       try {
           
           
        String requete = "INSERT INTO tokens (token) VALUES "
                + "('" + token +  "')";
        Statement st =  connexionBd.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
     
     }
       public boolean verifToken (String token) throws SQLException {
        
          String requete = "SELECT * FROM tokens WHERE token = '" + token +"'";
            Statement s =  connexionBd.getInstance().getCnx().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
            return true ; }
            else 
                return false ;
        
    }
     
     public boolean existUsername (String username) throws SQLException {
        
          String requete = "SELECT * FROM fos_user WHERE username = '" + username +"'";
            Statement s =  connexionBd.getInstance().getCnx().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
            return true ; }
            else 
                return false ;
        
    }
     public Utilisateur getUser(String n) throws SQLException{
           Utilisateur u=new Utilisateur();

         try{
                   String requete = "SELECT * FROM fos_user WHERE username = '" + n +"'";
                       Statement s =  connexionBd.getInstance().getCnx().createStatement();
            ResultSet rs = s.executeQuery(requete);
             while (rs.next()) {

                u.setId(rs.getInt("id")); 
                u.setNom(rs.getString("username"));
                u.setEmail(rs.getString("email"));
             } 
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
            return u; 


     }
    
      public String verifAdmin (String username) throws SQLException {
        String ch="";
          String requete = "SELECT roles FROM fos_user WHERE username = '" + username +"'";
            Statement s =  connexionBd.getInstance().getCnx().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
           ch= rs.getString(1) ; }
        return ch;
    }
         public String getNomPrenom (String username) throws SQLException {
        String ch="";
          String requete = "SELECT nom,prenom FROM fos_user WHERE username = '" + username +"'";
            Statement s =  connexionBd.getInstance().getCnx().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
           ch= rs.getString(2)+" "+rs.getString(1) ; }
        return ch;
    }
        public void supprimerToken(String t ){
        try{
        String requete = "DELETE FROM tokens WHERE token=?";
        PreparedStatement pst =  connexionBd.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1, t);
        pst.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
