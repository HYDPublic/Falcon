package com.kronos.udm.testcases;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kronos.udm.utils.ApplicationConfiguration;
import com.kronos.udm.utils.AppConstants;
import com.kronos.udm.utils.CommonUtility;
import com.kronos.udm.utils.CustomerDashboard;
import com.kronos.udm.utils.DeviceConfiguration;
import com.kronos.udm.utils.CustomerManagement;
import com.kronos.udm.utils.UtilityFunctions;
import com.kronos.udm.data.InputSharedTemplateToSingleProfileAssigner;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SharedTemplateToSingleProfileAssigner {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description ="Assigning Shared Template to Single Profile")
    public void validteAlertOnAssigningSharedTemplateToSingleProfile(String browserType, String uri, String usrnm, String pwd) throws Exception {
        // Create instance of xls to read input Data for the test
		InputSharedTemplateToSingleProfileAssigner getData = new InputSharedTemplateToSingleProfileAssigner();

        ExtentReports extentReport = new ExtentReports(AppConstants.REPORTS_ASSIGN_TEMPLATE_TOSINGLE, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.SHARED_TEMPLATE_SINGLE, AppConstants.VERIFY_TEMPLATE_ASSIGNMENT);
        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
            CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);
            // Choose AppConfigNm which is assigned to shared Profiles
            DeviceConfiguration.chooseAppConfigTemplateNm(driver, getData.getAppConfigNm());
            ApplicationConfiguration.changeProfileSelection(driver, getData.getProfileNm());
            ApplicationConfiguration.assignNewAppConfigTemplate (driver, getData.getAppConfigNewNm(), extentTest);
            // Verify Newly Assigned App Config Template
            ApplicationConfiguration.verifyNewAppConfigTemplate (driver, getData.getAppConfigNewNm(), extentTest);
        } 
        catch (Exception error) {
            extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        } finally {
        	CommonUtility.performSignout(driver, extentReport, extentTest);
            UtilityFunctions.cleanupBrowserInstances();
        }
    }

}
