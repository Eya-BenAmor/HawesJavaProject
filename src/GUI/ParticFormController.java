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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;
import Entities.Formation;
import Entities.ParticForm;
import Services.ServiceFormation;
import Services.ServiceParticForm;
import utils.Datasource;
import utils.MailerService;
import utils.Navigation;

/**
 * FXML Controller class
 *
 * @author seifi
 */
public class ParticFormController implements Initializable {

    @FXML
    private TextField nomp;
    @FXML
    private TextField prenompart;
    @FXML
    private TextField agepart;
    @FXML
    private TextField numpart;
    @FXML
    private Button ajoutP;
    @FXML
    private Button modP;
    @FXML
    private Button supP;
    @FXML
    private TableView<ParticForm> ListP;
    @FXML
    private TableColumn<ParticForm, String> n;
    @FXML
    private TableColumn<ParticForm, String> p;
    @FXML
    private TableColumn<ParticForm, Integer> a;
    @FXML
    private TableColumn<ParticForm, Integer> nu;
    @FXML
    private ComboBox<?> idform;

    ResultSet rs = null;
    Connection coonx = null;
    
    private static int idd;
    
    public static int getIdd(int id) {
        idd = id;
        return idd;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showP();
        selectid();

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

    public void showP() {
        ServiceParticForm serv = new ServiceParticForm();
        ObservableList<ParticForm> s = serv.getAllParticForm();
        
        
         ServiceFormation serv1 = new ServiceFormation();
        ObservableList<Formation> s1 = serv1.getAllForm();


        // ObservableList<ParticForm> list = getBooksList();
        n.setCellValueFactory(new PropertyValueFactory<ParticForm, String>("nom"));
        p.setCellValueFactory(new PropertyValueFactory<ParticForm, String>("prenom"));
        a.setCellValueFactory(new PropertyValueFactory<ParticForm, Integer>("age"));
        nu.setCellValueFactory(new PropertyValueFactory<ParticForm, Integer>("Numero"));
  

        ListP.setItems(s);
       
    }

    @FXML
    private void onsave(MouseEvent event) {
    }

    @FXML
    private void supPartic(ActionEvent event) {
        ServiceParticForm s = new ServiceParticForm();
        System.out.println(ListP.getSelectionModel().getSelectedItem().getNom());
        s.deletePartic(ListP.getSelectionModel().getSelectedItem().getNom());
        showP();
    }

    @FXML
    private void ajouter(ActionEvent event) {
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
        showP();


    }

    @FXML
    private void modP(ActionEvent event) {
        ParticForm f = new ParticForm(nomp.getText(), prenompart.getText(), parseInt(agepart.getText()), parseInt(numpart.getText()), Integer.parseInt(idform.getValue().toString()));
        System.out.println(f);
        ServiceParticForm serv = new ServiceParticForm();
        serv.updatePartic(f, ListP.getSelectionModel().getSelectedItem().getNom());
        showP();
    }

    

}
