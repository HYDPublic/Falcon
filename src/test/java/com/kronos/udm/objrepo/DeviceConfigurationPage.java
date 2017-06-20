package com.kronos.udm.objrepo;

public class DeviceConfigurationPage {

    //Generic Buttons on the Device Config Page and the Sub options
    public static final String FILTER_BTN = "//span[contains(@title,'Filter')]";
    public static final String CLICK_VIEW_BTN = "//div[contains(@class,'btn-group profile-view-changer')]//span[contains(@class,'udm-icon-caret-down')]";
    public static final String SELECT_PROFILE = "//div[contains(@class,'btn-group profile-view-changer open')]//ul//li//a[contains(text(),'Profile')]";
    public static final String NEW_BTN = "//div[@class='btn-group']//span[@class='udm-icon-caret-down']";
    public static final String Add_NEW_PROFILE = "//li[@role='menuitem']//a[@title='Profile']";
    public static final String ADD_NEW_DEVICE = "//li[@role='menuitem']//a[@title='Device']";

    public static final String ACTION_APPLY_PROFILE = "//div[@id='action_actions']//a[contains(@title,'Apply Profile')]";

    public static final String SET_SEARCH_CRITERIA_ONDEVICENM = "//div[contains(@class,'ui-grid-filter-container ng-scope')]//div//input[contains(@aria-label,'Filter for column')]";
    public static final String SET_SEARCH_CRITERIA_PROFILENM = "//div[contains(@class,'ui-grid-coluiGrid-0035')]//div[@ui-grid-filter='']/div/div/input";
    public static final String SET_SEARCH_CRITERAI_APPCONFIGNM = "//div[contains(@class,'ui-grid-coluiGrid-0038')]//div[@ui-grid-filter='']/div/div/input";
    public static final String SET_SEARCH_CRITERIACOMM_SETTINGNM = "//div[@id='profileConfigTable']//div[contains(@class,'ui-grid-coluiGrid-005H')]//div[@ui-grid-filter='']/div/div/input";
//    public static final String ALL_APP_CONGIG_TITLES = "//div[@id='configTable']//div[contains(@id,'-grid-container') and contains(@container-id,'body') and contains(@col-container-name,'body')]//div[@class='ui-grid-canvas']";
    public static final String ALL_APP_CONGIG_TITLES = "//div[@id='configTable']//div[contains(@id,'-grid-container') and contains(@container-id,'body')]//div[@class='ui-grid-canvas']";
    public static final String SEARCH_PROFILE = "//input[contains(@aria-label,'Filter for column')]";

    public static final String VERIFY_SELECTED_TAG = "//div[contains(@class,'tags')]//span[@title='&']";
    public static final String CHECK_DEVICE = "//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents']//div";
    public static final String CLICK_SOFT_KEY = "//div[contains(@id,'-0-uiGrid-004M-cell')]//div";

    public static final String SELECT_SEARCHED_PROFILE = "//input[contains(@aria-label,'Filter for column')]";
    public static final String SELECT_SEARCHED_RESULTS = "//div[@id='profileConfigTable']//div[@class='ui-grid-canvas']//div[contains(@id,'-0-uiGrid')]//div//span[@class='ng-binding']";
    public static final String CHECK_PROFILE = "//div[@id='profileConfigTable']//div[contains(@id,'0-uiGrid-')]//div//div//div[contains(@ng-click,'event')]";
    public static final String CLICK_ADD_COMMSETTINGS = "//div[@id='profileConfigTable']//div[contains(@id,'-0-uiGrid-')]//div[contains(.,'+')]";
    public static final String sTemp = "\"'body'\"";
    public static final String COMM_SETTINGTITLE_HOLDER = "//div[@id='profileConfigTable']//div[contains(@id,'-grid-container') and @container-id="+sTemp+"]//div[@class='ui-grid-canvas']";
    
    
    public static final String ALERT_MESSAGE = "//div[contains(@class,'message-container alert-wrapper alert')]/div";
    public static final String DIV_ID_PROFILE_CONFIG_TABLE_DIV_CLASS_UI_GRID_CANVAS = "//div[@id='profileConfigTable']//div[@class='ui-grid-canvas']";

    
    public static final String DIV_ID_PROFILE_CONFIG_TABLE_DIV_CONTAINS_ID_GRID_CONTAINER_AND_CONTAINERID_BODY_DIV_ROLE_AND_UIGRIDROW = "//div[@id='profileConfigTable']//div[contains(@id,'-grid-container') and @container-id="+sTemp+"]//div[@role='row' and @ui-grid-row='row']";
    
    public static final String DIV_CLASS_UI_GRID_CANVAS = "//div[@class='ui-grid-canvas']";
    public static final String DIV_ID_CONFIG_TABLE_DIV_CLASS_UI_GRID_CANVAS = "//div[@id='configTable']//div[@class='ui-grid-canvas']";
    
    public static final String DEFAULT_TRANSACTION_TEMPLATE_HOLDER = "//div[@id='configTable']//div[@container-id="+sTemp+"]//div[contains(@class,'ui-grid-canvas')]//div[@class='ng-isolate-scope' and @role='row']";
}