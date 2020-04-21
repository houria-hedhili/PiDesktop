/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.aziza;

/**
 *
 * @author GLOBALINFO
 */
public class RatingPersonnel {
    private int id_user;
    private int id_personnel ;
    private int idRating ;
    private double note ;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_personnel() {
        return id_personnel;
    }

    public void setId_personnel(int id_personnel) {
        this.id_personnel = id_personnel;
    }

    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public RatingPersonnel(int id_user, int id_personnel, double note) {
        this.id_user = id_user;
        this.id_personnel = id_personnel;
        this.note = note;
    }

    public RatingPersonnel() {
    }

  
    
    
}
