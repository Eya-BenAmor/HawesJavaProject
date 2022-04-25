/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mezen Bayounes
 */
public class Database {
   String url ="jdbc:mysql://localhost:3306/hawes";
     String login ="root";
     String pwd ="";
    static Connection myConnex;
    static Database myInstanceConnex;
    private Database() {
        
         try {
            myConnex = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion ok");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
  public static Database getInstanceConnex(){
      if(myInstanceConnex == null)
          myInstanceConnex = new Database();
      
        return myInstanceConnex ;  
  }
    
  
  public  Connection getConnection(){
      return myConnex;
  }

      
}
