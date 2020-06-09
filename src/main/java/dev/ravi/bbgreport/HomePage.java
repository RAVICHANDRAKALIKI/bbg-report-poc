package dev.ravi.bbgreport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    public static void login(String email, String password) {
        WebDriver driver = Chrome.driver;
        By loginLinkXpath =  new By.ByXPath(Constants.LOGIN_LINK_XPATH);
        WebElement loginLink = driver.findElement(loginLinkXpath);
        loginLink.click();

        By emailIdXpath = new By.ByXPath(Constants.EMAIL_ID_XPATH);
        WebElement emailId = driver.findElement(emailIdXpath);
        emailId.sendKeys(email);

        By passwordXpath = new By.ByXPath(Constants.PASSWORD_XPATH);
        WebElement passwordInput = driver.findElement(passwordXpath);
        passwordInput.sendKeys(password);

        By loginBtnXpath = new By.ByXPath(Constants.LOGIN_BTN_XPATH);
        WebElement loginBtn = driver.findElement(loginBtnXpath);
        loginBtn.click();

        By nextPageBtnXpath = new By.ByXPath(Constants.NEXT_PAGE_BTN_XPATH);
        ExpectedCondition pageLoad = ExpectedConditions.presenceOfAllElementsLocatedBy(nextPageBtnXpath);
        new WebDriverWait(driver,15).until(pageLoad);
    }

}
