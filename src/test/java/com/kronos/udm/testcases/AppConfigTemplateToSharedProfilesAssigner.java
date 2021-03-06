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
import com.kronos.udm.data.InputAppConfigTemplateToSharedProfilesAssigner;
import com.kronos.udm.utils.UtilityFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AppConfigTemplateToSharedProfilesAssigner {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description="Assigns AppConfig template to shared profiles")
    public void assignAppConfigTemplateToSharedProfiles(String browserType, String uri, String usrnm, String pwd) throws Exception {
        // Create instance of xls to read input Data for the test
        InputAppConfigTemplateToSharedProfilesAssigner getData = new InputAppConfigTemplateToSharedProfilesAssigner();

        ExtentReports extentReport = new ExtentReports(AppConstants.REPORT_ASSIGN_SHARED, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.APPLY_APPCONFIG_SAME_PROFILE, AppConstants.VERIFY_APP_ASSIGNMENT);

        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
            CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);
            DeviceConfiguration.selectProfile(driver, getData.getProfileNm());
            ApplicationConfiguration.addAppConfigName(driver, getData.getAppConfigNm());
            DeviceConfiguration.verifyAppConfigNm(driver, getData.getAppConfigNm(), extentTest);
        } 
        catch (Exception error) {
            extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        } finally {
            CommonUtility.performSignout(driver, extentReport, extentTest);
            UtilityFunctions.cleanupBrowserInstances();
        }
    }
}
