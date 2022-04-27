/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Randonnee;
import Services.RandonneeService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AccueilController implements Initializable {

    @FXML
    private GridPane grid;
 private final List<Randonnee> rando = new ArrayList<>();
   RandonneeService randoservice = new RandonneeService();
    @FXML
    private AnchorPane accueil;
    
      public static final String ACCOUNT_SIDD= System.getenv("ACCOUNT_SID");
    public static final String AUTH_TOKENN = System.getenv("AUTH_TOKEN");

 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          // TODO
          
        
          
          
          
        rando.addAll(randoservice.afficherRandonnee());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < rando.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("RandoFront.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

                RandoFrontController rc = fxmlloader.getController();
              
                rc.AfficherRandoFront(rando.get(i));
                 
                if (column == 1 ) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
             
                }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }    

    @FXML
    private void randonneeFront(ActionEvent event) {
        
        grid.getChildren().clear();
        rando.addAll(randoservice.afficherRandonnee());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < rando.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("RandoFront.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                
                RandoFrontController rc = fxmlloader.getController();
                
                rc.AfficherRandoFront(rando.get(i));
                
                if (column == 1 ) {
                    column = 0;
                    row++;
                }
                
                grid.add(anchorPane, column++, row);
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
   

    @FXML
    private void competitionFront(ActionEvent event) {
    }

    @FXML
    private void categorieFront(ActionEvent event) {
    }

    @FXML
    private void platFront(ActionEvent event) {
        
        
 
    }

    @FXML
    private void reclamationFront(ActionEvent event) {
    }

    @FXML
    private void formationFront(ActionEvent event) {
    }

    @FXML
    private void profFront(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event)  {
     
        
    }

    @FXML
    private void cadeauFront(ActionEvent event) {
    }

    
}
