/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import Services.UserService;
import com.itextpdf.text.Chunk;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class UserListController implements Initializable {

    @FXML
    private AnchorPane MainAnchor;
    @FXML
    private TableView<User> UserListTable;
    @FXML
    private TableColumn<User, String> UserLn;
    @FXML
    private TableColumn<User, String> UserFn;
    @FXML
    private TableColumn<User, String> UserEmail;
    @FXML
    private Button updBtn;
    @FXML
    private TextField tfUserLn;
    @FXML
    private TextField tfUserFn;
    @FXML
    private TextField tfUserEmail;
    @FXML
    private Button UPDbtn;
    @FXML
    private Button delBtn;
    @FXML
    private Button pdfBtn;

    public void initColumns() {
        
        UserLn.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        UserFn.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        UserEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initColumns();
        UserService Userr = new UserService();
        UserListTable.setItems(Userr.getAllUsers());
    }    

    @FXML
    private void OnUpdate(ActionEvent event) throws SQLException {
        System.out.println("hello world");
        
        User U = UserListTable.getSelectionModel().getSelectedItem();
        UserService USER = new UserService();
        tfUserLn.setText(U.getNom());
        tfUserFn.setText(U.getPrenom());
        tfUserEmail.setText(U.getEmail());
        /*
        USER.modifier(U,U.getId());
        UserListTable.getItems().clear();
        UserListTable.setItems(USER.getAllUsers());
        
        UPDbtn.setDisable(true);
        delBtn.setDisable(true);*/
    }

    @FXML
    private void OnDelete(ActionEvent event) throws SQLException {
        
        UserService Userr = new UserService();
        System.out.println("*****");
        System.out.println(UserListTable.getSelectionModel().getSelectedItem().getId());
        Userr.supprimer(UserListTable.getSelectionModel().getSelectedItem().getId());
        UserListTable.getItems().clear();
        UserListTable.setItems(Userr.getAllUsers());
    }

    @FXML
    private void OnUpDate(ActionEvent event) throws SQLException {
        
        User U = UserListTable.getSelectionModel().getSelectedItem();
        UserService USER = new UserService();
        User MU = new User(U.getId(),tfUserLn.getText(),tfUserFn.getText(),tfUserEmail.getText());
        System.out.println(U.getId());
        USER.modifier(MU);
        UserListTable.getItems().clear();
        UserListTable.setItems(USER.getAllUsers());
    }

    @FXML
    private void OnPdf(ActionEvent event) {
        Document document = new Document();
        UserService USER = new UserService();
      try
      {
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AdminList.pdf"));
         document.open();
         document.add(new Paragraph((Chunk) USER.getAllUsers()));
         document.close();
         writer.close();
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
   }

}
