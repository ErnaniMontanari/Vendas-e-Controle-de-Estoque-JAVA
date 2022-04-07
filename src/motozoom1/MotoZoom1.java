/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motozoom1;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import motozoom1.swing.mainFrame;
import java.sql.*;
import motozoom1.dados.Usuario;
import motozoom1.swing.LoginScreem;

/**
 *
 * @author ErNaNiSS
 */
public class MotoZoom1 {

    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public static Usuario usuario = null;
    public static JFrame mainframe = null;
    public static JFrame loginframe = null;
    
    
    /**
     * @param args the command line arguments
     */
    static GraphicsDevice device = GraphicsEnvironment
        .getLocalGraphicsEnvironment().getScreenDevices()[0];
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        
        JFrame loginScreem = new LoginScreem();
        loginScreem.setVisible(true);
        

        // TODO code application logic here
        //JFrame a = new mainFrame();
        //a.setVisible(true);
        //device.setFullScreenWindow(a);d
    }
    
}
