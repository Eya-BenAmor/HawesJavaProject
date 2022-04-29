package Entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author msi
 */
public class Plat {
    private int id;
 
     private String description;
     private String nom;
     private int id_categorie;
     private int prix;

    public Plat(String nom, String description, int prix, int id_categorie) {
        this.id_categorie = id_categorie;
        this.description= description;
        this.nom = nom;
        this.prix = prix;
    }
     public Plat(String nom, String description, int prix) {
      
        this.description= description;
        this.nom = nom;
        this.prix = prix;
    }

    public Plat(int id,String description, String nom, int prix,int id_categorie) {
        this.id = id;
   
        this.description = description;
        this.nom = nom;
        this.prix = prix;
        this.id_categorie = id_categorie;
    }


    
 public int getPrix() {
        return prix;
    }
   

    public Plat(String nom, int prix, String description) {
       this.prix = prix;
        this.description = description;
        this.nom = nom;
    }

    public Plat() {
       
    }

    public Plat(int id, int prix, String description) {
        this.id = id;
        this.prix = prix;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public void setCategorie(int prix) {
        this.prix = prix;
    }

    public Plat(int id,String nom,int prix, String description) {
        this.id = id;
        this.prix = prix;
        this.description = description;
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description= description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    @Override
    public String toString() {
        return "Plat{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", prix=" + prix + ", id_categorie=" + id_categorie + '}';
    }
    
    
    
    
    
}
