/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Competition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Mezen Bayounes
 */
public class CompetitionFrontController implements Initializable {

    @FXML
    private Label nomL;
    @FXML
    private Label distanceL;
    @FXML
    private Label prixL;
    private Label dateL;
    @FXML
    private TextField tfId;
    @FXML
    private Label nomL1;
    @FXML
    private Label distanceL1;
    @FXML
    private Label prixL1;
   
    private Competition comp;
    @FXML
    private Label datee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
     public void AfficherCompetitionFront(Competition c) {
        comp = c;
        nomL.setText(c.getNom());
        distanceL.setText(String.valueOf(c.getDistance()));
        datee.setText(String.valueOf(c.getDate()));
        prixL.setText(String.valueOf(c.getPrix()));
      
    }
    
}
