package Entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author EYA
 */
public class Reclamation {
       private int id;
       private String nom;
         private  String description;
    private Date date_reclamation;
    private String image;
    private int id_client;
    private ImageView img;

       private String client;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getNom() {
        return nom;
    }

    public Reclamation() {
    }

    public Reclamation(String nom, String description, Date date_reclamation, String image, int id_client) {
        this.nom = nom;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.image = image;
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", date_reclamation=" + date_reclamation + ", image=" + image + ", id_client=" + id_client + ", img=" + img + '}';
    }

    public Reclamation(String nom, String description, Date date_reclamation, String image, int id_client, ImageView img) {
        this.nom = nom;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.image = image;
        this.id_client = id_client;
        this.img = img;
    }

    public Reclamation(int id, String nom, String description, Date date_reclamation, String image, int id_client) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.image = image;
        this.id_client = id_client;
    }

    public Reclamation(String nom, String description, Date date_reclamation, String image, String client) {
        this.nom = nom;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.image = image;
        this.client = client;
    }

    public Reclamation(int id, String nom, String description, Date date_reclamation, String image, String client) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.image = image;
        this.client = client;
    }

  

    public Reclamation(int id, String nom, String description, Date date_reclamation, String image, int id_client, ImageView img) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.image = image;
        this.id_client = id_client;
        this.img = img;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
  
    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public Reclamation(int id, String description, Date date_reclamation, String image, int id_client) {
        this.id = id;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.image = image;
        this.id_client = id_client;
    }

    public Reclamation(String description, Date date_reclamation, String image, int id_client) {
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.image = image;
        this.id_client = id_client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_reclamation() {
        return date_reclamation;
    }

    public void setDate_reclamation(Date date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    
    
}
