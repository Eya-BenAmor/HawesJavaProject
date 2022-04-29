package Services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entities.Reclamation;
import utils.Datasource;

import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author EYA
 */
public class ReclamationService {
  private PreparedStatement pst;
    private Statement ste ;
    private Connection connection;
    private ResultSet rs;
    public ReclamationService() {
        
        connection=Datasource.getInstance().getConnection();
    }
    public ObservableList <Reclamation> readAll(String client){
        
        Reclamation rec=null;
            String req="select* from reclamation where client = "+client+"";    
            
            ObservableList <Reclamation> list=FXCollections.observableArrayList();
            
         try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
                rec = new Reclamation(rs.getInt("id"),rs.getString("nom"),rs.getString("description"), rs.getDate("date_reclamation"), rs.getString("image"),rs.getString("client") );
                              
                File file = new File(rec.getImage());
                Image image = new Image(file.toURI().toString());
                
                ImageView imageView =new ImageView(image);
                imageView.setImage(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);

                rec.setImg(imageView);                
                list.add(rec);
            }
        } catch (SQLException ex) {
             Logger.getLogger(Reclamation.class.getName()).log(Level.SEVERE, null, ex);
        }   
            return list;
    }
    public void ajouterReclamation(Reclamation r){
        String req="insert into reclamation (nom,description,image,date_reclamation,client) values (?,?,?,?,?)";
        try {
            pst=connection.prepareStatement(req);
            java.sql.Date sqlDate=new java.sql.Date(r.getDate_reclamation().getTime());
            pst.setString(1, r.getNom());
            pst.setString(2, r.getDescription());
            pst.setString(3,r.getImage());
            pst.setDate(4,sqlDate);
            pst.setString(5,r.getClient());
          
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
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
    public ObservableList <Reclamation> readAllForAdmin(){
        Reclamation rec = null;
            String req="select* from reclamation ";          
            ObservableList <Reclamation> list=FXCollections.observableArrayList();
         try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
                
                 rec = new Reclamation(rs.getInt("id"),rs.getString("nom"),rs.getString("description"), rs.getDate("date_reclamation"), rs.getString("image"),rs.getString("client") );
                              
                File file = new File(rec.getImage());
                Image image = new Image(file.toURI().toString());
                
                ImageView imageView =new ImageView(image);
                imageView.setImage(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);

                rec.setImg(imageView);                
                list.add(rec);
            }
        } catch (SQLException ex) {
             Logger.getLogger(Reclamation.class.getName()).log(Level.SEVERE, null, ex);
        }   
            return list;
    }
    
    
     public ArrayList<Reclamation> getAll() {

        ArrayList<Reclamation> ListeReclamation = new ArrayList<>();
        String req = "SELECT * FROM reclamation";
        try {
 ste=connection.createStatement();
            rs=ste.executeQuery(req);

            while (rs.next()) {
                Reclamation a = new Reclamation();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                a.setDescription(rs.getString("Description"));
  

                ListeReclamation.add(a);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
        return ListeReclamation;
    }
}
