package com.qs.Pages;

import com.qs.utility.CommonUtils;
import com.qs.utility.Constants;
import com.qs.utility.PropertyUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
public class Login {

    private final WebDriver driver;
    private final CommonUtils utils;
    private static final Logger log = LoggerFactory.getLogger(Login.class);
    private static final PropertyUtils locator = new PropertyUtils(Constants.LOGIN_PROPERTIES_PATH);
    private static final PropertyUtils config = new PropertyUtils(Constants.CONFIG_PROPERTIES_PATH);

    public Login(WebDriver driver) {
        this.driver = driver;
        this.utils = new CommonUtils(driver);
    }

    /**
     * This enum helps to handle the Login Portals
     */
    public enum LoginPortals {
        QS_HUB("Qs.Hub"),
        EVENT_PORTAL("Events.Portal");
        private final String value;

        LoginPortals(String value) {
            this.value = value;
        }

        public String getLocatorValue() {
            return locator.getProperty(value);
        }
    }

    /**
     * This Method helps to handle the cookies Banner
     */
    public void acceptCookiesBanner() {
        log.info("This Method helps to handle the cookies Banner");
        utils.handleCookieBanner(locator.getProperty("Login.Cookies"));
    }

    /**
     * This Method helps to click on the Required Portal for Login(Qs.Hb/Event portal)
     * @param loginPortals <loginPortals as Enum>
     */
    public void clickOnTheRequiredPortalForLogin(LoginPortals loginPortals) {
        log.info("This Method helps to click on the Required Portal for Login");
        utils.scrollToElement(loginPortals.getLocatorValue());
        utils.click(loginPortals.getLocatorValue());
    }

    /**
     * This Method helps to click on the Required Portal for Login(Qs.Hb/Event portal)
     */
    public void fetchQsHubInnerTextDisplayAndCloseThePreviousTab() {
        log.info("This Method helps to click on the Required Portal for Login(Qs.Hb/Event portal)");
        try {
            String parentWindow = driver.getWindowHandle();
            utils.switchToTheChildWindow();
            String QsText = utils.getText(locator.getProperty("QsHub.Logo"));
            System.out.println("The QS Hub Logo Inner text is" + "------>" + QsText);
            driver.switchTo().window(parentWindow);
            Assert.assertEquals(driver.getCurrentUrl(), config.getProperty("login.url"),
                    "Not switched back to the parent window");
            driver.close();
        } catch (Exception e) {
            log.error("Unable to switch to the window and fetch the Logo inner text {}", e.getMessage());
            Assert.fail("Unable to switch to the window and fetch the Logo inner text" + e.getMessage());
        }
    }

}
