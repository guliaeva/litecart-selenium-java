package com.guliaeva.litecart.pages.admin;

import com.guliaeva.litecart.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminLoginPage {

    private static final Logger logger = LoggerFactory.getLogger(AdminLoginPage.class);

    private static final By USERNAME_INPUT = By.name("username");
    private static final By PASSWORD_INPUT = By.name("password");
    private static final By LOGIN_BUTTON = By.cssSelector("button[name='login']");

    private final WebDriver driver;

    public AdminLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public AdminLoginPage open() {
        logger.info("Opening admin login page");
        driver.get(Config.getAdminUrl());
        return this;
    }

    public void loginAs(String username, String password) {
        logger.info("Logging in as user: {}", username);

        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }
}