package com.placelab.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class SinglePlaceSearchReportFormPage {
    private final WebDriver driver;
    private final Faker faker = new Faker();
    private final WebDriverWait wait;
    private final static By SINGLE_PLACE_REPORT_FORM = By.id("single_poi_dialog");
    private final static By SINGLE_PLACE_REPORT_HEADER = By.xpath("//*[@id=\"single_poi_dialog\"]/div[1]");
    private final static String EXPECTED_SINGLE_PLACE_REPORT_HEADER = "Create Single Place Search Report";
    private final static By SINGLE_PLACE_REPORT_NAME_FIELD = By.id("name");
    private final static String EXPECTED_REPORT_NAME_PLACEHOLDER_TEXT = "Enter report name...";
    private final static String EXPECTED_PLACE_NAME_PLACEHOLDER_TEXT = "Place Name";
    private final static By PLACE_NAME_FIELD = By.id("single_text");
    private final static By PHONE_FORM = By.xpath("//*[@id=\"single_poi_query\"]/div[3]/div[1]");
    private final static By PHONE_FIELD = By.id("single_phone");
    private final static By PHONE_FORM_NAME = By.xpath("//*[@id=\"single_poi_query\"]/div[3]/div[1]/label");
    private final static String EXPECTED_PHONE_FORM_NAME = "Phone";
    private final static String EXPECTED_PHONE_FIELD_PLACEHOLDER_TEXT = "e.g. 1-222-333-4444";
    private final static By CATEGORY = By.xpath("//*[@id=\"single_poi_query\"]/div[3]/div[2]");
    private final static By CATEGORY_NAME = By.xpath("//*[@id=\"single_poi_query\"]/div[3]/div[2]/label");
    private final static String EXPECTED_CATEGORY_NAME = "Category";
    private final static String EXPECTED_DROPDOWN_MENU_BTN_NAME = "All";
    private final static By DROPDOWN_MENU_BTN = By.xpath("//*[@id=\"single_poi_query\"]/div[3]/div[2]/div/button");
    private final static By CARET_SYMBOL_BTN = By.xpath("//*[@id=\"single_poi_query\"]/div[3]/div[2]/div/button/b");
    private final static By LOCATION_FORM = By.xpath("//*[@id=\"single_poi_query\"]/div[4]/div[1]/div");
    private final static By LOCATION_FORM_NAME = By.xpath("//*[@id=\"single_poi_query\"]/div[4]/div[1]/div/label");
    private final static By LOCATION_NAME_FIELD = By.id("location_name");
    private final static String EXPECTED_LOCATION_FORM_NAME = "On the following location";
    private final static String EXPECTED_LOCATION_FIELD_PLACEHOLDER_TEXT = "e.g. W Illinois St, Chicago";
    private final static By COORDINATES_FORM = By.xpath("//*[@id=\"single_poi_query\"]/div[4]/div[2]/div");
    private final static By COORDINATES_FORM_NAME = By.xpath("//*[@id=\"single_poi_query\"]/div[4]/div[2]/div/div[1]/label");
    private final static String EXPECTED_COORDINATES_FORM_NAME = "Place Coordinates:";
    private final static By LATITUDE_FIELD = By.id("city_lat");
    private final static By LONGITUDE_FIELD = By.id("city_lng");
    private final static String EXPECTED_LATITUDE_FIELD_PLACEHOLDER_TEXT = "Latitude";
    private final static String EXPECTED_LONGITUDE_FIELD_PLACEHOLDER_TEXT = "Longitude";
    private final static By RECOMMENDATION_LOCATION_DROPDOWN = By.xpath("//*[@id=\"single_poi_query\"]/div[4]/div[1]/div/ul/li");
    private final static By MAP = By.xpath("//*[@id=\"single_poi_query\"]/div[4]/div[3]");
    private final static By CONFIRMATION_DIALOG = By.id("divConfirmDialog");
    private final static By CONFIRM_BTN = By.xpath("/html/body/div[10]/div[3]");
    private final static By CREATE_BTN = By.xpath("//*[@id=\"single_poi_query\"]/button");
    private final static By PROGRESS_SCREEN = By.id("progress-screen");

    public SinglePlaceSearchReportFormPage(final WebDriver webDriver) {
        this.driver = webDriver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void validateSinglePlaceReportContent() {
        Assert.assertTrue(driver.findElement(SINGLE_PLACE_REPORT_FORM).isDisplayed());

        final String actualSinglePlaceHeader = driver.findElement(SINGLE_PLACE_REPORT_HEADER).getText().replace("place", "");
        Assert.assertEquals(actualSinglePlaceHeader, EXPECTED_SINGLE_PLACE_REPORT_HEADER, "Validate the header of the create page for Single Place Search report");

        Assert.assertTrue(driver.findElement(SINGLE_PLACE_REPORT_NAME_FIELD).isDisplayed());

        final String actualReportNamePlaceholderText = driver.findElement(SINGLE_PLACE_REPORT_NAME_FIELD).getAttribute("placeholder");
        Assert.assertEquals(actualReportNamePlaceholderText, EXPECTED_REPORT_NAME_PLACEHOLDER_TEXT, "Validate a placeholder text for a report name field");

        Assert.assertTrue(driver.findElement(PLACE_NAME_FIELD).isDisplayed());

        final String actualPlaceNamePlaceholderText = driver.findElement(PLACE_NAME_FIELD).getAttribute("placeholder");
        Assert.assertEquals(actualPlaceNamePlaceholderText, EXPECTED_PLACE_NAME_PLACEHOLDER_TEXT, "Validate a placeholder text for a place name field");

        Assert.assertTrue(driver.findElement(PHONE_FORM).isDisplayed());

        final String actualPhoneFormName = driver.findElement(PHONE_FORM_NAME).getText();
        Assert.assertEquals(actualPhoneFormName, EXPECTED_PHONE_FORM_NAME, "Validate the name of the phone field");

        Assert.assertTrue(driver.findElement(PHONE_FIELD).isDisplayed());

        final String actualPhoneFieldPlaceholderText = driver.findElement(PHONE_FIELD).getAttribute("placeholder");
        Assert.assertEquals(actualPhoneFieldPlaceholderText, EXPECTED_PHONE_FIELD_PLACEHOLDER_TEXT, "Validate the placeholder text for the phone field");

        Assert.assertTrue(driver.findElement(CATEGORY).isDisplayed());

        final String actualCategoryDropdownMenuName = driver.findElement(CATEGORY_NAME).getText();
        Assert.assertEquals(actualCategoryDropdownMenuName, EXPECTED_CATEGORY_NAME, "Validate the name of category field");

        Assert.assertTrue(driver.findElement(DROPDOWN_MENU_BTN).isDisplayed());

        final String actualDropdownMenuBtnName = driver.findElement(DROPDOWN_MENU_BTN).getAttribute("title");
        Assert.assertEquals(actualDropdownMenuBtnName, EXPECTED_DROPDOWN_MENU_BTN_NAME);

        Assert.assertTrue(driver.findElement(CARET_SYMBOL_BTN).isDisplayed());

        Assert.assertTrue(driver.findElement(LOCATION_FORM).isDisplayed());

        Assert.assertTrue(driver.findElement(LOCATION_FORM_NAME).isDisplayed());

        final String actualLocationFormName = driver.findElement(LOCATION_FORM_NAME).getText();
        Assert.assertEquals(actualLocationFormName, EXPECTED_LOCATION_FORM_NAME, "Validate the name of the Location form field");

        Assert.assertTrue(driver.findElement(LOCATION_NAME_FIELD).isDisplayed());

        final String actualLocationFieldPlaceholderText = driver.findElement(LOCATION_NAME_FIELD).getAttribute("placeholder");
        Assert.assertEquals(actualLocationFieldPlaceholderText, EXPECTED_LOCATION_FIELD_PLACEHOLDER_TEXT, "Validate the placeholder text for the location field");

        Assert.assertTrue(driver.findElement(COORDINATES_FORM).isDisplayed());

        Assert.assertTrue(driver.findElement(COORDINATES_FORM_NAME).isDisplayed());

        final String actualCoordinateFormName = driver.findElement(COORDINATES_FORM_NAME).getText();
        Assert.assertEquals(actualCoordinateFormName, EXPECTED_COORDINATES_FORM_NAME);

        Assert.assertTrue(driver.findElement(LATITUDE_FIELD).isDisplayed());

        Assert.assertTrue(driver.findElement(LONGITUDE_FIELD).isDisplayed());

        final String actualLatitudeFieldPlaceholderText = driver.findElement(LATITUDE_FIELD).getAttribute("placeholder");

        Assert.assertEquals(actualLatitudeFieldPlaceholderText, EXPECTED_LATITUDE_FIELD_PLACEHOLDER_TEXT, "Validate the placeholder text for the Latitude filed");

        final String actualLongitudeFieldPlaceholderText = driver.findElement(LONGITUDE_FIELD).getAttribute("placeholder");

        Assert.assertEquals(actualLongitudeFieldPlaceholderText, EXPECTED_LONGITUDE_FIELD_PLACEHOLDER_TEXT, "Validate the placeholder text for the Longitude field");

        Assert.assertTrue(driver.findElement(MAP).isDisplayed());
    }

    public void enterSinglePlaceSearchReportData () {

        driver.findElement(SINGLE_PLACE_REPORT_NAME_FIELD).sendKeys(faker.company().name());

        driver.findElement(PLACE_NAME_FIELD).sendKeys(faker.company().name());

        driver.findElement(PHONE_FIELD).sendKeys(faker.phoneNumber().phoneNumber().trim());

        driver.findElement(LOCATION_NAME_FIELD).sendKeys("New Hampshire");

        wait.until(ExpectedConditions.elementToBeClickable(RECOMMENDATION_LOCATION_DROPDOWN));

        Assert.assertTrue(driver.findElement(RECOMMENDATION_LOCATION_DROPDOWN).isDisplayed());

        driver.findElement(RECOMMENDATION_LOCATION_DROPDOWN).click();

        Assert.assertTrue(driver.findElement(CONFIRMATION_DIALOG).isDisplayed());

        Assert.assertTrue(driver.findElement(CONFIRM_BTN).isDisplayed());

        driver.findElement(CONFIRM_BTN).click();
    }

    public void createReport () {
        Assert.assertTrue(driver.findElement(CREATE_BTN).isDisplayed());

        driver.findElement(CREATE_BTN).click();

        Assert.assertTrue(driver.findElement(PROGRESS_SCREEN).isDisplayed());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Report is still loading or not created");
        }
    }
}
