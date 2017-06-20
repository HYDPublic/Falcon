package com.kronos.udm.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.kronos.udm.utils.AppConstants;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InputDeviceCreator {

    String customerNm;
    String deviceId;
    String noOfDevicesToAdd;
    String deviceNm;
    String usrNmUdm;
    String pwdUdm;
    String usrNmSdm;
    String pwdSdm;
    String udmUrl;
    String sdmUrl;
    String browserTyp;

    public InputDeviceCreator() {
        FileInputStream fs = null;
        XSSFWorkbook wb = null;
        try {
            fs = new FileInputStream(AppConstants.UDMREGRESSION_PROPERTY);
            wb = new XSSFWorkbook(fs);
            //XSSFSheet DCTab = wb.getSheetAt(0);
            XSSFSheet DCTab = wb.getSheet("DCTab");
            customerNm = DCTab.getRow(1).getCell(0).getStringCellValue();
            deviceId = DCTab.getRow(1).getCell(1).getStringCellValue();
            DCTab.getRow(1).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            noOfDevicesToAdd = DCTab.getRow(1).getCell(2).getStringCellValue();
            DCTab.getRow(1).getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            deviceNm = DCTab.getRow(1).getCell(3).getStringCellValue();
            usrNmUdm = DCTab.getRow(1).getCell(4).getStringCellValue();
            pwdUdm = DCTab.getRow(1).getCell(5).getStringCellValue();
            usrNmSdm = DCTab.getRow(1).getCell(6).getStringCellValue();
            pwdSdm = DCTab.getRow(1).getCell(7).getStringCellValue();
            udmUrl = DCTab.getRow(1).getCell(8).getStringCellValue();
            sdmUrl = DCTab.getRow(1).getCell(9).getStringCellValue();
            browserTyp = DCTab.getRow(1).getCell(10).getStringCellValue();
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getNoOfDevicesToAdd() {
        return noOfDevicesToAdd;
    }

    public void setNoOfDevicesToAdd(String noOfDevicesToAdd) {
        this.noOfDevicesToAdd = noOfDevicesToAdd;
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
