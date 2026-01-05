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
 * Page Object for the checkout complete page.
 */
public class CheckoutCompletePage {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutCompletePage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By completeHeader = By.className("complete-header");

    /**
     * Constructor for the CheckoutCompletePage.
     * @param driver The WebDriver instance.
     */
    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Using 30 seconds as used previously
    }

    /**
     * Gets the confirmation message text.
     * @return The confirmation message.
     */
    @Step("Get confirmation message")
    public String getConfirmationMessage() {
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
        logger.info("Confirmation message: {}", message);
        return message;
    }
}
