package com.kronos.udm.testcases;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kronos.udm.utils.ApplicationConfiguration;
import com.kronos.udm.utils.AppConstants;
import com.kronos.udm.utils.CommonUtility;
import com.kronos.udm.utils.CustomerDashboard;
import com.kronos.udm.utils.CustomerManagement;
import com.kronos.udm.utils.DeviceConfiguration;
import com.kronos.udm.data.InputSharedProfileBetweenDevicesToSingleDeviceAssigner;
import com.kronos.udm.utils.UtilityFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SharedProfileBetweenDevicesToSingleDeviceAssigner {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description="Assign Shared Profile To Single Device")
    public void assignSharedProfileBetweenDevicesToSingleDevice(String browserType, String uri, String usrnm, String pwd) throws Exception {
        // Create instance of xls to read input Data for the test
        InputSharedProfileBetweenDevicesToSingleDeviceAssigner getData = new InputSharedProfileBetweenDevicesToSingleDeviceAssigner();

        ExtentReports extentReport = new ExtentReports(AppConstants.REPORTS_SHARED_SINGLE, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.ASSIGN_SHARED_SINGLE, AppConstants.VERIFY_APPLIED_PROFILE);
        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
            CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);
            DeviceConfiguration.selectProfile(driver, getData.getProfileNm());
            ApplicationConfiguration.changeAssignmentToSingleDevice(driver, getData.getDeviceNm());
            ApplicationConfiguration.addNewProfileAndTemplate(driver, getData.getAppConfigNewNm(), getData.getProfileNewNm());

            // VERIFY NEWLY ASSIGNED PROFILE AND TEMPLATE NAME
            ApplicationConfiguration.verifyProfileAndTemplateNm(driver, getData.getAppConfigNewNm(), getData.getProfileNewNm(), extentTest);
        } 
        catch (Exception error) {
            extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        } finally {
        	CommonUtility.performSignout(driver, extentReport, extentTest);
            UtilityFunctions.cleanupBrowserInstances();
        }
    }
}