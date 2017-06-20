package com.kronos.udm.objrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AutoRegenerationConfigurationDialog {
    public static final String CHECKBOX_AUTO_REGISTRATION 	= "//input[contains(@name,'udm-tenant-attribute-auto-register')]";
    public static final String DEVICE_PWD					= "//input[contains(@name,'udm-tenant-attribute-password')]";
    public static final String CONFIRM_PWD					= "//input[contains(@name,'udm-tenant-attribute-pwd-confirm')]";
    public static final String CHECKBOX_ALLOW_AUTO_CONFIG 	= "//input[contains(@name,'udm-tenant-attribute-auto-config')]";
    public static final String BTN_SAVE						= "//div[contains(@class,'dialog-window udm-modal-panel')]//button[@id='btn_save']";
    public static final String BTN_CANCEL					= "//div[contains(@class,'dialog-window udm-modal-panel')]//button[@id='btn_cancel']";
    public static final String COMBO_TIMEZONE				= "//select[contains(@name,'udm-tenant-attribute-timezone')]";
    public static final String COMBO_K4500		= "//select[contains(@class,'udm-grid__column-select ng-pristine ng-untouched ng-valid') and contains(@ng-init,'0')]";
    public static final String COMBO_KINTOUCH	= "//select[contains(@class,'udm-grid__column-select ng-pristine ng-untouched ng-valid') and contains(@ng-init,'1')]";
    
   
    public static void selectProfile (WebDriver driver, String profileForKronosInTouch){
    	boolean elementFound = false;
    	int i =0; 
	Select selectProfile = new Select(driver.findElement(By.xpath(AutoRegenerationConfigurationDialog.COMBO_KINTOUCH)));
		while (!elementFound){
			selectProfile.selectByIndex(i);
	    	String extractElement = selectProfile.getAllSelectedOptions().get(0).getText();
			if (extractElement.equalsIgnoreCase(profileForKronosInTouch)) {
				elementFound = true;
				break;
			}
			i++;
		} 
    }
}




