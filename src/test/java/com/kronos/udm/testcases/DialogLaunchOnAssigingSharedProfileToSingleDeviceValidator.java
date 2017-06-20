package com.kronos.udm.testcases;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kronos.udm.data.InputDialogLaunchOnAssigingSharedProfileToSingleDeviceValidator;
import com.kronos.udm.utils.ApplicationConfiguration;
import com.kronos.udm.utils.AppConstants;
import com.kronos.udm.utils.CommonUtility;
import com.kronos.udm.utils.DeviceConfiguration;
import com.kronos.udm.utils.CustomerDashboard;
import com.kronos.udm.utils.CustomerManagement;
import com.kronos.udm.utils.UtilityFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DialogLaunchOnAssigingSharedProfileToSingleDeviceValidator {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description="validates dialog launch on assiging shared Profile to Single device")
    public void validateDialogLaunchOnAssigingSharedProfileToSingleDevice(String browserType, String uri, String usrnm, String pwd) throws Exception {
        // Create instance of xls to read input Data for the test
        InputDialogLaunchOnAssigingSharedProfileToSingleDeviceValidator getData = new InputDialogLaunchOnAssigingSharedProfileToSingleDeviceValidator();
        
        ExtentReports extentReport = new ExtentReports(AppConstants.DIALOGUE_SHAREDPROFILE_ASSIGN, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.VALIDATE_ON_REASSIGN_PROFILE, AppConstants.VERIFY_LAUNCH_ERRORMSG);
        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
        	CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);
            DeviceConfiguration.selectProfile(driver, getData.getProfileNm());
            ApplicationConfiguration.changeAssignmentToSingleDevice(driver, getData.getDeviceNm());
            ApplicationConfiguration.verifyNewTemplateAndProfileDialogIsVissible(driver, extentTest);
            // Verify error message on attempting to clicking APPLY while leaving templateNm and profileNm empty
            ApplicationConfiguration.verifyErrorMessage(driver, extentTest);
        } 
        catch (Exception error) {
            extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        } finally {
        	CommonUtility.performSignout(driver, extentReport, extentTest);
            UtilityFunctions.cleanupBrowserInstances();
        }
    }
}