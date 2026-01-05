package com.saucedemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

/**
 * Page Object for the Cart page.
 */
public class CartPage {

    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By cartItems = By.className("cart_item");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    /**
     * Constructor for the CartPage.
     * @param driver The WebDriver instance.
     */
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Gets the count of items in the cart.
     * @return The number of items in the cart.
     */
    @Step("Get number of items in cart")
    public int getCartItemCount() {
        // Wait for the cart items to be present (or not present)
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartItems));
        int count = driver.findElements(cartItems).size();
        logger.info("Number of items in cart: {}", count);
        return count;
    }

    /**
     * Removes a product from the cart by its name.
     * @param productName The name of the product to remove.
     */
    @Step("Remove product from cart: {productName}")
    public void removeProductFromCart(String productName) {
        logger.info("Removing product '{}' from cart", productName);
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='cart_item']//button[text()='Remove']", productName);
        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        removeButton.click();
        // Wait for the item to disappear from the DOM
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }

    /**
     * Proceeds to the checkout process.
     */
    @Step("Click checkout button")
    public void proceedToCheckout() {
        logger.info("Clicking checkout button");
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
    
    /**
     * Finds a cart item element by product name.
     * @param productName The name of the product.
     * @return The WebElement for the cart item, or null if not found.
     */
    @Step("Find cart item by name: {productName}")
    public WebElement findCartItemByName(String productName) {
        logger.info("Finding cart item by name: {}", productName);
        String xpath = String.format("//div[@class='inventory_item_name' and text()='%s']/ancestor::div[@class='cart_item']", productName);
        List<WebElement> items = driver.findElements(By.xpath(xpath));
        return items.isEmpty() ? null : items.get(0);
    }
}
