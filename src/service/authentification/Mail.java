/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.authentification;

import com.sun.mail.util.MailSSLSocketFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;

import javax.mail.*;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
 

/**
 *
 * @author Imen
 */
public class Mail {
    private String username = "imen.manai@esprit.tn";
private String password = "imenmanai12";
public static String contenue= "<!DOCTYPE html>\n" +
"<!-- saved from url=(0086)http://themes.propeller.in/bootstrap4/html/bootstrap4-admin-theme/reset-password.html# -->\n" +
"<html lang=\"en-US\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"\n" +
"<title>Bootstrap 4 Admin HTML Theme - Reset Password Email Template</title>\n" +
"<meta name=\"description\" content=\"Reset password email template. Bootstrap 4 Admin HTML Theme is a material design and bootstrap 4 based responsive dashboard template by propeller created mainly for admin and backend applications.\">\n" +
"<style type=\"text/css\">\n" +
"a:hover { text-decoration: underline !important; }\n" +
"</style>\n" +
"</head>\n" +
"\n" +
"<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f8f9;\" leftmargin=\"0\" data-gr-c-s-loaded=\"true\">\n" +
"<!--100% body table-->\n" +
"<table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f8f9\" style=\"@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300); font-family: &#39;Roboto&#39;, sans-serif , Arial, Helvetica, sans-serif;\">\n" +
"  <tbody><tr>\n" +
"    <td>\n" +
"    	<table style=\"background-color: #f2f8f9; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"  			<tbody><tr>\n" +
"    			<td style=\"height:80px;\">&nbsp;</td>\n" +
"         	</tr>\n" +
"          <tr>\n" +
"    			  <td style=\"text-align:center;\">\n" +
"            </td>\n" +
"         	</tr>\n" +
"            <tr>\n" +
"    			<td style=\"height:40px;\">&nbsp;</td>\n" +
"         	</tr>\n" +
"            <tr>\n" +
"            	<td>\n" +
"                    <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 1px 3px 0 rgba(0, 0, 0, 0.16), 0 1px 3px 0 rgba(0, 0, 0, 0.12);-moz-box-shadow:0 1px 3px 0 rgba(0, 0, 0, 0.16), 0 1px 3px 0 rgba(0, 0, 0, 0.12);box-shadow:0 1px 3px 0 rgba(0, 0, 0, 0.16), 0 1px 3px 0 rgba(0, 0, 0, 0.12)\">\n" +
"                        <tbody><tr>\n" +
"                            <td style=\"height:40px;\">&nbsp;</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td style=\"padding:0 15px;\">\n" +
"                                <h1 style=\"color:#3075BA; font-weight:400; margin:0;font-size:30px;\">Vous avez demande de reinitialiser votre mot de passe du compte coccinelle</h1>\n" +
"                                <span style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\n" +
"                                <p style=\"color:#171f23de; font-size:15px;line-height:24px; margin:0;\">\n" +
"                                	Voici votre code de verification : \n" +
"                                </p>";
static public String contenue2=" \n" +
"                            </td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td style=\"height:40px;\">&nbsp;</td>\n" +
"                        </tr>\n" +
"                    </tbody></table>\n" +
"                </td>\n" +
"            </tr><tr>\n" +
"    			<td style=\"height:20px;\">&nbsp;</td>\n" +
"         	</tr>\n" +
"          <tr>\n" +
"    			  <td style=\"text-align:center;\">\n" +
"					     <p style=\"font-size:14px; color:#455056bd; line-height:18px; margin:0 0 0;\">Powered by <strong>Coccinelle </strong></p>\n" +
"            </td>\n" +
"         	</tr>\n" +
"          <tr>\n" +
"  			   <td style=\"height:80px;\">&nbsp;</td>\n" +
"         	</tr>\n" +
"     	</tbody></table>\n" +
"    </td>\n" +
"  </tr>\n" +
"</tbody></table><!--/100% body table-->\n" +
"\n" +
"</body></html>";
    public Mail() {
    }

public void envoyer(String mail,String token) throws IOException  {
// Etape 1 : Création de la session


   
       Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.googlemail.com");
        props.put("mail.from",username);
          props.setProperty("mail.transport.protocol", "smtp");     
    props.setProperty("mail.host", "smtp.gmail.com");  
    props.put("mail.smtp.auth", "true");  
    props.put("mail.smtp.port", "465");  
    props.put("mail.debug", "true");  
    props.put("mail.smtp.socketFactory.port", "465");  
    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
    props.put("mail.smtp.socketFactory.fallback", "false");   
    Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {
       protected PasswordAuthentication getPasswordAuthentication() {  
       return new PasswordAuthentication(username,password);  
   }  
   });  
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom();
            msg.setRecipients(Message.RecipientType.TO,
                              mail);
            msg.setSubject("Réinitialisation du mot de passe de votre compte Coccinelle");
            msg.setSentDate(new Date());
           // msg.setText(contenu);
                     buildMessage(token, msg);

            Transport.send(msg);
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }



}

public static void buildMessage(String token, Message msg) throws MessagingException, IOException {
 
    String sujet = msg.getSubject();
    StringBuilder sb = new StringBuilder();
    sb.append(contenue);
   sb.append(token);
    sb.append(contenue2);
   /* sb.append(sujet + "imen");
    sb.append("</TITLE>");
    sb.append("</HEAD>");
 
    sb.append("<BODY>");
    sb.append("<H1>" + sujet + "</H1>" + "");
 
      sb.append(contenu);
      sb.append("");
 
    sb.append("</BODY>");
    sb.append("</HTML>");*/
 
    msg.setDataHandler(new DataHandler(new ByteArrayDataSource(sb.toString(), "text/html")));
  }
//Etape 4 : Tester la méthode

}
