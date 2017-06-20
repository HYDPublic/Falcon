package com.kronos.udm.testcases;

import java.io.IOException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kronos.udm.utils.AppConstants;
import com.kronos.udm.utils.CommonUtility;
import com.kronos.udm.utils.CustomerDashboard;
import com.kronos.udm.utils.CustomerManagement;
import com.kronos.udm.utils.InputSoftKeyScheduleCreator;
import com.kronos.udm.utils.UtilityFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SoftKeyScheduleCreator {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description="Create SoftKey Schedule")
    public void createSoftKeySchedule(String browserType, String uri, String usrnm, String pwd) throws Exception {
        // CREATE INSTANCE OF XLS TO READ INPUT DATA FOR THE TEST
    	InputSoftKeyScheduleCreator getData = new InputSoftKeyScheduleCreator();

        ExtentReports extentReport = new ExtentReports(AppConstants.REPORT_CREATE_SOFTKEYSCHEDULE, true);
        ExtentTest extentTest = extentReport.startTest("Soft Key Schedule Migration", "Verify migrated Key");
        WebDriver driver = CommonUtility.openBrowser(browserType);
		try {
			CommonUtility.performLogin(driver, usrnm, pwd, uri);
	        CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
	        CustomerDashboard.NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);
	        /*
	         * Test On HOLD
	         */
		}
		catch (Exception error){
			extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
		}
		finally {
        	CommonUtility.performSignout(driver, extentReport, extentTest);
		    UtilityFunctions.cleanupBrowserInstances();
		}
    }
}