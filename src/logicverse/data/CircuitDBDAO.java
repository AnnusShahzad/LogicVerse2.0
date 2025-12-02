/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicverse.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Logger;

/**
 *
 * @author zohaib
 */
public class CircuitDBDAO extends AbstractDBDAO{
    private static final Logger logger = Logger.getLogger(CircuitDBDAO.class.getName());
     @Override
    public boolean save(Hashtable<String,String> data){
        int count = 0;
        try{
            Connection conn = getConnection();            
            PreparedStatement stmt = updateStatement(conn,data);
            count = stmt.executeUpdate();
            if (count == 0){
                stmt = insertStatement(conn,data);
                count = stmt.executeUpdate();            
            }                        
        } catch(SQLException ex){
            logger.severe("SQLException: " + ex.getMessage());
            logger.severe("SQLState: " + ex.getSQLState());
            logger.severe("VendorError: " + ex.getErrorCode());
            return false;
        }
        return count > 0 ? true : false;
    }
    
    @Override
    public boolean delete(Hashtable<String,String> params){
        int count = 0;
        try{
            Connection conn = getConnection();
            PreparedStatement stmt = deleteStatement(conn,params.get("CIRid"));
            count = stmt.executeUpdate();            
        } catch(SQLException ex){
            logger.severe("SQLException: " + ex.getMessage());
            logger.severe("SQLState: " + ex.getSQLState());
            logger.severe("VendorError: " + ex.getErrorCode());
            return false;
        }
        return count > 0 ? true : false;
    }
    
    @Override
    public Hashtable<String,String> load(Hashtable<String,String> params){
        Hashtable<String,String> data = new Hashtable<String,String>();
        try{            
            Connection conn = getConnection();
            PreparedStatement stmt = selectSingleStatement(conn,params.get("CIRid"));
            ResultSet rs = stmt.executeQuery();
                    
            while (rs.next()){                
                data.put("CIRid", rs.getString("CIRid"));
                data.put("Name", rs.getString("Name"));              
            }        
        } catch(SQLException ex){
            logger.severe("SQLException: " + ex.getMessage());
            logger.severe("SQLState: " + ex.getSQLState());
            logger.severe("VendorError: " + ex.getErrorCode());
        }
        return data;
    }
    
    @Override
    public ArrayList<Hashtable<String,String>> loadAll(Hashtable<String, String> params){
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();
        
        String query = "select CIRid,Name from Circuit";
        try {
          Connection conn = getConnection();
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          while (rs.next()) {
            Hashtable<String,String> o = new Hashtable<>();
            o.put("CIRid",rs.getString("CIRid"));
            o.put("Name",rs.getString("Name"));
            
            data.add(o);
          }
        } catch (SQLException ex) {
            logger.severe("SQLException: " + ex.getMessage()); 
            logger.severe("SQLState: " + ex.getSQLState());
            logger.severe("VendorError: " + ex.getErrorCode());
        }
        
        return data;
    }
    
    private PreparedStatement updateStatement(Connection conn,Hashtable<String,String> data) throws SQLException{
        String query = "update Circuit set Name = ? where CIRid = ?";
        PreparedStatement stmt = conn.prepareStatement(query);        
        stmt.setString(1,data.get("Name"));
        stmt.setString(2,data.get("CIRid"));
        return stmt;
    }
    private PreparedStatement insertStatement(Connection conn,Hashtable<String,String> data) throws SQLException{
        String query = "insert into Circuit(CIRid,Name) values(?,?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        
        stmt.setString(1,data.get("CIRid"));
        stmt.setString(2,data.get("Name"));
        
        return stmt;
    }
    
    private PreparedStatement selectSingleStatement(Connection conn,String id) throws SQLException{
        String query = "select CIRid,Name from Circuit where CIRid = ?";
        PreparedStatement stmt = conn.prepareStatement(query);        
        stmt.setString(1,id);
        return stmt;
    }
    
    private PreparedStatement deleteStatement(Connection conn,String id) throws SQLException{
        String query = "delete from Circuit where CIRid = ?";
        PreparedStatement stmt = conn.prepareStatement(query);        
        stmt.setString(1,id);
        return stmt;
    }
    
}
