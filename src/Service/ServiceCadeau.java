/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entites.Cadeau;
import Entites.Competition;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.Database;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



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
        connexion = Database.getInstanceConnex().getConnection();
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
    
    
 
    
    
    
