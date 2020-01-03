package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    public static WebDriver driver;

    public static WebDriver driversSetUp (String browsers) {
        if(driver!=null){
            return driver;
        }//singleton pattern

        if ("chrome".equals(browsers.toLowerCase())) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if ("ff".equals(browsers.toLowerCase())) {
            WebDriverManager.firefoxdriver().setup();//gecko driver
            driver = new FirefoxDriver();
        } else if ("ie".equals(browsers.toLowerCase())) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else if ("opera".equals(browsers.toLowerCase())) {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        } else if ("edge".equals(browsers.toLowerCase())) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }

    public static void closeDriver(){
        if(driver != null){
            // driver.quit();
            driver=null;
        }
    }




}

