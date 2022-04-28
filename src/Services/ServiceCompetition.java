package Services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entites.Cadeau;
import Entites.Competition;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import utils.Datasource;
/**
 *
 * @author Mezen Bayounes
 */
public class ServiceCompetition {
    
    
  private PreparedStatement pst;
    private Statement ste ;
    private Connection connection;
    private ResultSet rs;
    public ServiceCompetition() {
        connection =Datasource.getInstance().getConnection();
      
    }
    
    
    //
    
    
    //
    public ObservableList<Competition> getAllComp(){
   
String requete="select * from competition";
     
     ObservableList <Competition> list=FXCollections.observableArrayList();
        try {
            ste = connection.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) { 
                list.add(new Competition(rs.getInt("id"),rs.getString("Nom"),rs.getInt("distance"),rs.getInt("prix"),rs.getDate("date")) );              
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCadeau.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
      public void addCompetition(Competition c){
    String req="insert into competition (Nom,Distance,Prix,Date) values (?,?,?,?)";
        try {
            pst=connection.prepareStatement(req);
            java.sql.Date sqlDate=new java.sql.Date(c.getDate().getTime());
         
            pst.setString(1, c.getNom());
             pst.setInt(2,c.getDistance());
             pst.setInt(3,c.getPrix());
            pst.setDate(4,sqlDate);
          
          
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCompetition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public void deleteCompetition(String id){
        String req = "delete from competition where id = ?";
        try {
            pst=connection.prepareStatement(req);
           pst.setString(1, id);
            
            pst.executeUpdate();
          
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCompetition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
     public void updateCompetition(Competition c){
     String req="update competition set Nom = ? , Distance = ? , Prix = ? ,Date = ? where id = ?";
        try {
            pst=connection.prepareStatement(req);
            java.sql.Date sqlDate=new java.sql.Date(c.getDate().getTime());
         
            pst.setString(1, c.getNom());
             pst.setInt(2,c.getDistance());
              pst.setInt(3,c.getPrix());
              pst.setDate(4,sqlDate);
             
                pst.setInt(5,c.getId());
      
          
          
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCompetition.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    
    MediaPlayer mediaPlayer;
	public void music() {
		String s = "C:\\Users\\MSI\\Documents\\GitHub\\HawesJavaProject\\music\\notif.mp3";
		Media h = new Media(Paths.get(s).toUri().toString());
		mediaPlayer =new MediaPlayer(h);
		mediaPlayer.play();}
    
    
}
