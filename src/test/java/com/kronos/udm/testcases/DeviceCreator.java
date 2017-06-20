package com.kronos.udm.testcases;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kronos.udm.data.InputDeviceCreator;
import com.kronos.udm.utils.AppConstants;
import com.kronos.udm.utils.CommonUtility;
import com.kronos.udm.utils.CustomerDashboard;
import com.kronos.udm.utils.CustomerManagement;
import com.kronos.udm.utils.UtilityFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DeviceCreator {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description="Creates Device")
    public void createDevice(String browserType, String uri, String usrnm, String pwd) throws Exception {
	
        // Create instance of xls to read input Data for the test
        InputDeviceCreator getData = new InputDeviceCreator();

        ExtentReports extentReport = new ExtentReports(AppConstants.REPORT_CREATEDEVICE, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.CREATE_DEVICE, AppConstants.VERIFY_DEVICE);
    
        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
        	CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);
            CustomerDashboard.addDevice(driver, getData.getDeviceId(), getData.getDeviceNm(), getData.getNoOfDevicesToAdd(), extentTest);
            CustomerDashboard.searchAndVerifyDevice(driver, getData.getDeviceNm(), extentTest);
        } catch (Exception error) {
            extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        } finally {
        	CommonUtility.performSignout(driver, extentReport, extentTest);
            UtilityFunctions.cleanupBrowserInstances();
        }
    }
}