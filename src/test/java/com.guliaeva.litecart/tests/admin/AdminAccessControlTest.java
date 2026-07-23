package com.guliaeva.litecart.tests.admin;

import com.guliaeva.litecart.config.Config;
import com.guliaeva.litecart.core.BaseTest;
import com.guliaeva.litecart.pages.admin.AdminLoginPage;
import com.guliaeva.litecart.pages.admin.TopBar;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AccessProtectedAdminPanel extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(AccessProtectedAdminPanel.class);
    private static final String INTERNAL_ADDRESS = "/?app=currencies&doc=currencies";

    @Test
    void userCanNotAccessProtectedAdminPanelWithoutAuthentication(){

        AdminLoginPage loginPage = new AdminLoginPage(driver);
        driver.get(Config.getAdminUrl()+INTERNAL_ADDRESS);
        assertTrue(loginPage.isOpened());
        String curUrl = driver.getCurrentUrl();
        String expUrl = getExpectedUrl(INTERNAL_ADDRESS);
        assertEquals(expUrl, curUrl,
                "Unauthenticated user should be redirected to login page with encoded redirect_url");
    }

    private static String getExpectedUrl(String internalUrl){
        return Config.getAdminUrl() +
                "/login.php?redirect_url=" +
                URLEncoder.encode("/admin"+INTERNAL_ADDRESS, StandardCharsets.UTF_8);
    }

    @Test
    void userShoudBeRedirectedToRequestedPageAfterLogin(){
        AdminLoginPage loginPage = new AdminLoginPage(driver);
        String requestedUrl = Config.getAdminUrl()+INTERNAL_ADDRESS;
        driver.get(requestedUrl);
        assertTrue(loginPage.isOpened());
        loginPage.submitLoginForm(Config.getAdminUsername(),Config.getAdminPassword());
        String curUrl = driver.getCurrentUrl();
        assertEquals(requestedUrl, curUrl,
                "User should be redirected to originaly requested page after successful authentication");
        new TopBar(driver).signOut();
    }





}