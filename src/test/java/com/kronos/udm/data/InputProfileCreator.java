package com.kronos.udm.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.kronos.udm.utils.AppConstants;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InputProfileCreator {
    String customerNm;
    String profileNm;
    String noOfProfile;
    String deviceNm;
    String usrNmUdm;
    String pwdUdm;
    String usrNmSdm;
    String pwdSdm;
    String udmUrl;
    String sdmUrl;
    String primaryServer;
    String primaryServerInstanceId;
    String port;
    String browserTyp;

    public InputProfileCreator() {
        FileInputStream fs = null;
        XSSFWorkbook wb = null;
        try {
            fs = new FileInputStream(AppConstants.UDMREGRESSION_PROPERTY);
            wb = new XSSFWorkbook(fs);
            //XSSFSheet PCTab = wb.getSheetAt(2);
            XSSFSheet PCTab = wb.getSheet("PCTab");
            customerNm 	= PCTab.getRow(1).getCell(0).getStringCellValue();
            profileNm 	= PCTab.getRow(1).getCell(1).getStringCellValue();
            noOfProfile = PCTab.getRow(1).getCell(2).getStringCellValue();
            deviceNm 	= PCTab.getRow(1).getCell(3).getStringCellValue();
            usrNmUdm 	= PCTab.getRow(1).getCell(4).getStringCellValue();
            pwdUdm 		= PCTab.getRow(1).getCell(5).getStringCellValue();
            usrNmSdm 	= PCTab.getRow(1).getCell(6).getStringCellValue();
            pwdSdm 		= PCTab.getRow(1).getCell(7).getStringCellValue();
            udmUrl 		= PCTab.getRow(1).getCell(8).getStringCellValue();
            sdmUrl 		= PCTab.getRow(1).getCell(9).getStringCellValue();
            browserTyp = PCTab.getRow(1).getCell(10).getStringCellValue();
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

    public String getNoOfProfile() {
        return noOfProfile;
    }

    public void setNoOfProfile(String noOfProfile) {
        this.noOfProfile = noOfProfile;
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