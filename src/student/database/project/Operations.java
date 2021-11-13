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
    /* Methods Used For LoginScreen and Student DBMS*/
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
     
     /* METHODS USED FOR GRADEBOOK */
     public static boolean registerNewStudent(int userID, int courseID, int examOne, int examTwo, int finalExam, JFrame frame){
         try{
             int records = 0;
             Connection myConn = MySQLConnection.getConnection();
             PreparedStatement pst2 = myConn.prepareStatement("SELECT COUNT(*) FROM enrolled WHERE enrolled.cid = ?");
             pst2.setInt(1, courseID);
             ResultSet rs = pst2.executeQuery();
             while (rs.next()) {
                 records = rs.getInt(1);
             }

             if (records < 10) {

                 PreparedStatement pst = myConn.prepareStatement("INSERT into enrolled (sid, cid, exam1, exam2, final) VALUES (?,?,?,?,?) ");

                 pst.setInt(1, userID);
                 pst.setInt(2, courseID);
                 pst.setInt(3, examOne);
                 pst.setInt(4, examTwo);
                 pst.setInt(5, finalExam);

                 pst.executeUpdate();
                 return true;
             } else {
                 JOptionPane.showMessageDialog(frame, "Maxed Enrollement. Please select another class!");
             }

         } catch (Exception e) {
             JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
         }
         return false;
     }
     
     public static boolean updateStudent(int userID, int courseID, int examOne, int examTwo, int finalExam, JFrame frame){
              try{
         Connection myConn = MySQLConnection.getConnection();
         PreparedStatement pst = myConn.prepareStatement("UPDATE enrolled SET sid =?, cid=?,"
             + "exam1 =?, exam2 =?, final =? WHERE sid =?");
         
        
         pst.setInt(1, userID);
         pst.setInt(2, courseID);
         pst.setInt(3, examOne);
         pst.setInt(4, examTwo);
         pst.setInt(5, finalExam); 
         pst.setInt(6, userID);
         pst.executeUpdate();
             
        return true;
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
        }
        return false;
     }
     
     public static boolean deleteStudent(int userID, int courseID, int examOne, int examTwo, int finalexam, JFrame frame){
         try {
             Connection myConn = MySQLConnection.getConnection();
             PreparedStatement pst = myConn.prepareStatement("DELETE FROM enrolled where "
                     + "sid =?");

             pst.setInt(1, userID);
             pst.executeUpdate();

             return true;

         } catch (Exception e) {
             JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
         }
         return false;
     }

    /* METHODS USED FOR COURSES */
     public static boolean addNewCourses(int courseID, String courseName, String meetsAt,int room, int facultyID, int limit, JFrame frame){
         try{
             Connection myConn = MySQLConnection.getConnection();
             PreparedStatement pst = myConn.prepareStatement("INSERT into courses (cid, cname, meets_at, room, FID, limitz) VALUES (?,?,?,?,?,?) ");
             
             pst.setInt(1, courseID);
             pst.setString(2, courseName);
             pst.setString(3, meetsAt);
             pst.setInt(4, room);
             pst.setInt(5, facultyID);
             pst.setInt(6, limit);
             
             pst.executeUpdate();
             
             return true;
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
         }
         return false;
     }

     public static boolean updateCourses(int courseID, String courseName, String meetsAt,int room, int facultyID, int limit, JFrame frame){
         try{
            Connection myConn = MySQLConnection.getConnection();
            PreparedStatement pst = myConn.prepareStatement("UPDATE courses SET cid =?, cname =?,"
                + "meets_at =?, room =?, FID =?, limitz =? WHERE cid =?");
         
        
            pst.setInt(1, courseID);
            pst.setString(2, courseName);
            pst.setString(3, meetsAt);
            pst.setInt(4, room);
            pst.setInt(5, facultyID); 
            pst.setInt(6, limit);
            pst.setInt(7, courseID);
            pst.executeUpdate();

        return true;
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
        }
        return false;
     }

     public static boolean deleteCourses(int courseID, String courseName, String meetsAt, int room, int facultyID, int limit, JFrame frame){
         try {
             Connection myConn = MySQLConnection.getConnection();
             PreparedStatement pst = myConn.prepareStatement("DELETE FROM courses where "
                     + "cid =?");

             pst.setInt(1, courseID);
             pst.executeUpdate();

             return true;

         } catch (Exception e) {
             JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
         }
         return false;
     }
     
     /* METHODS USED FOR FACULTY */
     public static boolean addNewFaculty(int facultyID, String facultyName, int department, JFrame frame){
         try {
             Connection myConn = MySQLConnection.getConnection();
             PreparedStatement pst = myConn.prepareStatement("INSERT into faculty (fid, fname, deptid) VALUES (?,?,?) ");

             pst.setInt(1, facultyID);
             pst.setString(2, facultyName);
             pst.setInt(3, department);

             pst.executeUpdate();

             return true;

         } catch (Exception e) {
             JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
         }
         return false;
     }

     public static boolean deleteFaculty(int facultyID, String facultyName, int department, JFrame frame){
         try {
             Connection myConn = MySQLConnection.getConnection();
             PreparedStatement pst = myConn.prepareStatement("DELETE FROM faculty where "
                     + "fid =?");

             pst.setInt(1, facultyID);
             pst.executeUpdate();

             return true;

         } catch (Exception e) {
             JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
         }
         return false;
     }
     
     public static boolean updateFaculty(int facultyID, String facultyName, int department, JFrame frame){
         try {
             Connection myConn = MySQLConnection.getConnection();
             PreparedStatement pst = myConn.prepareStatement("UPDATE faculty SET fid =?, fname =?,"
                     + "deptid =? WHERE fid =?");

             pst.setInt(1, facultyID);
             pst.setString(2, facultyName);
             pst.setInt(3, department);
             pst.setInt(4, facultyID);

             pst.executeUpdate();

             return true;

         } catch (Exception e) {
             JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
         }
         return false;
     }

     /* METHODS USED FOR Staff */
      public static boolean addNewStaff(int staffID, String staffName, int department, JFrame frame){
         try {
             Connection myConn = MySQLConnection.getConnection();
             PreparedStatement pst = myConn.prepareStatement("INSERT into staff (sid, sname, deptid) VALUES (?,?,?) ");

             pst.setInt(1, staffID);
             pst.setString(2, staffName);
             pst.setInt(3, department);

             pst.executeUpdate();

             return true;

         } catch (Exception e) {
             JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
         }
         return false;
     }

     public static boolean deleteStaff(int staffID, String staffName, int department, JFrame frame){
         try {
             Connection myConn = MySQLConnection.getConnection();
             PreparedStatement pst = myConn.prepareStatement("DELETE FROM staff where "
                     + "sid =?");

             pst.setInt(1, staffID);
             pst.executeUpdate();

             return true;

         } catch (Exception e) {
             JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
         }
         return false;
     }
     
     public static boolean updateStaff(int facultyID, String facultyName, int department, JFrame frame){
         try {
             int length = String.valueOf(department).length();
             if(length == 4){
             Connection myConn = MySQLConnection.getConnection();
             PreparedStatement pst = myConn.prepareStatement("UPDATE staff SET sid =?, sname =?,"
                     + "deptid =? WHERE sid =?");

             pst.setInt(1, facultyID);
             pst.setString(2, facultyName);
             pst.setInt(3, department);
             pst.setInt(4, facultyID);

             pst.executeUpdate();

             return true;
             }
             else{
                 JOptionPane.showMessageDialog(frame, "Please enter correct values for deptid");
             }
         } catch (Exception e) {
             JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
         }
         return false;
     }

    /* Student Methods */
     public static boolean addClass(int userid, int courseID, String courseName, String meets_at, int roomNum, JFrame frame){
          try{
             int exam1, exam2, finalz, records;
             exam1 = exam2 = finalz = records = 0;
             Connection myConn = MySQLConnection.getConnection();
             PreparedStatement pst2 = myConn.prepareStatement("SELECT COUNT(*) FROM enrolled WHERE enrolled.cid = ?");
             pst2.setInt(1, courseID);
             ResultSet rs = pst2.executeQuery();
             while (rs.next()){
                 records = rs.getInt(1);
             }
             
            if(records < 10){ 
             
             PreparedStatement pst = myConn.prepareStatement("INSERT into enrolled (sid, cid, exam1, exam2, final) VALUES (?,?,?,?,?) ");
             
             pst.setInt(1, userid);
             pst.setInt(2, courseID);
             pst.setInt(3, exam1);
             pst.setInt(4, exam2);
             pst.setInt(5, finalz);
            
             
             pst.executeUpdate();
             return true;
            }else{
                JOptionPane.showMessageDialog(frame, "Maxed Enrollement. Please select another class!");
            }
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
         }
         return false;
     }

     public static boolean dropClass(int userid, int courseID, int exam_one, int exam_two, int final_exam, JFrame frame){
         try {
             Connection myConn = MySQLConnection.getConnection();
             PreparedStatement pst = myConn.prepareStatement("DELETE FROM enrolled where "
                     + "sid =? AND cid =? AND exam1 =? AND exam2 = ? AND final =?");

                pst.setInt(1, userid);
                pst.setInt(2, courseID);
                pst.setInt(3, exam_one);
                pst.setInt(4, exam_two);
                pst.setInt(5, final_exam);
                
                
                pst.executeUpdate();

             return true;

         } catch (Exception e) {
             JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
         }
         return false;
     }
}