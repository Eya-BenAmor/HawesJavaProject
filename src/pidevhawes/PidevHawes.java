/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhawes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pidev.Entities.Formation;
import pidev.Entities.ParticForm;
import pidev.Services.ServiceFormation;
import pidev.Services.ServiceParticForm;
import pidev.Utils.DataSource;

/**
 *
 * @author seifi
 */
public class PidevHawes extends Application {
    
    
    
    @Override
    public void start(Stage stage) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("ParticForm.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* DataSource ds1=new DataSource();
        
        ServiceFormation serv = new ServiceFormation();
        List<Formation> s = serv.getAllForm();
        
        s.forEach(System.out::println);
        
        
        Formation f=new Formation("test", "test", "2", "test","test","test");
        Formation f2=new Formation(75,"2", "2", "2", "test2","test2","tes2t");
        // serv.ajouterForm(f);
        
        // serv.deleteForm(74);
        //serv.updateForm(f2);
    
        ServiceParticForm ser = new ServiceParticForm();
        List<ParticForm> s1 = ser.getAllParticForm();
        
        s1.forEach(System.out::println);
        */
        
        
            launch(args);
        
        
    }
    
}
