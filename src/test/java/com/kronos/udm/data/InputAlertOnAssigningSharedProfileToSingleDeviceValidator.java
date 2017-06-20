package com.kronos.udm.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.kronos.udm.utils.AppConstants;

public class InputAlertOnAssigningSharedProfileToSingleDeviceValidator {

    String customerNm;
    String profileNm;
    String profileNewNm;
    String appConfigNm;
    String deviceNm;
    String usrNmUdm;
    String pwdUdm;
    String usrNmSdm;
    String pwdSdm;
    String udmUrl;
    String sdmUrl;
    String browserTyp;

    public InputAlertOnAssigningSharedProfileToSingleDeviceValidator() {
        FileInputStream fs = null;
        XSSFWorkbook wb = null;
        try {
            fs = new FileInputStream(AppConstants.UDMREGRESSION_PROPERTY);
            wb = new XSSFWorkbook(fs);
            // XSSFSheet AOASHPTSDVTab = wb.getSheetAt(8);
            XSSFSheet AOASHPTSDVTab = wb.getSheet("AOASHPTSDVTab");
            customerNm 		= AOASHPTSDVTab.getRow(1).getCell(0).getStringCellValue();
            profileNm 		= AOASHPTSDVTab.getRow(1).getCell(1).getStringCellValue();
            profileNewNm 	= AOASHPTSDVTab.getRow(1).getCell(2).getStringCellValue();
            appConfigNm 	= AOASHPTSDVTab.getRow(1).getCell(3).getStringCellValue();
            deviceNm 		= AOASHPTSDVTab.getRow(1).getCell(4).getStringCellValue();
            usrNmUdm 		= AOASHPTSDVTab.getRow(1).getCell(5).getStringCellValue();
            pwdUdm 			= AOASHPTSDVTab.getRow(1).getCell(6).getStringCellValue();
            usrNmSdm 		= AOASHPTSDVTab.getRow(1).getCell(7).getStringCellValue();
            pwdSdm 			= AOASHPTSDVTab.getRow(1).getCell(8).getStringCellValue();
            udmUrl 			= AOASHPTSDVTab.getRow(1).getCell(9).getStringCellValue();
            sdmUrl 			= AOASHPTSDVTab.getRow(1).getCell(10).getStringCellValue();
            browserTyp 		= AOASHPTSDVTab.getRow(1).getCell(11).getStringCellValue();
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

    public String getProfileNm() {
        return profileNm;
    }

    public void setProfileNm(String profileNm) {
        this.profileNm = profileNm;
    }

    public String getProfileNewNm() {
        return profileNewNm;
    }

    public void setProfileNewNm(String profileNewNm) {
        this.profileNewNm = profileNewNm;
    }

    //////
    public String getAppConfigNm() {
        return appConfigNm;
    }

    public void setAppConfigNm(String appConfigNm) {
        this.appConfigNm = appConfigNm;
    }

    public String getDeviceNm() {
        return deviceNm;
    }

    public void setDeviceNm(String deviceNm) {
        this.deviceNm = deviceNm;
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
