/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author MSI
 */
public class Participant {
     private int id;
     private int age,id_randonnee,id_user;
    private String tel,classe,maladie;

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }

    public String getClasse() {
        return classe;
    }

    public String getMaladie() {
        return maladie;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getId_randonnee() {
        return id_randonnee;
    }

    public int getId_user() {
        return id_user;
    }

    public String getTel() {
        return tel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId_randonnee(int id_randonnee) {
        this.id_randonnee = id_randonnee;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Participant() {
    }

    public Participant(int age, int id_randonnee, int id_user, String tel) {
        this.age = age;
        this.id_randonnee = id_randonnee;
        this.id_user = id_user;
        this.tel = tel;
    }

    public Participant(int id,int age, String tel , String maladie, String classe,int id_randonnee, int id_user ) {
        this.age = age;
        this.id_randonnee = id_randonnee;
        this.id_user = id_user;
        this.tel = tel;
        this.classe = classe;
        this.maladie = maladie;
         this.id = id;
    }

    @Override
    public String toString() {
        return "Participant{" + "id=" + id + ", age=" + age + ", id_randonnee=" + id_randonnee + ", id_user=" + id_user + ", tel=" + tel + ", classe=" + classe + ", maladie=" + maladie + '}';
    }

   

}
