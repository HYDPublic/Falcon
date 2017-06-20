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
import com.kronos.udm.data.InputAlertOnAssigningSharedTemplateToSingleProfileValidator;
import com.kronos.udm.utils.UtilityFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AlertOnAssigningSharedTemplateToSingleProfileValidator {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description ="Validate Alert Message On Assigning Shared Template to Single Profile w/o Changing The Name")
    public void validteAlertOnAssigningSharedTemplateToSingleProfile(String browserType, String uri, String usrnm, String pwd) throws Exception {
        // Create instance of xls to read input Data for the test
        InputAlertOnAssigningSharedTemplateToSingleProfileValidator getData = new InputAlertOnAssigningSharedTemplateToSingleProfileValidator();

        ExtentReports extentReport = new ExtentReports(AppConstants.REPORT_ALERT_SHAREDTEMPLATE, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.REASSIGNING_VALIDATE_ALERT_ON_PROFILE, AppConstants.VALIDATE_ALERT);
        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
        	CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);
            // Choose AppConfigNm which is assigned to shared Profiles
            DeviceConfiguration.chooseAppConfigTemplateNm(driver, getData.getAppConfigNm());
            ApplicationConfiguration.changeProfileSelection(driver, getData.getProfileNm());
            // Assign Shared Template to Single Profile
            ApplicationConfiguration.validateChooseNewTemplateDialog(driver, getData.getAppConfigNm(), extentTest);
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
