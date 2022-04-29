/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
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

     private Reponse rep;
  
    @FXML
    private TextField tfId;
  

    @FXML
    private Label noml;
    @FXML
    private Label descl;
    @FXML
    private Label noml1;
    @FXML
    private Label descL1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
     public void AfficherRepFront(Reponse r) {
         rep = r;
        noml.setText(r.getNom_rec());
       descl.setText(r.getText());
       
       
       
    }    
    
}
