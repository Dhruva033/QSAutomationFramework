# QSAutomationFramework
QSAutomation

This project contains automated test cases for validating features of the QS Insight's , Login and Contact Us pages using Selenium, TestNG, and Java.

# Project Overview

This is an Data Driven testing framework developed using Java , Selenium WebDriver, TestNG, and Maven.

# Prerequisites

1.JDK 8 or above
2.IntelliJ IDEA Community Edition (https://www.jetbrains.com/idea/download/?section=windows)
3.Git 

## Setup Instructions

## Clone the Project in IntelliJ IDEA

1. Open IntelliJ IDEA
2. Go to File -> New -> Project from Version Control -> Git
3. Paste the repository URL
4. Click Clone

## Build the Project

1.Wait for IntelliJ to index and recognize the Maven 'pom.xml'
2.Maven will automatically download dependencies

## How to Run the Tests

1.Open the testng.xml file
2.Add the required test case classes inside the <classes> tag.
3.Right-click -> Run 

## Included Test Cases
1.TC100_ValidateTheSubmissionFlowOnContactUsPage 
2.TC101_CaptureAndVerifyTheDatesAcrossAllThePreviewCards
3.TC102_VerifyTheBrokenLinksInTheFooterSection
4.TC103_VerifyTheBrokenImagesInTheInsightSection
5.TC104_VerifyTheLoginPageUsingWindowHandles

## Framework Highlights
src/main/java

1.**BaseTest** (BaseConfig) – Sets up WebDriver, handles browser launch and teardown  
2. **pages/** – Business logic layer (e.g LoginPage.java,InsightsPage.java)  
3.**PropertyUtils** (utility) – Reads values from config.properties  
4.**Constants** (utility) – Stores global constants like file paths  
5.**ReadExcel** (utility) – Uses **Fillo** to fetch test data from Excel  
6.**CommonUtils** (commonutility) – Reusable actions (sendKeys, scroll, wait, click)

src/test/java

1. **objectrepository/** – Stores object locators for specific pages  
2. **properties/config.properties** – Config for browser type, URL, and wait times  
3. **RetryAnalyzer** (retryUtility) – Adds automatic retries for failed test cases  
4. **testcases/** – Contains actual test case classes organized by scenario

src/test/resources

1. **testdata/QSTestData.xlsx** –  Contains test data used in test cases. 
 




