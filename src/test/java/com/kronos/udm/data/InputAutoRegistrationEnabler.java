package com.kronos.udm.data;

import java.io.FileInputStream;
import com.kronos.udm.utils.AppConstants;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InputAutoRegistrationEnabler {
    String customerNm;
    String pwd; 						
    String confirmPwd; 					
    String profileForKronos4500; 		
    String profileForKronosInTouch; 
    String timeZone;
    String usrNmUdm;
    String pwdUdm;
    String usrNmSdm;
    String pwdSdm;
    String udmUrl;
    String sdmUrl;
    String browserTyp;

    public InputAutoRegistrationEnabler() {
        FileInputStream fs = null;
        XSSFWorkbook wb = null;
        try {
            fs = new FileInputStream(AppConstants.UDMREGRESSION_PROPERTY);
            wb = new XSSFWorkbook(fs);
            XSSFSheet IARETab = wb.getSheet("IARETab");
            customerNm 					= IARETab.getRow(1).getCell(0).getStringCellValue();
            pwd 						= IARETab.getRow(1).getCell(1).getStringCellValue();
            confirmPwd 					= IARETab.getRow(1).getCell(2).getStringCellValue();
            profileForKronos4500 		= IARETab.getRow(1).getCell(3).getStringCellValue();
            profileForKronosInTouch 	= IARETab.getRow(1).getCell(4).getStringCellValue();
            timeZone					= IARETab.getRow(1).getCell(5).getStringCellValue();
            usrNmUdm 					= IARETab.getRow(1).getCell(6).getStringCellValue();
            pwdUdm 						= IARETab.getRow(1).getCell(7).getStringCellValue();
            usrNmSdm 					= IARETab.getRow(1).getCell(8).getStringCellValue();
            pwdSdm 						= IARETab.getRow(1).getCell(9).getStringCellValue();
            udmUrl 						= IARETab.getRow(1).getCell(10).getStringCellValue();
            sdmUrl 						= IARETab.getRow(1).getCell(11).getStringCellValue();
            browserTyp 					= IARETab.getRow(1).getCell(12).getStringCellValue();
        } catch (FileNotFoundException e) {
           
            e.printStackTrace();
        } catch (IOException e) {
         
            e.printStackTrace();
        }

    }

    public String getCustomerNm() {
        return customerNm;
    }

    public void setCustomerNm(String customerNm) {
        this.customerNm = customerNm;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }
    
    public String getProfileForKronos4500() {
        return profileForKronos4500;
    }

    public void setProfileForKronos4500(String profileForKronos4500) {
        this.profileForKronos4500 = profileForKronos4500;
    }
    
    public String getProfileForKronosInTouch() {
        return profileForKronosInTouch;
    }

    public void setProfileForKronosInTouch(String profileForKronosInTouch) {
        this.profileForKronosInTouch = profileForKronosInTouch;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
    
    public String getUsrNmUdm() {
        return usrNmUdm;
    }

    public void setUsrNmUdm(String usrNmUdm) {
        this.usrNmUdm = usrNmUdm;
    }

    public String getPwdUdm() {
        return pwdUdm;
    }

    public void setPwdUdm(String pwdUdm) {
        this.pwdUdm = pwdUdm;
    }

    public String getUsrNmSdm() {
        return usrNmSdm;
    }

    public void setUsrNmSdm(String usrNmSdm) {
        this.usrNmSdm = usrNmSdm;
    }

    public String getPwdSdm() {
        return pwdSdm;
    }

    public void setPwdSdm(String pwdSdm) {
        this.pwdSdm = pwdSdm;
    }

    public String getUdmUrl() {
        return udmUrl;
    }

    public void setUdmUrl(String udmUrl) {
        this.udmUrl = udmUrl;
    }

    public String getSdmUrl() {
        return sdmUrl;
    }

    public void setSdmUrl(String sdmUrl) {
        this.sdmUrl = sdmUrl;
    }

    public String getBrowserTyp() {
        return browserTyp;
    }

    public void setbBowserTyp(String browserTyp) {
        this.browserTyp = browserTyp;
    }
}
