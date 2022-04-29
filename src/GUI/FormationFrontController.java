package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Notifications;
import Entities.Formation;
import utils.Navigation;

/**
 * FXML Controller class
 *
 * @author seifi
 */
public class FormationFrontController implements Initializable {

    @FXML
    private Label nomL;
    @FXML
    private Label prenomL;
    @FXML
    private Label ageL;
    @FXML
    private Label teleL;
    @FXML
    private TextField tfId;
    @FXML
    private ImageView image;
    @FXML
    private Label nomL1;
    @FXML
    private Label prenomL1;
    @FXML
    private Label AgeL1;
    @FXML
    private Label teleL1;
    
     private Formation form;
     
     private int ok;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        if(ok==1){
        
      
                  ok=0;
                  
  
        }
    }    

     public void AfficherFormationFront(Formation c) {
        form = c;
        nomL.setText(c.getNomform());
        prenomL.setText(String.valueOf(c.getDomaine()));
        ageL.setText(String.valueOf(c.getDuree()));
        teleL.setText(String.valueOf(c.getNomeq()));
      
    }
     
     
    @FXML
    private void participer(ActionEvent event) throws IOException {
        
        ParticFormController.getIdd(form.getId());
         int x = ParticFormController.getIdd(form.getId());
        System.out.println(x);
        
        Navigation nav = new Navigation();
                    nav.navigate(event, "ProjetJava", "/GUI/ParticFormFront.fxml");
        
    }

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }
    
    
   
}
