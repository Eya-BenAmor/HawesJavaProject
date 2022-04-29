package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import Entities.Formation;
import Entities.ParticForm;
import Services.ServiceFormation;
import Services.ServiceParticForm;
import utils.Navigation;

/**
 * FXML Controller class
 *
 * @author seifi
 */
public class FormationController implements Initializable {

    @FXML
    private TextField nomf;
    @FXML
    private TextField domaine;
    @FXML
    private TextField duree;
    @FXML
    private Button ajoutF;
    @FXML
    private TextField nomeq;
    @FXML
    private Button modF;
    @FXML
    private Button supF;
    @FXML
    private TableView<Formation> ListF;
    @FXML
    private TableColumn<Formation, String> nF;
    @FXML
    private TableColumn<Formation, String> dF;
    @FXML
    private TableColumn<Formation, String> dureeF ;
    @FXML
    private TableColumn<Formation, String> nomeqF;
    private Label errnom;
    private Label errdomaine;
    @FXML
    private Button stats;
    @FXML
    private TextField recherche;
    
    ObservableList<Formation> dataList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         showF();
         
                  
    }    
    
     public void showF(){
         ServiceFormation serv = new ServiceFormation();
        ObservableList<Formation> s = serv.getAllForm();
        
       
        
        nF.setCellValueFactory(new PropertyValueFactory<Formation, String>("nomeq"));
        dF.setCellValueFactory(new PropertyValueFactory<Formation, String>("domaine"));
        dureeF.setCellValueFactory(new PropertyValueFactory<Formation, String>("duree"));
        nomeqF.setCellValueFactory(new PropertyValueFactory<Formation, String>("nomform"));
        
        
        ListF.setItems(s);
      
    }

     public static boolean isNumeric(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}
     
    @FXML
    private void inscrire(ActionEvent event) {
        
        
        if (nomf.getText().isEmpty())
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
         if (domaine.getText().isEmpty())
         {
                        
                       Notifications notificationBuilder= Notifications.create()
                  .title("Error")
                  .text("Domaine invalide")
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
     if (duree.getText().isEmpty())
         {
                        
                       Notifications notificationBuilder= Notifications.create()
                  .title("Error")
                  .text("duree invalide")
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
     
      if (nomeq.getText().isEmpty())
         {
                        
                       Notifications notificationBuilder= Notifications.create()
                  .title("Error")
                  .text("Nom Equipe invalide")
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
      else
      {
          ServiceFormation serv = new ServiceFormation();
        Formation f=new Formation(nomf.getText(),domaine.getText(), duree.getText(),nomeq.getText());
        serv.ajouterForm(f);
        showF();
        
                       Notifications notificationBuilder= Notifications.create()
                  .title("Sucess")
                  .text("Formation Ajouter avec  succes")
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
                  
      
      }
     
    }

    @FXML
    private void onsave(MouseEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        ServiceFormation s= new ServiceFormation();
        s.deleteForm( ListF.getSelectionModel().getSelectedItem().getNomform());
        showF();
        
       
    }

    @FXML
    private void modifier(ActionEvent event) {
        Formation f=new Formation(nomf.getText(),domaine.getText(), duree.getText(),nomeq.getText());
        System.out.println(f);
         ServiceFormation serv = new ServiceFormation();
         serv.updateForm(f,ListF.getSelectionModel().getSelectedItem().getNomform());
         
           
                       Notifications notificationBuilder= Notifications.create()
                  .title("Sucess")
                  .text("Formation modifier avec  succes")
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
                  
                    
          showF();
    }

    @FXML
    private void stats(ActionEvent event) {
        
        try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Stats.fxml"));
        //AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Stats.fxml"));
        /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load(), 630, 400);
        Stage stage = new Stage();
        stage.setTitle("Statistique");
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        Logger logger = Logger.getLogger(getClass().getName());
        logger.log(Level.SEVERE, "Failed to create new Window.", e);
    }

}

    @FXML
    private void chercher(ActionEvent event) {
    
        nF.setCellValueFactory(new PropertyValueFactory<Formation, String>("nomeq"));
        dF.setCellValueFactory(new PropertyValueFactory<Formation, String>("domaine"));
        dureeF.setCellValueFactory(new PropertyValueFactory<Formation, String>("duree"));
        nomeqF.setCellValueFactory(new PropertyValueFactory<Formation, String>("nomform"));
          
         try{
              ServiceFormation serv = new ServiceFormation();
          dataList=serv.getAllForm();
         
          ListF.setItems(dataList);
          FilteredList<Formation> filtredData = new FilteredList<>(dataList, b -> true);
          recherche.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(formation-> {
                 if(newValue == null|| newValue.isEmpty()){
                     
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                 if(formation.getNomform().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }else if(formation.getNomeq().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }
                 else if(formation.getDomaine().toLowerCase().indexOf(lowerCaseFilter)!=-1)
                     return true;
                     else
                     return false;
                 });
             });
         SortedList<Formation> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(ListF.comparatorProperty());
         ListF.setItems(sortedData);

         }catch(Exception e){
             System.out.println(e.getMessage());
             
         } 
    }         

   
       
    }


   

