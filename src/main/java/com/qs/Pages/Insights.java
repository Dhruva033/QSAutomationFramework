package com.qs.Pages;

import com.qs.utility.CommonUtils;
import com.qs.utility.Constants;
import com.qs.utility.PropertyUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class Insights {

    private WebDriver driver;
    private final CommonUtils utils;
    private static final Logger log = LoggerFactory.getLogger(Insights.class);
    private static final PropertyUtils locator = new PropertyUtils(Constants.INSIGHTS_PROPERTIES_PATH);
    List<WebElement> allCards = new ArrayList<>();
    List<WebElement> allDates = new ArrayList<>();

    public Insights(WebDriver driver) {
        this.driver = driver;
        this.utils = new CommonUtils(driver);
    }

    /**
     * This enum helps to handle the Topics Dropdown
     */
    public enum TopicsDropDown {
        QS_NEWS("QS news");
        private final String value;

        TopicsDropDown(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * This Method helps to handle the cookies Banner
     */
    public void acceptCookiesBanner() {
        log.info("This Method helps to handle the cookies Banner");
        utils.handleCookieBanner(locator.getProperty("insights.Cookies"));
    }

    /**
     * This method helps to select QS News Value From Topics DropDown
     * @param topicsDropDown <topicsDropDown as enum>
     */
    public void selectQSNewsValueFromTopicsDropDown(TopicsDropDown topicsDropDown) {
        log.info("This method helps to select QS News Value From Topics DropDown");
        utils.scrollToElement(locator.getProperty("Insights.Topics"));
        utils.selectDropdownByVisibleText(locator.getProperty("Insights.Topics"), topicsDropDown.getValue());
    }

    /**
     * This Method helps to capture and Display the dates in the console
     */
    public void captureAndVerifyTheDates() {
        log.info("This Method helps to capture and Display the dates in the console");
        int pageNum = 1;
        while (true) {
            try {
                utils.waitTillTheVisibility(locator.getProperty("Insights.CurrentPage").replace("replaceText",String.valueOf(pageNum)));
                utils.waitForPageLoad(1000);
                List<WebElement> cardsOnPage = utils.findElements(locator.getProperty("Insights.PreviewCards"));
                List<WebElement> datesOnPage = utils.findElements(locator.getProperty("Insights.Dates"));
                boolean anyDateFound = false;
                for (WebElement date : datesOnPage) {
                    String text = date.getText().trim();
                    if (!text.isEmpty()) {
                        if (!anyDateFound) {
                            System.out.println("Dates found in the page " + pageNum);
                            anyDateFound = true;
                        }
                        System.out.println("Date: " + text);
                    } else {
                        System.out.println("Date not found in this preview card");
                    }
                }
                allCards.addAll(cardsOnPage);
                allDates.addAll(datesOnPage);
                if (utils.isElementPresent(locator.getProperty("Insights.NextButton"))) {
                    WebElement nextBtn = utils.findElement(locator.getProperty("Insights.NextButton"));
                    utils.scrollToElement(locator.getProperty("Insights.NextButton"));
                    utils.waitForPageLoad(5000);
                    nextBtn.click();
                    pageNum++;
                } else {
                    break;
                }
            } catch (Exception e) {
                Assert.fail("Capture and Verify Dates Failed " + e.getMessage());
            }
        }
        System.out.println("Total cards found: " + allCards.size());
        System.out.println("Total dates found: " + allDates.size());
    }

    /**
     * This method helps to Identify the Broken Images and Display the url in Console
     * @param attribute <attribute as enum>
     */
    public void checkBrokenImagesAcrossAllPages(CommonUtils.Attributes attribute) {
        log.info("This method helps to Identify the Broken Images and Display the url in Console");
        int pageNum=1;
        while (true) {
            try{
            boolean hasBrokenUrls=utils.checkBrokenUrlsForMultiplePages(locator.getProperty("Insights.Image"), attribute.getValue(),
            locator.getProperty("Insights.CurrentPage").replace("replaceText",String.valueOf(pageNum)));
               if (!hasBrokenUrls){
                   System.out.println("No broken URLs found on page " + pageNum);
               }
                if (utils.isElementPresent(locator.getProperty("Insights.NextButton"))) {
                    utils.findElement(locator.getProperty("Insights.NextButton"));
                    utils.scrollToElement(locator.getProperty("Insights.NextButton"));
                    utils.waitForPageLoad(5000);
                    utils.click(locator.getProperty("Insights.NextButton"));
                    pageNum++;
                } else {
                    break;
                }
            } catch (Exception e) {
                log.error("Check for the Broken Images Failed {}", e.getMessage());
                Assert.fail("Check for the Broken Images Failed " + e.getMessage());
            }
        }
    }
}
