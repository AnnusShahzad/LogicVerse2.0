/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author zohaib
 */
public abstract class AbstractDBDAO implements IDAO {
    private static Connection conn;
    protected static Connection getConnection()
            throws SQLException
    {           
        
        if(conn == null){
            conn = DriverManager.getConnection("jdbc:sqlite:db/LogicVerseDB.db"); 
        }
        return conn;
            
    }
}
