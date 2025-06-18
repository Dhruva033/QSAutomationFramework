package com.qs.platform.testcases;

import com.qs.BaseConfig.BaseTest;
import com.qs.utility.CommonUtils;
import com.qs.Pages.Home;
import com.qs.platform.RetryUtility.RetryAnalyzer;
import org.testng.annotations.*;

public class TC102_VerifyTheBrokenLinksInTheFooterSection extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyTheBrokenLinksInTheFooterSection() {
        getDriver().get(config.getProperty("base.url"));
        Home home = new Home(getDriver());
        home.acceptCookiesBanner();
        home.validateForTheBrokenLinks(CommonUtils.Attributes.HREF);
    }
}
