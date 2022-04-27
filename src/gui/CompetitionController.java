/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entites.Competition;
import Service.ServiceCompetition;
import Utils.MailerService;
import Utils.Navigation;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Mezen Bayounes
 */
public class CompetitionController implements Initializable {

    @FXML
    private TextField text_Nom_Competition;
    @FXML
    private DatePicker text_Date_Competition;
    @FXML
    private Button but_insert;
    @FXML
    private Button but_update;
    @FXML
    private Button but_delete;
    @FXML
    
  
    private TextField text_recherche_competition;
    private TextField text_Distance_competition;
    private TextField text_Prix_competition;
    
        private final ObservableList<Competition> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Competition, String> col_Nom_competition;
    @FXML
    private TableColumn<Competition, Integer> col_Distance_competition;
    @FXML
    private TableColumn<Competition, Date> col_Date_competition;
    @FXML
    private TableColumn<Competition, Integer> col_Prix_competition;
    @FXML
    private TableView<Competition> tv_Competition;
    @FXML
    private TextField text_Distance_Competition;
    @FXML
    private TextField text_Prix_Competition;
    @FXML
    private TextField tf_id;
    
        ServiceCompetition s = new  ServiceCompetition();
    @FXML
    private TableColumn<Competition,Integer> col_id;
    @FXML
    private TableColumn<?, ?> col_Nom_competition2;
   
    @FXML
    private Label erprixcomp;
    @FXML
    private Label erdatecomp;
    @FXML
    private Button nav;
    /**
     * Initializes the controller class.
     */
     
    public void showCompetition() {
        list.clear();
        ObservableList<Competition> lista = new ServiceCompetition().getAllComp();
        list.setAll(lista);
          col_id.setCellValueFactory(new PropertyValueFactory<Competition, Integer>("id"));
        col_Nom_competition.setCellValueFactory(new PropertyValueFactory<Competition, String>("Nom"));
        col_Distance_competition.setCellValueFactory(new PropertyValueFactory<Competition, Integer>("distance"));
        col_Date_competition.setCellValueFactory(new PropertyValueFactory<Competition, Date>("date"));
        col_Prix_competition.setCellValueFactory(new PropertyValueFactory<Competition, Integer>("prix"));
        tv_Competition.setItems(list);
      
    }

   




   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       showCompetition(); //To change body of generated methods, choose Tools | Templates.
    }

  

   

  

    @FXML
    private void addCompetition(MouseEvent event) {
      if (text_Nom_Competition.getText().isEmpty())
         {
                        Notifications notificationBuilder= Notifications.create()
                  .title("erreur")
                  .text("invalid Nom")
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
        
           if (text_Distance_Competition.getText().isEmpty())
         {
                       Notifications notificationBuilder= Notifications.create()
                  .title("erreur")
                  .text("invalid Distance")
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
          if (text_Prix_Competition.getText().isEmpty())
         {
                        Notifications notificationBuilder= Notifications.create()
                  .title("erreur")
                  .text("invalid Competition")
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

        System.out.println(text_Date_Competition.getValue().getYear());
        Date d = java.sql.Date.valueOf(text_Date_Competition.getValue());
        Competition c = new Competition(text_Nom_Competition.getText(), parseInt(text_Distance_Competition.getText()), parseInt(text_Prix_Competition.getText()),d);
        
          
        s.addCompetition(c);
        MailerService m=new MailerService();
    m.replyMail("mezen.bayounes@esprit.tn", "User", "ajout de Competition", "Bonjour !une Competition ajoutee");
     Notifications notificationBuilder= Notifications.create()
                  .title("succes")
                  .text("Competition ajoute")
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

                  notificationBuilder.showInformation();
        showCompetition();
           
    }

   

    @FXML
    private void tableClicked(MouseEvent event) {
             Competition rec = tv_Competition.getSelectionModel().getSelectedItem();
               
        tf_id.setText(Integer.toString(rec.getId()));
          text_Distance_Competition.setText(Integer.toString(rec.getDistance()));
             text_Prix_Competition.setText(Integer.toString(rec.getPrix()));
         text_Nom_Competition.setText(rec.getNom());
    
        int year = Integer.parseInt(rec.getDate().toString().substring(0, 4));
        int month = Integer.parseInt(rec.getDate().toString().substring(5,7));
        int day= Integer.parseInt(rec.getDate().toString().substring(8,10));

        text_Date_Competition.setValue(LocalDate.of(year, month, day));
          showCompetition();
    }

  

    @FXML
    private void updateComp(MouseEvent event) {
              Date d = java.sql.Date.valueOf(text_Date_Competition.getValue());
        
              Competition c = new Competition(Integer.parseInt(tf_id.getText()),text_Nom_Competition.getText(), parseInt(text_Distance_Competition.getText()), parseInt(text_Prix_Competition.getText()),d);
        
              s.updateCompetition(c);
               Notifications notificationBuilder= Notifications.create()
                  .title("succes")
                  .text("competition updated")
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

                  notificationBuilder.showInformation();
        showCompetition();
    }

    @FXML
    private void deleteCom(MouseEvent event) {
        
           s.deleteCompetition(tf_id.getText());
            Notifications notificationBuilder= Notifications.create()
                  .title("succes")
                  .text("Competition supprimer avec succes")
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

                  notificationBuilder.showInformation();
        showCompetition();
    }

    @FXML
    private void nav(ActionEvent event) throws IOException {
                             
        Navigation nav = new Navigation();
                    nav.navigate(event, "gui", "/gui/Cadeau.fxml");
    }

}