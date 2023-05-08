package com.placelab.tests;

import com.placelab.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTestInvalidEmail {
    private WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setup(final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");

        System.out.println("Opened browser: " + browser);
    }

    @Parameters("password")
    @Test
    public void testPositiveLogin(final String password) {
        final String actualPageTitle = driver.getTitle();
        final String expectedPageTitle = "PlaceLab";
        final String expectedMessage = "Invalid credentials!";

        Assert.assertEquals(actualPageTitle, expectedPageTitle);
        System.out.println("Page title: " + actualPageTitle);

        final boolean isHeaderDisplayed = driver.findElement(By.cssSelector("div#login > p.headline")).isDisplayed();
        Assert.assertTrue(isHeaderDisplayed);

        final String headerText = driver.findElement(By.cssSelector("div#login > p.headline")).getText();
        System.out.println("Header is displayed with the following text: " + headerText);

        final boolean isEmailFieldDisplayed = driver.findElement(By.id("email")).isDisplayed();
        Assert.assertTrue(isEmailFieldDisplayed);

        driver.findElement(By.id("email")).sendKeys("abc@invalid.com");

        final boolean isPasswordFieldDisplayed = driver.findElement(By.id("password")).isDisplayed();
        Assert.assertTrue(isPasswordFieldDisplayed);

        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//*[@id=\"login_form\"]/input[4]")).click();

        final boolean isInvalidCredentialsMessageDisplayed = driver.findElement(By.className("error-area")).isDisplayed();
        Assert.assertTrue(isInvalidCredentialsMessageDisplayed);

        final String actualMessage = driver.findElement(By.className("error-area")).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Validate error message when an invalid Email is provided");

        System.out.println("Error message is displayed when an invalid Email is provided and the text is: " + actualMessage);
    }

    @AfterTest
    public void teardown() {
        driver.close();
    }
}
