/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.capone.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Restituisce la connessione JDBC al database
 * @author Fulvio
 */
public class DBConnect {
	
	private DBConnect(){}

	/*
	 * VERSIONE NUOVA CON IL CONNECTION POOL
	 */
	public static Connection getConnection() {
		
		Context initContext;
		try{
			//JNDI query to locate the DataSource object
			initContext = new InitialContext();
			System.out.println("Prendo la connessione dal Connection pool di Tomcat...");
			Context envContext  = (Context)initContext.lookup("java:/comp/env") ; // JNDI standard naming root
			DataSource ds = (DataSource)envContext.lookup("jdbc/spesa");
			// Ask DataSource for a connection 
			Connection conn;
			try {	
				conn = ds.getConnection();
				return conn;
			}
	        catch(SQLException ex)
	        {
	        	Logger.getLogger(DBConnect.class.getName(), null).log(Level.SEVERE, null, ex);
	            throw new RuntimeException("cannot open Connection", ex);
	        }
	       
		} 
		catch(NamingException ex)
		{
			Logger.getLogger(DBConnect.class.getName(), null).log(Level.SEVERE, null, ex);
			throw new RuntimeException("cannot find DataSource reference", ex);
		}
		  catch(Exception ex3)
        {
            System.out.println("Eccezione generica: " +ex3.getMessage());
            throw new RuntimeException("Generic Exception", ex3);
        }
		
	}
	
	
	
	//OLD VERSION
	
    /*public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

        //String dburl = "jdbc:mysql://localhost:3306/spesa?user=root&password=";

        Connection conn = null ;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/listaspesa", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn ;
    }*/
}
