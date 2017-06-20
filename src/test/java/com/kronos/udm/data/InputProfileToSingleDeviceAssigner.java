package com.kronos.udm.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.kronos.udm.utils.AppConstants;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InputProfileToSingleDeviceAssigner {
    String customerNm;
    String profileNm;
    String deviceNm;
    String usrNmUdm;
    String pwdUdm;
    String usrNmSdm;
    String pwdSdm;
    String udmUrl;
    String sdmUrl;
    String browserTyp;

    public InputProfileToSingleDeviceAssigner() {
        FileInputStream fs = null;
        XSSFWorkbook wb = null;
        try {
            fs = new FileInputStream(AppConstants.UDMREGRESSION_PROPERTY);
            wb = new XSSFWorkbook(fs);
            //XSSFSheet ASDTSPTab = wb.getSheetAt(4);
            XSSFSheet ASDTSPTab = wb.getSheet("ASDTSPTab");
            customerNm = ASDTSPTab.getRow(1).getCell(0).getStringCellValue();
            profileNm = ASDTSPTab.getRow(1).getCell(1).getStringCellValue();
            ASDTSPTab.getRow(1).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            deviceNm = ASDTSPTab.getRow(1).getCell(2).getStringCellValue();
            //cell.setCellType(Cell.CELL_TYPE_STRING)
            usrNmUdm = ASDTSPTab.getRow(1).getCell(3).getStringCellValue();
            pwdUdm = ASDTSPTab.getRow(1).getCell(4).getStringCellValue();
            usrNmSdm = ASDTSPTab.getRow(1).getCell(5).getStringCellValue();
            pwdSdm = ASDTSPTab.getRow(1).getCell(6).getStringCellValue();
            udmUrl = ASDTSPTab.getRow(1).getCell(7).getStringCellValue();
            sdmUrl = ASDTSPTab.getRow(1).getCell(8).getStringCellValue();
            browserTyp = ASDTSPTab.getRow(1).getCell(9).getStringCellValue();
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
