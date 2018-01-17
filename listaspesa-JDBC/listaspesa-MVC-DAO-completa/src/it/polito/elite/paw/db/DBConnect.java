/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polito.elite.paw.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Restituisce la connessione JDBC al database
 * @author Fulvio
 */
public class DBConnect {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

        String dburl = "jdbc:mysql://localhost:3306/listaspesa?user=root&password=";

        Connection conn = null ;
        
        try {
            conn = DriverManager.getConnection(dburl);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn ;
    }
}
