/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class DAO {
    private Connection con ;


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public DAO () {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "abc123");
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void addIssues(String bookid , String userid ,String  returndate ) {
         String query = "INSERT INTO issues (bookid , userid, issuedate) VALUES (?,?,?)";
        
        try {
            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, bookid);
                ps.setString(2, userid);
                ps.setString(3,returndate);
                ps.execute();
                System.out.println("hi");
                
            } finally {
                con.close();
            }
        } catch (Exception ex) {
           Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateIssues(String bookid , String userid ,String  returndate )  {
       
        
         String query = "UPDATE issues SET  returndate = ? WHERE bookid = ? AND userid = ?";
        
        try {
            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, returndate);
                ps.setString(2, bookid);
                ps.setString(3,userid);
                ps.execute();
            } finally {
                con.close();
            }
        } catch (Exception ex) {
           Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateStatus (String bookid , String status) {
         String query = "UPDATE books SET status =\'" + status + "\' WHERE bookid =" + bookid;
        try {
            try {
             Statement s = con.prepareStatement(query);
             s.execute(query);
            } finally {
                con.close();
            }
        } catch (Exception ex) {
           Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
         public String updateDetailsFrame (String bookid) {
         System.out.println(bookid);
         String query = "SELECT userid FROM issues WHERE bookid =" + bookid;
         String id = "";
        try {
             try {
             Statement s = con.prepareStatement(query);
             ResultSet rs = s.executeQuery(query);
             while (rs.next()){
                id = rs.getString(1);
                 return id; 
             }
            } finally {
                con.close();
            }
        } catch (Exception ex) {
           Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return id;
    }
    
    public void addBooks (String title, String author, int price) {
        String query = "INSERT INTO books (title, author , price) VALUES (?, ?, ?)";
        try {
            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, title);
                ps.setString(2, author);
                ps.setInt(3, price);
                ps.execute();  
            } finally {
                con.close();
            }
        } catch (Exception ex) {
           Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DefaultTableModel insertBooks ()  {
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("Book Code");
        dm.addColumn("Title");
        dm.addColumn("Author");
        dm.addColumn("Status");
         String query = "SELECT bookid, title, author, status FROM books";
        try {
         
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            dm.setRowCount(0);

            while (rs.next()) {
               dm.addRow(new Object[] {rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)} );
            }
            con.close();
            
        } catch (Exception ex) {
            
        
        ex.printStackTrace();
        
        
        }
        return dm;
    } 
    
    public void setCustomers () {
        String query = "SELECT userid FROM users";
         try {
            try {
                Statement s = con.createStatement();
                ResultSet rs = s.executeQuery(query);
                while (rs.next()) {
                    int id = rs.getInt("userid");
                    System.out.println(id);
                } 
            } finally {
                con.close();
            }
        } catch (SQLException ex) {
           Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
           
        }
    }
      
    
}
