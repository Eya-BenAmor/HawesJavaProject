/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import Entities.ParticForm;
import utils.Datasource;

/**
 *
 * @author seifi
 */
public class ServiceParticForm {
    
  
    private PreparedStatement pst;
    private Statement ste ;
    private Connection connection;
    private ResultSet rs;
    
    
    public ServiceParticForm() {
        
        connection=Datasource.getInstance().getConnection();
     }
   
     
     
       //affichage
public ObservableList<ParticForm> getAllParticForm(){
     ObservableList<ParticForm> partL = FXCollections.observableArrayList();
     String requete="select * from partic_form";
     Statement st;
     ResultSet rs;
        try {
            ste = connection.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) { 
                partL.add(new ParticForm(rs.getString("nom"),rs.getString("prenom"),rs.getInt("age"),rs.getInt("Numero"),rs.getInt("id_formation_id")) );              
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return partL;
    }


  

            
        

     //ajout
     public void ajouterPartic(ParticForm t)  {
     String requeteInsert = "INSERT INTO partic_form ( nom, prenom , age, exp,so_domaine,numero,id_formation_id) VALUES (?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(requeteInsert);
            pst.setString(1,t.getNom());
            pst.setString(2,t.getPrenom());
            pst.setInt(3,t.getAge());
            pst.setString(4,t.getExp());
            pst.setString(5,t.getSo_domaine());
            pst.setInt(6,t.getNumero());
            pst.setInt(7,t.getId_formation_id());
            pst.executeUpdate();
            System.out.println("Participant ajoute!!");
        } 
       
        catch (SQLException ex) {
           Logger.getLogger(ParticForm.class.getName()).log(Level.SEVERE, null, ex);
        }
     }  
     //delete
      public void deletePartic(String id) {
        String requete = "DELETE FROM partic_form WHERE nom ='"+id+"'";
        
           try {
            ste = connection.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticForm.class.getName()).log(Level.SEVERE, null, ex);
        }  }

   //miseajour
  public void updatePartic(ParticForm t,String s) {
     
        
          String requete="UPDATE partic_form SET nom = '"+t.getNom()+"' , prenom ='"+t.getPrenom()+"', age ='"+t.getAge()+"', exp ='"+t.getExp()+"', Numero ='"+t.getNumero()+"', id_formation_id ='"+t.getId_formation_id() +"' where nom='" + s + "' ";
       try {
            ste = connection.createStatement();
            ste.executeUpdate(requete);
    
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

  
    
}

