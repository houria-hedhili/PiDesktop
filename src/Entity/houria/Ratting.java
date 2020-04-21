/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.houria;

/**
 *
 * @author LENOVO
 */
public class Ratting {
    private int id_user;
    private int idCours ;
    private int idRating ;
    private double note ;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idEvent) {
        this.idCours = idEvent;
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

    public Ratting(int id_user, int idCours, double note) {
        this.id_user = id_user;
        this.idCours = idCours;
        this.note = note;
    }

   
    
    
}
