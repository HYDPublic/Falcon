package com.kronos.udm.testcases;

import java.util.List;
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
import com.kronos.udm.data.InputSingleTemplateToMultipleProfilesAssigner;
import com.kronos.udm.utils.UtilityFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SingleTemplateToMultipleProfilesAssigner {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description="Assign Single Template To Multiple Profiles")
    public void assignSingleTemplateToMultipleProfiles(String browserType, String uri, String usrnm, String pwd) throws Exception {
        // Create instance of xls to read input Data for the test
        InputSingleTemplateToMultipleProfilesAssigner getData = new InputSingleTemplateToMultipleProfilesAssigner();

        ExtentReports extentReport = new ExtentReports(AppConstants.REPORTS_TEMPLATE_MULTIPLE, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.TEMPLATE_ASSIGN_MULTIPLE, AppConstants.VERIFY_TEMPASSIGNMENT_MULTIPLE);
        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
            CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);
            // Adds new profile(s) based on the input Received from excel for "NoOfProfile"
            List<String> newProfileList = DeviceConfiguration.addProfile(driver, getData.getProfileNm(), getData.getNoOfProfile(), extentTest);
            // Choose either of the newly created Profiles
            // "0" - Picks profile at Index ZERO
            DeviceConfiguration.chooseAProfile(driver, newProfileList, 0);

            ApplicationConfiguration.addAppConfigTemplate(driver, getData.getAppConfigNm());
            DeviceConfiguration.chooseAProfile(driver, newProfileList, 1);
            ApplicationConfiguration.selectExistingAppConfigTemplate(driver, getData.getAppConfigNm());

            // Verify the ALL OPTION get Enabled for the Profile
            ApplicationConfiguration.verifyProfileAllocationToMultipleProfiles(driver, getData.getAppConfigNm(), extentTest);
        } 
        catch (Exception error) {
        	extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        } finally {
        	CommonUtility.performSignout(driver, extentReport, extentTest);
            UtilityFunctions.cleanupBrowserInstances();
        }
    }
}

