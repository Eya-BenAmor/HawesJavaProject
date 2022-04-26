/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Yahya
 */
public class Datasource {
    private String url="jdbc:mysql://localhost:3306/hawes";
    private String login="root";
    private String pwd="";
     private Connection conn;
    static Datasource myInstanceConnex;
    public Datasource() {
        
        try{
           conn = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion etablie");
            
      
     } catch (SQLException ex) {
            Logger.getLogger(Datasource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static Datasource getInstance(){
        if(myInstanceConnex == null)
            myInstanceConnex = new Datasource();
       return myInstanceConnex;
    }

    public Connection getConnection() {
        return conn;
    }
    
}