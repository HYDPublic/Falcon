package com.kronos.udm.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.kronos.udm.utils.AppConstants;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InputCommSettingsCreator {

    String customerNm;
    String profileNm;
    String commSettingNm;
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

    public InputCommSettingsCreator() {
        FileInputStream fs = null;
        XSSFWorkbook wb = null;
        try {
            fs = new FileInputStream(AppConstants.UDMREGRESSION_PROPERTY);
            wb = new XSSFWorkbook(fs);
            //XSSFSheet CSTab = wb.getSheetAt(3);
            XSSFSheet CSTab = wb.getSheet("CSTab");
            customerNm = CSTab.getRow(1).getCell(0).getStringCellValue();
            profileNm = CSTab.getRow(1).getCell(1).getStringCellValue();
            commSettingNm = CSTab.getRow(1).getCell(2).getStringCellValue();
            usrNmUdm = CSTab.getRow(1).getCell(3).getStringCellValue();
            pwdUdm = CSTab.getRow(1).getCell(4).getStringCellValue();
            usrNmSdm = CSTab.getRow(1).getCell(5).getStringCellValue();
            pwdSdm = CSTab.getRow(1).getCell(6).getStringCellValue();
            udmUrl = CSTab.getRow(1).getCell(7).getStringCellValue();
            sdmUrl = CSTab.getRow(1).getCell(8).getStringCellValue();

            primaryServer = CSTab.getRow(1).getCell(9).getStringCellValue();
            primaryServerInstanceId = CSTab.getRow(1).getCell(10).getStringCellValue();
            port = CSTab.getRow(1).getCell(11).getStringCellValue();
            browserTyp = CSTab.getRow(1).getCell(12).getStringCellValue();
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

    public String getCommSettingNm() {
        return commSettingNm;
    }

    public void setCommSettingNm(String commSettingNm) {
        this.commSettingNm = commSettingNm;
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

    public String getPrimaryServer() {
        return primaryServer;
    }

    public void setPrimaryServer(String primaryServer) {
        this.primaryServer = primaryServer;
    }

    public String getPrimaryServerInstanceId() {
        return primaryServerInstanceId;
    }

    public void setPrimaryServerInstanceId(String primaryServerInstanceId) {
        this.primaryServerInstanceId = primaryServerInstanceId;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getBrowserTyp() {
        return browserTyp;
    }

    public void setbBowserTyp(String browserTyp) {
        this.browserTyp = browserTyp;
    }

}
