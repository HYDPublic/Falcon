package com.kronos.udm.objrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class CreateCustomerDialog {
	//CUSTOMER INFO
	public static final String NAME 		= "//form[@name='tenantForm']//input[@id='name']";
	public static final String SOLUTION_ID	= "//form[@name='tenantForm']//input[@id='solutionId']";
	public static void setEnvTyp(WebDriver driver, String envTyp ){
		WebElement selectEnv = driver.findElement(By.id("tenantTypeId"));
		Select dropdown = new Select(selectEnv);
		dropdown.selectByVisibleText(envTyp);
	}
	public static final String TENANT_ID	= "//form[@name='tenantForm']//input[@id='idName']";
	//CONTACTS
	public static final String NAME2 		= "//input[@id='kr_p_name']";
	public static final String TITLE 		= "//input[@id='kr_p_title']";
	public static final String EMAIL 		= "//input[@id='contactType-0' and @type='email']";
	public static final String HOME_PHONE 	= "//input[@id='contactType-1']";
	public static final String BTN_SAVE 	= "//div[@class='dialog-buttons']//button[@id='btn_createContact']";
	public static final String BTN_NEXT 	= "//button[@id='btn_nextTenant']";
	//LINK TO TIMEKEEPING
	public static void setHostServer(WebDriver driver, String hostServerTyp ){
		WebElement selectHostServer = driver.findElement(By.id("hostServerType"));
		Select dropdown = new Select(selectHostServer);
		dropdown.selectByVisibleText(hostServerTyp);
	}
	public static final String HOST_URL 		= "//input[@id='hostUrl']";
	public static final String HOST_USR			= "//input[@id='hostUser']";
	public static final String HOST_PWD 		= "//input[@id='hostPassword']";
	public static final String CONFIRM_PWD		= "//input[@id='confirmHostPassword']";
	public static final String BTN_CREATE 		= "//button[@id='btn_createTenant']";
	
	public static final String BTN_CANCEL 		= "//button[@id='btn_cancelTenant']";
	public static final String CLOSE_DIALOG 	= "//button[@id='lnk_topClose']";
	public static final String DUPLICATE_TENTANT_ALERT = "//div[@class='modal-body']//div[@class='message-error']//span[@class='error-msg-txt']";
}