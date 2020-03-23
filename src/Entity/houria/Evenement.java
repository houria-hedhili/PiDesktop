/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.houria;

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author LENOVO
 */
public class Evenement {
    private int idEvent;
    private String description ;
    private Date date;
    private Date date_fin;
    private int nbpart;
    private String nom; 
    private String local ;
    private String image;
    private ImageView photo;
    public Evenement() {
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }
    
    public Evenement(String description, Date date, Date date_fin, int nbpart, String nom, String local,String image) {
        this.description = description;
        this.date = date;
        this.date_fin = date_fin;
        this.nbpart = nbpart;
        this.nom = nom;
        this.local = local;
        this.image=image;
    }
      public Evenement(String description, int nbpart, String nom, String local) {
        this.description = description;
        this.nbpart = nbpart;
        this.nom = nom;
        this.local = local;
  
    }

    public Evenement(String description, int nbpart, String nom, String local,String image) {
        this.description = description;
        this.nbpart = nbpart;
        this.nom = nom;
        this.local = local;
        this.image=image;
    }


    public Evenement(String nom) {
        this.nom = nom;
    }

    public Evenement(int idEvent, String description, Date date, Date date_fin, int nbpart, String nom, String local, String image) {
        this.idEvent = idEvent;
        this.description = description;
        this.date = date;
        this.date_fin = date_fin;
        this.nbpart = nbpart;
        this.nom = nom;
        this.local = local;
        this.image = image;
    }

    public Evenement(String descriptionE, java.util.Date date_debutE, java.util.Date date_finE, int nb_place, String titreE, String adresseE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getNbpart() {
        return nbpart;
    }

    public void setNbpart(int nbpart) {
        this.nbpart = nbpart;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

}
