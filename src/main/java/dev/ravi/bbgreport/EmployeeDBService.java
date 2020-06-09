package dev.ravi.bbgreport;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDBService {


    public void printSample() throws SQLException {
        Statement stmt = SampleApp.connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from employees limit 20");


        while (rs.next()) {
            int emp_no = rs.getInt("emp_no");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            String gender = rs.getString("gender");
            Date birth_date = rs.getDate("birth_date");
            Date hire_date = rs.getDate("hire_date");

        }
        rs.close();

    }

    public static Employee getEmployeeRecord(int empId) throws SQLException {

        Statement stmt = SampleApp.connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from employees where emp_no = " + empId);

        Employee employee = null;
        if (rs.next()) {
            int emp_no = rs.getInt("emp_no");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            String gender = rs.getString("gender");
            Date birth_date = rs.getDate("birth_date");
            Date hire_date = rs.getDate("hire_date");

            employee = new Employee(empId, first_name, last_name, gender, birth_date, hire_date);
            System.out.println("DB:" + emp_no + "," +
                    first_name + "," +
                    last_name + "," +
                    gender + "," +
                    birth_date + "," +
                    hire_date);

        }
        rs.close();
        return employee;

    }

    public static Employee getEmployeeRecord(String empId) throws SQLException {

        return getEmployeeRecord(Integer.valueOf(empId));
    }
}

