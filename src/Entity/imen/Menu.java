/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.imen;

import java.sql.Date;

/**
 *
 * @author Imen
 */
public class Menu {
    
    private int id;
    private Date date;
    private int idPlat;
    private int idDessert;
    private int idParent;
    private int idenfant;
private String nom;
private String prenom;
private String dessert;
private String plat;

    public String getNom() {
        return nom;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
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
       
    public Menu(int idPlat, int idDessert, int idParent, int idenfant) {
        this.idPlat = idPlat;
        this.idDessert = idDessert;
        this.idParent = idParent;
        this.idenfant = idenfant;
    }

    public Menu(Date date, int idPlat, int idDessert, int idParent, int idenfant) {
        this.date = date;
        this.idPlat = idPlat;
        this.idDessert = idDessert;
        this.idParent = idParent;
        this.idenfant = idenfant;
    }

    public Menu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(int idPlat) {
        this.idPlat = idPlat;
    }

    public int getIdDessert() {
        return idDessert;
    }

    public void setIdDessert(int idDessert) {
        this.idDessert = idDessert;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public int getIdenfant() {
        return idenfant;
    }

    public void setIdenfant(int idenfant) {
        this.idenfant = idenfant;
    }
    
    
           
    
    
}
