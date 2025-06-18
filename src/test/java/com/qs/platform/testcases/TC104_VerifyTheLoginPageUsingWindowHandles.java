package com.qs.platform.testcases;

import com.qs.BaseConfig.BaseTest;
import com.qs.Pages.Login;
import com.qs.platform.RetryUtility.RetryAnalyzer;
import org.testng.annotations.*;

public class TC104_VerifyTheLoginPageUsingWindowHandles extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyTheLoginPageUsingWindowHandles() {
        getDriver().get(config.getProperty("login.url"));
        Login login = new Login(getDriver());
        login.acceptCookiesBanner();
        login.clickOnTheRequiredPortalForLogin(Login.LoginPortals.QS_HUB);
        login.fetchQsHubInnerTextDisplayAndCloseThePreviousTab();
    }
}
