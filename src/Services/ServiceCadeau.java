package Services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entities.Cadeau;
import Entities.Competition;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import utils.Datasource;



/**  
 *
 * @author Mezen Bayounes
 */
public class ServiceCadeau {
     private Connection connexion;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs;
    
  
    


    public ServiceCadeau() {
        connexion = Datasource.getInstance().getConnection();
    }
    
    
    
     public int Stats(String par,String field) {
   String sql = "select count(*) from Cadeau where "+field+""+ "=" +"'"+par+"'";
   
  
      try {
            
    
           ste = connexion.createStatement();
           rs= ste.executeQuery(sql);

            int num = 0;
            while(rs.next()){
                num = (rs.getInt(1));
                return num;
 
            }
        } catch (SQLException ex) {
            
        }
        return 0 ;
    }
    
    
    public ObservableList<Cadeau> getAll(){
   
String requete="select * from cadeau";
 
       ObservableList <Cadeau> list=FXCollections.observableArrayList();
        try {
            ste = connexion.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) { 
                list.add(new Cadeau(rs.getInt("id"),rs.getString("nom"),rs.getString("categorie_cadeau"),rs.getString("description_cadeau"),rs.getInt("competition_id")) );              
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCadeau.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    
        public void addCadeau(Cadeau cr){
    String requete="insert into cadeau (nom,categorie_cadeau,description_cadeau,competition_id) values('"+cr.getNom()+"','"+cr.getCategorie()+"','"+cr.getDescription()+"','"+cr.getCompetition()+"')";
    try {
    ste = connexion.createStatement();
    ste.executeUpdate(requete);
    } catch (SQLException ex) {
    Logger.getLogger(ServiceCadeau.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
       
    
     MediaPlayer mediaPlayer;
	public void music() {
		String s = "C:\\Users\\MSI\\Documents\\GitHub\\HawesJavaProject\\music\\notif.mp3";
		Media h = new Media(Paths.get(s).toUri().toString());
		mediaPlayer =new MediaPlayer(h);
		mediaPlayer.play();}

    
   public void deleteCadeau(String id){
        String req = "delete from cadeau where id = ?";
        try {
            pst=connexion.prepareStatement(req);
           pst.setString(1, id);
            
            pst.executeUpdate();
          
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCadeau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void updateCadeau(Cadeau c){
      String req=" update cadeau set  competition_id = ?, categorie_cadeau = ? ,description_cadeau = ? ,nom = ?  where id = ?";
        try {
            pst=connexion.prepareStatement(req);
         
              pst.setInt(1,c.getCompetition());
             pst.setString(2,c.getCategorie());
              pst.setString(3,c.getDescription());
               pst.setString(4, c.getNom());
                pst.setInt(5,c.getId());
      
          
          
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCadeau.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    } 
    
    
    
    
    
    }
    
    
 
    
    
    
