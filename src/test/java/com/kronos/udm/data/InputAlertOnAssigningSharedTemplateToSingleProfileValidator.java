package com.kronos.udm.data;

import java.io.FileInputStream;
import com.kronos.udm.utils.AppConstants;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InputAlertOnAssigningSharedTemplateToSingleProfileValidator {
    String customerNm;
    String profileNm;
    String appConfigNm;
    String usrNmUdm;
    String pwdUdm;
    String usrNmSdm;
    String pwdSdm;
    String udmUrl;
    String sdmUrl;
    String browserTyp;

    public InputAlertOnAssigningSharedTemplateToSingleProfileValidator() {
        FileInputStream fs = null;
        XSSFWorkbook wb = null;
        try {
            fs = new FileInputStream(AppConstants.UDMREGRESSION_PROPERTY);
            wb = new XSSFWorkbook(fs);
            //XSSFSheet AOASTTSPTab = wb.getSheetAt(11);
            XSSFSheet AOASTTSPTab = wb.getSheet("AOASTTSPTab");
            customerNm 		= AOASTTSPTab.getRow(1).getCell(0).getStringCellValue();
            profileNm 		= AOASTTSPTab.getRow(1).getCell(1).getStringCellValue();
            appConfigNm 	= AOASTTSPTab.getRow(1).getCell(2).getStringCellValue();
            usrNmUdm 		= AOASTTSPTab.getRow(1).getCell(3).getStringCellValue();
            pwdUdm 			= AOASTTSPTab.getRow(1).getCell(4).getStringCellValue();
            usrNmSdm 		= AOASTTSPTab.getRow(1).getCell(5).getStringCellValue();
            pwdSdm 			= AOASTTSPTab.getRow(1).getCell(6).getStringCellValue();
            udmUrl 			= AOASTTSPTab.getRow(1).getCell(7).getStringCellValue();
            sdmUrl 			= AOASTTSPTab.getRow(1).getCell(8).getStringCellValue();
            browserTyp 		= AOASTTSPTab.getRow(1).getCell(9).getStringCellValue();
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

    public void setProfileNm1(String profileNm1) {
        this.profileNm = profileNm1;
    }

    public String getAppConfigNm() {
        return appConfigNm;
    }

    public void setAppConfigNm(String appConfigNm) {
        this.appConfigNm = appConfigNm;
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