package com.qs.platform.testcases;

import com.qs.BaseConfig.BaseTest;
import com.qs.Pages.Insights;
import com.qs.platform.RetryUtility.RetryAnalyzer;
import org.testng.annotations.*;

public class TC101_CaptureAndVerifyTheDatesAcrossAllThePreviewCards extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void captureAndVerifyTheDatesAcrossAllThePreviewCards() {
        getDriver().get(config.getProperty("insights.url"));
        Insights insights = new Insights(getDriver());
        insights.acceptCookiesBanner();
        insights.selectQSNewsValueFromTopicsDropDown(Insights.TopicsDropDown.QS_NEWS);
        insights.captureAndVerifyTheDates();
    }
}
