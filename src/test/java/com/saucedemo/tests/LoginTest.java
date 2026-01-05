package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Feature("Authentication")
public class LoginTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @DataProvider(name = "credentials")
    public Object[][] credentialsData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", true},
                {"locked_out_user", "secret_sauce", false},
                {"problem_user", "secret_sauce", true},
                {"performance_glitch_user", "secret_sauce", true},
                {"invalid_user", "invalid_password", false}
        };
    }

    @Test(dataProvider = "credentials")
    @Description("Test login functionality with various credentials.")
    public void loginTest(String username, String password, boolean expected) {
        logger.info("Starting login test for user: {}", username);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        if (expected) {
            ProductsPage productsPage = new ProductsPage(driver);
            Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Login should be successful and products page should be displayed.");
            logger.info("Login successful for user: {}", username);
        } else {
            Assert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface:"), "Login should fail and an error message should be displayed.");
            logger.info("Login failed as expected for user: {}", username);
        }
    }
}
