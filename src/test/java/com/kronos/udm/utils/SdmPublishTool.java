package com.kronos.udm.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SdmPublishTool {

    public static final String BUTTON_CLASS_BTN_I_DROPDOWN_TOGGLE = "//button[@class='btn i dropdown-toggle']";
    public static final String UL_CLASS_SEARCH_OPTIONS_LI = "//ul[@class='search-options']/li";

    public static void selectSourceCustomer(WebDriver driver, String customerNm) throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(By.xpath(BUTTON_CLASS_BTN_I_DROPDOWN_TOGGLE)).click();
        WebElement customerList = driver.findElement(By.xpath(UL_CLASS_SEARCH_OPTIONS_LI));
    }
}
