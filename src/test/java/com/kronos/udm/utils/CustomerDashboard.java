package com.kronos.udm.utils;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.kronos.udm.objrepo.AddDevice;
import com.kronos.udm.objrepo.AssignGroup;
import com.kronos.udm.objrepo.AssignTag;
import com.kronos.udm.objrepo.CreateNewGroup;
import com.kronos.udm.objrepo.CreateNewTag;
import com.kronos.udm.objrepo.CustomerDashboardPage;
import com.kronos.udm.objrepo.CustomerManagementPage;
import com.kronos.udm.objrepo.DeviceConfigurationPage;
import com.kronos.udm.objrepo.ManageGroups;
import com.kronos.udm.objrepo.ManageTags;
import com.kronos.udm.objrepo.Sidebar;
import com.kronos.udm.objrepo.SubFilterMenu;
import com.kronos.udm.objrepo.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CustomerDashboard {

    /*static Properties prop = new Properties();
    static {
        File propertiesFile = new File(ApplicationConstants.SETUP_PROPERTY);
        FileInputStream readpropertiesFile = null;
        try {
            readpropertiesFile = new FileInputStream(propertiesFile);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        File objRepo = new File(ApplicationConstants.OBJECTREPO_PROPERTY);
        FileInputStream readobjRepo = null;
        try {
            readobjRepo = new FileInputStream(objRepo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Properties prop = new Properties();
        try {
            prop.load(readpropertiesFile);
            prop.load(readobjRepo);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }*/
    static Properties prop = UtilityFunctions.ReadPropertyFile(AppConstants.OBJECTREPO_PROPERTY);

    public static void SDMLogin(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath(prop.getProperty("login.username"))).sendKeys(prop.getProperty("UserId"));
        driver.findElement(By.xpath(prop.getProperty("login.password"))).sendKeys(prop.getProperty("Password"));
        driver.findElement(By.xpath(prop.getProperty("login.submitBtn"))).click();
    }

    public static void addDevice(WebDriver driver, String deviceId, String deviceNm, String noOfDevicesToAdd, ExtentTest extentTest) throws Exception {
        int deviceCount = Integer.parseInt(noOfDevicesToAdd);
        for (int count = 0; count < deviceCount; count++) {
            driver.findElement(By.xpath(DeviceConfigurationPage.NEW_BTN)).click();
            CommonUtility.highLightElement(driver, driver.findElement(By.xpath(DeviceConfigurationPage.ADD_NEW_DEVICE)));
            try {
            driver.findElement(By.xpath(DeviceConfigurationPage.ADD_NEW_DEVICE)).click();
            }
             catch (Exception error) {
                extentTest.log(LogStatus.FAIL, "DEVICE NAME  ["+deviceNm+"]  ALREADY EXISTS");
                extentTest.log(LogStatus.FAIL, error);
                Thread.sleep(1000);
                driver.findElement(By.xpath(AddDevice.CANCEL_BTN)).click();
                //throw error;
            }
            if (count == 0) {
                driver.findElement(By.xpath(AddDevice.DEVICE_ID)).sendKeys(deviceId);
                driver.findElement(By.xpath(AddDevice.DEVICE_NM)).sendKeys(deviceNm);
            } else {
                int incrementedDeviceId = Integer.parseInt(deviceId) + count;
                driver.findElement(By.xpath(AddDevice.DEVICE_ID)).sendKeys(Integer.toString(incrementedDeviceId));
                driver.findElement(By.xpath(AddDevice.DEVICE_NM)).sendKeys(deviceNm + count);
            }
            org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(driver.findElement(By.xpath(AddDevice.COMMUNICATION_TYPE)));
            dropdown.selectByVisibleText("HostName");
            driver.findElement(By.xpath(AddDevice.IPADDRESSV6)).sendKeys("localhost");
            driver.findElement(By.xpath(AddDevice.TIMEZONE)).sendKeys("(GMT -05:00) Eastern Time (USA; Canada)");
            driver.findElement(By.xpath(AddDevice.NEXT_BTN)).click();
            driver.findElement(By.xpath(AddDevice.SEC_DEVICE_NM)).sendKeys(deviceNm);
            driver.findElement(By.xpath(AddDevice.TITLE)).sendKeys(deviceNm);
            driver.findElement(By.xpath(AddDevice.EMAIL)).sendKeys(deviceNm + "@kronos.com");
            CommonUtility.highLightElement(driver, driver.findElement(By.xpath(AddDevice.CREATE_BTN)));
            driver.findElement(By.xpath(AddDevice.CREATE_BTN)).click();
            Thread.sleep(1000);
        }
    }

    public static void addTag(WebDriver driver, String tagNm, ExtentTest extentTest) throws Exception {
        Thread.sleep(1000);
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(Sidebar.ADDTAG)));
        driver.findElement(By.xpath(Sidebar.ADDTAG)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(ManageTags.NEW_BTN)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(CreateNewTag.NAME)).sendKeys(tagNm);
        Thread.sleep(1000);
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(CreateNewTag.SAVE_BTN)));
        driver.findElement(By.xpath(CreateNewTag.SAVE_BTN)).click();
        Thread.sleep(1500);
        try {
            String successMessage = AppConstants.VERIFY_SUCCESSFULLY_ADDED;
            String validateOccuranceOfSuccessMsg = driver.findElement(By.xpath(ManageTags.VERIFY_MESSAGE)).getAttribute(ApplicationHtmlConstants.TITLE);
            Assert.assertEquals(validateOccuranceOfSuccessMsg, successMessage);
            CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ManageTags.CLOSE_BTN)));
            driver.findElement(By.xpath(ManageTags.CLOSE_BTN)).click();
        } catch (AssertionError error) {
            System.out.println("TAG  ["+tagNm +"] ALREADY EXISTS");
            extentTest.log(LogStatus.FAIL, "TAG NAME ["+tagNm+"] ALREADY EXISTS");
            extentTest.log(LogStatus.FAIL, error);
            driver.findElement(By.xpath(CreateNewTag.CANCEL_BTN)).click();
            Thread.sleep(500);
            driver.findElement(By.xpath(ManageTags.CLOSE_BTN)).click();
            throw error;
        }
    }

    //Search and Select Tag
    public static void searchAndselectTag(WebDriver driver, String tagNm, ExtentTest extentTest) throws InterruptedException {
        WebElement tag = driver.findElement(By.xpath(CustomerDashboardPage.DIV_CLASS_TAGS_UL));
        List<WebElement> iterateTag = tag.findElements(By.tagName(ApplicationHtmlConstants.SPAN));
        boolean ifElementPresent = false;
        for (int i = 0; i < iterateTag.size(); i++) {
            String extractTagNm = iterateTag.get(i).getAttribute(ApplicationHtmlConstants.TITLE);
            if (extractTagNm.equals(tagNm)) {
                ifElementPresent = true;
                Thread.sleep(500);
                CommonUtility.highLightElement(driver, iterateTag.get(i));
                iterateTag.get(i - 1).click();
                Thread.sleep(2000);
                extentTest.log(LogStatus.PASS, "FOUND TAG Nm ["+tagNm+"]");
                break;
            }
        }
        if (!ifElementPresent) {
            extentTest.log(LogStatus.FAIL, "TAG Nm  ["+tagNm+"]  NOT FOUND");
        }
    }

    //Search and Select Group
    public static void searchAndselectGroup(WebDriver driver, String groupNm, ExtentTest extentTest) throws Exception {
        WebElement group = driver.findElement(By.xpath(CustomerDashboardPage.DIV_ID_GROUP_DROPDOWN_SPAN));
        List<WebElement> iterateGroup = group.findElements(By.tagName(ApplicationHtmlConstants.SPAN));
        for (int i = 0; i < iterateGroup.size(); i++) {
            String extractGroupNm = iterateGroup.get(i).getAttribute(ApplicationHtmlConstants.TITLE);
            if (extractGroupNm.equals(groupNm)) {
                try {
                    Assert.assertEquals(groupNm, extractGroupNm);
                    Thread.sleep(500);
                    CommonUtility.highLightElement(driver, iterateGroup.get(i));
                    iterateGroup.get(i - 1).click();
                    Thread.sleep(2000);
                    extentTest.log(LogStatus.PASS, "FOUND GROUP ["+groupNm+"]");
                } catch (AssertionError error) {
                    extentTest.log(LogStatus.FAIL, "UNABLE TO LOCATE GROUP ["+groupNm+"]");
                    extentTest.log(LogStatus.FAIL, error);
                    //throw error;
                }
            }
        }
    }

    public static void selectTagAndAssignToDevice(WebDriver driver, String deviceNm, String tagNm, ExtentTest extentTest) throws InterruptedException {
        driver.findElement(By.xpath(SubFilterMenu.FILTER_BTN)).click();
        SetFilter.onDeviceNm_OnDeviceDashborad(driver, deviceNm);
        WebElement devices = driver.findElement(By.xpath(CustomerDashboardPage.DIV_CLASS_UI_GRID_CANVAS));
        List<WebElement> deviceList = devices.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        boolean ifElementPresent = false;
        for (int count = 0; count < deviceList.size(); count++) {
            String extractDeviceNm = deviceList.get(count).getAttribute(ApplicationHtmlConstants.TITLE);
            if (extractDeviceNm.equals(deviceNm)) {
                ifElementPresent = true;
                Assert.assertEquals(extractDeviceNm, deviceNm);
                deviceList.get(count - 4).click();
                driver.findElement(By.xpath(SubFilterMenu.ACTION_BTN)).click();
                CommonUtility.highLightElement(driver, driver.findElement(By.xpath(CustomerDashboardPage.SELECT_ASSIGN_TAG)));
                driver.findElement(By.xpath(CustomerDashboardPage.SELECT_ASSIGN_TAG)).click();
                Thread.sleep(3000);
                driver.findElement(By.xpath(AssignTag.FILTER_BTN)).click();
                driver.findElement(By.xpath(AssignTag.TAGSEARCH)).sendKeys(tagNm);
                Thread.sleep(2000);
                driver.findElement(By.xpath(AssignTag.SELECT_SEARCH_TAG)).click();
                CommonUtility.highLightElement(driver, driver.findElement(By.xpath(AssignTag.SAVE_BTN)));
                driver.findElement(By.xpath(AssignTag.SAVE_BTN)).click();
                Thread.sleep(1000);
                break;
            }
        }
        if (!ifElementPresent) {
            extentTest.log(LogStatus.FAIL, "DEVICE ["+deviceNm+"] NOT FOUND");
        }

    }

    public static boolean verifyTagAssignment(WebDriver driver, String deviceNm, String linkedTagName, ExtentTest extentTest) throws Exception {
        driver.findElement(By.xpath(SubFilterMenu.FILTER_BTN)).click();
        driver.findElement(By.xpath(SubFilterMenu.FILTER_BTN)).click();
        Thread.sleep(1000);
        SetFilter.onDeviceNm_OnDeviceDashborad(driver, deviceNm);
        Thread.sleep(1000);
        //Below code iterates through all the Devices and selects the one which is assigned to TagName
        try {
            WebElement deviceTitle = driver.findElement(By.xpath(CustomerDashboardPage.DEVICE_NAME_HOLDER));
            List<WebElement> iterateTitles = deviceTitle.findElements(By.tagName(ApplicationHtmlConstants.DIV));
            for (int count = 0; count < iterateTitles.size(); count++) {
                String extractDeviceNm = iterateTitles.get(count).getAttribute(ApplicationHtmlConstants.TITLE);
                if (extractDeviceNm.equals(deviceNm)) {
                    Assert.assertEquals(extractDeviceNm, deviceNm);
                    CommonUtility.highLightElement(driver, iterateTitles.get(count));
                    extentTest.log(LogStatus.INFO, "FOR DEVICE  ["+deviceNm+"] FOUND LINKED TAG  ["+linkedTagName+"]");
                    return true;
                }
            }
        } catch (Exception error) {
            extentTest.log(LogStatus.FAIL, "FOR DEVICE  ["+deviceNm+"]  CANNOT LOCATE LINKED TAG  ["+linkedTagName+"]");
            throw error;
        }


        return false;
    }

    public static void verifyAssignment(WebDriver driver, String deviceNm, String linkedNm, String toVerify, ExtentTest extentTest) throws InterruptedException {
        driver.findElement(By.xpath(SubFilterMenu.FILTER_BTN)).click();
        driver.findElement(By.xpath(SubFilterMenu.FILTER_BTN)).click();
        Thread.sleep(1000);
        SetFilter.onDeviceNm_OnDeviceDashborad(driver, deviceNm);
        Thread.sleep(1000);
        /*BELOW CODE ITERATES THROUGH ALL THE DEVICES AND
		 * SELECTS THE DEVICE WHICH IS ASSIGNED TO GIVEN TAG OR GROUP */
        WebElement deviceTitle = driver.findElement(By.xpath(CustomerDashboardPage.DEVICE_NAME_HOLDER));
        List<WebElement> iterateTitles = deviceTitle.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        boolean ifElementPresent = false;
        for (int count = 0; count < iterateTitles.size(); count++) {
            String extractDeviceNm = iterateTitles.get(count).getAttribute(ApplicationHtmlConstants.TITLE);
            Thread.sleep(50);
            if (extractDeviceNm.equals(deviceNm)) {
                ifElementPresent = true;
                CommonUtility.highLightElement(driver, iterateTitles.get(count));
                if (toVerify.equalsIgnoreCase("group")) {
                    extentTest.log(LogStatus.PASS, "FOR DEVICE ["+deviceNm+"] FOUND LINKED GROUP ["+linkedNm+"]");
                } else if (toVerify.equalsIgnoreCase("tag")) {
                    extentTest.log(LogStatus.PASS, "FOR DEVICE ["+deviceNm+"] FOUND LINKED TAG ["+linkedNm+"]");
                }
                break;
            }
        }
        if (!ifElementPresent) {
            extentTest.log(LogStatus.FAIL, "FOR DEVICE NAME ["+deviceNm+"] LINKED  ["+toVerify+"]  NOT FOUND");
        }
    }

    public static void addGroup(WebDriver driver, String grpNm, ExtentTest extentTest) throws Exception {
        Thread.sleep(1000);
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(Sidebar.ADDGRP)));
        driver.findElement(By.xpath(Sidebar.ADDGRP)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(ManageGroups.NEW_BTN)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(CreateNewGroup.NAME)).sendKeys(grpNm);
        try {
            CommonUtility.highLightElement(driver, driver.findElement(By.xpath(CreateNewTag.SAVE_BTN)));
            driver.findElement(By.xpath(CreateNewGroup.SAVE_BTN)).click();
            String successMessage = AppConstants.VERIFY_SUCCESSFULLY_ADDED;
            Thread.sleep(1500);
            String validateOccuranceOfSuccessMsg = driver.findElement(By.xpath(ManageGroups.VERIFY_MESSAGE)).getAttribute(ApplicationHtmlConstants.TITLE);
            Assert.assertEquals(validateOccuranceOfSuccessMsg, successMessage);
            CommonUtility.highLightElement(driver, driver.findElement(By.xpath(ManageTags.CLOSE_BTN)));
            driver.findElement(By.xpath(ManageGroups.CLOSE_BTN)).click();
            Thread.sleep(1000);
        } catch (AssertionError error) {
        	System.out.println("GROUP NAME  ["+grpNm+"]  ALREADY EXISTS");
        	extentTest.log(LogStatus.FAIL, error);
            driver.findElement(By.xpath(CreateNewGroup.CANCEL_BTN)).click();
            driver.findElement(By.xpath(ManageGroups.CLOSE_BTN)).click();
            throw error;
        }

    }

    public static boolean selectDeviceAndAssignGroup(WebDriver driver, String deviceNm, String grpNm, ExtentTest extentTest) throws Exception {
        driver.findElement(By.xpath(SubFilterMenu.FILTER_BTN)).click();
        Thread.sleep(500);
        SetFilter.onDeviceNm_OnDeviceDashborad(driver, deviceNm);
        WebElement deviceTitle = driver.findElement(By.xpath(CustomerDashboardPage.DEVICE_NAME_HOLDER));
        List<WebElement> deviceList = deviceTitle.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        for (int count = 0; count < deviceList.size(); count++) {
            String extractDevicenNm = deviceList.get(count).getAttribute(ApplicationHtmlConstants.TITLE);
            if (extractDevicenNm.equals(deviceNm)) {
                try {
                    deviceList.get(count-4).click();
                    driver.findElement(By.xpath(SubFilterMenu.ACTION_BTN)).click();
                    CommonUtility.highLightElement(driver, driver.findElement(By.xpath(CustomerDashboardPage.SELECT_ASSIGN_GROUP)));
                    driver.findElement(By.xpath(CustomerDashboardPage.SELECT_ASSIGN_GROUP)).click();
                    Thread.sleep(3000);
                    driver.findElement(By.xpath(AssignGroup.FILTER_GRP_BTN)).click();
                    driver.findElement(By.xpath(AssignGroup.GRP_SEARCH)).sendKeys(grpNm);
                    Thread.sleep(2000);
                    driver.findElement(By.xpath(AssignGroup.SELECT_SEARCH_GROUP)).click();
                    CommonUtility.highLightElement(driver, driver.findElement(By.xpath(AssignGroup.SAVE_GRP_BTN)));
                    driver.findElement(By.xpath(AssignGroup.SAVE_GRP_BTN)).click();
                    Thread.sleep(1000);
                    return true;
                } catch (AssertionError error) {
                    extentTest.log(LogStatus.FAIL, "UNABLE TO ASSIGN GROUP  (" + grpNm + ")  TO THE DEVICE  (" + deviceNm + ")");
                    extentTest.log(LogStatus.FAIL, error);
                   // throw error;
                }
            }
        }
        return false;
    }

    public static void selectDevice(WebDriver driver) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath(prop.getProperty("deviceDetailTable.checkDevice"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(prop.getProperty("deviceDetailTable.clickSoftKey"))).click();
    }

    public static String addAndVerifyDeviceGroup(WebDriver driver, String sDeviceGroupName) {
        driver.findElement(By.xpath(CustomerDashboardPage.A_ID_LNK_EDIT_GROUP)).click();
        driver.findElement(By.xpath(CustomerDashboardPage.BUTTON_ID_BTN_NEW_ITEM)).click();

        driver.findElement(By.xpath(CustomerDashboardPage.INPUT_ID_NEWITEM_0)).sendKeys(sDeviceGroupName);// +d.getTime());
        driver.findElement(By.xpath(CustomerDashboardPage.BUTTON_ID_BTN_SAVE_ITEM)).click();
        try {
            Thread.sleep(1000);
            driver.findElement(By.xpath(CustomerDashboardPage.BUTTON_ID_LNK_FILTER_ON_DEVICES)).click();
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        WebElement wManageGroupFilterTable = driver.findElement(By.xpath(CustomerDashboardPage.DIV_ID_ROW00FILTER_TABLE));
        List<WebElement> weManageGroupFilterTextFields = wManageGroupFilterTable.findElements(By.tagName(ApplicationHtmlConstants.INPUT));
        WebElement wManageGrFilterField = weManageGroupFilterTextFields.get(0);
        wManageGrFilterField.sendKeys(sDeviceGroupName);

        WebElement wManageDeviceGroupFilterTable = driver.findElement(By.xpath(CustomerDashboardPage.DIV_ID_ROW0FILTER_TABLE));
        List<WebElement> ManageGroupFilterRows = wManageDeviceGroupFilterTable.findElements(By.tagName(ApplicationHtmlConstants.DIV));

        for (int rnum5 = 0; rnum5 < ManageGroupFilterRows.size(); rnum5++) {
            if (ManageGroupFilterRows.get(rnum5).getText().equals(sDeviceGroupName)) {
                System.out.println("DEVICE GROUP " + ManageGroupFilterRows.get(rnum5).getText() + " ADDED SUCCESSFULLY");
                break;
            }
        }
        try {
            Thread.sleep(5000);
            driver.findElement(By.xpath(CustomerDashboardPage.BUTTON_ID_BTN_CLOSE_GROUP)).click();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sDeviceGroupName;
    }

    public static void DeleteDevice(WebDriver driver, String sDeviceName) {
        NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);
        try {
            Thread.sleep(2000);
            driver.findElement(By.xpath(CustomerDashboardPage.SPAN_ID_LNK_FILTER_ON_DEVICES)).click();
            WebElement wFilterTable = driver.findElement(By.xpath(CustomerDashboardPage.DIV_ID_CONFIG_TABLE));
            WebElement wFilterDeviceTable = wFilterTable.findElement(By.xpath(CustomerDashboardPage.DIV_CONTAINS_ID_GRID_CONTAINER));
            List<WebElement> weTextFields = wFilterDeviceTable.findElements(By.tagName(ApplicationHtmlConstants.INPUT));
            WebElement wDeviceNameSearchField = weTextFields.get(0);
            wDeviceNameSearchField.sendKeys(sDeviceName);
            Thread.sleep(3000);
            WebElement wDeviceGroupTableNew = driver.findElement(By.xpath(CustomerDashboardPage.DIV_CONTAINS_ID_GRID_CONTAINER1));
            List<WebElement> DeviceGrRowsNew = wDeviceGroupTableNew.findElements(By.tagName(ApplicationHtmlConstants.DIV));

            DeviceGrRowsNew.get(35).click();

            driver.findElement(By.xpath(CustomerDashboardPage.SPAN_ID_BTN_ACTION)).click();
            driver.findElement(By.xpath(CustomerDashboardPage.A_ID_BTN_DELETE_DEVICES)).click();
            driver.findElement(By.xpath(CustomerDashboardPage.BUTTON_ID_BTN_DELETE)).click();
            Thread.sleep(3000);
            Assert.assertTrue(driver.findElement(By.xpath(CustomerDashboardPage.BUTTON_ID_BTN_SUCCESS)).isDisplayed(), "Device deletion Assertion");
            driver.findElement(By.xpath(CustomerDashboardPage.BUTTON_ID_BTN_SUCCESS1)).click();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void DeleteDeviceGroup(WebDriver driver, String sDeviceGroupName) {
        NavigateTo(driver, "Dashboard", null);
        driver.findElement(By.xpath(CustomerDashboardPage.A_ID_LNK_EDIT_GROUP1)).click();
        try {
            Thread.sleep(2000);
            driver.findElement(By.xpath(CustomerDashboardPage.BUTTON_ID_LNK_FILTER_ON_DEVICES1)).click();
            WebElement wManageDeviceGroupTable = driver.findElement(By.xpath(CustomerDashboardPage.DIV_ID_ROW00FILTER_TABLE1));
            List<WebElement> ManageDeviceGrInput = wManageDeviceGroupTable.findElements(By.tagName(ApplicationHtmlConstants.INPUT));
            WebElement eSearch = ManageDeviceGrInput.get(0);
            eSearch.sendKeys(sDeviceGroupName);
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        WebElement wManageDeviceGroupTable = driver.findElement(By.xpath(CustomerDashboardPage.DIV_ID_ROW0FILTER_TABLE1));
        List<WebElement> ManageDeviceGrRows = wManageDeviceGroupTable.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        ManageDeviceGrRows.get(2).click();
        driver.findElement(By.xpath(CustomerDashboardPage.BUTTON_ID_BTN_DELETE_ITEM)).click();
        driver.findElement(By.xpath(CustomerDashboardPage.BUTTON_ID_BTN_OK_DIALOG_BTN)).click();
        driver.findElement(By.xpath(CustomerDashboardPage.BUTTON_ID_BTN_CLOSE_GROUP1)).click();
    }

    public static void NavigateTo(WebDriver driver, String sSubmenu, String sFinalMenu) {
        try {
            Thread.sleep(2000);
            driver.findElement(By.xpath(CustomerDashboardPage.SPAN_CLASS_ICON_K_MENU)).click();
            Thread.sleep(2000);
            if (sSubmenu != null) {
                driver.findElement(By.xpath(CustomerDashboardPage.A_TITLE + sSubmenu + "']")).click();
            }
            Thread.sleep(2000);
            if (sFinalMenu != null) {
                CommonUtility.highLightElement(driver, driver.findElement(By.xpath(CustomerDashboardPage.A_TITLE + sFinalMenu + "']")));
                driver.findElement(By.xpath(CustomerDashboardPage.A_TITLE + sFinalMenu + "']")).click();
            }
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void goToDashBoard(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5000);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(locators.LINK_LAURIETEST)));
        driver.findElement(By.id(locators.LINK_LAURIETEST)).click();
        Thread.sleep(2000);
        driver.findElement(By.id(locators.LINK_DASHBOARD)).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //Select  Device for LAURIE TEST
    public static boolean selectSource(WebDriver driver, String sMigrateType) throws InterruptedException {
        try {
            boolean bEnv = true;
            Thread.sleep(8000);
            WebElement wContainer = driver.findElement(By.xpath(CustomerDashboardPage.DIV_CLASS_COL_MD_6_SOURCE_HEADER));
            List<WebElement> wSource = wContainer.findElements(By.tagName(ApplicationHtmlConstants.INPUT));

            for (int rnum3 = 0; rnum3 < wSource.size(); rnum3++) {
                if (wSource.get(rnum3).getAttribute("id").equals("comboInput")) {
                    wSource.get(rnum3).click();
                }
                //To Invoke WDM flow which chooses a file for Device migration
                if (sMigrateType.equals("SelectaFile")) {
                    wSource.get(rnum3).findElement(By.xpath(CustomerDashboardPage.SPAN_TITLE_SELECT_A_FILE)).click();
                    if (driver.findElements(By.xpath(CustomerDashboardPage.SPAN_CLASS_MSG_TEXT_NG_BINDING)).size() != 0) {
                        String sSDMError = driver.findElement(By.xpath(CustomerDashboardPage.SPAN_CLASS_MSG_TEXT_NG_BINDING)).getAttribute("innerText");
                        bEnv = false;
                        return bEnv;
                    }
                } else {
                    //To Invoke SDM flow which chooses a tenant's device for Device migration
                    wSource.get(rnum3).sendKeys(Keys.TAB);

                    WebElement wSelectSourceCombo = driver.findElement(By.xpath(CustomerDashboardPage.UL_CLASS_SEARCH_OPTIONS));
                    List<WebElement> wSourceOptions = wSelectSourceCombo.findElements(By.tagName("li"));

                    for (int rnum11 = 0; rnum11 < wSourceOptions.size(); rnum11++) {
                        Thread.sleep(1000);
                        wSourceOptions.get(rnum11).sendKeys(Keys.DOWN);


                        if (rnum11 == 19) {
                            driver.switchTo().defaultContent();
                            WebElement wSelectSourceComboNew = driver.findElement(By.xpath(CustomerDashboardPage.UL_CLASS_SEARCH_OPTIONS));
                            List<WebElement> wSourceOptionsNew = wSelectSourceComboNew.findElements(By.tagName("li"));

                            for (int rnum12 = 20; rnum12 < wSourceOptionsNew.size(); rnum12++) {
                                Thread.sleep(1000);
                                wSourceOptions.get(rnum11).sendKeys(Keys.DOWN);
                                if (wSourceOptions.get(rnum11).findElement(By.xpath(CustomerDashboardPage.SPAN_TITLE + sMigrateType + "']")).isDisplayed()) {
                                    wSourceOptions.get(rnum11).findElement(By.xpath(CustomerDashboardPage.SPAN_TITLE + sMigrateType + "']")).click();
                                    if (driver.findElements(By.xpath(CustomerDashboardPage.SPAN_CLASS_MSG_TEXT_NG_BINDING)).size() != 0) {
                                        String sSDMError = driver.findElement(By.xpath(CustomerDashboardPage.SPAN_CLASS_MSG_TEXT_NG_BINDING)).getAttribute("innerText");
                                        bEnv = false;
                                        return bEnv;
                                    }
                                }
                                ;
                                break;
                            }
                        }
                    }
                    try {
                        Thread.sleep(3000);
                        WebElement wOptions = driver.findElement(By.xpath(CustomerDashboardPage.TR_ID_FANCYTREE_ID_SOURCE_252));
                        wOptions.click();
                        Thread.sleep(3000);
                        List<WebElement> wSourceOptionsNew = wOptions.findElements(By.tagName(ApplicationHtmlConstants.SPAN));
                        wSourceOptionsNew.get(1).click();

                        WebElement wDevices = driver.findElement(By.xpath(CustomerDashboardPage.TR_ID_FANCYTREE_ID_SOURCE_253));
                        wDevices.click();
                        driver.switchTo().defaultContent();

                        Thread.sleep(3000);
                        List<WebElement> wSourceDeviceName = wDevices.findElements(By.tagName(ApplicationHtmlConstants.SPAN));
                        wSourceDeviceName.get(1).click();

                        driver.findElement(By.xpath(CustomerDashboardPage.TR_ID_FANCYTREE_ID_SOURCE_2531)).click();

                        Thread.sleep(3000);
                        WebElement wDevicesName = driver.findElement(By.xpath(CustomerDashboardPage.TR_ID_FANCYTREE_ID_SOURCE_3253));
                        Thread.sleep(3000);
                        List<WebElement> wDeviceName = wDevicesName.findElements(By.tagName(ApplicationHtmlConstants.SPAN));
                        for (int rnum13 = 0; rnum13 < wDeviceName.size(); rnum13++) {

                            if (wDeviceName.get(rnum13).getAttribute("class").equals("fancytree-checkbox")) {
                                wDeviceName.get(rnum13).click();
                                Thread.sleep(3000);
                            }
                        }
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            }

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    public static boolean searchAndVerifyDevice(WebDriver driver, String deviceName, ExtentTest extentTest) throws InterruptedException {
        // CLICK ON HAMBURGER MENU AND CLICK DEVICE
        CustomerDashboard.NavigateTo(driver, null, "Device");
        Thread.sleep(1000);
        driver.findElement(By.xpath(SubFilterMenu.FILTER_BTN)).click();
        SetFilter.onDeviceNm(driver, deviceName);
        Thread.sleep(2000);
        WebElement deviceGroupTable = driver.findElement(By.xpath(CustomerDashboardPage.DIV_CONTAINS_ID_GRID_CONTAINER2));
        List<WebElement> extractRows = deviceGroupTable.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        boolean ifElementExists = false;
        for (int count = 0; count < extractRows.size(); count++) {
            if (extractRows.get(count).getAttribute(ApplicationHtmlConstants.TITLE).equals(deviceName)) {
                ifElementExists = true;
                CommonUtility.highLightElement(driver, extractRows.get(count));
                Assert.assertTrue(driver.findElement(By.xpath("//div[@title='" + deviceName + "']")).isDisplayed(), "Device Search Assertion");
                extentTest.log(LogStatus.PASS, "DEVICE Nm [" + deviceName + "] SUCCESSFULLY ADDED");
                break;
            }
        }
        Thread.sleep(2000);
        if (!ifElementExists) {
            extentTest.log(LogStatus.FAIL, "FAILED TO ADD DEVICE ["+deviceName+"]");
        }
        return ifElementExists;
    }
    
    
    
    

}
