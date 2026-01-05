package com.saucedemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object for the Sauce Demo Login Page.
 */
public class LoginPage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private final WebDriver driver;

    // Locators
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    /**
     * Constructor for the LoginPage.
     * @param driver The WebDriver instance.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Enters the username into the username field.
     * @param username The username to enter.
     */
    @Step("Enter username: {username}")
    public void enterUsername(String username) {
        logger.info("Entering username: {}", username);
        driver.findElement(usernameField).sendKeys(username);
    }

    /**
     * Enters the password into the password field.
     * @param password The password to enter.
     */
    @Step("Enter password: {password}")
    public void enterPassword(String password) {
        logger.info("Entering password: {}", password);
        driver.findElement(passwordField).sendKeys(password);
    }

    /**
     * Clicks the login button.
     */
    @Step("Click login button")
    public void clickLoginButton() {
        logger.info("Clicking login button");
        driver.findElement(loginButton).click();
    }

    /**
     * Performs a login action.
     * @param username The username.
     * @param password The password.
     */
    @Step("Login with username: {username} and password: {password}")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * Gets the error message text.
     * @return The error message.
     */
    @Step("Get error message")
    public String getErrorMessage() {
        String error = driver.findElement(errorMessage).getText();
        logger.info("Error message is: {}", error);
        return error;
    }
}
