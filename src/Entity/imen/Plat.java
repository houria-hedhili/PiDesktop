/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.imen;

import java.util.Date;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 *
 * @author Imen
 */
public class Plat {
    private int id;
    private String nom;
    private String description;
    private String  image;
    private Date date;
    private String type;
    private int nbrVue;
    private int nbrAime;
 private ImageView photo= new ImageView();

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

   

  
    public Plat() {
    }

    public Plat(String nom, String description, String image, String type, int nbrVue, int nbrAime) {
        this.nom = nom;
        this.description = description;
        this.image = image;
//this.photo.setImage(new Image(image));
        this.type = type;//rigel jawek
        this.nbrVue = nbrVue;
        this.nbrAime = nbrAime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNbrVue() {
        return nbrVue;
    }

    public void setNbrVue(int nbrVue) {
        this.nbrVue = nbrVue;
    }

    public int getNbrAime() {
        return nbrAime;
    }

    public void setNbrAime(int nbrAime) {
        this.nbrAime = nbrAime;
    }
    
    
    
}
