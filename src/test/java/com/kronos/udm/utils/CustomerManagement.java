package com.kronos.udm.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.kronos.udm.objrepo.CreateCustomerDialog;
import com.kronos.udm.objrepo.CustomerDashboardPage;
import com.kronos.udm.objrepo.CustomerManagementPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CustomerManagement {
	
	public static void searchTenant(WebDriver driver, String tenantNm) throws Exception {
		Thread.sleep(2000);
	    CommonUtility.highLightElement(driver, driver.findElement(By.xpath(CustomerManagementPage.FILTER)));
	    driver.findElement(By.xpath(CustomerManagementPage.FILTER)).click();
	    Thread.sleep(1000);
	    SetFilter.onTenantNm(driver, tenantNm);
	}
	
	public static void chooseTenant(WebDriver driver, String tenantNm) throws InterruptedException {
        try {
        	searchTenant(driver, tenantNm);
            driver.findElement(By.xpath(CustomerManagementPage.CUSTOMER_NAME.replace("&", tenantNm))).click();
            Thread.sleep(1000);
            WebElement tenantTable = driver.findElement(By.xpath(CustomerDashboardPage.CONTENT_TABLE_TENANT));
            List<WebElement> iterateTenantTable = tenantTable.findElements(By.tagName(ApplicationHtmlConstants.DIV));
            for (int rnum = 0; rnum < iterateTenantTable.size(); rnum++) {
                if (iterateTenantTable.get(rnum).getAttribute(ApplicationHtmlConstants.TITLE).equals(tenantNm)) {
                    Thread.sleep(800);
                    driver.findElement(By.id(CustomerManagementPage.LINK_DASHBOARD)).click();
                    break;
                }
            }
            Thread.sleep(2000);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public static void createTenant(WebDriver driver, String tenantNm, String solutionId, String envTyp, 
			String tenantId, String name, String title, String email, String hostServerTyp, String url, 
			String usr, String pwd, String confirmPwd, ExtentTest extentTest) throws Exception {
		Thread.sleep(1000);
		CommonUtility.highLightElement(driver, driver.findElement(By.xpath(CustomerManagementPage.ADD_CUSTOMER)));
		driver.findElement(By.xpath(CustomerManagementPage.ADD_CUSTOMER)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(CreateCustomerDialog.NAME)).sendKeys(tenantNm);
		driver.findElement(By.xpath(CreateCustomerDialog.SOLUTION_ID)).sendKeys(solutionId);
		CreateCustomerDialog.setEnvTyp(driver, envTyp);
		driver.findElement(By.xpath(CreateCustomerDialog.TENANT_ID)).sendKeys(tenantId);
		CommonUtility.highLightElement(driver, driver.findElement(By.xpath(CreateCustomerDialog.BTN_NEXT)));
		driver.findElement(By.xpath(CreateCustomerDialog.BTN_NEXT)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(CreateCustomerDialog.NAME2)).sendKeys(name);
		driver.findElement(By.xpath(CreateCustomerDialog.TITLE)).sendKeys(title);
		driver.findElement(By.xpath(CreateCustomerDialog.EMAIL)).sendKeys(email);
		driver.findElement(By.xpath(CreateCustomerDialog.BTN_SAVE)).click();
		Thread.sleep(1000);
		CommonUtility.highLightElement(driver, driver.findElement(By.xpath(CreateCustomerDialog.BTN_NEXT)));
		driver.findElement(By.xpath(CreateCustomerDialog.BTN_NEXT)).click();
		Thread.sleep(1000);		
		CreateCustomerDialog.setHostServer(driver, hostServerTyp);
		driver.findElement(By.xpath(CreateCustomerDialog.HOST_URL)).sendKeys(url);
		driver.findElement(By.xpath(CreateCustomerDialog.HOST_USR)).sendKeys(usr);
		driver.findElement(By.xpath(CreateCustomerDialog.HOST_PWD)).sendKeys(pwd);
		driver.findElement(By.xpath(CreateCustomerDialog.CONFIRM_PWD)).sendKeys(confirmPwd);
		CommonUtility.highLightElement(driver, driver.findElement(By.xpath(CreateCustomerDialog.BTN_CREATE)));
		driver.findElement(By.xpath(CreateCustomerDialog.BTN_CREATE)).click();
		Thread.sleep(3000);
		if (driver.findElement(By.xpath(CreateCustomerDialog.DUPLICATE_TENTANT_ALERT)).isDisplayed() == true){
			CommonUtility.highLightElement(driver, driver.findElement(By.xpath(CreateCustomerDialog.BTN_CANCEL)));
			driver.findElement(By.xpath(CreateCustomerDialog.BTN_CANCEL)).click();
		}
    }
	 
	public static void verifyTenant(WebDriver driver, String tenantNm, ExtentTest extentTest) throws Exception {
		searchTenant(driver, tenantNm);
		WebElement tenantTable = driver.findElement(By.xpath(CustomerManagementPage.TENANT_HOLDER));
        List<WebElement> iterateTenantTable = tenantTable.findElements(By.tagName(ApplicationHtmlConstants.DIV));
        boolean tenantFound = false;
        for (int i = 0; i < iterateTenantTable.size(); i++) {
          String extractTenantNm = iterateTenantTable.get(i).getAttribute(ApplicationHtmlConstants.TITLE).toString();
	    	if (extractTenantNm.equals(tenantNm)) {
	    		CommonUtility.highLightElement(driver,iterateTenantTable.get(i));
	    		 tenantFound = true;
	            Assert.assertEquals(extractTenantNm, tenantNm);
	            extentTest.log(LogStatus.PASS, "SUCCESSFULLY ADDED TENANT  ["+tenantNm+"]");
	            break;
	        }
        }
        if (!tenantFound) {
            extentTest.log(LogStatus.FAIL, "UNABLE TO LOCATE TENANT  ["+tenantNm+"]");
        }
	}
}
