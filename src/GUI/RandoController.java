/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entities.Randonnee;
import Services.RandonneeService;
import static java.lang.Float.parseFloat;
import static java.lang.String.format;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class RandoController implements Initializable {

     private Label label;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfDestination;
    @FXML
    private TextField tfDescription;
    @FXML
    private DatePicker dateRando;
    @FXML
    private ComboBox<String> categorie;
    @FXML
    private TextField tfDuree;
    @FXML
    private TextField tfPrix;
    @FXML
    private AnchorPane categ;
     @FXML
    private TableColumn<?, ?> nomAff;
    @FXML
    private TableColumn<?, ?> destinationAff;
    @FXML
    private TableColumn<?, ?> categorieAff;
    @FXML
    private TableColumn<?, ?> dateAff;
    @FXML
    private TableColumn<?, ?> dureeAff;
    @FXML
    private TableColumn<?, ?> prixAff;
 
    @FXML
    private TableView<Randonnee> tableRando;
    

    
    
    RandonneeService randoservice = new RandonneeService();
    ObservableList<Randonnee> oblist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> descriptionAff;
    @FXML
    private TextField tfId;
   
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       AfficherRandonnee();
    }

    @FXML
    private void AjouterRandonnee(ActionEvent event) {

        Randonnee rando = new Randonnee();
        rando.setNom_rando(tfNom.getText());
        rando.setDescription(tfDescription.getText());
        rando.setDestination(tfDestination.getText());
        rando.setDuree_rando(tfDuree.getText());
        rando.setPrix(parseFloat(tfPrix.getText()));
     
        rando.setCategorie_rando(categorie.getValue());
    
        rando.setDate_rando(dateRando.getValue());
        if (((tfNom.getText().isEmpty())) || ((tfDescription.getText().isEmpty())) || ((tfDestination.getText().isEmpty())) || ((tfDuree.getText().isEmpty())) || ((dateRando.getValue().equals(""))) || ((categorie.getSelectionModel().isEmpty())) || ((tfPrix.getText().isEmpty()))) {

            System.out.println("erreur d ajout");
        } else {
            randoservice.ajouterRandonnee(rando);
           
            
        }
    }
   
    private void AfficherRandonnee() {
     
        List<Randonnee> r = randoservice.afficherRandonnee();
        r.forEach(e->oblist.add(e));
        r.forEach(e -> System.out.println("res =>"+e));        
        nomAff.setCellValueFactory(new PropertyValueFactory<>("nom_rando"));
        destinationAff.setCellValueFactory(new PropertyValueFactory<>("destination"));
        dureeAff.setCellValueFactory(new PropertyValueFactory<>("duree_rando"));
        prixAff.setCellValueFactory(new PropertyValueFactory<>("prix"));
         categorieAff.setCellValueFactory(new PropertyValueFactory<>("categorie_rando"));
         descriptionAff.setCellValueFactory(new PropertyValueFactory<>("description"));
           dateAff.setCellValueFactory(new PropertyValueFactory<>("date_rando"));

       
        
    }

    @FXML
    private void SupprimerRandonnee(ActionEvent event) {
   
            ObservableList<Randonnee> all,Single;
            all=tableRando.getItems();
            Single=tableRando.getSelectionModel().getSelectedItems();
            Randonnee A = Single.get(0);
            randoservice.supprimerRandonnee(A.getId());
           
    }


    @FXML
    private void ModifierRandonnee(ActionEvent event) {
       
            ObservableList<Randonnee> all,Single;
          
            Single=tableRando.getSelectionModel().getSelectedItems();
            Randonnee A = Single.get(0);
        
        System.out.println(A.getNom_rando());
        
        A.setNom_rando(tfNom.getText());
        A.setDescription(tfDescription.getText());
        A.setDestination(tfDestination.getText());
        A.setDuree_rando(tfDuree.getText());
        A.setPrix(parseFloat(tfPrix.getText()));
     
        A.setCategorie_rando(categorie.getValue());

        A.setDate_rando(dateRando.getValue());
        if (((tfNom.getText().isEmpty())) || ((tfDescription.getText().isEmpty())) || ((tfDestination.getText().isEmpty())) || ((tfDuree.getText().isEmpty())) || ((dateRando.getValue().equals(""))) || ((categorie.getSelectionModel().isEmpty())) || ((tfPrix.getText().isEmpty()))) {

            System.out.println("erreur de modification");
        } else {
            randoservice.modifierRandonnee(A,A.getId());
             AfficherRandonnee();
        }
    }
}

