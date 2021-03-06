/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Mezen Bayounes
 */
public class Competition {
    private int id;
    private String Nom;
    private int distance;
    private int prix;
    private Date date;

    public Competition(int id, String Nom, int prix, Date date) {
        this.id = id;
        this.Nom = Nom;
        this.prix = prix;
        this.date = date;
    }

    public Competition(String Nom, int distance, Date date, int prix) {
       
        this.Nom = Nom;
        this.distance = distance;
        this.prix = prix;
        this.date = date;
    }
  public Competition(String Nom, int distance,int prix, Date date) {
       
        this.Nom = Nom;
        this.distance = distance;
        this.prix = prix;
        this.date = date;
    }
  

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Competition(String Nom, int distance, int prix) {
    
        this.Nom = Nom;
        this.distance = distance;  
        this.prix =prix;
    }

    public Competition(int id, String Nom, int distance, int prix) {
        this.id = id;
        this.Nom = Nom;
        this.distance = distance;
        this.prix = prix;
    }

    public Competition(int id, String Nom, int distance, int prix, Date date) {
        this.id = id;
        this.Nom = Nom;
        this.distance = distance;
        this.prix = prix;
        this.date = date;
    }

   

  

    


   

  

   
    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Competition(String Nom, int distance) {
        this.Nom = Nom;
        this.distance = distance;
    }

    public Competition(int id, String Nom, int distance) {
        this.id = id;
        this.Nom = Nom;
        this.distance = distance;
    }

    public Competition() {
       
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Competition{" + "id=" + id + ", Nom=" + Nom + ", distance=" + distance + ", prix=" + prix + ", date=" + date + '}';
    }
    
    
    
    
    
    
    
    
    
}
