/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Randonnee;
import Services.RandonneeService;
import java.io.File;
import java.io.IOException;
import static java.lang.Float.parseFloat;

import java.net.URL;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class RandoController implements Initializable {

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
    @FXML
    private Label tfimage;
    @FXML
    private Button button_inserer_image;
    @FXML
    private Label file_path;

    @FXML
    private ImageView ImageViewer;
  
   

    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList(
                "voiture", "pied", "velo");
        categorie.setValue("pied");
        categorie.setItems(list);
        
        AfficherRandonnee();
         
    }

    @FXML
    private void AjouterRandonnee(ActionEvent event) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //obtenir la date courante
        Date date = new Date();

        Randonnee rando = new Randonnee();
        rando.setNom_rando(tfNom.getText());
        rando.setDescription(tfDescription.getText());
        rando.setDestination(tfDestination.getText());
        rando.setDuree_rando(tfDuree.getText());
        rando.setPrix(parseFloat(tfPrix.getText()));
        rando.setImage(file_path.getText());
        rando.setCategorie_rando(categorie.getValue());
        java.sql.Date sqlDate = java.sql.Date.valueOf(dateRando.getValue());
        rando.setDate_rando(sqlDate);
        if (((tfNom.getText().isEmpty())) || ((tfDescription.getText().isEmpty())) || ((tfDestination.getText().isEmpty())) || ((tfDuree.getText().isEmpty())) || ((dateRando.getValue().equals(""))) || ((categorie.getSelectionModel().isEmpty())) || ImageViewer.getImage() == null || ((tfPrix.getText().isEmpty()))) {

            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
        } else if (parseFloat(tfPrix.getText()) < 0) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Prix doit etre positif");
        } else if (java.sql.Date.valueOf(dateRando.getValue()).compareTo(date) < 0) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "La date doit etre supérieure à la date d'ajourd'hui");
        } else {
             
            randoservice.ajouterRandonnee(rando);
        
            Image img = new Image("/logo.png");
            Notifications notificationBuilder = Notifications.create()
                    .title("Notification ")
                    .text(" Randonnée ajoutée avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
           
        }
 

        AfficherRandonnee();
    }

    private void AfficherRandonnee() {
        oblist.clear();
        List<Randonnee> r = randoservice.afficherRandonnee();
        r.forEach(e -> oblist.add(e));
        r.forEach(e -> System.out.println("res =>" + e));
        nomAff.setCellValueFactory(new PropertyValueFactory<>("nom_rando"));
        destinationAff.setCellValueFactory(new PropertyValueFactory<>("destination"));
        dureeAff.setCellValueFactory(new PropertyValueFactory<>("duree_rando"));
        prixAff.setCellValueFactory(new PropertyValueFactory<>("prix"));
        categorieAff.setCellValueFactory(new PropertyValueFactory<>("categorie_rando"));
        descriptionAff.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateAff.setCellValueFactory(new PropertyValueFactory<>("date_rando"));
        tableRando.setItems(oblist);

    }

    @FXML
    private void SupprimerRandonnee(ActionEvent event) {
        tableRando.setItems(oblist);
        ObservableList<Randonnee> all, Single;
        all = tableRando.getItems();
        Single = tableRando.getSelectionModel().getSelectedItems();
        Randonnee A = Single.get(0);
        randoservice.supprimerRandonnee(A.getId());
        Single.forEach(all::remove);
        AfficherRandonnee();
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Randonnee randoSelected = tableRando.getSelectionModel().getSelectedItem();
        tfNom.setText(randoSelected.getNom_rando());
        tfDestination.setText(randoSelected.getDescription());
        tfDuree.setText(randoSelected.getDuree_rando());
        tfPrix.setText("" + randoSelected.getPrix());

        categorie.setValue(randoSelected.getCategorie_rando());

        dateRando.setValue(randoSelected.getDate_rando().toLocalDate());
        tfDescription.setText(randoSelected.getDescription());
       file_path.setText(randoSelected.getImage());
          Image image = new Image( randoSelected.getImage());
            ImageViewer.setImage(image);

    }

    @FXML
    private void ModifierRandonnee(ActionEvent event) {
        tableRando.setItems(oblist);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //obtenir la date courante
        Date date = new Date();
        ObservableList<Randonnee> all, Single;
        all = tableRando.getItems();
        Single = tableRando.getSelectionModel().getSelectedItems();
        Randonnee A = Single.get(0);

        System.out.println(A.getNom_rando());

        A.setNom_rando(tfNom.getText());
        A.setDescription(tfDescription.getText());
        A.setDestination(tfDestination.getText());
        A.setDuree_rando(tfDuree.getText());
        A.setPrix(parseFloat(tfPrix.getText()));
        A.setImage(file_path.getText());

        A.setCategorie_rando(categorie.getValue());
        java.sql.Date sqlDate = java.sql.Date.valueOf(dateRando.getValue());
        A.setDate_rando(sqlDate);
        if (((tfNom.getText().isEmpty())) || ((tfDescription.getText().isEmpty())) || ((tfDestination.getText().isEmpty())) || ((tfDuree.getText().isEmpty())) || ((dateRando.getValue().equals(""))) || ((categorie.getSelectionModel().isEmpty())) || ((tfPrix.getText().isEmpty()))) {

            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
        } else if (parseFloat(tfPrix.getText()) < 0) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Prix doit etre positif");
        } else if (java.sql.Date.valueOf(dateRando.getValue()).compareTo(date) < 0) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "La date doit etre supérieure à la date d'ajourd'hui");
        } else {
            randoservice.modifierRandonnee(A, A.getId());
Image img = new Image("/logo.png");
            Notifications notificationBuilder = Notifications.create()
                    .title("Notification  ")
                    .text(" Randonnée modifiée avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();

            AfficherRandonnee();
        }
    }

    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }

    @FXML
    private void insert_image(ActionEvent event) {
        FileChooser open = new FileChooser();
        Stage stage = (Stage) categ.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        if (file != null) {
            String path = file.getAbsolutePath();
            path = path.replace("\\", "\\\\");
            file_path.setText(path);
            Image image = new Image(file.toURI().toString(), 110, 110, false, true);
            ImageViewer.setImage(image);
        } else {
            System.out.println("NO DATA EXIST!");
        }
    }
    

}
