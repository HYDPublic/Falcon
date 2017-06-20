package com.kronos.udm.testcases;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kronos.udm.utils.AppConstants;
import com.kronos.udm.utils.CommonUtility;
import com.kronos.udm.utils.DeviceConfiguration;
import com.kronos.udm.utils.CustomerDashboard;
import com.kronos.udm.data.InputProfileToSingleDeviceAssigner;
import com.kronos.udm.utils.UtilityFunctions;
import com.kronos.udm.utils.CustomerManagement;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ProfileToSingleDeviceAssigner {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description="Assign profile to single device")
    public void assignProfileToSingleDevice(String browserType, String uri, String usrnm, String pwd) throws Exception {
        // Create instance of xls to read input Data for the test
        InputProfileToSingleDeviceAssigner getData = new InputProfileToSingleDeviceAssigner();
        
        ExtentReports extentReport = new ExtentReports(AppConstants.REPORT_ASSIGN_SINGLE, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.ASSIGN_PROFILE_SINGLE, AppConstants.VERIFY_ASSIGN_SINGLE);
        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
        	CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);

			/* Choose a Device and click Assign Profile
			* "1" - number of devices to select
			* "null" - second device name*/
            DeviceConfiguration.selectDeviceAndClickAction(driver, 1, getData.getDeviceNm(), null);

            // Apply Profile
            DeviceConfiguration.applyProfileToSelectedDevice(driver, getData.getProfileNm(), extentTest);
	
			/* Verify Profile 
			* validates the message "Profile applied successfully" on the UI and 
			* validates Profile name exists on Device Config table */
            DeviceConfiguration.verifyProfileCreation(driver, getData.getProfileNm(), getData.getDeviceNm(), extentTest);
        } catch (Exception error) {
            extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        } finally {
        	CommonUtility.performSignout(driver, extentReport, extentTest);
            UtilityFunctions.cleanupBrowserInstances();
        }
    }
}
