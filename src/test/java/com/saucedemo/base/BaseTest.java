package com.saucedemo.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/**
 * Base class for all tests, containing WebDriver setup and teardown logic.
 */
public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;

    /**
     * Sets up the WebDriver before each test method.
     * This method initializes the ChromeDriver using WebDriverManager,
     * maximizes the browser window, and sets implicit waits.
     */
    @BeforeMethod
    public void setUp() {
        logger.info("Setting up WebDriver...");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Crucial for CI/CD or certain environments
        // options.addArguments("--headless=new"); // Uncomment to run in headless mode
        // options.addArguments("--disable-dev-shm-usage"); // Sometimes needed for Linux environments
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        logger.info("WebDriver setup complete.");
    }

    /**
     * Tears down the WebDriver after each test method.
     * This method quits the driver, closing all associated windows.
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            logger.info("Tearing down WebDriver...");
            driver.quit();
            logger.info("WebDriver torn down.");
        }
    }
}
