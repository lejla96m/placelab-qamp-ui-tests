package com.placelab.tests;

import com.placelab.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SmokeTest {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = WebDriverSetup.getWebDriver(System.getProperty("browser"));
        driver.get("https://demo.placelab.com/");

        System.out.println("Opened browser");
    }

    @Test
    public void openPage() {
        System.out.println("Opened browser " + driver);
    }

    @AfterTest
    public void printTitle() {
        System.out.println(driver.getTitle());
    }
}
