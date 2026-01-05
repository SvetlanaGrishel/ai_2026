package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for the checkout process.
 */
@Feature("Checkout")
public class CheckoutTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutTest.class);
    private ProductsPage productsPage;

    /**
     * Logs in and initializes page objects before each test.
     */
    @BeforeMethod
    public void setupAndLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        productsPage = new ProductsPage(driver);
    }

    /**
     * Tests the end-to-end checkout process for a single product.
     */
    @Test
    @Description("Test the end-to-end checkout process for a single product.")
    public void checkoutEndToEndTest() {
        logger.info("Starting end-to-end checkout test.");
        productsPage.addProductToCart("Sauce Labs Fleece Jacket");
        productsPage.goToShoppingCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();

        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepOnePage.fillCheckoutInformation("John", "Doe", "12345");
        checkoutStepOnePage.clickContinue();

        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        Assert.assertTrue(checkoutStepTwoPage.getTotalPrice().contains("53.99"), "Total price should be correct.");
        checkoutStepTwoPage.clickFinish();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertEquals(checkoutCompletePage.getConfirmationMessage().toLowerCase(), "thank you for your order!", "Checkout should complete successfully.");
        logger.info("End-to-end checkout test completed successfully.");
    }
}
