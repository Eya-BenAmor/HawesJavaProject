/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entities.Participant;
import Entities.Randonnee;
import Services.ParticipantService;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ParticipantController implements Initializable {

    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfTel;
    @FXML
    private ComboBox<String> tfMaladie;
    @FXML
    private ComboBox<String> tfClasse;
    @FXML
 
    private ComboBox<?> tfJoin;
    Connection coonx = null;
    Statement st;
    ResultSet rs = null;

    ParticipantService partiservice = new ParticipantService();

 
    private ComboBox<?> tfjoinuser;
  ArrayList<Randonnee> randon = new ArrayList<Randonnee>(); 
    public ParticipantController() {
        coonx = utils.Datasource.getInstance().getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        affecterRandonnee();
        affecterUser();
      

    }

    private void affecterRandonnee() {
        try {

            rs = coonx.createStatement().executeQuery("select id from randonnee");

            ObservableList List = FXCollections.observableArrayList();
            while (rs.next()) {

                List.add(rs.getString(1));
            }
            tfJoin.setItems(List);

        } catch (Exception ex) {
            System.out.println("error while inserting record. ");
        }
    }

    private void affecterUser() {
        try {

            rs = coonx.createStatement().executeQuery("select id from user");

            ObservableList List = FXCollections.observableArrayList();
            while (rs.next()) {

                List.add(rs.getString(1));
            }
            tfjoinuser.setItems(List);

        } catch (Exception ex) {
            System.out.println("error while inserting record. ");
        }
    }

  
   

    @FXML
    private void ajouterParticipant(ActionEvent event) {
     
        Participant parti = new Participant();
        parti.setAge(parseInt(tfAge.getText()));
        parti.setTel(tfTel.getText());

        parti.setClasse(tfClasse.getValue());
        parti.setMaladie(tfMaladie.getValue());
        
        parti.setId_randonnee(Integer.parseInt(tfJoin.getValue().toString()));
        parti.setId_user(Integer.parseInt(tfjoinuser.getValue().toString()));
       

        if (((tfAge.getText().isEmpty())) || ((tfTel.getText().isEmpty())) || ((tfClasse.getSelectionModel().isEmpty())) || ((tfMaladie.getSelectionModel().isEmpty())) || ((tfJoin.getSelectionModel().isEmpty())) || ((tfjoinuser.getSelectionModel().isEmpty()))) {

            System.out.println("erreur d ajout");

        } else {
            partiservice.ajouterParticipant(parti);

            
        }
    }

   

   

}