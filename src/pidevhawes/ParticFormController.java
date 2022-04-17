/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhawes;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pidev.Entities.ParticForm;
import pidev.Services.ServiceFormation;
import pidev.Services.ServiceParticForm;

/**
 * FXML Controller class
 *
 * @author seifi
 */
public class ParticFormController implements Initializable {

    private TextField nompart;
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
    private TableColumn<ParticForm,Integer > a;
    @FXML
    private TableColumn<ParticForm, Integer> nu;
    @FXML
    private TableColumn<ParticForm, Integer> id;
    @FXML
    private ComboBox<?> idform;
    
     ResultSet rs = null;
     Connection coonx = null;
    @FXML
    private TextField nomp;

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

             coonx = pidev.Utils.DataSource.getInstance().getConnection();

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
     
     
     public void showP(){
         ServiceParticForm serv = new ServiceParticForm();
        ObservableList<ParticForm> s = serv.getAllParticForm();
        
       // ObservableList<ParticForm> list = getBooksList();
        
        n.setCellValueFactory(new PropertyValueFactory<ParticForm, String>("nom"));
        p.setCellValueFactory(new PropertyValueFactory<ParticForm, String>("prenom"));
        a.setCellValueFactory(new PropertyValueFactory<ParticForm, Integer>("age"));
        nu.setCellValueFactory(new PropertyValueFactory<ParticForm, Integer>("Numero"));
        id.setCellValueFactory(new PropertyValueFactory<ParticForm, Integer>("id_formation_id"));
        
        
        ListP.setItems(s);
    }
   

    @FXML
    private void onsave(MouseEvent event) {
    }

    @FXML
    private void supPartic(ActionEvent event) {
        ServiceParticForm s= new ServiceParticForm();
        System.out.println(ListP.getSelectionModel().getSelectedItem().getNom());
        s.deletePartic(ListP.getSelectionModel().getSelectedItem().getNom());
        showP();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        /*     ParticForm parti =new ParticForm("dfddsfs","sdfsdf",1,1,14);
        System.out.println(Integer.parseInt(idform.getValue().toString()));*/
        
        /* ParticForm parti = new ParticForm(nompart.getText(),prenompart.getText(), parseInt(numpart.getText()),parseInt(numpart.getText()),parseInt(idform.getValue().toString()));*/
         ParticForm parti =new ParticForm();
         
           parti.setNom(nompart.getText());
          parti.setPrenom(prenompart.getText());
          
          parti.setAge(parseInt(agepart.getText()));
          parti.setNumero(parseInt(numpart.getText()));
          
          parti.setId_formation_id(13);
          
        System.out.println(parti.getId_formation_id());

      
      
        ServiceParticForm serv = new ServiceParticForm();
           
                    
                    serv.ajouterPartic(parti);
                      showP();

            
         
       
    }

    @FXML
    private void modP(ActionEvent event) {
         ParticForm f=new ParticForm(nomp.getText(),prenompart.getText(), parseInt(agepart.getText()),parseInt(numpart.getText()),Integer.parseInt(idform.getValue().toString()));
        System.out.println(f);
         ServiceParticForm serv = new ServiceParticForm();
         serv.updatePartic(f,ListP.getSelectionModel().getSelectedItem().getNom());
          showP();
    }
    
}
