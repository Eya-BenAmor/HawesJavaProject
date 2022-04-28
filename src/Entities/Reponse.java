package Entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author EYA
 */
public class Reponse {
      private int id;
    private String text;
    private int id_reclamation;
  private String nom_rec ;

    public String getNom_rec() {
        return nom_rec;
    }

    public void setNom_rec(String nom_rec) {
        this.nom_rec = nom_rec;
    }

    public Reponse(String text, int id_reclamation, String nom_rec) {
        this.text = text;
        this.id_reclamation = id_reclamation;
        this.nom_rec = nom_rec;
    }

    public Reponse(int id, String text, int id_reclamation, String nom_rec) {
        this.id = id;
        this.text = text;
        this.id_reclamation = id_reclamation;
        this.nom_rec = nom_rec;
    }

    public Reponse(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Reponse(int id, String text, int id_reclamation) {
        this.id = id;
        this.text = text;
        this.id_reclamation = id_reclamation;
    }

    public Reponse(String text, int id_reclamation) {
        this.text = text;
        this.id_reclamation = id_reclamation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }
    
}
