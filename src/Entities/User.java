/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Yahya
 */
public class User {
    private int id;
    private String nom,prenom,email,mdp,confirm_mdp;
   
       
//constructor
    
    public User(int id, String nom, String prenom, String email, String confirm_mdp, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.confirm_mdp = confirm_mdp;
    }

    public User(int id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    //..
    public User ()
    {
       nom="";
       prenom="";
       email="";
       mdp="";
       confirm_mdp="";       
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public String getConfirmMdp() {
        return confirm_mdp;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setConfirmMdp(String confirm_mdp) {
        this.confirm_mdp = confirm_mdp;
    }
    
    
    //Affichage

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", mdp=" + mdp + ", confirm_mdp=" + confirm_mdp + '}';
    }
       
}
