package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTestUtils {
    private WebDriver driver;
    private WebDriverWait wait10;
    private Properties prop;

    public WebDriverWait getWait10() {
        return wait10;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void loginBackEnd() {

        getDriver().findElement(By.xpath("//input[@id='user_login']"))
                .sendKeys(prop.getProperty("default.admin.username"));
        getDriver().findElement(By.xpath("//input[@id='user_pass']"))
                .sendKeys(prop.getProperty("default.admin.password"));
        getDriver().findElement(By.xpath("//input[@id='wp-submit']"))
                .click();
    }

    @BeforeMethod
    protected void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        wait10 = new WebDriverWait(driver, 10);
//Properties for driver
        prop = new Properties();
        InputStream propStreem = getClass().getClassLoader().getResourceAsStream("local.properties");
        if (propStreem != null) {
            try {
                prop.load(propStreem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                throw new FileNotFoundException("property file '" + "local.properties" + "' not found in the classpath");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @AfterMethod
    public void closeDriver() {
        getDriver().quit();
    }

}
