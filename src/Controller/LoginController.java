/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Admin;
import Entity.User;
import Services.AdminService;
import Services.UserService;
import static hawesjava.HawesJava.LoginStage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Datasource;
import utils.Email;

/**
 *
 * @author Yahya
 */
public class LoginController implements Initializable {
    Connection cnx= Datasource.getInstance().getConnection();
    public static Stage dashBStage = null;
    public static User connectedUser;
    public static Admin connectedAdmin;

    @FXML
    private TextField tfEmail;
    @FXML
    private Button LoginBtn;
    @FXML
    private AnchorPane MainAnchor;
    @FXML
    private PasswordField pfPwd;
    @FXML
    private Label EmailErr;
    @FXML
    private Label pwdErr;
    
    
    public void closeLogin() {
        try{ 
            LoginStage = (Stage) LoginStage.getScene().getWindow();
            LoginStage.close();
        }catch(Exception e){
        }
    }
    private void handleButtonAction(ActionEvent event) throws IOException {
      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onLogin(ActionEvent event) throws Exception {

        System.out.println("**************");
        AdminService Admin = new AdminService();
        UserService User = new UserService();
        if ((Admin.verifierLogin(tfEmail.getText(), pfPwd.getText()))==true){
            connectedAdmin = Admin.getAdmin(tfEmail.getText());
            Parent page1 = FXMLLoader.load(getClass().getResource("AdminList.fxml"));
            Scene scene = new Scene(page1, 500, 500);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("List");
            stage.setScene(scene);
            stage.show();      
               
        }else if ((User.verifierLogin(tfEmail.getText(), pfPwd.getText()))==true){
            connectedUser = User.getUser(tfEmail.getText());
            Parent page1 = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            Scene scene = new Scene(page1, 900, 544);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Profile");
            stage.setScene(scene);
            stage.show();
                
                }else {
            EmailErr.setText("Veuillez verifier votre adresse email");
            pwdErr.setText("Veuillez verifier votre mot de passe");

        }
    }
 }
