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
import com.kronos.udm.data.InputAlertOnAssigningSharedProfileToSingleDeviceValidator;
import com.kronos.udm.utils.UtilityFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AlertOnAssigningSharedProfileToSingleDeviceValidator {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description="Validates occurance alert message if the user attempts to save App Config with same name")
    public void validateAlertOnAssigningSharedProfileToSingleDevice(String browserType, String uri, String usrnm, String pwd) throws Exception {
        // Create instance of xls to read input Data for the test
        InputAlertOnAssigningSharedProfileToSingleDeviceValidator getData = new InputAlertOnAssigningSharedProfileToSingleDeviceValidator();

        ExtentReports extentReport = new ExtentReports(AppConstants.REPORT_ALERT_SHAREDPROFILE, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.REASSIGNING_VALIDATE_ALERT, AppConstants.VALIDATE_ALERT);
        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
            CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);
            // Click on the Device that share common profile Name
            DeviceConfiguration.selectProfile(driver, getData.getProfileNm());

            ApplicationConfiguration.changeAssignmentToSingleDevice(driver, getData.getDeviceNm());
            ApplicationConfiguration.addNewProfile(driver, getData.getAppConfigNm(), getData.getProfileNewNm(),extentTest);

            // Verify warning alert after adding new profile
            ApplicationConfiguration.verifyAlertText(driver, getData.getAppConfigNm(), extentTest);
        } 
        catch (Exception error) {
            extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        } finally {      
        	CommonUtility.performSignout(driver, extentReport, extentTest);
            UtilityFunctions.cleanupBrowserInstances();
        }
    }
}
