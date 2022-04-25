/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entites.Cadeau;
import Service.ServiceCadeau;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mezen Bayounes
 */
public class CadeauController implements Initializable {

    @FXML
    private TextField tf_id;
    @FXML
    private TextField text_Nom_Cadeau;
    @FXML
    private Button but_insert;
    @FXML
    private Button but_update;
    @FXML
    private Button but_delete;
   
    @FXML
    private TextField text_recherche_competition;
    @FXML
    private TextField text_Categorie_Cadeau;
   
    @FXML
    private TextField text_Description_Cadeau;
    @FXML
    private TableView<Cadeau> tv_Cadeau;
    @FXML
    private TableColumn<Cadeau, String> col_Nom_Cadeau;
    @FXML
    private TableColumn<Cadeau, String> col_Categorie_Cadeau;
    @FXML
    private TableColumn<Cadeau, String> col_Description_Cadeau;
    @FXML
    private TableColumn<Cadeau, Integer> col_Competition_Cadeau;
    @FXML
    private Label erdatecomp;
    @FXML
    private Label erprixcomp;
    @FXML
    private ComboBox<?> comboBox;
    ResultSet rs = null;
     Connection coonx = null;
    @FXML
    private Label errnomcad;
    @FXML
    private Label errcatgoriecad;
    @FXML
    private Label errdescriptioncad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCadeau();
      selectid();
    }    

 public void showCadeau(){
         ServiceCadeau serv = new ServiceCadeau();
        ObservableList<Cadeau> s = serv.getAll();
        
     
        
        col_Nom_Cadeau.setCellValueFactory(new PropertyValueFactory<Cadeau, String>("nom"));
        col_Categorie_Cadeau.setCellValueFactory(new PropertyValueFactory<Cadeau, String>("categorie"));
        col_Description_Cadeau.setCellValueFactory(new PropertyValueFactory<Cadeau, String>("description"));
        col_Competition_Cadeau.setCellValueFactory(new PropertyValueFactory<Cadeau, Integer>("competition"));
       // col_id.setCellValueFactory(new PropertyValueFactory<Cadeau, Integer>("id"));
        
        
        tv_Cadeau.setItems(s);
    }
    @FXML
    private void tableClicked(MouseEvent event) {
                     Cadeau rec = tv_Cadeau.getSelectionModel().getSelectedItem();

                tf_id.setText(Integer.toString(rec.getId()));
            text_Nom_Cadeau.setText((rec.getNom()));
            text_Description_Cadeau.setText((rec.getDescription()));
         

            text_Categorie_Cadeau.setText((rec.getCategorie()));

                
    }

    @FXML
    private void addCadeau(MouseEvent event) {
          ServiceCadeau serv = new ServiceCadeau();
           if (text_Nom_Cadeau.getText().isEmpty())
         {
                        errnomcad.setText("nom Invalide");
                        errnomcad.setVisible(true);
                  
                        return;
                    }  if (text_Categorie_Cadeau.getText().isEmpty())
         {
                        errcatgoriecad.setText("categorie Invalide");
                        errcatgoriecad.setVisible(true);
                  
                        return;
                    }  if (text_Description_Cadeau.getText().isEmpty())
         {
                        errdescriptioncad.setText("description Invalide");
                        errdescriptioncad.setVisible(true);
                  
                        return;
                    } 
               Cadeau f=new Cadeau(text_Nom_Cadeau.getText(),text_Categorie_Cadeau.getText(), text_Description_Cadeau.getText(),parseInt(comboBox.getValue().toString()));

        System.out.println(parseInt(comboBox.getValue().toString()));
        
       
        serv.addCadeau(f);
           showCadeau();
    }
 private void selectid() {
        try {

             coonx = Utils.Database.getInstanceConnex().getConnection();

              rs = coonx.createStatement().executeQuery("select id from Competition");
            
            ObservableList List = FXCollections.observableArrayList();
            while (rs.next()) {
            
            List.add(rs.getString(1));
            }
            
            comboBox.setItems(List);
            
      } catch (Exception ex) {
            System.out.println("error while inserting record. ");
        }
        
        
    }
 
    @FXML
    private void updateCadeau(MouseEvent event) {
         ServiceCadeau serv = new ServiceCadeau();
               Cadeau f=new Cadeau(text_Nom_Cadeau.getText(),text_Categorie_Cadeau.getText(), text_Description_Cadeau.getText(),parseInt(comboBox.getValue().toString()));

        System.out.println(parseInt(comboBox.getValue().toString()));
        
       
        serv.updateCadeau(f);
           showCadeau();
    }

    @FXML
    private void deleteCadeau(MouseEvent event) {
                 ServiceCadeau s= new ServiceCadeau();

          s.deleteCadeau(col_Nom_Cadeau.getText());
        showCadeau();
        
    }
    
}
