package com.placelab.tests;

import com.placelab.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTestIncorrectPassword {
    private WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setup(final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");

        System.out.println("Opened browser: " + browser);
    }

    @Parameters("email")
    @Test
    public void testPositiveLogin(final String email) {
        final String actualPageTitle = driver.getTitle();
        final String expectedPageTitle = "PlaceLab";
        final String expectedMessage = "Invalid credentials!";

        Assert.assertEquals(actualPageTitle, expectedPageTitle);
        System.out.println("Page title: " + actualPageTitle);

        final boolean isHeaderDisplayed = driver.findElement(By.cssSelector("div#login > p.headline")).isDisplayed();
        Assert.assertTrue(isHeaderDisplayed);

        String headerText = driver.findElement(By.cssSelector("div#login > p.headline")).getText();
        System.out.println("Header is displayed with the following text: " + headerText);

        final boolean isEmailFieldDisplayed = driver.findElement(By.id("email")).isDisplayed();
        Assert.assertTrue(isEmailFieldDisplayed);

        driver.findElement(By.id("email")).sendKeys(email);

        final boolean isPasswordFieldDisplayed = driver.findElement(By.id("password")).isDisplayed();
        Assert.assertTrue(isPasswordFieldDisplayed);

        driver.findElement(By.id("password")).sendKeys("1234");

        driver.findElement(By.xpath("//*[@id=\"login_form\"]/input[4]")).click();

        final boolean isInvalidCredentialsMessageDisplayed = driver.findElement(By.className("error-area")).isDisplayed();
        Assert.assertTrue(isInvalidCredentialsMessageDisplayed);

        final String actualMessage = driver.findElement(By.className("error-area")).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Validate error message when an incorrect password is provided");

        System.out.println("Error message is displayed when an incorrect password is provided and the text is: " + actualMessage);

        final boolean isForgotPasswordProvided = driver.findElement(By.linkText("Forgot your password?")).isDisplayed();
        Assert.assertTrue(isForgotPasswordProvided);

        driver.findElement(By.linkText("Forgot your password?")).click();

        headerText = driver.findElement(By.cssSelector("div#login > p.headline")).getText();

        System.out.println("Header is displayed with the following text: " + headerText);

        driver.findElement(By.id("email")).isDisplayed();

        driver.findElement(By.id("email")).sendKeys(email);

        driver.findElement(By.xpath("//*[@id=\"login_form\"]/input[3]")).isDisplayed();

        driver.findElement(By.name("commit")).click();

        final String actualSentEmailMessage = driver.findElement(By.xpath("//*[@id=\"login\"]/p")).getText();

        System.out.println("The following text is displayed after trying to reset your password: " + actualSentEmailMessage);

        final boolean ifEmailNotSent = driver.findElement(By.linkText("Didn't get it?")).isDisplayed();
        Assert.assertTrue(ifEmailNotSent);

        driver.findElement(By.linkText("Didn't get it?")).click();
    }

    @AfterTest
    public void teardown() {
        driver.close();
    }
}
