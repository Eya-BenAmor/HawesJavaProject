/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Randonnee;
import Entities.Reponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class RepFrontController implements Initializable {

    @FXML
    private Label nomL;
    @FXML
    private Label destinationL;
    @FXML
    private TextField tfId;
    @FXML
    private Label nomL1;
    @FXML
    private Label destinationL1;
private Reponse rep;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
     public void AfficherRepFront(Reponse r) {
         rep = r;
        nomL.setText(r.getText());
        destinationL.setText(r.getNom_rec());
       
    }    
    
}
