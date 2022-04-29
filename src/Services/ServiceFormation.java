/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Entities.Formation;
import utils.Datasource;

/**
 *
 * @author seifi
 */
public class ServiceFormation{
    private PreparedStatement pst;
    private Statement ste ;
    private Connection connection;
    private ResultSet rs;
    
    
    
     public ServiceFormation() {connection=Datasource.getInstance().getConnection();;
     }
     
     
    
public ObservableList<Formation> getAllForm(){
     ObservableList<Formation> list = FXCollections.observableArrayList();
     String requete="select * from formation";
     Statement st;
     ResultSet rs;
   
        try {
            ste = connection.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) { 
                list.add(new Formation(rs.getString("nomeq"),rs.getString("domaine"),rs.getString("duree"),rs.getString("nomform")) );              
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


     //affichage
/*public List<Formation> readAll() throws SQLException {
List<Formation> arr=new ArrayList<>();
ste=connection.createStatement();
ResultSet rs=ste.executeQuery("select * from formation");
while (rs.next()) {

String nomeq=rs.getString("nomeq");
String domaine=rs.getString("domaine");
String duree=rs.getString("duree");
String nomform=rs.getString("nomform");
Formation ct= new Formation (nomeq,domaine,duree,nomform);
arr.add(ct);
}

return arr;
}

*/
            
     public int Stats(String par,String field) {
   String sql = "select count(*) from formation where "+field+""+ "=" +"'"+par+"'";
   
  
      try {
            
    
           ste = connection.createStatement();
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
     
 
     

     //ajout
     public void ajouterForm(Formation t)  {
     String requeteInsert = "INSERT INTO formation ( nomeq , domaine , duree, nomform,date,plan) VALUES (?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(requeteInsert);
            pst.setString(1,t.getNomeq());
            pst.setString(2,t.getDomaine());
            pst.setString(3,t.getDuree());
            pst.setString(4,t.getNomform());
            pst.setString(5,t.getDate());
            pst.setString(6,t.getPlan());
            pst.executeUpdate();
            System.out.println("formation ajoute!!");
        } 
       
        catch (SQLException ex) {
           Logger.getLogger(Formation.class.getName()).log(Level.SEVERE, null, ex);
        }
     }  
     //delete
      public void deleteForm(String id) {
          
        String requete = "DELETE FROM formation WHERE nomform= '"+id+"'";
        
           try {
            ste = connection.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
        }  }

   //miseajour
  public void updateForm(Formation t, String  s) {
     
        
          String requete="UPDATE formation SET nomeq = '"+t.getNomeq()+"' , domaine ='"+t.getDomaine()+"', duree ='"+t.getDuree()+"', nomform ='"+t.getNomform()+"', date ='"+t.getDate()+"', plan ='"+t.getPlan() +"' where nomform='" + s + "' ";
       try {
            ste = connection.createStatement();
            ste.executeUpdate(requete);
    
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

  
    
}


