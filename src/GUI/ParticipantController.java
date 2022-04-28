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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private TableColumn<?, ?> ageAff;
    @FXML
    private TableColumn<?, ?> telAff;
    @FXML
    private TableColumn<?, ?> maladieAff;
    @FXML
    private TableColumn<?, ?> classeAff;
    @FXML
    private TableColumn<?, ?> numAff;
    @FXML
    private ComboBox<?> tfJoin;
    Connection coonx = null;
    Statement st;
    ResultSet rs = null;

    ParticipantService partiservice = new ParticipantService();
    ObservableList<Participant> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<Participant> tableParti;
    @FXML
    private TableColumn<?, ?> userAff;
    @FXML
    private ComboBox<?> tfjoinuser;
    ArrayList<Randonnee> randon = new ArrayList<Randonnee>();

    public ParticipantController() {
        coonx = utils.Datasource.getInstance().getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList(
                "oui", "non");
        tfMaladie.setValue("non");
        tfMaladie.setItems(list);
        ObservableList<String> list2 = FXCollections.observableArrayList(
                "debutant", "intermediaire", "habitue");
        tfClasse.setValue("debutant");
        tfClasse.setItems(list2);
        affecterRandonnee();
        affecterUser();

        AfficherParticipant();

    }

    private void affecterRandonnee() {
        try {

            rs = coonx.createStatement().executeQuery("select nom_rando from randonnee");

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

            rs = coonx.createStatement().executeQuery("select nom from user");

            ObservableList List = FXCollections.observableArrayList();
            while (rs.next()) {

                List.add(rs.getString(1));
            }
            tfjoinuser.setItems(List);

        } catch (Exception ex) {
            System.out.println("error while inserting record. ");
        }
    }

    private void AfficherParticipant() {
        oblist.clear();
        List<Participant> r = partiservice.afficherParticipant();
        r.forEach(e -> oblist.add(e));
        r.forEach(e -> System.out.println("res =>" + e));
        ageAff.setCellValueFactory(new PropertyValueFactory<>("age"));
        telAff.setCellValueFactory(new PropertyValueFactory<>("tel"));
        classeAff.setCellValueFactory(new PropertyValueFactory<>("classe"));
        maladieAff.setCellValueFactory(new PropertyValueFactory<>("maladie"));
        numAff.setCellValueFactory(new PropertyValueFactory<>("id_randonnee"));
        userAff.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        tableParti.setItems(oblist);

    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Participant partiSelected = tableParti.getSelectionModel().getSelectedItem();
        tfAge.setText("" + partiSelected.getAge());
        tfTel.setText(partiSelected.getTel());

        tfClasse.setValue(partiSelected.getClasse());
        tfMaladie.setValue(partiSelected.getMaladie());

    }

    @FXML
    private void ajouterParticipant(ActionEvent event) {

        Participant parti = new Participant();
        parti.setAge(parseInt(tfAge.getText()));
        parti.setTel(tfTel.getText());
        parti.setMaladie(tfMaladie.getValue());
        parti.setClasse(tfClasse.getValue());
        int r = partiservice.rechercherRandonnee(tfJoin.getValue().toString());
        int r2 = partiservice.rechercherUser(tfjoinuser.getValue().toString());
        System.out.println(r);
        System.out.println(tfJoin.getValue().toString());
        parti.setId_randonnee(r);
        parti.setId_user(r2);

        if (((tfAge.getText().isEmpty())) || ((tfTel.getText().isEmpty())) || ((tfClasse.getSelectionModel().isEmpty())) || ((tfMaladie.getSelectionModel().isEmpty())) || ((tfJoin.getSelectionModel().isEmpty())) || ((tfjoinuser.getSelectionModel().isEmpty()))) {

            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");

        } else if (!Pattern.matches("((\\+)216)?[0-9]{8}", tfTel.getText())) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez respecter le format demandé  !");

        } else if (parseInt(tfAge.getText()) < 18) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez avoir au moins 18 ans pour participer  !");
        } else {
            partiservice.ajouterParticipant(parti);

            AfficherParticipant();
        }
    }

    @FXML
    private void modifierParticipant(ActionEvent event) {

        tableParti.setItems(oblist);
        ObservableList<Participant> all, Single;
        all = tableParti.getItems();
        Single = tableParti.getSelectionModel().getSelectedItems();
        Participant A = Single.get(0);

        A.setAge(parseInt(tfAge.getText()));
        A.setTel(tfTel.getText());

        A.setClasse(tfClasse.getValue());
        A.setMaladie(tfMaladie.getValue());

        if (((tfAge.getText().isEmpty())) || ((tfTel.getText().isEmpty())) || ((tfClasse.getSelectionModel().isEmpty())) || ((tfMaladie.getSelectionModel().isEmpty())) || ((tfJoin.getSelectionModel().isEmpty())) || ((tfjoinuser.getSelectionModel().isEmpty()))) {

            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");

        } else if (!Pattern.matches("((\\+)216)?[0-9]{8}", tfTel.getText())) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez respecter le format demandé  !");

        } else if (parseInt(tfAge.getText()) < 18) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez avoir au moins 18 ans pour participer  !");
        } else {
            partiservice.modifierParticipant(A, A.getId());

            AfficherParticipant();
        }

    }

    @FXML
    private void supprimerParticipant(ActionEvent event) {
        tableParti.setItems(oblist); //bch rtafsakh el data eli mawjouda w taawed thot data okhrin bch maysiresh tekrar
        ObservableList<Participant> all, Single;
        all = tableParti.getItems();
        Single = tableParti.getSelectionModel().getSelectedItems();
        Participant A = Single.get(0);
        partiservice.supprimerParticipant(A.getId());
        Single.forEach(all::remove);
        
       
        
        
        
        
        AfficherParticipant();
    }

    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
}
