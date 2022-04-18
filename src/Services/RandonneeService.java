/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Randonnee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Datasource;

/**
 *
 * @author MSI
 */
public class RandonneeService implements iRandonneeService {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet res;

    public RandonneeService() {
        con = Datasource.getInstance().getConnection();

    }

    @Override
    public void ajouterRandonnee(Randonnee r) {

        String requete = "INSERT INTO randonnee (nom_rando, destination, description, categorie_rando, date_rando, duree_rando, prix) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(requete);
            pst.setString(1, r.getNom_rando());
            pst.setString(2, r.getDestination());
            pst.setString(3, r.getDescription());
            pst.setString(4, r.getCategorie_rando());
            pst.setDate(5, r.getDate_rando());
            pst.setString(6, r.getDuree_rando());

            pst.setFloat(7, r.getPrix());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RandonneeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierRandonnee(Randonnee r, int id) {
        try {
            String requete = " update randonnee set nom_rando=? , destination=? , description=? , categorie_rando=?, date_rando=? ,duree_rando=? ,prix=?  where id='" + id + "'";
            pst = con.prepareStatement(requete);
            pst.setString(1, r.getNom_rando());
            pst.setString(2, r.getDestination());
            pst.setString(3, r.getDescription());
            pst.setString(4, r.getCategorie_rando());
            pst.setDate(5, r.getDate_rando());
            pst.setString(6, r.getDuree_rando());

            pst.setFloat(7, r.getPrix());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RandonneeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerRandonnee(int id) {
        try {
            String requete = " delete from randonnee where id='" + id + "'";
            pst = con.prepareStatement(requete);
            ste = con.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(RandonneeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Randonnee> afficherRandonnee() {
        String requete = "select * from  randonnee";
        ArrayList<Randonnee> list = new ArrayList<>();
        try {
            ste = con.createStatement();
            res = ste.executeQuery(requete);
            while (res.next()) {

                list.add(new Randonnee(res.getInt("id"), res.getString("nom_rando"), res.getString("destination"), res.getString("description"), res.getString("categorie_rando"), res.getDate("date_rando"), res.getString("duree_rando"), res.getFloat("prix")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(RandonneeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
   
   

}
