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
public class ComponentDBDAO extends AbstractDBDAO {

    @Override
    public boolean save(Hashtable<String, String> data) {
        int count = 0;
        try {
            Connection conn = getConnection();
            PreparedStatement compStmt = updateStatement(conn, data);
            if(data.containsKey("clicked"))
                updateClickStatement(conn, data).executeUpdate();
            count = compStmt.executeUpdate();
            if (count == 0) {
                compStmt = insertStatement(conn, data);
                count = compStmt.executeUpdate();
                if(data.containsKey("clicked"))
                    insertClickedStatement(conn, data).executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        return count > 0 ;
    }

    @Override
    public boolean delete(Hashtable<String, String> params) {
        int count = 0;
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = deleteStatement(conn, params.get("Cid"));
            count = stmt.executeUpdate();
            deleteCLickedStatement(conn, params.get("Cid")).executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        return count > 0 ;
    }

    @Override
    public Hashtable<String, String> load(Hashtable<String, String> params) {
        Hashtable<String, String> data = new Hashtable<String, String>();
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = selectSingleStatement(conn, params.get("Cid"));
            ResultSet rs = stmt.executeQuery();

            data.put("Name", rs.getString("Name"));
            data.put("internalName", rs.getString("internalName"));
            data.put("PosX", String.valueOf(rs.getString("PosX")));
            data.put("PosY", String.valueOf(rs.getString("PosY")));

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return data;
    }

    @Override
    public ArrayList<Hashtable<String, String>> loadAll(Hashtable<String, String> params) {
        ArrayList<Hashtable<String, String>> data = new ArrayList<>();

        String query = "select c.Name,c.internalName,c.PosX,c.PosY,c.Cid,cc.clicked from Component c Left JOIN ClickableComponent cc on cc.Cid = c.Cid where CIRid = ?;";
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);        
            stmt.setString(1,params.get("CIRid"));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Hashtable<String, String> o = new Hashtable<>();
                o.put("Cid", rs.getString("Cid"));
                o.put("Name", rs.getString("Name"));
                o.put("internalName", rs.getString("internalName"));
                o.put("PosX", String.valueOf(rs.getString("PosX")));
                o.put("PosY", String.valueOf(rs.getString("PosY")));
                if(rs.getString("clicked") != null)
                    o.put("clicked", rs.getString("clicked"));
                data.add(o);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return data;
    }

    public ArrayList<String> loadAllAssociatedComponents(String Cid){
        ArrayList<String> data = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String query = "select assiciatedCId from AssociatedComponents where parentCId = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, Cid);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                data.add(rs.getString("assiciatedCId"));
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return data;
    }
    
    public boolean saveAssociations(String Cid,ArrayList<String> associatedIds){
        String query = "INSERT INTO AssociatedComponents (parentCId, assiciatedCId) VALUES (?, ?);";
        try {
            
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            for(String Aid : associatedIds ){
                stmt.setString(1, Cid);
                stmt.setString(2, Aid);
                try{
                    stmt.executeUpdate();
                }
                catch(SQLException ex){
                    // if not Integrity Constraint Violation
                    if(!ex.getSQLState().equals("23000"))
                        throw ex; 
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        return true;
    }
    
        private PreparedStatement updateStatement(Connection conn, Hashtable<String, String> data) throws SQLException {
        String query = "update Component set Name = ?,internalName = ?,PosX = ?,PosY = ? where Cid = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, data.get("Name"));
        stmt.setString(2, data.get("internalName"));
        stmt.setString(3, data.get("PosX"));
        stmt.setString(4, data.get("PosY"));
        stmt.setString(5, data.get("Cid"));
        return stmt;
    }
        
        
    private PreparedStatement updateClickStatement(Connection conn, Hashtable<String, String> data) throws SQLException {
        String query = "update ClickableComponent set clicked = ? where Cid = ?;";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, data.get("clicked"));
        stmt.setString(2, data.get("Cid"));
        return stmt;
    }

    private PreparedStatement insertStatement(Connection conn, Hashtable<String, String> data) throws SQLException {
        String query = "insert into Component(CIRid, Cid,Name,internalName,PosX,PosY) values(?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setString(1, data.get("CIRid"));
        stmt.setString(2, data.get("Cid"));
        stmt.setString(3, data.get("Name"));
        stmt.setString(4, data.get("internalName"));
        stmt.setString(5, data.get("PosX"));
        stmt.setString(6, data.get("PosY"));

        return stmt;
    }
    private PreparedStatement insertClickedStatement(Connection conn, Hashtable<String, String> data) throws SQLException {
        String query = "insert into ClickableComponent (Cid,clicked) values(?,?)";
        
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, data.get("Cid"));
        stmt.setString(2, data.get("clicked"));

        return stmt;
    }

    private PreparedStatement selectSingleStatement(Connection conn, String cid) throws SQLException {
        String query = "select c.Name,c.internalName,c.PosX,c.PosY,c.Cid,cc.clicked from Component c Left JOIN ClickableComponent cc on cc.Cid = c.Cid where c.Cid = ?;";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, cid);
        return stmt;
    }

    private PreparedStatement deleteStatement(Connection conn, String Cid) throws SQLException {
        String query = "delete from Component where Cid = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, Cid);
        return stmt;
    }
    private PreparedStatement deleteCLickedStatement(Connection conn, String Cid) throws SQLException {
        String query = "delete from ClickableComponent where Cid = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, Cid);
        return stmt;
    }
   
}
