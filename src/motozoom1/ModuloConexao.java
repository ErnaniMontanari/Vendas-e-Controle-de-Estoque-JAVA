/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motozoom1;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ErNaNi
 */
public class ModuloConexao {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/motozoom";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection()
    {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("ERRO no getConnection() -> " + e);
        }
        
        return null;
    }
    
    public static void closeConnection(Connection con)
    {
        if(con != null)
        {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("ERRO no closeConnection() -> " + ex);
            }
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement psmt)
    {
        if(con != null)
        {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("ERRO no closeConnection() -> " + ex);
            }
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement psmt, ResultSet rs)
    {
        if(con != null)
        {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("ERRO no closeConnection() -> " + ex);
            }
        }
    }
    
}
