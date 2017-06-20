package com.kronos.udm.utils;

public class AppConstants {
	public static final String USRNM 			= "usrnm";
	public static final String PWD 				= "pwd";
	public static final String ENV 				= "url";
	public static final String BROWSER_TYP 		= "browser";
	public static final String DRIVER_PATH 		= "driverFilePath";
	    
	public static final String GECKODRIVER 					= "geckodriver.exe";
    public static final String WEBDRIVER_GECKODRIVER		= "webdriver.gecko.driver";
    public static final String IEDRIVER 					= "IEDriverServer.exe";
    public static final String WEBDRIVER_IEDRIVER 			= "webdriver.ie.driver";
    public static final String CHROMEDRIVER 				= "chromedriver.exe";
    public static final String WEBDRIVER_CHROME 			= "webdriver.chrome.driver";
    public static final String OBJECTREPO_PROPERTY			= "src\\test\\resources\\ObjectRepo.properties";
    public static final String ENV_PROPERTY 				= "src\\test\\resources\\Env.properties";
    
    public static final String UDMREGRESSION_PROPERTY		= "src\\test\\resources\\UdmRegressionTestInput.xlsx";
    public static final String REPORT_CREATE_TENANT			= "reports\\CreateTenant.html";
    public static final String REPORT_ALERT_SHAREDPROFILE	= "reports\\AlertOnAssigningSharedProfileToSingleDeviceValidator.html";
    public static final String REPORT_ALERT_SHAREDTEMPLATE	= "reports\\AlertOnAssigningSharedTemplateToSingleProfileValidator.html";
    public static final String REPORT_ASSIGN_SHARED			= "reports\\AssignAppConfigTemplateToSharedProfiles.html";
    public static final String REPORT_NM_COMMON_SETTINGS	= "reports\\CreateCommSettings.html";
    public static final String REPORT_CREATEDEVICE			= "reports\\CreateDevice.html";
    public static final String REPORT_CREATE_GROUP			= "reports\\CreateGroup.html";
    public static final String REPORT_CREATE_PROFILE		= "reports\\ProfileCreation.html";
    public static final String REPORT_ASSIGN_MULTIPLE		= "reports\\AssignProfileToMultipleDevices.html";
    public static final String DIALOGUE_SHAREDPROFILE_ASSIGN = "reports\\DialogLaunchOnAssigingSharedProfileToSingleDeviceValidator.html";
    public static final String REPORT_ASSIGN_SINGLE			= "reports\\AssignProfileToSingleDevice.html";
    public static final String REPORTS_SHARED_SINGLE		= "reports\\AssignSharedProfileBetweenDevicesToSingleDevice.html";    
    public static final String REPORTS_ASSIGN_TEMPLATE_TOSINGLE = "reports\\AssignSharedTemplateToSingleProfile.html";
    public static final String REPORTS_TEMPLATE_MULTIPLE 	= "reports\\AssignSingleTemplateToMultipleProfiles.html";
    public static final String REPORT_CREATETAG				= "reports\\CreateTag.html";
    public static final String REPORT_CREATE_SOFTKEYSCHEDULE = "reports\\CreateSoftKeySchedule.html";
    public static final String REPORTS_UPDATE_APP_CONFIG_TEMPLATE = "reports\\UpdateSharedAppConfigTemplate.html";
    public static final String REPORTS_NM_AUTO_CONFIG 			= "reports\\EnableAutoRegistration.html";
    public static final String REPORT_TRANSACTION_TEMPLATE	= "reports\\DefaultTransactionsTemplate.html";
    
    public static final String LABEL_USRNM 					= "User Name:";
    public static final String DEVICE 						= "Device";
    public static final String CONFIGURATION				= "Configuration";
    public static final String PAGE_TITLE					= "Customer Management";
    public static final String ERROR_MG						= "An incorrect username or password was entered.Please try again";

    public static final String APPLY_APPCONFIG_SAME_PROFILE = "Apply AppConfig Template To Device Sharing Same Profile";
    public static final String VERIFY_APP_ASSIGNMENT 		= "VERIFY APP CONFIG ASSIGNMENT";
    
    public static final String CREATE_COMM 					= "Create Communication Setting";
    public static final String VERIFY_COMM			 		= "VERIFY COMMUNICATION SETTINGS";
    
    public static final String CREATE_DEVICE 				= "Create Device";
    public static final String VERIFY_DEVICE 				= "VERIFY CREATED DEVICE";
    
    public static final String VALIDATE_ON_REASSIGN_PROFILE = "Validate Dialog Launch 'New template and profile name' on Reassigning Shared Profile ";
    public static final String VERIFY_LAUNCH_ERRORMSG = "VERIFY DIALOG LAUNCH AND ERROR MESSAGE";
    
    public static final String TAG = "tag";
    public static final String CREATE_TAG = "Create Tag";
    public static final String VERIFY_TAG = "VERIFY CREATED TAG";
    
    
    public static final String ENABLE_AUTO_CONFIG = "Enable Auto COnfig";
    public static final String VERIFY_CONFIG = "VERIFY AUTO CONFIG";
    
    public static final String CREATE_TENANT = "Create Tenant";
    public static final String VERIFY_TENANT = "Verify Tenant";
    
    public static final String GROUP = "group";
    public static final String CREATE_GROUP = "Create Group";
    public static final String VERIFY_GROUP = "VERIFY CREATED GROUP";
    
    public static final String CREATE_PROFILE = "Create Profile";
    public static final String VERIFY_CREATE_PROFILE = "VERIFY CREATED PROFILE";
    
    public static final String ASSIGN_PROFILE_MULTIPLE = "Assign Profile To Multiple Devices";
    public static final String VERIFY_ASSIGNMENT_MULTIPLE = "VERIFY PROFILE ASSIGNMENT FOR MULTIPE DEVICES";
    
    public static final String ASSIGN_PROFILE_SINGLE = "Assign Profile To Single Device ";
    public static final String VERIFY_ASSIGN_SINGLE = "VERIFY ASSIGNED PROFILE";
    
    public static final String ASSIGN_SHARED_SINGLE = "Assign Shared Profiles Between Devices To Single Device";
    public static final String VERIFY_APPLIED_PROFILE = "VERIFY NEWLY APPLIED PROFILE";
    
    public static final String SHARED_TEMPLATE_SINGLE = "Shared Template Assignment To Single Profile ";
    public static final String VERIFY_TEMPLATE_ASSIGNMENT = "VERIFY TEMPLATE ASSIGNMENT TO SINGLE PROFILE";
    
    public static final String TEMPLATE_ASSIGN_MULTIPLE = "Single Template Assignment To Multiple Profiles ";
    public static final String VERIFY_TEMPASSIGNMENT_MULTIPLE = "VERIFY TEMPLATE ASSIGNMENT TO MULTIPLE PROFILES";

    public static final String REASSIGNING_VALIDATE_ALERT_ON_PROFILE = "Validate Alert On Reassigning Shared Template To Single Profile";
    public static final String REASSIGNING_VALIDATE_ALERT = "Validate Alert On Reassigning Shared Profile Between Devices To Single Device";
    public static final String VALIDATE_ALERT = "VALIDATE ALERT";
    
    public static final String UPDATE_SHARED_APP_CONFIG_TEMPLATE = "Verify All Profile Using this Option is retained After Saving The Updates";
    public static final String VALIDATE_ALL_RADIO_OPTION = "VERIFY UPDATED APP CONFIG TEMPLATE";
    
    public static final String CREATE_TRANS_TEMPLATE = "Create Default Transcation Template ";
    public static final String VERIFY_TRANS_TEMPLATE = "VERIFY TRANSACTION TEMPLATE";
    
    public static final String VERIFY_SUCCESSFULLY_ADDED = "Successfully added.";
    
}
