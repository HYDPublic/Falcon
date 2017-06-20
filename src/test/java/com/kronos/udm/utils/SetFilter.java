package com.kronos.udm.utils;

import java.util.List;

import com.kronos.udm.objrepo.CustomerDashboardPage;
import com.kronos.udm.objrepo.SubFilterMenu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SetFilter {
	
	public static void onTenantNm(WebDriver driver, String tenantNm) throws InterruptedException {
	 WebElement filterTable = driver.findElement(By.xpath(CustomerDashboardPage.TENANT_TABLE));
	    List<WebElement> filterList = filterTable.findElements(By.tagName(ApplicationHtmlConstants.INPUT));
	    filterList.get(0).sendKeys(tenantNm);
	    Thread.sleep(2000);
	}

    public static void onDeviceNm(WebDriver driver, String deviceNm) throws InterruptedException {
        WebElement onDeviceNm = driver.findElement(By.xpath(SubFilterMenu.DIV_ID_CONFIG_TABLE_DIV_CLASS_UI_GRID_HEADER_CELL_ROW_AND_ROLE_ROW));
        List<WebElement> filterList = onDeviceNm.findElements(By.tagName(ApplicationHtmlConstants.INPUT));
        filterList.get(0).sendKeys(deviceNm);
        Thread.sleep(2000);
    }

    public static void onDeviceNm_OnDeviceDashborad(WebDriver driver, String deviceNm) throws InterruptedException {

        WebElement onDeviceNm = driver.findElement(By.xpath(SubFilterMenu.DIV_ID_DEVICE_DASHBOARD_GRID_DIV_CONTAINS_UI_GRID_FILTER));
        List<WebElement> filterList = onDeviceNm.findElements(By.tagName(ApplicationHtmlConstants.INPUT));
        filterList.get(0).sendKeys(deviceNm);
        Thread.sleep(2000);
    }

    public static void onProfileNm(WebDriver driver, String profileNm) throws InterruptedException {
        WebElement onProfileNm = driver.findElement(By.xpath(SubFilterMenu.DIV_ID_CONFIG_TABLE_DIV_CLASS_UI_GRID_HEADER_CELL_ROW_AND_ROLE_ROW));
        List<WebElement> filteredList = onProfileNm.findElements(By.tagName(ApplicationHtmlConstants.INPUT));
        Thread.sleep(1000);
        filteredList.get(1).sendKeys(profileNm);
        Thread.sleep(1000);
    }

    public static void onProfileNm_WithProfileView(WebDriver driver, String profileNm) throws InterruptedException {
        WebElement onProfileNm_WithViewAsProfile = driver.findElement(By.xpath(SubFilterMenu.DIV_ID_PROFILE_CONFIG_TABLE_DIV_UI_GRID_FILTER));
        List<WebElement> filteredList = onProfileNm_WithViewAsProfile.findElements(By.tagName(ApplicationHtmlConstants.INPUT));
        filteredList.get(0).sendKeys(profileNm);
        Thread.sleep(2000);
    }

    public static void onCommSettingNm(WebDriver driver, String commSettingNm) throws InterruptedException {
      WebElement commSetting = driver.findElement(By.xpath(SubFilterMenu.DEFAULT_COMM_SETTING_FILTER_LOADER));
      List<WebElement> commFilterList = commSetting.findElements(By.tagName(ApplicationHtmlConstants.INPUT));
      commFilterList.get(0).sendKeys(commSettingNm);
      Thread.sleep(2000);
    }

    public static void onAppConfigNm(WebDriver driver, String appConfigNm) throws InterruptedException {
        WebElement appConfig = driver.findElement(By.xpath(SubFilterMenu.DEFAULT_APP_CONFIG_FILTER_LOADER_ON_PROFILE_PAGE));
        List<WebElement> appConfigNmList = appConfig.findElements(By.tagName(ApplicationHtmlConstants.INPUT));
        appConfigNmList.get(1).sendKeys(appConfigNm);
        Thread.sleep(1500);
    }
    
    public static void onDefaultTransactionNm(WebDriver driver, String transTemplateNm) throws InterruptedException {
    	WebElement defaultTrans = driver.findElement(By.xpath(SubFilterMenu.DEFAULT_TRANSACTION_TEMPLATE_FILTER_LOADER));
        List<WebElement> defaultTransNmList = defaultTrans.findElements(By.tagName(ApplicationHtmlConstants.INPUT));
        CommonUtility.highLightElement(driver, defaultTransNmList.get(2));
        defaultTransNmList.get(2).sendKeys(transTemplateNm);
        Thread.sleep(1000);
    }
}
