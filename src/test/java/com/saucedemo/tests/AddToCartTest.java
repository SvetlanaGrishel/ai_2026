package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for adding products to the cart.
 */
@Feature("Cart")
public class AddToCartTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(AddToCartTest.class);
    private ProductsPage productsPage;

    /**
     * Logs in before each test in this class.
     */
    @BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        productsPage = new ProductsPage(driver);
    }

    /**
     * Tests adding a single product to the cart.
     */
    @Test
    @Description("Test adding a single product to the cart.")
    public void addSingleProductToCartTest() {
        logger.info("Starting test to add a single product to cart.");
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.goToShoppingCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "There should be one item in the cart.");
        Assert.assertNotNull(cartPage.findCartItemByName("Sauce Labs Backpack"), "Sauce Labs Backpack should be in the cart.");
        logger.info("Single product added to cart successfully.");

        // Cleanup
        cartPage.removeProductFromCart("Sauce Labs Backpack");
        Assert.assertEquals(cartPage.getCartItemCount(), 0, "Cart should be empty after cleanup.");
    }
}
