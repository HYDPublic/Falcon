package com.kronos.udm.testcases;
import java.io.IOException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.kronos.udm.data.InputGroupCreator;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.kronos.udm.utils.AppConstants;
import com.kronos.udm.utils.CommonUtility;
import com.kronos.udm.utils.CustomerDashboard;
import com.kronos.udm.utils.UtilityFunctions;
import com.kronos.udm.utils.CustomerManagement;

public class GroupCreator {
	@Parameters ({"browserType","uri","usr","pwd"})
    @Test (description="Creates Group")
    public void createGroup(String browserType, String uri, String usrnm, String pwd) throws InterruptedException, IOException, Exception {
        // Create instance of xls to read input Data for the test
        InputGroupCreator getData = new InputGroupCreator();
        
        ExtentReports extentReport = new ExtentReports(AppConstants.REPORT_CREATE_GROUP, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.CREATE_GROUP, AppConstants.VERIFY_GROUP);
        
        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
        	CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.addGroup(driver, getData.getGrpNm(), extentTest);
            CustomerDashboard.selectDeviceAndAssignGroup(driver, getData.getDeviceNm(), getData.getGrpNm(), extentTest);
            CustomerDashboard.searchAndselectGroup(driver, getData.getGrpNm(), extentTest);
            // Verify Group assignment
            CustomerDashboard.verifyAssignment(driver, getData.getDeviceNm(), getData.getGrpNm(), AppConstants.GROUP, extentTest);
        } catch (Exception error) {
            extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        } finally {
        	CommonUtility.performSignout(driver, extentReport, extentTest);
            UtilityFunctions.cleanupBrowserInstances();
        }
    }
}
