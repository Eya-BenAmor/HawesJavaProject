/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Admin;
import Services.AdminService;
import static Controller.LoginController.dashBStage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.FormulaEvaluator;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class AdminListController implements Initializable {

    @FXML
    private AnchorPane MainAnchor;
    @FXML
    private TableView<Admin> AdminListTable;
    @FXML
    private TableColumn<Admin, String> AdminLn;
    @FXML
    private TableColumn<Admin, String> AdminFn;
    @FXML
    private TableColumn<Admin, String> AdminEmail;
    @FXML
    private Button updBtn;
    @FXML
    private Button delBtn;
    @FXML
    private TextField tfAdminLn;
    @FXML
    private TextField tfAdminFn;
    @FXML
    private TextField tfAdminEmail;
    @FXML
    private Button UPDbtn;
    @FXML
    private TextField tfAdminCin;
    @FXML
    private TableColumn<Admin, Double> AdminCin;
    @FXML
    private Button addBtn;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button searchBtn;
    @FXML
    private Label ConnAdmin;

    public void initColumns() {
        
        AdminLn.setCellValueFactory(new PropertyValueFactory<Admin, String>("Nom"));
        AdminFn.setCellValueFactory(new PropertyValueFactory<Admin, String>("Prenom"));
        AdminCin.setCellValueFactory(new PropertyValueFactory<Admin, Double>("Cin"));
        AdminEmail.setCellValueFactory(new PropertyValueFactory<Admin, String>("Email"));
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initColumns();
        AdminService Admin = new AdminService();
        AdminListTable.setItems(Admin.getAllAdmins());
        ConnAdmin.setText(LoginController.connectedAdmin.getPrenom());

    }    

    @FXML
    private void OnUpdate(ActionEvent event) throws SQLException {
        Admin U = AdminListTable.getSelectionModel().getSelectedItem();
        AdminService Admin = new AdminService();
        tfAdminLn.setText(U.getNom());
        tfAdminFn.setText(U.getPrenom());
        tfAdminEmail.setText(U.getEmail());
        tfAdminCin.setText(U.getCin());
    }

    @FXML
    private void OnDelete(ActionEvent event) throws SQLException {
        AdminService Admin = new AdminService();
        System.out.println("*****");
        System.out.println(AdminListTable.getSelectionModel().getSelectedItem().getId());
        Admin.supprimer(AdminListTable.getSelectionModel().getSelectedItem().getId());
        AdminListTable.getItems().clear();
        AdminListTable.setItems(Admin.getAllAdmins());
    }

    @FXML
    private void OnUpDate(ActionEvent event) throws SQLException {
        Admin U = AdminListTable.getSelectionModel().getSelectedItem();
        AdminService Admin = new AdminService();
        
        
        Admin AU = new Admin(U.getId(),tfAdminLn.getText(),tfAdminFn.getText(),tfAdminEmail.getText(),U.getMdp(),U.getCin(),U.getConfirm_mdp());
        System.out.println(U.getId());
        Admin.modifier(AU);
        AdminListTable.getItems().clear();
        AdminListTable.setItems(Admin.getAllAdmins());
        
    }

    @FXML
    private void OnAdd(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddAdmin.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        dashBStage = stage;
        stage.show();
    }

    @FXML
    private void OnSearch(ActionEvent event) {
        AdminService Admin = new AdminService();
        String key = tfSearch.getText();
        AdminListTable.setItems(Admin.searchAdmins(key));
    }

    @FXML
    private void OnSearching(InputMethodEvent event) {
        AdminService Admin = new AdminService();
        String key = tfSearch.getText();
        AdminListTable.setItems(Admin.searchAdmins(key));
    }

    @FXML
    private void Search(KeyEvent event) {
        AdminService Admin = new AdminService();
        String key = tfSearch.getText();
        AdminListTable.getItems().clear();
        AdminListTable.setItems(Admin.searchAdmins(key));
    }

    @FXML
    private void OnHome(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("BackOffice.fxml"));
        Scene scene = new Scene(page1, 900, 544);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Admins List");
        stage.setScene(scene);
        stage.show();
    }

    

}

