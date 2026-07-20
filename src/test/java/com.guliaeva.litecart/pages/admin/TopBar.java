package com.guliaeva.litecart.pages.admin;

import com.guliaeva.litecart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopBar extends BasePage {

    private static final By SIGN_OUT =
            By.xpath("//ul[@id='top-bar']//a[@title='Sign Out']");

    public TopBar(WebDriver driver) {
        super(driver);
    }

    public AdminLoginPage signOut() {
        click(SIGN_OUT);
        return new AdminLoginPage(driver);
    }
}
