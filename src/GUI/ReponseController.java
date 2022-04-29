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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.MailerService;

/**
 * FXML Controller class
 *
 * @author EYA
 */
public class ReponseController implements Initializable {

    @FXML
    private TableView<Reclamation> tv_reclamation;
    @FXML
    private TableColumn<Reclamation, Integer> col_id;
    @FXML
    private TableColumn<Reclamation, Date> col_date;
    @FXML
    private TableColumn<Reclamation, String> col_desc;
    @FXML
    private TableColumn<Reclamation, ImageView> col_image;
    @FXML
    private TableColumn<Reclamation, String> col_clientId;
    @FXML
    private TextArea ta_reponse;
    @FXML
    private TextField tf_idReclamation;
    @FXML
    private Button btn_ReponseConfirm;
    @FXML
    private TableView<Reponse> tv_reponse;
    @FXML
    private TableColumn<Reponse, Integer> Col_id_Rec;
    @FXML
    private TableColumn<Reponse, String> col_text;
 
 
    @FXML
    private Label erreur;
    @FXML
    private TextField ta_id;
    @FXML
    private TableColumn<Reclamation,String> col_nom;
    @FXML
    private TextField tf_nomrec;
    @FXML
    private TableColumn<Reponse,String> col_nom_rec;
    @FXML
    private TextField recherche;
    @FXML
    private Button but_delete;


    @FXML
    private TextField tf_id;
    @FXML
    private Button btn_ReponseModifier;
    @FXML
    private TextField clientmail;
    @FXML
    private Button mailbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showReclamation();
    }   
    ReponseService rs =new ReponseService();
     ReponseService recs =new ReponseService();

    public void showReclamation(){
        ObservableList<Reclamation> list = new ReclamationService().readAllForAdmin();//we statically set the client id to just show his reclamations
         col_id.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("nom"));
        col_desc.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
        col_date.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date_reclamation"));
        col_image.setCellValueFactory(new PropertyValueFactory<Reclamation, ImageView>("img"));
        col_clientId.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("client"));
      
        tv_reclamation.setItems(list);
    }
    @FXML
    private void tableClicked(MouseEvent event) {
        
        Reclamation rec = tv_reclamation.getSelectionModel().getSelectedItem();
        
        tf_idReclamation.setText(Integer.toString(rec.getId()));
          tf_nomrec.setText(rec.getNom());
          tf_id.setText(Integer.toString(rec.getId()));
        
        showRepo(rec.getId());

    }
    
        public void showRepo(int id){
        ObservableList<Reponse> list = new ReponseService().afficherReponse(id);
        Col_id_Rec.setCellValueFactory(new PropertyValueFactory<Reponse, Integer>("id"));
        col_text.setCellValueFactory(new PropertyValueFactory<Reponse, String>("text"));
        col_nom_rec.setCellValueFactory(new PropertyValueFactory<Reponse, String>("nom_rec"));
        tv_reponse.setItems(list);
    }

    @FXML
    private void addReponse(MouseEvent event) {
         Reclamation rec = tv_reclamation.getSelectionModel().getSelectedItem();
        Reponse r = new Reponse(ta_reponse.getText(), Integer.parseInt(tf_idReclamation.getText()),tf_nomrec.getText());
          if ((ta_reponse.getText().isEmpty())) {
                        erreur.setText(" la réponse est vide ");
                        erreur.setVisible(true);
                  
                        return;
                    } 
        rs.addReponse(r);
          showRepo(rec.getId());

          new Alert(Alert.AlertType.INFORMATION, "Réponse ajouté").show();

        ta_reponse.setText("");
          TrayNotification tray = new TrayNotification();
        tray.setTitle("Ajout");
        tray.setMessage(" Réponse ajouté avec succès");
        tray.setNotificationType(NotificationType.WARNING);
        tray.showAndWait();
    }


    @FXML
    private void btnSupprimer(ActionEvent event) throws SQLException {
           Reclamation rec = tv_reclamation.getSelectionModel().getSelectedItem();  
          rs.deleteReponse(ta_id.getText());
           new Alert(Alert.AlertType.INFORMATION, "Réponse supprimé").show();
           showRepo(rec.getId());
           
             TrayNotification tray = new TrayNotification();
        tray.setTitle("Suppression");
        tray.setMessage(" Réponse supprimé avec succès");
        tray.setNotificationType(NotificationType.WARNING);
        tray.showAndWait();
      

    }

  

    @FXML
    private void modifierReponse(MouseEvent event) {
           
     Reclamation rec = tv_reclamation.getSelectionModel().getSelectedItem();
     
       Reponse r = new Reponse(Integer.parseInt(ta_id.getText()), ta_reponse.getText());
       
  rs.updateReponse(r);
       new Alert(Alert.AlertType.INFORMATION, "Réponse modifié").show();
         showRepo(rec.getId());
          
          TrayNotification tray = new TrayNotification();
        tray.setTitle("Modification");
        tray.setMessage(" Réponse modifié avec succès");
        tray.setNotificationType(NotificationType.WARNING);
        tray.showAndWait();
     
        
      
       
    }

   @FXML
    private void clicked(MouseEvent event) {
               Reclamation rec = tv_reclamation.getSelectionModel().getSelectedItem();
       
         Reponse rep = tv_reponse.getSelectionModel().getSelectedItem();
          ta_id.setText(Integer.toString(rep.getId()));
         tf_nomrec.setText(rep.getNom_rec());
         
         ta_reponse.setText(rep.getText());
        
      
        showRepo(rec.getId());
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

 private final ObservableList<Reclamation> list = FXCollections.observableArrayList();
  public void showReclamations() {
        list.clear();
        ObservableList<Reclamation> lista = new ReclamationService().readAll(connectedUser.getNom());// statically set the client id to just show his reclamations
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
    private void mail(ActionEvent event) {
                
            MailerService m=new MailerService();
    m.replyMail(clientmail.getText(), "User", "Reclamation traitée", "Bonjour ! votre réclamation a été traitéé");
    }
    
  

  
     

      
}
