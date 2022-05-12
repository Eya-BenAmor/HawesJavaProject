/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Datasource;
import Entity.User;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Yahya
 */
public class UserService {

    Connection cnx= Datasource.getInstance().getConnection();

    
    public void ajouter(User u) throws SQLException {
        //request 
        String req="INSERT INTO `user`(`Nom`, `Prenom`, `email`, `mdp`, `confrim_mdp`) "
                + "VALUES (?,?,?,?,?)";

        
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setString(1,u.getNom());
            pst.setString(2,u.getPrenom());
            pst.setString(3,u.getEmail());
            pst.setString(4,u.getMdp());
            pst.setString(5,u.getConfirmMdp());
                         
            pst.executeUpdate();
            System.out.println("Utilisateur ajouter avec Succes");
        
    }

    public void modifier(User u) throws SQLException {
            //request 
        String req="UPDATE `user` SET `Nom`=?, `Prenom`=? , `email` = ?, "
                + "`mdp` =?, `confrim_mdp`=? WHERE `id`=?";

        
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setString(1,u.getNom());
            pst.setString(2,u.getPrenom());
            pst.setString(3,u.getEmail());
            pst.setString(4,u.getMdp());
            pst.setString(5,u.getConfirmMdp());
            pst.executeUpdate();
            System.out.println("Modification terminer avec Succes");
    }

    public void supprimer(int id) throws SQLException {
        //request 
        String req="DELETE FROM `user` WHERE id=?";

        
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setInt(1,id);            
            pst.executeUpdate();
            
        
            System.out.println("User Supprimer avec Succes");
        
    }

    public ObservableList<User> getAllUsers() {
        //LIST
        ObservableList<User> utilisateurs = FXCollections.observableArrayList();;
        try {
            Statement st = cnx.createStatement();

            String req = "select * from user";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setNom(rs.getString(3));
                u.setPrenom(rs.getString(4));
                u.setEmail(rs.getString(2));
                utilisateurs.add(u);
            }

            return utilisateurs;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
    
    public String getEmailutilisateur(String nom) throws SQLException{
    String email="";
    
    String req="SELECT email FROM `user` WHERE nom=? ";
           
            //insert
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setString(1,nom);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                 email=rs.getString(1);
            }
    
    return email;
    }
    public boolean verifierLogin(String email,String mdp)throws SQLException{
    
    String req="SELECT * FROM `user` WHERE email=? AND mdp=? ;";
            int id=-1;
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setString(1,email);
            pst.setString(2,mdp);            
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                id=rs.getInt(1);
                System.out.println(id);
            }
            if(id!=-1)
                return true;
            
            return false;
    }
        
    public User getUser(String email) throws SQLException{
    User u=new User();
    
    String req="SELECT * FROM `user` WHERE email=? ;";
           
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setString(1,email);

            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
               u.setId(rs.getInt(1));
               u.setNom(rs.getString(2));
               u.setPrenom(rs.getString(3));
               u.setEmail(rs.getString(4));
               u.setMdp(rs.getString(5));
               u.setConfirmMdp(rs.getString(6));
            }
    return u;
    }
    
    
    public List<User> afficherUser() throws SQLException {
        //LIST
        List<User> utilisateurs = new ArrayList<>();
        //request 
        String req ="SELECT * FROM user";

            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                utilisateurs.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
                
            }
          
        return utilisateurs;
    }
    
    public List<User> rechercher(String chercher) throws SQLException {
        //LIST
        List<User> utilisateurs = new ArrayList<>();
        //request 
        String req ="SELECT * FROM user where nom like '%"+chercher+"%' OR prenom like '%"+chercher+"%' or email like '%"+chercher+"%' or mdp like '%"+chercher+"%' ";

            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                utilisateurs.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
                
            }
          
        return utilisateurs;
    }
}
