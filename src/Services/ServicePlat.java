/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Categorie;
import Entities.Plat;
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
 * @author msi
 */
public class ServicePlat {

    public static Object getInstanceConnex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     private Connection connexion;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs;

    public ServicePlat() {
        connexion = Datasource.getInstance().getConnection();
    }
    
    
    public ObservableList<Plat> getAllForm(){
   ObservableList <Plat> list=FXCollections.observableArrayList();
String requete="select * from plat";
   
        try {
            ste = connexion.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) { 
                list.add(new Plat(rs.getString("nom"),rs.getString("description"),rs.getInt("prix"),rs.getInt("id_categorie_id")) );              
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePlat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    
        public void addPlat(Plat cr){
    String requete="insert into plat (nom,description,prix) values('"+cr.getNom()+"','"+cr.getDescription()+"','"+cr.getPrix()+"')";
    try {
    ste = connexion.createStatement();
    ste.executeUpdate(requete);
    } catch (SQLException ex) {
    Logger.getLogger(ServicePlat.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
       
    
    
    
    public void deletePlat(String id) {
     
         String requete = "DELETE FROM plat WHERE nom ='"+id+"'";
        
           try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePlat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void updatePlat(Plat cr,String s){
      String requete="UPDATE plat SET nom = '"+cr.getNom()+"' , description ='"+cr.getDescription()+"', prix ='"+cr.getPrix()+"' where nom='" + s + "' ";
       try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
    
        } catch (SQLException ex) {
            Logger.getLogger(ServicePlat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    
    
    
    
    }
    
    
 
    
    
    
