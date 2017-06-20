package com.kronos.udm.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.kronos.udm.utils.AppConstants;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InputProfileToMultipleDevicesAssigner {
    String customerNm;
    String profileNm;
    String deviceNm;
    String deviceNm2;
    String usrNmUdm;
    String pwdUdm;
    String usrNmSdm;
    String pwdSdm;
    String udmUrl;
    String sdmUrl;
    String browserTyp;

    public InputProfileToMultipleDevicesAssigner() {
        FileInputStream fs = null;
        XSSFWorkbook wb = null;
        try {
            fs = new FileInputStream(AppConstants.UDMREGRESSION_PROPERTY);
            wb = new XSSFWorkbook(fs);
            //XSSFSheet AMDTSPTab = wb.getSheetAt(5);
            XSSFSheet AMDTSPTab = wb.getSheet("AMDTSPTab");
            customerNm = AMDTSPTab.getRow(1).getCell(0).getStringCellValue();
            profileNm = AMDTSPTab.getRow(1).getCell(1).getStringCellValue();
            deviceNm = AMDTSPTab.getRow(1).getCell(2).getStringCellValue();

            deviceNm2 = AMDTSPTab.getRow(1).getCell(3).getStringCellValue();
            usrNmUdm = AMDTSPTab.getRow(1).getCell(4).getStringCellValue();
            pwdUdm = AMDTSPTab.getRow(1).getCell(5).getStringCellValue();
            usrNmSdm = AMDTSPTab.getRow(1).getCell(6).getStringCellValue();
            pwdSdm = AMDTSPTab.getRow(1).getCell(7).getStringCellValue();
            udmUrl = AMDTSPTab.getRow(1).getCell(8).getStringCellValue();
            sdmUrl = AMDTSPTab.getRow(1).getCell(9).getStringCellValue();
            browserTyp = AMDTSPTab.getRow(1).getCell(10).getStringCellValue();
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
