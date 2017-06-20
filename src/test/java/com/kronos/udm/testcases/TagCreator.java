package com.kronos.udm.testcases;
import java.io.IOException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kronos.udm.data.InputTagCreator;
import com.kronos.udm.utils.AppConstants;
import com.kronos.udm.utils.CommonUtility;
import com.kronos.udm.utils.CustomerDashboard;
import com.kronos.udm.utils.CustomerManagement;
import com.kronos.udm.utils.UtilityFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TagCreator {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description="Create Tag")
    public void tagCreation(String browserType, String uri, String usrnm, String pwd) throws InterruptedException, IOException, Exception {
        // CREATE INSTANCE OF XLS TO READ INPUT DATA
        InputTagCreator getData = new InputTagCreator();
        
        ExtentReports extentReport = new ExtentReports(AppConstants.REPORT_CREATETAG, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.CREATE_TAG, AppConstants.VERIFY_TAG);
        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
            CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.addTag(driver, getData.getTagNm(), extentTest);
            CustomerDashboard.selectTagAndAssignToDevice(driver, getData.getDeviceNm(), getData.getTagNm(), extentTest);
            // SEARCH FOR ASSIGNED TAG IN PREVIOUS STEP
            CustomerDashboard.searchAndselectTag(driver, getData.getTagNm(), extentTest);
            // VERIFY TAG ASSIGNMENT
            CustomerDashboard.verifyAssignment(driver, getData.getDeviceNm(), getData.getTagNm(), AppConstants.TAG, extentTest);
        } 
        catch (Exception error) {
        	extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        }
        finally {
        	CommonUtility.performSignout(driver, extentReport, extentTest);
            UtilityFunctions.cleanupBrowserInstances();
        }
    }
}
