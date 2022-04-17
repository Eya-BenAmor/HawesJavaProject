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
public class Formation {
    
   private int id;
    private String nomeq,domaine,nomform,plan;
    private String date,duree;

    public Formation(int id, String duree, String nomeq, String domaine, String nomform, String plan, String date) {
        this.id = id;
        this.duree = duree;
        this.nomeq = nomeq;
        this.domaine = domaine;
        this.nomform = nomform;
        this.plan = plan;
        this.date = date;
    }

   

    public Formation(String nomeq, String domaine, String duree,String nomform, String  plan ,String date) {
       
        this.nomeq=nomeq;
        this.domaine=domaine;
        this.duree=duree;
        this.nomform=nomform;
        this.date=date;
        this.plan=plan;
            
    
        
    }
    
    public Formation(String nomeq, String domaine, String duree,String nomform) {
       
        this.nomeq=nomeq;
        this.domaine=domaine;
        this.duree=duree;
        this.nomform=nomform;

    }
    

    public Formation() {
       
    }

    public int getId() {
        return id;
    }

    public String getDuree() {
        return duree;
    }

    public String getNomeq() {
        return nomeq;
    }

    public String getDomaine() {
        return domaine;
    }

    public String getNomform() {
        return nomform;
    }

    public String getPlan() {
        return plan;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setNomeq(String nomeq) {
        this.nomeq = nomeq;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public void setNomform(String nomform) {
        this.nomform = nomform;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Formation{" + id + "duree=" + duree + ", nomeq=" + nomeq + ", domaine=" + domaine + ", nomform=" + nomform + ", plan=" + plan + ", date=" + date + '}';
    }
    
}
