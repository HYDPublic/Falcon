package com.kronos.udm.testcases;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kronos.udm.utils.CommonUtility;
import com.kronos.udm.utils.CustomerDashboard;
import com.kronos.udm.data.InputTagCreator;
import com.kronos.udm.utils.AppConstants;
import com.kronos.udm.utils.SdmPublishTool;
import com.kronos.udm.utils.UtilityFunctions;
import com.kronos.udm.utils.CustomerManagement;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SDMUDMDeviceMigrator {
	@Parameters ({"browserType","uri","usr","pwd"})
    @Test
    public void UDMSDMFlow(String browserType, String uri, String usrnm, String pwd) throws Exception {
        // Create instance of xls to read input Data for the test
        InputTagCreator getData = new InputTagCreator();

        Properties prop = UtilityFunctions.ReadPropertyFile(AppConstants.ENV_PROPERTY);
        String extentReportFile = "reports\\DeviceMigartion.html";

        ExtentReports extent = new ExtentReports(extentReportFile, true);//, true, NetworkMode.OFFLINE);
        ExtentTest extentTest = extent.startTest("Device Migration", "VERIFY MIGRATED DEVICE");
        WebDriver driver = CommonUtility.openBrowser(browserType);

        //CommonUtility.performLogin(driver, getData.getUsrNmUdm(), getData.getPwdSdm(), getData.getSdmUrl());
    	CommonUtility.performLogin(driver, usrnm, pwd, uri);
        SdmPublishTool.selectSourceCustomer(driver, getData.getCustomerNm());

        CustomerDashboard.selectSource(driver, "SelectaFile");
        UtilityFunctions.openFile("C:\\Kronos\\KronosPOCMaven\\src\\DataMigration2016-12-08T18-01-01.431.zip");

        Thread.sleep(4000);
        if (driver.findElements(By.xpath("//span[@class='msg-text ng-binding']")).size() != 0) {
            String sSDMError = driver.findElement(By.xpath("//span[@class='msg-text ng-binding']")).getAttribute("innerText");
            //extentTest.log(LogStatus.ERROR, sSDMError);
        } else {
            driver.findElement(By.xpath("//i[@title='Review and Publish']")).click();
            Thread.sleep(8000);
            WebElement wFilterTawPublicSummaryble = driver.findElement(By.xpath("//div[@class='publishTool-button']"));
            List<WebElement> wButtons = wFilterTawPublicSummaryble.findElements(By.tagName("button"));
            for (int rnum8 = 0; rnum8 < wButtons.size(); rnum8++) {
                Thread.sleep(1000);
                if (wButtons.get(rnum8).getAttribute("title").equals("Publish")) {
                    wButtons.get(rnum8).click();
                    Thread.sleep(3000);
                    break;
                }
            }
            String sSuccessMessage = driver.findElement(By.xpath("//span[@class='textTranfrom ng-binding']")).getAttribute("innerText");
            extentTest.log(LogStatus.INFO, "File successfully published: Status is - " + sSuccessMessage);
            driver.findElement(By.xpath("//button[@title='Go to Publish History']")).click();
            Thread.sleep(5000);
        }
        driver.quit();


        driver = CommonUtility.openBrowser(prop.getProperty("Browser"));


        CommonUtility.performLogin(driver, getData.getUsrNmUdm(), getData.getPwdUdm(), getData.getUdmUrl());

        // Goto DashBoard
        CustomerManagement.chooseTenant(driver, getData.getCustomerNm());

        //extent.endTest(extentTest);
        if (CustomerDashboard.searchAndVerifyDevice(driver, prop.getProperty("DeviceName"), extentTest)) {
            extentTest.log(LogStatus.INFO, "Device " + prop.getProperty("DeviceName") + " successfully migrated to UDM");
        }
        // Deleting the Added device
        CustomerDashboard.DeleteDevice(driver, prop.getProperty("DeviceName"));
        driver.quit();
        extent.flush();
    }
}
