package com.guliaeva.litecart.tests.admin;

import com.guliaeva.litecart.config.Config;
import com.guliaeva.litecart.core.BaseTest;
import com.guliaeva.litecart.pages.admin.AdminLoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AdminLoginTest extends BaseTest {

    @Test
    void adminCanLoginWithValidCredentials() {
        AdminLoginPage loginPage = new AdminLoginPage(driver);

        loginPage.open();
        loginPage.loginAs(Config.getAdminUsername(), Config.getAdminPassword());

        assertTrue(driver.getCurrentUrl().contains("/admin"),
                "Admin should stay in admin area after successful login");
    }
}