package com.kronos.udm.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.kronos.udm.objrepo.ApplicationConfigurationPage;
import com.kronos.udm.objrepo.ChooseNewTemplateAndProfileDialog;
import com.kronos.udm.objrepo.ChooseNewTemplateDialog;
import com.kronos.udm.objrepo.SelectATemplate;
import com.kronos.udm.objrepo.SubFilterMenu;
import com.kronos.udm.objrepo.SuccessDialog;
import com.kronos.udm.objrepo.WarningDialog;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ApplicationConfiguration {
    public static void verifyProfileAllocationToDevices(WebDriver driver, String profileNm, String deviceNm1, String deviceNm2, ExtentTest extentTest) throws Exception {
        String[] deviceArrayName = { deviceNm1, deviceNm2 };
        driver.findElement(By.xpath(SubFilterMenu.FILTER_BTN)).click();
        Thread.sleep(500);
        SetFilter.onProfileNm(driver, profileNm);
        Thread.sleep(1000);
        WebElement profileTitle = driver.findElement(By.xpath(ApplicationConfigurationPage.PROFILE_TITLE_HOLDER));
        List<WebElement> iterateTitles = profileTitle.findElements(By.tagName(ApplicationHtmlConstants.SPAN));
        // Iterate through Profile titles and click on required one
        for (int count = 0; count < iterateTitles.size(); count++) {
            String extractProfileNm = iterateTitles.get(count).getAttribute(ApplicationHtmlConstants.TITLE);
            if (extractProfileNm.equals(profileNm)) {
                CommonUtility.highLightElement(driver, iterateTitles.get(count));
                iterateTitles.get(count).click();
                Thread.sleep(1000);
				/*Iterate through available RADIO options 
                 and verify option "All devices and profiles using this template" is CHECKED */
                WebElement radioBtn = driver.findElement(By.xpath(ApplicationConfigurationPage.RADIO_APP_BTN_CONFIG));
                List<WebElement> iterateList = radioBtn.findElements(By.tagName(ApplicationHtmlConstants.DIV));
                for (int j = 0; j < iterateList.size(); j++) {
                    boolean radioBtnIfChecked = iterateList.get(j).findElement(By.tagName(ApplicationHtmlConstants.INPUT)).isSelected();
                    String radioBtnLabel = iterateList.get(j).findElement(By.tagName(ApplicationHtmlConstants.SPAN)).getText();
                    if (radioBtnLabel.equals("All devices and profiles using this template")) {
                        try {
                            Assert.assertTrue(radioBtnIfChecked == true);
                            extentTest.log(LogStatus.PASS, "FOR DEVICE  ["+deviceArrayName[j]+"] VERIFIED SELECTION " + " '" + "All devices and profiles using this template" + "'");
                            Thread.sleep(1500);
                            driver.findElement(By.xpath(ApplicationConfigurationPage.CANCEL_BTN)).click();
                        } catch (AssertionError error) {
                            extentTest.log(LogStatus.FAIL, "UNABLE TO VERIFY RADIO SELECTION " + " '" + " All devices and profiles using this template" + "'" + " ON APPLICATION CONFIGURATION Pg");
                            extentTest.log(LogStatus.FAIL, error);
                            //throw error;
                        }
                        profileTitle = driver.findElement(By.xpath(ApplicationConfigurationPage.DIV_CLASS_UI_GRID_CANVAS1));
                        iterateTitles = profileTitle.findElements(By.tagName(ApplicationHtmlConstants.SPAN));
                    }
                }
            }
        }
    }

    public static void verifyProfileAllocationToMultipleProfiles(WebDriver driver, String appCofigTemplateNm, ExtentTest extentTest) throws Exception {
		/*Iterate through available RADIO options and verify 
		 option "All profiles using this template" is CHECKED */
        Thread.sleep(2000);
        WebElement radioBtn = driver.findElement(By.xpath(ApplicationConfigurationPage.RADIO_APP_BTN_CONFIG));
        List<WebElement> iterateList = radioBtn.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        boolean ifElementPresent = false;
        for (int j = 0; j < iterateList.size(); j++) {
            boolean radioBtnIfChecked = iterateList.get(j).findElement(By.tagName(ApplicationHtmlConstants.INPUT)).isSelected();
            String radioBtnLabel = iterateList.get(j).findElement(By.tagName(ApplicationHtmlConstants.SPAN)).getText();
            if (radioBtnLabel.equals("All profiles using this template")) {
                ifElementPresent = true;
                try {
                    Assert.assertTrue(radioBtnIfChecked == true);
                    extentTest.log(LogStatus.PASS, "VERIFIED RADIO SELECTION : 'All profiles using this template' ON APP. CONFIG. PG");
                    Thread.sleep(1500);
                    driver.findElement(By.xpath(ApplicationConfigurationPage.CANCEL_BTN)).click();
                } catch (AssertionError error) {
                	driver.findElement(By.xpath(ApplicationConfigurationPage.CANCEL_BTN)).click();
                    extentTest.log(LogStatus.FAIL, "UNABLE TO VERIFY RADIO SELECTION : ' All profiles using this template' ON APP. CONFIG. PG");
                    extentTest.log(LogStatus.FAIL, error);
                    throw error;
                }
            }
        }
        if (!ifElementPresent) {
            extentTest.log(LogStatus.FAIL, "UNABLE TO LOCATE RADIO OPTION : ' All profiles using this template'");
        }
    }

    public static void addAppConfigName(WebDriver driver, String appConfigNm) throws Exception {
        Thread.sleep(1000);
        driver.findElement(By.xpath(ApplicationConfigurationPage.APP_CONFIG_NAME)).clear();
        driver.findElement(By.xpath(ApplicationConfigurationPage.APP_CONFIG_NAME)).sendKeys(appConfigNm);
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ApplicationConfigurationPage.SAVE_BTN)));
        driver.findElement(By.xpath(ApplicationConfigurationPage.SAVE_BTN)).click();
        Thread.sleep(2000);
        try {
            driver.findElement(By.xpath(ApplicationConfigurationPage.OK_BTN)).click();
            Thread.sleep(1000);
        } catch (Exception error) {
            System.out.println("APP CONFIG TEMPLATE NAME "+appConfigNm+" ALREADY EXISTS");
            throw error;
        }
        driver.findElement(By.xpath(ApplicationConfigurationPage.CANCEL_BTN)).click();
        Thread.sleep(1000);
    }

    public static void changeAssignmentToSingleDevice(WebDriver driver, String deviceNm) throws Exception {
        driver.findElement(By.xpath(ApplicationConfigurationPage.SINGLE_DEVICE_SELECTION.replace("&", deviceNm))).click();
        WebElement radioBtn = driver.findElement(By.xpath(ApplicationConfigurationPage.DIV_CONTAINS_CLASS_UDM_FORM_FLOAT_LEFT_MARGIN_LEFT_85PX_DIV_CLASS_UDM_FORM_ROW));
        List<WebElement> radioBtnOptions = radioBtn.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        for (int count = 0; count < radioBtnOptions.size(); count++) {
            WebElement radioInputType = radioBtnOptions.get(count).findElement(By.tagName(ApplicationHtmlConstants.INPUT));
            String radioBtnlabel = radioBtnOptions.get(count).findElement(By.tagName(ApplicationHtmlConstants.SPAN)).getText();
            if (radioBtnlabel.equals(deviceNm)) {
                try {
                    CommonUtility.highLightElement(driver, radioBtnOptions.get(count));
                    radioInputType.click();
                } catch (Exception error) {
                    System.out.println("UNABLE TO CLICK ON RADIO BUTTON SELECTION  [" + deviceNm + "]");
                    throw error;
                }
                break;
            }
        }
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ApplicationConfigurationPage.SAVE_BTN)));
        driver.findElement(By.xpath(ApplicationConfigurationPage.SAVE_BTN)).click();
        Thread.sleep(2000);
    }

    public static void verifyNewTemplateAndProfileDialogIsVissible(WebDriver driver, ExtentTest extentTest) {
        try {
            Assert.assertTrue(driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.PROFILENM)).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.TEMPLATENM)).isDisplayed());
            extentTest.log(LogStatus.PASS, "VALIDATED DIALOG LAUNCH : " + "'" + "Choose a new Template and Profile name" + "'");
        } catch (AssertionError error) {
            extentTest.log(LogStatus.FAIL, "UNABLE TO VALIDATE DIALOG LAUNCH : " + "'" + "Choose a new Template and Profile name" + "'");
            extentTest.log(LogStatus.FAIL, error);
        }
    }

    public static void verifyErrorMessage(WebDriver driver, ExtentTest extentTest) throws Exception {
        driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.TEMPLATENM)).clear();
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.APPLY_BTN)));
        driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.APPLY_BTN)).click();
        Thread.sleep(1000);
        String extractAlertforProfileNm = driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.ALERTMSGSPROFILE_NM)).getText();
        String extractAlertforTemplateNm = driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.ALERTMSGTEMPLATE_NM)).getText();
        try {
            Assert.assertEquals(extractAlertforProfileNm, "Please enter a Profile Name");
            Assert.assertEquals(extractAlertforTemplateNm, "Please enter a Template Name");
            driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.CANCEL_BTN)).click();
            Thread.sleep(1000);
            extentTest.log(LogStatus.PASS, "VALIDATED ALERT MESSAGE " + "'" + "Please enter a PROFILE Name" + "'");
            extentTest.log(LogStatus.PASS, "VALIDATED ALERT MESSAGE " + "'" + "Please enter a TEMPLATE Name" + "'");
        } catch (AssertionError error) {
            extentTest.log(LogStatus.FAIL, "UNABLE TO VERIFY ERROR MESSAGES");
            throw error;
        }
    }

    public static void addAppConfigTemplate(WebDriver driver, String appConfigTemplateNm) throws Exception {
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ApplicationConfigurationPage.APP_CONFIG_NAME)));
        driver.findElement(By.xpath(ApplicationConfigurationPage.APP_CONFIG_NAME)).sendKeys(appConfigTemplateNm);
        driver.findElement(By.xpath(ApplicationConfigurationPage.SAVE_BTN)).click();
        try {
            CommonUtility.highLightElement(driver, driver.findElement(By.xpath(SuccessDialog.OK_BTN)));
            driver.findElement(By.xpath(SuccessDialog.OK_BTN)).click();
            Thread.sleep(1000);
        } catch (Exception error) {
            System.out.println("APP CONFIG TEMPLATE  ["+appConfigTemplateNm+"] ALREDY EXISTS");
            throw error;
        }
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ApplicationConfigurationPage.CANCEL_BTN)));
        driver.findElement(By.xpath(ApplicationConfigurationPage.CANCEL_BTN)).click();
    }

    public static void selectExistingAppConfigTemplate(WebDriver driver, String appConfigTemplateNm) throws InterruptedException {
        Thread.sleep(1500);
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ApplicationConfigurationPage.SELECT_EXISTING_TEMPLATE)));
        driver.findElement(By.xpath(ApplicationConfigurationPage.SELECT_EXISTING_TEMPLATE)).click();
        Thread.sleep(4000);
        try {
            while (!searchTemplate(driver, appConfigTemplateNm)) {
                driver.findElement(By.xpath(SelectATemplate.VERTICAL_SCROLL)).click();
                Thread.sleep(3000);
                continue;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(SelectATemplate.SELECT)));
        driver.findElement(By.xpath(SelectATemplate.SELECT)).click();
        driver.findElement(By.xpath(ApplicationConfigurationPage.SAVE_BTN)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(SuccessDialog.OK_BTN)).click();
    }

    private static boolean searchTemplate(WebDriver driver, String appConfigTemplateNm) throws Exception {
        WebElement profiles = driver.findElement(By.xpath(SelectATemplate.PROFILE_TEMPLATES));
        List<WebElement> iterateProfileList = profiles.findElements(By.xpath(ApplicationConfigurationPage.DIV_CLASS_GRID_CELL));
        boolean ifElementExists = false;
        for (int i = 0; i < iterateProfileList.size(); i++) {
            String extractProfileNm = iterateProfileList.get(i).getText();
            if (extractProfileNm.equalsIgnoreCase(appConfigTemplateNm)) {
                ifElementExists = true;
                try {
                    WebElement radiobtns = iterateProfileList.get(i).findElement(By.tagName(ApplicationHtmlConstants.INPUT));
                    radiobtns.click();
                    break;
                } catch (Exception error) {
                    System.out.println("UNABLE TO SELECT RADIO BUTTON");
                    throw error;
                }
            }
        }
        if (!ifElementExists) {
            System.out.println("APP CONFIG TEMPLATE  ["+appConfigTemplateNm+"] NAME NOT FOUND");
        }
        return ifElementExists;
    }

    public static void addNewProfile(WebDriver driver, String templateNm, String profileNm, ExtentTest extentTest) throws Exception {
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.TEMPLATENM)));
        String extractTemplateNm = driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.TEMPLATENM)).getAttribute("value").toString();
        Assert.assertEquals(templateNm, extractTemplateNm);
        driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.PROFILENM)).sendKeys(profileNm);
        driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.APPLY_BTN)).click();
        Thread.sleep(1000);
        try {
            CommonUtility.highLightElement(driver, driver.findElement(By.xpath(WarningDialog.YES_BTN)));
            driver.findElement(By.xpath(WarningDialog.YES_BTN)).click();
        } catch (Exception error) {
        	extentTest.log(LogStatus.FAIL, "FAILED TO ADD PROFILE  ["+profileNm+"]" );
        	extentTest.log(LogStatus.FAIL, error);
            //throw error;
        }
    }

    public static void addNewProfileAndTemplate(WebDriver driver, String newTemplateNm, String newProfileNm) throws Exception {
        try {
            driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.TEMPLATENM)).clear();
            driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.TEMPLATENM)).sendKeys(newTemplateNm);
            driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.PROFILENM)).sendKeys(newProfileNm);
            CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.APPLY_BTN)));
            driver.findElement(By.xpath(ChooseNewTemplateAndProfileDialog.APPLY_BTN)).click();
            Thread.sleep(1000);
        } catch (Exception error) {
            System.out.println("!! UNABLE TO LOCATE 'AddNewProfileAndTemplate' DIALOG !! ");
            throw error;
        }
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(WarningDialog.YES_BTN)));
        driver.findElement(By.xpath(WarningDialog.YES_BTN)).click();
        Thread.sleep(1000);
    }

    public static void verifyAlertText(WebDriver driver, String appConfigNm, ExtentTest extentTest) throws Exception {
        Thread.sleep(1500);
        String alertText = "Error: A template with this type and name already exists: Type: Application Configuration, Name " + appConfigNm;
        try {
            String extractAlertText = driver.findElement(By.xpath(ApplicationConfigurationPage.WARNING_TEXT)).getText();
            Assert.assertEquals(extractAlertText, alertText);
            extentTest.log(LogStatus.PASS, "VALIDATED OCCURANCE OF ERROR: A template with this type and name already exists");
        } catch (AssertionError error) {
            extentTest.log(LogStatus.FAIL, "UNABLE TO VERIFY ERROR MESSAGE TEXT");
            extentTest.log(LogStatus.FAIL, error);
        }
    }

    public static void verifyProfileAndTemplateNm(WebDriver driver, String newTemplateNm, String newProfileNm, ExtentTest extentTest) throws Exception {
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ApplicationConfigurationPage.APP_CONFIG_NAME)));
        String extractTemplateNm = driver.findElement(By.xpath(ApplicationConfigurationPage.APP_CONFIG_NAME)).getAttribute("value");
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ApplicationConfigurationPage.PROFILE_NM)));
        String extractProfileNm = driver.findElement(By.xpath(ApplicationConfigurationPage.PROFILE_NM)).getText();
        try {
            Assert.assertEquals(extractTemplateNm, newTemplateNm);
            Assert.assertEquals(extractProfileNm, newProfileNm);
            driver.findElement(By.xpath(SuccessDialog.OK_BTN)).click();
            extentTest.log(LogStatus.PASS, "VALIDATED NEWLY ASSIGNED PROFILE Nm [" + extractProfileNm + "] and TEMPLATE Nm [" + extractTemplateNm + "]");
        } catch (AssertionError error) {
            extentTest.log(LogStatus.FAIL, "UNABLE TO VALIDATE PROFILE NAME AND/OR TEMPLATE NAME");
            extentTest.log(LogStatus.FAIL, error);
            //throw error;
        }
    }

    public static void changeProfileSelection(WebDriver driver, String profileNm) throws Exception {
        Thread.sleep(2000);
        WebElement radioBtn = driver.findElement(By.xpath(ApplicationConfigurationPage.RADIO_APP_BTN_CONFIG));
        List<WebElement> iterateList = radioBtn.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        for (int j = 0; j < iterateList.size(); j++) {
            WebElement radioBtnToSelect = iterateList.get(j).findElement(By.tagName(ApplicationHtmlConstants.INPUT));
            String radioBtnLabel = iterateList.get(j).findElement(By.tagName(ApplicationHtmlConstants.SPAN)).getText();
            if (radioBtnLabel.equals("All profiles using this template")) {
                radioBtnToSelect.click();
                Thread.sleep(500);
            }
        }
        try {
            List<WebElement> iterateList1 = radioBtn.findElements(By.tagName(ApplicationHtmlConstants.DIV));
            for (int k = 0; k < iterateList1.size(); k++) {
                WebElement radioBtnToSelect = iterateList1.get(k).findElement(By.tagName(ApplicationHtmlConstants.INPUT));
                String radioBtnLabel = iterateList1.get(k).findElement(By.tagName(ApplicationHtmlConstants.SPAN)).getText();
                if (radioBtnLabel.equals(profileNm)) {
                    radioBtnToSelect.click();
                    Thread.sleep(500);
                }
            }
            driver.findElement(By.xpath(ApplicationConfigurationPage.SAVE_BTN)).click();
            Thread.sleep(1000);
        } catch (Exception error) {
            throw error;
        }
    }

    public static void validateChooseNewTemplateDialog(WebDriver driver, String appConfigNm, ExtentTest extentTest) throws Exception {
        String extractAppConfigNm = driver.findElement(By.xpath(ChooseNewTemplateDialog.TEMPLATE_NM)).getAttribute("value");
        try {
            Assert.assertEquals(appConfigNm, extractAppConfigNm);
            extentTest.log(LogStatus.PASS, "VERIFIED " + "\"" + "ChooseNewTemplate" + "\"" + " DIALOG LAUNCH CARRYING THE APP.CONFIG Nm  [" + extractAppConfigNm + "] AS SEEN ON APP. CONFIG PG");
        } catch (AssertionError error) {
        	extentTest.log(LogStatus.FAIL, "EITHER THE CHOOSEN NEW TEMPLATE DIALOG IS NOT VISSIBLE OR THE APPCONFIG Nm  [" + extractAppConfigNm + "] DID NOT MATCH");
            extentTest.log(LogStatus.FAIL, error);
            //throw error;
        }
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ChooseNewTemplateDialog.APPLY_BTN)));
        driver.findElement(By.xpath(ChooseNewTemplateDialog.APPLY_BTN)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(WarningDialog.YES_BTN)).click();
    }
    
    public static void assignNewAppConfigTemplate(WebDriver driver, String appConfigNewNm, ExtentTest extentTest) throws Exception {
    	driver.findElement(By.xpath(ChooseNewTemplateDialog.TEMPLATE_NM)).clear();
    	driver.findElement(By.xpath(ChooseNewTemplateDialog.TEMPLATE_NM)).sendKeys(appConfigNewNm);
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ChooseNewTemplateDialog.APPLY_BTN)));
        driver.findElement(By.xpath(ChooseNewTemplateDialog.APPLY_BTN)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(WarningDialog.YES_BTN)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(SuccessDialog.OK_BTN)).click();
    }
    
    public static void verifyNewAppConfigTemplate(WebDriver driver, String appConfigNewNm, ExtentTest extentTest) throws Exception {
        Thread.sleep(500);
    	String extractAppConfigTemplateNm = driver.findElement(By.xpath(ApplicationConfigurationPage.APP_CONFIG_NAME)).getAttribute("value");
    	try {
    		Assert.assertEquals(appConfigNewNm, extractAppConfigTemplateNm);
    		extentTest.log(LogStatus.PASS, "SUCCESSFULLY ASSIGNED NEW APP CONFIG TEMPLATE ["+appConfigNewNm+"]" );
    	}
    	catch (AssertionError error){
    		extentTest.log(LogStatus.FAIL, "UNABLE TO VERIFY NEWLY ASSIGNED APP CONFIG TEMPLATE ["+appConfigNewNm+"]" );
    	}
    	Thread.sleep(1000);
    	driver.findElement(By.xpath(ApplicationConfigurationPage.CANCEL_BTN)).click();
    }
    
    public static void updateProfileData(WebDriver driver, String scheduleForEmp, ExtentTest extentTest) throws Exception {
    	driver.findElement(By.xpath(ApplicationConfigurationPage.EMP_NUM_SCHEDULE)).clear();
    	CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ApplicationConfigurationPage.EMP_NUM_SCHEDULE)));
    	driver.findElement(By.xpath(ApplicationConfigurationPage.EMP_NUM_SCHEDULE)).sendKeys(scheduleForEmp);
    	
    	// ******* DELETE THIS CODE ONES THE ISSUE IS FIXED ***********************************
    	WebElement radioBtn = driver.findElement(By.xpath(ApplicationConfigurationPage.RADIO_APP_BTN_CONFIG));
        List<WebElement> iterateList = radioBtn.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        for (int j = 0; j < iterateList.size(); j++) {
            WebElement radioBtnToSelect = iterateList.get(j).findElement(By.tagName(ApplicationHtmlConstants.INPUT));
            String radioBtnLabel = iterateList.get(j).findElement(By.tagName(ApplicationHtmlConstants.SPAN)).getText();
            if (radioBtnLabel.equals("All profiles using this template")) {
                radioBtnToSelect.click();
                Thread.sleep(500);
            }
        }
    	//************************************************************************************
    	
    	driver.findElement(By.xpath(ApplicationConfigurationPage.SAVE_BTN)).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath(SuccessDialog.OK_BTN)).click();
	}
    
    public static void verifyProfileSelection(WebDriver driver, ExtentTest extentTest ) throws Exception {
        Thread.sleep(2000);
        WebElement radioBtn = driver.findElement(By.xpath(ApplicationConfigurationPage.RADIO_APP_BTN_CONFIG));
        List<WebElement> iterateList = radioBtn.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        for (int j = 0; j < iterateList.size(); j++) {
            boolean radioBtnIfChecked = iterateList.get(j).findElement(By.tagName(ApplicationHtmlConstants.INPUT)).isSelected();
            String radioBtnLabel = iterateList.get(j).findElement(By.tagName(ApplicationHtmlConstants.SPAN)).getText();
            if (radioBtnLabel.equals("All profiles using this template")) {
            try {        
                Assert.assertTrue(radioBtnIfChecked == true);
            	extentTest.log(LogStatus.PASS, "SUCCESSFULLY VALIDATED RADIO OPTION [All profiles using this template]");
            }
            catch (AssertionError error) {
	    	 extentTest.log(LogStatus.FAIL, "UNABLE TO VALIDATE RADIO OPTION [All profiles using this template]");
	    	 extentTest.log(LogStatus.FAIL, error);
            }
          }
       }
    }
}
