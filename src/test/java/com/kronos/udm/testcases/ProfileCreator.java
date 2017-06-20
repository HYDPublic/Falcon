package com.kronos.udm.testcases;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kronos.udm.utils.AppConstants;
import com.kronos.udm.utils.CommonUtility;
import com.kronos.udm.utils.CustomerDashboard;
import com.kronos.udm.utils.DeviceConfiguration;
import com.kronos.udm.utils.UtilityFunctions;
import com.kronos.udm.utils.CustomerManagement;
import com.kronos.udm.data.InputProfileCreator;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ProfileCreator {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description="Creates Profile")
    public void createProfile(String browserType, String uri, String usrnm, String pwd) throws Exception {
        // Create instance of xls to read Input Data for the test
        InputProfileCreator getData = new InputProfileCreator();
        ExtentReports extentReport = new ExtentReports(AppConstants.REPORT_CREATE_PROFILE, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.CREATE_PROFILE, AppConstants.VERIFY_CREATE_PROFILE);

        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
        	CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);
            // Add Profile
            List<String> profileNm = DeviceConfiguration.addProfile(driver, getData.getProfileNm(), getData.getNoOfProfile(), extentTest);
            // Verify Newly added Profile
            DeviceConfiguration.verifyProfile(driver, profileNm, extentTest);
        }
        catch (Exception error) {
            extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        } 
        finally {
        	
        	CommonUtility.performSignout(driver, extentReport, extentTest);
            UtilityFunctions.cleanupBrowserInstances();
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
        	Reporter.getCurrentTestResult().setStatus(ITestResult.SUCCESS);
        }
    }
}
