/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author MSI
 */
public class Randonnee {

 private int id;
     private float prix;
    private String nom_rando,description,destination,duree_rando,categorie_rando;
    private Date date_rando;
    private float note;

    public Randonnee(int id) {
        this.id = id;
    }

    public Randonnee(String destination) {
        this.destination = destination;
    }

    
public Randonnee() {
    }

    public Randonnee(int id,String nom_rando, String destination, String description, String categorie_rando, Date date_rando , String duree_rando,float prix) {
        this.prix = prix;
        this.id = id;
        this.nom_rando = nom_rando;
        this.description = description;
        this.destination = destination;
        this.duree_rando = duree_rando;
        this.categorie_rando = categorie_rando;
       this.date_rando = date_rando;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setNom_rando(String nom_rando) {
        this.nom_rando = nom_rando;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDuree_rando(String duree_rando) {
        this.duree_rando = duree_rando;
    }

    public void setCategorie_rando(String categorie_rando) {
        this.categorie_rando = categorie_rando;
    }

    public void setDate_rando(Date date_rando) {
        this.date_rando = date_rando;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public float getPrix() {
        return prix;
    }

    public String getNom_rando() {
        return nom_rando;
    }

    public String getDescription() {
        return description;
    }

    public String getDestination() {
        return destination;
    }

    public String getDuree_rando() {
        return duree_rando;
    }

    public String getCategorie_rando() {
        return categorie_rando;
    }

    public Date getDate_rando() {
        return date_rando;
    }

    public float getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "Randonnee{" + "id=" + id + ", prix=" + prix + ", nom_rando=" + nom_rando + ", description=" + description + ", destination=" + destination + ", duree_rando=" + duree_rando + ", categorie_rando=" + categorie_rando + ", date_rando=" + date_rando + '}';
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }Randonnee other = (Randonnee) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom_rando, other.nom_rando)) {
            return false;
        }
       
        return true;
    }

  
   
   
}
