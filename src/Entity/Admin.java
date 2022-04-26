/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Yahya
 */
public class Admin {
    private int id;
    private String nom,prenom,email,mdp,confirm_mdp,cin;

    public Admin() {
    }

    public Admin(int id, String nom, String prenom, String email, String mdp, String confirm_mdp, String cin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.confirm_mdp = confirm_mdp;
        this.cin = cin;
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

    public String getConfirm_mdp() {
        return confirm_mdp;
    }

    public String getCin() {
        return cin;
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

    public void setConfirm_mdp(String confirm_mdp) {
        this.confirm_mdp = confirm_mdp;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
    //Affichage

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", cin=" + cin + ", mdp=" + mdp + ", confirm_mdp=" + confirm_mdp + '}';
    }
}