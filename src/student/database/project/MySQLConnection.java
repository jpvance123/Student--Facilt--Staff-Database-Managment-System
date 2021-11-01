/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.database.project;

import java.sql.*;

/**
 *
 * @author SIU856414916
 */
public class MySQLConnection {
    public static Connection getConnection() throws Exception {
        String DB_URL = "jdbc:mysql://localhost:3306/first_iteration";
        String USERNAME = "root";
        String PASSWORD = "qwertypoiu";
        
        Connection myConn = null;
        myConn = (Connection)DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        
        return myConn;
    }
}
