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
 * Page Object for the second step of the checkout process (Overview).
 */
public class CheckoutStepTwoPage {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutStepTwoPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By finishButton = By.id("finish");
    private final By summaryTotalLabel = By.className("summary_total_label");

    /**
     * Constructor for the CheckoutStepTwoPage.
     * @param driver The WebDriver instance.
     */
    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    /**
     * Gets the total price from the summary.
     * @return The total price as a string.
     */
    @Step("Get total price from summary")
    public String getTotalPrice() {
        String total = wait.until(ExpectedConditions.visibilityOfElementLocated(summaryTotalLabel)).getText();
        logger.info("Total price from summary: {}", total);
        return total;
    }

    /**
     * Clicks the finish button to complete the order.
     */
    @Step("Click finish button")
    public void clickFinish() {
        logger.info("Clicking finish button");
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }
}
