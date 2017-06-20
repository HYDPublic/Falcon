package com.kronos.udm.testcases;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kronos.udm.utils.ApplicationConfiguration;
import com.kronos.udm.utils.AppConstants;
import com.kronos.udm.utils.CommonUtility;
import com.kronos.udm.utils.DeviceConfiguration;
import com.kronos.udm.utils.CustomerDashboard;
import com.kronos.udm.utils.CustomerManagement;
import com.kronos.udm.data.InputProfileToMultipleDevicesAssigner;
import com.kronos.udm.utils.UtilityFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ProfileToMultipleDevicesAssigner {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description="Assigns profile to multiple devices")    
    public void assignProfileToMultipleDevices(String browserType, String uri, String usrnm, String pwd) throws Exception {
        // Create instance of xls to read input Data for the test
        InputProfileToMultipleDevicesAssigner getData = new InputProfileToMultipleDevicesAssigner();
        ExtentReports extentReport = new ExtentReports(AppConstants.REPORT_ASSIGN_MULTIPLE, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.ASSIGN_PROFILE_MULTIPLE, AppConstants.VERIFY_ASSIGNMENT_MULTIPLE);
        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
        	CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);
			/* Choose devices and click Assign Profile
			 "2" - number of devices to select */
            DeviceConfiguration.selectDeviceAndClickAction(driver, 2, getData.getDeviceNm(), getData.getDeviceNm2());

            DeviceConfiguration.applyProfileToSelectedDevice(driver, getData.getProfileNm(), extentTest);
			/*Verify Profile 
			  "true" is passed to perform CLICK on the ProfileNm once fetched */
            ApplicationConfiguration.verifyProfileAllocationToDevices(driver, getData.getProfileNm(), getData.getDeviceNm(), getData.getDeviceNm2(), extentTest);
        } catch (Exception error) {
            extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        } 
        finally {
        	CommonUtility.performSignout(driver, extentReport, extentTest);
            UtilityFunctions.cleanupBrowserInstances();
        }
    }
}
