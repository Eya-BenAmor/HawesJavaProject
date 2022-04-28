/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entities.Reclamation;
import Entities.Reponse;
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
    private TextField tf_id;
    @FXML
    private TextArea ta_desc;
    @FXML
    private DatePicker dp_date;
    @FXML
    private ImageView iv_image;
    @FXML
    private TableColumn<Reclamation, Integer> col_id;
    @FXML
    private TableColumn<Reclamation, Date> col_date;
    @FXML
    private TableColumn<Reclamation, String> col_desc;
    @FXML
    private TableColumn<Reclamation, ImageView> col_image;

    ReclamationService rs = new ReclamationService();
    @FXML
    private TableView<Reclamation> tv_reclamation;
    @FXML
    private TextField tf_image;
    @FXML
    private Button button_image;
    @FXML
    private Button but_insert;
    @FXML
    private Button but_update;
    @FXML
    private Button but_delete;
    @FXML
    private Button clear;

    public static int rec_id_for_rep;
    @FXML
    private TableView<Reponse> tv_reponse;
    @FXML
    private TableColumn<Reponse, Integer> Col_id_Rec;
    @FXML
    private TableColumn<Reponse, String> col_text;
    @FXML
    private TextField recherche;
    
    private final ObservableList<Reclamation> list = FXCollections.observableArrayList();
  @FXML
    private Label erreur;
    @FXML
    private Label erreurimage;
    @FXML
    private Label erreurdate;
    @FXML
    private TableColumn<Reclamation,String> col_nom;
    @FXML
    private TextArea ta_nom;
    
   
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iv_image.setImage(new Image("/img/upload.jpg"));
        showReclamations();
    }
    
     ServiceSysdate sys = new ServiceSysdate();
     
    public void showReclamations() {
        list.clear();
        ObservableList<Reclamation> lista = new ReclamationService().readAll(1);// statically set the client id to just show his reclamations
        list.setAll(lista);
        col_id.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("nom"));
        col_desc.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
        col_date.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date_reclamation"));
        col_image.setCellValueFactory(new PropertyValueFactory<Reclamation, ImageView>("img"));
        tv_reclamation.setItems(list);
        RechercheAV();
    }

    public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Reclamation> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(tmp -> {
				// If filter text is empty, display all reclamations.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare  with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (tmp.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches description.
				} else if (String.valueOf(tmp.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tv_reclamation.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tv_reclamation.setItems(sortedData);
    }    
    @FXML
    private void addReclamation(MouseEvent event) throws ParseException, IOException {
        File f = new File(tf_image.getText());

        System.out.println(dp_date.getValue().getYear());
        Date d = java.sql.Date.valueOf(dp_date.getValue());
        Reclamation r = new Reclamation(ta_nom.getText(),ta_desc.getText(), d, tf_image.getText(), 1);// i set the client statically, it has to be changed later in the integration 
        
       
          Date dateo = Date.valueOf(dp_date.getValue());
          
           // controle de saisie date doit etre la date actuelle
                 if (!dateo.equals(sys.selectDate())) {
                        erreurdate.setText("vérifier que  la date est égale à la date actuelle");
                        erreurdate.setVisible(true);
                        return;
                 
                 }
                  // controle de saisie sur la description non vide et sup à 4 lettres
                  
                       if ((ta_desc.getText().length() < 4)||(ta_desc.getText().isEmpty())) {
                        erreur.setText("Description Invalide");
                        erreur.setVisible(true);
                  
                        return;
                    } 
                       // controle de saisie image non vide
                       if (tf_image.getText().isEmpty()) {
                        erreurimage.setText("Veuillez ajouter une image");
                        erreurimage.setVisible(true);
                  
                        return;
                    }
                          // controle de saisie nom 
                       if (ta_nom.getText().isEmpty()) {
                        erreurimage.setText("nom vide");
                        erreurimage.setVisible(true);
                  
                        return;
                    }
                       
                     
                       else {
                         

        rs.ajouterReclamation(r);
 
        
        
        showReclamations();
        
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
    private void updateReclamation(MouseEvent event) {
        Date d = java.sql.Date.valueOf(dp_date.getValue());
        Reclamation r = new Reclamation(Integer.parseInt(tf_id.getText()),ta_nom.getText() ,ta_desc.getText(), d, tf_image.getText(), 1);// i set the client statically, it has to be changed later in the integration 
 Date dateo = Date.valueOf(dp_date.getValue());
          
                  // controle de saisie sur la description non vide et sup à 4 lettres
                  
                       if ((ta_desc.getText().length() < 4)||(ta_desc.getText().isEmpty())) {
                        erreur.setText("Description Invalide");
                        erreur.setVisible(true);
                  
                        return;
                    } 
                       // controle de saisie image non vide
                       if (tf_image.getText().isEmpty()) {
                        erreurimage.setText("Veuillez ajouter une image");
                        erreurimage.setVisible(true);
                  
                        return;
                    }
                              // controle de saisie nom 
                       if (ta_nom.getText().isEmpty()) {
                        erreurimage.setText("nom vide");
                        erreurimage.setVisible(true);
                  
                        return;
                    }
                       else {
        rs.modifierReclamation(r);
         new Alert(Alert.AlertType.INFORMATION, "Réclamation modifié").show();
        showReclamations();}
                         TrayNotification tray = new TrayNotification();
        tray.setTitle("Modification");
        tray.setMessage(" Reclamation modifiée avec succès");
        tray.setNotificationType(NotificationType.WARNING);
        tray.showAndWait();
    }
    @FXML
    private void deleteReclamation(MouseEvent event) {
        rs.deleteReclamation(tf_id.getText());
         new Alert(Alert.AlertType.INFORMATION, "Réclamation supprimé").show();
        showReclamations();
             
        
            
              TrayNotification tray = new TrayNotification();
        tray.setTitle("Suppression");
        tray.setMessage(" Reclamation supprimé avec succès");
        tray.setNotificationType(NotificationType.WARNING);
        tray.showAndWait();
    }
  @FXML
    private void tableClicked(MouseEvent event) {
        Reclamation rec = tv_reclamation.getSelectionModel().getSelectedItem();
        tf_id.setText(Integer.toString(rec.getId()));
        tf_image.setText(rec.getImage());
        File file = new File(tf_image.getText());
        Image image = new Image(file.toURI().toString());
        iv_image.setImage(image);
        ta_desc.setText(rec.getDescription());
          ta_nom.setText(rec.getNom());
        int year = Integer.parseInt(rec.getDate_reclamation().toString().substring(0, 4));
        int month = Integer.parseInt(rec.getDate_reclamation().toString().substring(5,7));
        int day= Integer.parseInt(rec.getDate_reclamation().toString().substring(8,10));

        dp_date.setValue(LocalDate.of(year, month, day));
        showRepo(rec.getId());

    }
    
        public void showRepo(int id){
        ObservableList<Reponse> list = new ReponseService().afficherReponse(id);
        Col_id_Rec.setCellValueFactory(new PropertyValueFactory<Reponse, Integer>("id"));
        col_text.setCellValueFactory(new PropertyValueFactory<Reponse, String>("text"));
        tv_reponse.setItems(list);
    }

    @FXML
    private void clearFields(javafx.event.ActionEvent event) {
        tf_id.setText("");
        tf_image.setText("");
        ta_desc.setText("");
        ta_nom.setText("");
        dp_date.setValue(null);
        iv_image.setImage(null);
        erreur.setText("");
        erreurimage.setText("");
        erreurdate.setText("");
        
    }

 
   
    }
    
