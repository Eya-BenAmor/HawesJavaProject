/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Admin;
import Services.AdminService;
import static GUI.LoginController.dashBStage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class AddAdminController implements Initializable {

    @FXML
    private AnchorPane MainAnchor;
    @FXML
    private TextField tfAdminLn;
    @FXML
    private TextField tfAdminFn;
    @FXML
    private TextField tfAdminEmail;
    @FXML
    private PasswordField pfAdminPwd;
    @FXML
    private PasswordField pfAdminCPwd;
    @FXML
    private Button addbtn;
    @FXML
    private TextField tfAdminCin;
    private Object fxml;
    @FXML
    private Label LnErr;
    @FXML
    private Label FnErr;
    @FXML
    private Label emailErr;
    @FXML
    private Label cinErr;
    @FXML
    private Label cpwdErr;
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
    private void OnSignUp(ActionEvent event) throws SQLException, IOException {
        AdminService Admin =new AdminService();
        Admin a =new Admin();
        int fnV=0 , lnV=0 , emailV=0 , pwdV=0 , cpwdV=0 , cinV=0;

        if(tfAdminLn.getText().isEmpty() || tfAdminLn.getText().length() < 3){
            LnErr.setText("Le nom doit contenir au moin 3 lettres");
        }else{
            LnErr.setText("");
            lnV=1;
        }
        if(tfAdminFn.getText().isEmpty() || tfAdminFn.getText().length() < 3){
            FnErr.setText("Le prenom doit contenir au moin 3 lettres");
        }else{
            FnErr.setText("");
            fnV=1;
        }
        if(tfAdminEmail.getText().isEmpty()){
            emailErr.setText("Veuillez saisir une adresse email valide");
        }else{
            emailErr.setText("");
            emailV=1;
        }
        if(pfAdminPwd.getText().length() < 8){
            pwdErr.setText("Le mot de passe doit contenir au moin 8 lettres");
        }else{
            pwdErr.setText("");
            pwdV=1;
        }
        if(!pfAdminCPwd.getText().equals(pfAdminPwd.getText())){
            cpwdErr.setText("Verifier votre mot de passe saisi");
            }else{
            pwdErr.setText("");
            cpwdV=1;
        }
        if(lnV==1 && fnV==1 && emailV==1 && pwdV==1 && cpwdV==1)
        {a.setNom(tfAdminLn.getText());
        a.setPrenom(tfAdminFn.getText());
        a.setEmail(tfAdminEmail.getText());
        a.setCin(tfAdminCin.getText());
        a.setMdp(pfAdminPwd.getText());
        a.setConfirm_mdp(pfAdminCPwd.getText());
        Admin.ajouter(a);

          /*  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminList.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root1));
                dashBStage = stage;
                stage.show();
               */
    }
    }
}
    

