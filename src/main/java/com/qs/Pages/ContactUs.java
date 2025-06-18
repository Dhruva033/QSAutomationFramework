package com.qs.Pages;

import com.qs.utility.CommonUtils;
import com.qs.utility.Constants;
import com.qs.utility.PropertyUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Map;

public class ContactUs {
    private WebDriver driver;
    private final CommonUtils utils;
    Map<String, Object> testDataMap;
    private static final Logger log = LoggerFactory.getLogger(ContactUs.class);
    private static final PropertyUtils locator = new PropertyUtils(Constants.CONTACT_US_PROPERTIES_PATH);


    public ContactUs(WebDriver driver) {
        this.driver = driver;
        this.utils = new CommonUtils(driver);
    }

    /**
     * This Enum helps to handle the area of interest
     */
    public enum AreaOfInterest {
        DIGITAL_INNOVATION("Digital Innovation"),
        EMPLOYABILITY("Employability"),
        GLOBAL_ENGAGEMENT("Global Engagement"),
        RANKINGS("Rankings"),
        REPUTATION_AND_PERFORMANCE("Reputation and Performance"),
        STUDENT_RECRUITMENT("Student Recruitment"),
        SUSTAINABILITY("Sustainability");
        private String value;

        AreaOfInterest(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * This Method helps to set the test data
     *
     * @param testDataMap <testDataMap as Map<String,Object>>
     */
    public void setTestData(Map<String, Object> testDataMap) {
        this.testDataMap = testDataMap;
    }

    /**
     * This Method Helps to Handle Cookies Banner
     */
    public void acceptCookiesBanner() {
        log.info("This Method helps to handle the cookies Banner");
        utils.handleCookieBanner(locator.getProperty("ContactUs.Cookies"));
    }

    /**
     * This Method helps to Validate Contact Us page Header is Displayed
     */
    public void validateTheContactUsHeader() {
        log.info("This Method helps to Validate The Contact Us page Header is Displayed");
        Assert.assertTrue(utils.findElement(locator.getProperty("ContactUs.Header")).isDisplayed(),
                "The Contact Us Header is not Displayed");
    }

    /**
     * This Method helps to enter the first Name
     */
    public void enterFirstName() {
        log.info("This Method helps to enter the first Name");
        utils.switchToFrame(locator.getProperty("ContactUs.iFrame"));
        utils.sendKeys(locator.getProperty("ContactUs.FirstName"), testDataMap.get("FirstName").toString());
    }

    /**
     * This Method helps to enter the Last Name
     */
    public void enterLastName() {
        log.info("This Method helps to enter the Last Name");
        utils.sendKeys(locator.getProperty("ContactUs.LastName"), testDataMap.get("LastName").toString());
    }

    /**
     * This Method helps to select the country
     */
    public void selectCountry() {
        log.info("This Method helps to select the country");
        utils.selectDropdownByVisibleText(locator.getProperty("ContactUs.Country"), testDataMap.get("Country").toString());
    }

    /**
     * This Method helps to enter the work email address
     */
    public void enterWorkEmailAddress() {
        log.info("This Method helps to enter the work email address");
        utils.sendKeys(locator.getProperty("ContactUs.Email"), testDataMap.get("WorkEmail").toString());
    }

    /**
     * This Method helps to enter the job title
     */
    public void enterJobTitle() {
        log.info("This Method helps to enter the job title");
        utils.sendKeys(locator.getProperty("ContactUs.JobTitle"), testDataMap.get("JobTitle").toString());
    }

    /**
     * This Method helps to select the Area of Interest
     *
     * @param areaOfInterest <areaOfInterest as Enum>
     */
    public void selectAreaOfInterest(AreaOfInterest areaOfInterest) {
        log.info("This Method helps to select area of Interest {}", areaOfInterest.getValue());
        utils.scrollToElement(locator.getProperty("ContactUs.AreaOfInterest").replace("replaceText", areaOfInterest.getValue()));
        utils.waitForPageLoad(800);
        utils.click(locator.getProperty("ContactUs.AreaOfInterest").replace("replaceText", areaOfInterest.getValue()));
    }

    /**
     * This Method helps to enter the Organisation name
     */
    public void enterOrganisationName() {
        log.info("This Method helps to enter the Organisation name");
        utils.sendKeys(locator.getProperty("ContactUs.Organisation"), testDataMap.get("OrganisationName").toString());
    }

    /**
     * This Method helps to enter the Message to Qs
     */
    public void enterMessageToQs() {
        log.info("This Method helps to enter the Message to Qs");
        utils.sendKeys(locator.getProperty("ContactUs.MessageToQs"), testDataMap.get("MessageToQS").toString());
    }

    /**
     * This Method helps to click on I agree to receive communications from QS.
     */
    public void clickOnAgreeToReceiveCommunication() {
        log.info("This Method helps to click on I agree to receive communications from QS.");
        utils.click(locator.getProperty("ContactUs.AgreeToReceive"));
    }

    /**
     * This Method helps to click on Submit button
     */
    public void clickOnSubmitButton() {
        log.info("This Method helps to click on Submit button");
        utils.scrollToElement(locator.getProperty("ContactUs.Submit"));
        utils.click(locator.getProperty("ContactUs.Submit"));
    }


}
