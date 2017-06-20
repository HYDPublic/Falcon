package com.kronos.udm.testcases;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kronos.udm.utils.AppConstants;
import com.kronos.udm.utils.CommonUtility;
import com.kronos.udm.utils.CustomerDashboard;
import com.kronos.udm.utils.DeviceConfiguration;
import com.kronos.udm.utils.CustomerManagement;
import com.kronos.udm.data.InputAutoRegistrationEnabler;
import com.kronos.udm.utils.UtilityFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AutoRegistrationEnabler {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test
	public static void enableAutoRegistration (String browserType, String uri, String usrnm, String pwd) throws Exception	{
		// Create instance of xls to read input Data for the test
		InputAutoRegistrationEnabler getData = new InputAutoRegistrationEnabler();

        ExtentReports extentReport = new ExtentReports(AppConstants.REPORTS_NM_AUTO_CONFIG, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.ENABLE_AUTO_CONFIG, AppConstants.VERIFY_CONFIG);
        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
        	CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);
            DeviceConfiguration.openAutoRegistration (driver);
            DeviceConfiguration.saveAutoRegistration (driver, getData.getPwd(), getData.getConfirmPwd(), getData.getProfileForKronosInTouch(), getData.getTimeZone(), extentTest);
        } 
        catch (Exception error) {
        	extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        }
        finally {
        	CommonUtility.performSignout(driver, extentReport, extentTest);
            /*extent.endTest(extentTest);
            extent.flush();
            extent.close();*/
            UtilityFunctions.cleanupBrowserInstances();
        }
	}

}
