package com.placelab.tests;

import com.placelab.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTestPositive {
    private WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setup(final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");

        System.out.println("Opened browser: " + browser);
    }

    @Parameters({"email", "password"})
    @Test
    public void testPositiveLogin(final String email, final String password) {
        final String actualPageTitle = driver.getTitle();
        final String expectedPageTitle = "PlaceLab";
        final String expectedUserRole = "Group Admin";
        final String expectedSignOutButtonText = "Sign out";

        Assert.assertEquals(actualPageTitle, expectedPageTitle);
        System.out.println("Page title: " + actualPageTitle);

        final boolean isHeaderDisplayed = driver.findElement(By.cssSelector("div#login > p.headline")).isDisplayed();
        Assert.assertTrue(isHeaderDisplayed);

        final String headerText = driver.findElement(By.cssSelector("div#login > p.headline")).getText();
        System.out.println("Header is displayed with the following text: " + headerText);

        final boolean isEmailFieldDisplayed = driver.findElement(By.id("email")).isDisplayed();
        Assert.assertTrue(isEmailFieldDisplayed);
        
        driver.findElement(By.id("email")).sendKeys(email);

        final boolean isPasswordFieldDisplayed = driver.findElement(By.id("password")).isDisplayed();
        Assert.assertTrue(isPasswordFieldDisplayed);
        
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//*[@id=\"login_form\"]/input[4]")).click();

        final boolean isUserRoleDisplayed = driver.findElement(By.id("user-role")).isDisplayed();
        Assert.assertTrue(isUserRoleDisplayed);

        final String actualUserRole = driver.findElement(By.id("user-role")).getText();
        Assert.assertEquals(actualUserRole, expectedUserRole, "Validate a user role");

        final boolean isDropdownDisplayed = driver.findElement(By.id("actions-nav-item")).isDisplayed();
        Assert.assertTrue(isDropdownDisplayed);

        driver.findElement(By.id("actions-nav-item")).click();

        final boolean isSignOutDisplayed = driver.findElement(By.linkText("Sign out")).isDisplayed();
        Assert.assertTrue(isSignOutDisplayed);

        final String actualSignOutButtonText = driver.findElement(By.linkText("Sign out")).getText();
        Assert.assertEquals(actualSignOutButtonText, expectedSignOutButtonText, "Validate the text of Sign Out button");

        driver.findElement(By.linkText("Sign out")).click();
    }

    @AfterTest
    public void teardown() {
        driver.close();
    }
}
