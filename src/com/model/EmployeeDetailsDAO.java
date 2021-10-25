package com.model;

import com.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import javax.sql.rowset.CachedRowSet;
import com.sun.rowset.CachedRowSetImpl;

//import static com.connection.DBConnection.prepareCall;

public class EmployeeDetailsDAO {

    //==================================================Insert=========================================================================
    public static void insertEmployeeDetails(String fname,String lname,String dept, String email, Integer cont,double salary) throws ClassNotFoundException, SQLException{

        String sql = "INSERT INTO employee_details (FirstName, LastName, Department, Email, ContactNo, Salary) VALUES ('"+fname+"','"+lname+"','"+dept+"','"+email+"','"+cont+"','"+salary+"')";

        try {
            DBConnection.dbExecuteQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Error in inserting");
            ex.printStackTrace();
            throw ex;
        }
    }


    //------------------------------------------------Display records-----------------------------------------------
    public static ObservableList<EmployeeDetails>getAllRecords() throws ClassNotFoundException,SQLException{

        String sql = "SELECT * FROM employee_details";

        try{
            ResultSet rsSet = DBConnection.dbExecute(sql);
            ObservableList<EmployeeDetails> EmployeeList = getEmployeeObjects(rsSet);
            return EmployeeList;
        }
        catch (SQLException e){
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<EmployeeDetails> getEmployeeObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException {

        try{
            ObservableList<EmployeeDetails> employeeList = FXCollections.observableArrayList();

            while(rsSet.next())
            {
                EmployeeDetails emp = new EmployeeDetails();
                emp.setEmployeeId(rsSet.getInt("idEmployee_details"));
                emp.setFirstName(rsSet.getString("FirstName"));
                emp.setLastNameName(rsSet.getString("LastName"));
                emp.setDepartment(rsSet.getString("Department"));
                emp.setEmailAddress(rsSet.getString("Email"));
                emp.setContactNo(rsSet.getInt("ContactNo"));
                emp.setSalary(rsSet.getDouble("Salary"));

                employeeList.add(emp);
            }
            return employeeList;
        }
        catch (SQLException e)
        {
            System.out.println("Error occurred while fetching data from db");
            e.printStackTrace();
            throw e;
        }
    }

    //----------------------------------------------------------Search----------------------------------------------------
    public static ObservableList<EmployeeDetails> search(String department) throws ClassNotFoundException ,SQLException {

        String sql = "SELECT * FROM employee_details WHERE Department like'%"+department +"%'";

        try{
            ResultSet rsSet = DBConnection.dbExecute(sql);
            ObservableList<EmployeeDetails> list = getEmployeeObjects(rsSet);
            return list;
        }
        catch (SQLException e){
            System.out.println("Error occurred while fetching data from db");
            e.printStackTrace();
            throw e;
        }
    }

    //-------------------------------------------------------Report---------------------------------------------------------
    public static ObservableList<EmployeeDetails> reportGenerate (String department) throws ClassNotFoundException ,SQLException {

        try {
            final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            final String CONNURL = "jdbc:mysql://localhost:3306/test";
            final String USERNAME = "root";
            final String PASSWORD = "root";

            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(CONNURL, USERNAME, PASSWORD);
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            String query = "{call GetDetails_Filter_Department(?)}";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, department);
            rs = pstmt.executeQuery();

            ObservableList<EmployeeDetails> list = getEmployeeObjects(rs);
            return list;
        }
        catch (SQLException e){
            System.out.println("Error occurred while fetching data from db");
            e.printStackTrace();
            throw e;
        }


    }


}
