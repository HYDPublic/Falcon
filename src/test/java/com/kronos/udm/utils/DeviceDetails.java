package com.kronos.udm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.kronos.udm.objrepo.DeviceConfigurationPage;
import com.kronos.udm.objrepo.SubFilterMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DeviceDetails {

    static Properties prop = new Properties();

    static {
        File objRepo = new File(AppConstants.OBJECTREPO_PROPERTY);
        FileInputStream readobjRepo = null;
        try {
            readobjRepo = new FileInputStream(objRepo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(readobjRepo);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void selectProfile(WebDriver driver, String profileNm) throws Exception {
        driver.findElement(By.xpath(SubFilterMenu.VIEW_BTN)).click();
        Thread.sleep(1000);
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(SubFilterMenu.VIEW_PROFILE)));
        driver.findElement(By.xpath(SubFilterMenu.VIEW_PROFILE)).click();
        driver.findElement(By.xpath(SubFilterMenu.FILTER_BTN)).click();
        Thread.sleep(1000);
        try {
            SetFilter.onProfileNm_WithProfileView(driver, profileNm);
            Thread.sleep(2000);
            driver.findElement(By.xpath(DeviceConfigurationPage.CHECK_PROFILE)).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            throw e;
        }
    }

    public static void verifyCommSettingTitle(WebDriver driver, String commSettingNm, ExtentTest extentTest, String profileNm) throws Exception {
        Thread.sleep(1500);
        driver.findElement(By.xpath(SubFilterMenu.VIEW_BTN)).click();
        Thread.sleep(1000);
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(SubFilterMenu.VIEW_PROFILE)));
        driver.findElement(By.xpath(SubFilterMenu.VIEW_PROFILE)).click();
        driver.findElement(By.xpath(SubFilterMenu.FILTER_BTN)).click();
        Thread.sleep(1000);
        SetFilter.onCommSettingNm(driver, commSettingNm);
        // Below code validates presence of Comm.setting on the grid
        WebElement commSettingsTitlesHolder = driver.findElement(By.xpath(DeviceConfigurationPage.COMM_SETTINGTITLE_HOLDER));
        List<WebElement> iterateCommSettingTitles = commSettingsTitlesHolder.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        boolean ifElementPresent = false;
        for (int count = 0; count < iterateCommSettingTitles.size(); count++) {
            String extractCommSettingName = iterateCommSettingTitles.get(count).getAttribute(ApplicationHtmlConstants.TITLE);
            if (extractCommSettingName.equalsIgnoreCase(commSettingNm)) {
                ifElementPresent = true;
                CommonUtility.highLightElement(driver, iterateCommSettingTitles.get(count));
                extentTest.log(LogStatus.PASS, "COMMUNICATION SETTING TEMPLATE ["+extractCommSettingName+"] ADDED & VERIFIED SUCCESSFULLY");
                break;
            }
        }
        if (!ifElementPresent) {
        	
            extentTest.log(LogStatus.FAIL, "COMMUNICATION SETTING TEMPLATE b["+commSettingNm+"] NOT FOUND");
        }
    }
}
