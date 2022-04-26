/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hawesjava;

import static hawesjava.HawesJava.LoginStage;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Email;

/**
 *
 * @author Yahya
 */
public class LoginController implements Initializable {
    public static Stage dashBStage = null;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField pwdEmail;
    @FXML
    private Button LoginBtn;
    @FXML
    private AnchorPane MainAnchor;
    
    
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
         try {
           System.out.println("**************");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminList.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root1));
                dashBStage = stage;
                stage.show();
               
                closeLogin();
                

            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
}
