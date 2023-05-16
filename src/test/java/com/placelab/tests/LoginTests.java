package com.placelab.tests;

import com.placelab.pages.HomePage;
import com.placelab.pages.LoginPage;
import com.placelab.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.UUID;

public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;


    @Parameters("browser")
    @BeforeTest
    public void setup(final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");

        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
    }

    @Parameters({"email", "password"})
    @Test(priority = 1, testName = "Login Test with valid credentials", description = "Validate that the user is able to login with valid email and correct password")
    public void testPositiveLogin(final String email, final String password) {
        final String expectedUserRole = "Group Admin";

        loginPage.validateLoginPageContent();

        loginPage.enterCredentials(email, password);

        loginPage.validateAndClickLoginBtn();

        final boolean isUserRoleDisplayed = driver.findElement(By.id("user-role")).isDisplayed();
        Assert.assertTrue(isUserRoleDisplayed);

        final String actualUserRole = driver.findElement(By.id("user-role")).getText();
        Assert.assertEquals(actualUserRole, expectedUserRole, "Validate a user role");
    }

    @Test(priority = 2, dependsOnMethods = "testPositiveLogin", testName = "Logout Test", description = "Validate that the user is able to logout after the user is logged in with valid credentials")
    public void testLogout() {
        homePage.logout();
    }

    @Test (priority = 3, testName = "Login with no credentials provided", description = "Try to login without provided credentials")
    public void loginWithNotProvidedCredentials() {
        loginPage.validateLoginPageContent();

        loginPage.enterCredentials("", "");

        loginPage.validateAndClickLoginBtn();

        loginPage.validateLoginPageErrorMessage();
    }

    @Parameters("password")
    @Test(priority = 4, testName = "Login with invalid Email", description = "Try to login with an invalid Email and valid password")
    public void loginWithInvalidEmail(final String password) {
        loginPage.validateLoginPageContent();

        final String fakeEmail = UUID.randomUUID().toString();
        loginPage.enterCredentials(fakeEmail, password);

        loginPage.validateAndClickLoginBtn();

        loginPage.validateLoginPageErrorMessage();
    }

    @Parameters("email")
    @Test(priority = 6, testName = "Login with an incorrect password", description = "Try to login with a valid email and an incorrect password and try to reset it")
    public void loginWithIncorrectPassword(final String email) {
        loginPage.validateLoginPageContent();

        final String randomPassword = UUID.randomUUID().toString();
        loginPage.enterCredentials(email, randomPassword);

        loginPage.validateAndClickLoginBtn();

        loginPage.validateLoginPageErrorMessage();

        loginPage.passwordReset(email);
    }

    @Parameters("email")
    @Test(priority = 6, testName = "Login with the empty password field", description = "Try to login with valid email and not provided password")
    public void loginWithEmptyPassword(final String email) {
        loginPage.validateLoginPageContent();

        loginPage.enterCredentials(email, "");

        loginPage.validateAndClickLoginBtn();

        loginPage.validateLoginPageErrorMessage();
    }

    @AfterTest
    public void teardown() {
        driver.close();
    }
}
