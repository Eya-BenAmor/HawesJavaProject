/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AccueilBackController implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane accueilback;
     String path = "C:\\Users\\MSI\\Documents\\GitHub\\HawesJavaProject\\music\\music.mp3";
     Media media = new Media(new File(path).toURI().toString());
     MediaPlayer mediaPlayer = new MediaPlayer(media);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
          FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("StatRando.fxml"));
       
            AnchorPane anchorPane = fxmlloader.load();
              grid.add(anchorPane, 1, 1);
             
        } catch (IOException ex) {
            Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML
    private void competionback(ActionEvent event) throws StripeException {
         grid.getChildren().clear();
         try {
          FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Competition.fxml"));
       
            AnchorPane anchorPane = fxmlloader.load();
              grid.add(anchorPane, 1, 1);
        } catch (IOException ex) {
            Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void categback(ActionEvent event) {
    }

    @FXML
    private void platcateg(ActionEvent event) {
    }


    @FXML
    private void formationback(ActionEvent event) {
    }

    @FXML
    private void userback(ActionEvent event) {
    }

    @FXML
    private void randonneeback(ActionEvent event) {
        grid.getChildren().clear();
         try {
          FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Rando.fxml"));
       
            AnchorPane anchorPane = fxmlloader.load();
              grid.add(anchorPane, 1, 1);
        } catch (IOException ex) {
            Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void cadeauback(ActionEvent event) {
         grid.getChildren().clear();
         try {
          FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Cadeau.fxml"));
       
            AnchorPane anchorPane = fxmlloader.load();
              grid.add(anchorPane, 1, 1);
        } catch (IOException ex) {
            Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    @FXML
    private void partiback(ActionEvent event) {
        grid.getChildren().clear();
         try {
          FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Participant.fxml"));
       
            AnchorPane anchorPane = fxmlloader.load();
              grid.add(anchorPane, 1, 1);
        } catch (IOException ex) {
            Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void partiformationback(ActionEvent event) {
       
    }

    @FXML
    private void reclamationback(ActionEvent event) {
         grid.getChildren().clear();
         try {
          FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Reponse.fxml"));
       
            AnchorPane anchorPane = fxmlloader.load();
              grid.add(anchorPane, 1, 1);
             
        } catch (IOException ex) {
            Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    

    @FXML
    private void stopMusic(MouseEvent event) {
         mediaPlayer.pause();
                Image img = new Image("/logo.png");
            Notifications notificationBuilder = Notifications.create()
                    .title("Musique")
                    .text("      Musique Arrêtée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();}
    

    @FXML
    private void playMusic(MouseEvent event) {
           mediaPlayer.play();
        Image img = new Image("/logo.png");
            Notifications notificationBuilder = Notifications.create()
                    .title("Musique")
                    .text("      Musique Jouée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
    }

    @FXML
    private void stat(ActionEvent event) {
         try {
          FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("StatRando.fxml"));
       
            AnchorPane anchorPane = fxmlloader.load();
              grid.add(anchorPane, 1, 1);
             
        } catch (IOException ex) {
            Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
