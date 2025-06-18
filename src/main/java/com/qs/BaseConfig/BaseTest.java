package com.qs.BaseConfig;

import com.qs.utility.Constants;
import com.qs.utility.PropertyUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static PropertyUtils config = new PropertyUtils(Constants.CONFIG_PROPERTIES_PATH);

    /**
     * This Method helps to launch the browser
     * @param browser <browser as String>
     */
    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional(Constants.CHROME) String browser) {
        WebDriver localDriver;
        switch (browser.toLowerCase()) {
            case Constants.CHROME:
                localDriver = new ChromeDriver();
                break;
            case Constants.FIREFOX:
                localDriver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        String waitTime = config.getProperty("implicit.wait");
        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(waitTime)));
        localDriver.manage().window().maximize();

        driver.set(localDriver);
    }

    /**
     * This method returns the WebDriver
     * @return
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * This method helps to quit the browser
     */
    @AfterMethod
    public void tearDown() {
        getDriver().quit();
    }


}
