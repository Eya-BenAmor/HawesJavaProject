/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Admin;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.Datasource;

/**
 *
 * @author Yahya
 */
public class AdminService {
    Connection cnx= Datasource.getInstance().getConnection();

    
    public void ajouter(Admin a) throws SQLException {
        //request 
        String req="INSERT INTO `admin`(`nom`, `prenom`, `email`, `mdp`, `cin`, `confirm_mdp`) "
                + "VALUES (?,?,?,?,?,?)";

        
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setString(1,a.getNom());
            pst.setString(2,a.getPrenom());
            pst.setString(3,a.getEmail());
            pst.setString(4,a.getMdp());
            pst.setString(5,a.getCin());
            pst.setString(6,a.getConfirm_mdp());
                         
            pst.executeUpdate();
            System.out.println("Admin ajouter avec Succes");
        
    }
    public void modifier(Admin a)  {
            //request 
        String req="UPDATE `admin` SET `Nom`=?, `Prenom`=? , `email` = ?, `mdp` =?, `cin` =?, `confirm_mdp`=? WHERE `id`=?";

        
            try{
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setString(1,a.getNom());
            pst.setString(2,a.getPrenom());
            pst.setString(3,a.getEmail());
            pst.setString(4,a.getMdp());
            pst.setString(5,a.getCin());
            pst.setString(6,a.getConfirm_mdp());
            pst.executeUpdate();
            }catch(SQLException e){}
            System.out.println("Modification terminer avec Succes");
    }

    public void supprimer(int id) throws SQLException {
        //request 
        String req="DELETE FROM `admin` WHERE id=?";

        
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setInt(1,id);            
            pst.executeUpdate();
            
        
            System.out.println("Admin Supprimer avec Succes");
        
    }

    public ObservableList<Admin> getAllAdmins() {
        //LIST
        ObservableList<Admin> admins = FXCollections.observableArrayList();;
        try {
            Statement st = cnx.createStatement();

            String req = "select * from admin";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                Admin a = new Admin();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setCin(rs.getString(6));
                admins.add(a);
            }

            return admins;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
    
    public String getEmailAdmin(String nom) throws SQLException{
    String email="";
    
    String req="SELECT email FROM `admin` WHERE nom=? ";
           
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
    
    String req="SELECT * FROM `admin` WHERE email=? AND mdp=? ;";
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
    
    public Admin getAdmin(int id) throws SQLException{
    Admin a =new Admin();
    
    String req="SELECT * FROM `admin` WHERE id=? ;";
           
            PreparedStatement pst =cnx.prepareStatement(req);
            pst.setInt(1,id);

            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
               a.setId(rs.getInt(1));
               a.setNom(rs.getString(2));
               a.setPrenom(rs.getString(3));
               a.setEmail(rs.getString(4));
               a.setMdp(rs.getString(5));
               a.setCin(rs.getString(6));
               a.setConfirm_mdp(rs.getString(7));


            }
    return a;
    }
    
    
        public ObservableList<Admin> searchAdmins(String key) {
        //LIST
        ObservableList<Admin> admins = FXCollections.observableArrayList();;
        try {
            Statement st = cnx.createStatement();

            String req = "select * from admin where nom LIKE '%"+key+"%' OR prenom LIKE '%"+key+"%' OR email LIKE '%"+key+"%'";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                Admin a = new Admin();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setCin(rs.getString(6));
                admins.add(a);
            }

            return admins;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
        
        public ObservableList<Admin> excel(){
            
                Admin a = new Admin();

                ObservableList<Admin> listeone=FXCollections.observableArrayList();
        try {
            Statement pstmt = cnx.createStatement();
            String req = "select *from admin where id="+a.getId();
            ResultSet rs= pstmt.executeQuery(req);
       
        XSSFWorkbook kk =new XSSFWorkbook();
        
        XSSFSheet sheet = kk.createSheet("User details");
        XSSFRow head=sheet.createRow(0);
        head.createCell(0).setCellValue("Nom");
        head.createCell(1).setCellValue("Prenom");
        head.createCell(2).setCellValue("Email");
        
        int i=1;
         while (rs.next())
         {
            XSSFRow row =sheet.createRow(i);
            
            row.createCell(0).setCellValue(rs.getString(1));
            row.createCell(1).setCellValue(rs.getString(2));
            row.createCell(2).setCellValue(rs.getString(3));
            i++;
       
        FileOutputStream fileout=new FileOutputStream("Admindetails.xlsx");
        kk.write(fileout);
        fileout.close();
            
        }} catch (Exception e) {
            System.out.println("test");
        }
        return null;
 
    
}
}
