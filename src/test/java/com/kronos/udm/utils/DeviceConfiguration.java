package com.kronos.udm.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.kronos.udm.objrepo.ApplicationConfigurationPage;
import com.kronos.udm.objrepo.ApplyNewProfile;
import com.kronos.udm.objrepo.AutoRegenerationConfigurationDialog;
import com.kronos.udm.objrepo.CreateNewProfile;
import com.kronos.udm.objrepo.CustomerDashboardPage;
import com.kronos.udm.objrepo.DefaultTransactionPage;
import com.kronos.udm.objrepo.DeviceConfigurationPage;
import com.kronos.udm.objrepo.ErrorDialog;
import com.kronos.udm.objrepo.SubFilterMenu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DeviceConfiguration {

    public static List<String> addProfile(WebDriver driver, String profileNm, String noOfProfiles, ExtentTest extentTest) throws Exception {
        List<String> newProfileList = new ArrayList<String>();

        for (int i = 1; i <= Integer.parseInt(noOfProfiles); i++) {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.findElement(By.xpath(CustomerDashboardPage.NEW_BTN)).click();
            Thread.sleep(2000);
            CommonUtility.highLightElement(driver, driver.findElement(By.xpath(CustomerDashboardPage.PROFILE)));
            driver.findElement(By.xpath(CustomerDashboardPage.PROFILE)).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(CreateNewProfile.PROFILE_NAME)).sendKeys(profileNm + i);
            String pNm = profileNm + i;
            newProfileList.add(pNm);
            // Select Value from Drop Down
            Select dropdown = new Select(driver.findElement(By.id("deviceType")));
            dropdown.selectByVisibleText("InTouch");
            driver.findElement(By.xpath(CreateNewProfile.NEXT_BTN)).click();
            try {
                // After adding profile navigate back to Device Configuration page
                driver.findElement(By.xpath(ApplicationConfigurationPage.CANCEL_BTN)).click();
                Thread.sleep(1200);
            } catch (Exception error) {
                Thread.sleep(500);
                System.out.println("PROFILE NAME  ["+profileNm+"]  ALREADY EXISTS");
                extentTest.log(LogStatus.FAIL, "PROFILE NAME  ["+profileNm+"]  ALREADY EXISTS");
                extentTest.log(LogStatus.FAIL, error);
                driver.findElement(By.xpath(ErrorDialog.OK_BTN)).click();
                driver.findElement(By.xpath(CreateNewProfile.CANCEL_BTN)).click();
                throw error;
            }
        }
        return newProfileList;
    }
    
    public static void openAutoRegistration(WebDriver driver) throws Exception {
    	CommonUtility.highLightElement(driver, driver.findElement(By.xpath(SubFilterMenu.ACTION_BTN)));
        driver.findElement(By.xpath(SubFilterMenu.ACTION_BTN)).click();
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(SubFilterMenu.OPTION_AUTO_REG_CONFIG)));
        driver.findElement(By.xpath(SubFilterMenu.OPTION_AUTO_REG_CONFIG)).click();
        Thread.sleep(2000);
    }
    
    public static void saveAutoRegistration(WebDriver driver, String pwd, String confrmPwd, String profileForKronosInTouch, String timezone, ExtentTest extentTest) throws Exception {
    	boolean isChecked = (driver.findElement(By.xpath(AutoRegenerationConfigurationDialog.CHECKBOX_AUTO_REGISTRATION))).isSelected();
    	if (!isChecked) {
	    	driver.findElement(By.xpath(AutoRegenerationConfigurationDialog.CHECKBOX_AUTO_REGISTRATION)).click();
	        driver.findElement(By.xpath(AutoRegenerationConfigurationDialog.DEVICE_PWD)).sendKeys(pwd);
	        Thread.sleep(500);
	        driver.findElement(By.xpath(AutoRegenerationConfigurationDialog.CONFIRM_PWD)).sendKeys(confrmPwd);
	        driver.findElement(By.xpath(AutoRegenerationConfigurationDialog.CHECKBOX_ALLOW_AUTO_CONFIG)).click();
	        Thread.sleep(500);
	        driver.findElement(By.xpath(AutoRegenerationConfigurationDialog.COMBO_KINTOUCH)).click();
	        AutoRegenerationConfigurationDialog.selectProfile(driver, profileForKronosInTouch);
	    	Thread.sleep(1000);
	    	driver.findElement(By.xpath(AutoRegenerationConfigurationDialog.COMBO_TIMEZONE)).click();
	    	try {
	    	Select selectTimeZone = new Select(driver.findElement(By.xpath(AutoRegenerationConfigurationDialog.COMBO_TIMEZONE)));
	    	selectTimeZone.selectByVisibleText(timezone);
	    	Thread.sleep(1000);
	    	}
	    	catch (Exception exception){
	    		extentTest.log(LogStatus.FAIL, "UNABLE TO SELECT TIME ZONE");
	    		extentTest.log(LogStatus.FAIL, exception);
	    	}
	    	CommonUtility.highLightElement(driver, driver.findElement(By.xpath(AutoRegenerationConfigurationDialog.BTN_SAVE)));
	    	driver.findElement(By.xpath(AutoRegenerationConfigurationDialog.BTN_SAVE)).click();
	    	Thread.sleep(1000);
	    	String exractSuccessMessage = driver.findElement(By.xpath(DeviceConfigurationPage.ALERT_MESSAGE)).getText();
	    	String validateSuccessMessage = "Success: Auto Registration Configuration has been saved successfully.";
	    	try {
	    		Assert.assertEquals(exractSuccessMessage, validateSuccessMessage);
	    		extentTest.log(LogStatus.PASS, "AUTO REGISTRATION SUCCESSFULLY DONE");
	    	}
	    	catch (AssertionError error){
	    		extentTest.log(LogStatus.FAIL, "AUTO REGISTRATION FAILED");
	    		extentTest.log(LogStatus.FAIL, error);
	    	}
	    }
    	else{
    		CommonUtility.highLightElement(driver, driver.findElement(By.xpath(AutoRegenerationConfigurationDialog.BTN_CANCEL)));
	    	driver.findElement(By.xpath(AutoRegenerationConfigurationDialog.BTN_CANCEL)).click();
	    	extentTest.log(LogStatus.FAIL, "Registration Aborted !! System Is Alredy Configured");
    	}
    }

	public static void searchProfile(WebDriver driver, String profileNm) throws InterruptedException {
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(SubFilterMenu.VIEW_BTN)));
        driver.findElement(By.xpath(SubFilterMenu.VIEW_BTN)).click();
        Thread.sleep(500);
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(SubFilterMenu.VIEW_PROFILE)));
        driver.findElement(By.xpath(SubFilterMenu.VIEW_PROFILE)).click();
        Thread.sleep(500);
        driver.findElement(By.xpath(SubFilterMenu.FILTER_BTN)).click();
        Thread.sleep(500);
        driver.findElement(By.xpath(DeviceConfigurationPage.SEARCH_PROFILE)).sendKeys(profileNm);
        Thread.sleep(2000);
    }

    public static boolean selectSearchedProfile(WebDriver driver, String profileNm) throws InterruptedException {
        WebElement profile = driver.findElement(By.xpath(DeviceConfigurationPage.DIV_ID_PROFILE_CONFIG_TABLE_DIV_CLASS_UI_GRID_CANVAS));
        List<WebElement> profileList = profile.findElements(By.tagName(ApplicationHtmlConstants.SPAN));
        for (int count = 0; count < profileList.size(); count++) {
            String extractProfileNm = profileList.get(count).getAttribute(ApplicationHtmlConstants.TITLE);
            if (extractProfileNm.equalsIgnoreCase(profileNm)) {
                try {
                    CommonUtility.highLightElement(driver, profileList.get(count));
                    profileList.get(count).click();
                    return true;
                } catch (Exception e) {
                    System.out.println("SEARCHED PROFILE NOT FOUND");
                }
            }
        }
        return false;
    }

    public static void verifyProfile(WebDriver driver, List<String> profileNm, ExtentTest extentTest) throws InterruptedException {
        String profileToVerify = profileNm.get(0);
        searchProfile(driver, profileToVerify);
        WebElement profile = driver.findElement(By.xpath(DeviceConfigurationPage.DIV_ID_PROFILE_CONFIG_TABLE_DIV_CLASS_UI_GRID_CANVAS));
        List<WebElement> profileList = profile.findElements(By.tagName(ApplicationHtmlConstants.SPAN));
        boolean ifElementExist = false;
        for (int count = 0; count < profileList.size(); count++) {
            String extractProfileNm = profileList.get(count).getAttribute(ApplicationHtmlConstants.TITLE);
            if (extractProfileNm.equalsIgnoreCase(profileToVerify)) {
                ifElementExist = true;
                CommonUtility.highLightElement(driver, profileList.get(count));
                extentTest.log(LogStatus.PASS, "PROFILE NAME  ["+profileToVerify+"]  SUCCESSFULLY ADDED & VERIFIED");
            }
        }
        if (!ifElementExist) {
            extentTest.log(LogStatus.FAIL, "PROFILE NAME  ["+profileToVerify +"]  NOT FOUND");
        }
    }

    public static void searchAppConfigTemplate(WebDriver driver, String appConfigNm) throws Exception {
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(SubFilterMenu.VIEW_BTN)));
        driver.findElement(By.xpath(SubFilterMenu.VIEW_BTN)).click();
        Thread.sleep(500);
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(SubFilterMenu.VIEW_PROFILE)));
        driver.findElement(By.xpath(SubFilterMenu.VIEW_PROFILE)).click();
        Thread.sleep(500);
        driver.findElement(By.xpath(SubFilterMenu.FILTER_BTN)).click();
        Thread.sleep(500);
        SetFilter.onAppConfigNm(driver, appConfigNm);
        Thread.sleep(1500);
    }
    
    public static boolean selectSearchedAppConfigTemplate(WebDriver driver, String appConfigNm) throws InterruptedException {
    	WebElement appConfigTemplate = driver.findElement(By.xpath(DeviceConfigurationPage.DIV_ID_PROFILE_CONFIG_TABLE_DIV_CONTAINS_ID_GRID_CONTAINER_AND_CONTAINERID_BODY_DIV_ROLE_AND_UIGRIDROW));
    	List<WebElement> appConfigTemplateList = appConfigTemplate.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        for (int count = 0; count < appConfigTemplateList.size(); count++) {
            String extractAppConfigNm = appConfigTemplateList.get(count).getAttribute(ApplicationHtmlConstants.TITLE);
            if (extractAppConfigNm.equalsIgnoreCase(appConfigNm)) {
                try {
                    CommonUtility.highLightElement(driver, appConfigTemplateList.get(count));
                    appConfigTemplateList.get(count).click();
                    Thread.sleep(500);
                    return true;
                } catch (Exception e) {
                    System.out.println("SEARCHED APP CONFIG TEMPLATE  ["+appConfigNm+"]  NOT FOUND");
                }
            }
        }
        return false;
    }
    
    public static void selectSearchedDevice(WebDriver driver, String deviceNm, boolean click) throws InterruptedException {
        WebElement deviceTitle = driver.findElement(By.xpath(DeviceConfigurationPage.DIV_CLASS_UI_GRID_CANVAS));
        List<WebElement> iterateTitles = deviceTitle.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        boolean ifElementPresent = false;
        for (int count = 0; count < iterateTitles.size(); count++) {
            String extractDeviceNm = iterateTitles.get(count).getAttribute(ApplicationHtmlConstants.TITLE);
            if (extractDeviceNm.equals(deviceNm)) {
                ifElementPresent = true;
                CommonUtility.highLightElement(driver, iterateTitles.get(count));
                iterateTitles.get(count - 4).click();
                if (click == true) {
                    iterateTitles.get(count).click();
                }
                break;
            }
        }
        if (!ifElementPresent) {
            System.out.println("SEARCHED DEVICE NOT FOUND");
        }
    }

    public static void chooseProfile(WebDriver driver, String profileNm) throws InterruptedException {
        searchProfile(driver, profileNm);
        selectSearchedProfile(driver, profileNm);
    }

    public static void chooseAProfile(WebDriver driver, List<String> newProfileNm, int profileIndexToPick) throws InterruptedException {
        String profileNm = newProfileNm.get(profileIndexToPick);
        searchProfile(driver, profileNm);
        selectSearchedProfile(driver, profileNm);
    }

    public static void chooseAppConfigTemplateNm(WebDriver driver, String appConfigNm) throws Exception {
        searchAppConfigTemplate (driver, appConfigNm);
        selectSearchedAppConfigTemplate(driver, appConfigNm);
    }
    
    public static void selectDeviceAndClickAction(WebDriver driver, int numberOfDeviceToCheck, String deviceNm, String deviceNm2) throws InterruptedException {
        Thread.sleep(1000);
        if (numberOfDeviceToCheck == 1) {
            selectSearchedDevice(driver, deviceNm, false);
        } else if (numberOfDeviceToCheck == 2) {
            selectSearchedDevice(driver, deviceNm, false);
            selectSearchedDevice(driver, deviceNm2, false);
        }
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(SubFilterMenu.ACTION_BTN)));
        driver.findElement(By.xpath(SubFilterMenu.ACTION_BTN)).click();
        Thread.sleep(1500);
    }

    public static void selectProfile(WebDriver driver, String profileNm) throws InterruptedException {
        driver.findElement(By.xpath(SubFilterMenu.FILTER_BTN)).click();
        Thread.sleep(1000);
        SetFilter.onProfileNm(driver, profileNm);
        // Iterate through Profile titles and click on selected one
        WebElement profileTitle = driver.findElement(By.xpath(DeviceConfigurationPage.DIV_CLASS_UI_GRID_CANVAS));
        List<WebElement> iterateTitles = profileTitle.findElements(By.tagName(ApplicationHtmlConstants.SPAN));
        boolean ifElementPresent = false;
        for (int count = 0; count < iterateTitles.size(); count++) {
            String extractProfileNm = iterateTitles.get(count).getAttribute(ApplicationHtmlConstants.TITLE);
            if (extractProfileNm.equals(profileNm)) {
                ifElementPresent = true;
                CommonUtility.highLightElement(driver, iterateTitles.get(count));
                iterateTitles.get(count).click();
                Thread.sleep(1000);
                break;
            }
        }
        if (!ifElementPresent) {
            System.out.println("[" + profileNm + "]  NOT FOUND");
        }
    }

    public static boolean verifyProfileCreation(WebDriver driver, String profileNm, String deviceNm, ExtentTest extentTest) throws InterruptedException {
        driver.findElement(By.xpath(SubFilterMenu.FILTER_BTN)).click();
        Thread.sleep(500);
        SetFilter.onProfileNm(driver, profileNm);
        try {
            searchProfileNmInconfigTable(driver, profileNm, false);
            extentTest.log(LogStatus.PASS, "SUCCESSFULLY ADDED & VERFIED PROFILE  [" + profileNm + "] FOR DEVICE  [" + deviceNm + "]");
            return true;
        } catch (AssertionError error) {
            extentTest.log(LogStatus.FAIL, "UNABLE TO VALIDATE PROFILE  [" + profileNm + "]");
            extentTest.log(LogStatus.FAIL, error);
        }
        return false;
    }

    public static boolean searchProfileNmInconfigTable(WebDriver driver, String profileNm, boolean performClick) throws InterruptedException {
        WebElement profileTitle = driver.findElement(By.xpath(DeviceConfigurationPage.DIV_ID_CONFIG_TABLE_DIV_CLASS_UI_GRID_CANVAS));
        List<WebElement> iterateProfileTitles = profileTitle.findElements(By.tagName(ApplicationHtmlConstants.SPAN));
        for (int count = 0; count < iterateProfileTitles.size(); count++) {
            String extractedTitle = iterateProfileTitles.get(count).getAttribute(ApplicationHtmlConstants.TITLE);
            //Locate the required profile Name and return true
            if (extractedTitle.equals(profileNm) && performClick == false) {
                CommonUtility.highLightElement(driver, iterateProfileTitles.get(count));
                return true;
            }
            //Locate the required profile Name and perform Click
            else if (extractedTitle.equals(profileNm) && performClick == true) {
                CommonUtility.highLightElement(driver, iterateProfileTitles.get(count));
                iterateProfileTitles.get(count).click();
                return true;
            }
        }
        return false;
    }

    public static void applyProfileToSelectedDevice(WebDriver driver, String profileNm, ExtentTest extentTest) throws InterruptedException {
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(DeviceConfigurationPage.ACTION_APPLY_PROFILE)));
        driver.findElement(By.xpath(DeviceConfigurationPage.ACTION_APPLY_PROFILE)).click();
        Thread.sleep(2000);
        // Select Profile from Drop Down
        try {
            Select dropdown = new Select(driver.findElement(By.xpath(ApplyNewProfile.SELECT_PROFILE)));
            dropdown.selectByVisibleText(profileNm);
            Thread.sleep(1500);
        } catch (Exception e) {
            System.out.println("SEARCHED PROFILE  ["+profileNm+"]  NOT FOUND");
        }
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ApplyNewProfile.APPLY_BTN)));
        driver.findElement(By.xpath(ApplyNewProfile.APPLY_BTN)).click();
        Thread.sleep(2000);
        try {
            String alertMessage = driver.findElement(By.xpath(DeviceConfigurationPage.ALERT_MESSAGE)).getText();
            Assert.assertEquals(alertMessage, "Success: Profile applied successfully.");
        } catch (AssertionError error) {
            System.out.println("UNABLE TO VERIFY SUCCESS MESSAGE");
            extentTest.log(LogStatus.FAIL, error);
        }
        Thread.sleep(1000);
    }

    public static void verifyAppConfigNm(WebDriver driver, String appConfigNm, ExtentTest extentTest) throws InterruptedException {
        driver.findElement(By.xpath(SubFilterMenu.FILTER_BTN)).click();
        Thread.sleep(1000);
        SetFilter.onAppConfigNm(driver, appConfigNm);
        Thread.sleep(1000);
        WebElement appConfigTitles = driver.findElement(By.xpath(DeviceConfigurationPage.ALL_APP_CONGIG_TITLES));
        List<WebElement> iterateAppConfigTitles = appConfigTitles.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        boolean ifElementPresent = false;
        for (int count = 0; count < iterateAppConfigTitles.size(); count++) {
            String extractAppConfigName = iterateAppConfigTitles.get(count).getAttribute(ApplicationHtmlConstants.TITLE);

            if (extractAppConfigName.equalsIgnoreCase(appConfigNm)) {
                ifElementPresent = true;
                CommonUtility.highLightElement(driver, iterateAppConfigTitles.get(count));
                //iterateTitles.get(count).click();
                extentTest.log(LogStatus.PASS, "VALIDATED APP CONFIG TITLE  ["+extractAppConfigName+"]");
                break;
            }
        }
        if (!ifElementPresent) {
            extentTest.log(LogStatus.FAIL, "APP CONFIG TITLE  ["+appConfigNm+"]  NOT FOUND");
        }
    }
    
    public static void createDefaultTransactionTemplate(WebDriver driver, String transactionTemplateNm, ExtentTest extentTest)throws Exception {
    	clickDefaulttransactionTempalteBtn(driver);
    	Thread.sleep(2000);
        driver.findElement(By.xpath(DefaultTransactionPage.NAME)).clear();
        driver.findElement(By.xpath(DefaultTransactionPage.NAME)).sendKeys(transactionTemplateNm);
        driver.findElement(By.xpath(DefaultTransactionPage.SAVE_BTN)).click();
        Thread.sleep(1000);
    	try {
            driver.findElement(By.xpath(DefaultTransactionPage.OK_BTN)).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(DefaultTransactionPage.CANCEL_BTN)).click();
        } catch (Exception error) {
            extentTest.log(LogStatus.FAIL, "DEFAULT TRANSACTION TEMPLATE NAME ["+transactionTemplateNm+"] ALREDY EXISTS");
            extentTest.log(LogStatus.FAIL, error);
            throw error;
        }
    }
    
    public static void clickDefaulttransactionTempalteBtn(WebDriver driver) throws Exception {
    	WebElement transactionTempalteHolder = driver.findElement(By.xpath(DeviceConfigurationPage.DEFAULT_TRANSACTION_TEMPLATE_HOLDER));
    	List<WebElement> traverseList = transactionTempalteHolder.findElements(By.tagName("div"));
    	CommonUtility.highLightElement(driver, traverseList.get(6));
    	traverseList.get(6).click();
    }
    
    public static void verifyDefaultTransactionTemplate(WebDriver driver, String defaultTransTemplateNm, ExtentTest extentTest)throws Exception {
    	driver.findElement(By.xpath(DeviceConfigurationPage.FILTER_BTN)).click();
    	Thread.sleep(2000);
    	SetFilter.onDefaultTransactionNm(driver, defaultTransTemplateNm);
    	// Below Code validates presence of DEFAULT TRANSACTION TEMPLATE 
    	WebElement defaultTransTemplateHolder = driver.findElement(By.xpath(DeviceConfigurationPage.DEFAULT_TRANSACTION_TEMPLATE_HOLDER));
        List<WebElement> iterateDefaultTransTemplateList = defaultTransTemplateHolder.findElements(By.tagName(ApplicationHtmlConstants.DIV));
         boolean ifElementPresent = false;
         for (int count = 0; count < iterateDefaultTransTemplateList.size(); count++) {
             String extractTransTemplateNm = iterateDefaultTransTemplateList.get(count).getAttribute(ApplicationHtmlConstants.TITLE);
             if (extractTransTemplateNm.equalsIgnoreCase(defaultTransTemplateNm)) {
                 ifElementPresent = true;
                 CommonUtility.highLightElement(driver, iterateDefaultTransTemplateList.get(count));
                 extentTest.log(LogStatus.PASS, "DEFAULT TRANSACTION TEMPLATE  ["+defaultTransTemplateNm+"]  VERIFIED SUCCESSFULLY");
                 break;
             }
         }
         if (!ifElementPresent) {
             extentTest.log(LogStatus.FAIL, "DEFAULT TRANSACTION TEMPLATE  ["+defaultTransTemplateNm+"]  NOT FOUND");
             
         }
    }
}
