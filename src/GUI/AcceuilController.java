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
 * @author Mezen Bayounes
 */
public class AcceuilController implements Initializable {

    @FXML
    private GridPane grid;
 private final List<Competition> comp = new ArrayList<>();
   ServiceCompetition s= new ServiceCompetition();
    @FXML
    private AnchorPane accueil;
      @FXML
 private final List<Cadeau> cad = new ArrayList<>();
   ServiceCadeau sc= new ServiceCadeau();
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        comp.addAll(s.getAllComp());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < comp.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("CompetitionFront.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

               GUI.CompetitionFrontController rc = fxmlloader.getController();
              
                rc.AfficherCompetitionFront(comp.get(i));
                 
                if (column == 1 ) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
             
                }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        
        
        
        
        
        
        
        
        
         cad.addAll(sc.getAll());
        int columnn = 0;
        int roww = 1;
        try {
            for (int i = 0; i < comp.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("CadeauFront.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

               GUI.CadeauFrontController rc = fxmlloader.getController();
              
                rc.AfficherCadeauFront(cad.get(i));
                 
                if (columnn == 1 ) {
                    columnn = 0;
                    roww++;
                }

                grid.add(anchorPane, column++, row);
             
                }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        
        
    }    

    @FXML
    private void competitionFront(ActionEvent event) {
        grid.getChildren().clear();
        comp.addAll(s.getAllComp());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < comp.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("CompetitionFront.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                
                GUI.CompetitionFrontController rc = fxmlloader.getController();
                
                rc.AfficherCompetitionFront(comp.get(i));
                
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
    private void randonneeFront(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
    }

    @FXML
    private void cadeauFront(ActionEvent event) {
          grid.getChildren().clear();
        cad.addAll(sc.getAll());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < comp.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("CadeauFront.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                
                GUI.CadeauFrontController rc = fxmlloader.getController();
                
                rc.AfficherCadeauFront(cad.get(i));
                
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
    
}
