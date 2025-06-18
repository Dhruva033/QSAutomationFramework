package com.qs.utility;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class CommonUtils {
    WebDriver driver;
    WebDriverWait wait;
    public static PropertyUtils config = new PropertyUtils(Constants.CONFIG_PROPERTIES_PATH);

    public CommonUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("explicit.wait"))));
    }

    /**
     * This enum helps to handle the attributes
     */
    public enum Attributes {
        HREF("href"),
        SRC("src");
        private final String value;

        Attributes(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * This Method helps to get wait
     * @return
     */
    public WebDriverWait getWait() {
        return wait;
    }
    /**
     * This method helps to click on the element
     * @param xpath <xpath as String>
     */
    public void click(String xpath) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
        } catch (Exception e) {
            Assert.fail("Click failed for: " + xpath + " due to " + e.getMessage());
        }
    }

    /**
     * This Method helps to enter the text into the text field
     * @param xpath <xpath as String>
     * @param value <value as String>
     */
    public void sendKeys(String xpath, String value) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            element.clear();
            element.sendKeys(value);
        } catch (Exception e) {
            Assert.fail("SendKeys failed for: " + xpath + " due to " + e.getMessage());
        }
    }

    /**
     * This Method helps to fetch the text
     * @param xpath <xpath as String>
     * @return <String>
     */
    public String getText(String xpath) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
        } catch (Exception e) {
            System.out.println("GetText failed for: " + xpath + " due to " + e.getMessage());
            return null;
        }
    }

    /**
     * This Method Helps to select the value from the dropdown
     * @param xpath <xpath as String>
     * @param visibleText <visibleText as String>
     */
    public void selectDropdownByVisibleText(String xpath, String visibleText) {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            new Select(dropdown).selectByVisibleText(visibleText);
        } catch (Exception e) {
            Assert.fail("Dropdown selection failed for: " + xpath + " due to " + e.getMessage());
        }
    }

    /**
     * This Method helps to locate the element on the web page
     * @param xpath <xpath as String>
     * @return WebElement
     */
    public WebElement findElement(String xpath) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            System.out.println("FindElement failed for: " + xpath + " due to " + e.getMessage());
            return null;
        }
    }

    /**
     * This Method helps to locate the Web elements on the web page
     * @param xpath <xpath as String>
     * @return List<WebElement>
     */
    public List<WebElement> findElements(String xpath) {
        try {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        } catch (Exception e) {
            System.out.println("FindElements failed for: " + xpath + " due to " + e.getMessage());
            return null;
        }
    }
    /**
     * This method helps to switch to Frame
     * @param xpath <xpath as String>
     */
    public void switchToFrame(String xpath) {
        try {
            WebElement frameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            driver.switchTo().frame(frameElement);
            System.out.println("Switched to frame: " + xpath);
        } catch (Exception e) {
            Assert.fail("Switch to frame failed for: " + xpath + " due to " + e.getMessage());
        }
    }

    /**
     * This Method helps to switch to the default content
     */
    public void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
            System.out.println("Switched back to default content");
        } catch (Exception e) {
            Assert.fail("Switch to default content failed due to: " + e.getMessage());
        }
    }

    /**
     * This Method helps to scroll to particular element
     * @param xpath <xpath as String>
     */
    public void scrollToElement(String xpath) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            Assert.fail("Scroll failed for: " + xpath + " → " + e.getMessage());
        }
    }
    /**
     * This Method helps to wait for specified time
     * @param millis <millis as long>
     */
    public void waitForPageLoad(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Wait interrupted: " + e.getMessage());
        }
    }

    /**
     * This method checks if the element is present in the DOM and returns true, else returns false.
     * It does not wait — it checks immediately.
     * @param xpath <xpath as String>
     * @return true if at least one element is found ;false otherwise
     */
    public boolean isElementPresent(String xpath) {
        try {
            return !driver.findElements(By.xpath(xpath)).isEmpty();
        } catch (Exception e) {
            return false;
        }
}
    /**
     * This Method helps to click on the element using javaScript Executor
     * @param element <element as WebElement>
     */
    public void jsClick(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            System.out.println("JS click executed on element: " + element.toString());
        } catch (Exception e) {
            Assert.fail("JS click failed: " + e.getMessage());
        }
    }
    /**
     * This Method Helps to Handle Cookies Banner
     */
    public void handleCookieBanner(String locator) {
        try {
            WebElement acceptButton =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            Assert.assertTrue(acceptButton.isDisplayed(), "Accept button is not Displayed");
            acceptButton.click();
        } catch (Exception e) {
            Assert.fail("Cookie banner not found or click failed: " + e.getMessage());
        }
    }

    /**
     * This Method helps to wait till the element is visible
     * @param xpath <xpath as String>
     */
    public void waitTillTheVisibility(String xpath) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not visible within the wait time: " + xpath, e);
        }
    }
    /**
     * This Method helps to display the broken url in the console
     * @param xpath  <xpath as String>
     * @param attribute <attribute as String>
     */
    public void checkBrokenUrls(String xpath, String attribute) {
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        for (WebElement element : elements) {
            String url = element.getAttribute(attribute);
            if (url == null || url.isEmpty()) {
                continue;
            }
            try {
                HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());
                connection.setRequestMethod("HEAD");
                connection.connect();
                int statusCode = connection.getResponseCode();
                if (statusCode >= 400) {
                    System.out.println("Broken URL: " + url + "Status: " + statusCode);
                }
            } catch (Exception e) {
                Assert.fail("Error checking URL: " + url + " → " + e.getMessage());
            }
        }
    }
    /**
     * This Method helps to display the broken urls in the console across all the pages
     * @param xpath  <xpath as String>
     * @param attribute <attribute as String>
     */
    public boolean checkBrokenUrlsForMultiplePages(String xpath, String attribute,String currentPageXpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(currentPageXpath)));
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        boolean brokenUrlFound = false;
        for (WebElement element : elements) {
            String url = element.getAttribute(attribute);
            if (url == null || url.isEmpty()) {
                continue;
            }
            try {
                HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());
                connection.setRequestMethod("HEAD");
                connection.connect();
                int statusCode = connection.getResponseCode();
                if (statusCode >= 400) {
                    System.out.println("Broken URL: " + url + "Status: " + statusCode);
                    brokenUrlFound=true;
                }
            } catch (Exception e) {
                Assert.fail("Error checking URL: " + url + " → " + e.getMessage());
            }
        }
        return brokenUrlFound;
    }

    /**
     * This Method helps to switch to the child window
     */
    public void switchToTheChildWindow(){
        try {
            String parentWindow=driver.getWindowHandle();
            Set<String> allTabs = driver.getWindowHandles();
            for(String  allTab:allTabs){
                if(!parentWindow.equals(allTab)){
                    driver.switchTo().window(allTab);
                    System.out.println("Switched to child window: " + allTab);
                    return;
                }
            }
            Assert.fail("No child window found to switch to.");
        }
        catch (Exception e){
            Assert.fail("Window Switching failed"+e.getMessage());
        }
    }
}
