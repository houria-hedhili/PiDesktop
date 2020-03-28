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
public class Bus {
    private int id;
    private String matricule;
    private int nbPlaces;
    private String ligne;



       public Bus( String matricule, int nbPlaces, String ligne) {
        this.matricule = matricule;
       this.nbPlaces = nbPlaces;
        this.ligne = ligne;
    }
       
      public Bus(int id, String matricule, int nbPlaces, String ligne/*,Button suppr,Button update*/) {
        this.id = id;
        this.matricule = matricule;
        this.nbPlaces = nbPlaces;
        this.ligne = ligne;/*
        this.suppr=suppr;
        this.update=update;*/
    }
    

    public Bus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }
    @Override
    public String toString() {
        return "Bus{" + "id=" + id + ", matricule=" + matricule + ", nbPlaces=" + nbPlaces + ", ligne=" + ligne + '}';
    }
    
    
}
