/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.database.project;

import javax.swing.JFrame;
/**
 *
 * @author SIU856414916
 */
public class Logout {
    public static void logOut(JFrame context, LoginScreen loginScreen){
        LoginSession.isLoggedIn = false;
        context.setVisible(false);
        loginScreen.setVisible(true);
    }
}
