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

/**
 *
 * @author zohaib
 */
public class ConnectionDBDAO extends AbstractDBDAO {
    
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
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        return count > 0 ? true : false;
    }
    
    @Override
    public boolean delete(Hashtable<String,String> params){
        int count = 0;
        try{
            Connection conn = getConnection();
            PreparedStatement stmt = deleteStatement(conn,params.get("ConnectionId"));
            count = stmt.executeUpdate();            
        } catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        return count > 0 ? true : false;
    }
    
    @Override
    public Hashtable<String,String> load(Hashtable<String,String> params){
        Hashtable<String,String> data = new Hashtable<String,String>();
        try{            
            Connection conn = getConnection();
            PreparedStatement stmt = selectSingleStatement(conn,params.get("ConnectionId"));
            ResultSet rs = stmt.executeQuery();
                    
            if (rs.next()){                
                data.put("ConnectionId", rs.getString("ConnectionId"));
                data.put("Cid", rs.getString("Cid"));
                data.put("inputPointId", rs.getString("inputPointId"));
                data.put("outputPointId", rs.getString("outputPointId"));
            }        
        } catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return data;
    }
    
    @Override
    public ArrayList<Hashtable<String,String>> loadAll(Hashtable<String, String> params){
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();
        
        String query = "select ConnectionId, Cid, inputPointId, outputPointId from Connection";
        try {
          Connection conn = getConnection();
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          while (rs.next()) {
            Hashtable<String,String> o = new Hashtable<>();
            o.put("ConnectionId", rs.getString("ConnectionId"));
            o.put("Cid", rs.getString("Cid"));
            o.put("inputPointId", rs.getString("inputPointId"));
            o.put("outputPointId", rs.getString("outputPointId"));
            data.add(o);
          }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage()); 
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return data;
    }
    
    private PreparedStatement updateStatement(Connection conn,Hashtable<String,String> data) throws SQLException{
        String query = "update Connection set Cid = ?, inputPointId = ?, outputPointId = ? where ConnectionId = ?";
        PreparedStatement stmt = conn.prepareStatement(query);        
        stmt.setString(1, data.get("Cid"));
        stmt.setString(2, data.get("inputPointId"));
        stmt.setString(3, data.get("outputPointId"));
        stmt.setString(4, data.get("ConnectionId"));
        return stmt;
    }
    private PreparedStatement insertStatement(Connection conn,Hashtable<String,String> data) throws SQLException{
        String query = "insert into Connection(ConnectionId, Cid, inputPointId, outputPointId) values(?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        
        stmt.setString(1, data.get("ConnectionId"));
        stmt.setString(2, data.get("Cid"));
        stmt.setString(3, data.get("inputPointId"));
        stmt.setString(4, data.get("outputPointId"));
        
        return stmt;
    }
    
    private PreparedStatement selectSingleStatement(Connection conn,String connectionId) throws SQLException{
        String query = "select ConnectionId, Cid, inputPointId, outputPointId from Connection where ConnectionId = ?";
        PreparedStatement stmt = conn.prepareStatement(query);        
        stmt.setString(1, connectionId);
        return stmt;
    }
    
    private PreparedStatement deleteStatement(Connection conn,String connectionId) throws SQLException{
        String query = "delete from Connection where ConnectionId = ?";
        PreparedStatement stmt = conn.prepareStatement(query);        
        stmt.setString(1, connectionId);
        return stmt;
    }
}
