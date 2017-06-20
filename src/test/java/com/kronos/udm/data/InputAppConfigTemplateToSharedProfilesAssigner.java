package com.kronos.udm.data;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.kronos.udm.utils.AppConstants;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InputAppConfigTemplateToSharedProfilesAssigner {

    String customerNm;
    String profileNm;
    String appConfigNm;
    String deviceNm;
    String deviceNm2;
    String usrNmUdm;
    String pwdUdm;
    String usrNmSdm;
    String pwdSdm;
    String udmUrl;
    String sdmUrl;
    String browserTyp;

    public InputAppConfigTemplateToSharedProfilesAssigner() {
        FileInputStream fs = null;
        XSSFWorkbook wb = null;
        try {
            fs = new FileInputStream(AppConstants.UDMREGRESSION_PROPERTY);
            wb = new XSSFWorkbook(fs);
            //XSSFSheet AACTTSPTabTab = wb.getSheetAt(6);
            XSSFSheet AACTTSPTab = wb.getSheet("AACTTSPTab");
            customerNm 		= AACTTSPTab.getRow(1).getCell(0).getStringCellValue();
            profileNm 		= AACTTSPTab.getRow(1).getCell(1).getStringCellValue();
            appConfigNm 	= AACTTSPTab.getRow(1).getCell(2).getStringCellValue();
            deviceNm 		= AACTTSPTab.getRow(1).getCell(3).getStringCellValue();
            //cell.setCellType(Cell.CELL_TYPE_STRING)
            deviceNm2 		= AACTTSPTab.getRow(1).getCell(4).getStringCellValue();
            usrNmUdm 		= AACTTSPTab.getRow(1).getCell(5).getStringCellValue();
            pwdUdm 			= AACTTSPTab.getRow(1).getCell(6).getStringCellValue();
            usrNmSdm 		= AACTTSPTab.getRow(1).getCell(7).getStringCellValue();
            pwdSdm 			= AACTTSPTab.getRow(1).getCell(8).getStringCellValue();
            udmUrl 			= AACTTSPTab.getRow(1).getCell(9).getStringCellValue();
            sdmUrl 			= AACTTSPTab.getRow(1).getCell(10).getStringCellValue();
            browserTyp 		= AACTTSPTab.getRow(1).getCell(11).getStringCellValue();
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

    public String getDeviceNm2() {
        return deviceNm2;
    }

    public void setDeviceNm2(String deviceNm2) {
        this.deviceNm2 = deviceNm2;
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
