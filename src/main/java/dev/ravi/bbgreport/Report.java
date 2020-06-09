package dev.ravi.bbgreport;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Report {

    private XSSFWorkbook xssfWorkbook;
    private XSSFSheet dbSheet;
    private int nextRow = 1;
    private XSSFSheet webSheet;
    private XSSFSheet compareSheet;
    private FileOutputStream fileOutputStream;

    Report(String name) throws IOException, InvalidFormatException {
       File reportFile = new File("./reports/" + name + ".xlsx");
       fileOutputStream  = new FileOutputStream(reportFile);
       xssfWorkbook = new XSSFWorkbook();
       dbSheet = xssfWorkbook.createSheet("Database");
       XSSFRow header;
       XSSFCell cell;

       header = dbSheet.createRow(0);
       cell = header.createCell(0);
       cell.setCellValue("emp_id");
       cell = header.createCell(1);
       cell.setCellValue("emp_first_name");
       cell = header.createCell(2);
       cell.setCellValue("emp_last_name");
       cell = header.createCell(3);
       cell.setCellValue("emp_gender");
       cell = header.createCell(4);
       cell.setCellValue("emp_birth_date");
       cell = header.createCell(5);
       cell.setCellValue("emp_hire_date");

       webSheet = xssfWorkbook.createSheet("Online");


       header = webSheet.createRow(0);
       cell = header.createCell(0);
       cell.setCellValue("Employee Num");
       cell = header.createCell(1);
       cell.setCellValue("First Name");
       cell = header.createCell(2);
       cell.setCellValue("Last Name");
       cell = header.createCell(3);
       cell.setCellValue("Gender");
       cell = header.createCell(4);
       cell.setCellValue("Birth Date");
       cell = header.createCell(5);
       cell.setCellValue("Hire Date");

       compareSheet = xssfWorkbook.createSheet("Compare");

       header = compareSheet.createRow(0);
       cell = header.createCell(0);
       cell.setCellValue("Employee_ID");
       cell = header.createCell(1);
       cell.setCellValue("Matched?");
       cell = header.createCell(2);
       cell.setCellValue("Reason for Mismatch");

   }

   public void addRow(String empId, List<String> webData, Employee employee, boolean matched, String reason) {

        XSSFRow row;
        XSSFCell cell;
        row = webSheet.createRow(nextRow) ;
        cell = row.createCell(0);
        cell.setCellValue(empId);
        for(int i = 1 ; i <= webData.size() ; i++) {
            cell = row.createCell(i);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(webData.get(i - 1));
        }

        row = dbSheet.createRow(nextRow);
        cell = row.createCell(0);
        cell.setCellValue(employee.getEmp_id());
        cell = row.createCell(1);
        cell.setCellValue(employee.getFirst_name());
        cell = row.createCell(2);
        cell.setCellValue(employee.getLast_name());
        cell = row.createCell(3);
        cell.setCellValue(employee.getGender());
        cell = row.createCell(4);
        cell.setCellValue(SampleApp.sdf.format(employee.getHire_date()));
        cell = row.createCell(5);
        cell.setCellValue(SampleApp.sdf.format(employee.getHire_date()));

        row = compareSheet.createRow(nextRow);
        cell = row.createCell(0);
        cell.setCellValue(empId);
        cell = row.createCell(1);
        cell.setCellValue(matched);
        cell = row.createCell(2);
        cell.setCellValue(reason);

       nextRow++;

   }

   public void saveAndCloseReport() throws IOException {
       xssfWorkbook.write(fileOutputStream);
       fileOutputStream.flush();
       fileOutputStream.close();
       xssfWorkbook.close();
   }
}
