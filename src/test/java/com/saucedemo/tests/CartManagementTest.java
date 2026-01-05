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
 * Tests for cart management functionality.
 */
@Feature("Cart Management")
public class CartManagementTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CartManagementTest.class);
    private ProductsPage productsPage;
    private CartPage cartPage;

    /**
     * Logs in and initializes page objects before each test.
     */
    @BeforeMethod
    public void setupAndLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    /**
     * Tests removing a product from the cart.
     */
    @Test
    @Description("Test removing a product from the cart.")
    public void removeProductFromCartTest() {
        logger.info("Starting test to remove a product from cart.");
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");
        productsPage.goToShoppingCart();

        Assert.assertEquals(cartPage.getCartItemCount(), 2, "There should be two items in the cart initially.");

        cartPage.removeProductFromCart("Sauce Labs Backpack");
        logger.info("Removed 'Sauce Labs Backpack' from cart.");

        Assert.assertEquals(cartPage.getCartItemCount(), 1, "There should be one item remaining in the cart.");
        Assert.assertNull(cartPage.findCartItemByName("Sauce Labs Backpack"), "Sauce Labs Backpack should be removed from the cart.");
        Assert.assertNotNull(cartPage.findCartItemByName("Sauce Labs Bike Light"), "Sauce Labs Bike Light should still be in the cart.");
        logger.info("Product removed from cart successfully.");

        // Cleanup
        cartPage.removeProductFromCart("Sauce Labs Bike Light");
        Assert.assertEquals(cartPage.getCartItemCount(), 0, "Cart should be empty after cleanup.");
    }
}
