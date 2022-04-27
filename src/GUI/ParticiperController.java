/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Participant;
import Entities.Randonnee;
import Services.ParticipantService;
import Services.RandonneeService;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import static GUI.ParticipantController.showAlert;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ParticiperController implements Initializable {

    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfTel;
    @FXML
    private ComboBox<String> tfMaladie;
    @FXML
    private ComboBox<String> tfClasse;
    private static int idd;
       public static int prix ; 
 public static int getIdd(int id) {
        idd = id;
        return idd;
    }
 
  RandonneeService randoservice = new RandonneeService();
   ParticipantService partiservice = new ParticipantService();
    private Randonnee rando;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         rando =randoservice.TrouverById(idd) ;
         prix=(int) rando.getPrix();
           ObservableList<String> list = FXCollections.observableArrayList(
                "oui", "non");
        tfMaladie.setValue("non");
        tfMaladie.setItems(list);
        ObservableList<String> list2 = FXCollections.observableArrayList(
                "debutant", "intermediaire", "habitue");
        tfClasse.setValue("debutant");
        tfClasse.setItems(list2);
         System.out.println(idd);
    }    

    @FXML
    private void ajouterParticipant(ActionEvent event) throws IOException, StripeException {
          Participant parti = new Participant();
           try{
           
       
        parti.setAge(parseInt(tfAge.getText()));
         }catch(NumberFormatException exc){
            System.out.println("Number is required");
         
            return;
        }   
        parti.setTel(tfTel.getText());
        parti.setMaladie(tfMaladie.getValue());
        parti.setClasse(tfClasse.getValue());
     
        parti.setId_randonnee(rando.getId());
        System.out.println(rando.getId());
        parti.setId_user(4);

       if (((tfAge.getText().isEmpty())) || ((tfTel.getText().isEmpty())) || ((tfClasse.getSelectionModel().isEmpty())) || ((tfMaladie.getSelectionModel().isEmpty())) ) {

            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");

        }
       else if (!Pattern.matches("((\\+)216)?[0-9]{8}", tfTel.getText())) {
          showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez respecter le format demandé  !");  
           
       }
       else if (parseInt(tfAge.getText()) < 18) {
          showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez avoir au moins 18 ans pour participer  !");   
       }
       
        else {
           /* partiservice.ajouterParticipant(parti);
             Image img = new Image("/logo.png");
            Notifications notificationBuilder = Notifications.create()
                    .title("Bienvenue ")
                    .text(" Participation acceptée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
             */ 
           NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "ProjetJava", "/GUI/PaiementEnLigne.fxml");
            

           
           
           
           
           
        
           
        }
    }

    @FXML
    private void annulerR(ActionEvent event) throws IOException {
          NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "ProjetJava", "/GUI/Accueil.fxml");
    }

    
}
