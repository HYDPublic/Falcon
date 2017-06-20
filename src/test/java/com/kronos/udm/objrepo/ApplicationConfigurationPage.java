package com.kronos.udm.objrepo;

public class ApplicationConfigurationPage {

    public static final String APP_CONFIG_NAME				= "//div[@class='udm-form__row']//input[contains(@type,'text')]";
    //Radio Buttons to check if single profile or Multiple Profiles are selected
    public static final String RADIO_APP_BTN_CONFIG = "//div[contains(@class,'udm-form float--left margin_left')]//div[@class='udm-form__row']";
    public static final String SINGLE_DEVICE_SELECTION 		= "//span[contains(text(),'&')]";
    public static final String DEVICE_SELECTION_MULTIPLE	= "//span[contains(text(),'All devices and profiles using this template')]";
    //Different Tabs
    public static final String TAB_APPLICATION_OPTIONS	= "//a[contains(.,'Application Options')]";
    
    public static final String EMP_NUM_SCHEDULE 		= "//input[contains(@name,'udm-attribute-emp-num-schedules')]";
    public static final String TAB_LABOR_ASSIGNMENTS 	= "//a[contains(.,'Labor Assignments')]";
    public static final String TAB_LABOR_SETTINGS		= "//a[contains(.,'Labor Settings')]";
    public static final String TAB_LOCATION_OR_ALIAS_TRANSFER_SETTINGS = "//a[contains(.,'Location or Alias Transfer Settings')]";
    public static final String TAB_WORK_RULE_ASSIGNMENTS = "//a[contains(.,'Work Rule Assignments')]";

    public static final String CANCEL_BTN	= "//div[contains(@class,'udm-buttonp-panel float--right')]//button[contains(@title,'Cancel')]";
    public static final String SAVE_BTN		= "//div[contains(@class,'udm-buttonp-panel float--right')]//button[contains(@title,'Save')]";

    public static final String PROFILE_NM	= "//div[contains(@class,'udm-clock-panel ng-scope')]/span//span[contains(@class,'header-title')]";
    //confirmation Dialog
    public static final String OK_BTN		= "//div[@class='dialog-buttons']//button[contains(@title,'OK')]";

    //Warning Alert
    public static final String WARNING_TEXT	= "//div[contains(@class,'message-container alert-wrapper alert')]//div[@class='text-message']";

    public static final String SELECT_EXISTING_TEMPLATE	= "//a[contains(@id,'btn_selectExistingTemplate')]";
    public static final String SELECT_ANOTHER_TEMPLATE	= "//a[contains(@id,'btn_selectAnotherTemplate')]";
    public static final String REMOVE_TEMPLATE 			= "//a[contains(@id,'btn_removeTemplate')]";

    public static final String DIV_CONTAINS_CLASS_UDM_FORM_FLOAT_LEFT_MARGIN_LEFT_85PX_DIV_CLASS_UDM_FORM_ROW = "//div[contains(@class,'udm-form float--left margin_left--85px')]//div[@class='udm-form__row']";
    public static final String DIV_CLASS_GRID_CELL 			= "//div[@class='grid-cell ']";
    public static final String PROFILE_TITLE_HOLDER 		= "//div[@class='ui-grid-canvas']";
    public static final String DIV_CLASS_UI_GRID_CANVAS1	= "//div[@class='ui-grid-canvas']";
}
