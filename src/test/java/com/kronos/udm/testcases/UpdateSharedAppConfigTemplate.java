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
import com.kronos.udm.data.InputUpdateSharedAppConfigTemplate;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UpdateSharedAppConfigTemplate {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description ="Update Shared App Config Template")
    public void validteUpdatedAppConfigTemplate(String browserType, String uri, String usrnm, String pwd) throws Exception {
        // Create instance of xls to read input Data for the test
		InputUpdateSharedAppConfigTemplate getData = new InputUpdateSharedAppConfigTemplate();

        ExtentReports extentReport = new ExtentReports(AppConstants.REPORTS_UPDATE_APP_CONFIG_TEMPLATE, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.UPDATE_SHARED_APP_CONFIG_TEMPLATE, AppConstants.VALIDATE_ALL_RADIO_OPTION);
        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
            CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);
            // Choose AppConfigNm which is assigned to shared Profiles
            DeviceConfiguration.chooseAppConfigTemplateNm(driver, getData.getAppConfigNm());
            ApplicationConfiguration.updateProfileData(driver, getData.getScheduleforEmp(), extentTest );
            ApplicationConfiguration.verifyProfileSelection (driver, extentTest);
        } 
        catch (Exception error) {
            extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        } finally {
        	 CommonUtility.performSignout(driver, extentReport, extentTest);
            /*extent.endTest(extentTest);
            extent.flush();
            extent.close();*/
            UtilityFunctions.cleanupBrowserInstances();
        }
    }

}
