/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Entities;

/**
 *
 * @author seifi
 */
public class ParticForm {
    private int id,age,Numero,id_formation_id;
    private String nom,prenom,exp,so_domaine,so_ass;

    public ParticForm(int id, int age, int Numero, String nom, String prenom, String exp, String so_domaine, String so_ass) {
        this.id = id;
        this.age = age;
        this.Numero = Numero;
        this.nom = nom;
        this.prenom = prenom;
        this.exp = exp;
        this.so_domaine = so_domaine;
        this.so_ass = so_ass;
    }
    
    
    public ParticForm( int age, int Numero, String nom, String prenom, String exp, String so_domaine, String so_ass) {
        this.age = age;
        this.Numero = Numero;
        this.nom = nom;
        this.prenom = prenom;
        this.exp = exp;
        this.so_domaine = so_domaine;
        this.so_ass = so_ass;
    }

    public ParticForm() {
       
    }

    public ParticForm(String nom, String prenom, int age, int Numero) {
        this.age = age;
         this.nom = nom;
        this.prenom = prenom;
        this.Numero = Numero;
    }

    public ParticForm(String nom, String prenom, int age, int Numero,int id_formation_id ) {
         this.age = age;
         this.nom = nom;
        this.prenom = prenom;
        this.Numero = Numero;
        this.id_formation_id = id_formation_id;
        
    }

    public void setId_formation_id(int id_formation_id) {
        this.id_formation_id = id_formation_id;
    }

    public int getId_formation_id() {
        return id_formation_id;
    }

    
    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getNumero() {
        return Numero;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getExp() {
        return exp;
    }

    public String getSo_domaine() {
        return so_domaine;
    }

    public String getSo_ass() {
        return so_ass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public void setSo_domaine(String so_domaine) {
        this.so_domaine = so_domaine;
    }

    public void setSo_ass(String so_ass) {
        this.so_ass = so_ass;
    }

    @Override
    public String toString() {
        return "ParticForm{" + "age=" + age + ", Numero=" + Numero + ", nom=" + nom + ", prenom=" + prenom + ", exp=" + exp + ", so_domaine=" + so_domaine + ", so_ass=" + so_ass + ", idformation"+id_formation_id + '}';
    }
    
}

