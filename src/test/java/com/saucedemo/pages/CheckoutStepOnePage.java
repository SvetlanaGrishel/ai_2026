package com.saucedemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Page Object for the first step of the checkout process.
 */
public class CheckoutStepOnePage {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutStepOnePage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");

    /**
     * Constructor for the CheckoutStepOnePage.
     * @param driver The WebDriver instance.
     */
    public CheckoutStepOnePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Fills the checkout information form.
     * @param firstName The first name.
     * @param lastName The last name.
     * @param postalCode The postal code.
     */
    @Step("Fill checkout information: {firstName}, {lastName}, {postalCode}")
    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        logger.info("Filling checkout information: {} {} {}", firstName, lastName, postalCode);
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeField)).sendKeys(postalCode);
    }

    /**
     * Clicks the continue button to proceed to the next step.
     */
    @Step("Click continue button")
    public void clickContinue() {
        logger.info("Clicking continue button");
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }
}
