/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.ameni;

/**
 *
 * @author User
 */
public class categorieReclamation {
    public String nom;
    private String description;
    private int ref;

    public categorieReclamation() {
    }

    public categorieReclamation(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public categorieReclamation(String nom, String description, int ref) {
        this.nom = nom;
        this.description = description;
        this.ref = ref;
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

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "categorieReclamation{" + "nom=" + nom + ", description=" + description + ", ref=" + ref + '}';
    }
    
    
}
