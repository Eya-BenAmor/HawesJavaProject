/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Categorie;
import Services.ServiceCategorie;
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

/**
 * FXML Controller class
 *
 * @author msi
 */
public class CatInterfaceController implements Initializable {

    @FXML
    private TextField tfnomC;
    @FXML
    private TextField tfdescriptionC;
    @FXML
    private TableView<Categorie> tvCategorie;
    @FXML
    private TableColumn<Categorie, String> colnom;
    @FXML
    private TableColumn<Categorie, String> coldescription;
    @FXML
    private Button btnajouterC;
    @FXML
    private Button btnsupprimerC;
    @FXML
    private Button btnmodifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCateg();        
// TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        

        ServiceCategorie serv = new ServiceCategorie();
        Categorie f=new Categorie(tfnomC.getText(),tfdescriptionC.getText());
        serv.addCategorie(f);
        showCateg();
    
        
    }
    public void showCateg() {
    
            ServiceCategorie serv = new ServiceCategorie();
        ObservableList<Categorie> s = serv.getAllCateg();
     
        colnom.setCellValueFactory(new PropertyValueFactory<Categorie, String>("nom"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Categorie, String>("description"));
       
        tvCategorie.setItems(s);
        
  
     
    } 
    
     

    @FXML
    private void supprimer(ActionEvent event) {
         ServiceCategorie s= new ServiceCategorie();
        s.deleteCategorie( tvCategorie.getSelectionModel().getSelectedItem().getNom());
      
        showCateg();
    }

    @FXML
    private void modifiercategorie(ActionEvent event) {
           Categorie f=new Categorie(tfnomC.getText(),tfdescriptionC.getText());
        System.out.println(f);
         ServiceCategorie serv = new ServiceCategorie();
         serv.updateCategorie(f,tvCategorie.getSelectionModel().getSelectedItem().getNom());
           showCateg();
          
    }
    
}
