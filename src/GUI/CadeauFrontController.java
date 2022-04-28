/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Cadeau;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mezen Bayounes
 */
public class CadeauFrontController implements Initializable {

    @FXML
    private Label nomL;
    @FXML
    private Label descriptionL;
    @FXML
    private Label CompetitionL;
    @FXML
    private TextField tfId;
    @FXML
    private Label nomL1;
    @FXML
    private Label categorieL1;
    @FXML
    private Label descriptionL1;
    @FXML
    private Label competitionL1;
private Cadeau cad;
    @FXML
    private Label categorieL;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void AfficherCadeauFront(Cadeau c) {
        cad = c;
        nomL.setText(c.getNom());
        descriptionL.setText(String.valueOf(c.getDescription()));
        categorieL.setText(String.valueOf(c.getCategorie()));
        CompetitionL.setText(String.valueOf(c.getCompetition()));
      
    }
    
    
}
