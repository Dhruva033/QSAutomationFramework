package com.qs.platform.testcases;

import com.qs.BaseConfig.BaseTest;
import com.qs.Pages.ContactUs;
import com.qs.platform.RetryUtility.RetryAnalyzer;
import com.qs.utility.ReadExcel;
import org.testng.annotations.*;
import java.util.Map;

public class TC100_ValidateTheSubmissionFlowOnContactUsPage extends BaseTest {
    static String TestDataSheet = "ContactUsForm";
    Map<String, Object> testDataMap;

    @Factory(dataProvider = "testDataProvider")
    public TC100_ValidateTheSubmissionFlowOnContactUsPage(Map<String, Object> testDataMap) {
        this.testDataMap = testDataMap;
    }

    @DataProvider
    public static Object[][] testDataProvider() {
        return ReadExcel.getTestData(TestDataSheet);
    }

    /**
     * Step  4 (Click on Submit)
     * and Step 5 (Capture success message using TestNG assertion)
     * are intentionally skipped as per requirement and are not automated.
     */
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void validateTheSubmissionFlowOnContactUsPage() {
        getDriver().get(config.getProperty("contactus.url"));
        ContactUs contactUs = new ContactUs(getDriver());
        contactUs.setTestData(testDataMap);
        contactUs.acceptCookiesBanner();
        contactUs.validateTheContactUsHeader();
        contactUs.enterFirstName();
        contactUs.enterLastName();
        contactUs.selectCountry();
        contactUs.enterWorkEmailAddress();
        contactUs.enterJobTitle();
        contactUs.enterOrganisationName();
        contactUs.selectAreaOfInterest(ContactUs.AreaOfInterest.SUSTAINABILITY);
        contactUs.selectAreaOfInterest(ContactUs.AreaOfInterest.EMPLOYABILITY);
        contactUs.selectAreaOfInterest(ContactUs.AreaOfInterest.RANKINGS);
        contactUs.selectAreaOfInterest(ContactUs.AreaOfInterest.DIGITAL_INNOVATION);
        contactUs.enterMessageToQs();
        contactUs.clickOnAgreeToReceiveCommunication();
        contactUs.clickOnSubmitButton();
    }
}
