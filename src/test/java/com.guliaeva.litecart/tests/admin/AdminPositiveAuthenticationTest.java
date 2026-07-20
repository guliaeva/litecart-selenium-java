package com.guliaeva.litecart.tests.admin;

import com.guliaeva.litecart.config.Config;
import com.guliaeva.litecart.core.BaseTest;
import com.guliaeva.litecart.pages.admin.AdminDashboard;
import com.guliaeva.litecart.pages.admin.AdminLoginPage;
import com.guliaeva.litecart.pages.admin.TopBar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class AdminPositiveAuthenticationTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(AdminPositiveAuthenticationTest.class);

    @Test
    void adminCanLoginWithValidCredentials()    {
        AdminLoginPage loginPage = new AdminLoginPage(driver);

        loginPage.open();

        AdminDashboard dashboard = loginPage.loginAs(Config.getAdminUsername(),Config.getAdminPassword());

        assertTrue(dashboard.isSuccessfulAlertDisplayed(),
                "Successful alert should be displayed after successful login");

        assertTrue(dashboard.isDashboardDisplayed(),
                "Admin dashboard should be displayed after successful login");
    }


    @Test
    void adminCanLiginWithValidPasswordAndUsernameInUppercase(){
        AdminLoginPage loginPage = new AdminLoginPage(driver);

        loginPage.open();

        AdminDashboard dashboard = loginPage.loginAs(Config.getAdminUsername().toUpperCase(),Config.getAdminPassword());

        assertTrue(dashboard.isSuccessfulAlertDisplayed(),
                "Successful alert should be displayed after successful login");

        assertTrue(dashboard.isDashboardDisplayed(),
                "Admin dashboard should be displayed after successful login");
    }





}