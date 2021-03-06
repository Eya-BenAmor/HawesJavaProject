package Services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entities.Randonnee;
import Entities.Reclamation;
import Entities.Reponse;
import utils.Datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author EYA
 */
public class ReponseService {
   private PreparedStatement pst;
    private Statement ste;
    private Statement steR;
    private ResultSet rs;

    private Connection con;
  

 

    private Connection connection;
    public ReponseService() {
        connection=Datasource.getInstance().getConnection();
    }
    public void addReponse(Reponse r){
        String req="insert into reponse (text,id_reclamation,nom_rec) values (?,?,?)";
        try {
            pst=connection.prepareStatement(req);
         
            pst.setString(1, r.getText());
            pst.setInt(2,r.getId_reclamation());
            pst.setString(3, r.getNom_rec());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.ALL.SEVERE, null, ex);
        }
    }
      public ObservableList<Reponse> afficherReponse(Reponse r){
         String req="select * from reponse ";   
         System.out.println(req);
            ObservableList <Reponse> list=FXCollections.observableArrayList();
         try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
                list.add(new Reponse(rs.getInt("id"),rs.getString("text"),rs.getInt("id_reclamation"),rs.getString("nom_rec")));
            }
        } catch (SQLException ex) {
             Logger.getLogger(Reponse.class.getName()).log(Level.SEVERE, null, ex);
        }   
         return list;
    }
    public ObservableList<Reponse> afficherReponse(int id_reclamtion){
         String req="select * from reponse where id_reclamation = "+id_reclamtion+"";   
         System.out.println(req);
            ObservableList <Reponse> list=FXCollections.observableArrayList();
         try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
                list.add(new Reponse(rs.getInt("id"),rs.getString("text"),rs.getInt("id_reclamation"),rs.getString("nom_rec")));
            }
        } catch (SQLException ex) {
             Logger.getLogger(Reponse.class.getName()).log(Level.SEVERE, null, ex);
        }   
         return list;
    }
    
     public ObservableList<Reponse> getReponses(){
   
String requete="select * from reponse";
     
     ObservableList <Reponse> list=FXCollections.observableArrayList();
        try {
            ste = connection.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) { 
                list.add(new Reponse(rs.getInt("id"),rs.getString("text"),rs.getInt("id_reclamation"),rs.getString("nom_rec")) );              
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public void deleteReponse(String id){
        String req = "delete from reponse where id = ?";
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, id);
            
            pst.executeUpdate();
          
        } catch (SQLException ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
          public void updateReponse(Reponse r){
              
            String req="update reponse set text = ?  where id = ?";
    try {
            pst=connection.prepareStatement(req);
         
            pst.setString(1, r.getText());
            pst.setInt(2,r.getId());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.ALL.SEVERE, null, ex);
        }
        
    }
   
    public void deleteReclamation(String id){
        String req = "delete from reclamation where id = ?";
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, id);
            
            pst.executeUpdate();
          
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
