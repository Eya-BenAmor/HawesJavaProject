/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Randonnee;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Navigation;
import utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class RandoFrontController implements Initializable {

    @FXML
    private Label nomL;
    @FXML
    private Label destinationL;
    @FXML
    private Label categorieL;
    @FXML
    private Label dateL;
    @FXML
    private Label dureeL;
    @FXML
    private Label prixL;
    @FXML
    private Label descriptionL;
    @FXML
    private TextField tfId;
 private Randonnee rando;
    @FXML
    private ImageView image;
    @FXML
    private Label nomL1;
    @FXML
    private Label destinationL1;
    @FXML
    private Label categorieL1;
    @FXML
    private Label dateL1;
    @FXML
    private Label dureeL1;
    @FXML
    private Label prixL1;
    @FXML
    private Label descriptionL1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  public void AfficherRandoFront(Randonnee r) {
        rando = r;
        nomL.setText(r.getNom_rando());
        destinationL.setText(r.getDestination());
        descriptionL.setText(r.getDescription());
        dateL.setText(String.valueOf(r.getDate_rando()));
         dureeL.setText(r.getDuree_rando());
          prixL.setText(String.valueOf(r.getPrix()));
           categorieL.setText(r.getCategorie_rando());
       String picture ="file:" +  r.getImage();
        Image imagee = new Image(picture, 110, 110, false, true);
        image.setImage(imagee);
       // ParticiperController.getIdd(r.getId());
    }
    @FXML
    private void participer(ActionEvent event) throws IOException {
        ParticiperController.getIdd(rando.getId());
         int x = ParticiperController.getIdd(rando.getId());
        System.out.println(x);
        
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "GUI", "/GUI/Participer.fxml");
    }

   
    
}
