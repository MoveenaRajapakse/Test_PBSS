package com.connection;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class DBConnection {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static Connection connection = null;
    static final String CONNURL = "jdbc:mysql://localhost:3306/test";
    static final String USERNAME = "root";
    static final String PASSWORD = "root";

    public static void dbConnect() throws SQLException, ClassNotFoundException{
        try{
            Class.forName(JDBC_DRIVER);
        }
        catch(ClassNotFoundException e){
            System.out.println("MySql JDBC Driver Not Found !");
            e.printStackTrace();
            throw e;
        }
        System.out.println("JBDC Driver has been registered!");

        try{
            connection = (Connection) DriverManager.getConnection(CONNURL,USERNAME,PASSWORD);
            System.out.println(connection);
        }
        catch(SQLException e){
            System.out.println("Connection Faild " + e);
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException{
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        catch(Exception e){
            throw e;
        }
    }

    //-------------- For insert - delete - update ----------
    public static void dbExecuteQuery(String sqlStmt) throws SQLException, ClassNotFoundException{
        Statement stmt  = null;
        try{
            dbConnect();
            connection.createStatement();
            stmt = (Statement) connection.createStatement();
            stmt.executeUpdate(sqlStmt);
        }
        catch(SQLException e){
            System.out.println("Problem occured at dbExecuteQuery operation " + e);
            throw e;
        }
        finally{
            if(stmt !=null ){
                stmt.close();
            }
            dbDisconnect();
        }
    }
    //--------------For retriving records----------------
    public static ResultSet dbExecute(String sqlQuery) throws ClassNotFoundException, SQLException{
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;

        try{
            dbConnect();
            stmt =  (Statement) connection.createStatement();
            rs =stmt.executeQuery(sqlQuery);
            crs = new CachedRowSetImpl();
            crs.populate(rs);
        }
        catch(SQLException e){
            System.out.println("Error occured in dbExecute operation" + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }


    /*public static CallableStatement prepareCall(String query) throws ClassNotFoundException, SQLException{
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;
        //CachedRowSet crs = null;

        try{
            dbConnect();
            stmt =  (Statement) connection.createStatement();
            rs =stmt.executeQuery(query);
            crs = new CachedRowSetImpl();
            crs.populate(rs);
        }
        catch(SQLException e){
            System.out.println("Error occured in dbExecute operation" + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            }
            dbDisconnect();
        }
        return (CallableStatement) crs;
    }*/
}
