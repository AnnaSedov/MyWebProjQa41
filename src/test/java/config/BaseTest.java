package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.BasePage;

import java.time.Duration;

public class BaseTest {
    private static final ThreadLocal<WebDriver> driverThreadLocal= new ThreadLocal<>();
    public static WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    @BeforeMethod
    @Parameters("browser")
    // if parameters is empty -> deafault firefox or edge
    public  void setUp(@Optional("firefox") String browser){
        if (browser.equalsIgnoreCase("chrome")){
            //driver
            WebDriverManager.chromedriver().setup();
            ChromeOptions options=new ChromeOptions();
            // english
            options.addArguments("--lang=en");
            //сокращает время для chrome
          //  options.addArguments("--headless");
            driverThreadLocal.set(new ChromeDriver(options));

        }
        else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options= new FirefoxOptions();
            options.addArguments("intl.accept_languages","en");
            driverThreadLocal.set(new FirefoxDriver(options));

        }
        else if (browser.equalsIgnoreCase("edge")) {
            // Настройки для Edge
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.setCapability("language", "en");
            //options.addArguments("--headless");
            driverThreadLocal.set(new EdgeDriver(options));
        }
        else{ throw new IllegalArgumentException("Invalid browser"+browser);       }
        WebDriver driver=getDriver();
        driver.manage().window().maximize();// расширяет окно
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(20000));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(20000));
        BasePage.setDriver(driver);


    }
@AfterMethod
    public void tearDown(){
        WebDriver driver=getDriver();
        if (driver !=null){
            driver.quit();
            driverThreadLocal.remove();
        }
}
}
