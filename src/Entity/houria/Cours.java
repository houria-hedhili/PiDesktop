/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.houria;

import java.sql.Time;
import javafx.scene.image.ImageView;

/**
 *
 * @author LENOVO
 */
public class Cours {
    private Matiere a;  
    private int id_mat;
        private int id  ;
    private String description ;
    private String image;
    private int seats;
    private Time duree; 
    private int age ;
    private ImageView photo;


    public Cours(int id_mat, String description, Time duree, int seats, int age, String image) {
        this.id_mat = id_mat;
        this.description = description;
        this.seats = seats;
        this.duree = duree;
        this.age = age;
        this.image = image;
        
    }
    public Cours(int id_mat, String description, Time duree, int seats, int age) {
        this.id_mat = id_mat;
        this.description = description;
        this.seats = seats;
        this.duree = duree;
        this.age = age;
        
    }

    public int getId_mat() {
        return id_mat;
    }

    public void setId_mat(int id_mat) {
        this.id_mat = id_mat;
    }

    public Cours() {
    }

    public Cours(Matiere a, String description, int seats, Time duree, int age, ImageView photo) {
        this.a = a;
        this.description = description;
        this.seats = seats;
        this.duree = duree;
        this.age = age;
        this.photo = photo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public Matiere getA() {
        return a;
    }

    public void setA(Matiere a) {
        this.a = a;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getDuree() {
        return duree;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }



    
}
