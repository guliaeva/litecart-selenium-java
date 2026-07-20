package com.guliaeva.litecart.core;

import com.guliaeva.litecart.config.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected WebDriver driver;

    /*
     * Current browser lifecycle strategy: one new browser session per test.
     *
     * This keeps tests isolated and prevents shared state between test cases
     * such as cookies, active sessions, or page state left by previous tests.
     *
     * TODO: Add configurable browser lifecycle strategy if execution time becomes a problem.
     */
    @BeforeEach
    void setUp() {
        logger.info("Starting browser");
        System.out.println("Creating driver for browser: " + Config.getBrowser());

        driver = DriverFactory.createDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            logger.info("Closing browser");
            driver.quit();
        }
    }
}