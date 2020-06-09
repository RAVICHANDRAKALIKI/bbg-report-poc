package dev.ravi.bbgreport;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SampleApp {

    public static Connection connection;
    public static Report myReport;
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

    public static void main(String[] args) throws SQLException, IOException {
        Settings prop = Settings.get();
        connection =DriverManager.getConnection(
                    prop.getString("database.url"),
                    prop.getString("database.user"),
                    prop.getString("database.password"));

        Chrome.initialize(prop.getString("web.page.home.url"));

        try {
            HomePage.login(
                    prop.getString("web.page.login"),
                    prop.getString("web.page.password"));


            myReport = new Report("Compare-Report-" +LocalDateTime.now().format(dtf));

            for (int page = 1; page <= 1; page++) {

                EmployeePage employeePage = EmployeePage.readPageData();

                employeePage.getEmpPageData().keySet().forEach( empid -> {

                    try {
                        matchDataWebPage(empid,
                                employeePage.getEmpPageData().get(empid),
                                EmployeeDBService.getEmployeeRecord(empid));

                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }
                });

                EmployeePage.goToNextPage();
            }
            myReport.saveAndCloseReport();
            Chrome.driver.quit();

        }
        catch (Exception e) {
            Chrome.driver.quit();
            connection.close();
            e.printStackTrace();
        }
    }


    public static void matchDataWebPage(String empId, List<String> empPageData, Employee employee) {

        boolean matched = true;
        String reason = "";

        if (employee == null) {
            matched = false;
            reason ="Employee Not found on Database";
        }
        else {
            int mismatch = 0;
            if (! employee.getFirst_name().equals(empPageData.get(0)) ) {
                matched = false;
                reason = "Mismatched first name";
                mismatch++;
            }
            if (! employee.getLast_name().equals(empPageData.get(1))) {

                matched = false;
                if (mismatch > 0) {
                    reason += ", last Name";
                }
                else {
                    reason = "Mismatched Last name";
                }
                mismatch++;
            }
            if (! employee.getGender().equals(empPageData.get(2))) {

                matched = false;
                if (mismatch > 0) {
                    reason += ", gender";
                }
                else {
                    reason = "Mismatched gender";
                }
                mismatch++;
            }

            String b = sdf.format(employee.getBirth_date());
            if (! b.equals(empPageData.get(3))) {
                matched = false;
                if (mismatch > 0) {
                    reason += ", birth date";
                }
                else {
                    reason = "Mismatched Birth Date (" + b + "," + empPageData.get(3) + ")";
                }
                mismatch++;
            }
            String h = sdf.format(employee.getHire_date());
            if (! h.equals(empPageData.get(4))) {
                matched = false;
                if (mismatch > 0) {
                    reason += ", hire date(" + h + "," + empPageData.get(4) + ")";
                }
                else {
                    reason = "Mismatched Hire Date (" + h + "," + empPageData.get(4) + ")";
                }
                mismatch++;
            }
        }

        myReport.addRow(empId,empPageData,employee,matched,reason);
    }


}
