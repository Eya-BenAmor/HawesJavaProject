/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceCadeau;
import Services.ServiceFormation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class StatCadeauController implements Initializable {

    @FXML
    private PieChart chartfor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Cadeau();
        
    }    
    
    
    @FXML
    private void Cadeau() {
         ServiceCadeau serv = new ServiceCadeau();
        Integer s1 = serv.Stats("JEU","categorie_cadeau");
        Integer s2 = serv.Stats("TECH","categorie_cadeau");
        Integer s3 = serv.Stats("ARGENT","categorie_cadeau");
        System.out.println(s1 +""+s2+""+s3+"");


     ObservableList<PieChart.Data> PieChartData
             =FXCollections.observableArrayList(
                     new PieChart.Data("JEU",s1),
                     new PieChart.Data("TECH",s2),
                     new PieChart.Data("ARGENT",s3)
                   

             );
     chartfor.setData(PieChartData);
     
    }

    
}
