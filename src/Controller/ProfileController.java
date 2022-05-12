/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.User;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utils.NavigationEntreInterfaces;


/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class ProfileController implements Initializable {

    @FXML
    private Label connName;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField prenomtf;
    @FXML
    private TextField emailtf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connName.setText(LoginController.connectedUser.getPrenom());
        nomtf.setText(LoginController.connectedUser.getNom());
        prenomtf.setText(LoginController.connectedUser.getPrenom());
        emailtf.setText(LoginController.connectedUser.getEmail());
    }    

    @FXML
    private void upDate(ActionEvent event) throws SQLException {
        UserService USER = new UserService();
        User U = LoginController.connectedUser;
        User MU = new User(U.getId(),nomtf.getText(),prenomtf.getText(),emailtf.getText(),U.getMdp(),U.getConfirmMdp());
        System.out.println(U.getId());
        USER.modifier(MU);
        nomtf.clear();
        prenomtf.clear();
        emailtf.clear();
        nomtf.setText(LoginController.connectedUser.getNom());
        prenomtf.setText(LoginController.connectedUser.getPrenom());
        emailtf.setText(LoginController.connectedUser.getEmail());
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException, IOException {
        UserService User = new UserService();
        User.supprimer(LoginController.connectedUser.getId());
        LoginController.connectedUser = null;
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "GUI", "/GUI/SignUp.fxml");
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        LoginController.connectedUser = null;
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "GUI", "/GUI/SignUp.fxml");
    }
    
}
