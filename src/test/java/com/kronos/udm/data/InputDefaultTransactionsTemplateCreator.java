package com.kronos.udm.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.kronos.udm.utils.AppConstants;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InputDefaultTransactionsTemplateCreator {

    String customerNm;
    String profileNm;
    String transTemplateNm;
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

    public InputDefaultTransactionsTemplateCreator() {
        FileInputStream fs = null;
        XSSFWorkbook wb = null;
        try {
            fs = new FileInputStream(AppConstants.UDMREGRESSION_PROPERTY);
            wb = new XSSFWorkbook(fs);
            
            XSSFSheet DTTCTab	= wb.getSheet("DTTCTab");
            customerNm 			= DTTCTab.getRow(1).getCell(0).getStringCellValue();
            profileNm 			= DTTCTab.getRow(1).getCell(1).getStringCellValue();
            transTemplateNm 	= DTTCTab.getRow(1).getCell(2).getStringCellValue();
            usrNmUdm 			= DTTCTab.getRow(1).getCell(3).getStringCellValue();
            pwdUdm 				= DTTCTab.getRow(1).getCell(4).getStringCellValue();
            usrNmSdm 			= DTTCTab.getRow(1).getCell(5).getStringCellValue();
            pwdSdm 				= DTTCTab.getRow(1).getCell(6).getStringCellValue();
            udmUrl 				= DTTCTab.getRow(1).getCell(7).getStringCellValue();
            sdmUrl 				= DTTCTab.getRow(1).getCell(8).getStringCellValue();

            primaryServer = DTTCTab.getRow(1).getCell(9).getStringCellValue();
            primaryServerInstanceId = DTTCTab.getRow(1).getCell(10).getStringCellValue();
            port = DTTCTab.getRow(1).getCell(11).getStringCellValue();
            browserTyp = DTTCTab.getRow(1).getCell(12).getStringCellValue();
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

    public String getTransTemplateNm() {
        return transTemplateNm;
    }

    public void setTransTemplateNm(String transTemplateNm) {
        this.transTemplateNm = transTemplateNm;
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
