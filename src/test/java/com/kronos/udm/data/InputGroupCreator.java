package com.kronos.udm.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.kronos.udm.utils.AppConstants;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InputGroupCreator {
    String customerNm;
    String grpNm;
    String deviceNm;
    String usrNmUdm;
    String pwdUdm;
    String usrNmSdm;
    String pwdSdm;
    String udmUrl;
    String sdmUrl;
    String browserTyp;

    public InputGroupCreator() {
        FileInputStream fs	= null;
        XSSFWorkbook wb 	= null;
        try {
            fs = new FileInputStream(AppConstants.UDMREGRESSION_PROPERTY);
            wb = new XSSFWorkbook(fs);
            //XSSFSheet GrpTab = wb.getSheetAt(14);
            XSSFSheet GrpTab = wb.getSheet("GrpTab");
            customerNm = GrpTab.getRow(1).getCell(0).getStringCellValue();
            grpNm = GrpTab.getRow(1).getCell(1).getStringCellValue();
            GrpTab.getRow(1).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            deviceNm = GrpTab.getRow(1).getCell(2).getStringCellValue();
            usrNmUdm = GrpTab.getRow(1).getCell(3).getStringCellValue();
            pwdUdm = GrpTab.getRow(1).getCell(4).getStringCellValue();
            usrNmSdm = GrpTab.getRow(1).getCell(5).getStringCellValue();
            pwdSdm = GrpTab.getRow(1).getCell(6).getStringCellValue();
            udmUrl = GrpTab.getRow(1).getCell(7).getStringCellValue();
            sdmUrl = GrpTab.getRow(1).getCell(8).getStringCellValue();
            browserTyp = GrpTab.getRow(1).getCell(9).getStringCellValue();
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

    public String getGrpNm() {
        return grpNm;
    }

    public void setGgpNm(String grpNm) {
        this.grpNm = grpNm;
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
