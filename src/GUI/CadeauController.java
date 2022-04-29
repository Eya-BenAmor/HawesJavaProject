/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cadeau;
import Services.ServiceCadeau;
import utils.MailerService;
import utils.Navigation;


import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;
import javax.sound.sampled.*;
import java.io.*;
import utils.Datasource;
/**
 * FXML Controller class
 *
 * @author Mezen Bayounes
 */
public class CadeauController implements Initializable {

    @FXML
    private TextField tf_id;
    @FXML
    private TextField text_Nom_Cadeau;
    @FXML
    private Button but_insert;
    @FXML
    private Button but_update;
    @FXML
    private Button but_delete;
   
    @FXML
    private TextField text_Categorie_Cadeau;
   
    @FXML
    private TextField text_Description_Cadeau;
    @FXML
    private TableView<Cadeau> tv_Cadeau;
    @FXML
    private TableColumn<Cadeau, String> col_Nom_Cadeau;
    @FXML
    private TableColumn<Cadeau, String> col_Categorie_Cadeau;
    @FXML
    private TableColumn<Cadeau, String> col_Description_Cadeau;
    @FXML
    private TableColumn<Cadeau, Integer> col_Competition_Cadeau;
    @FXML
    private Label erdatecomp;
    @FXML
    private Label erprixcomp;
    @FXML
    private ComboBox<?> comboBox;
    ResultSet rs = null;
     Connection coonx = null;
    @FXML
    private Label errnomcad;
    @FXML
    private TableColumn<Cadeau, Integer> col_id;
    ObservableList<Cadeau> dataList;
    @FXML
    private TextField recherche;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCadeau();
      selectid();
    }    

 public void showCadeau(){
         ServiceCadeau serv = new ServiceCadeau();
        ObservableList<Cadeau> s = serv.getAll();
        
     
        
        col_Nom_Cadeau.setCellValueFactory(new PropertyValueFactory<Cadeau, String>("nom"));
        col_Categorie_Cadeau.setCellValueFactory(new PropertyValueFactory<Cadeau, String>("categorie"));
        col_Description_Cadeau.setCellValueFactory(new PropertyValueFactory<Cadeau, String>("description"));
        col_Competition_Cadeau.setCellValueFactory(new PropertyValueFactory<Cadeau, Integer>("competition"));
         col_id.setCellValueFactory(new PropertyValueFactory<Cadeau, Integer>("id"));
        
        
        tv_Cadeau.setItems(s);
    }
    @FXML
    private void tableClicked(MouseEvent event) {
                     Cadeau rec = tv_Cadeau.getSelectionModel().getSelectedItem();

                tf_id.setText(Integer.toString(rec.getId()));
                
            text_Nom_Cadeau.setText((rec.getNom()));
            text_Description_Cadeau.setText((rec.getDescription()));
         

            text_Categorie_Cadeau.setText((rec.getCategorie()));

                
    }

    @FXML
    private void addCadeau(MouseEvent event) {
          ServiceCadeau serv = new ServiceCadeau();
           if (text_Nom_Cadeau.getText().isEmpty())
         {
                       serv.music();
                         Notifications notificationBuilder= Notifications.create()
                  .title("erreur")
                  .text("invalid nom")
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
                    }  if (text_Categorie_Cadeau.getText().isEmpty())
         {                        serv.music();

                        Notifications notificationBuilder= Notifications.create()
                  .title("erreur")
                  .text("invalid Categorie")
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
                    }  if (text_Description_Cadeau.getText().isEmpty())
         {
                                    serv.music();

               Notifications notificationBuilder= Notifications.create()
                  .title("erreur")
                  .text("invalid Description")
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
               Cadeau f=new Cadeau(text_Nom_Cadeau.getText(),text_Categorie_Cadeau.getText(), text_Description_Cadeau.getText(),parseInt(comboBox.getValue().toString()));

        System.out.println(parseInt(comboBox.getValue().toString()));
        
       
        serv.addCadeau(f);
        MailerService m=new MailerService();
    m.replyMail("mezen.bayounes@esprit.tn", "User", "ajout de cadeau", "Bonjour !un Cadeau ajoutee");
              Image img=new Image("/gui/img.png");

     Notifications notificationBuilder= Notifications.create()
                  .title("succes")
                  .text("cadeau ajoute")
                  .graphic(new ImageView(img))
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
             
           showCadeau();
    }
 private void selectid() {
        try {

             coonx =Datasource.getInstance().getConnection();

              rs = coonx.createStatement().executeQuery("select id from Competition");
            
            ObservableList List = FXCollections.observableArrayList();
            while (rs.next()) {
            
            List.add(rs.getString(1));
            }
            
            comboBox.setItems(List);
            
      } catch (Exception ex) {
            System.out.println("error while inserting record. ");
        }
        
        
    }
 
    @FXML
    private void updateCadeau(MouseEvent event) {
         ServiceCadeau serv = new ServiceCadeau();
               Cadeau f=new Cadeau(Integer.parseInt(tf_id.getText()),text_Nom_Cadeau.getText(),text_Categorie_Cadeau.getText(), text_Description_Cadeau.getText(),parseInt(comboBox.getValue().toString()));

        System.out.println(parseInt(comboBox.getValue().toString()));
     serv.updateCadeau(f);
          Image img=new Image("/gui/img.png");

 Notifications notificationBuilder= Notifications.create()
                  .title("succes")
                  .text("cadeau updated")
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
        
           showCadeau();
    }

    @FXML
    private void deleteCadeau(MouseEvent event) {
                 ServiceCadeau s= new ServiceCadeau();

          s.deleteCadeau(tf_id.getText());
          Image img=new Image("/gui/img.png");
           Notifications notificationBuilder= Notifications.create()
                   
                  .title("succes")
                  .text("cadeau supprime avec succes")
                  .graphic(new ImageView(img))
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
         
                          showCadeau();

                  }

    private void nav(ActionEvent event) throws IOException {
                           
        Navigation nav = new Navigation();
                    nav.navigate(event, "gui", "/gui/Competition.fxml");
        
    }


    @FXML
    private void cherche(ActionEvent event) {
        
        
          
       col_Nom_Cadeau.setCellValueFactory(new PropertyValueFactory<Cadeau, String>("nom"));
        col_Categorie_Cadeau.setCellValueFactory(new PropertyValueFactory<Cadeau, String>("categorie"));
        col_Description_Cadeau.setCellValueFactory(new PropertyValueFactory<Cadeau, String>("description"));
        col_Competition_Cadeau.setCellValueFactory(new PropertyValueFactory<Cadeau, Integer>("competition"));
          
         try{
              ServiceCadeau serv = new ServiceCadeau();
          dataList =serv.getAll();
         
          tv_Cadeau.setItems(dataList);
          FilteredList<Cadeau> filtredData = new FilteredList<>(dataList, b -> true);
          recherche.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(Cadeau-> {
                 if(newValue == null|| newValue.isEmpty()){
                     
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                 if(Cadeau.getNom().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }else if(Cadeau.getCategorie().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }else if(String.valueOf(Cadeau.getCompetition()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }
                 else if(String.valueOf(Cadeau.getDescription()).indexOf(lowerCaseFilter)!=-1)
                     return true;
                     else
                     return false;
                 });
             });
         SortedList<Cadeau> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(tv_Cadeau.comparatorProperty());
         tv_Cadeau.setItems(sortedData);

         }catch(Exception e){
             System.out.println(e.getMessage());
             
         }

    }

    
       
    }


    

