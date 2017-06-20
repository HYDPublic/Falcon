package com.kronos.udm.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.kronos.udm.utils.AppConstants;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InputUpdateSharedAppConfigTemplate {
	String customerNm;
    String profileNm;
    String appConfigNm;
    String scheduleforEmp;
    String usrNmUdm;
    String pwdUdm;
    String usrNmSdm;
    String pwdSdm;
    String udmUrl;
    String sdmUrl;
    String browserTyp;
    public InputUpdateSharedAppConfigTemplate() {
        FileInputStream fs = null;
        XSSFWorkbook wb = null;
        try {
	            fs = new FileInputStream(AppConstants.UDMREGRESSION_PROPERTY);
	            wb = new XSSFWorkbook(fs);
	            //XSSFSheet USACTTab = wb.getSheetAt(12);
	            XSSFSheet USACTTab = wb.getSheet("USACTTab");
	            customerNm 	= USACTTab.getRow(1).getCell(0).getStringCellValue();
	            profileNm 	= USACTTab.getRow(1).getCell(1).getStringCellValue();
	            appConfigNm = USACTTab.getRow(1).getCell(2).getStringCellValue();
	         scheduleforEmp = USACTTab.getRow(1).getCell(3).getStringCellValue();
	            usrNmUdm 	= USACTTab.getRow(1).getCell(4).getStringCellValue();
	            pwdUdm 		= USACTTab.getRow(1).getCell(5).getStringCellValue();
	            usrNmSdm 	= USACTTab.getRow(1).getCell(6).getStringCellValue();
	            pwdSdm 		= USACTTab.getRow(1).getCell(7).getStringCellValue();
	            udmUrl 		= USACTTab.getRow(1).getCell(8).getStringCellValue();
	            sdmUrl 		= USACTTab.getRow(1).getCell(9).getStringCellValue();
	            browserTyp 	= USACTTab.getRow(1).getCell(10).getStringCellValue();
        } catch (FileNotFoundException fof) {
            fof.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
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
    
    public String getScheduleforEmp() {
        return scheduleforEmp;
    }

    public void setScheduleforEmp(String scheduleforEmp) {
        this.scheduleforEmp = scheduleforEmp;
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
