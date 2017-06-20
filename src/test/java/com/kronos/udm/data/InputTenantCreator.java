package com.kronos.udm.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.kronos.udm.utils.AppConstants;

public class InputTenantCreator {
	String tenantNm;
	String solutionId;
	String envTyp;
	String tenantId;
	String name;
	String title;
	String email;
	String serverTyp;
	String hostUrl;
	String hostUsr;
	String hostPwd;
	String confirmPwd;
    String usrNmUdm;
    String pwdUdm;
    String usrNmSdm;
    String pwdSdm;
    String udmUrl;
    String sdmUrl;
    String browserTyp;

    public InputTenantCreator() {
        FileInputStream fs = null;
        XSSFWorkbook wb = null;
        try {
            fs = new FileInputStream(AppConstants.UDMREGRESSION_PROPERTY);
            wb = new XSSFWorkbook(fs);
            XSSFSheet AOASHPTSDVTab = wb.getSheet("CCTab");
            tenantNm 		= AOASHPTSDVTab.getRow(1).getCell(0).getStringCellValue();
            solutionId 		= AOASHPTSDVTab.getRow(1).getCell(1).getStringCellValue();
            envTyp	 		= AOASHPTSDVTab.getRow(1).getCell(2).getStringCellValue();
            tenantId 		= AOASHPTSDVTab.getRow(1).getCell(3).getStringCellValue();
            name 			= AOASHPTSDVTab.getRow(1).getCell(4).getStringCellValue();
            title		 	= AOASHPTSDVTab.getRow(1).getCell(5).getStringCellValue();
            email		 	= AOASHPTSDVTab.getRow(1).getCell(6).getStringCellValue();
            serverTyp	 	= AOASHPTSDVTab.getRow(1).getCell(7).getStringCellValue();
            hostUrl 		= AOASHPTSDVTab.getRow(1).getCell(8).getStringCellValue();
            hostUsr		 	= AOASHPTSDVTab.getRow(1).getCell(9).getStringCellValue();
            hostPwd 		= AOASHPTSDVTab.getRow(1).getCell(10).getStringCellValue();
            confirmPwd 		= AOASHPTSDVTab.getRow(1).getCell(11).getStringCellValue();

            usrNmUdm 		= AOASHPTSDVTab.getRow(1).getCell(12).getStringCellValue();
            pwdUdm 			= AOASHPTSDVTab.getRow(1).getCell(13).getStringCellValue();
            usrNmSdm 		= AOASHPTSDVTab.getRow(1).getCell(14).getStringCellValue();
            pwdSdm 			= AOASHPTSDVTab.getRow(1).getCell(15).getStringCellValue();
            udmUrl 			= AOASHPTSDVTab.getRow(1).getCell(16).getStringCellValue();
            sdmUrl 			= AOASHPTSDVTab.getRow(1).getCell(17).getStringCellValue();
            browserTyp 		= AOASHPTSDVTab.getRow(1).getCell(18).getStringCellValue();
        } catch (FileNotFoundException e) {
         
            e.printStackTrace();
        } catch (IOException e) {
           
            e.printStackTrace();
        }
    }

    public String getTenantNm() {
        return tenantNm ;
    }

    public String getSolutionId() {
        return solutionId;
    }

    public String getEnvTyp() {
        return envTyp;
    }
    
    public String getTenantId() {
        return tenantId;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getServerTyp() {
        return serverTyp;
    }
    
    public String getHostUrl() {
        return hostUrl;
    }
    
    public String getHostUsr() {
        return hostUsr;
    }
    
    public String getHostPwd() {
    	return hostPwd;
    }

    public String getConfirmPwd() {
    	return confirmPwd;
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
