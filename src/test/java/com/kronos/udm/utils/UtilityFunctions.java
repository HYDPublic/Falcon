package com.kronos.udm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import autoitx4java.AutoItX;
import com.jacob.com.LibraryLoader;


public class UtilityFunctions {

    public static Properties ReadPropertyFile(String sLocation) {
        File propertiesFile = new File(sLocation);
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(propertiesFile);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        Properties prop = new Properties();
        try {
            prop.load(fileInput);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return prop;
    }
   
    
    // Below method is used to handle windows dialog required for migration tests
    public static void openFile(String sFileName) {
        String jacobDllVersionToUse = "jacob-1.18-x64.dll";
        File file = new File("lib", jacobDllVersionToUse);
        System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());

        AutoItX x = new AutoItX();
        x.winActivate("Open");
        try {
            x.send(sFileName);
            Thread.sleep(2000);
            x.controlClick("Open", "", "1");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* If the tests COMPLETES successfully or terminates abruptly
     * then it leaves behind leftover DRIVER and BROWSER instances
     * Below method closes those leftovers
     * SEEMS ISSUE WITH WEB-DRIVER
     *  */
    public static void cleanupBrowserInstances() {
        try {
            //To Be Implemented
            /*Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
			Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
