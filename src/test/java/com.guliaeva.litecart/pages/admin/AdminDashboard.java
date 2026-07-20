package com.guliaeva.litecart.pages.admin;

import com.guliaeva.litecart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminDashboard extends BasePage {

    private static final By SUCCESS_ALERT = By.cssSelector(".alert.alert-success");

    private static final String WIDGET_LOCATOR_TEMPLATE =
            "//*[normalize-space()='%s']/ancestor::div[contains(@class,'card') and contains(@class,'card-default')][1]";

    private static final By MONTHLY_SALES_WIDGET = widgetCardByName("Monthly Sales");
    private static final By DAILY_SALES_WIDGET = widgetCardByName("Daily Sales");
    private static final By STATISTICS_WIDGET = widgetCardByName("Statistics");
    private static final By ORDERS_WIDGET = widgetCardByName("Orders");

    public AdminDashboard(WebDriver driver) {
        super(driver);
    }

    private static By widgetCardByName(String widgetName) {
        return By.xpath(WIDGET_LOCATOR_TEMPLATE.formatted(widgetName));
    }

    public boolean isSuccessfulAlertDisplayed() {
        return isDisplayed(SUCCESS_ALERT);
    }

    public boolean isDashboardDisplayed() {
        return isDisplayed(MONTHLY_SALES_WIDGET)
                && isDisplayed(DAILY_SALES_WIDGET)
                && isDisplayed(STATISTICS_WIDGET)
                && isDisplayed(ORDERS_WIDGET);
    }
}