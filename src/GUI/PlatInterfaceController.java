package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import Entities.Pdf;
import Entities.Plat;
import Services.ServicePlat;
import utils.Datasource;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class PlatInterfaceController implements Initializable {
     private final ObservableList<Plat> list = FXCollections.observableArrayList();

    @FXML
    private TextField nomfiled;
    @FXML
    private TextField descriptionfiled;
    @FXML
    private TextField prixflid;
    @FXML
    private TableView<Plat> plattv;
    @FXML
    private TableColumn<Plat, String> nomtv;
    @FXML
    private TableColumn<Plat, String> descriptiontv;
    @FXML
    private TableColumn<Plat, Integer> prixtv;
    @FXML
    private Button supprimerplat;
    @FXML
    private Button modifierplat;
    @FXML
    private TextField recherche;
    @FXML
    private Button bnt_imprimer;
    @FXML
    private Button actualiserb;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        showplat();
       
       
    }    
public void showplat() {
    list.clear();
    
            ServicePlat serv = new ServicePlat();
        ObservableList<Plat> s = (ObservableList<Plat>) serv.getAllForm();
         list.setAll(s);
     
        nomtv.setCellValueFactory(new PropertyValueFactory<Plat, String>("nom"));
        descriptiontv.setCellValueFactory(new PropertyValueFactory<Plat, String>("description"));
        prixtv.setCellValueFactory(new PropertyValueFactory<Plat, Integer>("prix"));
         
        plattv.setItems(s);
          RechercheAV();
       
      
        
  
     
    }     

    @FXML
    private void supprimerplat(ActionEvent event) {
          ServicePlat s= new ServicePlat();
        s.deletePlat( plattv.getSelectionModel().getSelectedItem().getNom());
      
        showplat();
    }

   
    

    @FXML
    private void ajouterplatt(ActionEvent event) {
           
        ServicePlat serv = new ServicePlat();
        Plat f=new Plat(nomfiled.getText(),descriptionfiled.getText(),Integer.parseInt(prixflid.getText().toString()));
        serv.addPlat(f);
      showplat();
    }

    @FXML
    private void modifierplatt(ActionEvent event) {
        Plat f=new Plat(nomfiled.getText(),descriptionfiled.getText(),Integer.parseInt(prixflid.getText().toString()));
        System.out.println(f);
         ServicePlat serv = new ServicePlat();
         serv.updatePlat(f,plattv.getSelectionModel().getSelectedItem().getNom());
           showplat();
    }
    public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Plat> filteredData = new FilteredList<>(list, b -> true);
		
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
		SortedList<Plat> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(plattv.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		plattv.setItems(sortedData);
    }

    @FXML
    private void imprimer(ActionEvent event)throws FileNotFoundException, SQLException, DocumentException{ 
       
            
         Connection conn = Datasource.getInstance().getConnection();
        try{
            
            JasperDesign jasperDesign = JRXmlLoader.load("D:\\desktop\\hawes desk\\src\\report\\Report.jrxml");  
            
        String sql="SELECT plat.id, plat.nom,plat.description, plat.prix FROM plat ";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jasperDesign.setQuery(newQuery);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            JasperViewer.viewReport(jasperPrint,false);           
        } catch (JRException ex) {
             System.out.println(ex.getMessage());

    }
    }
    @FXML
    private void actualiser(ActionEvent event) {
           nomfiled.setText("");
        descriptionfiled.setText("");
        prixflid.setText("");
        showplat() ;
       
    }

    @FXML
    private void Quitter(ActionEvent event) {
             System.exit(0);
    }
       
        

}
    
    


