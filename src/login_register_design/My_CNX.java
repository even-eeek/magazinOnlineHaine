/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_register_design;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class My_CNX {
    
    public static My_CNX My_CNXInstance = null;
    
    public static int count = 0;

    public My_CNX() {
    }
    
    public My_CNX(String dbname) {
        this.dbname = dbname;
    }
    
    public static My_CNX getInstance(String dbname){
        if (My_CNXInstance == null){ //if there is no instance available... create new one
            My_CNXInstance = new My_CNX(dbname);
        } 

        return My_CNXInstance;
    }
    
    public static My_CNX getInstance(){
        if (My_CNXInstance == null){ //if there is no instance available... create new one
            My_CNXInstance = new My_CNX();
        } 

        return My_CNXInstance;
    }
    
    private static String servername = "localhost";
    private static String username = "root";
    private static String dbname = "sportmagazine_db";
    private static Integer portnumber = 3306;
    private static String password = "";

    public static String getDbname() {
        return dbname;
    }

    public static void setDbname(String dbname) {
        My_CNX.dbname = dbname;
    }
    
    public static Connection getConnection() {
        Connection cnx = null;
        
        MysqlDataSource datasource = setMySqlDataSource();
        
        try {
            cnx = datasource.getConnection();
            
        } catch (SQLException ex) {
            Logger.getLogger("GET CONNECTION -> " + My_CNX.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cnx;
    }
    
    
//    public static void checkDB1(String DBName) {
//        try {
//            ResultSet resultSet = getConnection().getMetaData().getCatalogs();
//
//            //iterate each catalog in the ResultSet
//            while (resultSet.next()) {
//              // Get the database name, which is at position 1
//              String databaseName = resultSet.getString(1);
//            }
//            resultSet.close();
//        }
//    }
//    public static boolean check(String DBName) {
//        String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema'[sportmagazine_db]' AND table_name = '[sportmagazine_db]'";
//        ResultSet rs = stmt.executeQuery(query);                  
//        rs.next();
//        boolean exists = rs.getInt("COUNT(*)") > 0;
//        // Close connection, statement, and result set.
//        return exists;   
//    }
    public static boolean checkDBExist(String DBName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "show databases like 'sportmagazine_db'";
        
        setDbname("");
        
        try {
            System.out.println("Creating database...");
            
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
              // Get the database name, which is at position 1
              String databaseName = rs.getString(1);
              if (databaseName.equals(DBName)) {
                  return true;
              }
            }
            
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1007) {
                // Database already exists error
                JOptionPane.showMessageDialog(null, "Error: Cannot retrieve data bases!");
            } else {
                // Some other problems, e.g. Server down, no permission, etc
                ex.printStackTrace();
            }
        } catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        
        return false;
    }
    
    public static void CreateDB(String DBName) {
        
        if (checkDBExist(DBName)) {
            JOptionPane.showMessageDialog(null, "Error: DATA BASE already EXISTS!");
            return;
        }
        Statement stmt = null;
        PreparedStatement ps = null;
        
        String sql = "CREATE DATABASE sportmagazine_db";
//        String sql = "CREATE DATABASE ?";
        
        setDbname("");
        
        try {
            System.out.println("Creating database...");

            stmt = getConnection().createStatement();
            
//            stmt = getConnection().prepareStatement(sql);
            
//            ps = My_CNX.getConnection().prepareStatement(sql);
//            ps.setString(1, DBName);

            stmt.executeUpdate(sql);
            
//            if(stmt.executeUpdate(sql) != 0){
//                JOptionPane.showMessageDialog(null, "SUCCCES. CREATED DATA BASE ");
//            }else{
//                JOptionPane.showMessageDialog(null, "Error: Creating DATA BASE");
//            }
            
//            ps.executeUpdate(sql);

            System.out.println("Database created successfully...");
        
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1007) {
                // Database already exists error
                JOptionPane.showMessageDialog(null, "Error: DATA BASE already EXISTS!");
            } else {
                // Some other problems, e.g. Server down, no permission, etc
                ex.printStackTrace();
            }
            Logger.getLogger("GET CONNECTION -> " + My_CNX.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        
    }
    
    public static boolean tableExist(String tableName) throws SQLException {
        boolean tExists = false;
        try (ResultSet rs = getConnection().getMetaData().getTables(null, null, tableName, null)) {
            while (rs.next()) { 
                String tName = rs.getString("TABLE_NAME");
                if (tName != null && tName.equals(tableName)) {
                    tExists = true;
                    break;
                }
            }
        }
        return tExists;
    }
    
    public static void INSERT_TABLE(String DBName, String sql, String newTableName) throws SQLException {

        if (tableExist(newTableName)) {
            JOptionPane.showMessageDialog(null, "Error: TABLE " + newTableName + " already EXISTS!");
            return;
        }
        
        Statement stmt = null;
        PreparedStatement ps = null;
        
        setDbname(DBName);
        
        try {
            stmt = getConnection().createStatement();

            stmt.executeUpdate(sql);

            System.out.println("Created table in given database...");

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1007) {
                // Database already exists error
//                System.out.println(ex.getMessage());
            } else {
                // Some other problems, e.g. Server down, no permission, etc
//                ex.printStackTrace();
            }
            Logger.getLogger("GET CONNECTION -> " + My_CNX.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            //Handle errors for Class.forName
//            e.printStackTrace();
         }
    }
    
    public static MysqlDataSource setMySqlDataSource() {
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setServerName(servername);
        datasource.setUser(username);
        datasource.setPassword(password);
        datasource.setDatabaseName(dbname);
        datasource.setPortNumber(portnumber);
        
        return datasource;
    }
}
