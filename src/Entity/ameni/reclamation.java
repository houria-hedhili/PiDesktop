/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.ameni;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author User
 */
public class reclamation {
    private int id;
    private Date date;
    private String etat;
    private String description;
    private String categorieReclamation;
    private int idParent;
    private String nom;
    private int idCategorie;

    
    public reclamation(int id, Date date, String etat, String description, int idCategorie, String nom) {
        this.id = id;
        this.date = date;
        this.etat = etat;
        this.description = description;
        this.nom = nom;
        this.idCategorie=idCategorie;
    }

  
    
    public reclamation(String description,int idCategorie,String etat) {
        this.description = description;
        this.idParent = 1;
        this.idCategorie = idCategorie;
        this.etat=etat;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
//nom winou ?f categorie nom mte33 reclamation houa esm el categorie?esm l categorie
    public reclamation() {
    }

    public reclamation(Date date, String etat, String description, String categorieReclamation, int idParent) {
        this.date = date;
        this.etat = etat;
        this.description = description;
        this.categorieReclamation = categorieReclamation;
        this.idParent = idParent;
    }

    public reclamation(int id, Date date, String etat, String description, String categorieReclamation, int idParent) {
        this.id = id;
        this.date = date;
        this.etat = etat;
        this.description = description;
        this.categorieReclamation = categorieReclamation;
        this.idParent = idParent;
    }

    public reclamation(LocalDate value, String text, String text0, reclamation value0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorieReclamation() {
        return categorieReclamation;
    }

    public void setCategorieReclamation(String categorieReclamation) {
        this.categorieReclamation = categorieReclamation;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", date=" + date + ", etat=" + etat + ", description=" + description + ", categorieReclamation=" + categorieReclamation + ", idParent=" + idParent + '}';
    }
    
    
    
}
