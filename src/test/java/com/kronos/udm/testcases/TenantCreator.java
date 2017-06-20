package com.kronos.udm.testcases;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import com.kronos.udm.data.InputTenantCreator;
import com.kronos.udm.utils.AppConstants;
import com.kronos.udm.utils.CommonUtility;
import com.kronos.udm.utils.CustomerManagement;
import com.kronos.udm.utils.UtilityFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TenantCreator {
	@Parameters ({"browserType","uri","usr","pwd"})
	@Test (description="Creates Tenant")
    public void createTenant(String browserType, String uri, String usrnm, String pwd) throws InterruptedException, IOException, Exception {
	
    	// CREATE INSTANCE OF XLS TO READ DATA
        InputTenantCreator getData = new InputTenantCreator();
        
        ExtentReports extentReport = new ExtentReports(AppConstants.REPORT_CREATE_TENANT, true);
        ExtentTest extentTest = extentReport.startTest(AppConstants.CREATE_TENANT, AppConstants.VERIFY_TENANT);
        
        //WebDriver driver = CommonUtility.openBrowser(getData.getBrowserTyp());
        /*Properties prop = UtilityFunctions.ReadPropertyFile(AppConstants.ENV_PROPERTY);
        WebDriver driver = CommonUtility.openBrowser(prop.getProperty(AppConstants.BROWSER_TYP));*/
        WebDriver driver = CommonUtility.openBrowser(browserType);
        
        try {
            //CommonUtility.performLogin(driver, getData.getUsrNmUdm(), getData.getPwdUdm(), getData.getUdmUrl());
        	//CommonUtility.performLogin(driver, prop.getProperty(AppConstants.USRNM), prop.getProperty(AppConstants.PWD), prop.getProperty(AppConstants.ENV));
        	CommonUtility.performLogin(driver, usrnm, pwd, uri);
        	
            CustomerManagement.createTenant(driver, getData.getTenantNm(), getData.getSolutionId(), getData.getEnvTyp(), 
            		getData.getTenantId(), getData.getName(), getData.getTitle(), getData.getEmail(), 
            		getData.getServerTyp(), getData.getHostUrl(), getData.getHostUsr(), getData.getHostPwd(), getData.getConfirmPwd(), extentTest);
            
            CustomerManagement.verifyTenant(driver,getData.getTenantNm(), extentTest);
            
        } catch (Exception error) {
            extentTest.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(error));
        } 
        finally {
        	CommonUtility.performSignout(driver, extentReport, extentTest);
            UtilityFunctions.cleanupBrowserInstances();
        }
    }
}