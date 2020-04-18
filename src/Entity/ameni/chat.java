/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.ameni;

/**
 *
 * @author User
 */
public class chat {

    private String question;
    private String reponse;
    private int idChat;

    public chat(String question, String reponse) {
        this.question = question;
        this.reponse = reponse;
    }

    public chat(String question, String reponse, int idChat) {
        this.question = question;
        this.reponse = reponse;
        this.idChat = idChat;
    }

    public chat(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    public int getIdChat() {
        return idChat;
    }

    public void setIdChat(int idChat) {
        this.idChat = idChat;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    @Override
    public String toString() {
        return "chat{" + "question=" + question + ", reponse=" + reponse + ", idChat=" + idChat + '}';
    }


    
    
}
