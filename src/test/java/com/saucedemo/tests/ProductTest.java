package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests for product functionality.
 */
@Feature("Products")
public class ProductTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ProductTest.class);
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
     * Tests the product sorting functionality.
     */
    @Test
    @Description("Test product sorting functionality.")
    public void sortProductsTest() {
        logger.info("Starting product sorting test.");
        productsPage.sortProducts("Name (Z to A)");
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page should still be displayed after sorting.");
        logger.info("Product sorting test finished.");
    }
}
