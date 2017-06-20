package com.kronos.udm.utils;


import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import com.kronos.udm.objrepo.CustomerManagementPage;
import com.kronos.udm.objrepo.Login;
import com.kronos.udm.objrepo.Logout;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

public class CommonUtility {

    //Performs and Controls Browser Launch
    public static WebDriver openBrowser(String browser) throws Exception{
    	Properties prop = UtilityFunctions.ReadPropertyFile(AppConstants.ENV_PROPERTY);
        WebDriver driver = null;
        try {
	        if (browser.equalsIgnoreCase("mozilla") || browser.equalsIgnoreCase("ff")) {
	            //System.setProperty(AppConstants.WEBDRIVER_GECKODRIVER, AppConstants.GECKODRIVER);
	        	System.setProperty(AppConstants.WEBDRIVER_GECKODRIVER, prop.getProperty(AppConstants.DRIVER_PATH)+AppConstants.GECKODRIVER);
	            //LAUNCH FF BROWSER INTO 'PRIVATE' mode
	        	FirefoxProfile firefoxProfile = new FirefoxProfile();
	            firefoxProfile.setPreference("browser.private.browsing.autostart",true);
	        	
	    		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	            capabilities.setCapability("marionette", true);
	            driver = new FirefoxDriver(capabilities);
	            driver.manage().window().maximize();
	        }
	        if (browser.equalsIgnoreCase("ie") || browser.equalsIgnoreCase("internetexplorer")) {
	            //File driverFileIE = new File(AppConstants.IEDRIVER);
	            System.setProperty(AppConstants.WEBDRIVER_IEDRIVER, prop.getProperty(AppConstants.DRIVER_PATH)+AppConstants.IEDRIVER);
	            //LAUNCH IE BROWSER INTO 'PRIVATE' mode
	            /*DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	            capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true); 
	            capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");*/
	            driver = new InternetExplorerDriver();
	            driver.manage().window().maximize();
	        }
	        if (browser.equalsIgnoreCase("ch")|| browser.equalsIgnoreCase("chrome")) {
	            //File driverFileChrome = new File(AppConstants.CHROMEDRIVER);
	            System.setProperty(AppConstants.WEBDRIVER_CHROME, prop.getProperty(AppConstants.DRIVER_PATH)+AppConstants.CHROMEDRIVER);
	            ChromeOptions options = new ChromeOptions();
	            //SUPRESS THE DEBUG LOG ON CONSOLE
	            System.setProperty("webdriver.chrome.silentOutput", "true");
	            // LAUNCH CHROME BROWSER INTO 'INCOGNITO' mode
	            options.addArguments("incognito");
	            options.addArguments("start-maximized");
	            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	            driver = new ChromeDriver(capabilities);
	        }
        
        }
        catch (Exception e){
        	System.out.println("UNABLE TO LAUNCH THE BROWSER");
        	throw e;
        }
        
        driver.manage().deleteAllCookies();
        return driver;
    }
    
    //Performs Login action
    public static void performLogin(WebDriver driver, String usrnm, String pwd, String applicationUrl) throws Exception {
    	driver.get(applicationUrl);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(Login.USERNAME)));
        driver.findElement(By.xpath(Login.USERNAME)).sendKeys(usrnm);
        CommonUtility.highLightElement(driver, driver.findElement(By.xpath(Login.PASSWORD)));
        driver.findElement(By.xpath(Login.PASSWORD)).sendKeys(pwd);
        driver.findElement(By.xpath(Login.SUBMIT_BTN)).click();
        Thread.sleep(1000);
        try {
        	String extractTitle = driver.findElement(By.xpath(CustomerManagementPage.PAGE_TITLE)).getText();
        	Assert.assertEquals(extractTitle, AppConstants.PAGE_TITLE);
        }
        catch (AssertionError error){
        	System.out.println("AN INCORRECT USERNAME OR PASSWORD WAS ENTERED.PLEASE TRY AGAIN");
        }
    }

    // Performs Logout action
    public static void performSignout(WebDriver driver, ExtentReports extentReport, ExtentTest extentTest) throws InterruptedException {
        driver.findElement(By.xpath(Logout.MENU_TOGGLE_BTN)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(Logout.SIGN_OUT_BTN)).click();
        Thread.sleep(500);
        extentReport.endTest(extentTest);
        extentReport.flush();
        extentReport.close();
        driver.close();
    }

    // HIGHLIGHTING HTML objects
    public static void highLightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: Aqua; border: 5px solid MediumPurple;');", element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        js.executeScript("arguments[0].setAttribute('style','border: solid 5px gray');", element);
    }
    
    
   /* public static void setFocus(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(Document.getE
        js.executeScript ("document.getElementById(element).focus()"); 
        
    }*/
}
