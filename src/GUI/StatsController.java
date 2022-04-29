package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import Entities.Formation;
import Services.ServiceFormation;

/**
 * FXML Controller class
 *
 * @author seifi
 */
public class StatsController implements Initializable {

    @FXML
    private PieChart chartfor;
    @FXML
    private Button domaine;
    @FXML
    private Button equipe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
     
 
      
    }   

    @FXML
    private void domaine(ActionEvent event) {
         ServiceFormation serv = new ServiceFormation();
        Integer s1 = serv.Stats("FullStack","domaine");
        Integer s2 = serv.Stats("Santé","domaine");
        Integer s3 = serv.Stats("qqqq","domaine");
        Integer s4 = serv.Stats("Doine","domaine");
        System.out.println(s1 +""+s2+""+s3+""+s4);


     ObservableList<PieChart.Data> PieChartData
             =FXCollections.observableArrayList(
                     new PieChart.Data("FullStack",s1),
                     new PieChart.Data("Santé",s2),
                     new PieChart.Data("Doine",s3)
                   

             );
     chartfor.setData(PieChartData);
     
    }

    @FXML
    private void equipe(ActionEvent event) {
         ServiceFormation serv = new ServiceFormation();
        Integer s1 = serv.Stats("EquipeA","nomform");
        Integer s2 = serv.Stats("EquipeB","nomform");
        Integer s3 = serv.Stats("EquipeC","nomform");
       ;
        System.out.println(s1+""+s2+""+s3);


     ObservableList<PieChart.Data> PieChartData
             =FXCollections.observableArrayList(
                     new PieChart.Data("EquipeA",s1),
                     new PieChart.Data("EquipeB",s2),
                     new PieChart.Data("EquipeC",s3)
                  

             );
     chartfor.setData(PieChartData);
     
    }
    
    
     
        
  
    
}
