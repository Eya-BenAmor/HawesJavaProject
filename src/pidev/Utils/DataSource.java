/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author seifi
 */
public class DataSource {
    
    static DataSource instance;

     public static DataSource getInstance(){
        if(instance == null)
            instance = new DataSource();
       return instance;
    }
    
 private String url="jdbc:mysql://localhost:3306/hawes";
    private String login="root";
    private String pwd="";
    private Connection conn;
    
    public DataSource(){
        
        
        try {
            conn=DriverManager.getConnection(url,login,pwd);
        } catch (SQLException ex) {
            
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Connection getConnection() {
        return conn;
    }
    
}
