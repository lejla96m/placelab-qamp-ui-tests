package com.placelab.tests;

import com.placelab.pages.HomePage;
import com.placelab.pages.LoginPage;
import com.placelab.pages.SinglePlaceSearchReportFormPage;
import com.placelab.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class SinglePlaceSearchReportTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private SinglePlaceSearchReportFormPage singlePlaceSearchReportFormPage;

    @Parameters({"browser", "email", "password"})
    @BeforeTest
    public void setup(final String browser, final String email, final String password) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");

        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
        this.singlePlaceSearchReportFormPage = new SinglePlaceSearchReportFormPage(driver);

        loginPage.validateLoginPageContent();

        loginPage.enterCredentials(email, password);

        loginPage.validateAndClickLoginBtn();
    }

    @Test(priority = 1, testName = "Creating a Single Place Search Report", description = "Validate that the user is able to create a Single Place Search Report")
    public void createSinglePlaceSearchReport() {

        homePage.validateHomePageContent();

        homePage.navigateToCreateSinglePlaceSearchReport();

        singlePlaceSearchReportFormPage.validateSinglePlaceReportContent();

        singlePlaceSearchReportFormPage.enterSinglePlaceSearchReportData();

        singlePlaceSearchReportFormPage.createReport();
    }

   @AfterTest
    public void teardown() {
        homePage.logout();

        driver.close();
    }
}
