/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entities.Reclamation;
import Entities.Reponse;
import static GUI.LoginController.connectedUser;
import Services.ReclamationService;
import Services.ReponseService;
import Services.ServiceSysdate;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import net.glxn.qrgen.QRCode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tray.notification.NotificationType;
import tray.notification.TrayNotification;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.ZoneId;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author EYA
 */
public class ReclamationController implements Initializable {

  private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextArea ta_desc;
    @FXML
    private DatePicker dp_date;
    @FXML
    private ImageView iv_image;


    ReclamationService rs = new ReclamationService();

    @FXML
    private TextField tf_image;
    @FXML
    private Button button_image;
    @FXML
    private Button but_insert;
    @FXML
    private Button clear;


    
    private final ObservableList<Reclamation> list = FXCollections.observableArrayList();

 
    @FXML
    private Label erreurdate;
    private TableColumn<Reclamation,String> col_nom;
    @FXML
    private TextArea ta_nom;
    @FXML
    private Label erreurnom;
 
    @FXML
    private Label erreurdesc;
    @FXML
    private Label erreurim;
    
   
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iv_image.setImage(new Image("/img/upload.jpg"));
    
    }
    
     ServiceSysdate sys = new ServiceSysdate();
     

   
    @FXML
    private void addReclamation(MouseEvent event) throws ParseException, IOException {
        File f = new File(tf_image.getText());

        System.out.println(dp_date.getValue().getYear());
        Date d = java.sql.Date.valueOf(dp_date.getValue());
        Reclamation r = new Reclamation(ta_nom.getText(),ta_desc.getText(), d, tf_image.getText(), connectedUser.getNom());// i set the client statically, it has to be changed later in the integration 
        
       
          Date dateo = Date.valueOf(dp_date.getValue());
          
           // controle de saisie date doit etre la date actuelle
                 if (!dateo.equals(sys.selectDate())) {
                        erreurdate.setText("la date doit être date actuelle");
                        erreurdate.setVisible(true);
                        return;
                 
                 }
                  // controle de saisie sur la description non vide et sup à 4 lettres
                  
                       if ((ta_desc.getText().isEmpty())) {
                        erreurdesc.setText("Description Invalide");
                        erreurdesc.setVisible(true);
                  
                        return;
                    } 
                       // controle de saisie image non vide
                       if (tf_image.getText().isEmpty()) {
                        erreurim.setText("Veuillez ajouter une image");
                        erreurim.setVisible(true);
                  
                        return;
                    }
                          // controle de saisie nom 
                       if (ta_nom.getText().isEmpty()) {
                        erreurnom.setText("nom vide");
                        erreurnom.setVisible(true);
                  
                        return;
                    }
                       
                     
                       else {
                         

        rs.ajouterReclamation(r);
 
        
        
  
        
          //Files.copy(Paths.get(tf_image.getText()),Paths.get("C:\\Users\\Eya\\Downloads\\"+f.getName()),REPLACE_EXISTING);
           QRcode(r);
           
               TrayNotification tray = new TrayNotification();
        tray.setTitle("Ajout");
        tray.setMessage(" Reclamation ajouté avec succès");
        tray.setNotificationType(NotificationType.WARNING);
        tray.showAndWait();
                       }
 
    }
 public static String projectPath = System.getProperty("user.dir").replace("\\", "/");
    private void QRcode(Reclamation r) throws FileNotFoundException, IOException {
        String contenue = "Description : " + r.getDescription()+ "\n"+"Nom : " + r.getNom()+ "\n"+ "Date: " + r.getDate_reclamation().toString(); 
        ByteArrayOutputStream out = QRCode.from(contenue).to(net.glxn.qrgen.image.ImageType.JPG).stream();
        File f = new File(projectPath + "\\src\\qr\\" + r.getNom().toString()+ ".jpg");
        FileOutputStream fos = new FileOutputStream(f); //creation du fichier de sortie
        fos.write(out.toByteArray()); //ecrire le fichier du sortie converter
        fos.flush(); // creation final

    }
    @FXML
    private void uploadImage(MouseEvent event) {
        FileChooser fc = new FileChooser();
           fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        String path = fc.showOpenDialog(button_image.getScene().getWindow()).getPath();
        tf_image.setText(path);
        File file = new File(tf_image.getText());
        Image image = new Image(file.toURI().toString());
        iv_image.setImage(image);
        

    }
   
 
    
   

    @FXML
    private void clearFields(javafx.event.ActionEvent event) {
     
        tf_image.setText("");
        ta_desc.setText("");
        ta_nom.setText("");
        dp_date.setValue(null);
        iv_image.setImage(null);
        erreurnom.setText("");
        erreurdesc.setText("");
        erreurim.setText("");
        erreurdate.setText("");
        
    }

 
   
    }
    
