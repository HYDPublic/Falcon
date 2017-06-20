package com.kronos.udm.objrepo;

public class AddDevice {

    public static final String DIALOG = "//div[@class='modal-content']//div[contains(@class,'dialog-window dialog-window-fixed ng-scope ui-draggable')]";
    // Create page
    public static final String DEVICE_ID = "//input[@id='id']";
    public static final String DEVICE_NM = "//input[@id='name']";
    public static final String NEXT_BTN = "//button[@id='btn_addDeviceNext']";
    public static final String CANCEL_BTN = "//div[@class='dialog-buttons-all']//button[@id='btn_cancel']";

    public static final String CHOOSE_DEVICE = "//a[@id='btn_showAddDeviceDialog']";
    public static final String COMMUNICATION_TYPE = "//select[@id='comm']";
    public static final String IPADDRESS1 = "//input[@id='ipAddress1']";
    public static final String IPADDRESS2 = "//input[@id='ipAddress2']";
    public static final String IPADDRESSV6 = "//input[@id='hstname']";
    public static final String TIMEZONE = "//select[@id='timezone']";

    //CLOSE DIALOG
    public static final String CLOSE_DIALOG = "//div[@class='modal-header']//button[contains(@id,'lnk_topClose')]";
    // Contact page
    public static final String SEC_DEVICE_NM = "//input[@id='kr_p_name']";
    public static final String TITLE = "//input[@id='kr_p_title']";
    public static final String EMAIL = "//input[@id='contactType-0']";
    //	public static String createBtn="//button[@class='udm-button-panel__button--primary ng-binding']";
    public static final String CREATE_BTN = "//div[@class='dialog-buttons-all']//button[contains(@id,'create')]";
}