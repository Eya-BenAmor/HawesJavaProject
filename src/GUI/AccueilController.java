/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cadeau;
import Entities.Competition;
import Entities.Formation;
import Entities.Randonnee;
import Entities.Reponse;
import static GUI.LoginController.dashBStage;
import Services.RandonneeService;
import Services.ReponseService;
import Services.ServiceCadeau;
import Services.ServiceCompetition;
import Services.ServiceFormation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Navigation;


/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AccueilController implements Initializable {

    @FXML
    private GridPane grid;
 private final List<Randonnee> rando = new ArrayList<>();
  private final List<Reponse> rep = new ArrayList<>();
   RandonneeService randoservice = new RandonneeService();
      ReponseService repservice = new ReponseService();
      private final List<Competition> comp = new ArrayList<>();
   ServiceCompetition s= new ServiceCompetition();
   private final List<Cadeau> cad = new ArrayList<>();
   ServiceCadeau sc= new ServiceCadeau();
   private final List<Formation> form = new ArrayList<>();
   ServiceFormation sF= new ServiceFormation();
    @FXML
    private AnchorPane accueil;
    
      public static final String ACCOUNT_SIDD= System.getenv("ACCOUNT_SID");
    public static final String AUTH_TOKENN = System.getenv("AUTH_TOKEN");
    @FXML
    private Label connName;
    @FXML
    private Label nom;

 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          // TODO
          
       // nom.setText(LoginController.connectedUser.getPrenom());
          
          
          
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
         grid.getChildren().clear();
        rep.addAll(repservice.getReponses());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < rep.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("RepFront.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                
                RepFrontController rc = fxmlloader.getController();
                
                rc.AfficherRepFront(rep.get(i));
                
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
    private void formationFront(ActionEvent event) {
          grid.getChildren().clear();
        form.addAll(sF.getAllForm());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < form.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("FormationFront.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                
                GUI.FormationFrontController rc = fxmlloader.getController();
                
                rc.AfficherFormationFront(form.get(i));
                
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
    private void cadeauFront(ActionEvent event) {
          grid.getChildren().clear();
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

                grid.add(anchorPane, columnn++, roww);
             
                }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    @FXML
    private void chat(ActionEvent event) throws IOException {
           grid.getChildren().clear();
             try {
          FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("ClientChat.fxml"));
       
            AnchorPane anchorPane = fxmlloader.load();
              grid.add(anchorPane, 1, 1);
             
        } catch (IOException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reclam(ActionEvent event) throws IOException {
          grid.getChildren().clear();
             try {
          FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Reclamation.fxml"));
       
            AnchorPane anchorPane = fxmlloader.load();
              grid.add(anchorPane, 1, 1);
             
        } catch (IOException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
