package com.guliaeva.litecart.tests.admin;

import com.guliaeva.litecart.config.Config;
import com.guliaeva.litecart.core.BaseTest;
import com.guliaeva.litecart.pages.admin.AdminLoginPage;
import com.guliaeva.litecart.pages.admin.TopBar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class AdminNegativeAuthenticationTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(AdminNegativeAuthenticationTest.class);
    public static final String WRONG_PASSWORD = "wr0ngP@ssword";
    public static final String WRONG_LOGIN = "not_existing_username";

    void userFailToLogin(String login, String password, String expectedErrorMessage){
        AdminLoginPage loginPage = new AdminLoginPage(driver);
        loginPage.open();
        loginPage.submitLoginForm(login, password);
        assertTrue(loginPage.isErrorAlertDisplayed(),"Error alert should be displayed");
        assertTrue(loginPage.getErrorMessage().contains(expectedErrorMessage));
    }



    @Test
    void userCanNotLoginWithValidLoginAndInvalidPassword(){
        userFailToLogin(
                Config.getAdminUsername(),
                WRONG_PASSWORD,
                AdminLoginPage.attemptMessage(2));
    }

    @Test
    void userCanNotLoginWithInvalidLoginAndValidPassword(){
        userFailToLogin(
                WRONG_LOGIN,
                Config.getAdminPassword(),
                AdminLoginPage.wrongUsernameMessage());
    }

    @Test
    void userCanNotLoginWithValidLoginAndValidPasswordInUppercase(){
        userFailToLogin(
                Config.getAdminUsername(),
                Config.getAdminPassword().toUpperCase(),
                AdminLoginPage.attemptMessage(2));
    }

    //TODO Login with username entered using an incorrect keyboard layout and valid password
    //TODO Login with valid username and password entered using an incorrect keyboard layout


    // TODO: Replace UI cleanup with DB reset when database access is added.
    @AfterEach
    void successfulLogin(){
        logger.info("Cleaning up");
        AdminLoginPage loginPage = new AdminLoginPage(driver);
        loginPage.clearLoginField();
        loginPage.loginAs(Config.getAdminUsername(),Config.getAdminPassword());
        new TopBar(driver)
                .signOut()
                .clearLoginField();
    }
}