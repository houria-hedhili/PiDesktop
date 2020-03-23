/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.wifek;

/**
 *
 * @author ASUS
 */
public class enfant {
    private int id;
    private String sexe;
    private String nom;
    private String prenom;
    private int age;
    private int id_Bus;
    private int idParent;

    public enfant(String sexe, String nom, String prenom, int age, int id_Bus, int idParent) {
        this.sexe = sexe;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.id_Bus = id_Bus;
        this.idParent = idParent;
    }

 
    public enfant(int id, String sexe, String nom, String prenom, int age, int id_Bus, int idParent) {
        this.id = id;
        this.sexe = sexe;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.id_Bus = id_Bus;
        this.idParent = idParent;
    }

   public enfant() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId_Bus() {
        return id_Bus;
    }

    public void setId_Bus(int id_Bus) {
        this.id_Bus = id_Bus;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    @Override
    public String toString() {
        return "enfant{" + "id=" + id + ", sexe=" + sexe + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", id_Bus=" + id_Bus + ", idParent=" + idParent + '}';
    }
    
}
