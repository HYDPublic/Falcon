package com.kronos.udm.objrepo;

public class SubFilterMenu {

    public static final String FILTER_BTN 		= "//span[contains(@title,'Filter')]";
    public static final String CLOSE_PANNEL_BTN = "//span[contains(@class,'icon-k-collapse-left')]";
    public static final String VIEW_BTN 		= "//div[contains(@class,'btn-group profile-view-changer')]//span[contains(@class,'udm-icon-caret-down')]";
    public static final String VIEW_DEVICE 		= "//div[contains(@class,'btn-group profile-view-changer open')]//ul//li//a[contains(text(),'Device')]";
    public static final String VIEW_PROFILE 	= "//div[contains(@class,'btn-group profile-view-changer open')]//ul//li//a[contains(text(),'Profile')]";
    
    public static final String ACTION_BTN 		= "//span[contains(@title,'Actions')]";
    public static final String OPTION_DELETE_PROFILE = "//a[contains(@title,'Delete Profiles & Templates')]";
    public static final String OPTION_AUTO_REG_CONFIG = "//a[contains(@title,'Auto Registration Configuration')]";
    
    public static final String SHARE_BTN 		= "//span[contains(text(),'Share')]";
    public static final String DIV_ID_DEVICE_DASHBOARD_GRID_DIV_CONTAINS_UI_GRID_FILTER = "//div[@id='deviceDashboardGrid']//div[contains(@ui-grid-filter,'')]";
    public static final String DIV_ID_PROFILE_CONFIG_TABLE_DIV_UI_GRID_FILTER = "//div[@id='profileConfigTable']//div[@ui-grid-filter='']";
    static String sTemp = "\"'body'\"";    
    public static final String DEFAULT_COMM_SETTING_FILTER_LOADER = "//div[@id='profileConfigTable']//div[@container-id="+sTemp+"]//div[contains(@ui-grid-filter,'')]";
    public static final String DIV_CONTAINS_UI_GRID_FILTER = "//div[contains(@ui-grid-filter,'')]";
    public static final String DIV_ID_CONFIG_TABLE_DIV_CONTAINS_ID_GRID_CONTAINER_AND_CONTAINER_ID = "//div[@id='configTable']//div[contains(@id,'grid-container') and @container-id=";
    
    public static final String DIV_CLASS_UI_GRID_HEADER_CELL_ROW_AND_ROLE_ROW = "//div[@class='ui-grid-header-cell-row'  and  @role='row']";
    public static final String DIV_ID_CONFIG_TABLE_DIV_CLASS_UI_GRID_HEADER_CELL_ROW_AND_ROLE_ROW = "//div[@id='configTable']//div[@class='ui-grid-header-cell-row'  and  @role='row']";
    
    public static final String DEFAULT_APP_CONFIG_FILTER_LOADER_ON_PROFILE_PAGE = "//div[@id='profileConfigTable']//div[@container-id="+sTemp+"]//div[contains(@ui-grid-filter,'')]";
    // BELOW XPATH CAN BE USED FOR ALL OTHER FILTER ON DEVICE CONFIG PAGE
    // '0' index for COMM SETTING
	// '1' index for APP CONFIG
	// '2' index for DEFAULT TRANSACTION
	// '3' index for SOFT KEY
    public static final String DEFAULT_TRANSACTION_TEMPLATE_FILTER_LOADER = "//div[@id='configTable']//div[@container-id="+sTemp+"]//div[contains(@ui-grid-filter,'')]";
    
    																		
 
}