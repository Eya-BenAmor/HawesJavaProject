/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class BackOfficeController implements Initializable {

    @FXML
    private Label adminN;
    @FXML
    private AnchorPane MainAnchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //adminN.setText(LoginController.connectedAdmin.getPrenom());
    }    

    @FXML
    private void OnAdminsList(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("AdminList.fxml"));
        Scene scene = new Scene(page1, 900, 544);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Admins List");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void OnUsersList(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("UserList.fxml"));
        Scene scene = new Scene(page1, 900, 544);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Users List");
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void OnAddAdmin(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("AddAdmin.fxml"));
        Scene scene = new Scene(page1, 900, 544);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Add Admin");
        stage.setScene(scene);
        stage.show();
    }
    
}
