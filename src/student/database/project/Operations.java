/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.database.project;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author SIU856414916
 */
public class Operations {
    public static boolean studentLogin(int userID, String userType, JFrame frame){
        try{
          Connection myConn = MySQLConnection.getConnection();
          String mySqlQuery =
                  "SELECT sid FROM students WHERE sid = '"+
                  userID+
                  "'";
            PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                LoginSession.UID = resultSet.getInt("sid");
   
                return true;
            }
                  
        }catch(Exception e){
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
        }
        return false;
    }
    
    public static boolean facultyLogin(int userID, String userType, JFrame frame){
        try{
          Connection myConn = MySQLConnection.getConnection();
          String mySqlQuery =
                  "SELECT fid FROM faculty WHERE fid = '"+
                  userID+
                  "'";
            PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                LoginSession.UID = resultSet.getInt("fid");
   
                return true;
            }
                  
        }catch(Exception e){
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
        }
        return false;
    }
    
     public static boolean staffLogin(int userID, String userType, JFrame frame){
        try{
          Connection myConn = MySQLConnection.getConnection();
          String mySqlQuery =
                  "SELECT sid FROM staff WHERE sid = '"+
                  userID+
                  "'";
            PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                LoginSession.UID = resultSet.getInt("sid");
   
                return true;
            }
                  
        }catch(Exception e){
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
        }
        return false;
    }
     
     public static boolean addNewStudent(int userID, String userName, String majorType, String studentLevel, int ageOfStudent, JFrame frame){
         try{
             Connection myConn = MySQLConnection.getConnection();
             PreparedStatement pst = myConn.prepareStatement("INSERT into students (sid, sname, major, s_level, age) VALUES (?,?,?,?,?) ");
             
             pst.setInt(1, userID);
             pst.setString(2, userName);
             pst.setString(3, majorType);
             pst.setString(4, studentLevel);
             pst.setInt(5, ageOfStudent);
             
             pst.executeUpdate();
             
             return true;
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
         }
         return false;
     }
    
     public static boolean updateStudent(int id, int userID, String userName, String majorType, String studentLevel, int ageOfStudent, JFrame frame){
         
        try{
         Connection myConn = MySQLConnection.getConnection();
         PreparedStatement pst = myConn.prepareStatement("UPDATE students SET sid =?, sname=?,"
             + "major =?, s_level =?, age =? where id =?");
         PreparedStatement pst2 = myConn.prepareStatement("UPDATE courses SET cname =?, meets_at=?,"
             + "room =?");
         PreparedStatement pst3 = myConn.prepareStatement("UPDATE enrolled SET exam1 =?, exam2 =?,"
             + "final =?");
         PreparedStatement pst4 = myConn.prepareStatement("UPDATE faculty SET fname =?, fid =?");
         PreparedStatement pst5 = myConn.prepareStatement("UPDATE department SET dname =?, did =?,");
         
        
         pst.setInt(1, userID);
         pst.setString(2, userName);
         pst.setString(3, majorType);
         pst.setString(4, studentLevel);
         pst.setInt(5, ageOfStudent); 
         pst.setInt(6,id);
         pst.executeUpdate();
             
        return true;
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
        }
        return false;
    }
     public static boolean deleteStudent(int id, int userID, String userName, String majorType, String studentLevel, int ageOfStudent, JFrame frame){
         
        try{
         Connection myConn = MySQLConnection.getConnection();
         PreparedStatement pst = myConn.prepareStatement("DELETE FROM students where "
                 + "id =?");
         
        
         pst.setInt(1, id);
         pst.executeUpdate();
             
        return true;
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
        }
        return false;
    }
     public static boolean gradeBook(int userID, JFrame frame){
          try{
             Connection myConn = MySQLConnection.getConnection();
             PreparedStatement pst = myConn.prepareStatement("SELECT students.sid, courses.cid, courses.meets_at, courses.room, courses.fid, enrolled.exam1, enrolled.exam2, enrolled.final"
                     + "FROM students"
                     + "INNER JOIN enrolled ON students.sid = enrolled.sid"
                     + "INNER JOIN courses on courses.cid = enrolled.cid"
                     + "WHERE s.sid = '"+
                     userID+
                     "'");
            ResultSet resultSet = pst.executeQuery();
            
            while(resultSet.next()){
                return true;
            }
            
         }catch(Exception e){
             JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
         }
         return false;
     }
}

