/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hawesjava;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane MainAnchor;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button LoginBtn;
    @FXML
    private PasswordField pfPwd;
    @FXML
    private Label EmailErr;
    @FXML
    private Label pwdErr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onLogin(ActionEvent event) {
    }
    
}
