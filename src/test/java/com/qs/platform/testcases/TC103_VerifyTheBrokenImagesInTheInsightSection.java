package com.qs.platform.testcases;

import com.qs.BaseConfig.BaseTest;
import com.qs.utility.CommonUtils;
import com.qs.Pages.Insights;
import com.qs.platform.RetryUtility.RetryAnalyzer;
import org.testng.annotations.*;

public class TC103_VerifyTheBrokenImagesInTheInsightSection extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyTheBrokenImagesInTheInsightSection() {
        getDriver().get(config.getProperty("insights.url"));
        Insights insights = new Insights(getDriver());
        insights.acceptCookiesBanner();
        insights.checkBrokenImagesAcrossAllPages(CommonUtils.Attributes.SRC);
    }
}
