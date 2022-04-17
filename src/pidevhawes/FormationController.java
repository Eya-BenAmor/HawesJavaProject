/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhawes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pidev.Entities.Formation;
import pidev.Entities.ParticForm;
import pidev.Services.ServiceFormation;
import pidev.Services.ServiceParticForm;

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
    @FXML
    private TableColumn<?, ?> nF1;
    @FXML
    private TableColumn<?, ?> nF2;
    @FXML
    private TableColumn<?, ?> nF3;

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
        
       
        
        nF.setCellValueFactory(new PropertyValueFactory<Formation, String>("nomform"));
        dF.setCellValueFactory(new PropertyValueFactory<Formation, String>("domaine"));
        dureeF.setCellValueFactory(new PropertyValueFactory<Formation, String>("duree"));
        nomeqF.setCellValueFactory(new PropertyValueFactory<Formation, String>("nomeq"));
        
        
        ListF.setItems(s);
      
    }

    @FXML
    private void inscrire(ActionEvent event) {
        
        ServiceFormation serv = new ServiceFormation();
        Formation f=new Formation(nomf.getText(),domaine.getText(), duree.getText(),nomeq.getText());
        serv.ajouterForm(f);
        showF();
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
          showF();
    }


    

   
}
