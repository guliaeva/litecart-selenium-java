package com.guliaeva.litecart.pages.admin;

import com.guliaeva.litecart.config.Config;
import com.guliaeva.litecart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLoginPage extends BasePage {

    private static final String ATTEMPTS_MESSAGE =
            "You have %d login attempts left until your account is temporarily blocked";
    private static final String WRONG_USERNAME_MESSAGE =
            "The user could not be found in our database";

    private static final By LOGIN_BOX = By.cssSelector("#box-login");
    private static final By USERNAME_INPUT = By.name("username");
    private static final By PASSWORD_INPUT = By.name("password");
    private static final By REMEMBER_ME_CHECKBOX = By.cssSelector("input[type='checkbox']");
    private static final By LOGIN_BUTTON = By.cssSelector("button[name='login']");
    private static final By ERROR_ALERT = By.cssSelector(".alert.alert-danger");

    public AdminLoginPage(WebDriver driver) {
        super(driver);
    }

    public AdminLoginPage open() {
        driver.get(Config.getAdminUrl());
        return this;
    }

    public boolean isOpened() {
        return isDisplayed(LOGIN_BOX);
    }

    public AdminLoginPage submitLoginForm(String username, String password) {
        type(USERNAME_INPUT, username);
        type(PASSWORD_INPUT, password);
        click(LOGIN_BUTTON);
        return this;
    }

    public AdminDashboard loginAs(String username, String password) {
        submitLoginForm(username, password);
        return new AdminDashboard(driver);
    }

    public boolean isErrorAlertDisplayed() {
        return isDisplayed(ERROR_ALERT);
    }

    public String getErrorMessage() {
        return getText(ERROR_ALERT);
    }

    public void clearLoginField() {
        find(USERNAME_INPUT).clear();
    }

    public static String attemptMessage(int numberOfAttemptsLeft) {
        return ATTEMPTS_MESSAGE.formatted(numberOfAttemptsLeft);
    }

    public static String wrongUsernameMessage() {
        return WRONG_USERNAME_MESSAGE;
    }
}