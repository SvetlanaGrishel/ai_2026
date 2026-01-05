package com.saucedemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object for the Products page.
 */
public class ProductsPage {

    private static final Logger logger = LoggerFactory.getLogger(ProductsPage.class);
    private final WebDriver driver;

    // Locators
    private final By productSortContainer = By.className("product_sort_container");
    private final By shoppingCartLink = By.className("shopping_cart_link");
    private final By productTitle = By.className("title");

    /**
     * Constructor for the ProductsPage.
     * @param driver The WebDriver instance.
     */
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Checks if the Products page is displayed.
     * @return true if the title is "Products", false otherwise.
     */
    @Step("Verify Products page is displayed")
    public boolean isProductsPageDisplayed() {
        boolean isDisplayed = driver.findElement(productTitle).isDisplayed();
        logger.info("Products page is displayed: {}", isDisplayed);
        return isDisplayed;
    }

    /**
     * Sorts products by a given visible text.
     * @param sortBy The visible text to sort by (e.g., "Name (A to Z)").
     */
    @Step("Sort products by: {sortBy}")
    public void sortProducts(String sortBy) {
        logger.info("Sorting products by: {}", sortBy);
        Select sortDropdown = new Select(driver.findElement(productSortContainer));
        sortDropdown.selectByVisibleText(sortBy);
    }

    /**
     * Adds a product to the cart by its name.
     * @param productName The name of the product to add.
     */
    @Step("Add product to cart: {productName}")
    public void addProductToCart(String productName) {
        logger.info("Adding product '{}' to cart", productName);
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", productName);
        driver.findElement(By.xpath(xpath)).click();
    }

    /**
     * Clicks the shopping cart link to navigate to the cart page.
     */
    @Step("Go to shopping cart")
    public void goToShoppingCart() {
        logger.info("Navigating to shopping cart");
        driver.findElement(shoppingCartLink).click();
    }
}
