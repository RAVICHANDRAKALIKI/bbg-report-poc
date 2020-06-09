package dev.ravi.bbgreport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Chrome {

    public static WebDriver driver;

    public static void initialize(String url) throws IOException {

        System.setProperty("webdriver.chrome.driver", Constants.LOCAL_CHROMEDRIVER);
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(Boolean.parseBoolean(Settings.get().getString("web.driver.headless")));
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);


    }


}
