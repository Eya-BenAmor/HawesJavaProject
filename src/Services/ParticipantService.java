/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Participant;
import Entities.Randonnee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Datasource;

/**
 *
 * @author MSI
 */
public class ParticipantService implements iParticipantService {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet res;

    public ParticipantService() {
        con = Datasource.getInstance().getConnection();

    }

    @Override
    public void ajouterParticipant(Participant p) {
        String requete = "INSERT INTO participant (age, tel, maladie, classe, randonnee_id, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(requete);
            pst.setInt(1, p.getAge());
            pst.setString(2, p.getTel());
            pst.setString(3, p.getMaladie());
            pst.setString(4, p.getClasse());
            pst.setInt(5, p.getId_randonnee());
            pst.setInt(6, p.getId_user());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ParticipantService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierParticipant(Participant p, int id) {
        try {
            String requete = " update participant set age=?, tel=?, maladie=?, classe=?  where id='" + id + "'";
            pst = con.prepareStatement(requete);
            pst.setInt(1, p.getAge());
            pst.setString(2, p.getTel());
            pst.setString(3, p.getMaladie());
            pst.setString(4, p.getClasse());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParticipantService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerParticipant(int id) {
        try {
            String requete = " delete from participant where id='" + id + "'";
            pst = con.prepareStatement(requete);
            ste = con.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ParticipantService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Participant> afficherParticipant() {
        String requete = "select * from  participant";
        ArrayList<Participant> list = new ArrayList<>();
        try {
            ste = con.createStatement();
            res = ste.executeQuery(requete);
            while (res.next()) {

                list.add(new Participant(res.getInt("id"), res.getInt("age"), res.getString("tel"), res.getString("maladie"), res.getString("classe"), res.getInt("randonnee_id"), res.getInt("user_id")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ParticipantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
  
    
}
