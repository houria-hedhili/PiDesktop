/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.aziza;


import javafx.scene.image.ImageView;

/**
 *
 * @author GLOBALINFO
 */
public class Personnel {
     private int id;
      private String nom;
    private String prenom;
     private int age;
     private float nb_h;
     private float prix_h;
     private Categorie categorie;
      private String image;
      private ImageView photo;

    public Personnel(String text, String text0, int age, float prix, float heure, String img) {
        this.nom=text;
        this.prenom=text0;
this.age=age;
this.prix_h=prix;
this.nb_h=heure;
this.image=img;//To change body of generated methods, choose Tools | Templates.
    }

    

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNb_h() {
        return nb_h;
    }

    public void setNb_h(float nb_h) {
        this.nb_h = nb_h;
    }

    public double getPrix_h() {
        return prix_h;
    }

    public void setPrix_h(float prix_h) {
        this.prix_h = prix_h;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

   

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    @Override
    public String toString() {
        return "Personnel{" + "id=" + id + ", nb_h=" + nb_h + ", prix_h=" + prix_h + ", age=" + age + ", categorie=" + categorie + ", nom=" + nom + ", prenom=" + prenom + ", image=" + image + ", photo=" + photo + '}';
    }

    public Personnel() {
    }

   
    public Personnel(String nom, String prenom, int age, float nb_h, float prix_h, String image, ImageView photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.nb_h = nb_h;
        this.prix_h = prix_h;
        this.image = image;
        this.photo = photo;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

   

   

    public Personnel(String nom, String prenom, int age, float nb_h, float prix_h, Categorie categorie, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.nb_h = nb_h;
        this.prix_h = prix_h;
        this.categorie = categorie;
        this.image = image;
    }

    public float getNb_h(float aFloat) {
        return nb_h;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
      
}