package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;
import Entities.ParticForm;
import Services.ServiceParticForm;
import utils.Datasource;
import utils.MailerService;
import utils.Navigation;
import utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author seifi
 */
public class ParticFormFrontController implements Initializable {

    @FXML
    private TextField nomp;
    @FXML
    private TextField prenompart;
    @FXML
    private TextField agepart;
    @FXML
    private Button ajoutP;
    @FXML
    private TextField numpart;
    @FXML
    private ComboBox<?> idform;
    ResultSet rs = null;
    Connection coonx = null;
    
    
    private int ok=0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        selectid();
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException, InterruptedException {
        if (nomp.getText().isEmpty())
         {
                       Notifications notificationBuilder= Notifications.create()
                  .title("Error")
                  .text("nom invalide")
                  .graphic(null)
                  .position(Pos.CENTER)
                  .onAction(new EventHandler <ActionEvent>()
                  {@Override
                  public void handle(ActionEvent event)
                  {System.out.println("clicked");
                  }
                  }
                  );
                 notificationBuilder.darkStyle();

                  notificationBuilder.showError();
                  
                        return;
                    }
         if (prenompart.getText().isEmpty())
         {
                        
                       Notifications notificationBuilder= Notifications.create()
                  .title("Error")
                  .text("Prenom invalide")
                  .graphic(null)
                  .position(Pos.CENTER)
                  .onAction(new EventHandler <ActionEvent>()
                  {@Override
                  public void handle(ActionEvent event)
                  {System.out.println("clicked");
                  }
                  }
                  );
                 notificationBuilder.darkStyle();

                  notificationBuilder.showError();
                  
                        return;
                    }
     if (agepart.getText().isEmpty())
         {
                        
                       Notifications notificationBuilder= Notifications.create()
                  .title("Error")
                  .text("age invalide")
                  .graphic(null)
                  .position(Pos.CENTER)
                  .onAction(new EventHandler <ActionEvent>()
                  {@Override
                  public void handle(ActionEvent event)
                  {System.out.println("clicked");
                  }
                  }
                  );
                 notificationBuilder.darkStyle();

                  notificationBuilder.showError();
                  
                        return;
                    }
     
      if (numpart.getText().isEmpty())
         {
                        
                       Notifications notificationBuilder= Notifications.create()
                  .title("Error")
                  .text("Numero invalide")
                  .graphic(null)
                  .position(Pos.CENTER)
                  .onAction(new EventHandler <ActionEvent>()
                  {@Override
                  public void handle(ActionEvent event)
                  {System.out.println("clicked");
                  }
                  }
                  );
                 notificationBuilder.darkStyle();

                  notificationBuilder.showError();
                  
                        return;
                    }


        ParticForm parti = new ParticForm(nomp.getText(), prenompart.getText(), parseInt(numpart.getText()), parseInt(numpart.getText()), parseInt(idform.getValue().toString()));

        parti.setPrenom(prenompart.getText());

        parti.setAge(parseInt(agepart.getText()));
        parti.setNumero(parseInt(numpart.getText()));

        parti.setId_formation_id(parseInt(idform.getValue().toString()));

        ServiceParticForm serv = new ServiceParticForm();

        serv.ajouterPartic(parti);
           
                       Notifications notificationBuilder= Notifications.create()
                  .title("Information")
                  .text("Participation Acceptée")
                  .graphic(null)
                  .position(Pos.CENTER)
                  .onAction(new EventHandler <ActionEvent>()
                  {@Override
                  public void handle(ActionEvent event)
                  {System.out.println("clicked");
                  }
                  }
                  );
                 notificationBuilder.darkStyle();

                  notificationBuilder.showError();
                  
                 // TimeUnit.MILLISECONDS.sleep(900);

                  
                 /* Navigation nav = new Navigation();
                 nav.navigate(event, "pidevhawes", "/pidevhawes/Acceuil.fxml");
                 return;
                 */
                 
                 
                 try {
                     FormationFrontController ffc=new FormationFrontController();
                     ffc.setOk(1);
                NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "ProjetJava", "/GUI/Accueil.fxml");
            } catch (IOException ex) 
            {
            Logger.getLogger(ParticFormFrontController.class.getName()).log(Level.SEVERE, null, ex);
            

                    }
                 
        
         MailerService m=new MailerService();
    m.replyMail("seifelislem.bensib@esprit.tn", "User", "nouveau participant", "Bonjour ! il y'a un nouveau participant");
    }

    @FXML
    private void onsave(MouseEvent event) {
    }
    
    
     private void selectid() {
        try {

            coonx = Datasource.getInstance().getConnection();

            rs = coonx.createStatement().executeQuery("select id from formation");

            ObservableList List = FXCollections.observableArrayList();
            while (rs.next()) {

                List.add(rs.getString(1));
            }

            idform.setItems(List);

        } catch (Exception ex) {
            System.out.println("error while inserting record. ");
        }
    }
      public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }
}
