/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Datasource;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Mezen Bayounes
 */
public class ServiceCategorie {
    
    
      private Connection connexion;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs;

    /* public ServiceCategorie() {
    connexion = DataSource.getInstanceConnex().getConnection();
    }
    */
    public ServiceCategorie() {connexion=Datasource.getInstance().getConnection();
     }
     
    
     
    public ObservableList<Categorie> getAllCateg(){
    ObservableList <Categorie> list=FXCollections.observableArrayList();
    
String requete="select * from categorie";
       
      
        try {
            ste = connexion.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) { 
                list.add(new Categorie(rs.getString("Nom"),rs.getString("description")) );              
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePlat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
      public void addCategorie(Categorie cr){
    String requete="insert into Categorie (Nom,description) values('"+cr.getNom()+"','"+cr.getDescription()+"')";
    try {
    ste = connexion.createStatement();
    ste.executeUpdate(requete);
    } catch (SQLException ex) {
    Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    
    public void deleteCategorie(String id) {
     
         String requete = "DELETE FROM categorie WHERE nom ='"+id+"'";
        
           try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void updateCategorie(Categorie cr,String s){
      String requete="UPDATE categorie SET Nom = '"+cr.getNom()+"' , description ='"+cr.getDescription()+"' where nom='" + s + "' ";
       try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
    
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    
    
    
    
}
