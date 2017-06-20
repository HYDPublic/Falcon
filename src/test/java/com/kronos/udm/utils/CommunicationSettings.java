package com.kronos.udm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.kronos.udm.objrepo.CommunicationSettingsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentTest;

public class CommunicationSettings {

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

    public static void clickCommunicationSettingBtn(WebDriver driver) {
        WebElement commSetting = driver.findElement(By.xpath(CommunicationSettingsPage.ADDCOMMONSETTINGS));
        List<WebElement> iterateList = commSetting.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        CommonUtility.highLightElement(driver, iterateList.get(2));
        iterateList.get(2).click();
    }

    public static void createCommunicationSettings(WebDriver driver, String commTemplateNm, String primaryServer,
                                                   String primaryServerInstanceId, String port, ExtentTest extentTest)
            throws Exception {
        clickCommunicationSettingBtn(driver);
        Thread.sleep(2000);
        driver.findElement(By.xpath(CommunicationSettingsPage.NAME)).clear();
        driver.findElement(By.xpath(CommunicationSettingsPage.NAME)).sendKeys(commTemplateNm);
        Thread.sleep(1000);
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(CommunicationSettingsPage.RADIO_BTNHTTP)));
        driver.findElement(By.xpath(CommunicationSettingsPage.RADIO_BTNHTTP)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(CommunicationSettingsPage.PRIMARYSERVER)).clear();
        driver.findElement(By.xpath(CommunicationSettingsPage.PRIMARYSERVER)).sendKeys(primaryServer);
        driver.findElement(By.xpath(CommunicationSettingsPage.PRIMARYSERVERINSTANCEID)).clear();
        driver.findElement(By.xpath(CommunicationSettingsPage.PRIMARYSERVERINSTANCEID)).sendKeys(primaryServerInstanceId);
        driver.findElement(By.xpath(CommunicationSettingsPage.PORT)).clear();
        driver.findElement(By.xpath(CommunicationSettingsPage.PORT)).sendKeys(port);
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(CommunicationSettingsPage.SAVE_BTN)));
        driver.findElement(By.xpath(CommunicationSettingsPage.SAVE_BTN)).click();
        Thread.sleep(1500);
        try {
            driver.findElement(By.xpath(CommunicationSettingsPage.OK_BTN)).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(CommunicationSettingsPage.CANCEL_BTN)).click();
        } catch (Exception error) {
            System.out.println("APP CONFIG TEMPLATE WITH THE SAME NAME ALREDY EXISTS !!");
            throw error;
        }
    }
}
