package com.model;

import javafx.beans.property.*;

public class EmployeeDetails {

    private IntegerProperty employeeId;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty department;
    private StringProperty emailAddress;
    private IntegerProperty contactNo;
    private DoubleProperty salary;

    public EmployeeDetails(){
        this.employeeId = new SimpleIntegerProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.department = new SimpleStringProperty();
        this.emailAddress = new SimpleStringProperty();
        this.contactNo = new SimpleIntegerProperty();
        this.salary = new SimpleDoubleProperty();
    }

    //---------------------------------------------------
    public int getEmployeeId() {
        return  employeeId.get();
    }

    public IntegerProperty getEmployeeIdProperty() {
        return  employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId.set(employeeId);
    }

    //----------------------------------------------------
    public String getfirstName() {
        return  firstName.get();
    }

    public StringProperty getFirstNameProperty() {
        return  firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    //-----------------------------------------------------
    public String getlastName() {
        return  lastName.get();
    }

    public StringProperty getLastNameNameProperty() {
        return  lastName;
    }

    public void setLastNameName(String lastName) {
        this.lastName.set(lastName);
    }

    //-------------------------------------------------------
    public String getdepartment() {
        return  department.get();
    }

    public StringProperty getDepartmentProperty() {
        return  department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    //-------------------------------------------------------
    public String getemailAddress() {
        return  emailAddress.get();
    }

    public StringProperty getEmailAddressProperty() {
        return  emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress.set(emailAddress);
    }

    //-------------------------------------------------------
    public int getcontactNo() {
        return  contactNo.get();
    }

    public IntegerProperty getContactNoProperty() {
        return  contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo.set(contactNo);
    }

    //--------------------------------------------------------
    public double getsalary() {
        return  salary.get();
    }

    public DoubleProperty getSalaryProperty() {
        return  salary;
    }

    public void setSalary(double salary) {
        this.salary.set(salary);
    }
}

