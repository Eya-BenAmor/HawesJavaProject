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
                list.add(new Cadeau(rs.getString("nom"),rs.getString("categorie_cadeau"),rs.getString("description_cadeau"),rs.getInt("competition_id")) );              
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCadeau.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    
        public void addCadeau(Cadeau cr){
    String requete="insert into cadeau (categorie_cadeau,description_cadeau,nom,competition_id) values('"+cr.getCategorie()+"','"+cr.getDescription()+"','"+cr.getNom()+"','"+cr.getCompetition()+"')";
    try {
    ste = connexion.createStatement();
    ste.executeUpdate(requete);
    } catch (SQLException ex) {
    Logger.getLogger(ServiceCadeau.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
       
    
    
    
   public void deleteCadeau(String id){
        String req = "delete from cadeau where nom = ?";
        try {
            pst=connexion.prepareStatement(req);
           pst.setString(1, id);
            
            pst.executeUpdate();
          
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCadeau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void updateCadeau(Cadeau cr){
      String requete="UPDATE cadeau SET nom = '"+cr.getNom()+"' , competition_id ='"+cr.getCompetition()+"' , description_cadeau ='"+cr.getDescription()+"', categorie_cadeau ='"+cr.getCategorie()+"' where nom='" + cr.getNom() + "' ";
       try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
    
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCadeau.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    
    
    
    
    }
    
    
 
    
    
    
