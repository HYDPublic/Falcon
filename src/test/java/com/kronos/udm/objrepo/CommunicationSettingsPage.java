package com.kronos.udm.objrepo;

public class CommunicationSettingsPage {

    public static final String SETSEARCHCRITERIAONDEVICE_NM = "//div[contains(@class,'ui-grid-filter-container ng-scope')]//div//input[contains(@aria-label,'Filter for column')]";
    static String sTemp = "\"'body'\"";
    public static final String ADDCOMMONSETTINGS = "//div[@id='profileConfigTable']//div[contains(@id,'grid-container') and @container-id="+sTemp+"]//div[@row-render-index='rowRenderIndex']";
    public static final String NAME = "//div[@class='udm-form__row']//input[contains(@type,'text')]";
    public static final String RADIO_BTNHTTP = "//input[@name='udm-attribute-communication-protocol' and @value='HTTP']";

    public static final String PRIMARYSERVER = "//input[@name='udm-attribute-primary-server']";
    public static final String PRIMARYSERVERINSTANCEID = "//input[@name='udm-attribute-instanceId']";
    public static final String PORT = "//input[@name='udm-attribute-serverPort']";

    public static final String SAVE_BTN = "//button[contains(@title,'Save')]";
    public static final String CANCEL_BTN = "//button[contains(@title,'Cancel')]";
    public static final String OK_BTN = "//div[@class='dialog-buttons']//button[@title='OK']";
}
