package com.kronos.udm.testcases;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kronos.udm.utils.AppConstants;
import com.kronos.udm.utils.CommonUtility;
import com.kronos.udm.utils.CommunicationSettings;
import com.kronos.udm.utils.CustomerDashboard;
import com.kronos.udm.utils.CustomerManagement;
import com.kronos.udm.utils.DeviceDetails;
import com.kronos.udm.data.InputCommSettingsCreator;
import com.kronos.udm.utils.UtilityFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CommSettingsCreator {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description="Creates communication setting")
    public void createCommSettings (String browserType, String uri, String usrnm, String pwd) throws Exception {
        // Create instance of xls to read Input Data for the test
        InputCommSettingsCreator getData = new InputCommSettingsCreator();
        
        ExtentReports extentReport = new ExtentReports(AppConstants.REPORT_NM_COMMON_SETTINGS, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.CREATE_COMM, AppConstants.VERIFY_COMM);
        WebDriver driver = CommonUtility.openBrowser(browserType);
        try {
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
            CustomerManagement.chooseTenant(driver, getData.getCustomerNm());
            CustomerDashboard.NavigateTo(driver, AppConstants.CONFIGURATION, AppConstants.DEVICE);
            DeviceDetails.selectProfile(driver, getData.getProfileNm());
            CommunicationSettings.createCommunicationSettings(driver, getData.getCommSettingNm(), getData.getPrimaryServer(),
                                                              getData.getPrimaryServerInstanceId(), getData.getPort(), extentTest);

            DeviceDetails.verifyCommSettingTitle(driver, getData.getCommSettingNm(), extentTest, getData.getProfileNm());
        } catch (Exception error) {
            extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        } 
        finally {
        	CommonUtility.performSignout(driver, extentReport, extentTest);
            UtilityFunctions.cleanupBrowserInstances();
        }

    }
}