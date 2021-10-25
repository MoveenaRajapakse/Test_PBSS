package com.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.connection.DBConnection.*;
import com.model.EmployeeDetails;
import com.model.EmployeeDetailsDAO;
import com.connection.DBConnection;
import com.Dialog.AlertDialog;

public class EmployeeDetailsController implements Initializable{

    private ObservableList<String> departmentList = FXCollections.observableArrayList("Software Development", "Payroll", "ERP", "SME", "HR");

    @FXML
    private AnchorPane pane;

    @FXML
    private Label title;

    @FXML
    private Label lblfName;

    @FXML
    private TextField txtfName;

    @FXML
    private TextField txtlName;

    @FXML
    private ComboBox<String> cmbDepertment;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtSalary;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private TableView table;

    @FXML
    private TableColumn<EmployeeDetails, String> clmFname;

    @FXML
    private TableColumn<EmployeeDetails, String> clmLname;

    @FXML
    private TableColumn<EmployeeDetails, String> clmDepartment;

    @FXML
    private TableColumn<EmployeeDetails, String> clmSalary;

    @FXML
    private ComboBox<String> cmbRepDept;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView table2;

    @FXML
    private TableColumn<EmployeeDetails, String> tblempId;

    @FXML
    private TableColumn<EmployeeDetails, String> tblFname;

    @FXML
    private TableColumn<EmployeeDetails, String> tblLname;

    @FXML
    private TableColumn<EmployeeDetails, String> tblDept;

    @FXML
    private TableColumn<EmployeeDetails, String> tblEmail;

    @FXML
    private TableColumn<EmployeeDetails, String> tblCont;

    @FXML
    private TableColumn<EmployeeDetails, String> tblSalary;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cmbDepertment.getItems().addAll(departmentList);
        cmbRepDept.getItems().addAll(departmentList);

        try{
            clmFname.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
            clmLname.setCellValueFactory(cellData -> cellData.getValue().getLastNameNameProperty());
            clmDepartment.setCellValueFactory(cellData -> cellData.getValue().getDepartmentProperty());
            clmSalary.setCellValueFactory(cellData -> cellData.getValue().getSalaryProperty().asString());

            ObservableList<EmployeeDetails> employeeList1 = EmployeeDetailsDAO.getAllRecords();
            populateTable(employeeList1);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


    //------------------------------------------------Insert Employee Details---------------------------------------------------------
    public void Insert(ActionEvent event) throws ClassNotFoundException, SQLException {

        try{
            int contactNo = Integer.parseInt(txtContact.getText());
            double salary = Double.parseDouble(txtSalary.getText());

            EmployeeDetailsDAO.insertEmployeeDetails(txtfName.getText(),txtlName.getText(),cmbDepertment.getValue(),txtEmail.getText(),contactNo,salary);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        AlertDialog.display("Alert Message","Successfully Inserted");
        clear();
    }

    //----------------------------------------------------------Clear-----------------------------------------------------------------
    public void clear() {

        txtfName.setText("");
        txtlName.setText("");
        cmbDepertment.setValue("");
        txtEmail.setText("");
        txtContact.setText("");
        txtSalary.setText("");
    }

    private void clear(ActionEvent event) {
        clear();
    }



    //-------------------------------------------------Display Table-----------------------------------------------------------------
    private void populateTable(ObservableList<EmployeeDetails> EmployeeList) {

        table.setItems(EmployeeList);
    }

    private void displayAll(ActionEvent event) throws ClassNotFoundException, SQLException {

        ObservableList<EmployeeDetails> employeeList = EmployeeDetailsDAO.getAllRecords();
        populateTable(employeeList);
    }

    //----------------------------------------------Report Details by Department-------------------------------------------------------
    public void generateReport() throws ClassNotFoundException, SQLException{

        String department;
        department = cmbRepDept.getValue();
        System.out.println(department);

        try{
            tblempId.setCellValueFactory(cellData -> cellData.getValue().getEmployeeIdProperty().asString());
            tblFname.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
            tblLname.setCellValueFactory(cellData -> cellData.getValue().getLastNameNameProperty());
            tblDept.setCellValueFactory(cellData -> cellData.getValue().getDepartmentProperty());
            tblEmail.setCellValueFactory(cellData -> cellData.getValue().getEmailAddressProperty());
            tblCont.setCellValueFactory(cellData -> cellData.getValue().getContactNoProperty().asString());
            tblSalary.setCellValueFactory(cellData -> cellData.getValue().getSalaryProperty().asString());

            ObservableList<EmployeeDetails> list = EmployeeDetailsDAO.reportGenerate(department);
            populateTableforReports(list);
        }
        catch (SQLException e )
        {
            e.printStackTrace();
            Logger.getLogger(EmployeeDetailsController.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            Logger.getLogger(EmployeeDetailsController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void populateTableforReports(ObservableList<EmployeeDetails> EmployeeList) {

        table2.setItems(EmployeeList);
    }

    private void displayAllRep(ActionEvent event) throws ClassNotFoundException, SQLException {

        ObservableList<EmployeeDetails> employeeList = EmployeeDetailsDAO.getAllRecords();
        populateTableforReports(employeeList);
    }



    //---------------------------------------------------------Search--------------------------------------------------------------------
    public void search() {

        txtSearch.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                try{
                    if(txtSearch.getText().equals(""))
                    {
                        ObservableList<EmployeeDetails> employeeList = EmployeeDetailsDAO.getAllRecords();
                        populateTable(employeeList);
                    }
                    else
                    {
                        ObservableList<EmployeeDetails> list = EmployeeDetailsDAO.search(txtSearch.getText());
                        populateTable(list);
                    }
                }
                catch (SQLException e )
                {
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

}
