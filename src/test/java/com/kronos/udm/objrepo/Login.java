package com.kronos.udm.objrepo;

public class Login {
	public static final String LABEL_USRNM		= "//div[@class='group-field-block']/label";
	
    public static final String USERNAME 		= "//input[contains(@name,'0') and @type='text']";
    public static final String PASSWORD 		= "//input[contains(@type,'password')]";
    public static final String SUBMIT_BTN 		= "//input[@type='submit']";
    public static final String ERROR_MESSAGE	= "//div[@id='messages']//div[@class='errorMessage']//span[@class='error-inner']";
    
}