package dev.ravi.bbgreport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeePage {

    private int pageId;
    private Map<String, List<String>> empPageData = new TreeMap<>();

    public static EmployeePage readPageData() {
        EmployeePage employeePage = new EmployeePage();
        WebElement pageHeading =  Chrome.driver.findElement(new By.ByXPath(Constants.PAGE_INFO_DIV_XPATH)) ;

        Pattern pattern = Pattern.compile("(Page Number: )([0-9]+)( / )([0-9]+)$");
        Matcher matcher = pattern.matcher(pageHeading.getText());
        matcher.find();

        int curPage = Integer.valueOf(matcher.group(2));
        employeePage.setPageId(curPage);

        int allPages = Integer.valueOf(matcher.group(4));

        List<WebElement> rows  = Chrome.driver.findElements(new By.ByXPath(Constants.PAGE_ALL_ROWS)) ;
        int repetitions = curPage < allPages ? Constants.EMP_PAGE_ROW_SIZE : rows.size();

        for (int i = 0 ; i < repetitions ; i++) {
            String empId = "";
            for (int j = 0; j < Constants.COL_SIZE ; j++) {
                String cell_xpth = Constants.CELL_ELEMENT
                        .replace("$col$", String.valueOf(j + 1))
                        .replace("$row$",String.valueOf(i + 1));
                By cellXpath = new By.ByXPath(cell_xpth);

                WebElement cell = Chrome.driver.findElement(cellXpath);

                if (j == 0) {
                    empId = cell.getText();
                    ArrayList<String> row = new ArrayList<>();
                    employeePage.getEmpPageData().put(empId, row);
                }
                else {
                    employeePage.getEmpPageData().get(empId).add(cell.getText());
                }
            }

        }

        return employeePage;
    }

    public static void goToNextPage() {
        By nextPageXpath = new By.ByXPath(Constants.NEXT_PAGE_BTN_XPATH);
        WebElement nextPage = Chrome.driver.findElement(nextPageXpath);
        nextPage.click();

        By pageInfoXpath = new By.ByXPath(Constants.PAGE_INFO_DIV_XPATH) ;
        ExpectedCondition pageLoad = ExpectedConditions.presenceOfAllElementsLocatedBy(pageInfoXpath);
        new WebDriverWait(Chrome.driver,15).until(pageLoad);

    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public Map<String, List<String>> getEmpPageData() {
        return empPageData;
    }

    public void setEmpPageData(Map<String, List<String>> empPageData) {
        this.empPageData = empPageData;
    }

    public void printPage() {

        this.getEmpPageData().keySet().forEach( empid -> {
            StringBuffer stringBuffer = new StringBuffer(empid);
            this.getEmpPageData().get(empid).forEach( colData -> {
                stringBuffer.append(", " + colData);
            });
            System.out.println(stringBuffer.toString());
        });
    }
}
