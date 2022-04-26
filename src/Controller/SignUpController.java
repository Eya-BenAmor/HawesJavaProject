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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import utils.Email;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class SignUpController implements Initializable {

    @FXML
    private AnchorPane MainAnchor;
    @FXML
    private TextField tfUserLn;
    @FXML
    private TextField tfUserFn;
    @FXML
    private TextField tfUserEmail;
    @FXML
    private PasswordField pfUserPwd;
    @FXML
    private PasswordField pfUserCPwd;
    private Object fxml;
    @FXML
    private Label nameErr;
    @FXML
    private Label fnErr;
    @FXML
    private Label emailErr;
    @FXML
    private Label pwdErr;
    @FXML
    private Label cpwdErr;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnSignUp(ActionEvent event) throws SQLException, IOException, Exception {
        UserService USER=new UserService();
        User U=new User();
        String userE = tfUserEmail.getText();
        Email email =  new Email();
        int fnV=0 , lnV=0 , emailV=0 , pwdV=0 , cpwdV=0;
        if(tfUserLn.getText().isEmpty() || tfUserLn.getText().length() < 3){
            nameErr.setText("Le nom doit contenir au moin 3 lettres");
        }else{
            nameErr.setText("");
            lnV=1;
        }
        if(tfUserFn.getText().isEmpty() || tfUserFn.getText().length() < 3){
            fnErr.setText("Le prenom doit contenir au moin 3 lettres");
        }else{
            fnErr.setText("");
            fnV=1;
        }
        if(tfUserEmail.getText().isEmpty()){
            emailErr.setText("Veuillez saisir une adresse email valide");
        }else{
            emailErr.setText("");
            emailV=1;
        }
        if(pfUserPwd.getText().length() < 8){
            pwdErr.setText("Le mot de passe doit contenir au moin 8 lettres");
        }else{
            pwdErr.setText("");
            pwdV=1;
        }
        if(!pfUserCPwd.getText().equals(pfUserPwd.getText())){
            cpwdErr.setText("Verifier votre mot de passe saisi");
            }else{
            pwdErr.setText("");
            cpwdV=1;
        }
        if(lnV==1 && fnV==1 && emailV==1 && pwdV==1 && cpwdV==1){
            U.setMdp(pfUserPwd.getText());
            U.setEmail(tfUserEmail.getText());
            U.setNom(tfUserLn.getText());
            U.setPrenom(tfUserFn.getText());
            U.setConfirmMdp(pfUserCPwd.getText());
            USER.ajouter(U);
            Email.sendEmail(userE, "HAWES", "Merci pour votre inscription."
                    + "On vous souhaite une bonne randonnée.");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserList.fxml"));
            fxml=loader.load(); 
            }
    }
    
}
