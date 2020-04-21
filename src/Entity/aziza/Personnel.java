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
     private String categorie;
     private int id_cat;
      private String image;
      private ImageView photo;
    public double t;

    public double getT() {
        return t;
    }

    public Personnel(String nom, String prenom, int age, float nb_h, float prix_h, String categorie, int id_cat, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.nb_h = nb_h;
        this.prix_h = prix_h;
        this.categorie = categorie;
        this.id_cat = id_cat;
        this.image = image;
    }
    
   public Personnel(int id,String nom, String prenom, int age, float nb_h, float prix_h, String categorie, String image) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.nb_h = nb_h;
        this.prix_h = prix_h;
        this.categorie = categorie;
        this.image = image;
    }
    public Personnel(int id,String nom, String prenom, int age, float nb_h, float prix_h, String categorie, String image,int idCat) {
        this.id=id;
        this.nom = nom;
        
       this.id_cat=idCat;
        this.prenom = prenom;
        this.age = age;
        this.nb_h = nb_h;
        this.prix_h = prix_h;
        this.categorie = categorie;
        this.image = image;
    }
    public Personnel(String text, String text0, int parseInt, float parseFloat, float parseFloat0, int idCategorie, String img) {
       this.nom=text;
       this.prenom=text0;
       this.age=parseInt;
       this.prix_h=parseFloat;
       this.nb_h=parseFloat0;
       this.id_cat=idCategorie;
       this.image=img;
    }

   

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public Personnel(int id, String nom, String prenom, int age, float nb_h, float prix_h, int id_cat, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.nb_h = nb_h;
        this.prix_h = prix_h;
        this.id_cat = id_cat;
        this.image = image;
    }
      

    public Personnel(String text, String text0, int age, float prix, float heure, String img) {
        this.nom=text;
        this.prenom=text0;
this.age=age;
this.prix_h=prix;
this.nb_h=heure;
this.image=img;//To change body of generated methods, choose Tools | Templates.
    }

    public Personnel(int id, String nom, String prenom, int age, float nb_h, float prix_h, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.nb_h = nb_h;
        this.prix_h = prix_h;
        this.image = image;
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    

  


    public Personnel(String nom, String prenom, int age, float nb_h, float prix_h, String categorie, String image) {
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
