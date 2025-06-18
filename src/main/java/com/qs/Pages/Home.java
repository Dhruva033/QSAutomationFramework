package com.qs.Pages;

import com.qs.utility.CommonUtils;
import com.qs.utility.Constants;
import com.qs.utility.PropertyUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Home {

    private final WebDriver driver;
    private final CommonUtils utils;
    private static final Logger log = LoggerFactory.getLogger(Home.class);
    private static final PropertyUtils locator = new PropertyUtils(Constants.HOME_PROPERTIES_PATH);

    public Home(WebDriver driver) {
        this.driver = driver;
        this.utils = new CommonUtils(driver);
    }

    /**
     * This Method Helps to Handle Cookies Banner
     */
    public void acceptCookiesBanner() {
        log.info("This Method helps to handle the cookies Banner");
        utils.handleCookieBanner(locator.getProperty("Home.Cookies"));
    }

    /**
     * This Method helps to validate The Broken Links
     * @param attribute <attribute as Enum>
     */
    public void validateForTheBrokenLinks(CommonUtils.Attributes attribute) {
        log.info("This Method helps to validate The Broken Links");
        utils.scrollToElement(locator.getProperty("Home.Footer"));
        utils.checkBrokenUrls(locator.getProperty("Home.FooterLinks"), attribute.getValue());
    }

}
