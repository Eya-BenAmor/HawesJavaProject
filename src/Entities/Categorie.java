package Entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;

/**
 *
 * @author msi
 */
public class Categorie {
    private int id;
    private String Nom;
    private String description;
    

    public Categorie(int id, String Nom) {
        this.id = id;
        this.Nom = Nom;
        
    }

    public Categorie(String Nom, String description ) {
       
        this.Nom = Nom;
        this.description = description;
        
    }

   


    public Categorie(int id, String Nom, String description) {
        this.id = id;
        this.Nom = Nom;
        this.description = description;
       
    }


   

  

    


   

  

   
  



    public Categorie() {
       
    }

    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", Nom=" + Nom + ", description=" + description + '}';
    }
}