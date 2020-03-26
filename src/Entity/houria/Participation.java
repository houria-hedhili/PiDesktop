/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.houria;

import Entity.user.user;

/**
 *
 * @author LENOVO
 */
public class Participation {

    private int id_participation;
    private int id_user;
    private user user; 
    private int id_event;    

    public Participation(user user, int id_event) {
        this.user = user;
        this.id_event = id_event;
    }

    public Participation(int id_user) {
        this.id_user = id_user;
    }

    public Participation(user user) {
        this.user = user;
    }

    public int getId_participation() {
        return id_participation;
    }

    public void setId_participation(int id_participation) {
        this.id_participation = id_participation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }
    
}
