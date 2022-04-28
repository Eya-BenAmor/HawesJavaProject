/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class StatRandoController implements Initializable {

    @FXML
    private PieChart piechart1;
private Statement st;
    private ResultSet rs;
    private Connection cnx;
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cnx = utils.Datasource.getInstance().getConnection();
        stat();
    }    
    private void stat() {

        try {

            String query = "SELECT COUNT(*),categorie_rando FROM randonnee GROUP BY categorie_rando";

            PreparedStatement PreparedStatement = cnx.prepareStatement(query);
            rs = PreparedStatement.executeQuery();

            while (rs.next()) {
                data.add(new PieChart.Data(rs.getString("categorie_rando"), rs.getInt("COUNT(*)")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RandoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        piechart1.setTitle("Statistiques des différents des catégories");
        piechart1.setLegendSide(Side.LEFT);
        piechart1.setData(data);

    } 
}
