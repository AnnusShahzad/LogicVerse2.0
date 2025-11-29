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
public class PointDBDAO extends AbstractDBDAO{
    
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
            PreparedStatement stmt = deleteStatement(conn,params.get("Pid"));
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
            PreparedStatement stmt = selectSingleStatement(conn,params.get("Pid"));
            ResultSet rs = stmt.executeQuery();
                    
            while (rs.next()){
                data.put("Cid",rs.getString("Cid"));
                data.put("Name",rs.getString("Name"));
                data.put("bit",rs.getString("bit"));
                data.put("degree",rs.getString("degree"));
                data.put("type",rs.getString("type"));
                                
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
        
        String query = "select * from Point where Cid = ? and type = ?;";
        try {
          Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);        
            stmt.setString(1,params.get("Cid"));
            stmt.setString(2, params.get("type"));
            ResultSet rs = stmt.executeQuery();
            
          while (rs.next()) {
            Hashtable<String,String> o = new Hashtable<>();
            o.put("Pid",rs.getString("Pid"));
            o.put("Cid",rs.getString("Cid"));
            o.put("Name",rs.getString("Name"));
            o.put("bit",rs.getString("bit"));
            o.put("degree",rs.getString("degree"));
            o.put("type",rs.getString("type"));
            
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
        String query = "update Point set Cid = ?,Name = ?,bit = ?,degree = ?,type = ? where Pid = ?";
        PreparedStatement stmt = conn.prepareStatement(query);        
        stmt.setString(1,data.get("Cid"));
        stmt.setString(2,data.get("Name"));
        stmt.setString(3,data.get("bit"));
        stmt.setString(4,data.get("degree"));
        stmt.setString(5,data.get("type"));
        stmt.setString(6,data.get("Pid"));
        return stmt;
    }
    private PreparedStatement insertStatement(Connection conn,Hashtable<String,String> data) throws SQLException{
        String query = "insert into Point(Pid,Cid,Name,bit,degree,type) values(?,?,?,?,?,?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        
        stmt.setString(1,data.get("Pid"));
        stmt.setString(2,data.get("Cid"));
        stmt.setString(3,data.get("Name"));
        stmt.setString(4,data.get("bit"));
        stmt.setString(5,data.get("degree"));
        stmt.setString(6,data.get("type"));
        
        return stmt;
    }
    
    private PreparedStatement selectSingleStatement(Connection conn,String id) throws SQLException{
        String query = "select Cid,Name,bit,degree,type from Point where Pid = ?";
        PreparedStatement stmt = conn.prepareStatement(query);        
        stmt.setString(1,id);
        return stmt;
    }
    
    private PreparedStatement deleteStatement(Connection conn,String id) throws SQLException{
        String query = "delete from Point where Pid = ?";
        PreparedStatement stmt = conn.prepareStatement(query);        
        stmt.setString(1,id);
        return stmt;
    } 
}
