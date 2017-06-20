package com.kronos.udm.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InputSoftKeyScheduleCreator {

    String customerNm;
    String tagNm;
    String deviceNm;
    String usrNmUdm;
    String pwdUdm;
    String usrNmSdm;
    String pwdSdm;
    String udmUrl;
    String sdmUrl;
    String browserTyp;

    public InputSoftKeyScheduleCreator() {
        FileInputStream fs = null;
        XSSFWorkbook wb = null;
        try {
            fs = new FileInputStream(AppConstants.UDMREGRESSION_PROPERTY);
            wb = new XSSFWorkbook(fs);
            XSSFSheet sheet1 = wb.getSheetAt(0);
            customerNm = sheet1.getRow(1).getCell(0).getStringCellValue();
            tagNm = sheet1.getRow(1).getCell(1).getStringCellValue();
            sheet1.getRow(1).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            deviceNm = sheet1.getRow(1).getCell(2).getStringCellValue();
            usrNmUdm = sheet1.getRow(1).getCell(3).getStringCellValue();
            pwdUdm = sheet1.getRow(1).getCell(4).getStringCellValue();
            usrNmSdm = sheet1.getRow(1).getCell(5).getStringCellValue();
            pwdSdm = sheet1.getRow(1).getCell(6).getStringCellValue();
            udmUrl = sheet1.getRow(1).getCell(7).getStringCellValue();
            sdmUrl = sheet1.getRow(1).getCell(8).getStringCellValue();
            browserTyp = sheet1.getRow(1).getCell(9).getStringCellValue();
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
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

    public String getTagNm() {
        return tagNm;
    }

    public void setTagNm(String tagNm) {
        this.tagNm = tagNm;
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
