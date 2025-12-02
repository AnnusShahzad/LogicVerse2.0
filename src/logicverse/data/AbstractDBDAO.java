/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Abstract base class for database data access objects (DAO).
 * Implements the IDAO interface and provides database connection management
 * for all concrete DAO implementations.
 * Uses SQLite database and maintains a singleton connection.
 * @author zohaib
 */
public abstract class AbstractDBDAO implements IDAO {
    /**
     * Database connection instance.
     * Singleton connection shared across all DAO operations.
     */
    private static Connection conn;
    
    /**
     * Gets or creates a database connection.
     * Implements lazy initialization and connection reuse pattern.
     * Creates a new connection if none exists or if the existing connection is closed.
     * @return Connection object to the SQLite database
     * @throws SQLException if database connection fails
     */
    protected static Connection getConnection()
            throws SQLException
    {           
        
        if(conn == null || conn.isClosed()){
            conn = DriverManager.getConnection("jdbc:sqlite:db/LogicVerseDB.db"); 
        }
        return conn;
            
    }
}
