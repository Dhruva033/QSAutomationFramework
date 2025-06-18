package com.qs.platform.RetryUtility;

import com.qs.utility.Constants;
import com.qs.utility.PropertyUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount;

    static {
        PropertyUtils config = new PropertyUtils(Constants.CONFIG_PROPERTIES_PATH);
        String retryValue = config.getProperty("retry.count");
        try {
            maxRetryCount = Integer.parseInt(retryValue);
        } catch (Exception e) {
            throw new RuntimeException(" Failed to read or parse 'retry.count' from config" + e.getMessage());
        }
    }

    /**
     * This Method helps to Run the Test classes if the TestFails
     * @param result
     * @return Boolean
     */
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
