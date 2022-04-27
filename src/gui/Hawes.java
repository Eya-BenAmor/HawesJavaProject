/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Cadeau;
import Entites.Competition;
import Service.ServiceCadeau;
import Service.ServiceCompetition;
import Utils.Database;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author Mezen Bayounes
 */
public class Hawes extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
         
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    MediaPlayer mediaPlayer;
	public void music() {
		String s = "C:/Users/Mezen Bayounes/Desktop/home.mp3";
		Media h = new Media(Paths.get(s).toUri().toString());
		mediaPlayer =new MediaPlayer(h);
		mediaPlayer.play();}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            launch(args);
       
       
    }
    
}
